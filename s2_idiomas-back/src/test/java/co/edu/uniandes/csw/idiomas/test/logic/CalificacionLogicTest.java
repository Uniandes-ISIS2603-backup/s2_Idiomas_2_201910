/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.test.logic;

import co.edu.uniandes.csw.idiomas.ejb.CalificacionLogic;
import co.edu.uniandes.csw.idiomas.entities.CalificacionEntity;
import co.edu.uniandes.csw.idiomas.persistence.CalificacionPersistence;
import co.edu.uniandes.csw.idiomas.entities.ActividadEntity;
import co.edu.uniandes.csw.idiomas.entities.AdministradorEntity;
import co.edu.uniandes.csw.idiomas.entities.CoordinadorEntity;
import co.edu.uniandes.csw.idiomas.entities.GrupoDeInteresEntity;
import co.edu.uniandes.csw.idiomas.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.junit.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 * Pruebas de logica de Calificaciones
 *
 * @author jdruedaa
 */
@RunWith(Arquillian.class)
public class CalificacionLogicTest {

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private CalificacionLogic calificacionLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<CalificacionEntity> data = new ArrayList<CalificacionEntity>();

    private List<ActividadEntity> actividadData = new ArrayList();
    
    private List<AdministradorEntity> administradorData = new ArrayList();
    
    private List<CoordinadorEntity> coordinadorData = new ArrayList();
    
    private List<GrupoDeInteresEntity> grupoDeInteresData = new ArrayList();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CalificacionEntity.class.getPackage())
                .addPackage(CalificacionLogic.class.getPackage())
                .addPackage(CalificacionPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * Configuración inicial de la prueba.
     */
    @Before
    public void configTest() {
        try {
            utx.begin();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * Limpia las tablas que están implicadas en la prueba.
     */
    private void clearData() {
        em.createQuery("delete from CalificacionEntity").executeUpdate();
        em.createQuery("delete from ActividadEntity").executeUpdate();
        em.createQuery("delete from AdministradorEntity").executeUpdate();
        em.createQuery("delete from CoordinadorEntity").executeUpdate();
        em.createQuery("delete from GrupoDeInteresEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            ActividadEntity actividad = factory.manufacturePojo(ActividadEntity.class);
            em.persist(actividad);
            actividadData.add(actividad);
        }
        for (int i = 0; i < 3; i++) {
            CalificacionEntity entity = factory.manufacturePojo(CalificacionEntity.class);
            entity.setActividad(actividadData.get(0));
            entity.setCalificacion(3);

            em.persist(entity);
            data.add(entity);
        }
        for (int i = 0; i < 3; i++) {
            AdministradorEntity administrador = factory.manufacturePojo(AdministradorEntity.class);
            em.persist(administrador);
            administradorData.add(administrador);
        }
        for (int i = 0; i < 3; i++) {
            CalificacionEntity entity = factory.manufacturePojo(CalificacionEntity.class);
            entity.setAdministrador(administradorData.get(0));
            entity.setCalificacion(2);

            em.persist(entity);
            data.add(entity);
        }
        for (int i = 0; i < 3; i++) {
            CoordinadorEntity coordinador = factory.manufacturePojo(CoordinadorEntity.class);
            em.persist(coordinador);
            coordinadorData.add(coordinador);
        }
        for (int i = 0; i < 3; i++) {
            CalificacionEntity entity = factory.manufacturePojo(CalificacionEntity.class);
            entity.setCoordinador(coordinadorData.get(0));
            entity.setCalificacion(1);

            em.persist(entity);
            data.add(entity);
        }
        for (int i = 0; i < 3; i++) {
            GrupoDeInteresEntity grupoDeInteres = factory.manufacturePojo(GrupoDeInteresEntity.class);
            em.persist(grupoDeInteres);
            grupoDeInteresData.add(grupoDeInteres);
        }
        for (int i = 0; i < 3; i++) {
            CalificacionEntity entity = factory.manufacturePojo(CalificacionEntity.class);
            entity.setGrupo(grupoDeInteresData.get(0));
            entity.setCalificacion(5);
            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Prueba para crear una Calificacion
     *
     * @throws co.edu.uniandes.csw.idiomas.exceptions.BusinessLogicException
     */
    @Test
    public void createCalificacionTest() throws BusinessLogicException {
        CalificacionEntity newEntity = factory.manufacturePojo(CalificacionEntity.class);
        newEntity.setActividad(actividadData.get(0));
        newEntity.setCalificacion(4);
        CalificacionEntity result = calificacionLogic.createCalificacion(newEntity);
        Assert.assertNotNull(result);
        CalificacionEntity entity = em.find(CalificacionEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getCalificacion(), entity.getCalificacion());
        Assert.assertEquals(newEntity.getMensaje(), entity.getMensaje());
    }

    /**
     * Prueba para crear un Calificacion con Calificacion nula
     *
     * @throws co.edu.uniandes.csw.idiomas.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createCalificacionTestConCalificacionNula() throws BusinessLogicException {
        CalificacionEntity newEntity = factory.manufacturePojo(CalificacionEntity.class);
        newEntity.setActividad(actividadData.get(0));
        newEntity.setCalificacion(null);
        calificacionLogic.createCalificacion(newEntity);
    }
    
    /**
     * Prueba para crear un Calificacion con Calificacion fuera de límites
     *
     * @throws co.edu.uniandes.csw.idiomas.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createCalificacionTestConCalificacionFueraDeLimites() throws BusinessLogicException {
        CalificacionEntity newEntity = factory.manufacturePojo(CalificacionEntity.class);
        newEntity.setActividad(actividadData.get(0));
        newEntity.setCalificacion(6);
        calificacionLogic.createCalificacion(newEntity);
    }
    
    /**
     * Prueba para crear una Calificacion con Mensaje fuera de límites
     *
     * @throws co.edu.uniandes.csw.idiomas.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createCalificacionTestConMensajeFueraDeLimites() throws BusinessLogicException {
        CalificacionEntity newEntity = factory.manufacturePojo(CalificacionEntity.class);
        newEntity.setActividad(actividadData.get(0));
        newEntity.setMensaje("Lorem Ipsum es simplemente el texto de relleno de las imprentas y archivos de texto. Lorem Ipsum ha sido el texto de relleno estándar de las industrias desde el año 1500, cuando un impresor (N. del T. persona que se dedica a la imprenta) desconocido usó");
        newEntity.setCalificacion(3);
        calificacionLogic.createCalificacion(newEntity);
    }

    /**
     * Prueba para crear un Calificacion con actividad en null.
     *
     * @throws co.edu.uniandes.csw.idiomas.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createCalificacionTestSinRelacion() throws BusinessLogicException {
        CalificacionEntity newEntity = factory.manufacturePojo(CalificacionEntity.class);
        newEntity.setActividad(null);
        newEntity.setAdministrador(null);
        newEntity.setCoordinador(null);
        newEntity.setGrupo(null);
        calificacionLogic.createCalificacion(newEntity);
    }
    
    /**
     * Prueba para crear una Calificacion con una actividad que no existe.
     *
     * @throws co.edu.uniandes.csw.idiomas.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createCalificacionTestConActividadInexistente() throws BusinessLogicException {
        CalificacionEntity newEntity = factory.manufacturePojo(CalificacionEntity.class);
        ActividadEntity actividadEntity = new ActividadEntity();
        actividadEntity.setId(Long.MIN_VALUE);
        newEntity.setActividad(actividadEntity);
        calificacionLogic.createCalificacion(newEntity);
    }
    
    /**
     * Prueba para crear un Calificacion con un administrador que no existe.
     *
     * @throws co.edu.uniandes.csw.idiomas.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createCalificacionTestConAdministradorInexistente() throws BusinessLogicException {
        CalificacionEntity newEntity = factory.manufacturePojo(CalificacionEntity.class);
        AdministradorEntity administradorEntity = new AdministradorEntity();
        administradorEntity.setId(Long.MIN_VALUE);
        newEntity.setAdministrador(administradorEntity);
        calificacionLogic.createCalificacion(newEntity);
    }
    
    /**
     * Prueba para crear un Calificacion con un coordinador que no existe.
     *
     * @throws co.edu.uniandes.csw.idiomas.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createCalificacionTestConCoordinadorInexistente() throws BusinessLogicException {
        CalificacionEntity newEntity = factory.manufacturePojo(CalificacionEntity.class);
        CoordinadorEntity coordinadorEntity = new CoordinadorEntity();
        coordinadorEntity.setId(Long.MIN_VALUE);
        newEntity.setCoordinador(coordinadorEntity);
        calificacionLogic.createCalificacion(newEntity);
    }
    
    /**
     * Prueba para crear un Calificacion con un grupoDeInteres que no existe.
     *
     * @throws co.edu.uniandes.csw.idiomas.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createCalificacionTestConGrupoDeInteresInexistente() throws BusinessLogicException {
        CalificacionEntity newEntity = factory.manufacturePojo(CalificacionEntity.class);
        GrupoDeInteresEntity grupoDeInteresEntity = new GrupoDeInteresEntity();
        grupoDeInteresEntity.setId(Long.MIN_VALUE);
        newEntity.setGrupo(grupoDeInteresEntity);
        calificacionLogic.createCalificacion(newEntity);
    }

    /**
     * Prueba para consultar la lista de Calificaciones.
     */
    @Test
    public void getCalificacionesTest() {
        List<CalificacionEntity> list = calificacionLogic.getCalificaciones();
        Assert.assertEquals(data.size(), list.size());
        for (CalificacionEntity entity : list) {
            boolean found = false;
            for (CalificacionEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Calificacion.
     */
    @Test
    public void getCalificacionTest() {
        CalificacionEntity entity = data.get(0);
        CalificacionEntity resultEntity = calificacionLogic.getCalificacion(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getCalificacion(), resultEntity.getCalificacion());
        Assert.assertEquals(entity.getMensaje(), resultEntity.getMensaje());
    }

    /**
     * Prueba para actualizar una Calificacion.
     *
     * @throws co.edu.uniandes.csw.idiomas.exceptions.BusinessLogicException
     */
    @Test
    public void updateCalificacionTest() throws BusinessLogicException {
        CalificacionEntity entity = data.get(0);
        CalificacionEntity pojoEntity = factory.manufacturePojo(CalificacionEntity.class);
        pojoEntity.setId(entity.getId());
        pojoEntity.setCalificacion(3);
        calificacionLogic.updateCalificacion(pojoEntity.getId(), pojoEntity);
        CalificacionEntity resp = em.find(CalificacionEntity.class, entity.getId());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getCalificacion(), resp.getCalificacion());
        Assert.assertEquals(pojoEntity.getMensaje(), resp.getMensaje());
    }

    /**
     * Prueba para actualizar una Calificacion con una Calificacion fuera de límites.
     *
     * @throws co.edu.uniandes.csw.idiomas.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void updateCalificacionTestConCalificacionFueraDeLimites() throws BusinessLogicException {
        CalificacionEntity entity = data.get(0);
        CalificacionEntity pojoEntity = factory.manufacturePojo(CalificacionEntity.class);
        pojoEntity.setCalificacion(6);
        pojoEntity.setId(entity.getId());
        calificacionLogic.updateCalificacion(pojoEntity.getId(), pojoEntity);
    }

    /**
     * Prueba para actualizar una Calificacion con un Mensaje fuera de límites.
     *
     * @throws co.edu.uniandes.csw.idiomas.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void updateCalificacionTestConMensajeFueraDeLimites() throws BusinessLogicException {
        CalificacionEntity entity = data.get(0);
        CalificacionEntity pojoEntity = factory.manufacturePojo(CalificacionEntity.class);
        pojoEntity.setMensaje("Lorem Ipsum es simplemente el texto de relleno de las imprentas y archivos de texto. Lorem Ipsum ha sido el texto de relleno estándar de las industrias desde el año 1500, cuando un impresor (N. del T. persona que se dedica a la imprenta) desconocido usó");
        pojoEntity.setId(entity.getId());
        calificacionLogic.updateCalificacion(pojoEntity.getId(), pojoEntity);
    }

    /**
     * Prueba para eliminar una Calificacion.
     *
     * @throws co.edu.uniandes.csw.idiomas.exceptions.BusinessLogicException
     */
    @Test
    public void deleteCalificacionTest() throws BusinessLogicException {
        CalificacionEntity entity = data.get(0);
        calificacionLogic.deleteCalificacion(entity.getId());
        CalificacionEntity deleted = em.find(CalificacionEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}

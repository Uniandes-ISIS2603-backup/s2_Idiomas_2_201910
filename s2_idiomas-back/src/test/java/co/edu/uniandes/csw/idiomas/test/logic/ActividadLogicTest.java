/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.test.logic;

import co.edu.uniandes.csw.idiomas.ejb.ActividadLogic;
import co.edu.uniandes.csw.idiomas.entities.ActividadEntity;
import co.edu.uniandes.csw.idiomas.entities.ComentarioActividadEntity;
import co.edu.uniandes.csw.idiomas.entities.CoordinadorEntity;
import co.edu.uniandes.csw.idiomas.entities.UsuarioEntity;
import co.edu.uniandes.csw.idiomas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.idiomas.persistence.ActividadPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 * Pruebas de lógica de Actividad
 * @author g.cubillosb
 */
@RunWith(Arquillian.class)
public class ActividadLogicTest {

    /**
     * Atributo que representa los datos aleatorios a ser creados.
     */
    private PodamFactory factory = new PodamFactoryImpl();

    /**
     * Inyección de dependencias con ActividadLogic.
     */
    @Inject
    private ActividadLogic actividadLogic;

    /**
     * Contexto de persistencia que se va a utilizar para acceder a la base 
     * de datos.
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * Variable para marcar las transacciones del "em" (Entity Manager) cuando
     * se crean/borran datos.
     */
    @Inject
    private UserTransaction utx;

    private List<ActividadEntity> data = new ArrayList<>();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ActividadEntity.class.getPackage())
                .addPackage(ActividadLogic.class.getPackage())
                .addPackage(ActividadPersistence.class.getPackage())
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
        em.createQuery("delete from UsuarioEntity").executeUpdate();
        em.createQuery("delete from ComentarioActividadEntity").executeUpdate();
        em.createQuery("delete from ActividadEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            ActividadEntity entity = factory.manufacturePojo(ActividadEntity.class);
            em.persist(entity);
            entity.setAsistentes(new ArrayList<>());
            entity.setComentarios(new ArrayList<>());
            entity.setCoordinadores(new ArrayList<>());
            // TODO : GC Poner calificación
            data.add(entity);
        }
        ActividadEntity actividad = data.get(2);
        UsuarioEntity entity = factory.manufacturePojo(UsuarioEntity.class);
        entity.getActividades().add(actividad);
        em.persist(entity);
        actividad.getAsistentes().add(entity);

        ComentarioActividadEntity comentarios = factory.manufacturePojo(ComentarioActividadEntity.class);
        comentarios.setActividad(data.get(1));
        em.persist(comentarios);
        data.get(1).getComentarios().add(comentarios);
        
//        CoordinadorEntity coordinador = factory.manufacturePojo(CoordinadorEntity.class);
//        coordinador.setActividadesCoordinadas((List<ActividadEntity>) data.get(1));
//        em.persist(coordinador);
//        data.get(1).getCoordinadores().add(coordinador);
        
    }

    /**
     * Prueba para crear un Actividad.
     * @throws co.edu.uniandes.csw.idiomas.exceptions.BusinessLogicException
     */
    @Test
    public void createActividadTest() throws BusinessLogicException {
        ActividadEntity newEntity = factory.manufacturePojo(ActividadEntity.class);
        ActividadEntity result = actividadLogic.createActividad(newEntity);
        Assert.assertNotNull(result);
        ActividadEntity entity = em.find(ActividadEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
        Assert.assertEquals(newEntity.getDescripcion(), entity.getDescripcion());
        Assert.assertEquals(newEntity.getFecha(), entity.getFecha());
        
    }

    /**
     * Prueba para consultar la lista de Actividades.
     */
    @Test
    public void getActividadesTest() {
        List<ActividadEntity> list = actividadLogic.getActividades();
        Assert.assertEquals(data.size(), list.size());
        for (ActividadEntity entity : list) {
            boolean found = false;
            for (ActividadEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Actividad.
     */
    @Test
    public void getActividadTest() {
        ActividadEntity entity = data.get(0);
        ActividadEntity resultEntity = actividadLogic.getActividad(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(resultEntity.getId(), entity.getId());
        Assert.assertEquals(resultEntity.getNombre(), entity.getNombre());
        Assert.assertEquals(resultEntity.getDescripcion(), entity.getDescripcion());
        Assert.assertEquals(resultEntity.getFecha(), entity.getFecha());
    }

    /**
     * Prueba para actualizar un Actividad.
     */
    @Test
    public void updateActividadTest() {
        ActividadEntity entity = data.get(0);
        ActividadEntity pojoEntity = factory.manufacturePojo(ActividadEntity.class);

        pojoEntity.setId(entity.getId());

        actividadLogic.updateActividad(pojoEntity.getId(), pojoEntity);

        ActividadEntity resp = em.find(ActividadEntity.class, entity.getId());

        Assert.assertEquals(resp.getId(), entity.getId());
        Assert.assertEquals(resp.getNombre(), entity.getNombre());
        Assert.assertEquals(resp.getDescripcion(), entity.getDescripcion());
        Assert.assertEquals(resp.getFecha(), entity.getFecha());
    }

    /**
     * Prueba para eliminar un Actividad
     *
     * @throws co.edu.uniandes.csw.idiomas.exceptions.BusinessLogicException
     */
    @Test
    public void deleteActividadTest() throws BusinessLogicException {
        ActividadEntity entity = data.get(0);
        actividadLogic.deleteActividad(entity.getId());
        ActividadEntity deleted = em.find(ActividadEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

//    /**
//     * Prueba para eliminar un Actividad asociado a un usuario
//     *
//     * 
//     * @throws co.edu.uniandes.csw.idiomas.exceptions.BusinessLogicException
//     */
//    @Test(expected = BusinessLogicException.class)
//    public void deleteActividadConUsuarioTest() throws BusinessLogicException {
//        actividadLogic.deleteActividad(data.get(2).getId());
//    }

    /**
     * Prueba para eliminar un Actividad asociado a un comentario
     *
     * 
     * @throws co.edu.uniandes.csw.idiomas.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void deleteActividadConComentarioTest() throws BusinessLogicException {
        actividadLogic.deleteActividad(data.get(1).getId());
    }
    
}

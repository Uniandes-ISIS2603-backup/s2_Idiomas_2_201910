/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.test.logic;

import co.edu.uniandes.csw.idiomas.ejb.EstadiaLogic;
import co.edu.uniandes.csw.idiomas.ejb.CoordinadorLogic;
import co.edu.uniandes.csw.idiomas.entities.EstadiaEntity;
import co.edu.uniandes.csw.idiomas.entities.ComentarioActividadEntity;
import co.edu.uniandes.csw.idiomas.entities.CoordinadorEntity;
import co.edu.uniandes.csw.idiomas.entities.UsuarioEntity;
import co.edu.uniandes.csw.idiomas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.idiomas.persistence.EstadiaPersistence;
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
 * Pruebas de lógica de Estadia
 * @author g.cubillosb
 */
@RunWith(Arquillian.class)
public class EstadiaLogicTest {

    /**
     * Atributo que representa los datos aleatorios a ser creados.
     */
    private PodamFactory factory = new PodamFactoryImpl();

    /**
     * Inyección de dependencias con EstadiaLogic.
     */
    @Inject
    private EstadiaLogic estadiaLogic;
    
    /**
     * Inyección de dependencias con CoordinadorLogic
     */
    @Inject
    private CoordinadorLogic corLogic;
    
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

    private List<EstadiaEntity> data = new ArrayList<>();
    
    private List<CoordinadorEntity> coordinadorData = new ArrayList<>();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(EstadiaEntity.class.getPackage())
                .addPackage(EstadiaLogic.class.getPackage())
                .addPackage(EstadiaPersistence.class.getPackage())
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
        em.createQuery("delete from EstadiaEntity").executeUpdate();
        em.createQuery("delete from CoordinadorEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++)
        {
            CoordinadorEntity coordinador = factory.manufacturePojo(CoordinadorEntity.class);
            em.persist(coordinador);
            coordinadorData.add(coordinador);
        }
        for (int i = 0; i < 3; i++) 
        {
            EstadiaEntity entity = factory.manufacturePojo(EstadiaEntity.class);
            em.persist(entity);
            entity.setAsistentes(new ArrayList<>());
            entity.setComentarios(new ArrayList<>());
            entity.setCoordinadores(new ArrayList<>());
            entity.getCoordinadores().add(coordinadorData.get(0));
            // TODO : GC Poner calificación
            data.add(entity);
            data.add(entity);
        }
    }

    /**
     * Prueba para crear un Estadia.
     * @throws co.edu.uniandes.csw.idiomas.exceptions.BusinessLogicException
     */
    @Test
    public void createEstadiaTest() throws BusinessLogicException {
        EstadiaEntity newEntity = factory.manufacturePojo(EstadiaEntity.class);
        CoordinadorEntity newCorEntity = factory.manufacturePojo(CoordinadorEntity.class);
        
        // TODO: GC Conectar con coordinador Logic
//        newCorEntity = corLogic.createCoordinador(newCorEntity);
//        newEntity.getCoordinadores().add(newCorEntity);
        EstadiaEntity result = estadiaLogic.createEstadia(newEntity);
        Assert.assertNotNull(result);
        EstadiaEntity entity = em.find(EstadiaEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
        
    }
    
    /**
     * Prueba para crear un Estadia con nombre inválido
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createEstadiaTestConNombreInvalido1() throws BusinessLogicException {
        EstadiaEntity newEntity = factory.manufacturePojo(EstadiaEntity.class);
        newEntity.setNombre("");
        estadiaLogic.createEstadia(newEntity);
    }

    /**
     * Prueba para crear un Estadia con nombre inválido
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createEstadiaTestConNombreInvalido2() throws BusinessLogicException {
        EstadiaEntity newEntity = factory.manufacturePojo(EstadiaEntity.class);
        newEntity.setNombre(null);
        estadiaLogic.createEstadia(newEntity);
    }
    
    /**
     * Prueba para crear un Estadia sin un coordinador
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    // TODO: GC Conectar con coordinador
//    @Test(expected = BusinessLogicException.class)
//    public void createEstadiaTestSinCoordinador() throws BusinessLogicException {
//        EstadiaEntity newEntity = factory.manufacturePojo(EstadiaEntity.class);
//        newEntity.setCoordinadores(null);
//        EstadiaLogic.createEstadia(newEntity);
//    }
    
    // TODO: GC Arreglar test.
//    /**
//     * Prueba para crear un Estadia ya existente.
//     *
//     * @throws co.edu.uniandes.csw.idiomas.exceptions.BusinessLogicException
//     */
//    @Test(expected = BusinessLogicException.class)
//    public void createEstadiaTestYaExistente() throws BusinessLogicException {
//        List<EstadiaEntity> estadias = estadiaLogic.getEstadias();
//        EstadiaEntity newEntity = estadias.get(0);
//        estadiaLogic.createEstadia(newEntity);
//    }
    
    /**
     * Prueba para crear un Estadia sin un pais válido.
     * @throws co.edu.uniandes.csw.idiomas.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createEstadiaTestConPaisInvalido1() throws BusinessLogicException {
        EstadiaEntity newEntity = factory.manufacturePojo(EstadiaEntity.class);
        newEntity.setPais("");
        estadiaLogic.createEstadia(newEntity);
    }
    
    /**
     * Prueba para crear un Estadia sin un pais válido.
     * @throws co.edu.uniandes.csw.idiomas.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createEstadiaTestConLugarInvalido2() throws BusinessLogicException {
        EstadiaEntity newEntity = factory.manufacturePojo(EstadiaEntity.class);
        newEntity.setPais(null);
        estadiaLogic.createEstadia(newEntity);
    }
    
    /**
     * Prueba para crear un Estadia sin un anfitrion.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    // TODO: GC Conectar con coordinador
//    @Test(expected = BusinessLogicException.class)
//    public void createEstadiaTestSinCoordinador() throws BusinessLogicException {
//        EstadiaEntity newEntity = factory.manufacturePojo(EstadiaEntity.class);
//        newEntity.setCoordinadores(null);
//        EstadiaLogic.createEstadia(newEntity);
//    }

    /**
     * Prueba para consultar un Estadia.
     */
    @Test
    public void getEstadiaTest() {
        EstadiaEntity entity = data.get(0);
        EstadiaEntity resultEntity = estadiaLogic.getEstadia(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(resultEntity.getId(), entity.getId());
        Assert.assertEquals(resultEntity.getNombre(), entity.getNombre());
        Assert.assertEquals(resultEntity.getDescripcion(), entity.getDescripcion());
        Assert.assertEquals(resultEntity.getFecha(), entity.getFecha());
    }

    /**
     * Prueba para actualizar un Estadia.
     * @throws co.edu.uniandes.csw.idiomas.exceptions.BusinessLogicException
     */
    @Test
    public void updateEstadiaTest() throws BusinessLogicException {
        EstadiaEntity entity = data.get(0);
        EstadiaEntity pojoEntity = factory.manufacturePojo(EstadiaEntity.class);

        pojoEntity.setId(entity.getId());

        estadiaLogic.updateEstadia(pojoEntity.getId(), pojoEntity);

        EstadiaEntity resp = em.find(EstadiaEntity.class, entity.getId());

        Assert.assertEquals(resp.getId(), entity.getId());
//        Assert.assertEquals(resp.getNombre(), entity.getNombre());
//        Assert.assertEquals(resp.getDescripcion(), entity.getDescripcion());
//        Assert.assertEquals(resp.getFecha(), entity.getFecha());
    }

    /**
     * Prueba para eliminar un Estadia
     *
     * @throws co.edu.uniandes.csw.idiomas.exceptions.BusinessLogicException
     */
    @Test
    public void deleteEstadiaTest() throws BusinessLogicException {
        EstadiaEntity entity = data.get(0);
        estadiaLogic.deleteEstadia(entity.getId());
        EstadiaEntity deleted = em.find(EstadiaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

//    /**
//     * Prueba para eliminar un Estadia asociado a un usuario
//     *
//     * 
//     * @throws co.edu.uniandes.csw.idiomas.exceptions.BusinessLogicException
//     */
//    @Test(expected = BusinessLogicException.class)
//    public void deleteEstadiaConUsuarioTest() throws BusinessLogicException {
//        EstadiaLogic.deleteEstadia(data.get(2).getId());
//    }

    /**
     * Prueba para eliminar un Estadia asociado a un comentario
     *
     * 
     * @throws co.edu.uniandes.csw.idiomas.exceptions.BusinessLogicException
     */
//    @Test(expected = BusinessLogicException.class)
//    public void deleteEstadiaConComentarioTest() throws BusinessLogicException {
//        EstadiaLogic.deleteEstadia(data.get(1).getId());
//    }
    
}

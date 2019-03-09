/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.test.persistence;

import co.edu.uniandes.csw.idiomas.entities.AnfitrionEntity;
import co.edu.uniandes.csw.idiomas.entities.AnfitrionEntity;
import co.edu.uniandes.csw.idiomas.persistence.AnfitrionPersistence;
import co.edu.uniandes.csw.idiomas.persistence.AnfitrionPersistence;
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
 *
 * @author j.barbosaj 201717575
 */
@RunWith(Arquillian.class)
public class AnfitrionPersistenceTest 
{ 
     /**
     * Inyección de la dependencia a la clase AnfitrionPersistence cuyos métodos
     * se van a probar.
     */
    @Inject
    private AnfitrionPersistence anfitrionPersistence;
    
     /**
     * Contexto de Persistencia que se va a utilizar para acceder a la Base de
     * datos por fuera de los métodos que se están probando.
     */
    @PersistenceContext 
    private EntityManager em;
    
     /**
     * Variable para martcar las transacciones del em anterior cuando se
     * crean/borran datos para las pruebas.
     */
    @Inject
    UserTransaction utx;
    
     /**
     * Lista que tiene los datos de prueba.
     */
    private List<AnfitrionEntity> data = new ArrayList<AnfitrionEntity>();
    
    
    
     /**
     *
     * @return Devuelve el jar que Arquillian va a desplegar en el Glassfish
     * embebido. El jar contiene las clases de Anfitrion, el descriptor de la
     * base de datos y el archivo beans.xml para resolver la inyección de
     * dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(AnfitrionEntity.class.getPackage())
                .addPackage(AnfitrionPersistence.class.getPackage())
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
            em.joinTransaction();
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
        em.createQuery("delete from AnfitrionEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            AnfitrionEntity entity = factory.manufacturePojo(AnfitrionEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }
    
    /**
     * Prueva el crear
     */
    @Test
    public void createAnfitrionTest()
    {
        PodamFactory factory = new PodamFactoryImpl();
        AnfitrionEntity newEntity = factory.manufacturePojo(AnfitrionEntity.class);
        AnfitrionEntity result = anfitrionPersistence.create(newEntity);
        
         Assert.assertNotNull(result);
         
         AnfitrionEntity entity = em.find(AnfitrionEntity.class, result.getId());

        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
    }
    
    /**
     * Prueba para consultar la lista de Anfitriones.
     */
    @Test
    public void getAnfitrionesTest() {
        List<AnfitrionEntity> list = anfitrionPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (AnfitrionEntity ent : list) {
            boolean found = false;
            for (AnfitrionEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Anfitrion.
     */
    @Test
    public void getAnfitrionTest() {
        AnfitrionEntity entity = data.get(0);
        AnfitrionEntity newEntity = anfitrionPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getNombre(), newEntity.getNombre());
        Assert.assertEquals(entity.getContrasenia(), newEntity.getContrasenia());
       }

    /**
     * Prueba para actualizar un Anfitrion.
     */
    @Test
    public void updateAnfitrionTest() {
        AnfitrionEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        AnfitrionEntity newEntity = factory.manufacturePojo(AnfitrionEntity.class);

        newEntity.setId(entity.getId());

        anfitrionPersistence.update(newEntity);

        AnfitrionEntity resp = em.find(AnfitrionEntity.class, entity.getId());
        
        Assert.assertEquals(resp.getId(), newEntity.getId());
        Assert.assertEquals(resp.getNombre(), newEntity.getNombre());        
        Assert.assertEquals(resp.getContrasenia(), newEntity.getContrasenia());
    }

    /**
     * Prueba para eliminar un Anfitrion.
     */
    @Test
    public void deleteAnfitrionTest() {
        AnfitrionEntity entity = data.get(0);
        anfitrionPersistence.delete(entity.getId());
        AnfitrionEntity deleted = em.find(AnfitrionEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
    /**
     * Prueba para consultasr un Anfitrion por nombre.
     */
    @Test
    public void findAnfitrionByNameTest() {
        AnfitrionEntity entity = data.get(0);
        AnfitrionEntity newEntity = anfitrionPersistence.findByName(entity.getNombre());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getNombre(), newEntity.getNombre());

        newEntity = anfitrionPersistence.findByName(null);
        Assert.assertNull(newEntity);
        newEntity = anfitrionPersistence.findByName("");
        Assert.assertNull(newEntity);
    }
}

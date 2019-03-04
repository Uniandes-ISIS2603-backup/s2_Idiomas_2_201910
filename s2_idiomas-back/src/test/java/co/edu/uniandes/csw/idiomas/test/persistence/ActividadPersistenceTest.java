/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.test.persistence;

import co.edu.uniandes.csw.idiomas.entities.ActividadEntity;
import co.edu.uniandes.csw.idiomas.persistence.ActividadPersistence;
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
 * Pruebas de persistencia de Actividad
 *
 * @actividad g.cubillosb
 */
@RunWith(Arquillian.class)
public class ActividadPersistenceTest {

    /**
     * Inyecta la dependencia de ActividadPersistence.
     */
    @Inject
    private ActividadPersistence actividadPersistence;

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
    UserTransaction utx;

    /**
     * Lista de los datos de prueba.
     */
    private List<ActividadEntity> data = new ArrayList<ActividadEntity>();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ActividadEntity.class.getPackage())
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
        em.createQuery("delete from ActividadEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            ActividadEntity entity = factory.manufacturePojo(ActividadEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }

//    /**
//     * Prueba para crear un Actividad.
//     */
//    @Test
//    public void createActividadTest() {
//        PodamFactory factory = new PodamFactoryImpl();
//        ActividadEntity newEntity = factory.manufacturePojo(ActividadEntity.class);
//        ActividadEntity result = actividadPersistence.create(newEntity);
//
//        Assert.assertNotNull(result);
//
//        ActividadEntity entity = em.find(ActividadEntity.class, result.getId());
//
//        Assert.assertEquals(newEntity.getName(), entity.getName());
//    }

    /**
     * Prueba para consultar la lista de Actividades.
     */
    @Test
    public void getActividadesTest() {
        List<ActividadEntity> list = actividadPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (ActividadEntity ent : list) {
            boolean found = false;
            for (ActividadEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
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
        ActividadEntity newEntity = actividadPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
        Assert.assertEquals(entity.getFecha(), newEntity.getFecha());
    }

    /**
     * Prueba para actualizar un Actividad.
     */
    @Test
    public void updateActividadTest() {
        ActividadEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        ActividadEntity newEntity = factory.manufacturePojo(ActividadEntity.class);

        newEntity.setId(entity.getId());

        actividadPersistence.update(newEntity);

        ActividadEntity resp = em.find(ActividadEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
    }

    /**
     * Prueba para eliminar un Actividad.
     */
    @Test
    public void deleteActividadTest() {
        ActividadEntity entity = data.get(0);
        actividadPersistence.delete(entity.getId());
        ActividadEntity deleted = em.find(ActividadEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}


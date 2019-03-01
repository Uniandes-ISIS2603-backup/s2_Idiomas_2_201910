/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.test.persistence;

import co.edu.uniandes.csw.idiomas.entities.EstadiaEntity;
import co.edu.uniandes.csw.idiomas.persistence.EstadiaPersistence;
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
 * Pruebas de persistencia de Estadia
 *
 * @estadia g.cubillosb
 */
@RunWith(Arquillian.class)
public class EstadiaPersistenceTest {

    /**
     * Inyecta la dependencia de EstadiaPersistence.
     */
    @Inject
    private EstadiaPersistence estadiaPersistence;

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
    private List<EstadiaEntity> data = new ArrayList<>();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(EstadiaEntity.class.getPackage())
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
        em.createQuery("delete from EstadiaEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            EstadiaEntity entity = factory.manufacturePojo(EstadiaEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Prueba para crear un Estadia.
     */
    @Test
    public void createEstadiaTest() {
        PodamFactory factory = new PodamFactoryImpl();
        EstadiaEntity newEntity = factory.manufacturePojo(EstadiaEntity.class);
        EstadiaEntity result = estadiaPersistence.create(newEntity);

        Assert.assertNotNull(result);

        EstadiaEntity entity = em.find(EstadiaEntity.class, result.getId());

        Assert.assertEquals(newEntity.getName(), entity.getName());
    }

    /**
     * Prueba para consultar la lista de Estadias.
     */
    @Test
    public void getEstadiasTest() {
        List<EstadiaEntity> list = estadiaPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (EstadiaEntity ent : list) {
            boolean found = false;
            for (EstadiaEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Estadia.
     */
    @Test
    public void getEstadiaTest() {
        EstadiaEntity entity = data.get(0);
        EstadiaEntity newEntity = estadiaPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
        Assert.assertEquals(entity.getFecha(), newEntity.getFecha());
    }

    /**
     * Prueba para actualizar un Estadia.
     */
    @Test
    public void updateEstadiaTest() {
        EstadiaEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        EstadiaEntity newEntity = factory.manufacturePojo(EstadiaEntity.class);

        newEntity.setId(entity.getId());

        estadiaPersistence.update(newEntity);

        EstadiaEntity resp = em.find(EstadiaEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
    }

    /**
     * Prueba para eliminar un Estadia.
     */
    @Test
    public void deleteEstadiaTest() {
        EstadiaEntity entity = data.get(0);
        estadiaPersistence.delete(entity.getId());
        EstadiaEntity deleted = em.find(EstadiaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}

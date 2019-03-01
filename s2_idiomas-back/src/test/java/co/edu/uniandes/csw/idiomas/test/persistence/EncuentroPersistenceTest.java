/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.test.persistence;

import co.edu.uniandes.csw.idiomas.entities.EncuentroEntity;
import co.edu.uniandes.csw.idiomas.persistence.EncuentroPersistence;
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
 * Pruebas de persistencia de Encuentro
 *
 * @encuentro g.cubillosb
 */
@RunWith(Arquillian.class)
public class EncuentroPersistenceTest {

    /**
     * Inyecta la dependencia de EncuentroPersistence.
     */
    @Inject
    private EncuentroPersistence encuentroPersistence;

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
    private List<EncuentroEntity> data = new ArrayList<>();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(EncuentroEntity.class.getPackage())
                .addPackage(EncuentroPersistence.class.getPackage())
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
        em.createQuery("delete from EncuentroEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            EncuentroEntity entity = factory.manufacturePojo(EncuentroEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Prueba para crear un Encuentro.
     */
    @Test
    public void createEncuentroTest() {
        PodamFactory factory = new PodamFactoryImpl();
        EncuentroEntity newEntity = factory.manufacturePojo(EncuentroEntity.class);
        EncuentroEntity result = encuentroPersistence.create(newEntity);

        Assert.assertNotNull(result);

        EncuentroEntity entity = em.find(EncuentroEntity.class, result.getId());

        Assert.assertEquals(newEntity.getName(), entity.getName());
    }

    /**
     * Prueba para consultar la lista de Encuentros.
     */
    @Test
    public void getEncuentrosTest() {
        List<EncuentroEntity> list = encuentroPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (EncuentroEntity ent : list) {
            boolean found = false;
            for (EncuentroEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Encuentro.
     */
    @Test
    public void getEncuentroTest() {
        EncuentroEntity entity = data.get(0);
        EncuentroEntity newEntity = encuentroPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
        Assert.assertEquals(entity.getFecha(), newEntity.getFecha());
    }

    /**
     * Prueba para actualizar un Encuentro.
     */
    @Test
    public void updateEncuentroTest() {
        EncuentroEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        EncuentroEntity newEntity = factory.manufacturePojo(EncuentroEntity.class);

        newEntity.setId(entity.getId());

        encuentroPersistence.update(newEntity);

        EncuentroEntity resp = em.find(EncuentroEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
    }

    /**
     * Prueba para eliminar un Encuentro.
     */
    @Test
    public void deleteEncuentroTest() {
        EncuentroEntity entity = data.get(0);
        encuentroPersistence.delete(entity.getId());
        EncuentroEntity deleted = em.find(EncuentroEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}

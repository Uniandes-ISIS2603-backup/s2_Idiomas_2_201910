/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.test.persistence;

import co.edu.uniandes.csw.idiomas.entities.OtroEntity;
import co.edu.uniandes.csw.idiomas.persistence.OtroPersistence;
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
 * Pruebas de persistencia de Otro
 *
 * @otro g.cubillosb
 */
@RunWith(Arquillian.class)
public class OtroPersistenceTest {

    /**
     * Inyecta la dependencia de OtroPersistence.
     */
    @Inject
    private OtroPersistence otroPersistence;

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
    private List<OtroEntity> data = new ArrayList<>();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(OtroEntity.class.getPackage())
                .addPackage(OtroPersistence.class.getPackage())
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
        em.createQuery("delete from OtroEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            OtroEntity entity = factory.manufacturePojo(OtroEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Prueba para crear un Otro.
     */
    @Test
    public void createOtroTest() {
        PodamFactory factory = new PodamFactoryImpl();
        OtroEntity newEntity = factory.manufacturePojo(OtroEntity.class);
        OtroEntity result = otroPersistence.create(newEntity);

        Assert.assertNotNull(result);

        OtroEntity entity = em.find(OtroEntity.class, result.getId());

        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
    }

    /**
     * Prueba para consultar la lista de Otros.
     */
    @Test
    public void getOtrosTest() {
        List<OtroEntity> list = otroPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (OtroEntity ent : list) {
            boolean found = false;
            for (OtroEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Otro.
     */
    @Test
    public void getOtroTest() {
        OtroEntity entity = data.get(0);
        OtroEntity newEntity = otroPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getNombre(), newEntity.getNombre());
        Assert.assertEquals(entity.getFecha(), newEntity.getFecha());
    }

    /**
     * Prueba para actualizar un Otro.
     */
    @Test
    public void updateOtroTest() {
        OtroEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        OtroEntity newEntity = factory.manufacturePojo(OtroEntity.class);

        newEntity.setId(entity.getId());

        otroPersistence.update(newEntity);

        OtroEntity resp = em.find(OtroEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getNombre(), resp.getNombre());
    }

    /**
     * Prueba para eliminar un Otro.
     */
    @Test
    public void deleteOtroTest() {
        OtroEntity entity = data.get(0);
        otroPersistence.delete(entity.getId());
        OtroEntity deleted = em.find(OtroEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}

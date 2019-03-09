/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.test.persistence;


import co.edu.uniandes.csw.idiomas.entities.EstadiaEntity;
import co.edu.uniandes.csw.idiomas.entities.GrupoDeInteresEntity;
import co.edu.uniandes.csw.idiomas.persistence.EstadiaPersistence;
import co.edu.uniandes.csw.idiomas.persistence.GrupoDeInteresPersistence;
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
 *
 * @author le.perezl
 */
@RunWith(Arquillian.class)
public class GrupoDeInteresPersistenceTest {
        /**
     * Inyecta la dependencia de EstadiaPersistence.
     */
    @Inject
    private GrupoDeInteresPersistence grupoPersistence;

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
    private List<GrupoDeInteresEntity> data = new ArrayList<>();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(GrupoDeInteresEntity.class.getPackage())
                .addPackage(GrupoDeInteresPersistence.class.getPackage())
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
        em.createQuery("delete from GrupoDeInteresEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            GrupoDeInteresEntity grupo = factory.manufacturePojo(GrupoDeInteresEntity.class);

            em.persist(grupo);
            data.add(grupo);
        }
    }

    /**
     * Prueba para crear un Estadia.
     */
    @Test
    public void createGrupoDeInteresTest() {
        PodamFactory factory = new PodamFactoryImpl();
        GrupoDeInteresEntity newEntity = factory.manufacturePojo(GrupoDeInteresEntity.class);
        GrupoDeInteresEntity result = grupoPersistence.create(newEntity);

        Assert.assertNotNull(result);

        EstadiaEntity entity = em.find(EstadiaEntity.class, result.getId());

        Assert.assertEquals(newEntity.getId(), entity.getId());
    }

    /**
     * Prueba para consultar la lista de Estadias.
     */
    @Test
    public void getGrupoDeInteresLTest() {
        List<GrupoDeInteresEntity> list = grupoPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (GrupoDeInteresEntity ent : list) {
            boolean found = false;
            for (GrupoDeInteresEntity entity : data) {
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
    public void getGrupoDeInteresTest() {
        GrupoDeInteresEntity entity = data.get(0);
        GrupoDeInteresEntity newEntity = grupoPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getId(), newEntity.getId());
        Assert.assertEquals(entity.getIdioma(), newEntity.getIdioma());
    }

    /**
     * Prueba para actualizar un Estadia.
     */
    @Test
    public void updateGrupoDeInteresTest() {
        GrupoDeInteresEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        GrupoDeInteresEntity newEntity = factory.manufacturePojo(GrupoDeInteresEntity.class);

        newEntity.setId(entity.getId());

        grupoPersistence.update(newEntity);

        EstadiaEntity resp = em.find(EstadiaEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getId(), resp.getId());
    }

    /**
     * Prueba para eliminar un Estadia.
     */
    @Test
    public void deleteGrupoDeInteresTest() {
        GrupoDeInteresEntity entity = data.get(0);
        grupoPersistence.delete(entity.getId());
        GrupoDeInteresEntity deleted = em.find(GrupoDeInteresEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}

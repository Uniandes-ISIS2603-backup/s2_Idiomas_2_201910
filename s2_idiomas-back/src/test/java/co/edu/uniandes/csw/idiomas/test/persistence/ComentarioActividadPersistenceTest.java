/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.test.persistence;

import co.edu.uniandes.csw.idiomas.entities.ComentarioActividadEntity;
import co.edu.uniandes.csw.idiomas.entities.ComentarioEntity;
import co.edu.uniandes.csw.idiomas.persistence.ComentarioActividadPersistence;
import co.edu.uniandes.csw.idiomas.persistence.ComentarioPersistence;
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
 * @author se.gamboa
 */
@RunWith(Arquillian.class)
public class ComentarioActividadPersistenceTest {
    
    @Inject
    private ComentarioActividadPersistence comentariop;
    @PersistenceContext
    private EntityManager em;
    @Inject
    UserTransaction utx;
    /**
     * Lista de los datos de prueba.
     */
    private List<ComentarioActividadEntity> data = new ArrayList<>();

  @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ComentarioActividadEntity.class.getPackage())
                .addPackage(ComentarioActividadPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    /**
     * Limpia las tablas que están implicadas en la prueba.
     */
    private void clearData() {
        em.createQuery("delete from ComentarioActividadEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            ComentarioActividadEntity entity = factory.manufacturePojo(ComentarioActividadEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }
    
    @Before
    public void configurarTest(){
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

    @Test
    public void createTest() {
        PodamFactory factory = new PodamFactoryImpl();
        ComentarioActividadEntity newEntity = factory.manufacturePojo(ComentarioActividadEntity.class);
        ComentarioActividadEntity result = comentariop.create(newEntity);
        Assert.assertNotNull(result);

        ComentarioActividadEntity entity = em.find(ComentarioActividadEntity.class, result.getId());

        Assert.assertEquals(newEntity.getTitulo(),entity.getTitulo());
    }
    
    /**
     * Prueba para consultar un ComentarioActividad.
     */
    @Test
    public void getComentarioActividadTest() {
        ComentarioActividadEntity entity = data.get(0);
        ComentarioActividadEntity newEntity = comentariop.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getTexto(), newEntity.getTexto());
        Assert.assertEquals(entity.getFecha(), newEntity.getFecha());
        Assert.assertEquals(entity.getAutor(), newEntity.getAutor());
        Assert.assertEquals(entity.getTitulo(), newEntity.getTitulo());
    }

    /**
     * Prueba para actualizar un ComentarioActividad.
     */
    @Test
    public void updateComentarioActividadTest() {
        ComentarioActividadEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        ComentarioActividadEntity newEntity = factory.manufacturePojo(ComentarioActividadEntity.class);

        newEntity.setId(entity.getId());

        comentariop.update(newEntity);

        ComentarioActividadEntity resp = em.find(ComentarioActividadEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getTexto(), resp.getTexto());
        Assert.assertEquals(newEntity.getFecha(), resp.getFecha());
        Assert.assertEquals(newEntity.getAutor(), resp.getAutor());
        Assert.assertEquals(newEntity.getTitulo(), resp.getTitulo());
    }

    /**
     * Prueba para eliminar un ComentarioActividad.
     */
    @Test
    public void deleteComentarioActividadTest() {
        ComentarioActividadEntity entity = data.get(0);
        comentariop.delete(entity.getId());
        ComentarioActividadEntity deleted = em.find(ComentarioActividadEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}

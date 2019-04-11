/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.test.logic;

import co.edu.uniandes.csw.idiomas.ejb.ActividadComentarioActividadLogic;
import co.edu.uniandes.csw.idiomas.ejb.ComentarioLogic;
import co.edu.uniandes.csw.idiomas.entities.ActividadEntity;
import co.edu.uniandes.csw.idiomas.entities.ComentarioEntity;
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
 * Pruebas de logica de la relacion Actividad - ComentarioActividad
 * @author g.cubillosb
 */
@RunWith(Arquillian.class)
public class ActividadComentarioActividadLogicTest {

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private ActividadComentarioActividadLogic actividadComentarioActividadLogic;

    @Inject
    private ComentarioLogic comentarioActividadLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private ActividadEntity actividad = new ActividadEntity();
    private List<ComentarioEntity> data = new ArrayList<>();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ActividadEntity.class.getPackage())
                .addPackage(ComentarioEntity.class.getPackage())
                .addPackage(ActividadComentarioActividadLogic.class.getPackage())
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
        em.createQuery("delete from ActividadEntity").executeUpdate();
        em.createQuery("delete from ComentarioActividadEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {

        actividad = factory.manufacturePojo(ActividadEntity.class);
        actividad.setId(1L);
        actividad.setComentarios(new ArrayList<>());
        em.persist(actividad);

        for (int i = 0; i < 3; i++) {
            ComentarioEntity entity = factory.manufacturePojo(ComentarioEntity.class);
            entity.setActividad(actividad);
            em.persist(entity);
            data.add(entity);
            actividad.getComentarios().add(entity);
        }
    }

    /**
     * Prueba para asociar un actividad a un comentario.
     *
     *
     */
//    @Test
//    public void addComentarioActividadTest() throws BusinessLogicException {
//        ComentarioActividadEntity newComentarioActividad = factory.manufacturePojo(ComentarioActividadEntity.class);
//        comentarioActividadLogic.createActivityComment(newComentarioActividad);
//        ComentarioActividadEntity comentarioActividadEntity = actividadComentarioActividadLogic.addComentarioActividad(actividad.getId(), newComentarioActividad.getId());
//        Assert.assertNotNull(comentarioActividadEntity);
//
//        Assert.assertEquals(comentarioActividadEntity.getId(), newComentarioActividad.getId());
//        Assert.assertEquals(comentarioActividadEntity.getActividad(), newComentarioActividad.getActividad());
//        Assert.assertEquals(comentarioActividadEntity.getTitulo(), newComentarioActividad.getTitulo());
//
//        ComentarioActividadEntity lastComentarioActividad = actividadComentarioActividadLogic.getComentarioActividad(actividad.getId(), newComentarioActividad.getId());
//
//        Assert.assertEquals(comentarioActividadEntity.getId(), newComentarioActividad.getId());
//        Assert.assertEquals(comentarioActividadEntity.getActividad(), newComentarioActividad.getActividad());
//        Assert.assertEquals(comentarioActividadEntity.getTitulo(), newComentarioActividad.getTitulo());
//    }

    /**
     * Prueba para consultar la lista de Comentarios de un actividad.
     */
    @Test
    public void getComentariosTest() {
        List<ComentarioEntity> comentarioActividadEntities = actividadComentarioActividadLogic.getComentarios(actividad.getId());

        Assert.assertEquals(data.size(), comentarioActividadEntities.size());

        for (int i = 0; i < data.size(); i++) {
            Assert.assertTrue(comentarioActividadEntities.contains(data.get(0)));
        }
    }

    /**
     * Prueba para cpnsultar un comentario de un actividad.
     *
     * @throws co.edu.uniandes.csw.comentarioActividadstore.exceptions.BusinessLogicException
     */
//    @Test
//    public void getComentarioActividadTest() throws BusinessLogicException {
//        ComentarioActividadEntity comentarioActividadEntity = data.get(0);
//        ComentarioActividadEntity comentarioActividad = actividadComentarioActividadLogic.getComentarioActividad(actividad.getId(), comentarioActividadEntity.getId());
//        Assert.assertNotNull(comentarioActividad);
//
//        Assert.assertEquals(comentarioActividadEntity.getId(), comentarioActividad.getId());
//        Assert.assertEquals(comentarioActividadEntity.getActividad(), comentarioActividad.getActividad());
//        Assert.assertEquals(comentarioActividadEntity.getTitulo(), comentarioActividad.getTitulo());
//    }

    /**
     * Prueba para actualizar los comentarios de un actividad.
     *
     * @throws co.edu.uniandes.csw.comentarioActividadstore.exceptions.BusinessLogicException
     */
//    @Test
//    public void replaceComentariosTest() throws BusinessLogicException {
//        List<ComentarioActividadEntity> nuevaLista = new ArrayList<>();
//        for (int i = 0; i < 3; i++) {
//            ComentarioActividadEntity entity = factory.manufacturePojo(ComentarioActividadEntity.class);
//            entity.setActividad(actividad);
//            comentarioActividadLogic.createActivityComment(entity);
//            nuevaLista.add(entity);
//        }
//        actividadComentarioActividadLogic.replaceComentarios(actividad.getId(), nuevaLista);
//        List<ComentarioActividadEntity> comentarioActividadEntities = actividadComentarioActividadLogic.getComentarios(actividad.getId());
//        for (ComentarioActividadEntity aNuevaLista : nuevaLista) {
//            Assert.assertTrue(comentarioActividadEntities.contains(aNuevaLista));
//        }
//    }

    /**
     * Prueba desasociar un comentario con un actividad.
     *
     */
//    @Test
//    public void removeComentarioActividadTest() {
//        for (ComentarioActividadEntity comentarioActividad : data) {
//            actividadComentarioActividadLogic.removeComentarioActividad(actividad.getId(), comentarioActividad.getId());
//        }
//        Assert.assertTrue(actividadComentarioActividadLogic.getComentarios(actividad.getId()).isEmpty());
//    }
}

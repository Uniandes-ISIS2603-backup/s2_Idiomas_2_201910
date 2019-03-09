/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.test.logic;

import co.edu.uniandes.csw.idiomas.ejb.ComentarioCalificacionLogic;
import co.edu.uniandes.csw.idiomas.ejb.ComentarioCalificacionLogic;
import co.edu.uniandes.csw.idiomas.entities.ComentarioCalificacionEntity;
import co.edu.uniandes.csw.idiomas.entities.ComentarioCalificacionEntity;
import co.edu.uniandes.csw.idiomas.entities.ComentarioGrupoEntity;
import co.edu.uniandes.csw.idiomas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.idiomas.persistence.ComentarioCalificacionPersistence;
import co.edu.uniandes.csw.idiomas.persistence.ComentarioCalificacionPersistence;
import co.edu.uniandes.csw.idiomas.persistence.ComentarioGrupoPersistence;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class ComentarioCalificacionTest {

    @Inject
    private ComentarioCalificacionLogic commentLogic;
    @PersistenceContext
    private EntityManager em;
    
    /**
     * Variable para marcar las transacciones del "em" (Entity Manager) cuando
     * se crean/borran datos.
     */
    @Inject
    private UserTransaction utx;

    private List<ComentarioCalificacionEntity> data = new ArrayList<>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ComentarioCalificacionEntity.class.getPackage())
                .addPackage(ComentarioCalificacionLogic.class.getPackage())
                .addPackage(ComentarioCalificacionPersistence.class.getPackage())
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
        em.createQuery("delete from ComentarioCalificacionEntity").executeUpdate();
    }
    
    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            ComentarioCalificacionEntity entity = factory.manufacturePojo(ComentarioCalificacionEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }

//Prueba que un comentario se cree correctamente.
    @Test
    public void createCalificacionComment() throws BusinessLogicException {
        PodamFactory factory = new PodamFactoryImpl();
        ComentarioCalificacionEntity newEntity = factory.manufacturePojo(ComentarioCalificacionEntity.class);
        ComentarioCalificacionEntity result = commentLogic.createCalifComment(newEntity);
        Assert.assertNotNull(result);

        ComentarioCalificacionEntity entity = em.find(ComentarioCalificacionEntity.class, result.getId());

        Assert.assertEquals(newEntity.getTitulo(), entity.getTitulo());
    }
//Prueba que un comentario se cree incorrectamente (Sin titulo) para verificar las reglas del negocio.

    @Test(expected = BusinessLogicException.class)
    public void crearComentarioSinTitulo() throws BusinessLogicException {
        PodamFactory factory = new PodamFactoryImpl();
        ComentarioCalificacionEntity newEntity = factory.manufacturePojo(ComentarioCalificacionEntity.class);
        newEntity.setTitulo("");
        commentLogic.createCalifComment(newEntity);
    }

    //Prueba que un comentario se cree incorrectamente (Sin texto) para verificar las reglas del negocio.
    @Test(expected = BusinessLogicException.class)
    public void crearComentarioSinTexto() throws BusinessLogicException {
        PodamFactory factory = new PodamFactoryImpl();
        ComentarioCalificacionEntity newEntity = factory.manufacturePojo(ComentarioCalificacionEntity.class);
        newEntity.setTexto("");
        commentLogic.createCalifComment(newEntity);
    }

    //Prueba que un comentario se cree incorrectamente (Con mas de 300 caracteres) para verificar las reglas del negocio.
    @Test(expected = BusinessLogicException.class)
    public void crearComentariMaxTexto() throws BusinessLogicException {
        PodamFactory factory = new PodamFactoryImpl();
        ComentarioCalificacionEntity newEntity = factory.manufacturePojo(ComentarioCalificacionEntity.class);
        String texto = "";
        for(int i = 0; i<301; i++){
            texto += "a";
        }
        newEntity.setTexto(texto);
        commentLogic.createCalifComment(newEntity);
    }
}

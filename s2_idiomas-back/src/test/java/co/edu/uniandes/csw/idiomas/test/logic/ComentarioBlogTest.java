/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.test.logic;

import co.edu.uniandes.csw.idiomas.ejb.ComentarioBlogLogic;
import co.edu.uniandes.csw.idiomas.entities.ComentarioBlogEntity;
import co.edu.uniandes.csw.idiomas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.idiomas.persistence.ComentarioBlogPersistence;
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
public class ComentarioBlogTest {

    @Inject
    private ComentarioBlogLogic commentLogic;
    @PersistenceContext
    private EntityManager em;
    
    /**
     * Variable para marcar las transacciones del "em" (Entity Manager) cuando
     * se crean/borran datos.
     */
    @Inject
    private UserTransaction utx;

    private List<ComentarioBlogEntity> data = new ArrayList<>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ComentarioBlogEntity.class.getPackage())
                .addPackage(ComentarioBlogLogic.class.getPackage())
                .addPackage(ComentarioBlogPersistence.class.getPackage())
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
        em.createQuery("delete from ComentarioBlogEntity").executeUpdate();
    }
    
    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            ComentarioBlogEntity entity = factory.manufacturePojo(ComentarioBlogEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }

//Prueba que un comentario se cree correctamente.
    @Test
    public void createBlogComment() throws BusinessLogicException {
        PodamFactory factory = new PodamFactoryImpl();
        ComentarioBlogEntity newEntity = factory.manufacturePojo(ComentarioBlogEntity.class);
        ComentarioBlogEntity result = commentLogic.createBlogComment(newEntity);
        Assert.assertNotNull(result);

        ComentarioBlogEntity entity = em.find(ComentarioBlogEntity.class, result.getId());

        Assert.assertEquals(newEntity.getTitulo(), entity.getTitulo());
    }
//Prueba que un comentario se cree incorrectamente (Sin titulo) para verificar las reglas del negocio.

    @Test(expected = BusinessLogicException.class)
    public void crearComentarioSinTitulo() throws BusinessLogicException {
        PodamFactory factory = new PodamFactoryImpl();
        ComentarioBlogEntity newEntity = factory.manufacturePojo(ComentarioBlogEntity.class);
        newEntity.setTitulo("");
        commentLogic.createBlogComment(newEntity);
    }

    //Prueba que un comentario se cree incorrectamente (Sin texto) para verificar las reglas del negocio.
    @Test(expected = BusinessLogicException.class)
    public void crearComentarioSinTexto() throws BusinessLogicException {
        PodamFactory factory = new PodamFactoryImpl();
        ComentarioBlogEntity newEntity = factory.manufacturePojo(ComentarioBlogEntity.class);
        newEntity.setTexto("");
        commentLogic.createBlogComment(newEntity);
    }

    //Prueba que un comentario se cree incorrectamente (Con mas de 300 caracteres) para verificar las reglas del negocio.
    @Test(expected = BusinessLogicException.class)
    public void crearComentariMaxTexto() throws BusinessLogicException {
        PodamFactory factory = new PodamFactoryImpl();
        ComentarioBlogEntity newEntity = factory.manufacturePojo(ComentarioBlogEntity.class);
        String texto = "";
        for(int i = 0; i<301; i++){
            texto += "a";
        }
        newEntity.setTexto(texto);
        commentLogic.createBlogComment(newEntity);
    }
}

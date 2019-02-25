/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.test.logic;

import co.edu.uniandes.csw.idiomas.ejb.ComentarioActividadLogic;
import co.edu.uniandes.csw.idiomas.entities.ComentarioActividadEntity;
import co.edu.uniandes.csw.idiomas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.idiomas.persistence.ComentarioActividadPersistence;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author se.gamboa
 */
@RunWith(Arquillian.class)
public class ComentarioActividadTest {

    @Inject
    private ComentarioActividadLogic commentLogic;
    @PersistenceContext
    private EntityManager em;

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ComentarioActividadEntity.class.getPackage())
                .addPackage(ComentarioActividadLogic.class.getPackage())
                .addPackage(ComentarioActividadPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    @Test
    public void createBlogComment() throws BusinessLogicException {
        PodamFactory factory = new PodamFactoryImpl();
        ComentarioActividadEntity newEntity = factory.manufacturePojo(ComentarioActividadEntity.class);
        ComentarioActividadEntity result = commentLogic.createActivityComment(newEntity);
        Assert.assertNotNull(result);

        ComentarioActividadEntity entity = em.find(ComentarioActividadEntity.class, result.getId());

        Assert.assertEquals(newEntity.getTitulo(), entity.getTitulo());
    }

    @Test(expected = BusinessLogicException.class)
    public void crearComentarioSinTitulo() throws BusinessLogicException {
        PodamFactory factory = new PodamFactoryImpl();
        ComentarioActividadEntity newEntity = factory.manufacturePojo(ComentarioActividadEntity.class);
        newEntity.setTitulo("");
        commentLogic.createActivityComment(newEntity);
    }
}

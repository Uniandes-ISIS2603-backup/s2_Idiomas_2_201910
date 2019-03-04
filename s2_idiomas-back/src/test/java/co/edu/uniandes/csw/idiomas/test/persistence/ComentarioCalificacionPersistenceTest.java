/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.test.persistence;

import co.edu.uniandes.csw.idiomas.entities.ComentarioCalificacionEntity;
import co.edu.uniandes.csw.idiomas.entities.ComentarioEntity;
import co.edu.uniandes.csw.idiomas.persistence.ComentarioCalificacionPersistence;
import co.edu.uniandes.csw.idiomas.persistence.ComentarioPersistence;
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
public class ComentarioCalificacionPersistenceTest {
    @Inject
    private ComentarioCalificacionPersistence comentariop;
    @PersistenceContext
    private EntityManager em;

  @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ComentarioEntity.class.getPackage())
                .addPackage(ComentarioPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    @Test
    public void createTest() {
        PodamFactory factory = new PodamFactoryImpl();
        ComentarioCalificacionEntity newEntity = factory.manufacturePojo(ComentarioCalificacionEntity.class);
        ComentarioCalificacionEntity result = comentariop.create(newEntity);
        Assert.assertNotNull(result);

        ComentarioCalificacionEntity entity = em.find(ComentarioCalificacionEntity.class, result.getId());

        Assert.assertEquals(newEntity.getTitulo(),entity.getTitulo());
    }
}

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
public class ComentarioActividadPersistenceTest {
    
    @Inject
    private ComentarioActividadPersistence comentariop;
    @PersistenceContext
    private EntityManager em;

  @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ComentarioActividadEntity.class.getPackage())
                .addPackage(ComentarioActividadPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
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
}

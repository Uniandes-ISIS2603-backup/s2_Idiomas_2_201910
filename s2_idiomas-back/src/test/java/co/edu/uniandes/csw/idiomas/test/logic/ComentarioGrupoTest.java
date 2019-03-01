/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.test.logic;

import co.edu.uniandes.csw.idiomas.ejb.ComentarioGrupoLogic;
import co.edu.uniandes.csw.idiomas.entities.ComentarioBlogEntity;
import co.edu.uniandes.csw.idiomas.entities.ComentarioGrupoEntity;
import co.edu.uniandes.csw.idiomas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.idiomas.persistence.ComentarioGrupoPersistence;
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
public class ComentarioGrupoTest {

    @Inject
    private ComentarioGrupoLogic commentLogic;
    @PersistenceContext
    private EntityManager em;

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ComentarioGrupoEntity.class.getPackage())
                .addPackage(ComentarioGrupoLogic.class.getPackage())
                .addPackage(ComentarioGrupoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    @Test
    public void createBlogComment() throws BusinessLogicException {
        PodamFactory factory = new PodamFactoryImpl();
        ComentarioGrupoEntity newEntity = factory.manufacturePojo(ComentarioGrupoEntity.class);
        ComentarioGrupoEntity result = commentLogic.createGroupComment(newEntity);
        Assert.assertNotNull(result);

        ComentarioGrupoEntity entity = em.find(ComentarioGrupoEntity.class, result.getId());

        Assert.assertEquals(newEntity.getTitulo(), entity.getTitulo());
    }

    @Test(expected = BusinessLogicException.class)
    public void crearComentarioSinTitulo() throws BusinessLogicException {
        PodamFactory factory = new PodamFactoryImpl();
        ComentarioGrupoEntity newEntity = factory.manufacturePojo(ComentarioGrupoEntity.class);
        newEntity.setTitulo("");
        commentLogic.createGroupComment(newEntity);
    }
}

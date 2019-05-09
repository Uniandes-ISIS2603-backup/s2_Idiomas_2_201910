package co.edu.uniandes.csw.idiomas.test.logic;

import co.edu.uniandes.csw.idiomas.ejb.CalificacionGrupoLogic;
import co.edu.uniandes.csw.idiomas.ejb.CalificacionLogic;
import co.edu.uniandes.csw.idiomas.entities.GrupoDeInteresEntity;
import co.edu.uniandes.csw.idiomas.entities.CalificacionEntity;
import co.edu.uniandes.csw.idiomas.exceptions.BusinessLogicException;
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
 * Pruebas de logica de la relacion Calificacion - GrupoDeInteres
 *
 * @author jdruedaa
 */
@RunWith(Arquillian.class)
public class CalificacionGrupoDeInteresLogicTest {

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private CalificacionLogic calificacionLogic;
    
    @Inject
    private CalificacionGrupoLogic calificacionGrupoDeInteresLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<GrupoDeInteresEntity> data = new ArrayList<GrupoDeInteresEntity>();

    private List<CalificacionEntity> calificacionesData = new ArrayList();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(GrupoDeInteresEntity.class.getPackage())
                .addPackage(CalificacionLogic.class.getPackage())
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
        em.createQuery("delete from CalificacionEntity").executeUpdate();
        em.createQuery("delete from GrupoDeInteresEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            CalificacionEntity calificaciones = factory.manufacturePojo(CalificacionEntity.class);
            em.persist(calificaciones);
            calificacionesData.add(calificaciones);
        }
        for (int i = 0; i < 3; i++) {
            GrupoDeInteresEntity entity = factory.manufacturePojo(GrupoDeInteresEntity.class);
            em.persist(entity);
            data.add(entity);
            if (i == 0) {
                calificacionesData.get(i).setGrupo(entity);
            }
        }
    }

    /**
     * Prueba para remplazar las instancias de Calificacions asociadas a una instancia
     * de GrupoDeInteres.
     */
    @Test
    public void replaceGrupoDeInteresTest() {
        CalificacionEntity entity = calificacionesData.get(0);
        calificacionGrupoDeInteresLogic.replaceGrupoDeInteres(entity.getId(), data.get(1).getId());
        entity = calificacionLogic.getCalificacion(entity.getId());
        Assert.assertEquals(entity.getGrupo(), data.get(1));
    }

    /**
     * Prueba para desasociar un Calificacion existente de un GrupoDeInteres existente
     *
     * @throws co.edu.uniandes.csw.idiomas.exceptions.BusinessLogicException
     */
    @Test
    public void removeCalificacionesTest() throws BusinessLogicException {
        calificacionGrupoDeInteresLogic.removeGrupoDeInteres(calificacionesData.get(0).getId());
        CalificacionEntity response = calificacionLogic.getCalificacion(calificacionesData.get(0).getId());
        Assert.assertNull(response.getGrupo());
    }
}
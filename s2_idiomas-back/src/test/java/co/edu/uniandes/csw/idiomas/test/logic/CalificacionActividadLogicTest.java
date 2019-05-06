package co.edu.uniandes.csw.idiomas.test.logic;

import co.edu.uniandes.csw.idiomas.ejb.CalificacionActividadLogic;
import co.edu.uniandes.csw.idiomas.ejb.CalificacionLogic;
import co.edu.uniandes.csw.idiomas.entities.ActividadEntity;
import co.edu.uniandes.csw.idiomas.entities.CalificacionEntity;
import co.edu.uniandes.csw.idiomas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.idiomas.persistence.ActividadPersistence;
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
 * Pruebas de logica de la relacion Calificacion - Actividad
 *
 * @author ISIS2603
 */
@RunWith(Arquillian.class)
public class CalificacionActividadLogicTest {

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private CalificacionLogic calificacionLogic;
    
    @Inject
    private CalificacionActividadLogic calificacionActividadLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<ActividadEntity> data = new ArrayList<ActividadEntity>();

    private List<CalificacionEntity> calificacionesData = new ArrayList();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ActividadEntity.class.getPackage())
                .addPackage(CalificacionLogic.class.getPackage())
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
        em.createQuery("delete from CalificacionEntity").executeUpdate();
        em.createQuery("delete from ActividadEntity").executeUpdate();
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
            ActividadEntity entity = factory.manufacturePojo(ActividadEntity.class);
            em.persist(entity);
            data.add(entity);
            if (i == 0) {
                calificacionesData.get(i).setActividad(entity);
            }
        }
    }

    /**
     * Prueba para remplazar las instancias de Calificacions asociadas a una instancia
     * de Actividad.
     */
    @Test
    public void replaceActividadTest() {
        CalificacionEntity entity = calificacionesData.get(0);
        calificacionActividadLogic.replaceActividad(entity.getId(), data.get(1).getId());
        entity = calificacionLogic.getCalificacion(entity.getId());
        Assert.assertEquals(entity.getActividad(), data.get(1));
    }

    /**
     * Prueba para desasociar un Calificacion existente de un Actividad existente
     *
     * @throws co.edu.uniandes.csw.idiomas.exceptions.BusinessLogicException
     */
    @Test
    public void removeCalificacionesTest() throws BusinessLogicException {
        calificacionActividadLogic.removeActividad(calificacionesData.get(0).getId());
        CalificacionEntity response = calificacionLogic.getCalificacion(calificacionesData.get(0).getId());
        Assert.assertNull(response.getActividad());
    }
}
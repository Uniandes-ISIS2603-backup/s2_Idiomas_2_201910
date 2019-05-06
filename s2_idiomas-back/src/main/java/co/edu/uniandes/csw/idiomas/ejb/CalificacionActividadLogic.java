package co.edu.uniandes.csw.idiomas.ejb;
import co.edu.uniandes.csw.idiomas.entities.CalificacionEntity;
import co.edu.uniandes.csw.idiomas.entities.ActividadEntity;
import co.edu.uniandes.csw.idiomas.persistence.ActividadPersistence;
import co.edu.uniandes.csw.idiomas.persistence.CalificacionPersistence;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Clase que implementa la conexion con la persistencia para las CalificacionActividades.
 *
 * @author jdruedaa
 */
@Stateless
public class CalificacionActividadLogic {

    private static final Logger LOGGER = Logger.getLogger(CalificacionActividadLogic.class.getName());

    @Inject
    private CalificacionPersistence calificacionPersistence;

    @Inject
    private ActividadPersistence actividadPersistence;

    /**
     * Remplazar la Actividad de un Calificacion.
     *
     * @param calificacionesId id del libro que se quiere actualizar.
     * @param actividadesId El id de la Actividad que se será del libro.
     * @return el nuevo libro.
     */
    public CalificacionEntity replaceActividad(Long calificacionesId, Long actividadesId) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar libro con id = {0}", calificacionesId);
        ActividadEntity actividadEntity = actividadPersistence.find(actividadesId);
        CalificacionEntity calificacionEntity = calificacionPersistence.find(calificacionesId);
        calificacionEntity.setActividad(actividadEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar libro con id = {0}", calificacionEntity.getId());
        return calificacionEntity;
    }

    /**
     * Borrar un Calificacion de una Actividad. Este metodo se utiliza para borrar la
     * relacion de un libro.
     *
     * @param calificacionesId El libro que se desea borrar de la Actividad.
     */
    public void removeActividad(Long calificacionesId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar la Actividad del libro con id = {0}", calificacionesId);
        CalificacionEntity calificacionEntity = calificacionPersistence.find(calificacionesId);
        ActividadEntity actividadEntity = actividadPersistence.find(calificacionEntity.getActividad().getId());
        calificacionEntity.setActividad(null);
        actividadEntity.getCalificaciones().remove(calificacionEntity);
        LOGGER.log(Level.INFO, "Termina proceso de borrar la Actividad del libro con id = {0}", calificacionEntity.getId());
    }
}
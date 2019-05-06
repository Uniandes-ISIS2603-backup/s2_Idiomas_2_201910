package co.edu.uniandes.csw.idiomas.ejb;
import co.edu.uniandes.csw.idiomas.entities.CoordinadorEntity;
import co.edu.uniandes.csw.idiomas.entities.CalificacionEntity;
import co.edu.uniandes.csw.idiomas.persistence.CoordinadorPersistence;
import co.edu.uniandes.csw.idiomas.persistence.CalificacionPersistence;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Clase que implementa la conexion con la persistencia para las CalificacionCoordinadores.
 *
 * @author jdruedaa
 */
@Stateless
public class CalificacionCoordinadorLogic {

    private static final Logger LOGGER = Logger.getLogger(CalificacionCoordinadorLogic.class.getName());

    @Inject
    private CalificacionPersistence calificacionPersistence;
    
    @Inject
    private CoordinadorPersistence coordinadorPersistence;

    /**
     * Remplazar el Coordinador de un Calificacion.
     *
     * @param calificacionesId id de la Calificacion que se quiere actualizar.
     * @param coordinadoresId El id de el Coordinador que se será de la Calificacion.
     * @return el nuevo calificacion.
     */
    public CalificacionEntity replaceCoordinador(Long calificacionesId, Long coordinadoresId) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar calificacion con id = {0}", calificacionesId);
        CoordinadorEntity coordinadorEntity = coordinadorPersistence.find(coordinadoresId);
        CalificacionEntity calificacionEntity = calificacionPersistence.find(calificacionesId);
        calificacionEntity.setCoordinador(coordinadorEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar calificacion con id = {0}", calificacionEntity.getId());
        return calificacionEntity;
    }

    /**
     * Borrar un Calificacion de una Coordinador. Este metodo se utiliza para borrar la
     * relacion de un calificacion.
     *
     * @param calificacionesId La Calificacion que se desea borrar de el Coordinador.
     */
    public void removeCoordinador(Long calificacionesId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el Coordinador de la Calificacion con id = {0}", calificacionesId);
        CalificacionEntity calificacionEntity = calificacionPersistence.find(calificacionesId);
        CoordinadorEntity coordinadorEntity = coordinadorPersistence.find(calificacionEntity.getCoordinador().getId());
        calificacionEntity.setCoordinador(null);
        coordinadorEntity.getCalificaciones().remove(calificacionEntity);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el Coordinador de la Calificacion con id = {0}", calificacionEntity.getId());
    }
}
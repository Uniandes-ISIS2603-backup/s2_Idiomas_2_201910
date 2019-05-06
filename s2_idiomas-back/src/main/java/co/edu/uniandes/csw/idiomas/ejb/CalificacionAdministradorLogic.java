package co.edu.uniandes.csw.idiomas.ejb;
import co.edu.uniandes.csw.idiomas.entities.CalificacionEntity;
import co.edu.uniandes.csw.idiomas.entities.AdministradorEntity;
import co.edu.uniandes.csw.idiomas.persistence.AdministradorPersistence;
import co.edu.uniandes.csw.idiomas.persistence.CalificacionPersistence;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Clase que implementa la conexion con la persistencia para las CalificacionAdministradores.
 *
 * @author jdruedaa
 */
@Stateless
public class CalificacionAdministradorLogic {

    private static final Logger LOGGER = Logger.getLogger(CalificacionAdministradorLogic.class.getName());
    
    @Inject
    private CalificacionPersistence calificacionPersistence;
    
    @Inject
    private AdministradorPersistence administradorPersistence;

    /**
     * Remplazar el Administrador de un Calificacion.
     *
     * @param calificacionesId id de la Calificacion que se quiere actualizar.
     * @param administradoresId El id de el Administrador que se será de la Calificacion.
     * @return la nueva calificacion.
     */
    public CalificacionEntity replaceAdministrador(Long calificacionesId, Long administradoresId) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar calificacion con id = {0}", calificacionesId);
        AdministradorEntity administradorEntity = administradorPersistence.find(administradoresId);
        CalificacionEntity calificacionEntity = calificacionPersistence.find(calificacionesId);
        calificacionEntity.setAdministrador(administradorEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar calificacion con id = {0}", calificacionEntity.getId());
        return calificacionEntity;
    }

    /**
     * Borrar un Calificacion de una Administrador. Este metodo se utiliza para borrar la
     * relacion de un calificacion.
     *
     * @param calificacionesId La Calificacion que se desea borrar de el Administrador.
     */
    public void removeAdministrador(Long calificacionesId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el Administrador de la Calificacion con id = {0}", calificacionesId);
        CalificacionEntity calificacionEntity = calificacionPersistence.find(calificacionesId);
        AdministradorEntity administradorEntity = administradorPersistence.find(calificacionEntity.getAdministrador().getId());
        calificacionEntity.setAdministrador(null);
        administradorEntity.getCalificaciones().remove(calificacionEntity);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el Administrador de la Calificacion con id = {0}", calificacionEntity.getId());
    }
}
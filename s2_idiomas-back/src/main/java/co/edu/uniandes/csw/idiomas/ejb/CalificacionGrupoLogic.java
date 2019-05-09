package co.edu.uniandes.csw.idiomas.ejb;
import co.edu.uniandes.csw.idiomas.entities.GrupoDeInteresEntity;
import co.edu.uniandes.csw.idiomas.entities.CalificacionEntity;
import co.edu.uniandes.csw.idiomas.persistence.GrupoDeInteresPersistence;
import co.edu.uniandes.csw.idiomas.persistence.CalificacionPersistence;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Clase que implementa la conexion con la persistencia para las CalificacionGrupoes.
 *
 * @author jdruedaa
 */
@Stateless
public class CalificacionGrupoLogic {

    private static final Logger LOGGER = Logger.getLogger(CalificacionGrupoLogic.class.getName());
    
    @Inject
    private CalificacionPersistence calificacionPersistence;
    
    @Inject
    private GrupoDeInteresPersistence grupoDeInteresPersistence;

    /**
     * Remplazar el GrupoDeInteres de un Calificacion.
     *
     * @param calificacionesId id de la Calificacion que se quiere actualizar.
     * @param gruposDeInteresId El id de el GrupoDeInteres que se será de la Calificacion.
     * @return la nueva calificacion.
     */
    public CalificacionEntity replaceGrupoDeInteres(Long calificacionesId, Long gruposDeInteresId) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar calificacion con id = {0}", calificacionesId);
        GrupoDeInteresEntity grupoDeInteresEntity = grupoDeInteresPersistence.find(gruposDeInteresId);
        CalificacionEntity calificacionEntity = calificacionPersistence.find(calificacionesId);
        calificacionEntity.setGrupo(grupoDeInteresEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar calificacion con id = {0}", calificacionEntity.getId());
        return calificacionEntity;
    }

    /**
     * Borrar un Calificacion de una GrupoDeInteres. Este metodo se utiliza para borrar la
     * relacion de un calificacion.
     *
     * @param calificacionesId La Calificacion que se desea borrar de el GrupoDeInteres.
     */
    public void removeGrupoDeInteres(Long calificacionesId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el GrupoDeInteres de la Calificacion con id = {0}", calificacionesId);
        CalificacionEntity calificacionEntity = calificacionPersistence.find(calificacionesId);
        GrupoDeInteresEntity grupoDeInteresEntity = grupoDeInteresPersistence.find(calificacionEntity.getGrupo().getId());
        calificacionEntity.setGrupo(null);
        grupoDeInteresEntity.getCalificaciones().remove(calificacionEntity);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el GrupoDeInteres de la Calificacion con id = {0}", calificacionEntity.getId());
    }
}
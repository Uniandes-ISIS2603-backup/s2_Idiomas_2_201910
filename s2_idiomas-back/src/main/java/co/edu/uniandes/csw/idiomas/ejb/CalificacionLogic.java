package co.edu.uniandes.csw.idiomas.ejb;
import co.edu.uniandes.csw.idiomas.entities.CalificacionEntity;
import co.edu.uniandes.csw.idiomas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.idiomas.persistence.ActividadPersistence;
import co.edu.uniandes.csw.idiomas.persistence.AdministradorPersistence;
import co.edu.uniandes.csw.idiomas.persistence.CalificacionPersistence;
import co.edu.uniandes.csw.idiomas.persistence.CoordinadorPersistence;
import co.edu.uniandes.csw.idiomas.persistence.GrupoDeInteresPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Clase que implementa la conexion con la persistencia para las calificaciones.
 *
 * @author jdruedaa
 */
@Stateless
public class CalificacionLogic {

    private static final Logger LOGGER = Logger.getLogger(CalificacionLogic.class.getName());

    @Inject
    private CalificacionPersistence calificacionPersistence;
    
    @Inject
    private ActividadPersistence actividadPersistence;
    
    @Inject
    private AdministradorPersistence administradorPersistence;
    
    @Inject
    private CoordinadorPersistence coordinadorPersistence;
    
    @Inject
    private GrupoDeInteresPersistence grupoDeInteresPersistence;

    /**
     * Guardar una nueva calificacion
     *
     * @param calificacionEntity La entidad de tipo calificacion de la nueva calificacion a persistir.
     * @return La entidad luego de persistirla
     * @throws BusinessLogicException Si la Calificacion o el Mensaje asociados están fuera de limites.
     */
    public CalificacionEntity createCalificacion(CalificacionEntity calificacionEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de creación de la calificacion");
        CalificacionEntity newCalificacionEntity = calificacionPersistence.create(calificacionEntity);
        if(newCalificacionEntity != null)
        {
            if (newCalificacionEntity.getActividad() != null)
            {
                if (actividadPersistence.find(calificacionEntity.getActividad().getId()) == null)
                {
                    throw new BusinessLogicException("Actividad no válida");
                }
            }
            else if (newCalificacionEntity.getAdministrador() != null)
            {
                if (administradorPersistence.find(calificacionEntity.getAdministrador().getId()) == null)
                {
                    throw new BusinessLogicException("Administrador no válido");
                }
            }
            else if (newCalificacionEntity.getCoordinador() != null)
            {
                if (coordinadorPersistence.find(calificacionEntity.getCoordinador().getId()) == null)
                {
                    throw new BusinessLogicException("Coordinador no válido");
                }
            }
            else if (newCalificacionEntity.getGrupo() != null)
            {
                if (grupoDeInteresPersistence.find(calificacionEntity.getGrupo().getId()) == null)
                {
                    throw new BusinessLogicException("Grupo de interés no válido");
                }
            }
            else
            {
                throw new BusinessLogicException("No se puede crear calificaciones sin específicar qué está calificando");   
            }
            if (newCalificacionEntity.getCalificacion() < 0 || newCalificacionEntity.getCalificacion() > 5)
            {
                throw new BusinessLogicException("La calificación no se encuentra entre 0 y 5");   
            }
            if (newCalificacionEntity.getMensaje() != null)
            {
                if(newCalificacionEntity.getMensaje().length() > 250)
                {
                    throw new BusinessLogicException("El mensaje supera el número de caracteres permitidos");
                }
            }
        }
        LOGGER.log(Level.INFO, "Termina proceso de creación de la calificacion");
        return newCalificacionEntity;
    }

    /**
     * Devuelve todas las calificaciones que hay en la base de datos.
     *
     * @return Lista de entidades de tipo calificacion.
     */
    public List<CalificacionEntity> getCalificaciones() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todas las calificaciones");
        List<CalificacionEntity> calificaciones = calificacionPersistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todas las calificaciones");
        return calificaciones;
    }

    /**
     * Busca una calificacion por ID
     *
     * @param calificacionesId El id de la calificacion a buscar
     * @return La calificacion encontrada, null si no la encuentra.
     */
    public CalificacionEntity getCalificacion(Long calificacionesId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la calificacion con id = {0}", calificacionesId);
        CalificacionEntity calificacionEntity = calificacionPersistence.find(calificacionesId);
        if (calificacionEntity == null) {
            LOGGER.log(Level.SEVERE, "El libro con el id = {0} no existe", calificacionesId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar la calificacion con id = {0}", calificacionesId);
        return calificacionEntity;
    }

    /**
     * Actualizar una calificacion por ID
     *
     * @param calificacionesId El ID de la calificacion a actualizar
     * @param calificacionEntity La entidad de la calificacion con los cambios deseados
     * @return La entidad de la calificacion luego de actualizarla
     * @throws BusinessLogicException Si la Calificacion o el Mensaje están fuera de límites
     */
    public CalificacionEntity updateCalificacion(Long calificacionesId, CalificacionEntity calificacionEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar la calificacion con id = {0}", calificacionesId);
        if (calificacionEntity.getCalificacion() < 0 || calificacionEntity.getCalificacion() > 5) {
            throw new BusinessLogicException("La calificación no se encuentra entre 0 y 5");
        }
        if (calificacionEntity.getMensaje().length() > 250)
        {
            throw new BusinessLogicException("El mensaje supera el número de caracteres permitidos");
        }
        CalificacionEntity newEntity = calificacionPersistence.update(calificacionEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar la calificacion con id = {0}", calificacionEntity.getId());
        return newEntity;
    }

    /**
     * Eliminar un libro por ID
     *
     * @param calificacionesId El ID de la calificacion a eliminar
     */
    public void deleteCalificacion(Long calificacionesId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar la calificacion con id = {0}", calificacionesId);
        calificacionPersistence.delete(calificacionesId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar la calificacion con id = {0}", calificacionesId);
    }
}
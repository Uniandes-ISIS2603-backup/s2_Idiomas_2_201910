/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.ejb;

import co.edu.uniandes.csw.idiomas.entities.ActividadEntity;
import co.edu.uniandes.csw.idiomas.entities.ComentarioEntity;
import co.edu.uniandes.csw.idiomas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.idiomas.persistence.ActividadPersistence;
import co.edu.uniandes.csw.idiomas.persistence.ComentarioPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;

/**
 * Clase que implementa la conexion con la persistencia para la relación entre
 * la entidad de Actividad y ComentarioActividad.
 * @author g.cubillosb
 */
public class ActividadComentarioActividadLogic {
    
    // -----------------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------------
    
    /**
     * Logger para las asociaciones de la clase.
     */
    private static final Logger LOGGER = Logger.getLogger(ActividadComentarioActividadLogic.class.getName());

    /**
     * Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.
     */
    @Inject
    private ComentarioPersistence comentarioActividadPersistence;

    /**
     * Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.
     */
    @Inject
    private ActividadPersistence actividadPersistence;
    
    // -------------------------------------------------------------------------
    // Métodos
    // -------------------------------------------------------------------------

    /**
     * Asocia un ComentarioActividad existente a un Actividad
     *
     * @param actividadesId Identificador de la instancia de Actividad
     * @param comentariosId Identificador de la instancia de ComentarioActividad
     * @return Instancia de ComentarioEntity que fue asociada a Actividad
     */
    public ComentarioEntity addComentarioActividad(Long actividadesId, Long comentariosId) {
        LOGGER.log(Level.INFO, "Inicia proceso de asociarle un comentario a la actividad con id = {0}", actividadesId);
        ActividadEntity actividadEntity = actividadPersistence.find(actividadesId);
        ComentarioEntity comentarioActividadEntity = comentarioActividadPersistence.find(comentariosId);
        comentarioActividadEntity.setActividad(actividadEntity);
        LOGGER.log(Level.INFO, "Termina proceso de asociarle un comentario al actividad con id = {0}", actividadesId);
        return comentarioActividadPersistence.find(comentariosId);
    }

    /**
     * Obtiene una colección de instancias de ComentarioEntity asociadas a una
     * instancia de Actividad
     *
     * @param actividadesId Identificador de la instancia de Actividad
     * @return Colección de instancias de ComentarioEntity asociadas a la instancia de
     * Actividad
     */
    public List<ComentarioEntity> getComentarios(Long actividadesId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los comentarios del actividad con id = {0}", actividadesId);
        return actividadPersistence.find(actividadesId).getComentarios();
    }

    /**
     * Obtiene una instancia de ComentarioEntity asociada a una instancia de Actividad
     *
     * @param actividadesId Identificador de la instancia de Actividad
     * @param comentariosId Identificador de la instancia de ComentarioActividad
     * @return La entidadd de Libro del actividad
     * @throws BusinessLogicException Si el comentario no está asociado al actividad
     */
    public ComentarioEntity getComentarioActividad(Long actividadesId, Long comentariosId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el comentario con id = {0} del actividad con id = " + actividadesId, comentariosId);
        List<ComentarioEntity> comentarios = actividadPersistence.find(actividadesId).getComentarios();
        ComentarioEntity comentarioActividadEntity = comentarioActividadPersistence.find(comentariosId);
        int index = comentarios.indexOf(comentarioActividadEntity);
        LOGGER.log(Level.INFO, "Termina proceso de consultar el comentario con id = {0} del actividad con id = " + actividadesId, comentariosId);
        if (index >= 0) {
            return comentarios.get(index);
        }
        throw new BusinessLogicException("El comentario no está asociado al actividad");
    }

    /**
     * Remplaza las instancias de ComentarioActividad asociadas a una instancia de Actividad
     *
     * @param actividadId Identificador de la instancia de Actividad
     * @param comentarios Colección de instancias de ComentarioEntity a asociar a instancia
     * de Actividad
     * @return Nueva colección de ComentarioEntity asociada a la instancia de Actividad
     */
    public List<ComentarioEntity> replaceComentarios(Long actividadId, List<ComentarioEntity> comentarios) {
        LOGGER.log(Level.INFO, "Inicia proceso de reemplazar los comentarios asocidos al actividad con id = {0}", actividadId);
        ActividadEntity actividadEntity = actividadPersistence.find(actividadId);
        List<ComentarioEntity> comentarioActividadList = comentarioActividadPersistence.findAll();
        for (ComentarioEntity comentarioActividad : comentarioActividadList) {
            if (comentarios.contains(comentarioActividad)) {
                if (!comentarioActividad.getActividad().equals(actividadEntity)) {
                    comentarioActividad.setActividad(actividadEntity);
                }
            } else {
                comentarioActividad.setActividad(null);
            }
        }
        actividadEntity.setComentarios(comentarios);
        LOGGER.log(Level.INFO, "Termina proceso de reemplazar los comentarios asocidos al actividad con id = {0}", actividadId);
        return actividadEntity.getComentarios();
    }

    /**
     * Desasocia un ComentarioActividad existente de un Actividad existente
     *
     * @param actividadesId Identificador de la instancia de Actividad
     * @param comentariosId Identificador de la instancia de ComentarioActividad
     */
    public void removeComentarioActividad(Long actividadesId, Long comentariosId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar un comentario del actividad con id = {0}", actividadesId);
        ComentarioEntity comentarioActividadEntity = comentarioActividadPersistence.find(comentariosId);
        comentarioActividadEntity.setActividad(null);
        LOGGER.log(Level.INFO, "Termina proceso de borrar un comentario del actividad con id = {0}", actividadesId);
    }
}

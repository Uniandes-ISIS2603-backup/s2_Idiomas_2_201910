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
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Clase que implementa la conexíon con la persistencia para la relaciónentre la
 * entidad de Actividad y Comentario.
 * @actividad g.cubillosb
 */
@Stateless
public class ActividadComentarioLogic 
{
    
    private static final Logger LOGGER = Logger.getLogger(ActividadComentarioLogic.class.getName());

    @Inject
    private ComentarioPersistence comentarioPersistence;

    @Inject
    private ActividadPersistence actividadPersistence;

    /**
     * Asocia un Comentario existente a un Actividad
     *
     * @param actividadesId Identificador de la instancia de Actividad
     * @param comentariosId Identificador de la instancia de Comentario
     * @return Instancia de ComentarioEntity que fue asociada a Actividad
     */
    public ComentarioEntity addComentario(Long actividadesId, Long comentariosId) {
        LOGGER.log(Level.INFO, "Inicia proceso de asociarle un libro al autor con id = {0}", actividadesId);
        ActividadEntity actividadEntity = actividadPersistence.find(actividadesId);
        ComentarioEntity comentarioEntity = comentarioPersistence.find(comentariosId);
        comentarioEntity.setActividad(actividadEntity);
        LOGGER.log(Level.INFO, "Termina proceso de asociarle un libro al autor con id = {0}", actividadesId);
        return comentarioPersistence.find(comentariosId);
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
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los libros del autor con id = {0}", actividadesId);
        return actividadPersistence.find(actividadesId).getComentarios();
    }

    /**
     * Obtiene una instancia de ComentarioEntity asociada a una instancia de Actividad
     *
     * @param actividadesId Identificador de la instancia de Actividad
     * @param comentariosId Identificador de la instancia de Comentario
     * @return La entidadd de Libro del autor
     * @throws BusinessLogicException Si el libro no está asociado al autor
     */
    public ComentarioEntity getComentario(Long actividadesId, Long comentariosId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el libro con id = {0} del autor con id = " + actividadesId, comentariosId);
        List<ComentarioEntity> comentarios = actividadPersistence.find(actividadesId).getComentarios();
        ComentarioEntity comentarioEntity = comentarioPersistence.find(comentariosId);
        int index = comentarios.indexOf(comentarioEntity);
        LOGGER.log(Level.INFO, "Termina proceso de consultar el libro con id = {0} del autor con id = " + actividadesId, comentariosId);
        if (index >= 0) {
            return comentarios.get(index);
        }
        throw new BusinessLogicException("El libro no está asociado al autor");
    }

    /**
     * Remplaza las instancias de Comentario asociadas a una instancia de Actividad
     *
     * @param actividadId Identificador de la instancia de Actividad
     * @param comentarios Colección de instancias de ComentarioEntity a asociar a instancia
     * de Actividad
     * @return Nueva colección de ComentarioEntity asociada a la instancia de Actividad
     */
    public List<ComentarioEntity> replaceComentarios(Long actividadId, List<ComentarioEntity> comentarios) {
        LOGGER.log(Level.INFO, "Inicia proceso de reemplazar los libros asocidos al actividad con id = {0}", actividadId);
        ActividadEntity actividadEntity = actividadPersistence.find(actividadId);
        List<ComentarioEntity> comentarioList = comentarioPersistence.findAll();
        for (ComentarioEntity comentario : comentarioList) {
            if (comentarios.contains(comentario)) {
                if (!comentario.getActividad().contains(actividadEntity)) {
                    comentario.getActividad().add(actividadEntity);
                }
            } else {
                comentario.getActividad().remove(actividadEntity);
            }
        }
        actividadEntity.setComentarios(comentarios);
        LOGGER.log(Level.INFO, "Termina proceso de reemplazar los libros asocidos al actividad con id = {0}", actividadId);
        return actividadEntity.getComentarios();
    }

    /**
     * Desasocia un Comentario existente de un Actividad existente
     *
     * @param actividadesId Identificador de la instancia de Actividad
     * @param comentariosId Identificador de la instancia de Comentario
     */
    public void removeComentario(Long actividadesId, Long comentariosId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar un libro del actividad con id = {0}", actividadesId);
        ActividadEntity actividadEntity = actividadPersistence.find(actividadesId);
        ComentarioEntity comentarioEntity = comentarioPersistence.find(comentariosId);
        comentarioEntity.getActividad().remove(actividadEntity);
        LOGGER.log(Level.INFO, "Termina proceso de borrar un libro del actividad con id = {0}", actividadesId);
    }
}
}

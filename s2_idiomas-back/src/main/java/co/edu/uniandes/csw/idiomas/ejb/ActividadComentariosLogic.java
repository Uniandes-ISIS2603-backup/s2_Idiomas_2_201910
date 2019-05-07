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
public class ActividadComentariosLogic 
{

    private static final Logger LOGGER = Logger.getLogger(ActividadComentariosLogic.class.getName());

    @Inject
    private ComentarioPersistence comentarioPersistence;

    @Inject
    private ActividadPersistence actividadPersistence;

    /**
     * Agregar un comentario a la actividad
     *
     * @param comentariosId El id comentario a guardar
     * @param actividadesId El id de la actividad en la cual se va a guardar el
     * comentario.
     * @return El comentario creado.
     */
    public ComentarioEntity addComentario(Long comentariosId, Long actividadesId) {
        LOGGER.log(Level.INFO, "Inicia proceso de agregarle un comentario a la actividad con id = {0}", actividadesId);
        ActividadEntity actividadEntity = actividadPersistence.find(actividadesId);
        ComentarioEntity comentarioEntity = comentarioPersistence.find(comentariosId);
        comentarioEntity.setActividad(actividadEntity);
        LOGGER.log(Level.INFO, "Termina proceso de agregarle un comentario a la actividad con id = {0}", actividadesId);
        return comentarioEntity;
    }

    /**
     * Retorna todos los comentarios asociados a una actividad
     *
     * @param actividadesId El ID de la actividad buscada
     * @return La lista de comentarios de la actividad
     */
    public List<ComentarioEntity> getComentarios(Long actividadesId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar los comentarios asociados a la actividad con id = {0}", actividadesId);
        return actividadPersistence.find(actividadesId).getComentarios();
    }

    /**
     * Retorna un comentario asociado a una actividad
     *
     * @param actividadesId El id de la actividad a buscar.
     * @param comentariosId El id del comentario a buscar
     * @return El comentario encontrado dentro de la actividad.
     * @throws BusinessLogicException Si el comentario no se encuentra en la
     * actividad
     */
    public ComentarioEntity getComentario(Long actividadesId, Long comentariosId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el comentario con id = {0} de la actividad con id = " + actividadesId, comentariosId);
        List<ComentarioEntity> comentarios = actividadPersistence.find(actividadesId).getComentarios();
        ComentarioEntity comentarioEntity = comentarioPersistence.find(comentariosId);
        int index = comentarios.indexOf(comentarioEntity);
        LOGGER.log(Level.INFO, "Termina proceso de consultar el comentario con id = {0} de la actividad con id = " + actividadesId, comentariosId);
        if (index >= 0) {
            return comentarios.get(index);
        }
        throw new BusinessLogicException("El comentario no está asociado a la actividad");
    }

    /**
     * Remplazar comentarios de una actividad
     *
     * @param comentarios Lista de comentarios que serán los de la actividad.
     * @param actividadesId El id de la actividad que se quiere actualizar.
     * @return La lista de comentarios actualizada.
     */
    public List<ComentarioEntity> replaceComentarios(Long actividadesId, List<ComentarioEntity> comentarios) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar la actividad con id = {0}", actividadesId);
        ActividadEntity actividadEntity = actividadPersistence.find(actividadesId);
        List<ComentarioEntity> comentarioList = comentarioPersistence.findAll();
        for (ComentarioEntity comentario : comentarioList) {
            if (comentarios.contains(comentario)) {
                comentario.setActividad(actividadEntity);
            } else if (comentario.getActividad() != null && comentario.getActividad().equals(actividadEntity)) {
                comentario.setActividad(null);
            }
        }
        LOGGER.log(Level.INFO, "Termina proceso de actualizar la actividad con id = {0}", actividadesId);
        return comentarios;
    }
}
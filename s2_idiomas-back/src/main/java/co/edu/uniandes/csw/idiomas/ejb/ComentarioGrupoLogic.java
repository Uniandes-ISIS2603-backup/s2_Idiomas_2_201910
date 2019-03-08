/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.ejb;

import co.edu.uniandes.csw.idiomas.entities.ComentarioGrupoEntity;
import co.edu.uniandes.csw.idiomas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.idiomas.persistence.ComentarioGrupoPersistence;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author se.gamboa
 */
@Stateless
public class ComentarioGrupoLogic {

    @Inject
    private ComentarioGrupoPersistence persistence;

    private static final Logger LOGGER = Logger.getLogger(ComentarioCalificacionLogic.class.getName());
    /**
     * Creac un comentario de tipo Grupo
     *
     * @param entidad
     * @return entidad creada.
     * @throws BusinessLogicException
     */
    public ComentarioGrupoEntity createGroupComment(ComentarioGrupoEntity entidad) throws BusinessLogicException {
        if (entidad.getTitulo() == null) {
            throw new BusinessLogicException("El titulo del comentario no puede ser null");
        }
        if (entidad.getTitulo().length() == 0) {
            throw new BusinessLogicException("El titulo debe contener al menos un caracter");
        }
        entidad = persistence.create(entidad);
        return entidad;
    }
    
    public ComentarioGrupoEntity getComentario(Long commentId) 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la chat con id = {0}", commentId);
        // Note que, por medio de la inyección de dependencias se llama al método "find(id)" que se encuentra en la persistencia.
        ComentarioGrupoEntity comentarioEntity = persistence.find(commentId);
        if (comentarioEntity == null) {
            LOGGER.log(Level.SEVERE, "La chat con el id = {0} no existe", commentId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar la chat con id = {0}", commentId);
        return comentarioEntity;
    }
    
    public void deleteComment(Long commentId) throws BusinessLogicException 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar la chat con id = {0}", commentId);
        persistence.delete(commentId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar la chat con id = {0}", commentId);
    }
    
    /**
     *
     * Actualizar una chat.
     *
     * @param commentId: id de la chat para buscarla en la base de
     * datos.
     * @param comentarioEntity: chat con los cambios para ser actualizada,
     * por ejemplo el nombre.
     * @return la chat con los cambios actualizados en la base de datos.
     */
    public ComentarioGrupoEntity updateComment(Long  commentId, ComentarioGrupoEntity comentarioEntity) 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar la chat con id = {0}",  commentId);
        // Note que, por medio de la inyección de dependencias se llama al método "update(entity)" que se encuentra en la persistencia.
        ComentarioGrupoEntity newEntity = persistence.update(comentarioEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar la chat con id = {0}", comentarioEntity.getId());
        return newEntity;
    }
}

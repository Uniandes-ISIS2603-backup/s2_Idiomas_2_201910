/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.ejb;

import co.edu.uniandes.csw.idiomas.entities.ComentarioBlogEntity;
import co.edu.uniandes.csw.idiomas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.idiomas.persistence.ComentarioBlogPersistence;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author se.gamboa
 */
@Stateless
public class ComentarioBlogLogic {
    @Inject
    private ComentarioBlogPersistence persistence;
    private static final Logger LOGGER = Logger.getLogger(ComentarioBlogLogic.class.getName());
    
    /**
     * Creac un comentario de tipo blog
     * @param entidad
     * @return
     * @throws BusinessLogicException 
     */
    public ComentarioBlogEntity createBlogComment(ComentarioBlogEntity entidad)throws BusinessLogicException
    {
         //Verifica que el titulo no sea null
        if(entidad.getTitulo() == null)
        {
            throw new BusinessLogicException("El titulo del comentario no puede ser null");
        }
         //Verifica que el titulo tenga longitud mayor a cero
        if(entidad.getTitulo().length()== 0)
        {
            throw new BusinessLogicException("El titulo debe contener al menos un caracter");
        }
        //        Verifica que el texto no sea de longitud = 0.
        if(entidad.getTexto().length()== 0){
            throw new BusinessLogicException("El texto no puede ser vacío");
        }
//        Verifica que el texto no sea mayor a 300 caracteres.
        if(entidad.getTexto().length()> 300){
            throw new BusinessLogicException("El texto no puede ser mayor a 300 caracteres");
        }
        entidad = persistence.create(entidad);
        return entidad;
    }
    
    public ComentarioBlogEntity getComentario(Long commentId) 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la chat con id = {0}", commentId);
        // Note que, por medio de la inyección de dependencias se llama al método "find(id)" que se encuentra en la persistencia.
        ComentarioBlogEntity comentarioEntity = persistence.find(commentId);
        if (comentarioEntity == null) {
            LOGGER.log(Level.SEVERE, "La chat con el id = {0} no existe", commentId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar la chat con id = {0}", commentId);
        return comentarioEntity;
    }
    
    public void deleteComment(Long commentId)  
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
    public ComentarioBlogEntity updateComment(Long  commentId, ComentarioBlogEntity comentarioEntity) 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar la chat con id = {0}",  commentId);
        // Note que, por medio de la inyección de dependencias se llama al método "update(entity)" que se encuentra en la persistencia.
        ComentarioBlogEntity newEntity = persistence.update(comentarioEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar la chat con id = {0}", comentarioEntity.getId());
        return newEntity;
    }
}

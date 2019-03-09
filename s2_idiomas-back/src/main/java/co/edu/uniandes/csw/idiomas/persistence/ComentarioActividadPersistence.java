/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.persistence;

import co.edu.uniandes.csw.idiomas.entities.ComentarioActividadEntity;
import co.edu.uniandes.csw.idiomas.entities.ComentarioEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author se.gamboa
 */
@Stateless
public class ComentarioActividadPersistence {
    @PersistenceContext(unitName = "idiomasPU")
    protected EntityManager em;
    private static final Logger LOGGER = Logger.getLogger(ComentarioActividadPersistence.class.getName());

    public ComentarioActividadEntity create(ComentarioActividadEntity entidad) {
        LOGGER.log(Level.INFO, "Creando un nuevo comentario");
        em.persist(entidad);
        LOGGER.log(Level.INFO, "Saliendo de crear un comentario nuevo");
        return entidad;
    }

    public ComentarioActividadEntity find(Long commentsID) {
        return em.find(ComentarioActividadEntity.class, commentsID);
    }
    
    public List<ComentarioActividadEntity> findAll(){
        TypedQuery<ComentarioActividadEntity> query = em.createQuery("select u from ComentarioActividadEntity u", ComentarioActividadEntity.class);
        return query.getResultList();
    }
    
    /**
     *
     * Borra una comment de la base de datos recibiendo como argumento el id de la
     * comment.
     *
     * @param commentId: id correspondiente a la comment a borrar.
     */
    public void delete(Long commentId) {
        LOGGER.log(Level.INFO, "Borrando el comentario con id={0}", commentId);
        ComentarioActividadEntity comentarioEntity = em.find(ComentarioActividadEntity.class, commentId);
        em.remove(comentarioEntity);
    }
    
     /**
     * Actualiza una comentario.
     *
     * @param comentarioEntity: la comment que viene con los nuevos cambios. Por ejemplo
     * el nombre pudo cambiar. En ese caso, se haria uso del método update.
     * @return una comment con los cambios aplicados.
     */
    public ComentarioActividadEntity update(ComentarioActividadEntity comentarioEntity) {
        LOGGER.log(Level.INFO, "Actualizando editorial con id = {0}", comentarioEntity.getId());
        LOGGER.log(Level.INFO, "Saliendo de actualizar la editorial con id = {0}", comentarioEntity.getId());
        return em.merge(comentarioEntity);
    }
}

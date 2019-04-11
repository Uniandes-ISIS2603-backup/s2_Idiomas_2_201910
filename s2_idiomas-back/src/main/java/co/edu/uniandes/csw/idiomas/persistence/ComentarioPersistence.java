/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.persistence;

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
public class ComentarioPersistence {

    @PersistenceContext(unitName = "idiomasPU")
    protected EntityManager em;
    private static final Logger LOGGER = Logger.getLogger(ComentarioPersistence.class.getName());

    public ComentarioEntity create(ComentarioEntity entidad) {
        LOGGER.log(Level.INFO, "Creando un nuevo comentario");
        em.persist(entidad);
        LOGGER.log(Level.INFO, "Saliendo de crear un comentario nuevo");
        return entidad;
    }

    public ComentarioEntity find(Long commentsID) {
        LOGGER.log(Level.INFO, "{0}", em.find(ComentarioEntity.class, commentsID).getId());
        return em.find(ComentarioEntity.class, commentsID);
        
        
    }

    public List<ComentarioEntity> findAll() {
        TypedQuery<ComentarioEntity> query = em.createQuery("select ID from COMENTARIOENTITY WHERE TITULO = 'MUNDO'", ComentarioEntity.class);
         LOGGER.log(Level.INFO, "Consultando todos los comentarios: " + query.getResultList().get(0));
         System.out.println("Consultando todos los comentarios: " + query.getResultList().get(0).getAutor());
        return query.getResultList();
    }

    public void delete(Long commentId) {
        LOGGER.log(Level.INFO, "Borrando el libro con id={0}", commentId);
        ComentarioEntity comentarioEntity = em.find(ComentarioEntity.class, commentId);
        em.remove(comentarioEntity);
    }
    
        /**
     * Actualiza una Comentario.
     *
     * @param pComentarioEntity: la Comentario que viene con los nuevos cambios.
     * Por ejemplo el nombre pudo cambiar. En ese caso, se haria uso del método
     * update.
     * @return una Comentario con los cambios aplicados.
     */
    public ComentarioEntity update(ComentarioEntity pComentarioEntity) {
        LOGGER.log(Level.INFO, "Actualizando Comentario con id = {0}", pComentarioEntity.getId());
        /* Note que hacemos uso de un método propio del EntityManager llamado merge() que recibe como argumento
        la Comentario con los cambios, esto es similar a 
        "UPDATE table_name SET column1 = value1, column2 = value2, ... WHERE condition;" en SQL.
         */
        LOGGER.log(Level.INFO, "Saliendo de actualizar la Comentario con id = {0}", pComentarioEntity.getId());
        return em.merge(pComentarioEntity);
    }
}

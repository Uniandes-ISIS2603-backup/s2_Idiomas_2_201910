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
 * @author Santiago Gamboa
 */
@Stateless
public class ComentarioPersistence {

    @PersistenceContext(unitName = "idiomasPU")
    protected EntityManager em;
    //private static final Logger LOGGER = Logger.getLogger(ComentarioPersistence.class.getName());

    public ComentarioEntity create(ComentarioEntity entidad) {
        // LOGGER.log(Level.INFO, "Creando un nuevo comentario");
        em.persist(entidad);
        //LOGGER.log(Level.INFO, "Saliendo de crear un comentario nuevo");
        return entidad;
    }

    public ComentarioEntity find(Long commentsID) {
        return em.find(ComentarioEntity.class, commentsID);
    }
    
    public List<ComentarioEntity> findAll(){
        TypedQuery<ComentarioEntity> query = em.createQuery("select u from ComentarioEntity u", ComentarioEntity.class);
        return query.getResultList();
    }
}

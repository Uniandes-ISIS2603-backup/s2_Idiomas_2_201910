/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.persistence;

import co.edu.uniandes.csw.idiomas.entities.ComentarioBlogEntity;
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
public class ComentarioBlogPersistence {
    @PersistenceContext(unitName = "idiomasPU")
    protected EntityManager em;
    private static final Logger LOGGER = Logger.getLogger(ComentarioBlogPersistence.class.getName());

    public ComentarioBlogEntity create(ComentarioBlogEntity entidad) {
        LOGGER.log(Level.INFO, "Creando un nuevo comentario");
        em.persist(entidad);
        LOGGER.log(Level.INFO, "Saliendo de crear un comentario nuevo");
        return entidad;
    }

    public ComentarioBlogEntity find(Long commentsID) {
        return em.find(ComentarioBlogEntity.class, commentsID);
    }
    
    public List<ComentarioBlogEntity> findAll(){
        TypedQuery<ComentarioBlogEntity> query = em.createQuery("select u from ComentarioBlogEntity u", ComentarioBlogEntity.class);
        return query.getResultList();
    }
}

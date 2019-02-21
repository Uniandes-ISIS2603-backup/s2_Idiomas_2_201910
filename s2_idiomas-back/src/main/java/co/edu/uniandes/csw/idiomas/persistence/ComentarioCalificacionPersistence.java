/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.persistence;

import co.edu.uniandes.csw.idiomas.entities.ComentarioActividadEntity;
import co.edu.uniandes.csw.idiomas.entities.ComentarioCalificacionEntity;
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
public class ComentarioCalificacionPersistence {

    @PersistenceContext(unitName = "idiomasPU")
    protected EntityManager em;
    private static final Logger LOGGER = Logger.getLogger(ComentarioCalificacionPersistence.class.getName());

    public ComentarioCalificacionEntity create(ComentarioCalificacionEntity entidad) {
        LOGGER.log(Level.INFO, "Creando un nuevo comentario");
        em.persist(entidad);
        LOGGER.log(Level.INFO, "Saliendo de crear un comentario nuevo");
        return entidad;
    }

    public ComentarioCalificacionEntity find(Long commentsID) {
        return em.find(ComentarioCalificacionEntity.class, commentsID);
    }

    public List<ComentarioCalificacionEntity> findAll() {
        TypedQuery<ComentarioCalificacionEntity> query = em.createQuery("select u from ComentarioCalificacionEntity u", ComentarioCalificacionEntity.class);
        return query.getResultList();
    }
}

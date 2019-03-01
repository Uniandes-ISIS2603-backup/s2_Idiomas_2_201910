/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.persistence;


import co.edu.uniandes.csw.idiomas.entities.ComentarioGrupoEntity;
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
public class ComentarioGrupoPersistence {
    @PersistenceContext(unitName = "idiomasPU")
    protected EntityManager em;
    private static final Logger LOGGER = Logger.getLogger(ComentarioGrupoPersistence.class.getName());

    public ComentarioGrupoEntity create(ComentarioGrupoEntity entidad) {
        LOGGER.log(Level.INFO, "Creando un nuevo comentario de tipo grupo");
        em.persist(entidad);
        LOGGER.log(Level.INFO, "Saliendo de crear un comentario grupo nuevo");
        return entidad;
    }

    public ComentarioGrupoEntity find(Long commentsID) {
        return em.find(ComentarioGrupoEntity.class, commentsID);
    }
    
    public List<ComentarioGrupoEntity> findAll(){
        TypedQuery<ComentarioGrupoEntity> query = em.createQuery("select u from ComentarioGrupoEntity u", ComentarioGrupoEntity.class);
        return query.getResultList();
    }
}

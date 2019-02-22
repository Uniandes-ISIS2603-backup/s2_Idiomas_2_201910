/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.persistence;

import co.edu.uniandes.csw.idiomas.entities.ActividadEntity;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

// TODO Completar documentación
/**
 *
 * @author g.cubillosb
 */
@Stateless
public class ActividadPersistence {
    
    // TODO Completar
//    private static final Logger LOGGER = Logger.getLogger(ActividadPersistence.class.getName());
//    
//    @PersistenceContext (unitName = "idiomasPU")
//    protected EntityManager em;
//    
//    /**
//     * Método para persistir
//     * @param pActividadEntity
//     * @return 
//     */
//    public ActividadEntity create (ActividadEntity pActividadEntity)
//    {
//        LOGGER.log(Level.INFO, "Creando una actividad nueva");
//        em.persist(pActividadEntity);
//        LOGGER.log(Level.INFO, "Saliendo de crear una actividad nueva");
//        return pActividadEntity;
//    }
}

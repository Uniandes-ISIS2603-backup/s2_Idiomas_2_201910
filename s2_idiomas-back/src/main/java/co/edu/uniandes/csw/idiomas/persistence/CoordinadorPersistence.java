/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.persistence;

import co.edu.uniandes.csw.idiomas.entities.CoordinadorEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author j.barbosaj 201717575
 */
@Stateless
public class CoordinadorPersistence {
    private static final Logger LOGGER = Logger.getLogger(CoordinadorPersistence.class.getName());

    @PersistenceContext(unitName = "idiomasPU")
    protected EntityManager em;

    /**
     * Método para persisitir la entidad en la base de datos.
     *
     * @param CoordinadorEntity objeto Coordinador que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public CoordinadorEntity create(CoordinadorEntity CoordinadorEntity) {
        LOGGER.log(Level.INFO, "Creando una Coordinador nueva");        
        em.persist(CoordinadorEntity);
        LOGGER.log(Level.INFO, "Saliendo de crear una Coordinador nueva");
        return CoordinadorEntity;
    }

    /**
     *
     * Borra una Coordinador de la base de datos recibiendo como argumento el id
     * de la Coordinador
     *
     * @param CoordinadorsId: id correspondiente a la Coordinador a borrar.
     */
    public void delete(Long CoordinadorsId) {
        LOGGER.log(Level.INFO, "Borrando Coordinador con id = {0}", CoordinadorsId);
        // Se hace uso de mismo método que esta explicado en public CoordinadorEntity find(Long id) para obtener la Coordinador a borrar.
        CoordinadorEntity entity = em.find(CoordinadorEntity.class, CoordinadorsId);
        /* Note que una vez obtenido el objeto desde la base de datos llamado "entity", volvemos hacer uso de un método propio del
         EntityManager para eliminar de la base de datos el objeto que encontramos y queremos borrar.
         Es similar a "delete from CoordinadorEntity where id=id;" - "DELETE FROM table_nombre WHERE condition;" en SQL.*/
        em.remove(entity);
        LOGGER.log(Level.INFO, "Saliendo de borrar la Coordinador con id = {0}", CoordinadorsId);
    }

    /**
     * Busca si hay alguna Coordinador con el nombre que se envía de argumento
     *
     * @param nombre: Nombre de la Coordinador que se está buscando
     * @return null si no existe ninguna Coordinador con el nombre del argumento.
     * Si existe alguna devuelve la primera.
     */
    public CoordinadorEntity findByName(String nombre) {
        LOGGER.log(Level.INFO, "Consultando Coordinador por nombre ", nombre);
        // Se crea un query para buscar Coordinadors con el nombre que recibe el método como argumento. ":nombre" es un placeholder que debe ser remplazado
        TypedQuery query = em.createQuery("Select e From CoordinadorEntity e where e.nombre = :nombre", CoordinadorEntity.class);
        // Se remplaza el placeholder ":nombre" con el valor del argumento 
        query = query.setParameter("nombre", nombre);
        // Se invoca el query se obtiene la lista resultado
        List<CoordinadorEntity> sameName = query.getResultList();
        CoordinadorEntity result;
        if (sameName == null) {
            result = null;
        } else if (sameName.isEmpty()) {
            result = null;
        } else {
            result = sameName.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar Coordinador por nombre ", nombre);
        return result;
    }
}

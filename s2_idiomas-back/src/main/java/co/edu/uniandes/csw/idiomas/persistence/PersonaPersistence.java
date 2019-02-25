/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.persistence;

import co.edu.uniandes.csw.idiomas.entities.PersonaEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author j.barbosaj
 */
@Stateless
public class PersonaPersistence 
{
    private static final Logger LOGGER = Logger.getLogger(PersonaPersistence.class.getName());
    
    @PersistenceContext(unitName = "idiomasPU")
    protected EntityManager em; 
    
    /**
     * Método para persisitir la entidad en la base de datos.
     *
     * @param PersonaEntity objeto Persona que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public PersonaEntity create(PersonaEntity PersonaEntity) {
        LOGGER.log(Level.INFO, "Creando una Persona nueva");        
        em.persist(PersonaEntity);
        LOGGER.log(Level.INFO, "Saliendo de crear una Persona nueva");
        return PersonaEntity;
    }

    /**
     *
     * Borra una Persona de la base de datos recibiendo como argumento el id
     * de la Persona
     *
     * @param PersonasId: id correspondiente a la Persona a borrar.
     */
    public void delete(Long PersonasId) {
        LOGGER.log(Level.INFO, "Borrando Persona con id = {0}", PersonasId);
        // Se hace uso de mismo método que esta explicado en public PersonaEntity find(Long id) para obtener la Persona a borrar.
        PersonaEntity entity = em.find(PersonaEntity.class, PersonasId);
        /* Note que una vez obtenido el objeto desde la base de datos llamado "entity", volvemos hacer uso de un método propio del
         EntityManager para eliminar de la base de datos el objeto que encontramos y queremos borrar.
         Es similar a "delete from PersonaEntity where id=id;" - "DELETE FROM table_nombre WHERE condition;" en SQL.*/
        em.remove(entity);
        LOGGER.log(Level.INFO, "Saliendo de borrar la Persona con id = {0}", PersonasId);
    }

    /**
     * Busca si hay alguna Persona con el nombre que se envía de argumento
     *
     * @param nombre: Nombre de la Persona que se está buscando
     * @return null si no existe ninguna Persona con el nombre del argumento.
     * Si existe alguna devuelve la primera.
     */
    public PersonaEntity findByNombre(String nombre) {
        LOGGER.log(Level.INFO, "Consultando Persona por nombre ", nombre);
        // Se crea un query para buscar Personas con el nombre que recibe el método como argumento. ":nombre" es un placeholder que debe ser remplazado
        TypedQuery query = em.createQuery("Select e From PersonaEntity e where e.nombre = :nombre", PersonaEntity.class);
        // Se remplaza el placeholder ":nombre" con el valor del argumento 
        query = query.setParameter("nombre", nombre);
        // Se invoca el query se obtiene la lista resultado
        List<PersonaEntity> sameNombre = query.getResultList();
        PersonaEntity result;
        if (sameNombre == null) {
            result = null;
        } else if (sameNombre.isEmpty()) {
            result = null;
        } else {
            result = sameNombre.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar Persona por nombre ", nombre);
        return result;
    }
}

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
 * @persona g.cubillosb
 */
@Stateless
public class ActividadPersistence {
    
    
    private static final Logger LOGGER = Logger.getLogger(PersonaPersistence.class.getName());

    @PersistenceContext(unitName = "BookStorePU")
    protected EntityManager em;

    /**
     * Crea un autor en la base de datos
     *
     * @param personaEntity objeto persona que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public PersonaEntity create(PersonaEntity personaEntity) {
        LOGGER.log(Level.INFO, "Creando un autor nuevo");
        /* Note que hacemos uso de un método propio de EntityManager para persistir la persona en la base de datos.
        Es similar a "INSERT INTO table_name (column1, column2, column3, ...) VALUES (value1, value2, value3, ...);" en SQL.
         */
        em.persist(personaEntity);
        LOGGER.log(Level.INFO, "Persona creado");
        return personaEntity;
    }

    /**
     * Devuelve todas las personaes de la base de datos.
     *
     * @return una lista con todas las personaes que encuentre en la base de
     * datos, "select u from PersonaEntity u" es como un "select * from
     * PersonaEntity;" - "SELECT * FROM table_name" en SQL.
     */
    public List<PersonaEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todos los personas");
        // Se crea un query para buscar todas las personaes en la base de datos.
        TypedQuery query = em.createQuery("select u from PersonaEntity u", PersonaEntity.class);
        // Note que en el query se hace uso del método getResultList() que obtiene una lista de personaes.
        return query.getResultList();
    }

    /**
     * Busca si hay alguna persona con el id que se envía de argumento
     *
     * @param personasId: id correspondiente a la persona buscada.
     * @return un persona.
     */
    public PersonaEntity find(Long personasId) {
        LOGGER.log(Level.INFO, "Consultando el autor con id={0}", personasId);
        /* Note que se hace uso del metodo "find" propio del EntityManager, el cual recibe como argumento 
        el tipo de la clase y el objeto que nos hara el filtro en la base de datos en este caso el "id"
        Suponga que es algo similar a "select * from PersonaEntity where id=id;" - "SELECT * FROM table_name WHERE condition;" en SQL.
         */
        return em.find(PersonaEntity.class, personasId);
    }

    /**
     * Actualiza una persona.
     *
     * @param personaEntity: la persona que viene con los nuevos cambios. Por
     * ejemplo el nombre pudo cambiar. En ese caso, se haria uso del método
     * update.
     * @return una persona con los cambios aplicados.
     */
    public PersonaEntity update(PersonaEntity personaEntity) {
        LOGGER.log(Level.INFO, "Actualizando el persona con id={0}", personaEntity.getId());
        /* Note que hacemos uso de un método propio del EntityManager llamado merge() que recibe como argumento
        la persona con los cambios, esto es similar a 
        "UPDATE table_name SET column1 = value1, column2 = value2, ... WHERE condition;" en SQL.
         */
        return em.merge(personaEntity);
    }

    /**
     * Borra una persona de la base de datos recibiendo como argumento el id de
     * la persona
     *
     * @param personasId: id correspondiente a la persona a borrar.
     */
    public void delete(Long personasId) {

        LOGGER.log(Level.INFO, "Borrando el persona con id={0}", personasId);
        // Se hace uso de mismo método que esta explicado en public PersonaEntity find(Long id) para obtener la persona a borrar.
        PersonaEntity personaEntity = em.find(PersonaEntity.class, personasId);
        /* Note que una vez obtenido el objeto desde la base de datos llamado "entity", volvemos hacer uso de un método propio del
        EntityManager para eliminar de la base de datos el objeto que encontramos y queremos borrar.
        Es similar a "delete from PersonaEntity where id=id;" - "DELETE FROM table_name WHERE condition;" en SQL.*/
        em.remove(personaEntity);
    }
    
}

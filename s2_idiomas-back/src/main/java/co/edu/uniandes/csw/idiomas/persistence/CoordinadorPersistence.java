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
 * @coordinador j.barbosaj 201717575
 */
@Stateless
public class CoordinadorPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(CoordinadorPersistence.class.getName());

    @PersistenceContext(unitName = "BookStorePU")
    protected EntityManager em;

    /**
     * Crea un autor en la base de datos
     *
     * @param coordinadorEntity objeto coordinador que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public CoordinadorEntity create(CoordinadorEntity coordinadorEntity) {
        LOGGER.log(Level.INFO, "Creando un autor nuevo");
        /* Note que hacemos uso de un método propio de EntityManager para persistir la coordinador en la base de datos.
        Es similar a "INSERT INTO table_name (column1, column2, column3, ...) VALUES (value1, value2, value3, ...);" en SQL.
         */
        em.persist(coordinadorEntity);
        LOGGER.log(Level.INFO, "Autor creado");
        return coordinadorEntity;
    }

    /**
     * Devuelve todas las coordinadores de la base de datos.
     *
     * @return una lista con todas las coordinadores que encuentre en la base de
     * datos, "select u from CoordinadorEntity u" es como un "select * from
     * CoordinadorEntity;" - "SELECT * FROM table_name" en SQL.
     */
    public List<CoordinadorEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todos los autores");
        // Se crea un query para buscar todas las coordinadores en la base de datos.
        TypedQuery query = em.createQuery("select u from CoordinadorEntity u", CoordinadorEntity.class);
        // Note que en el query se hace uso del método getResultList() que obtiene una lista de coordinadores.
        return query.getResultList();
    }

    /**
     * Busca si hay alguna coordinador con el id que se envía de argumento
     *
     * @param coordinadoresId: id correspondiente a la coordinador buscada.
     * @return un coordinador.
     */
    public CoordinadorEntity find(Long coordinadoresId) {
        LOGGER.log(Level.INFO, "Consultando el autor con id={0}", coordinadoresId);
        /* Note que se hace uso del metodo "find" propio del EntityManager, el cual recibe como argumento 
        el tipo de la clase y el objeto que nos hara el filtro en la base de datos en este caso el "id"
        Suponga que es algo similar a "select * from CoordinadorEntity where id=id;" - "SELECT * FROM table_name WHERE condition;" en SQL.
         */
        return em.find(CoordinadorEntity.class, coordinadoresId);
    }

    /**
     * Actualiza una coordinador.
     *
     * @param coordinadorEntity: la coordinador que viene con los nuevos cambios. Por
     * ejemplo el nombre pudo cambiar. En ese caso, se haria uso del método
     * update.
     * @return una coordinador con los cambios aplicados.
     */
    public CoordinadorEntity update(CoordinadorEntity coordinadorEntity) {
        LOGGER.log(Level.INFO, "Actualizando el coordinador con id={0}", coordinadorEntity.getId());
        /* Note que hacemos uso de un método propio del EntityManager llamado merge() que recibe como argumento
        la coordinador con los cambios, esto es similar a 
        "UPDATE table_name SET column1 = value1, column2 = value2, ... WHERE condition;" en SQL.
         */
        return em.merge(coordinadorEntity);
    }

    /**
     * Borra una coordinador de la base de datos recibiendo como argumento el id de
     * la coordinador
     *
     * @param coordinadoresId: id correspondiente a la coordinador a borrar.
     */
    public void delete(Long coordinadoresId) {

        LOGGER.log(Level.INFO, "Borrando el coordinador con id={0}", coordinadoresId);
        // Se hace uso de mismo método que esta explicado en public CoordinadorEntity find(Long id) para obtener la coordinador a borrar.
        CoordinadorEntity coordinadorEntity = em.find(CoordinadorEntity.class, coordinadoresId);
        /* Note que una vez obtenido el objeto desde la base de datos llamado "entity", volvemos hacer uso de un método propio del
        EntityManager para eliminar de la base de datos el objeto que encontramos y queremos borrar.
        Es similar a "delete from CoordinadorEntity where id=id;" - "DELETE FROM table_name WHERE condition;" en SQL.*/
        em.remove(coordinadorEntity);
    }
}

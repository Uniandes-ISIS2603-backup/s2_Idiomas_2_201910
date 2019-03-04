/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.persistence;

import co.edu.uniandes.csw.idiomas.entities.AnfitrionEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @anfitrion j.barbosaj 201717575
 */
@Stateless
public class AnfitrionPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(AnfitrionPersistence.class.getName());

    @PersistenceContext(unitName = "idiomasPU")
    protected EntityManager em;

    /**
     * Crea un autor en la base de datos
     *
     * @param anfitrionEntity objeto anfitrion que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public AnfitrionEntity create(AnfitrionEntity anfitrionEntity) {
        LOGGER.log(Level.INFO, "Creando un anfitrion nuevo");
        /* Note que hacemos uso de un método propio de EntityManager para persistir la anfitrion en la base de datos.
        Es similar a "INSERT INTO table_name (column1, column2, column3, ...) VALUES (value1, value2, value3, ...);" en SQL.
         */
        em.persist(anfitrionEntity);
        LOGGER.log(Level.INFO, "Anfitrion creado");
        return anfitrionEntity;
    }

    /**
     * Devuelve todas las anfitriones de la base de datos.
     *
     * @return una lista con todas las anfitriones que encuentre en la base de
     * datos, "select u from AnfitrionEntity u" es como un "select * from
     * AnfitrionEntity;" - "SELECT * FROM table_name" en SQL.
     */
    public List<AnfitrionEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todos los anfitriones");
        // Se crea un query para buscar todas las anfitriones en la base de datos.
        TypedQuery query = em.createQuery("select u from AnfitrionEntity u", AnfitrionEntity.class);
        // Note que en el query se hace uso del método getResultList() que obtiene una lista de anfitriones.
        return query.getResultList();
    }

    /**
     * Busca si hay alguna anfitrion con el id que se envía de argumento
     *
     * @param anfitrionesId: id correspondiente a la anfitrion buscada.
     * @return un anfitrion.
     */
    public AnfitrionEntity find(Long anfitrionesId) {
        LOGGER.log(Level.INFO, "Consultando el anfitrion con id={0}", anfitrionesId);
        /* Note que se hace uso del metodo "find" propio del EntityManager, el cual recibe como argumento 
        el tipo de la clase y el objeto que nos hara el filtro en la base de datos en este caso el "id"
        Suponga que es algo similar a "select * from AnfitrionEntity where id=id;" - "SELECT * FROM table_name WHERE condition;" en SQL.
         */
        return em.find(AnfitrionEntity.class, anfitrionesId);
    }

    /**
     * Actualiza una anfitrion.
     *
     * @param anfitrionEntity: la anfitrion que viene con los nuevos cambios. Por
     * ejemplo el nombre pudo cambiar. En ese caso, se haria uso del método
     * update.
     * @return una anfitrion con los cambios aplicados.
     */
    public AnfitrionEntity update(AnfitrionEntity anfitrionEntity) {
        LOGGER.log(Level.INFO, "Actualizando el anfitrion con id={0}", anfitrionEntity.getId());
        /* Note que hacemos uso de un método propio del EntityManager llamado merge() que recibe como argumento
        la anfitrion con los cambios, esto es similar a 
        "UPDATE table_name SET column1 = value1, column2 = value2, ... WHERE condition;" en SQL.
         */
        return em.merge(anfitrionEntity);
    }

    /**
     * Borra una anfitrion de la base de datos recibiendo como argumento el id de
     * la anfitrion
     *
     * @param anfitrionesId: id correspondiente a la anfitrion a borrar.
     */
    public void delete(Long anfitrionesId) {

        LOGGER.log(Level.INFO, "Borrando el anfitrion con id={0}", anfitrionesId);
        // Se hace uso de mismo método que esta explicado en public AnfitrionEntity find(Long id) para obtener la anfitrion a borrar.
        AnfitrionEntity anfitrionEntity = em.find(AnfitrionEntity.class, anfitrionesId);
        /* Note que una vez obtenido el objeto desde la base de datos llamado "entity", volvemos hacer uso de un método propio del
        EntityManager para eliminar de la base de datos el objeto que encontramos y queremos borrar.
        Es similar a "delete from AnfitrionEntity where id=id;" - "DELETE FROM table_name WHERE condition;" en SQL.*/
        em.remove(anfitrionEntity);
    }
}

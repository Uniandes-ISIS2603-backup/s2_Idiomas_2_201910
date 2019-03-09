/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.persistence;

import co.edu.uniandes.csw.idiomas.entities.CalificacionEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Clase que maneja la persistencia para Author. Se conecta a través del Entity
 * Manager de javax.persistance con la base de datos SQL.
 *
 * @author ISIS2603
 */
@Stateless
public class CalificacionPersistence {

    private static final Logger LOGGER = Logger.getLogger(CalificacionPersistence.class.getName());

    @PersistenceContext(unitName = "idiomasPU")
    protected EntityManager em;

    /**
     * Crea una calificación en la base de datos
     *
     * @param calificacionEntity objeto calificación que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public CalificacionEntity create(CalificacionEntity calificacionEntity) {
        LOGGER.log(Level.INFO, "Creando una calificación nueva");
        em.persist(calificacionEntity);
        LOGGER.log(Level.INFO, "Autor creado");
        return calificacionEntity;
    }

    /**
     * Devuelve todas las calificaciones de la base de datos.
     *
     * @return una lista con todas las calificaciones que encuentre en la base de
     * datos, "select u from CalificacionEntity u" es como un "select * from
     * CalificacionEntity;" - "SELECT * FROM table_name" en SQL.
     */
    public List<CalificacionEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todas las calificaciones");
        // Se crea un query para buscar todas las calificaciones en la base de datos.
        TypedQuery query = em.createQuery("select u from CalificacionEntity u", CalificacionEntity.class);
        // Note que en el query se hace uso del método getResultList() que obtiene una lista de authores.
        return query.getResultList();
    }

    /**
     * Busca si hay alguna calificación con el id que se envía de argumento
     *
     * @param calificacionesId: id correspondiente a la calificación buscada.
     * @return una calificación.
     */
    public CalificacionEntity find(Long calificacionesId) {
        LOGGER.log(Level.INFO, "Consultando el autor con id={0}", calificacionesId);
        /* Note que se hace uso del metodo "find" propio del EntityManager, el cual recibe como argumento 
        el tipo de la clase y el objeto que nos hara el filtro en la base de datos en este caso el "id"
        Suponga que es algo similar a "select * from CalificacionEntity where id=id;" - "SELECT * FROM table_name WHERE condition;" en SQL.
         */
        return em.find(CalificacionEntity.class, calificacionesId);
    }

    /**
     * Actualiza una calificacion.
     *
     * @param calificacionEntity: la calificacion que viene con los nuevos cambios. Por
     * ejemplo la calificación pudo cambiar. En ese caso, se haria uso del método
     * update.
     * @return una calificación con los cambios aplicados.
     */
    public CalificacionEntity update(CalificacionEntity calificacionEntity) {
        LOGGER.log(Level.INFO, "Actualizando el author con id={0}", calificacionEntity.getId());
        /* Note que hacemos uso de un método propio del EntityManager llamado merge() que recibe como argumento
        la author con los cambios, esto es similar a 
        "UPDATE table_name SET column1 = value1, column2 = value2, ... WHERE condition;" en SQL.
         */
        return em.merge(calificacionEntity);
    }

    /**
     * Borra una calificación de la base de datos recibiendo como argumento el id de
     * la calificación
     *
     * @param calificacionesId: id correspondiente a la calificación a borrar.
     */
    public void delete(Long calificacionesId) {

        LOGGER.log(Level.INFO, "Borrando la calificacion con id={0}", calificacionesId);
        // Se hace uso de mismo método que esta explicado en public AuthorEntity find(Long id) para obtener la author a borrar.
        CalificacionEntity calificacionEntity = em.find(CalificacionEntity.class, calificacionesId);
        /* Note que una vez obtenido el objeto desde la base de datos llamado "entity", volvemos hacer uso de un método propio del
        EntityManager para eliminar de la base de datos el objeto que encontramos y queremos borrar.
        Es similar a "delete from AuthorEntity where id=id;" - "DELETE FROM table_name WHERE condition;" en SQL.*/
        em.remove(calificacionEntity);
    }
}
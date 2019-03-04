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
 * @author j.barbosaj 201717575
 */
@Stateless
public class AnfitrionPersistence {
    private static final Logger LOGGER = Logger.getLogger(AnfitrionPersistence.class.getName());

    @PersistenceContext(unitName = "idiomasPU")
    protected EntityManager em;

    /**
     * Método para persisitir la entidad en la base de datos.
     *
     * @param AnfitrionEntity objeto Anfitrion que se creará en la base de datos
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
     * @param AnfitrionsId: id correspondiente a la Anfitrion a borrar.
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
     * Busca si hay alguna Anfitrion con el nombre que se envía de argumento
     *
     * @param nombre: Nombre de la Anfitrion que se está buscando
     * @return null si no existe ninguna Anfitrion con el nombre del argumento.
     * Si existe alguna devuelve la primera.
     */
    public AnfitrionEntity findByName(String nombre) {
        LOGGER.log(Level.INFO, "Consultando Anfitrion por nombre ", nombre);
        // Se crea un query para buscar Anfitrions con el nombre que recibe el método como argumento. ":nombre" es un placeholder que debe ser remplazado
        TypedQuery query = em.createQuery("Select e From AnfitrionEntity e where e.nombre = :nombre", AnfitrionEntity.class);
        // Se remplaza el placeholder ":nombre" con el valor del argumento 
        query = query.setParameter("nombre", nombre);
        // Se invoca el query se obtiene la lista resultado
        List<AnfitrionEntity> sameName = query.getResultList();
        AnfitrionEntity result;
        if (sameName == null) {
            result = null;
        } else if (sameName.isEmpty()) {
            result = null;
        } else {
            result = sameName.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar Anfitrion por nombre ", nombre);
        return result;
    }
}

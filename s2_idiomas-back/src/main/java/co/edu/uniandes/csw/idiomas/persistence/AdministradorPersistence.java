/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.persistence;

import co.edu.uniandes.csw.idiomas.entities.AdministradorEntity;
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
public class AdministradorPersistence {
     
    private static final Logger LOGGER = Logger.getLogger(AdministradorEntity.class.getName());

    @PersistenceContext(unitName = "idiomasPU")
    protected EntityManager em;

    /**
     * Crea un administrador en la base de datos
     *
     * @param administradorEntity objeto author que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public AdministradorEntity create(AdministradorEntity administradorEntity) {
        LOGGER.log(Level.INFO, "Creando un administrador nuevo");
        /* Note que hacemos uso de un método propio de EntityManager para persistir la author en la base de datos.
        Es similar a "INSERT INTO table_name (column1, column2, column3, ...) VALUES (value1, value2, value3, ...);" en SQL.
         */
        em.persist(administradorEntity);
        LOGGER.log(Level.INFO, "Autor creado");
        return administradorEntity;
    }

    /**
     * Devuelve todas las administradores de la base de datos.
     *
     * @return una lista con todas las administradores que encuentre en la base de
     * datos, "select u from AdministradorEntity u" es como un "select * from
     * AdministradorEntity;" - "SELECT * FROM table_name" en SQL.
     */
    public List<AdministradorEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todos los administradores");
        // Se crea un query para buscar todas las administradores en la base de datos.
        TypedQuery query = em.createQuery("select u from AdministradorEntity u", AdministradorEntity.class);
        // Note que en el query se hace uso del método getResultList() que obtiene una lista de administradores.
        return query.getResultList();
    }

    /**
     * Busca si hay alguna author con el id que se envía de argumento
     *
     * @param authorsId: id correspondiente a la author buscada.
     * @return un author.
     */
    public AdministradorEntity find(Long authorsId) {
        LOGGER.log(Level.INFO, "Consultando el administrador con id={0}", authorsId);
        /* Note que se hace uso del metodo "find" propio del EntityManager, el cual recibe como argumento 
        el tipo de la clase y el objeto que nos hara el filtro en la base de datos en este caso el "id"
        Suponga que es algo similar a "select * from AdministradorEntity where id=id;" - "SELECT * FROM table_name WHERE condition;" en SQL.
         */
        return em.find(AdministradorEntity.class, authorsId);
    }

    /**
     * Actualiza una author.
     *
     * @param administradorEntity: la author que viene con los nuevos cambios. Por
     * ejemplo el nombre pudo cambiar. En ese caso, se haria uso del método
     * update.
     * @return una author con los cambios aplicados.
     */
    public AdministradorEntity update(AdministradorEntity administradorEntity) {
        LOGGER.log(Level.INFO, "Actualizando el author con id={0}", administradorEntity.getId());
        /* Note que hacemos uso de un método propio del EntityManager llamado merge() que recibe como argumento
        la author con los cambios, esto es similar a 
        "UPDATE table_name SET column1 = value1, column2 = value2, ... WHERE condition;" en SQL.
         */
        return em.merge(administradorEntity);
    }

    /**
     * Borra una author de la base de datos recibiendo como argumento el id de
     * la author
     *
     * @param authorsId: id correspondiente a la author a borrar.
     */
    public void delete(Long authorsId) {

        LOGGER.log(Level.INFO, "Borrando el author con id={0}", authorsId);
        // Se hace uso de mismo método que esta explicado en public AdministradorEntity find(Long id) para obtener la author a borrar.
        AdministradorEntity administradorEntity = em.find(AdministradorEntity.class, authorsId);
        /* Note que una vez obtenido el objeto desde la base de datos llamado "entity", volvemos hacer uso de un método propio del
        EntityManager para eliminar de la base de datos el objeto que encontramos y queremos borrar.
        Es similar a "delete from AdministradorEntity where id=id;" - "DELETE FROM table_name WHERE condition;" en SQL.*/
        em.remove(administradorEntity);
    }

   /**
     * Busca si hay alguna editorial con el nombre que se envía de argumento
     *
     * @param nombre: Nombre de la editorial que se está buscando
     * @return null si no existe ninguna editorial con el nombre del argumento.
     * Si existe alguna devuelve la primera.
     */
    public AdministradorEntity findByName(String nombre) {
        LOGGER.log(Level.INFO, "Consultando editorial por nombre ", nombre);
        // Se crea un query para buscar editoriales con el nombre que recibe el método como argumento. ":nombre" es un placeholder que debe ser remplazado
        TypedQuery query = em.createQuery("Select e From AdministradorEntity e where e.nombre = :nombre", AdministradorEntity.class);
        // Se remplaza el placeholder ":nombre" con el valor del argumento 
        query = query.setParameter("nombre", nombre);
        // Se invoca el query se obtiene la lista resultado
        List<AdministradorEntity> sameName = query.getResultList();
        AdministradorEntity result;
        if (sameName == null) {
            result = null;
        } else if (sameName.isEmpty()) {
            result = null;
        } else {
            result = sameName.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar editorial por nombre ", nombre);
        return result;
    }
}

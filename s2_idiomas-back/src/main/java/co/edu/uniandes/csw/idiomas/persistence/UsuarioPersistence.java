/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.persistence;

import co.edu.uniandes.csw.idiomas.entities.UsuarioEntity;
import co.edu.uniandes.csw.idiomas.entities.UsuarioEntity;
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
public class UsuarioPersistence 
{
    
    /**
     * Logger para las acciones de la clase.
     */
    private static final Logger LOGGER = Logger.getLogger(UsuarioPersistence.class.getName());

    @PersistenceContext(unitName = "idiomasPU")
    protected EntityManager em;

    /**
     * Crea un usuario en la base de datos
     *
     * @param usuarioEntity objeto author que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public UsuarioEntity create(UsuarioEntity usuarioEntity) {
        LOGGER.log(Level.INFO, "Creando un usuario nuevo");
        /* Note que hacemos uso de un método propio de EntityManager para persistir la author en la base de datos.
        Es similar a "INSERT INTO table_name (column1, column2, column3, ...) VALUES (value1, value2, value3, ...);" en SQL.
         */
        em.persist(usuarioEntity);
        LOGGER.log(Level.INFO, "Autor creado");
        return usuarioEntity;
    }

    /**
     * Devuelve todas las usuarios de la base de datos.
     *
     * @return una lista con todas las usuarios que encuentre en la base de
     * datos, "select u from UsuarioEntity u" es como un "select * from
     * UsuarioEntity;" - "SELECT * FROM table_name" en SQL.
     */
    public List<UsuarioEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todos los usuarios");
        // Se crea un query para buscar todas las usuarios en la base de datos.
        TypedQuery query = em.createQuery("select u from UsuarioEntity u", UsuarioEntity.class);
        // Note que en el query se hace uso del método getResultList() que obtiene una lista de usuarios.
        return query.getResultList();
    }

    /**
     * Busca si hay alguna author con el id que se envía de argumento
     *
     * @param authorsId: id correspondiente a la author buscada.
     * @return un author.
     */
    public UsuarioEntity find(Long authorsId) {
        LOGGER.log(Level.INFO, "Consultando el usuario con id={0}", authorsId);
        /* Note que se hace uso del metodo "find" propio del EntityManager, el cual recibe como argumento 
        el tipo de la clase y el objeto que nos hara el filtro en la base de datos en este caso el "id"
        Suponga que es algo similar a "select * from UsuarioEntity where id=id;" - "SELECT * FROM table_name WHERE condition;" en SQL.
         */
        return em.find(UsuarioEntity.class, authorsId);
    }

    /**
     * Actualiza una author.
     *
     * @param usuarioEntity: la author que viene con los nuevos cambios. Por
     * ejemplo el nombre pudo cambiar. En ese caso, se haria uso del método
     * update.
     * @return una author con los cambios aplicados.
     */
    public UsuarioEntity update(UsuarioEntity usuarioEntity) {
        LOGGER.log(Level.INFO, "Actualizando el author con id={0}", usuarioEntity.getId());
        /* Note que hacemos uso de un método propio del EntityManager llamado merge() que recibe como argumento
        la author con los cambios, esto es similar a 
        "UPDATE table_name SET column1 = value1, column2 = value2, ... WHERE condition;" en SQL.
         */
        return em.merge(usuarioEntity);
    }

    /**
     * Borra una author de la base de datos recibiendo como argumento el id de
     * la author
     *
     * @param authorsId: id correspondiente a la author a borrar.
     */
    public void delete(Long authorsId) {

        LOGGER.log(Level.INFO, "Borrando el author con id={0}", authorsId);
        // Se hace uso de mismo método que esta explicado en public UsuarioEntity find(Long id) para obtener la author a borrar.
        UsuarioEntity usuarioEntity = em.find(UsuarioEntity.class, authorsId);
        /* Note que una vez obtenido el objeto desde la base de datos llamado "entity", volvemos hacer uso de un método propio del
        EntityManager para eliminar de la base de datos el objeto que encontramos y queremos borrar.
        Es similar a "delete from UsuarioEntity where id=id;" - "DELETE FROM table_name WHERE condition;" en SQL.*/
        em.remove(usuarioEntity);
    }
    /**
     * Busca si hay alguna editorial con el nombre que se envía de argumento
     *
     * @param nombre: Nombre de la editorial que se está buscando
     * @return null si no existe ninguna editorial con el nombre del argumento.
     * Si existe alguna devuelve la primera.
     */
    public UsuarioEntity findByName(String nombre) {
        LOGGER.log(Level.INFO, "Consultando editorial por nombre ", nombre);
        // Se crea un query para buscar editoriales con el nombre que recibe el método como argumento. ":nombre" es un placeholder que debe ser remplazado
        TypedQuery query = em.createQuery("Select e From UsuarioEntity e where e.nombre = :nombre", UsuarioEntity.class);
        // Se remplaza el placeholder ":nombre" con el valor del argumento 
        query = query.setParameter("nombre", nombre);
        // Se invoca el query se obtiene la lista resultado
        List<UsuarioEntity> sameName = query.getResultList();
        UsuarioEntity result;
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

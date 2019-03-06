/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.persistence;

import co.edu.uniandes.csw.idiomas.entities.ChatEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Clase que maneja la persistencia para Chat. Se conecta a través Entity
 * Manager de javax.persistance con la base de datos SQL.
 *
 * @author g.cubillosb
 */
@Stateless
public class ChatPersistence {

    // ----------------------------------------------------------------------
    // Atributos 
    // ----------------------------------------------------------------------
    /**
     * Logger para las acciones de la clase.
     */
    private static final Logger LOGGER = Logger.getLogger(ChatPersistence.class.getName());

    /**
     * Entity manager para la clase.
     */
    @PersistenceContext(unitName = "idiomasPU")
    protected EntityManager em;

    // ----------------------------------------------------------------------
    // Métodos
    // ----------------------------------------------------------------------
    /**
     * Método para persistir la entidad en la base de datos.
     *
     * @param pChatEntity Objeto chat que se creará en la base de datos.
     * @return Devuelve la chat creada con un id dado por la base de datos.
     */
    public ChatEntity create(ChatEntity pChatEntity) {
        LOGGER.log(Level.INFO, "Creando una chat nueva");
        em.persist(pChatEntity);
        LOGGER.log(Level.INFO, "Saliendo de crear una chat nueva");
        return pChatEntity;
    }

    /**
     * Devuelve todas las chats de la base de datos.
     *
     * @return una lista con todas las chats que encuentre en la base de datos,
     * "select u from ChatEntity u" es como un "select * from ChatEntity;" -
     * "SELECT * FROM table_name" en SQL.
     */
    public List<ChatEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todas las chats");
        // Se crea un query para buscar todas las chats en la base de datos.
        TypedQuery query = em.createQuery("select u from ChatEntity u", ChatEntity.class);
        // Se hace uso del método getResultList() que obtiene una lista de chats.
        return query.getResultList();
    }

    /**
     * Busca si hay alguna chat con el id que se envía de argumento
     *
     * @param pChatId: id correspondiente a la chat buscada.
     * @return una chat.
     */
    public ChatEntity find(Long pChatId) {
        LOGGER.log(Level.INFO, "Consultando editorial con id={0}", pChatId);
        /* Note que se hace uso del metodo "find" propio del EntityManager, el cual recibe como argumento 
        el tipo de la clase y el objeto que nos hara el filtro en la base de datos en este caso el "id"
        Suponga que es algo similar a "select * from ChatEntity where id=id;" - "SELECT * FROM table_name WHERE condition;" en SQL.
         */
        return em.find(ChatEntity.class, pChatId);
    }

    /**
     * Actualiza una chat.
     *
     * @param pChatEntity: la chat que viene con los nuevos cambios. Por ejemplo
     * el nombre pudo cambiar. En ese caso, se haria uso del método update.
     * @return una chat con los cambios aplicados.
     */
    public ChatEntity update(ChatEntity pChatEntity) {
        LOGGER.log(Level.INFO, "Actualizando editorial con id = {0}", pChatEntity.getId());
        /* Note que hacemos uso de un método propio del EntityManager llamado merge() que recibe como argumento
        la editorial con los cambios, esto es similar a 
        "UPDATE table_name SET column1 = value1, column2 = value2, ... WHERE condition;" en SQL.
         */
        LOGGER.log(Level.INFO, "Saliendo de actualizar la editorial con id = {0}", pChatEntity.getId());
        return em.merge(pChatEntity);
    }

    /**
     *
     * Borra una chat de la base de datos recibiendo como argumento el id de la
     * chat.
     *
     * @param pChatId: id correspondiente a la chat a borrar.
     */
    public void delete(Long pChatId) {
        LOGGER.log(Level.INFO, "Borrando chat con id = {0}", pChatId);
        // Se hace uso de mismo método que esta explicado en public ChatEntity 
        // find(Long id) para obtener la editorial a borrar.
        ChatEntity entity = em.find(ChatEntity.class, pChatId);
        /* Note que una vez obtenido el objeto desde la base de datos llamado 
         "entity", volvemos hacer uso de un método propio del
         EntityManager para eliminar de la base de datos el objeto que 
         encontramos y queremos borrar.
         Es similar a "delete from EditorialEntity where id=id;" - "DELETE FROM 
         table_name WHERE condition;" en SQL.*/
        em.remove(entity);
        LOGGER.log(Level.INFO, "Saliendo de borrar la chat con id = {0}", pChatId);
    }

    /**
     * Busca si hay alguna chat con el nombre que se envía de argumento.
     *
     * @param pName: Nombre de la chat que se está buscando
     * @return null si no existe ninguna chat con el nombre del argumento. Si
     * existe alguna devuelve la primera.
     */
    public ChatEntity findByName(String pName) {
        LOGGER.log(Level.INFO, "Consultando chat por nombre = {0}", pName);
        // Se crea un query para buscar editoriales con el nombre que recibe el método como argumento. ":name" es un placeholder que debe ser remplazado
        TypedQuery query = em.createQuery("Select e From ChatEntity e where e.nombre = :nombre", ChatEntity.class);
        // Se remplaza el placeholder ":name" con el valor del argumento 
        query = query.setParameter("nombre", pName);
        // Se invoca el query se obtiene la lista resultado
        List<ChatEntity> sameName = query.getResultList();
        ChatEntity result;
        if (sameName == null) {
            result = null;
        } else if (sameName.isEmpty()) {
            result = null;
        } else {
            result = sameName.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar chat por nombre = {0}", pName);
        return result;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.ejb;

import co.edu.uniandes.csw.idiomas.entities.ChatEntity;
import co.edu.uniandes.csw.idiomas.entities.ComentarioActividadEntity;
import co.edu.uniandes.csw.idiomas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.idiomas.persistence.ChatPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Clase que implementa la conexion con la persistencia para la entidad de
 * Chat.
 *
 * @author g.cubillosb
 */
@Stateless
public class ChatLogic {
    
    // -------------------------------------------------------------------------
    // Atributos
    // -------------------------------------------------------------------------
    
    /**
     * Logger para las acciones de la clase.
     */
    private static final Logger LOGGER = Logger.getLogger(ChatLogic.class.getName());

    /**
     * Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.
     */
    @Inject
    private ChatPersistence persistence; 

    // -------------------------------------------------------------------------
    // Métodos
    // -------------------------------------------------------------------------
    
    /**
     * Crea una chat en la persistencia.
     *
     * @param chatEntity La entidad que representa la chat a
     * persistir.
     * @return La entiddad de la chat luego de persistirla.
     * @throws BusinessLogicException Si la chat a persistir ya existe.
     */
    public ChatEntity createChat(ChatEntity chatEntity) throws BusinessLogicException 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de creación de la chat");
        // Verifica la regla de negocio que dice que no puede haber dos chats con el mismo nombre
        if (persistence.findByName(chatEntity.getNombre()) != null) {
            throw new BusinessLogicException("Ya existe una Chat con el nombre \"" + chatEntity.getNombre()+ "\"");
        }
        // Invoca la persistencia para crear la chat
        persistence.create(chatEntity);
        LOGGER.log(Level.INFO, "Termina proceso de creación de la chat");
        return chatEntity;
    }

    /**
     *
     * Obtener todas las chats existentes en la base de datos.
     *
     * @return una lista de chats.
     */
    public List<ChatEntity> getChats() 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todas las chats");
        // Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
        List<ChatEntity> chats = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todas las chats");
        return chats;
    }

    /**
     *
     * Obtener una chat por medio de su id.
     *
     * @param chatsId: id de la chat para ser buscada.
     * @return la chat solicitada por medio de su id.
     */
    public ChatEntity getChat(Long chatsId) 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la chat con id = {0}", chatsId);
        // Note que, por medio de la inyección de dependencias se llama al método "find(id)" que se encuentra en la persistencia.
        ChatEntity chatEntity = persistence.find(chatsId);
        if (chatEntity == null) {
            LOGGER.log(Level.SEVERE, "La chat con el id = {0} no existe", chatsId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar la chat con id = {0}", chatsId);
        return chatEntity;
    }

    /**
     *
     * Actualizar una chat.
     *
     * @param pChatsId: id de la chat para buscarla en la base de
     * datos.
     * @param chatEntity: chat con los cambios para ser actualizada,
     * por ejemplo el nombre.
     * @return la chat con los cambios actualizados en la base de datos.
     */
    public ChatEntity updateChat(Long  pChatsId, ChatEntity chatEntity) 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar la chat con id = {0}",  pChatsId);
        // Note que, por medio de la inyección de dependencias se llama al método "update(entity)" que se encuentra en la persistencia.
        ChatEntity newEntity = persistence.update(chatEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar la chat con id = {0}", chatEntity.getId());
        return newEntity;
    }

    /**
     * Borrar un chat
     *
     * @param pChatsId: id de la chat a borrar
     * @throws BusinessLogicException Si la chat a eliminar tiene libros.
     */
    public void deleteChat(Long pChatsId) throws BusinessLogicException 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar la chat con id = {0}", pChatsId);
        // Note que, por medio de la inyección de dependencias se llama al método "delete(id)" que se encuentra en la persistencia.
        List<ComentarioActividadEntity> comentarios = getChat(pChatsId).getComentarios();
        if (comentarios != null && !comentarios.isEmpty()) {
            throw new BusinessLogicException("No se puede borrar la chat con id = " + pChatsId + " porque tiene comentarios asociados");
        }
        persistence.delete(pChatsId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar la chat con id = {0}", pChatsId);
    }
    
}

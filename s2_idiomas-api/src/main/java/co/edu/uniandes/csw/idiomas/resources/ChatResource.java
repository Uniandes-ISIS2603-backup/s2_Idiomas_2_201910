/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.resources;

import co.edu.uniandes.csw.idiomas.dtos.ChatDTO;
import co.edu.uniandes.csw.idiomas.ejb.ChatLogic;
import co.edu.uniandes.csw.idiomas.entities.ChatEntity;
import co.edu.uniandes.csw.idiomas.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Clase que define los servicios de la clase chat
 * @author g.cubillosb
 */
@Path("chat")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class ChatResource {
    
    // ------------------------------------------------------------------------
    // Atributos
    // ------------------------------------------------------------------------
    
    /**
     * Atributo que representa el logger correspondiente de la clase. Para poder
     * enviar mensajes.
     */
    private static final Logger LOGGER = Logger.getLogger(ChatResource.class.getName());
    
    /**
     * Permite acceder a la lógica de la aplicación. Es una inyección de dependencias.
     */
    @Inject
    private ChatLogic chatLogic; 
    
    // ------------------------------------------------------------------------
    // Constructor
    // ------------------------------------------------------------------------
    
    // ------------------------------------------------------------------------
    // Métodos
    // ------------------------------------------------------------------------
    
    /**
     * Crea una nueva chat con la informacion que se recibe en el cuerpo de
     * la petición y se regresa un objeto identico con un id auto-generado por
     * la base de datos.
     *
     * @param chat {@link ChatDTO} - La chat que se desea
     * guardar.
     * @return JSON {@link ChatDTO} - La chat guardada con el atributo
     * id autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe la chat.
     */
    @POST
    public ChatDTO createChat(ChatDTO chat) throws BusinessLogicException 
    {
        LOGGER.log(Level.INFO, "ChatResource createChat: input: {0}", chat);
        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        ChatEntity chatEntity = chat.toEntity();
        // Invoca la lógica para crear la chat nueva
        ChatEntity nuevoChatEntity = chatLogic.createChat(chatEntity);
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        ChatDTO nuevoChatDTO = new ChatDTO(nuevoChatEntity);
        LOGGER.log(Level.INFO, "ChatResource createChat: output: {0}", nuevoChatDTO);
        return nuevoChatDTO;
    }

    /**
     * Busca y devuelve todas las chats que existen en la aplicacion.
     *
     * @return JSONArray {@link ChatDetailDTO} - Las chats
     * encontradas en la aplicación. Si no hay ninguna retorna una lista vacía.
     */
    @GET
    public List<ChatDTO> getChats() {
        LOGGER.info("ChatResource getChats: input: void");
        List<ChatDTO> listaChats = listEntity2DetailDTO(chatLogic.getChats());
        LOGGER.log(Level.INFO, "ChatResource getChats: output: {0}", listaChats);
        return listaChats;
    }

    /**
     * Busca la chat con el id asociado recibido en la URL y la devuelve.
     *
     * @param chatsId Identificador de la chat que se esta buscando.
     * Este debe ser una cadena de dígitos.
     * @return JSON {@link ChatDetailDTO} - La chat buscada
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la chat.
     */
    @GET
    @Path("{chatsId: \\d+}")
    public ChatDTO getChat(@PathParam("chatsId") Long chatsId) throws WebApplicationException {
        LOGGER.log(Level.INFO, "ChatResource getChat: input: {0}", chatsId);
        ChatEntity chatEntity = chatLogic.getChat(chatsId);
        if (chatEntity == null) {
            throw new WebApplicationException("El recurso /chats/" + chatsId + " no existe.", 404);
        }
        ChatDTO detailDTO = new ChatDTO(chatEntity);
        LOGGER.log(Level.INFO, "ChatResource getChat: output: {0}", detailDTO);
        return detailDTO;
    }

    /**
     * Actualiza la chat con el id recibido en la URL con la informacion
     * que se recibe en el cuerpo de la petición.
     *
     * @param chatsId Identificador de la chat que se desea
     * actualizar. Este debe ser una cadena de dígitos.
     * @param chat {@link ChatDetailDTO} La chat que se desea
     * guardar.
     * @return JSON {@link ChatDetailDTO} - La chat guardada.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la chat a
     * actualizar.
     */
    @PUT
    @Path("{chatsId: \\d+}")
    public ChatDTO updateChat(@PathParam("chatsId") Long chatsId, ChatDTO chat) throws BusinessLogicException 
    {
        LOGGER.log(Level.INFO, "ChatResource updateChat: input: id:{0} , chat: {1}", new Object[]{chatsId, chat});
        chat.setId(chatsId);
        if (chatLogic.getChat(chatsId) == null) {
            throw new WebApplicationException("El recurso /chats/" + chatsId + " no existe.", 404);
        }
        ChatDTO detailDTO = new ChatDTO(chatLogic.updateChat(chatsId, chat.toEntity()));
        LOGGER.log(Level.INFO, "ChatResource updateChat: output: {0}", detailDTO);
        return detailDTO;

    }

    /**
     * Borra la chat con el id asociado recibido en la URL.
     *
     * @param chatsId Identificador de la chat que se desea borrar.
     * Este debe ser una cadena de dígitos.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se puede eliminar la chat.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la chat.
     */
    @DELETE
    @Path("{chatsId: \\d+}")
    public void deleteChat(@PathParam("chatsId") Long chatsId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "ChatResource deleteChat: input: {0}", chatsId);
        if (chatLogic.getChat(chatsId) == null) {
            throw new WebApplicationException("El recurso /chats/" + chatsId + " no existe.", 404);
        }
        chatLogic.deleteChat(chatsId);
        LOGGER.info("ChatResource deleteChat: output: void");
    }

    // TODO: GC Conectar Chats con Comentarios y las otras clases.
//    /**
//     * Conexión con el servicio de libros para una chat.
//     * {@link ChatBooksResource}
//     *
//     * Este método conecta la ruta de /chats con las rutas de /books que
//     * dependen de la chat, es una redirección al servicio que maneja el
//     * segmento de la URL que se encarga de los libros de una chat.
//     *
//     * @param chatsId El ID de la chat con respecto a la cual se
//     * accede al servicio.
//     * @return El servicio de libros para esta chat en paricular.
//     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
//     * Error de lógica que se genera cuando no se encuentra la chat.
//     */
//    @Path("{chatsId: \\d+}/books")
//    public Class<ChatBooksResource> getChatBooksResource(@PathParam("chatsId") Long chatsId) {
//        if (chatLogic.getChat(chatsId) == null) {
//            throw new WebApplicationException("El recurso /chats/" + chatsId + " no existe.", 404);
//        }
//        return ChatBooksResource.class;
//    }

    /**
     * Convierte una lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos ChatEntity a una lista de
     * objetos ChatDetailDTO (json)
     *
     * @param entityList corresponde a la lista de chats de tipo Entity
     * que vamos a convertir a DTO.
     * @return la lista de chats en forma DTO (json)
     */
    private List<ChatDTO> listEntity2DetailDTO(List<ChatEntity> entityList) {
        List<ChatDTO> list = new ArrayList<>();
        for (ChatEntity entity : entityList) {
            list.add(new ChatDTO(entity));
        }
        return list;
    }
    
    
    
}

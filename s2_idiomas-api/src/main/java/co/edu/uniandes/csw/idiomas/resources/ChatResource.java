/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.resources;

import co.edu.uniandes.csw.idiomas.dtos.ChatDTO;
import co.edu.uniandes.csw.idiomas.exceptions.BusinessLogicException;
import java.util.logging.*;
import javax.enterprise.context.RequestScoped;
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
    
    // -----------------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------------
    
    /**
     * Atributo que representa el logger correspondiente de la clase. Se utiliza
     * para enviar mensajes.
     */
    private static final Logger LOGGER = Logger.getLogger(ChatResource.class.getName());
    
    // -----------------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------------
    
    // -----------------------------------------------------------------------
    // Métodos 
    // -----------------------------------------------------------------------
    
    /**
     * Crea un nuevo chat con la información que se recibe en el cuerpo de la
     * petición y se regresa un objeto idéntico con un id autogenerado por la 
     * base de datos
     * 
     * @param chat {@link ChatDTO} - El chat que se desea crear y guardar.
     * @return JSON {@link ChatDTO} - El chat que se guarda con el atributo id
     * autogenerado
     */
    @POST
    public ChatDTO createChat(ChatDTO chat)
    {
        // TODO Implementar lógica
        LOGGER.log(Level.INFO, "ChatResource createChat: input: {0}", chat);
//        ActividadDTO actividadDTO = new ActividadDTO(authorLogic.createAuthor(actividad.toEntity()));
//        LOGGER.log(Level.INFO, "ActividadResource createActividad: output: {0}", actividadDTO);
//        return actividadDTO;
        return chat;
    }
    
    /**
     * Actualiza un chat con el id recibido en la URL con la información que 
     * se recibe en el cuerpo de la petición.
     * 
     * @param chatId Identificador del chat que se desea actualizar. Debe ser 
     * una cadena de dígitos.
     * @param chat {@link ChatDTO} - El chat con la información que se desea
     * guardar. 
     * @return JSON {@link ChatDTO} - El chat guardado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el chat para 
     * actualizar.
     */
    @PUT
    @Path("{chatId: \\d+}")
    public ChatDTO updateChat (@PathParam("chatId") Long chatId, ChatDTO chat)
    {
        // TODO: Implementar lógica PUT
        LOGGER.log(Level.INFO, "ChatResource updateChat: input: chatId: {0}, chat: {1}",
                new Object[]{chatId, chat});
//        author.setId(authorsId);
//        if (authorLogic.getAuthor(authorsId) == null) {
//            throw new WebApplicationException("El recurso /authors/" + authorsId + " no existe.", 404);
//        }
//        AuthorDetailDTO detailDTO = new AuthorDetailDTO(authorLogic.updateAuthor(authorsId, author.toEntity()));
//        LOGGER.log(Level.INFO, "ChatResource updateChat: output: {0}", detailDTO);
        return chat;
    }
    
    /**
     * Busca el chat con el id asociado y lo devuelve.
     * 
     * @param chatId El identificador del chat que se busca. Debe ser una cadena
     * de dígitos.
     * @return JSON {@link ChatDTO} - El chat buscado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica cuando no se encuentra el chat.
     */
    @GET
    @Path("{chatId: \\d+}")
    public ChatDTO getChat (@PathParam("chatId") Long chatId)
    {
        LOGGER.log(Level.INFO, "ChatResource getChat: input: chatId: {0}", chatId);
        // TODO: Implementar lógica GET
//        AuthorEntity authorEntity = authorLogic.getAuthor(authorsId);
//        if (authorEntity == null) {
//            throw new WebApplicationException("El recurso /authors/" + authorsId + " no existe.", 404);
//        }
//        AuthorDetailDTO detailDTO = new AuthorDetailDTO(authorEntity);
//        LOGGER.log(Level.INFO, "AuthorResource getAuthor: output: {0}", detailDTO);
        return new ChatDTO();
    }
    
    /**
     * Elimina el chat con el id asociado recibido en la URL.
     * 
     * @param chatId Identificador del autor que se desea borrar. Debe ser una
     * cadena de dígitos.
     * @throws BusinessLogicException - Si el chat tiene usuarios asociados.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que ocurre cuando no se encuentra el chat a eliminar.
     */
    @DELETE
    @Path("{chatId: \\d+}")
    public void deleteChat(@PathParam("chatId") Long chatId) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "ChatResource deleteChat: input: chatId: {0}", chatId);
        // TODO: Implementar lógica DELETE
//        if (authorLogic.getAuthor(authorsId) == null) {
//            throw new WebApplicationException("El recurso /authors/" + authorsId + " no existe.", 404);
//        }
//        authorLogic.deleteAuthor(authorsId);
        LOGGER.info("ChatResource deleteChat: output: void");
        
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.resources;



import co.edu.uniandes.csw.idiomas.dtos.UsuarioDTO;
import co.edu.uniandes.csw.idiomas.dtos.UsuarioDetailDTO;
import co.edu.uniandes.csw.idiomas.ejb.UsuarioLogic;
import co.edu.uniandes.csw.idiomas.entities.UsuarioEntity;
import co.edu.uniandes.csw.idiomas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.idiomas.mappers.BusinessLogicExceptionMapper;
import co.edu.uniandes.csw.idiomas.mappers.WebApplicationExceptionMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 * Clase que implementa el recurso "usuarios".
 *
 * @author ISIS2603
 * @version 1.0
 */
@Path("usuarios")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class UsuarioResource
{
    private static final Logger LOGGER = Logger.getLogger(UsuarioResource.class.getName());
    private static final String NO_EXISTE =" no existe." ;

    @Inject
    UsuarioLogic usuarioLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    /**
     * Crea una nueva usuario con la informacion que se recibe en el cuerpo de
     * la petición y se regresa un objeto identico con un id auto-generado por
     * la base de datos.
     *
     * @param usuario {@link UsuarioDTO} - La usuario que se desea
     * guardar.
     * @return JSON {@link UsuarioDTO} - La usuario guardada con el atributo
     * id autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe la usuario.
     */
    @POST
    public UsuarioDTO createUsuario(UsuarioDTO usuario) throws BusinessLogicException {
       LOGGER.log(Level.INFO, "UsuarioResource createUsuario: input: {0}", usuario);
        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        UsuarioEntity usuarioEntity = usuario.toEntity();
        // Invoca la lógica para crear la usuario nueva
        usuarioLogic.createUsuario(usuarioEntity);
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
         System.out.println("en el resourse el entity toString : "+usuarioEntity.toString());
         LOGGER.log(Level.INFO, "entity: output: {0}", usuarioEntity);
         System.out.println("en el resourse el entity tiene : "+usuarioEntity.getId());
        UsuarioDTO nuevoUsuarioDTO = new UsuarioDTO(usuarioEntity);
        LOGGER.log(Level.INFO, "UsuarioResource createUsuario: output: {0}", nuevoUsuarioDTO);
        return nuevoUsuarioDTO;
    }

    /**
     * Borra la usuario con el id asociado recibido en la URL.
     *
     * @param usuarioesId Identificador de la usuario que se desea borrar.
     * Este debe ser una cadena de dígitos.
     * @return 
     */
    @DELETE
    @Path("{usuarioesId: \\d+}")
    public void deleteUsuario(@PathParam("usuarioesId") Long usuarioesId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "UsuarioResource deleteUsuario: input: {0}", usuarioesId);
        if (usuarioLogic.getUsuario(usuarioesId) == null) {
            throw new WebApplicationException("El recurso /usuarioes/" + usuarioesId +NO_EXISTE, 404);
        }
        usuarioLogic.deleteUsuario(usuarioesId);
        LOGGER.info("UsuarioResource deleteUsuario: output: void");
    }
    
   /**
     * Actualiza el autor con el id recibido en la URL con la información que se
     * recibe en el cuerpo de la petición.
     *
     * @param UsuarioId Identificador del autor que se desea actualizar. Este
     * debe ser una cadena de dígitos.
     * @param Usuario {@link UsuarioDTO} El autor que se desea guardar.
     * @return JSON {@link UsuarioDTO} - El autor guardado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el autor a
     * actualizar.
     */
    @PUT
    @Path("{UsuarioId: \\d+}")
    public UsuarioDTO updateUsuario(@PathParam("UsuarioId") Long usuarioId, UsuarioDTO usuario) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "UsuarioResource updateUsuario: input: UsuarioId: {0} , Usuario: {1}", new Object[]{usuarioId, usuario});
        usuario.setId(usuarioId);
        if (usuarioLogic.getUsuario(usuarioId) == null)
        {
            throw new WebApplicationException("El recurso /Usuarioes/" + usuarioId +NO_EXISTE, 404);
        }
        UsuarioDTO detailDTO = new UsuarioDTO(usuarioLogic.updateUsuario(usuarioId, usuario.toEntity()));
        LOGGER.log(Level.INFO, "UsuarioResource updateUsuario: output: {0}", detailDTO);
        return detailDTO;
    }
    
     /**
     * Actualiza la usuario con el id asociado recibido en la URL.
     *
     * @param usuarioesId Identificador de la usuario que se desea actualizar.
     * Este debe ser una cadena de dígitos.
     * @return 
     */
    @GET
    @Path("{usuarioesId: \\d+}")
    public UsuarioDetailDTO retornarUsuario(@PathParam("usuarioesId") Long usuarioesId) {
        LOGGER.log(Level.INFO, "UsuarioResource getUsuario: input: {0}", usuarioesId);
        UsuarioEntity usuarioEntity = usuarioLogic.getUsuario(usuarioesId);
        if (usuarioEntity == null) {
            throw new WebApplicationException("El recurso /usuarioes/" + usuarioesId +NO_EXISTE, 404);
        }
        UsuarioDetailDTO detailDTO = new UsuarioDetailDTO(usuarioEntity);
        LOGGER.log(Level.INFO, "UsuarioResource getUsuario: output: {0}", detailDTO);
        return detailDTO;
    }
    
      /**
     * Actualiza la usuario con el id asociado recibido en la URL.
     *
     * @param usuarioesId Identificador de la usuario que se desea actualizar.
     * Este debe ser una cadena de dígitos.
     * @return 
     */
    @GET    
    public List<UsuarioDTO> retornarUsuario() {
        LOGGER.info("UsuarioResource getUsuarios: input: void");
        List<UsuarioDTO> listaUsuarios = listEntity2DTO(usuarioLogic.getUsuarios());
        LOGGER.log(Level.INFO, "UsuarioResource getUsuarios: output: {0}", listaUsuarios);
        return listaUsuarios;
               
    }   
      /**
     * Convierte una lista de UsuarioEntity a una lista de UsuarioDTO.
     *
     * @param entityList Lista de UsuarioEntity a convertir.
     * @return Lista de UsuarioDTO convertida.
     */
    private List<UsuarioDTO> listEntity2DTO(List<UsuarioEntity> entityList) {
        List<UsuarioDTO> list = new ArrayList<>();
        for (UsuarioEntity entity : entityList) {
            list.add(new UsuarioDTO(entity));
        }
        return list;
    }
}

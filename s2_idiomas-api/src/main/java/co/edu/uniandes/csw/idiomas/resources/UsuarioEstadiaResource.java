/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.resources;
import co.edu.uniandes.csw.idiomas.dtos.EstadiaDTO;
import co.edu.uniandes.csw.idiomas.ejb.UsuarioEstadiaLogic;
import co.edu.uniandes.csw.idiomas.ejb.EstadiaLogic;
import co.edu.uniandes.csw.idiomas.entities.EstadiaEntity;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.WebApplicationException;

/**
 * Clase que implementa el recurso "actividades/{id}/comentariosUsuario".
 *
 * @author g.cubillosb
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsuarioEstadiaResource {
    
    // ------------------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------------

    /**
     * Logger para las acciones de la clase.
     */
    private static final Logger LOGGER = Logger.getLogger(UsuarioEstadiaResource.class.getName());

    /**
     * Variable para acceder a la persistencia de la aplicación. Es una
     * inyección de dependencias.
     */
    @Inject
    private UsuarioEstadiaLogic usuarioEstadiaLogic;

    /**
     * Variable para acceder a la persistencia de la aplicación. Es una
     * inyección de dependencias.
     */
    @Inject
    private EstadiaLogic EstadiaLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    // ------------------------------------------------------------------------
    // Métodos
    // ------------------------------------------------------------------------
    
    /**
     * Asocia un libro existente con un autor existente
     *
     * @param actividadesId El ID del autor al cual se le va a asociar el libro
     * @param comentariosUsuarioId El ID del libro que se asocia
     * @return JSON {@link EstadiaDTO} - El libro asociado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro.
     */
    @POST
    @Path("{comentariosUsuarioId: \\d+}")
    public EstadiaDTO addEstadia(@PathParam("actividadesId") Long actividadesId, @PathParam("comentariosUsuarioId") Long comentariosUsuarioId) {
        LOGGER.log(Level.INFO, "UsuarioEstadiaResource addEstadia: input: actividadesId {0} , comentariosUsuarioId {1}", new Object[]{actividadesId, comentariosUsuarioId});
        if (EstadiaLogic.getEstadia(comentariosUsuarioId) == null) {
            throw new WebApplicationException("El recurso /comentariosUsuario/" + comentariosUsuarioId + " no existe.", 404);
        }
        EstadiaDTO detailDTO = new EstadiaDTO(usuarioEstadiaLogic.addEstadia(actividadesId, comentariosUsuarioId));
        LOGGER.log(Level.INFO, "UsuarioEstadiaResource addEstadia: output: {0}", detailDTO);
        return detailDTO;
    }

    /**
     * Busca y devuelve todos los libros que existen en un autor.
     *
     * @param actividadesId El ID del autor del cual se buscan los libros
     * @return JSONArray {@link EstadiaDTO} - Los libros encontrados en el
     * autor. Si no hay ninguno retorna una lista vacía.
     */
//    @GET
//    public List<EstadiaDTO> getEstadia(@PathParam("actividadesId") Long actividadesId) {
//        LOGGER.log(Level.INFO, "UsuarioEstadiaResource getEstadia: input: {0}", actividadesId);
//        List<EstadiaDTO> lista = comentariosUsuarioListEntity2DTO(usuarioEstadiaLogic.getEstadia(actividadesId));
//        LOGGER.log(Level.INFO, "UsuarioEstadiaResource getEstadia: output: {0}", lista);
//        return lista;
//    }

    /**
     * Busca y devuelve el libro con el ID recibido en la URL, relativo a un
     * autor.
     *
     * @param actividadesId El ID del autor del cual se busca el libro
     * @param comentariosUsuarioId El ID del libro que se busca
     * @return {@link EstadiaDTO} - El libro encontrado en el autor.
     * @throws co.edu.uniandes.csw.comentariosUsuariotore.exceptions.BusinessLogicException
     * si el libro no está asociado al autor
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro.
     */
//    @GET
//    @Path("{comentariosUsuarioId: \\d+}")
//    public EstadiaDTO getEstadia(@PathParam("actividadesId") Long actividadesId, @PathParam("comentariosUsuarioId") Long comentariosUsuarioId) throws BusinessLogicException {
//        LOGGER.log(Level.INFO, "UsuarioEstadiaResource getEstadia: input: actividadesId {0} , comentariosUsuarioId {1}", new Object[]{actividadesId, comentariosUsuarioId});
//        if (EstadiaLogic.getEstadia(comentariosUsuarioId) == null) {
//            throw new WebApplicationException("El recurso /comentariosUsuario/" + comentariosUsuarioId + " no existe.", 404);
//        }
//        EstadiaDTO detailDTO = new EstadiaDTO(usuarioEstadiaLogic.getEstadia(actividadesId, comentariosUsuarioId));
//        LOGGER.log(Level.INFO, "UsuarioEstadiaResource getEstadia: output: {0}", detailDTO);
//        return detailDTO;
//    }

    /**
     * Actualiza la lista de libros de un autor con la lista que se recibe en el
     * cuerpo
     *
     * @param actividadesId El ID del autor al cual se le va a asociar el libro
     * @param comentariosUsuario JSONArray {@link EstadiaDTO} - La lista de libros que se
     * desea guardar.
     * @return JSONArray {@link EstadiaDTO} - La lista actualizada.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro.
     */
//    @PUT
//    public List<EstadiaDTO> replaceEstadia(@PathParam("actividadesId") Long actividadesId, List<EstadiaDTO> comentariosUsuario) {
//        LOGGER.log(Level.INFO, "UsuarioEstadiaResource replaceEstadia: input: actividadesId {0} , comentariosUsuario {1}", new Object[]{actividadesId, comentariosUsuario});
//        for (EstadiaDTO Estadia : comentariosUsuario) {
//            if (EstadiaLogic.getEstadia(Estadia.getId()) == null) {
//                throw new WebApplicationException("El recurso /comentariosUsuario/" + Estadia.getId() + " no existe.", 404);
//            }
//        }
//        List<EstadiaDTO> lista = comentariosUsuarioListEntity2DTO(usuarioEstadiaLogic.replaceEstadia(actividadesId, comentariosUsuarioListDTO2Entity(comentariosUsuario)));
//        LOGGER.log(Level.INFO, "UsuarioEstadiaResource replaceEstadia: output: {0}", lista);
//        return lista;
//    }

    /**
     * Elimina la conexión entre el libro y e autor recibidos en la URL.
     *
     * @param actividadesId El ID del autor al cual se le va a desasociar el libro
     * @param comentariosUsuarioId El ID del libro que se desasocia
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro.
     */
    @DELETE
    @Path("{comentariosUsuarioId: \\d+}")
    public void removeEstadia(@PathParam("actividadesId") Long actividadesId, @PathParam("comentariosUsuarioId") Long comentariosUsuarioId) {
        LOGGER.log(Level.INFO, "UsuarioEstadiaResource deleteEstadia: input: actividadesId {0} , comentariosUsuarioId {1}", new Object[]{actividadesId, comentariosUsuarioId});
        if (EstadiaLogic.getEstadia(comentariosUsuarioId) == null) {
            throw new WebApplicationException("El recurso /comentariosUsuario/" + comentariosUsuarioId + " no existe.", 404);
        }
        usuarioEstadiaLogic.removeEstadia(actividadesId, comentariosUsuarioId);
        LOGGER.info("UsuarioEstadiaResource deleteEstadia: output: void");
    }

    /**
     * Convierte una lista de EstadiaEntity a una lista de EstadiaDTO.
     *
     * @param entityList Lista de EstadiaEntity a convertir.
     * @return Lista de EstadiaDTO convertida.
     */
    private List<EstadiaDTO> comentariosUsuarioListEntity2DTO(List<EstadiaEntity> entityList) {
        List<EstadiaDTO> list = new ArrayList<>();
        for (EstadiaEntity entity : entityList) {
            list.add(new EstadiaDTO(entity));
        }
        return list;
    }

    /**
     * Convierte una lista de EstadiaDTO a una lista de EstadiaEntity.
     *
     * @param dtos Lista de EstadiaDTO a convertir.
     * @return Lista de EstadiaEntity convertida.
     */
    private List<EstadiaEntity> comentariosUsuarioListDTO2Entity(List<EstadiaDTO> dtos) {
        List<EstadiaEntity> list = new ArrayList<>();
        for (EstadiaDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    }
}

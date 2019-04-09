/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.resources;
import co.edu.uniandes.csw.idiomas.dtos.ComentarioActividadDTO;
import co.edu.uniandes.csw.idiomas.ejb.ActividadComentarioActividadLogic;
import co.edu.uniandes.csw.idiomas.ejb.ComentarioActividadLogic;
import co.edu.uniandes.csw.idiomas.entities.ComentarioActividadEntity;
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
 * Clase que implementa el recurso "actividades/{id}/comentariosActividad".
 *
 * @author g.cubillosb
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ActividadComentarioActividadResource {
    
    // ------------------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------------

    /**
     * Logger para las acciones de la clase.
     */
    private static final Logger LOGGER = Logger.getLogger(ActividadComentarioActividadResource.class.getName());

    /**
     * Variable para acceder a la persistencia de la aplicación. Es una
     * inyección de dependencias.
     */
    @Inject
    private ActividadComentarioActividadLogic actividadComentarioActividadLogic;

    /**
     * Variable para acceder a la persistencia de la aplicación. Es una
     * inyección de dependencias.
     */
    @Inject
    private ComentarioActividadLogic comentarioActividadLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    // ------------------------------------------------------------------------
    // Métodos
    // ------------------------------------------------------------------------
    
    /**
     * Asocia un libro existente con un autor existente
     *
     * @param actividadesId El ID del autor al cual se le va a asociar el libro
     * @param comentariosActividadId El ID del libro que se asocia
     * @return JSON {@link ComentarioActividadDTO} - El libro asociado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro.
     */
//    @POST
//    @Path("{comentariosActividadId: \\d+}")
//    public ComentarioActividadDTO addComentarioActividad(@PathParam("actividadesId") Long actividadesId, @PathParam("comentariosActividadId") Long comentariosActividadId) {
//        LOGGER.log(Level.INFO, "ActividadComentarioActividadResource addComentarioActividad: input: actividadesId {0} , comentariosActividadId {1}", new Object[]{actividadesId, comentariosActividadId});
//        if (comentarioActividadLogic.getComentarioActividad(comentariosActividadId) == null) {
//            throw new WebApplicationException("El recurso /comentariosActividad/" + comentariosActividadId + " no existe.", 404);
//        }
//        ComentarioActividadDTO detailDTO = new ComentarioActividadDTO(actividadComentarioActividadLogic.addComentarioActividad(actividadesId, comentariosActividadId));
//        LOGGER.log(Level.INFO, "ActividadComentarioActividadResource addComentarioActividad: output: {0}", detailDTO);
//        return detailDTO;
//    }

    /**
     * Busca y devuelve todos los libros que existen en un autor.
     *
     * @param actividadesId El ID del autor del cual se buscan los libros
     * @return JSONArray {@link ComentarioActividadDTO} - Los libros encontrados en el
     * autor. Si no hay ninguno retorna una lista vacía.
     */
//    @GET
//    public List<ComentarioActividadDTO> getComentarioActividad(@PathParam("actividadesId") Long actividadesId) {
//        LOGGER.log(Level.INFO, "ActividadComentarioActividadResource getComentarioActividad: input: {0}", actividadesId);
//        List<ComentarioActividadDTO> lista = comentariosActividadListEntity2DTO(actividadComentarioActividadLogic.getComentarioActividad(actividadesId));
//        LOGGER.log(Level.INFO, "ActividadComentarioActividadResource getComentarioActividad: output: {0}", lista);
//        return lista;
//    }

    /**
     * Busca y devuelve el libro con el ID recibido en la URL, relativo a un
     * autor.
     *
     * @param actividadesId El ID del autor del cual se busca el libro
     * @param comentariosActividadId El ID del libro que se busca
     * @return {@link ComentarioActividadDTO} - El libro encontrado en el autor.
     * @throws co.edu.uniandes.csw.comentariosActividadtore.exceptions.BusinessLogicException
     * si el libro no está asociado al autor
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro.
     */
//    @GET
//    @Path("{comentariosActividadId: \\d+}")
//    public ComentarioActividadDTO getComentarioActividad(@PathParam("actividadesId") Long actividadesId, @PathParam("comentariosActividadId") Long comentariosActividadId) throws BusinessLogicException {
//        LOGGER.log(Level.INFO, "ActividadComentarioActividadResource getComentarioActividad: input: actividadesId {0} , comentariosActividadId {1}", new Object[]{actividadesId, comentariosActividadId});
//        if (comentarioActividadLogic.getComentarioActividad(comentariosActividadId) == null) {
//            throw new WebApplicationException("El recurso /comentariosActividad/" + comentariosActividadId + " no existe.", 404);
//        }
//        ComentarioActividadDTO detailDTO = new ComentarioActividadDTO(actividadComentarioActividadLogic.getComentarioActividad(actividadesId, comentariosActividadId));
//        LOGGER.log(Level.INFO, "ActividadComentarioActividadResource getComentarioActividad: output: {0}", detailDTO);
//        return detailDTO;
//    }

    /**
     * Actualiza la lista de libros de un autor con la lista que se recibe en el
     * cuerpo
     *
     * @param actividadesId El ID del autor al cual se le va a asociar el libro
     * @param comentariosActividad JSONArray {@link ComentarioActividadDTO} - La lista de libros que se
     * desea guardar.
     * @return JSONArray {@link ComentarioActividadDTO} - La lista actualizada.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro.
     */
//    @PUT
//    public List<ComentarioActividadDTO> replaceComentarioActividad(@PathParam("actividadesId") Long actividadesId, List<ComentarioActividadDTO> comentariosActividad) {
//        LOGGER.log(Level.INFO, "ActividadComentarioActividadResource replaceComentarioActividad: input: actividadesId {0} , comentariosActividad {1}", new Object[]{actividadesId, comentariosActividad});
//        for (ComentarioActividadDTO comentarioActividad : comentariosActividad) {
//            if (comentarioActividadLogic.getComentarioActividad(comentarioActividad.getId()) == null) {
//                throw new WebApplicationException("El recurso /comentariosActividad/" + comentarioActividad.getId() + " no existe.", 404);
//            }
//        }
//        List<ComentarioActividadDTO> lista = comentariosActividadListEntity2DTO(actividadComentarioActividadLogic.replaceComentarioActividad(actividadesId, comentariosActividadListDTO2Entity(comentariosActividad)));
//        LOGGER.log(Level.INFO, "ActividadComentarioActividadResource replaceComentarioActividad: output: {0}", lista);
//        return lista;
//    }

    /**
     * Elimina la conexión entre el libro y e autor recibidos en la URL.
     *
     * @param actividadesId El ID del autor al cual se le va a desasociar el libro
     * @param comentariosActividadId El ID del libro que se desasocia
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro.
     */
//    @DELETE
//    @Path("{comentariosActividadId: \\d+}")
//    public void removeComentarioActividad(@PathParam("actividadesId") Long actividadesId, @PathParam("comentariosActividadId") Long comentariosActividadId) {
//        LOGGER.log(Level.INFO, "ActividadComentarioActividadResource deleteComentarioActividad: input: actividadesId {0} , comentariosActividadId {1}", new Object[]{actividadesId, comentariosActividadId});
//        if (comentarioActividadLogic.getComentarioActividad(comentariosActividadId) == null) {
//            throw new WebApplicationException("El recurso /comentariosActividad/" + comentariosActividadId + " no existe.", 404);
//        }
//        actividadComentarioActividadLogic.removeComentarioActividad(actividadesId, comentariosActividadId);
//        LOGGER.info("ActividadComentarioActividadResource deleteComentarioActividad: output: void");
//    }

    /**
     * Convierte una lista de ComentarioActividadEntity a una lista de ComentarioActividadDTO.
     *
     * @param entityList Lista de ComentarioActividadEntity a convertir.
     * @return Lista de ComentarioActividadDTO convertida.
     */
    private List<ComentarioActividadDTO> comentariosActividadListEntity2DTO(List<ComentarioActividadEntity> entityList) {
        List<ComentarioActividadDTO> list = new ArrayList<>();
        for (ComentarioActividadEntity entity : entityList) {
            list.add(new ComentarioActividadDTO(entity));
        }
        return list;
    }

    /**
     * Convierte una lista de ComentarioActividadDTO a una lista de ComentarioActividadEntity.
     *
     * @param dtos Lista de ComentarioActividadDTO a convertir.
     * @return Lista de ComentarioActividadEntity convertida.
     */
    private List<ComentarioActividadEntity> comentariosActividadListDTO2Entity(List<ComentarioActividadDTO> dtos) {
        List<ComentarioActividadEntity> list = new ArrayList<>();
        for (ComentarioActividadDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    }
}

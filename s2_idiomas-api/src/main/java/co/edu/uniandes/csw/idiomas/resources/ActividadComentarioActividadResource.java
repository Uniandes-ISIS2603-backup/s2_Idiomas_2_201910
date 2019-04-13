/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.resources;
import co.edu.uniandes.csw.idiomas.dtos.ComentarioDTO;
import co.edu.uniandes.csw.idiomas.ejb.ActividadComentarioActividadLogic;
import co.edu.uniandes.csw.idiomas.ejb.ComentarioLogic;
import co.edu.uniandes.csw.idiomas.entities.ComentarioEntity;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.logging.Logger;

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
    private ComentarioLogic comentarioActividadLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    // ------------------------------------------------------------------------
    // Métodos
    // ------------------------------------------------------------------------
    
    /**
     * Asocia un libro existente con un autor existente
     *
     * @param actividadesId El ID del autor al cual se le va a asociar el libro
     * @param comentariosActividadId El ID del libro que se asocia
     * @return JSON {@link ComentarioDTO} - El libro asociado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro.
     */
//    @POST
//    @Path("{comentariosActividadId: \\d+}")
//    public ComentarioDTO addComentarioActividad(@PathParam("actividadesId") Long actividadesId, @PathParam("comentariosActividadId") Long comentariosActividadId) {
//        LOGGER.log(Level.INFO, "ActividadComentarioActividadResource addComentarioActividad: input: actividadesId {0} , comentariosActividadId {1}", new Object[]{actividadesId, comentariosActividadId});
//        if (comentarioActividadLogic.getComentarioActividad(comentariosActividadId) == null) {
//            throw new WebApplicationException("El recurso /comentariosActividad/" + comentariosActividadId + " no existe.", 404);
//        }
//        ComentarioDTO detailDTO = new ComentarioDTO(actividadComentarioActividadLogic.addComentarioActividad(actividadesId, comentariosActividadId));
//        LOGGER.log(Level.INFO, "ActividadComentarioActividadResource addComentarioActividad: output: {0}", detailDTO);
//        return detailDTO;
//    }

    /**
     * Busca y devuelve todos los libros que existen en un autor.
     *
     * @param actividadesId El ID del autor del cual se buscan los libros
     * @return JSONArray {@link ComentarioDTO} - Los libros encontrados en el
     * autor. Si no hay ninguno retorna una lista vacía.
     */
//    @GET
//    public List<ComentarioDTO> getComentarioActividad(@PathParam("actividadesId") Long actividadesId) {
//        LOGGER.log(Level.INFO, "ActividadComentarioActividadResource getComentarioActividad: input: {0}", actividadesId);
//        List<ComentarioDTO> lista = comentariosActividadListEntity2DTO(actividadComentarioActividadLogic.getComentarioActividad(actividadesId));
//        LOGGER.log(Level.INFO, "ActividadComentarioActividadResource getComentarioActividad: output: {0}", lista);
//        return lista;
//    }

    /**
     * Busca y devuelve el libro con el ID recibido en la URL, relativo a un
     * autor.
     *
     * @param actividadesId El ID del autor del cual se busca el libro
     * @param comentariosActividadId El ID del libro que se busca
     * @return {@link ComentarioDTO} - El libro encontrado en el autor.
     * @throws co.edu.uniandes.csw.comentariosActividadtore.exceptions.BusinessLogicException
     * si el libro no está asociado al autor
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro.
     */
//    @GET
//    @Path("{comentariosActividadId: \\d+}")
//    public ComentarioDTO getComentarioActividad(@PathParam("actividadesId") Long actividadesId, @PathParam("comentariosActividadId") Long comentariosActividadId) throws BusinessLogicException {
//        LOGGER.log(Level.INFO, "ActividadComentarioActividadResource getComentarioActividad: input: actividadesId {0} , comentariosActividadId {1}", new Object[]{actividadesId, comentariosActividadId});
//        if (comentarioActividadLogic.getComentarioActividad(comentariosActividadId) == null) {
//            throw new WebApplicationException("El recurso /comentariosActividad/" + comentariosActividadId + " no existe.", 404);
//        }
//        ComentarioDTO detailDTO = new ComentarioDTO(actividadComentarioActividadLogic.getComentarioActividad(actividadesId, comentariosActividadId));
//        LOGGER.log(Level.INFO, "ActividadComentarioActividadResource getComentarioActividad: output: {0}", detailDTO);
//        return detailDTO;
//    }

    /**
     * Actualiza la lista de libros de un autor con la lista que se recibe en el
     * cuerpo
     *
     * @param actividadesId El ID del autor al cual se le va a asociar el libro
     * @param comentariosActividad JSONArray {@link ComentarioDTO} - La lista de libros que se
     * desea guardar.
     * @return JSONArray {@link ComentarioDTO} - La lista actualizada.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro.
     */
//    @PUT
//    public List<ComentarioDTO> replaceComentarioActividad(@PathParam("actividadesId") Long actividadesId, List<ComentarioDTO> comentariosActividad) {
//        LOGGER.log(Level.INFO, "ActividadComentarioActividadResource replaceComentarioActividad: input: actividadesId {0} , comentariosActividad {1}", new Object[]{actividadesId, comentariosActividad});
//        for (ComentarioDTO comentarioActividad : comentariosActividad) {
//            if (comentarioActividadLogic.getComentarioActividad(comentarioActividad.getId()) == null) {
//                throw new WebApplicationException("El recurso /comentariosActividad/" + comentarioActividad.getId() + " no existe.", 404);
//            }
//        }
//        List<ComentarioDTO> lista = comentariosActividadListEntity2DTO(actividadComentarioActividadLogic.replaceComentarioActividad(actividadesId, comentariosActividadListDTO2Entity(comentariosActividad)));
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
     * Convierte una lista de ComentarioActividadEntity a una lista de ComentarioDTO.
     *
     * @param entityList Lista de ComentarioActividadEntity a convertir.
     * @return Lista de ComentarioDTO convertida.
     */
    private List<ComentarioDTO> comentariosActividadListEntity2DTO(List<ComentarioEntity> entityList) {
        List<ComentarioDTO> list = new ArrayList<>();
        for (ComentarioEntity entity : entityList) {
            list.add(new ComentarioDTO(entity));
        }
        return list;
    }

    /**
     * Convierte una lista de ComentarioDTO a una lista de ComentarioActividadEntity.
     *
     * @param dtos Lista de ComentarioDTO a convertir.
     * @return Lista de ComentarioActividadEntity convertida.
     */
    private List<ComentarioEntity> comentariosActividadListDTO2Entity(List<ComentarioDTO> dtos) {
        List<ComentarioEntity> list = new ArrayList<>();
        for (ComentarioDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    }
}

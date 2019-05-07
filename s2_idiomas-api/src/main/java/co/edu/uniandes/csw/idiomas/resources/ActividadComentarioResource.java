/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.resources;

import co.edu.uniandes.csw.idiomas.dtos.ComentarioDetailDTO;
import co.edu.uniandes.csw.idiomas.ejb.ActividadComentarioLogic;
import co.edu.uniandes.csw.idiomas.ejb.ComentarioLogic;
import co.edu.uniandes.csw.idiomas.entities.ComentarioEntity;
import co.edu.uniandes.csw.idiomas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.idiomas.mappers.WebApplicationExceptionMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javax.ws.rs.core.MediaType;

/**
 * Clase que implementa el recurso "actividad/{id}/comentarios".
 * 
 * @author g.cubillosb
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ActividadComentarioResource 
{
    private static final Logger LOGGER = Logger.getLogger(ActividadComentarioResource.class.getName());

    @Inject
    private ActividadComentarioLogic actividadComentarioLogic;

    @Inject
    private ComentarioLogic comentarioLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    /**
     * Asocia un comentario existente con una actividad existente
     *
     * @param actividadesId El ID del actividad al cual se le va a asociar el comentario
     * @param comentariosId El ID del comentario que se asocia
     * @return JSON {@link ComentarioDetailDTO} - El comentario asociado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el comentario.
     */
    @POST
    @Path("{comentariosId: \\d+}")
    public ComentarioDetailDTO addComentario(@PathParam("actividadesId") Long actividadesId, @PathParam("comentariosId") Long comentariosId) {
        LOGGER.log(Level.INFO, "ActividadComentarioResource addComentario: input: actividadesId {0} , comentariosId {1}", new Object[]{actividadesId, comentariosId});
        if (comentarioLogic.getComentario(comentariosId) == null) {
            throw new WebApplicationException("El recurso /comentarios/" + comentariosId + " no existe.", 404);
        }
        ComentarioDetailDTO detailDTO = new ComentarioDetailDTO(actividadComentarioLogic.addComentario(actividadesId, comentariosId));
        LOGGER.log(Level.INFO, "ActividadComentarioResource addComentario: output: {0}", detailDTO);
        return detailDTO;
    }

    /**
     * Busca y devuelve todos los comentarios que existen en un actividad.
     *
     * @param actividadesId El ID del actividad del cual se buscan los comentarios
     * @return JSONArray {@link ComentarioDetailDTO} - Los comentarios encontrados en el
     * actividad. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<ComentarioDetailDTO> getComentarios(@PathParam("actividadesId") Long actividadesId) {
        LOGGER.log(Level.INFO, "ActividadComentarioResource getComentarios: input: {0}", actividadesId);
        List<ComentarioDetailDTO> lista = comentariosListEntity2DTO(actividadComentarioLogic.getComentarios(actividadesId));
        LOGGER.log(Level.INFO, "ActividadComentarioResource getComentarios: output: {0}", lista);
        return lista;
    }

    /**
     * Busca y devuelve el comentario con el ID recibido en la URL, relativo a un
     * actividad.
     *
     * @param actividadesId El ID del actividad del cual se busca el comentario
     * @param comentariosId El ID del comentario que se busca
     * @return {@link ComentarioDetailDTO} - El comentario encontrado en el actividad.
     * @throws co.edu.uniandes.csw.comentariostore.exceptions.BusinessLogicException
     * si el comentario no está asociado al actividad
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el comentario.
     */
    @GET
    @Path("{comentariosId: \\d+}")
    public ComentarioDetailDTO getComentario(@PathParam("actividadesId") Long actividadesId, @PathParam("comentariosId") Long comentariosId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "ActividadComentarioResource getComentario: input: actividadesId {0} , comentariosId {1}", new Object[]{actividadesId, comentariosId});
        if (comentarioLogic.getComentario(comentariosId) == null) {
            throw new WebApplicationException("El recurso /comentarios/" + comentariosId + " no existe.", 404);
        }
        ComentarioDetailDTO detailDTO = new ComentarioDetailDTO(actividadComentarioLogic.getComentario(actividadesId, comentariosId));
        LOGGER.log(Level.INFO, "ActividadComentarioResource getComentario: output: {0}", detailDTO);
        return detailDTO;
    }

    /**
     * Actualiza la lista de comentarios de un actividad con la lista que se recibe en el
     * cuerpo
     *
     * @param actividadesId El ID del actividad al cual se le va a asociar el comentario
     * @param comentarios JSONArray {@link ComentarioDetailDTO} - La lista de comentarios que se
     * desea guardar.
     * @return JSONArray {@link ComentarioDetailDTO} - La lista actualizada.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el comentario.
     */
    @PUT
    public List<ComentarioDetailDTO> replaceComentarios(@PathParam("actividadesId") Long actividadesId, List<ComentarioDetailDTO> comentarios) {
        LOGGER.log(Level.INFO, "ActividadComentarioResource replaceComentarios: input: actividadesId {0} , comentarios {1}", new Object[]{actividadesId, comentarios});
        for (ComentarioDetailDTO comentario : comentarios) {
            if (comentarioLogic.getComentario(comentario.getId()) == null) {
                throw new WebApplicationException("El recurso /comentarios/" + comentario.getId() + " no existe.", 404);
            }
        }
        List<ComentarioDetailDTO> lista = comentariosListEntity2DTO(actividadComentarioLogic.replaceComentarios(actividadesId, comentariosListDTO2Entity(comentarios)));
        LOGGER.log(Level.INFO, "ActividadComentarioResource replaceComentarios: output: {0}", lista);
        return lista;
    }

    /**
     * Elimina la conexión entre el comentario y e actividad recibidos en la URL.
     *
     * @param actividadesId El ID del actividad al cual se le va a desasociar el comentario
     * @param comentariosId El ID del comentario que se desasocia
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el comentario.
     */
    @DELETE
    @Path("{comentariosId: \\d+}")
    public void removeComentario(@PathParam("actividadesId") Long actividadesId, @PathParam("comentariosId") Long comentariosId) {
        LOGGER.log(Level.INFO, "ActividadComentarioResource deleteComentario: input: actividadesId {0} , comentariosId {1}", new Object[]{actividadesId, comentariosId});
        if (comentarioLogic.getComentario(comentariosId) == null) {
            throw new WebApplicationException("El recurso /comentarios/" + comentariosId + " no existe.", 404);
        }
        actividadComentarioLogic.removeComentario(actividadesId, comentariosId);
        LOGGER.info("ActividadComentarioResource deleteComentario: output: void");
    }

    /**
     * Convierte una lista de ComentarioEntity a una lista de ComentarioDetailDTO.
     *
     * @param entityList Lista de ComentarioEntity a convertir.
     * @return Lista de ComentarioDetailDTO convertida.
     */
    private List<ComentarioDetailDTO> comentariosListEntity2DTO(List<ComentarioEntity> entityList) {
        List<ComentarioDetailDTO> list = new ArrayList<>();
        for (ComentarioEntity entity : entityList) {
            list.add(new ComentarioDetailDTO(entity));
        }
        return list;
    }

    /**
     * Convierte una lista de ComentarioDetailDTO a una lista de ComentarioEntity.
     *
     * @param dtos Lista de ComentarioDetailDTO a convertir.
     * @return Lista de ComentarioEntity convertida.
     */
    private List<ComentarioEntity> comentariosListDTO2Entity(List<ComentarioDetailDTO> dtos) {
        List<ComentarioEntity> list = new ArrayList<>();
        for (ComentarioDetailDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    }
}

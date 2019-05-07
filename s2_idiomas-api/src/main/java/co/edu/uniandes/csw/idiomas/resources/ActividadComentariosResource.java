/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.resources;

import co.edu.uniandes.csw.idiomas.dtos.ComentarioDTO;
import co.edu.uniandes.csw.idiomas.dtos.ComentarioDetailDTO;
import co.edu.uniandes.csw.idiomas.ejb.ActividadComentariosLogic;
import co.edu.uniandes.csw.idiomas.ejb.ComentarioLogic;
import co.edu.uniandes.csw.idiomas.entities.ComentarioEntity;
import co.edu.uniandes.csw.idiomas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.idiomas.mappers.BusinessLogicExceptionMapper;
import co.edu.uniandes.csw.idiomas.mappers.WebApplicationExceptionMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
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
public class ActividadComentariosResource 
{
    private static final Logger LOGGER = Logger.getLogger(ActividadComentariosResource.class.getName());

    @Inject
    private ActividadComentariosLogic actividadComentariosLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @Inject
    private ComentarioLogic comentarioLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    /**
     * Guarda un libro dentro de una actividad con la informacion que recibe el
     * la URL. Se devuelve el libro que se guarda en la actividad.
     *
     * @param actividadesId Identificador de la actividad que se esta
     * actualizando. Este debe ser una cadena de dígitos.
     * @param comentariosId Identificador del libro que se desea guardar. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link ComentarioDTO} - El libro guardado en la actividad.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro.
     */
    @POST
    @Path("{comentariosId: \\d+}")
    public ComentarioDTO addComentario(@PathParam("actividadesId") Long actividadesId, @PathParam("comentariosId") Long comentariosId) {
        LOGGER.log(Level.INFO, "ActividadComentariosResource addComentario: input: actividadesID: {0} , comentariosId: {1}", new Object[]{actividadesId, comentariosId});
        if (comentarioLogic.getComentario(comentariosId) == null) {
            throw new WebApplicationException("El recurso /comentarios/" + comentariosId + " no existe.", 404);
        }
        ComentarioDTO comentarioDTO = new ComentarioDTO(actividadComentariosLogic.addComentario(comentariosId, actividadesId));
        LOGGER.log(Level.INFO, "ActividadComentariosResource addComentario: output: {0}", comentarioDTO);
        return comentarioDTO;
    }

    /**
     * Busca y devuelve todos los libros que existen en la actividad.
     *
     * @param actividadesId Identificador de la actividad que se esta buscando.
     * Este debe ser una cadena de dígitos.
     * @return JSONArray {@link ComentarioDetailDTO} - Los libros encontrados en la
     * actividad. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<ComentarioDetailDTO> getComentarios(@PathParam("actividadesId") Long actividadesId) {
        LOGGER.log(Level.INFO, "ActividadComentariosResource getComentarios: input: {0}", actividadesId);
        List<ComentarioDetailDTO> listaDetailDTOs = comentariosListEntity2DTO(actividadComentariosLogic.getComentarios(actividadesId));
        LOGGER.log(Level.INFO, "ActividadComentariosResource getComentarios: output: {0}", listaDetailDTOs);
        return listaDetailDTOs;
    }

    /**
     * Busca el libro con el id asociado dentro de la actividad con id asociado.
     *
     * @param actividadesId Identificador de la actividad que se esta buscando.
     * Este debe ser una cadena de dígitos.
     * @param comentariosId Identificador del libro que se esta buscando. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link ComentarioDetailDTO} - El libro buscado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro en la
     * actividad.
     */
    @GET
    @Path("{comentariosId: \\d+}")
    public ComentarioDetailDTO getComentario(@PathParam("actividadesId") Long actividadesId, @PathParam("comentariosId") Long comentariosId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "ActividadComentariosResource getComentario: input: actividadesID: {0} , comentariosId: {1}", new Object[]{actividadesId, comentariosId});
        if (comentarioLogic.getComentario(comentariosId) == null) {
            throw new WebApplicationException("El recurso /actividades/" + actividadesId + "/comentarios/" + comentariosId + " no existe.", 404);
        }
        ComentarioDetailDTO comentarioDetailDTO = new ComentarioDetailDTO(actividadComentariosLogic.getComentario(actividadesId, comentariosId));
        LOGGER.log(Level.INFO, "ActividadComentariosResource getComentario: output: {0}", comentarioDetailDTO);
        return comentarioDetailDTO;
    }

    /**
     * Remplaza las instancias de Comentario asociadas a una instancia de Actividad
     *
     * @param actividadesId Identificador de la actividad que se esta
     * remplazando. Este debe ser una cadena de dígitos.
     * @param comentarios JSONArray {@link ComentarioDTO} El arreglo de libros nuevo para la
     * actividad.
     * @return JSON {@link ComentarioDTO} - El arreglo de libros guardado en la
     * actividad.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro.
     */
    @PUT
    public List<ComentarioDetailDTO> replaceComentarios(@PathParam("actividadesId") Long actividadesId, List<ComentarioDetailDTO> comentarios) {
        LOGGER.log(Level.INFO, "ActividadComentariosResource replaceComentarios: input: actividadesId: {0} , comentarios: {1}", new Object[]{actividadesId, comentarios});
        for (ComentarioDetailDTO comentario : comentarios) {
            if (comentarioLogic.getComentario(comentario.getId()) == null) {
                throw new WebApplicationException("El recurso /comentarios/" + comentario.getId() + " no existe.", 404);
            }
        }
        List<ComentarioDetailDTO> listaDetailDTOs = comentariosListEntity2DTO(actividadComentariosLogic.replaceComentarios(actividadesId, comentariosListDTO2Entity(comentarios)));
        LOGGER.log(Level.INFO, "ActividadComentariosResource replaceComentarios: output: {0}", listaDetailDTOs);
        return listaDetailDTOs;
    }

    /**
     * Convierte una lista de ComentarioEntity a una lista de ComentarioDetailDTO.
     *
     * @param entityList Lista de ComentarioEntity a convertir.
     * @return Lista de ComentarioDTO convertida.
     */
    private List<ComentarioDetailDTO> comentariosListEntity2DTO(List<ComentarioEntity> entityList) {
        List<ComentarioDetailDTO> list = new ArrayList();
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
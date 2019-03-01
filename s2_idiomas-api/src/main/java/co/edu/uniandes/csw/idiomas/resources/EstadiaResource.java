/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.resources;

// TODO Completar documentación clase

import co.edu.uniandes.csw.idiomas.dtos.EstadiaDTO;
import co.edu.uniandes.csw.idiomas.dtos.EstadiaDetailDTO;
import co.edu.uniandes.csw.idiomas.exceptions.BusinessLogicException;
import java.util.logging.*;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;

/**
 * Clase que define los servicios de la clase estadia
 * @author g.cubillosb
 */
@Path("estadia")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class EstadiaResource {
    
    // ----------------------------------------------------------------------
    // Atributos
    // ----------------------------------------------------------------------
    
    /**
     * Atributo que representa el logger correspondiente de la clase. 
     * Puede enviar mensajes
     */
    private static final Logger LOGGER = Logger.getLogger(ActividadResource.class.getName());
    
    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------
    
    // ----------------------------------------------------------------------
    // Métodos
    // ----------------------------------------------------------------------
    
    /**
     * Crea una nueva estadia con la información que se recibe en el cuerpo de
     * la petición y se regresa un objeto con un id-autogenerado por la base
     * de datos
     * @param estadia {@link  EstadiaDTO} - La estadia que se desea guardar
     * @return JSON {@link EstadiaDTO) - La estadia guardada con el atributo
     * autogenerado
     */
    @POST
    public EstadiaDTO createEstadia(EstadiaDTO estadia)
    {
        // TODO Falta implementar la lógica
//        LOGGER.log(Level.INFO, "ActividadResource createActividad: input: {0}", actividad);
//        ActividadDTO actividadDTO = new ActividadDTO(authorLogic.createAuthor(actividad.toEntity()));
//        LOGGER.log(Level.INFO, "ActividadResource createActividad: output: {0}", actividadDTO);
//        return actividadDTO;
        return estadia;
    }
    
    /**
     * Busca la estadia con el id asociado que se recibe en la URL y lo devuelve.
     * @param estadiaId Identificador de la estadia que se busca. Debe ser 
     * una cadena de dígitos
     * @return JSON {@link EstadiaDetailDTO} - La estadia buscada.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la actividad.
     */
    @GET
    @Path("{estadiaId: \\d+")
    public EstadiaDetailDTO getEstadia(@PathParam ("estadiaId") Long estadiaId)
    {
        LOGGER.log(Level.INFO, "EstadiaResource getEstadia: input: estadiaId: {0}", estadiaId);
        // TODO Implementar lógica
//        ActividadEntity actividadEntity = actividadLogic.getActividad(actividadId);
//        if(ActividadEntity == null)
//        {
//            throw new WebApplicationException("El recurso /actividad/" + actividadId + " no existe.", 404;
//        }
//        ActividadDetailDTO detailDTO = new ActividadDetailDTO(actividadEntity);
//        LOGGER.log(Level.INFO, "ActividadResource getActividad: output: actividadId {0}"), detailDTO);
//        return detailDTO;
        return new EstadiaDetailDTO();
    }
    
    /**
     * Actualiza la estadia con el id recibido en la URL con la información que
     * se recibe en el cuerpo de la petición.
     * @param estadiaId El identificador de la estadia que se quiere actualizar.
     * Debe ser una cadena de dígitos.
     * @param estadia {@link EstadiaDetailDTO} - La estadia que se quiere guardar
     * @return JSON {@link EstadiaDetailDTO} - La estadia guardada.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} - 
     * Error de lógica que se genera cuando no se encuentra la estadia a actualizar.
     */
    @PUT
    @Path("{estadiaId: \\d++}")
    public EstadiaDetailDTO updateEstadia(@PathParam("estadiaId") Long estadiaId, EstadiaDetailDTO estadia)
    {
        LOGGER.log(Level.INFO, "EstadiaResource updateEstadia: input: estadiaId: {0}, actividad: {1}", 
                new Object[]{estadiaId, estadia});
        // TODO Implementar lógica
//        actividad.setId(actividadId);
//        if(actividadLogic.getActividad(actividadId) == null)
//        {
//            throw new WebApplicationException("El recurso /actividad/" + actividadId + " no existe.", 404);
//        }
//        ActividadDetailDTO detailDTO = new ActividadDetailDTO(actividadLogic.updateActividad(actividadId, actividad.toEntity()));
//        LOGGER.log(Level.INFO, "ActividadResource updateActividad: output: {0}", detailDTO);
//        return detailDTO;
        return estadia;
        
    }
    
    /**
     * Borra la estadia asociada con el id recibido en la URL.
     * @param estadiaId El identificador de la estadia a borrar. Debe ser una
     * cadena de dígitos.
     * @throws BusinessLogicException Si la estadia tiene usuarios asociados
     * @throws WebApplicationException {@link WebApplicationExceptionMapper}
     * Error de lógica que ocurre cuando no se encuentra la estadia a borrar.
     */
    @DELETE
    @Path("{estadiaId: \\d++}")
    public void deleteEstadia(@PathParam("estadiaId") Long estadiaId) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "EstadiaResource deletEstadia: input: {0}", estadiaId);
        // TODO Implementar lógica
//        if (actividadLogic.getActividad(actividadId) == null)
//        {
//            throw new WebApplicationException("El recurso /actividad/" + actividadId + "no existe.", 404);
//        }
//        actividadLogic.deleteActividad(actividadId)
    }
    // TODO Método DELETE
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.resources;

import co.edu.uniandes.csw.idiomas.dtos.ActividadDTO;
import co.edu.uniandes.csw.idiomas.dtos.ActividadDetailDTO;
import co.edu.uniandes.csw.idiomas.exceptions.BusinessLogicException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;

/**
 * Clase que define los servicios de la clase actividad
 * @author g.cubillosb
 */
@Path("actividad")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class ActividadResource {
    
    // ------------------------------------------------------------------------
    // Atributos
    // ------------------------------------------------------------------------
    
    /**
     * Atributo que representa el logger correspondiente de la clase. Para poder
     * enviar mensajes.
     */
    private static final Logger LOGGER = Logger.getLogger(ActividadResource.class.getName());
    
//    @Inject
//    private ActividadLogic actividadLogic; 
// Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.
    
    // ------------------------------------------------------------------------
    // Constructor
    // ------------------------------------------------------------------------
    
    // ------------------------------------------------------------------------
    // Métodos
    // ------------------------------------------------------------------------
    
    /**
     * Crea una nueva actividad con la información que se recibe en el cuerpo 
     * de la petición y se regresa un objeto idéntico con un id auto-generado por
     * la base de datos.
     * 
     * @param actividad {@link ActividadDTO} - La actividad que se desea guardar
     * @return JSON {@link ActividadDTO} - La actividad guardada con el atributo
     * autogenerado
     */
    @POST
    public ActividadDTO createActividad(ActividadDTO actividad)
    {
          // TODO Falta implementar la lógica
        LOGGER.log(Level.INFO, "ActividadResource createActividad: input: {0}", actividad);
//        ActividadDTO actividadDTO = new ActividadDTO(authorLogic.createAuthor(actividad.toEntity()));
//        LOGGER.log(Level.INFO, "ActividadResource createActividad: output: {0}", actividadDTO);
//        return actividadDTO;
        return actividad;
    }
    
    // TODO Completar documentación con lo que hace la excepción
    /**
     * Borra la actividad con el id asociado recibido en la URL
     * 
     * @param actividadId Identificador de la actividad que se desea eliminar. 
     * Debe ser una cadena de dígitos.
     * @throws BusinessLogicException 
     */
    @DELETE
    @Path("{actividadId: \\d+}")
    public void deleteActividad(@PathParam("actividadId") Long actividadId) throws BusinessLogicException 
    {
        LOGGER.log(Level.INFO, "ActividadResource deleteActividad: input: {0}", actividadId);
        // TODO Implementar lógica
//        if (actividadLogic.getActividad(actividadId) == null)
//        {
//            throw new WebApplicationException("El recurso /actividad/" + actividadId + "no existe.", 404);
//        }
//        actividadLogic.deleteActividad(actividadId)
    }
    
    /**
     * Actualiza la actividad con el id recibido en la URL con la información
     * que se recibe en el cuerpo de la petición.
     * @param actividadId El identificador de la actividad que se quiere
     * actualizar. Debe ser una cadena de dígitos.
     * @param actividad {@link ActividadDetailDTO} La actividad que se desea
     * guardar.
     * @return JSON {@link ActividadDetailDTO} La actvidad guardada.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} 
     * Error de lógica que se genera cuando no se encuentra la actividad a
     * actualizar.
     */
    @PUT
    @Path("{actividadId: \\d+}")
    public ActividadDTO updateActividad(@PathParam("actividadId") Long actividadId, ActividadDetailDTO actividad)
    {
        LOGGER.log(Level.INFO,"ActividadResource updateActividad: input: actividadId: {0}, actividad: {1}",
                new Object[]{actividadId, actividad});
        // TODO Implementar lógica
//        actividad.setId(actividadId);
//        if(actividadLogic.getActividad(actividadId) == null)
//        {
//            throw new WebApplicationException("El recurso /actividad/" + actividadId + " no existe.", 404);
//        }
//        ActividadDetailDTO detailDTO = new ActividadDetailDTO(actividadLogic.updateActividad(actividadId, actividad.toEntity()));
//        LOGGER.log(Level.INFO, "ActividadResource updateActividad: output: {0}", detailDTO);
//        return detailDTO;
        return actividad;
    }
    
    /**
     * Busca la actividad con el id asociado que se recibe en la URL y lo
     * devuelve.
     * 
     * @param actividadId Identificador de la actividad que se busca. Debe ser
     * una cadena de dígitos.
     * @return JSON {@link ActividadDetailDTO} La actividad buscada.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} 
     * Error de lógica que se genera cuando no se encuentra la actividad.
     */
    @GET
    @Path("{actividadId: \\d+")
    public ActividadDetailDTO getActividad(@PathParam ("actividadId") Long actividadId)
    {
        LOGGER.log(Level.INFO, "ActividadResource getActividad: input: actividadId: {0}", actividadId);
        // TODO Implementar lógica
//        ActividadEntity actividadEntity = actividadLogic.getActividad(actividadId);
//        if(ActividadEntity == null)
//        {
//            throw new WebApplicationException("El recurso /actividad/" + actividadId + " no existe.", 404;
//        }
//        ActividadDetailDTO detailDTO = new ActividadDetailDTO(actividadEntity);
//        LOGGER.log(Level.INFO, "ActividadResource getActividad: output: actividadId {0}"), detailDTO);
//        return detailDTO;
        return new ActividadDetailDTO();
    }
    
    
    
}

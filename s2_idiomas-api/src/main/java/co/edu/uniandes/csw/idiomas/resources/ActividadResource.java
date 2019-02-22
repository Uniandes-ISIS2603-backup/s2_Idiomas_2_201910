/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.resources;

import co.edu.uniandes.csw.idiomas.dtos.ActividadDTO;
import co.edu.uniandes.csw.idiomas.exceptions.BusinessLogicException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

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
//        LOGGER.log(Level.INFO, "ActividadResource createActividad: input: {0}", actividad);
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
    
    
    // TODO Método PUT
    // TODO Método GET
    
    
    
}

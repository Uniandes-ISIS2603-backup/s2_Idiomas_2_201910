/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.resources;

import co.edu.uniandes.csw.idiomas.dtos.OtroDTO;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.*;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Clase que define los servicios de la clase Otro
 * @author g.cubillosb
 */
@Path("otro")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class OtroResource {
    
    // ------------------------------------------------------------------------
    // Atributos
    // ------------------------------------------------------------------------
    
    private static final Logger LOGGER = Logger.getLogger(OtroResource.class.getName());
    
    // ------------------------------------------------------------------------
    // Constructor
    // ------------------------------------------------------------------------
    
    // ------------------------------------------------------------------------
    // Métodos
    // ------------------------------------------------------------------------
    
    /**
     * Crea un nuevo otro con la información que se recibe en el cuerpo de la
     * petición y se devuelve un objeto idéntico, con un id autogenerado por la
     * base de datos.
     * 
     * @param otro {@link OtroDTO} - El otro a guardar.
     * @return JSON {@link OtroDTO} - El otro generado con el atributo 
     * autogenerado.
     */
    @POST
    public OtroDTO createOtro (OtroDTO otro)
    {
        LOGGER.log(Level.INFO, "OtroResource createOtro: input: {0}", otro);
        // TODO: GC Implementar lógica POST
//        ActividadDTO actividadDTO = new ActividadDTO(authorLogic.createAuthor(actividad.toEntity()));
//        LOGGER.log(Level.INFO, "ActividadResource createActividad: output: {0}", actividadDTO);
//        return actividadDTO;
        return otro;
    }
    
    /**
     * Actualiza al otro asociado al id recibido en la URL con la información que
     * se recibe en el cuerpo de la petición.
     * 
     * @param otroId El identificador del otro que se quiere. Debe ser una cadena
     * de dígitos.
     * @param otro {@link OtroDTO} - El otro que se desea guardar
     * @return JSON {@link OtroDTO} - El otro guardado.
     * // TODO: GC Completar documentación con excepciones.
     */
    @PUT
    @Path("{otroId: \\d+}")
    public OtroDTO updateOtro (@PathParam("otroId") Long otroId, OtroDTO otro)
    {
        LOGGER.log(Level.INFO, "OtroResource createOtro: input: otroId: {0}, otro: {1}",
                new Object[]{otroId, otro});
        // TODO: GC Implementar lógica PUT
        //        actividad.setId(actividadId);
//        if(actividadLogic.getActividad(actividadId) == null)
//        {
//            throw new WebApplicationException("El recurso /actividad/" + actividadId + " no existe.", 404);
//        }
//        ActividadDetailDTO detailDTO = new ActividadDetailDTO(actividadLogic.updateActividad(actividadId, actividad.toEntity()));
//        LOGGER.log(Level.INFO, "ActividadResource updateActividad: output: {0}", detailDTO);
//        return detailDTO;
        return otro;
    }
    
    /**
     * Busca el otro con el id asociado que se recibe en la URL y lo devuelve.
     * 
     * @param otroId El identificador del otro que se busca. Debe ser una cadena
     * de dígitos
     * @return JSON {@link OtroDTO} - El otro buscado.
     * // TODO: GC Completar documentación con excepciones.
     */
    @GET
    @Path("{otroId: \\d+}")
    public OtroDTO getOtro (@PathParam("otroId") Long otroId)
    {
        LOGGER.log(Level.INFO, "OtroResource getOtro: input: {0}", otroId);
        // TODO: GC Implementar lógica GET
        //        ActividadEntity actividadEntity = actividadLogic.getActividad(actividadId);
//        if(ActividadEntity == null)
//        {
//            throw new WebApplicationException("El recurso /actividad/" + actividadId + " no existe.", 404;
//        }
//        ActividadDetailDTO detailDTO = new ActividadDetailDTO(actividadEntity);
//        LOGGER.log(Level.INFO, "ActividadResource getActividad: output: actividadId {0}"), detailDTO);
//        return detailDTO;
        return new OtroDTO();
    }
    
    /**
     * Borra el otro con el id asociado recibido en la URL
     * 
     * @param otroId El identificador de el otro que se desea eliminar.
     * // TODO: GC Completar documentación con excepciones
     */
    @DELETE
    @Path("{otroId: \\d+}")
    public void deleteOtro (@PathParam("otroId") Long otroId)
    {
        LOGGER.log(Level.INFO, "OtroResource deleteOtro: input: {0}", otroId);
        // TODO: GC Implementar lógica DELETE
//        if (actividadLogic.getActividad(actividadId) == null)
//        {
//            throw new WebApplicationException("El recurso /actividad/" + actividadId + "no existe.", 404);
//        }
//        actividadLogic.deleteActividad(actividadId)
    }
}

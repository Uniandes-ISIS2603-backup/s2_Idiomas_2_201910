/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.resources;


import co.edu.uniandes.csw.idiomas.dtos.EncuentroDTO;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Clase que define los servicios de la clase Encuentro.
 * @author g.cubillosb
 */
@Path("encuentro")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class EncuentroResource {
    
    // ------------------------------------------------------------------------
    // Atributos
    // ------------------------------------------------------------------------
    
    private static final Logger LOGGER = Logger.getLogger(EncuentroResource.class.getName());
    
    // ------------------------------------------------------------------------
    // Constructor
    // ------------------------------------------------------------------------
    
    // ------------------------------------------------------------------------
    // Métodos
    // ------------------------------------------------------------------------
    
    /**
     * Crea un nuevo encuentro con la información que se recibe en el cuerpo
     * de la petición y se regresa un objeto idéntico, con un id autogenerado
     * por la base de datos.
     * 
     * @param encuentro {@link EncuentroDTO} - El encuentro a guardar.
     * @return JSON {@link EncuentroDTO} - El encuentro guardado con el atributo
     * autogenerado.
     */
    @POST
    public EncuentroDTO createEncuentro (EncuentroDTO encuentro)
    {
        // TODO: GC Implementar lógica POST
        LOGGER.log(Level.INFO, "EncuentroResource createEncuentro: input: {0}", encuentro);
//        ActividadDTO actividadDTO = new ActividadDTO(authorLogic.createAuthor(actividad.toEntity()));
//        LOGGER.log(Level.INFO, "ActividadResource createActividad: output: {0}", actividadDTO);
//        return actividadDTO;
        return encuentro;
    }
    
    /**
     * Actualiza el encuentro asociado al id recibido en la URL con la información
     * que se recibe en el cuerpo de la petición.
     * 
     * @param encuentroId El identificador del encuentro que se quiere. Debe ser
     * una cadena de dígitos.
     * @param encuentro {@link EncuentroDTO} - El encuentro que se desea guardar.
     * @return JSON {@link EncuentroDTO} - El encuentro guardado.
     * // TODO: GC Completar documentación con excepciones
     */
    @PUT
    @Path("{encuentroId: \\d+}")
    public EncuentroDTO updateEncuentro (@PathParam("encuentroId") Long encuentroId, EncuentroDTO encuentro)
    {
        LOGGER.log(Level.INFO, "EncuentroResource updateEncuentro: input: encuentroId: {0}, encuentro: {1}", 
                new Object[]{encuentroId, encuentro});
        // TODO: GC Implementar lógica PUT
//        actividad.setId(actividadId);
//        if(actividadLogic.getActividad(actividadId) == null)
//        {
//            throw new WebApplicationException("El recurso /actividad/" + actividadId + " no existe.", 404);
//        }
//        ActividadDetailDTO detailDTO = new ActividadDetailDTO(actividadLogic.updateActividad(actividadId, actividad.toEntity()));
//        LOGGER.log(Level.INFO, "ActividadResource updateActividad: output: {0}", detailDTO);
//        return detailDTO;
        return encuentro;
    }
    
    /**
     * Busca y devuelve el encuentro con el id asociado recibido en la URL
     * 
     * @param encuentroId El identificador del encuentro que se busca. Debe ser
     * una cadena de dígitos.
     * @return JSON {@link EncuentroDTO} - El encuentro buscado.
     * // TODO: GC Completar documentación excepciones.
     */
    @GET
    @Path("{encuentroId: \\d+}")
    public EncuentroDTO getEncuentro (@PathParam("encuentroId") Long encuentroId)
    {
        LOGGER.log(Level.INFO, "EncuentroResource getEncuentro: input: encuentroId: {0}", encuentroId);
        // TODO: GC Implementar lógica GET
//        ActividadEntity actividadEntity = actividadLogic.getActividad(actividadId);
//        if(ActividadEntity == null)
//        {
//            throw new WebApplicationException("El recurso /actividad/" + actividadId + " no existe.", 404;
//        }
//        ActividadDetailDTO detailDTO = new ActividadDetailDTO(actividadEntity);
//        LOGGER.log(Level.INFO, "ActividadResource getActividad: output: actividadId {0}"), detailDTO);
//        return detailDTO;
        return new EncuentroDTO();
    }
    
    @DELETE
    @Path("{encuentroId: \\d+}")
    public void deleteEncuentro (@PathParam("encuentroId") Long encuentroId)
    {
        LOGGER.log(Level.INFO, "EncuentroResource deleteEncuentro: input: {0}", encuentroId);
        // TODO: GC Implementar lógica DELETE
//        if (actividadLogic.getActividad(actividadId) == null)
//        {
//            throw new WebApplicationException("El recurso /actividad/" + actividadId + "no existe.", 404);
//        }
//        actividadLogic.deleteActividad(actividadId)
    }
    
}

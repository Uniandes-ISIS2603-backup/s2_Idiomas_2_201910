/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.resources;

import co.edu.uniandes.csw.idiomas.dtos.CalificacionDTO;
import co.edu.uniandes.csw.idiomas.dtos.CalificacionDetailDTO;
import co.edu.uniandes.csw.idiomas.ejb.CalificacionLogic;
import co.edu.uniandes.csw.idiomas.exceptions.BusinessLogicException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
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

/**
 *
 * @author jdruedaa
 */
@Path("grades")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class CalificacionResource {
    private static final Logger LOGGER = Logger.getLogger(CalificacionResource.class.getName());
     @Inject
    private CalificacionLogic logica;
    @POST
    public CalificacionDTO createCalificacion (CalificacionDTO calificacion) throws BusinessLogicException
    {
        return calificacion;
    }
    @DELETE
    @Path("{calificacionId: \\d+}")
    public Integer deleteCalificacion(@PathParam("idCalificacion") Integer calificacionId) {
        return calificacionId;
    }
    @GET
    @Path("{calificacionesId: \\d+}")
    public CalificacionDetailDTO retornarCalificacion(@PathParam("calificacionesId") Integer calificacionId) {
        return null;
    }
    
    /**
     * Actualiza la calificacion con el id recibido en la URL con la informacion
     * que se recibe en el cuerpo de la petición.
     *
     * @param calificacionId Identificador de la calificacion que se desea
     * actualizar. Este debe ser una cadena de dígitos.
     * @param calificacion {@link calificacionDetailDTO} La calificacion que se desea
     * guardar.
     * @return JSON {@link calificacionDetailDTO} - La calificacion guardada.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la calificacion a
     * actualizar.
     */
    @PUT
    @Path("{CalificacionId: \\d+}")
    public CalificacionDTO updatecalificacion(@PathParam("calificacionId") Long calificacionId, CalificacionDTO calificacion) throws BusinessLogicException 
    {
        LOGGER.log(Level.INFO, "calificacionResource updatecalificacion: input: id:{0} , calificacion: {1}", new Object[]{calificacionId, calificacion});
        calificacion.setId(calificacionId);
        if (logica.getCalificacion(calificacionId) == null) {
            throw new WebApplicationException("El recurso /calificaciones/" + calificacionId + " no existe.", 404);
        }
        CalificacionDTO detailDTO = new CalificacionDTO(logica.updateCalificacion(calificacionId, calificacion.toEntity()));
        LOGGER.log(Level.INFO, "calificacionResource updatecalificacion: output: {0}", detailDTO);
        return detailDTO;

    }
}
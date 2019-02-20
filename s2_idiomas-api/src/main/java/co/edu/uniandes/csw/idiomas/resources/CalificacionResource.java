/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.resources;

import co.edu.uniandes.csw.idiomas.dtos.CalificacionDTO;
import co.edu.uniandes.csw.idiomas.exceptions.BusinessLogicException;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author jdruedaa
 */
@Path("calificaciones")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class CalificacionResource {
    private static final Logger LOGGER = Logger.getLogger(CalificacionResource.class.getName());
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
    public Integer retornarCoordinador(@PathParam("calificacionesId") Integer calificacionId) {
        return calificacionId;
    }
    
}

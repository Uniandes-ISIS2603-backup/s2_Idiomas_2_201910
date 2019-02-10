/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.resources;

import co.edu.uniandes.csw.idiomas.dtos.ComentarioGrupoDTO;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author estudiante
 */

@Path("GrupoComments")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped

public class ComentarioGrupoResource {
    
    private static final Logger LOGGER = Logger.getLogger(ComentarioCalificacionResource.class.getName());
    
    @PUT 
    public ComentarioGrupoDTO setComment(ComentarioGrupoDTO comentario)
    {
        return comentario;
    }
}

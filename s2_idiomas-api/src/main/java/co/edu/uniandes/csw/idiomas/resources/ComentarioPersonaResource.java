/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.resources;

import co.edu.uniandes.csw.idiomas.dtos.ComentarioDTO;
import co.edu.uniandes.csw.idiomas.dtos.ComentarioGrupoDTO;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author estudiante
 */

@Path("ComentarioPersona")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped

public class ComentarioPersonaResource {
    
    private static final Logger LOGGER = Logger.getLogger(ComentarioPersonaResource.class.getName());
    
    @GET 
    public ComentarioDTO getComment(ComentarioDTO comentario)
    {
        return comentario;
    }
    
    @POST 
    public ComentarioDTO createComment(ComentarioDTO comentario)
    {
        return comentario;
    }
    
    @DELETE 
    public ComentarioDTO deleteComment(ComentarioDTO comentario)
    {
        return comentario;
    }
}

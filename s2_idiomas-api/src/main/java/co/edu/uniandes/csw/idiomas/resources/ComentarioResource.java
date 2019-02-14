/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.resources;

import co.edu.uniandes.csw.idiomas.dtos.ComentarioDTO;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author Santiago Gamboa
 */
@Path("comments")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped


public class ComentarioResource {
    
    private static final Logger LOGGER = Logger.getLogger(ComentarioResource.class.getName());
    
    @POST 
    public ComentarioDTO createComment(ComentarioDTO comentario)
    {
        return comentario;
    }
}

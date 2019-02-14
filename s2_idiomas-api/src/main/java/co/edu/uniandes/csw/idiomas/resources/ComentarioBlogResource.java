/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.resources;

import co.edu.uniandes.csw.idiomas.dtos.ComentarioBlogDTO;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("BlogComments")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
/**
 *
 * @author Santiago Gamboa
 */
public class ComentarioBlogResource {
    
    private static final Logger LOGGER = Logger.getLogger(ComentarioBlogResource.class.getName());
    
    @PUT 
    public ComentarioBlogDTO setComment(ComentarioBlogDTO comentario)
    {
        return comentario;
    }
}

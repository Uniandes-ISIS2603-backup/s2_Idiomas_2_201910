/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.resources;

import co.edu.uniandes.csw.idiomas.dtos.BlogDTO;
import co.edu.uniandes.csw.idiomas.exceptions.BusinessLogicException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author estudiante
 */
@Path("blog")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class BlogResource {
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
     * @param grupoDeInteres {@link EncuentroDTO} - El encuentro a guardar.
     * @return JSON {@link EncuentroDTO} - El encuentro guardado con el atributo
     * autogenerado.
     *
     */
    @POST
    public BlogDTO createBlog (BlogDTO blog)throws BusinessLogicException
    {
        // TODO: GC Implementar lógica POST
        LOGGER.log(Level.INFO, "BlogResource createBlog: input: {0}", blog);
//        ActividadDTO actividadDTO = new ActividadDTO(authorLogic.createAuthor(actividad.toEntity()));
//        LOGGER.log(Level.INFO, "ActividadResource createActividad: output: {0}", actividadDTO);
//        return actividadDTO;
        return blog;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.resources;

import co.edu.uniandes.csw.idiomas.dtos.BlogDTO;
import co.edu.uniandes.csw.idiomas.dtos.BlogDetailDTO;
import co.edu.uniandes.csw.idiomas.dtos.GrupoDeInteresDTO;
import co.edu.uniandes.csw.idiomas.exceptions.BusinessLogicException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
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
     * @param blog {@link EncuentroDTO} - El blog a guardar.
     * @return JSON {@link EncuentroDTO} - El blog guardado con el atributo
     * autogenerado.
     *
     */
    @POST
    public BlogDTO createBlog (BlogDTO blog)throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "BlogResource createBlog: input: {0}", blog);
//        ActividadDTO actividadDTO = new ActividadDTO(authorLogic.createAuthor(actividad.toEntity()));
//        LOGGER.log(Level.INFO, "ActividadResource createActividad: output: {0}", actividadDTO);
//        return actividadDTO;
        return blog;
    }
       /**
     * Actualiza el encuentro asociado al id recibido en la URL con la información
     * que se recibe en el cuerpo de la petición.
     * 
     * @param blog El identificador del blog que se quiere. Debe ser
     * una cadena de dígitos.
     * @param blog {@link BlogDTO} - El grupo que se desea guardar.
     * @return JSON {@link BlogTO} - El grupo guardado.
     */
    @PUT
    @Path("{blogId: \\d+}")
    public BlogDTO updateBlog (@PathParam("blogId") Long blogId, BlogDTO blog)throws WebApplicationException
    {
        LOGGER.log(Level.INFO, "BlogResource updateBlog: input: blogId: {0}, blog: {1}", 
                new Object[]{blogId, blog});
//        actividad.setId(actividadId);
//        if(actividadLogic.getActividad(actividadId) == null)
//        {
//            throw new WebApplicationException("El recurso /actividad/" + actividadId + " no existe.", 404);
//        }
//        ActividadDetailDTO detailDTO = new ActividadDetailDTO(actividadLogic.updateActividad(actividadId, actividad.toEntity()));
//        LOGGER.log(Level.INFO, "ActividadResource updateActividad: output: {0}", detailDTO);
//        return detailDTO;
        return blog;
    }
        /**
     * Busca y devuelve el encuentro con el id asociado recibido en la URL
     * 
     * @param blogID El identificador del blog que se busca. Debe ser
     * una cadena de dígitos.
     * @return JSON {@link BlogDTO} - El blog buscado.
     */
    @GET
    @Path("{blogID: \\d+}")
    public BlogDetailDTO getBlog (@PathParam("blogID") Long blogID)
    {
        LOGGER.log(Level.INFO, "BlogResource getBlog: input: blogID: {0}", blogID);
//        ActividadEntity actividadEntity = actividadLogic.getActividad(actividadId);
//        if(ActividadEntity == null)
//        {
//            throw new WebApplicationException("El recurso /actividad/" + actividadId + " no existe.", 404;
//        }
//        ActividadDetailDTO detailDTO = new ActividadDetailDTO(actividadEntity);
//        LOGGER.log(Level.INFO, "ActividadResource getActividad: output: actividadId {0}"), detailDTO);
//        return detailDTO;
        return new BlogDetailDTO();
    }
    @DELETE
    @Path("{blogID: \\d+}")
    public void deleteBlog (@PathParam("blogID") Long blogID)
    {
        LOGGER.log(Level.INFO, "BlogResource deleteBlog: input: {0}", blogID);
//        if (actividadLogic.getActividad(actividadId) == null)
//        {
//            throw new WebApplicationException("El recurso /actividad/" + actividadId + "no existe.", 404);
//        }
//        actividadLogic.deleteActividad(actividadId)
    }
}

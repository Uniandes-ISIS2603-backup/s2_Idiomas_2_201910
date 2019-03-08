    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.resources;

import co.edu.uniandes.csw.idiomas.dtos.ComentarioDTO;
import co.edu.uniandes.csw.idiomas.dtos.ComentarioDetailDTO;
import co.edu.uniandes.csw.idiomas.ejb.ComentarioLogic;
import co.edu.uniandes.csw.idiomas.entities.ComentarioEntity;
import co.edu.uniandes.csw.idiomas.exceptions.BusinessLogicException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author se.gamboa
 */
@Path("ComentarioPersona")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped

public class ComentarioPersonaResource {

    private static final Logger LOGGER = Logger.getLogger(ComentarioPersonaResource.class.getName());

    @Inject
    private ComentarioLogic logica;
    /**
     * Actualiza el comentario con el id asociado recibido en la URL.
     *
     * @param comentariosId id del comentario buscado
     * @return
     */
    @GET
    @Path("{comentariosId : \\d+}")
    public ComentarioDetailDTO getComment(@PathParam("id") Long comentariosId) {
        ComentarioEntity entidad = logica.getComment(comentariosId);
        if(entidad == null){
            throw new WebApplicationException("El recurso " + comentariosId + " no existe",404);
        }
        return new ComentarioDetailDTO(entidad);
    }

    @POST
    public ComentarioDTO createComment(ComentarioDTO comentario) throws BusinessLogicException, ParseException {
        ComentarioEntity entidad = comentario.toEntity();
        entidad = logica.createComment(entidad);
        return new ComentarioDTO(entidad);
    }

    /**
     *Borra un comentario asociado a un id.
     * @param comentariosId
     * @throws BusinessLogicException
     */
    @DELETE
    @Path("{comentariosId: \\d+}")
    public void deleteComment(@PathParam("id") Long comentariosId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "ComentarioResource deleteComment: input: {0}", comentariosId);
        ComentarioEntity entity = logica.getComment(comentariosId);
        if (entity == null) {
            throw new WebApplicationException("El recurso" + comentariosId + " no existe.", 404);
        }
        logica.deleteComment(comentariosId);
        LOGGER.info("CommentResource deleteComment: output: void");
    }
}

    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.resources;

import co.edu.uniandes.csw.idiomas.dtos.ComentarioDTO;
import co.edu.uniandes.csw.idiomas.dtos.ComentarioGrupoDTO;
import co.edu.uniandes.csw.idiomas.ejb.ComentarioPersonaLogic;
import co.edu.uniandes.csw.idiomas.entities.ComentarioEntity;
import co.edu.uniandes.csw.idiomas.entities.ComentarioGrupoEntity;
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

    //private static final Logger LOGGER = Logger.getLogger(ComentarioPersonaResource.class.getName());

    //@Inject
   // private ComentarioPersonaLogic logica;
    
    /**
     * Crea una nueva comment con la informacion que se recibe en el cuerpo de
     * la petición y se regresa un objeto identico con un id auto-generado por
     * la base de datos.
     *
     * @param comentario {@link commentDTO} - La comment que se desea
     * guardar.
     * @return JSON {@link commentDTO} - La comment guardada con el atributo
     * id autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe la comment.
     */
   // @POST
   // public ComentarioDTO createComment(ComentarioDTO comentario, Long personaId) throws BusinessLogicException 
   // {
   //     LOGGER.log(Level.INFO, "commentResource createcomment: input: {0}", comentario);
        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
   //     ComentarioEntity comentarioEntity = comentario.toEntity();
        // Invoca la lógica para crear un comentario nuevo
   //     ComentarioEntity comentarioCalifEntity = logica.addComentarioPersona(comentarioEntity.getId(), personaId);
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
   //     ComentarioDTO comentarioDTO = new ComentarioDTO(comentarioCalifEntity);
   //     LOGGER.log(Level.INFO, "ComentarioResource createcomment: output: {0}", comentarioDTO);
   //     return comentarioDTO;
   // }
    
}

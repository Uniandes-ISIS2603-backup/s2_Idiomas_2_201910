/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.resources;

import co.edu.uniandes.csw.idiomas.dtos.CalificacionDTO;
import co.edu.uniandes.csw.idiomas.ejb.CalificacionLogic;
import co.edu.uniandes.csw.idiomas.entities.CalificacionEntity;
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
 * @author Jd.ruedaa
 */
@Path("actividadGrades")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class CalificacionActividadResource {
    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
    private static final Logger LOGGER = Logger.getLogger(CalificacionActividadResource.class.getName());
    
     @Inject
    private CalificacionLogic logica;
    
    /**
     * Crea una nueva calificacion con la informacion que se recibe en el cuerpo de
     * la petición y se regresa un objeto identico con un id auto-generado por
     * la base de datos.
     *
     * @param calificacion {@link calificacionDTO} - La calificacion que se desea
     * guardar.
     * @return JSON {@link calificacionDTO} - La calificacion guardada con el atributo
     * id autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando la calificacion no cumple con los requisitos.
     */
    @POST
    public CalificacionDTO createCalificacion(CalificacionDTO calificacion) throws BusinessLogicException 
    {
        LOGGER.log(Level.INFO, "calificacionResource createcalificacion: input: {0}", calificacion);
        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        CalificacionEntity calificacionEntity = calificacion.toEntity();
        // Invoca la lógica para crear una Calificacion nueva
        CalificacionEntity calificacionResEntity = logica.createCalificacion(calificacionEntity);
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        CalificacionDTO calificacionDTO = new CalificacionDTO(calificacionResEntity);
        LOGGER.log(Level.INFO, "CalificacionResource createcalificacion: output: {0}", calificacionDTO);
        return calificacionDTO;
    }
    
    /**
     * Busca el Calificacion con el id asociado recibido en la URL y la devuelve.
     *
     * @param CalificacionId Identificador de la Calificacion que se esta buscando.
     * Este debe ser una cadena de dígitos.
     * @return JSON {@link CalificacionDetailDTO} - La Calificacion buscada
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la Calificacion.
     */
    @GET
    @Path("{calificacionId: \\d+}")
    public CalificacionDTO getCalificacion(@PathParam("calificacionId") Long CalificacionId)
    {
        LOGGER.log(Level.INFO, "CalificacionResource getCalificacion: input: {0}", CalificacionId);
        CalificacionEntity CalificacionEntity = logica.getCalificacion(CalificacionId);
        if (CalificacionEntity == null) {
            throw new WebApplicationException("El recurso /Calificaciones/" + CalificacionId + " no existe.", 404);
        }
        CalificacionDTO detailDTO = new CalificacionDTO(CalificacionEntity);
        LOGGER.log(Level.INFO, "CalificacionResource getCalificacion: output: {0}", detailDTO);
        return detailDTO;
    }
    
    /**
     * Borra la Calificacion con el id asociado recibido en la URL.
     *
     * @param CalificacionId Identificador de la Calificacion que se desea borrar.
     * Este debe ser una cadena de dígitos.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se puede eliminar la Calificacion.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la Calificacion.
     */
    @DELETE
    @Path("{calificacionId: \\d+}")
    public void deleteCalificacion(@PathParam("calificacionId") Long CalificacionId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "CalificacionResource deleteCalificacion: input: {0}", CalificacionId);
        if (logica.getCalificacion(CalificacionId) == null) {
            throw new WebApplicationException("El recurso /Calificacions/" + CalificacionId + " no existe.", 404);
        }
        logica.deleteCalificacion(CalificacionId);
        LOGGER.info("CalificacionResource deleteCalificacion: output: void");
    }
    
     /**
     * Actualiza la Calificacion con el id recibido en la URL con la informacion
     * que se recibe en el cuerpo de la petición.
     *
     * @param CalificacionId Identificador de la Calificacion que se desea
     * actualizar. Este debe ser una cadena de dígitos.
     * @param Calificacion {@link CalificacionDetailDTO} La Calificacion que se desea
     * guardar.
     * @return JSON {@link CalificacionDetailDTO} - La Calificacion guardada.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la Calificacion a
     * actualizar.
     */
    @PUT
    @Path("{calificacionId: \\d+}")
    public CalificacionDTO updateCalificacion(@PathParam("calificacionId") Long CalificacionId, CalificacionDTO Calificacion) throws BusinessLogicException 
    {
        LOGGER.log(Level.INFO, "CalificacionResource updateCalificacion: input: id:{0} , Calificacion: {1}", new Object[]{CalificacionId, Calificacion});
        Calificacion.setId(CalificacionId);
        if (logica.getCalificacion(CalificacionId) == null) {
            throw new WebApplicationException("El recurso /Calificaciones/" + CalificacionId + " no existe.", 404);
        }
        CalificacionDTO detailDTO = new CalificacionDTO(logica.updateCalificacion(CalificacionId, Calificacion.toEntity()));
        LOGGER.log(Level.INFO, "CalificacionResource updateCalificacion: output: {0}", detailDTO);
        return detailDTO;

    }
}

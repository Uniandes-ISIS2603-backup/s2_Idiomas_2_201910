/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.resources;

import co.edu.uniandes.csw.idiomas.dtos.CoordinadorDTO;
import co.edu.uniandes.csw.idiomas.dtos.CalificacionDetailDTO;
import co.edu.uniandes.csw.idiomas.ejb.CoordinadorLogic;
import co.edu.uniandes.csw.idiomas.ejb.CalificacionCoordinadorLogic;
import co.edu.uniandes.csw.idiomas.ejb.CalificacionLogic;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

/**
 * Clase que implementa el recurso "coordinador/{id}/calificaciones".
 *
 * @author jdruedaa
 */
@Path("calificaciones/{calificacionesId: \\d+}/coordinador")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CalificacionCoordinadorResource {

    private static final Logger LOGGER = Logger.getLogger(CalificacionCoordinadorResource.class.getName());

    @Inject
    private CalificacionLogic calificacionLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @Inject
    private CalificacionCoordinadorLogic calificacionCoordinadorLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @Inject
    private CoordinadorLogic coordinadorLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    /**
     * Remplaza la instancia de Coordinador asociada a un Calificacion.
     *
     * @param calificacionesId Identificador de la calificacion que se esta actualizando. Este
     * debe ser una cadena de dígitos.
     * @param coordinador La coordinador que será de la calificacion.
     * @return JSON {@link CalificacionDetailDTO} - El arreglo de calificaciones guardado en la
     * coordinador.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la coordinador o la
     * calificacion.
     */
    @PUT
    public CalificacionDetailDTO replaceCoordinador(@PathParam("calificacionesId") Long calificacionesId, CoordinadorDTO coordinador) {
        LOGGER.log(Level.INFO, "CalificacionCoordinadorResource replaceCoordinador: input: calificacionesId{0} , Coordinador:{1}", new Object[]{calificacionesId, coordinador});
        if (calificacionLogic.getCalificacion(calificacionesId) == null) {
            throw new WebApplicationException("El recurso /calificaciones/" + calificacionesId + " no existe.", 404);
        }
        if (coordinadorLogic.getCoordinador(coordinador.getId()) == null) {
            throw new WebApplicationException("El recurso /coordinadors/" + coordinador.getId() + " no existe.", 404);
        }
        CalificacionDetailDTO calificacionDetailDTO = new CalificacionDetailDTO(calificacionCoordinadorLogic.replaceCoordinador(calificacionesId, coordinador.getId()));
        LOGGER.log(Level.INFO, "CalificacionCoordinadorResource replaceCoordinador: output: {0}", calificacionDetailDTO);
        return calificacionDetailDTO;
    }
}
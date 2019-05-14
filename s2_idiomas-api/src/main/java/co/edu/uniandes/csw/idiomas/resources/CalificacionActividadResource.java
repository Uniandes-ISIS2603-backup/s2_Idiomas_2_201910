/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.resources;

import co.edu.uniandes.csw.idiomas.dtos.ActividadDTO;
import co.edu.uniandes.csw.idiomas.dtos.CalificacionDetailDTO;
import co.edu.uniandes.csw.idiomas.ejb.ActividadLogic;
import co.edu.uniandes.csw.idiomas.ejb.CalificacionActividadLogic;
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
 * Clase que implementa el recurso "actividad/{id}/calificaciones".
 *
 * @author jdruedaa
 */
@Path("calificaciones/{calificacionesId: \\d+}/actividad")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CalificacionActividadResource {

    private static final Logger LOGGER = Logger.getLogger(CalificacionActividadResource.class.getName());

    @Inject
    private CalificacionLogic calificacionLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @Inject
    private CalificacionActividadLogic calificacionActividadLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @Inject
    private ActividadLogic actividadLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    /**
     * Remplaza la instancia de Actividad asociada a un Calificacion.
     *
     * @param calificacionesId Identificador de la calificacion que se esta actualizando. Este
     * debe ser una cadena de dígitos.
     * @param actividad La actividad que será de la calificacion.
     * @return JSON {@link CalificacionDetailDTO} - El arreglo de calificaciones guardado en la
     * actividad.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la actividad o la
     * calificacion.
     */
    @PUT
    public CalificacionDetailDTO replaceActividad(@PathParam("calificacionesId") Long calificacionesId, ActividadDTO actividad) {
        LOGGER.log(Level.INFO, "CalificacionActividadResource replaceActividad: input: calificacionesId{0} , Actividad:{1}", new Object[]{calificacionesId, actividad});
        if (calificacionLogic.getCalificacion(calificacionesId) == null) {
            throw new WebApplicationException("El recurso /calificaciones/" + calificacionesId + " no existe.", 404);
        }
        if (actividadLogic.getActividad(actividad.getId()) == null) {
            throw new WebApplicationException("El recurso /actividades/" + actividad.getId() + " no existe.", 404);
        }
        CalificacionDetailDTO calificacionDetailDTO = new CalificacionDetailDTO(calificacionActividadLogic.replaceActividad(calificacionesId, actividad.getId()));
        LOGGER.log(Level.INFO, "CalificacionActividadResource replaceActividad: output: {0}", calificacionDetailDTO);
        return calificacionDetailDTO;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.resources;

import co.edu.uniandes.csw.idiomas.dtos.GrupoDeInteresDTO;
import co.edu.uniandes.csw.idiomas.dtos.CalificacionDetailDTO;
import co.edu.uniandes.csw.idiomas.ejb.GrupoDeInteresLogic;
import co.edu.uniandes.csw.idiomas.ejb.CalificacionGrupoLogic;
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
 * Clase que implementa el recurso "grupoDeInteres/{id}/calificaciones".
 *
 * @author jdruedaa
 */
@Path("calificaciones/{calificacionesId: \\d+}/grupos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CalificacionGrupoResource {

    private static final Logger LOGGER = Logger.getLogger(CalificacionGrupoResource.class.getName());

    @Inject
    private CalificacionLogic calificacionLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @Inject
    private CalificacionGrupoLogic calificacionGrupoDeInteresLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @Inject
    private GrupoDeInteresLogic grupoDeInteresLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    /**
     * Remplaza la instancia de GrupoDeInteres asociada a un Calificacion.
     *
     * @param calificacionesId Identificador de la calificacion que se esta actualizando. Este
     * debe ser una cadena de dígitos.
     * @param grupoDeInteres El grupoDeInteres que será de la calificacion.
     * @return JSON {@link CalificacionDetailDTO} - El arreglo de calificaciones guardado en la
     * grupoDeInteres.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el grupoDeInteres o la
     * calificacion.
     */
    @PUT
    public CalificacionDetailDTO replaceGrupoDeInteres(@PathParam("calificacionesId") Long calificacionesId, GrupoDeInteresDTO grupoDeInteres) {
        LOGGER.log(Level.INFO, "CalificacionGrupoDeInteresResource replaceGrupoDeInteres: input: calificacionesId{0} , GrupoDeInteres:{1}", new Object[]{calificacionesId, grupoDeInteres});
        if (calificacionLogic.getCalificacion(calificacionesId) == null) {
            throw new WebApplicationException("El recurso /calificaciones/" + calificacionesId + " no existe.", 404);
        }
        if (grupoDeInteresLogic.getGrupoDeInteres(grupoDeInteres.getId()) == null) {
            throw new WebApplicationException("El recurso /grupoDeInteres/" + grupoDeInteres.getId() + " no existe.", 404);
        }
        CalificacionDetailDTO calificacionDetailDTO = new CalificacionDetailDTO(calificacionGrupoDeInteresLogic.replaceGrupoDeInteres(calificacionesId, grupoDeInteres.getId()));
        LOGGER.log(Level.INFO, "CalificacionGrupoDeInteresResource replaceGrupoDeInteres: output: {0}", calificacionDetailDTO);
        return calificacionDetailDTO;
    }
}

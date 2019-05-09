/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.resources;

import co.edu.uniandes.csw.idiomas.dtos.AdministradorDTO;
import co.edu.uniandes.csw.idiomas.dtos.CalificacionDetailDTO;
import co.edu.uniandes.csw.idiomas.ejb.AdministradorLogic;
import co.edu.uniandes.csw.idiomas.ejb.CalificacionAdministradorLogic;
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
 * Clase que implementa el recurso "administrador/{id}/calificaciones".
 *
 * @author jdruedaa
 */
@Path("calificaciones/{calificacionesId: \\d+}/administrador")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CalificacionAdministradorResource {

    private static final Logger LOGGER = Logger.getLogger(CalificacionAdministradorResource.class.getName());

    @Inject
    private CalificacionLogic calificacionLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @Inject
    private CalificacionAdministradorLogic calificacionAdministradorLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @Inject
    private AdministradorLogic administradorLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    /**
     * Remplaza la instancia de Administrador asociada a un Calificacion.
     *
     * @param calificacionesId Identificador de la calificacion que se esta actualizando. Este
     * debe ser una cadena de dígitos.
     * @param administrador La administrador que será de la calificacion.
     * @return JSON {@link CalificacionDetailDTO} - El arreglo de calificaciones guardado en la
     * administrador.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la administrador o la
     * calificacion.
     */
    @PUT
    public CalificacionDetailDTO replaceAdministrador(@PathParam("calificacionesId") Long calificacionesId, AdministradorDTO administrador) {
        LOGGER.log(Level.INFO, "CalificacionAdministradorResource replaceAdministrador: input: calificacionesId{0} , Administrador:{1}", new Object[]{calificacionesId, administrador});
        if (calificacionLogic.getCalificacion(calificacionesId) == null) {
            throw new WebApplicationException("El recurso /calificaciones/" + calificacionesId + " no existe.", 404);
        }
        if (administradorLogic.getAdministrador(administrador.getId()) == null) {
            throw new WebApplicationException("El recurso /administradors/" + administrador.getId() + " no existe.", 404);
        }
        CalificacionDetailDTO calificacionDetailDTO = new CalificacionDetailDTO(calificacionAdministradorLogic.replaceAdministrador(calificacionesId, administrador.getId()));
        LOGGER.log(Level.INFO, "CalificacionAdministradorResource replaceAdministrador: output: {0}", calificacionDetailDTO);
        return calificacionDetailDTO;
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.resources;

import co.edu.uniandes.csw.idiomas.dtos.ActividadDTO;
import co.edu.uniandes.csw.idiomas.dtos.ActividadDetailDTO;
import java.util.List;
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

/**
 * Clase que define los servicios de la clase actividad
 * @author g.cubillosb
 */

@Path("actividad")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class ActividadResource {
    
    // ------------------------------------------------------------------------
    // Atributos
    // ------------------------------------------------------------------------
    
    /**
     * Atributo que representa el logger correspondiente de la clase. Para poder
     * enviar mensajes.
     */
    private static final Logger LOGGER = Logger.getLogger(ActividadResource.class.getName());
    
//    @Inject
//    private ActividadLogic actividadLogic; 
// Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.
    
    // ------------------------------------------------------------------------
    // Constructor
    // ------------------------------------------------------------------------
    
    // ------------------------------------------------------------------------
    // Métodos
    // ------------------------------------------------------------------------
    
    /**
     * Crea una nueva actividad con la información que se recibe en el cuerpo 
     * de la petición y se regresa un objeto idéntico con un id auto-generado por
     * la base de datos.
     * 
     * @param actividad {@link ActividadDTO} - La actividad que se desea guardar
     * @return JSON {@link ActividadDTO} - La actividad guardada con el atributo
     * autogenerado
     */
    @POST
    public ActividadDTO createActividad(ActividadDTO actividad)
    {
        return actividad;
    }
    
    /**
     * Borra la actividad con el id asociado recibido en la URL
     * 
     * @param actividadId Identificador de la actividad que se desea borrar. Es
     * una cadena de dígitos
     */
    @DELETE
    @Path("{actividadId: \\d+}")
    public void deleteActvidad(@PathParam("actividadesId") Long actividadId)
    {
        LOGGER.log(Level.INFO, "ActividadResource deleteActividad: input: {0}", actividadId);
        /*if (actividadLogic.getEditorial(editorialsId) == null) {
            throw new WebApplicationException("El recurso /editorials/" + editorialsId + " no existe.", 404);
        }
        editorialLogic.deleteEditorial(editorialsId);*/
        LOGGER.info("ActividadResource deleteActividad: output: void");
    }
    
    /**
     * Actualiza la actividad con el id recibido en la URL con la información que
     * se recibe en el cuerpo de la petición.
     * 
     * @param actividadId Identificador de la actividad que se desea actualizar.
     * Es una cadena de dígitos.
     * @param actividad {@link ActividadDetailDTO} La actividad que se desea
     * guardar
     * @return JSON {@link EditorialsDetailDTO} - La editorial guardada 
     */
    @PUT
    @Path("{editorialsId: \\d+}")
    public ActividadDetailDTO updateEditorial(@PathParam("editorialsId") Long actividadId, ActividadDetailDTO actividad) //throws WebApplicationException 
    {
        LOGGER.log(Level.INFO, "EditorialResource updateEditorial: input: id:{0} , editorial: {1}", new Object[]{actividadId, actividad});
//        actividad.setId(actividadId);
//        if (editorialLogic.getEditorial(actividadId) == null) {
//            throw new WebApplicationException("El recurso /editorials/" + actividadId + " no existe.", 404);
//        }
//        EditorialDetailDTO detailDTO = new EditorialDetailDTO(editorialLogic.updateEditorial(actividadId, actividad.toEntity()));
//        LOGGER.log(Level.INFO, "EditorialResource updateEditorial: output: {0}", detailDTO);
        return actividad; // Falta poner el return adecuado.
    }
        
    /**
     * Busca y devuelve la información de una actividad específica
     * @return JSON {@link ActividadDetailDTO} - La actividad encontrada
     * en la aplicación
     */
    @GET
    public List<ActividadDetailDTO> getActividad() {
        LOGGER.info("ActividadResource getActividad: input: void");
//        List<ActividadDetailDTO> listaEditoriales = listEntity2DetailDTO(actividadLogic.getEditorials());
//        LOGGER.log(Level.INFO, "EditorialResource getEditorials: output: {0}", listaEditoriales);
//        return listaEditoriales;
        return null;
    }
    
}

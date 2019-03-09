/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.resources;



import co.edu.uniandes.csw.idiomas.dtos.AnfitrionDetailDTO;
import co.edu.uniandes.csw.idiomas.dtos.AnfitrionDTO;
import co.edu.uniandes.csw.idiomas.dtos.AnfitrionDetailDTO;
import co.edu.uniandes.csw.idiomas.dtos.AnfitrionDTO;
import co.edu.uniandes.csw.idiomas.dtos.AnfitrionDetailDTO;
import co.edu.uniandes.csw.idiomas.ejb.AnfitrionLogic;
import co.edu.uniandes.csw.idiomas.entities.AnfitrionEntity;
import co.edu.uniandes.csw.idiomas.entities.AnfitrionEntity;
import co.edu.uniandes.csw.idiomas.entities.AnfitrionEntity;
import co.edu.uniandes.csw.idiomas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.idiomas.mappers.BusinessLogicExceptionMapper;
import java.util.ArrayList;
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
import javax.ws.rs.WebApplicationException;

/**
 * Clase que implementa el recurso "anfitriones".
 *
 * @author ISIS2603
 * @version 1.0
 */
@Path("anfitriones")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class AnfitrionResource 
{
 private static final Logger LOGGER = Logger.getLogger(AnfitrionResource.class.getName());

    @Inject
    AnfitrionLogic anfitrionLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    /**
     * Crea una nueva anfitrion con la informacion que se recibe en el cuerpo de
     * la petición y se regresa un objeto identico con un id auto-generado por
     * la base de datos.
     *
     * @param anfitrion {@link AnfitrionDTO} - La anfitrion que se desea
     * guardar.
     * @return JSON {@link AnfitrionDTO} - La anfitrion guardada con el atributo
     * id autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe la anfitrion.
     */
    @POST
    public AnfitrionDTO  createAnfitrion(AnfitrionDTO anfitrion) throws BusinessLogicException {
       LOGGER.log(Level.INFO, "AnfitrionResource createAnfitrion: input: {0}", anfitrion);
        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        AnfitrionEntity anfitrionEntity = anfitrion.toEntity();
        // Invoca la lógica para crear la anfitrion nueva
        AnfitrionEntity nuevoAnfitrionEntity = anfitrionLogic.createAnfitrion(anfitrionEntity);
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        AnfitrionDTO nuevoAnfitrionDTO = new AnfitrionDTO(nuevoAnfitrionEntity);
        LOGGER.log(Level.INFO, "AnfitrionResource createAnfitrion: output: {0}", nuevoAnfitrionDTO);
        return nuevoAnfitrionDTO;
    }
/**
     * Borra la anfitrion con el id asociado recibido en la URL.
     *
     * @param anfitrionesId Identificador de la anfitrion que se desea borrar.
     * Este debe ser una cadena de dígitos.
     * @return 
     */
    @DELETE
    @Path("{anfitrionesId: \\d+}")
    public void deleteAnfitrion(@PathParam("anfitrionesId") Long anfitrionesId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "AnfitrionResource deleteAnfitrion: input: {0}", anfitrionesId);
        if (anfitrionLogic.getAnfitrion(anfitrionesId) == null) {
            throw new WebApplicationException("El recurso /anfitriones/" + anfitrionesId + " no existe.", 404);
        }
        anfitrionLogic.deleteAnfitrion(anfitrionesId);
        LOGGER.info("AnfitrionResource deleteAnfitrion: output: void");
    }
    
   /**
     * Actualiza el autor con el id recibido en la URL con la información que se
     * recibe en el cuerpo de la petición.
     *
     * @param AnfitrionId Identificador del autor que se desea actualizar. Este
     * debe ser una cadena de dígitos.
     * @param Anfitrion {@link AnfitrionDetailDTO} El autor que se desea guardar.
     * @return JSON {@link AnfitrionDetailDTO} - El autor guardado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el autor a
     * actualizar.
     */
    @PUT
    @Path("{AnfitrionId: \\d+}")
    public AnfitrionDetailDTO updateAnfitrion(@PathParam("AnfitrionId") Long AnfitrionId, AnfitrionDetailDTO Anfitrion) {
        LOGGER.log(Level.INFO, "AnfitrionResource updateAnfitrion: input: AnfitrionId: {0} , Anfitrion: {1}", new Object[]{AnfitrionId, Anfitrion.toString()});
        Anfitrion.setId(AnfitrionId);
        if (anfitrionLogic.getAnfitrion(AnfitrionId) == null)
        {
            throw new WebApplicationException("El recurso /Anfitriones/" + AnfitrionId + " no existe.", 404);
        }
        AnfitrionDetailDTO detailDTO = new AnfitrionDetailDTO(anfitrionLogic.updateAnfitrion(AnfitrionId, Anfitrion.toEntity()));
        LOGGER.log(Level.INFO, "AnfitrionResource updateAnfitrion: output: {0}", detailDTO.toString());
        return detailDTO;
    }
    
     /**
     * Actualiza la anfitrion con el id asociado recibido en la URL.
     *
     * @param anfitrionesId Identificador de la anfitrion que se desea actualizar.
     * Este debe ser una cadena de dígitos.
     * @return 
     */
    @GET
    @Path("{anfitrionesId: \\d+}")
    public AnfitrionDetailDTO retornarAnfitrion(@PathParam("anfitrionesId") Long anfitrionesId) {
        LOGGER.log(Level.INFO, "AnfitrionResource getAnfitrion: input: {0}", anfitrionesId);
        AnfitrionEntity anfitrionEntity = anfitrionLogic.getAnfitrion(anfitrionesId);
        if (anfitrionEntity == null) {
            throw new WebApplicationException("El recurso /anfitriones/" + anfitrionesId + " no existe.", 404);
        }
        AnfitrionDetailDTO detailDTO = new AnfitrionDetailDTO(anfitrionEntity);
        LOGGER.log(Level.INFO, "AnfitrionResource getAnfitrion: output: {0}", detailDTO);
        return detailDTO;
    }
    
      /**
     * Actualiza la anfitrion con el id asociado recibido en la URL.
     *
     * @param anfitrionesId Identificador de la anfitrion que se desea actualizar.
     * Este debe ser una cadena de dígitos.
     * @return 
     */
    @GET    
    public List<AnfitrionDetailDTO> retornarAnfitrion() {
        LOGGER.info("AnfitrionResource getAnfitrions: input: void");
        List<AnfitrionDetailDTO> listaAnfitrions = listEntity2DTO(anfitrionLogic.getAnfitrions());
        LOGGER.log(Level.INFO, "AnfitrionResource getAnfitrions: output: {0}", listaAnfitrions.toString());
        return listaAnfitrions;
               
    }   
      /**
     * Convierte una lista de AnfitrionEntity a una lista de AnfitrionDetailDTO.
     *
     * @param entityList Lista de AnfitrionEntity a convertir.
     * @return Lista de AnfitrionDetailDTO convertida.
     */
    private List<AnfitrionDetailDTO> listEntity2DTO(List<AnfitrionEntity> entityList) {
        List<AnfitrionDetailDTO> list = new ArrayList<>();
        for (AnfitrionEntity entity : entityList) {
            list.add(new AnfitrionDetailDTO(entity));
        }
        return list;
    }  
}

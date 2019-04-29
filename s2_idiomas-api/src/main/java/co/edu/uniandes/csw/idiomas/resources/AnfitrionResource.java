/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.resources;



import co.edu.uniandes.csw.idiomas.dtos.AnfitrionDTO;
import co.edu.uniandes.csw.idiomas.ejb.AnfitrionLogic;
import co.edu.uniandes.csw.idiomas.entities.AnfitrionEntity;
import co.edu.uniandes.csw.idiomas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.idiomas.mappers.BusinessLogicExceptionMapper;
import co.edu.uniandes.csw.idiomas.mappers.WebApplicationExceptionMapper;
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

    private static final String NO_EXISTE =" no existe." ;

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
    public AnfitrionDTO createAnfitrion(AnfitrionDTO anfitrion) throws BusinessLogicException {
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
            throw new WebApplicationException("El recurso /anfitriones/" + anfitrionesId + NO_EXISTE , 404);
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
     * @param Anfitrion {@link AnfitrionDTO} El autor que se desea guardar.
     * @return JSON {@link AnfitrionDTO} - El autor guardado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el autor a
     * actualizar.
     */
    @PUT
    @Path("{AnfitrionId: \\d+}")
    public AnfitrionDTO updateAnfitrion(@PathParam("AnfitrionId") Long anfitrionId, AnfitrionDTO anfitrion) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "AnfitrionResource updateAnfitrion: input: AnfitrionId: {0} , Anfitrion: {1}", new Object[]{anfitrionId, anfitrion});
        anfitrion.setId(anfitrionId);
        if (anfitrionLogic.getAnfitrion(anfitrionId) == null)
        {
            throw new WebApplicationException("El recurso /Anfitriones/" + anfitrionId +NO_EXISTE, 404);
        }
        AnfitrionDTO detailDTO = new AnfitrionDTO(anfitrionLogic.updateAnfitrion(anfitrionId, anfitrion.toEntity()));
        LOGGER.log(Level.INFO, "AnfitrionResource updateAnfitrion: output: {0}", detailDTO);
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
    public AnfitrionDTO retornarAnfitrion(@PathParam("anfitrionesId") Long anfitrionesId) {
        LOGGER.log(Level.INFO, "AnfitrionResource getAnfitrion: input: {0}", anfitrionesId);
        AnfitrionEntity anfitrionEntity = anfitrionLogic.getAnfitrion(anfitrionesId);
        if (anfitrionEntity == null) {
            throw new WebApplicationException("El recurso /anfitriones/" + anfitrionesId +NO_EXISTE, 404);
        }
        AnfitrionDTO detailDTO = new AnfitrionDTO(anfitrionEntity);
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
    public List<AnfitrionDTO> retornarAnfitrion() {
        LOGGER.info("AnfitrionResource getAnfitrions: input: void");
        List<AnfitrionDTO> listaAnfitrions = listEntity2DTO(anfitrionLogic.getAnfitrions());
        LOGGER.log(Level.INFO, "AnfitrionResource getAnfitrions: output: {0}", listaAnfitrions);
        return listaAnfitrions;
               
    }   
      /**
     * Convierte una lista de AnfitrionEntity a una lista de AnfitrionDTO.
     *
     * @param entityList Lista de AnfitrionEntity a convertir.
     * @return Lista de AnfitrionDTO convertida.
     */
    private List<AnfitrionDTO> listEntity2DTO(List<AnfitrionEntity> entityList) {
        List<AnfitrionDTO> list = new ArrayList<>();
        for (AnfitrionEntity entity : entityList) {
            list.add(new AnfitrionDTO(entity));
        }
        return list;
    }
}

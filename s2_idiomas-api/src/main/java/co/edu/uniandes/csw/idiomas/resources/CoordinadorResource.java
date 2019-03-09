/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.resources;

import co.edu.uniandes.csw.idiomas.dtos.CoordinadorDTO;
import co.edu.uniandes.csw.idiomas.dtos.CoordinadorDetailDTO;
import co.edu.uniandes.csw.idiomas.dtos.CoordinadorDTO;
import co.edu.uniandes.csw.idiomas.dtos.CoordinadorDetailDTO;
import co.edu.uniandes.csw.idiomas.ejb.CoordinadorLogic;
import co.edu.uniandes.csw.idiomas.ejb.CoordinadorLogic;
import co.edu.uniandes.csw.idiomas.entities.CoordinadorEntity;
import co.edu.uniandes.csw.idiomas.entities.CoordinadorEntity;
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
 * Clase que implementa el recurso "coordinadores".
 *
 * @author ISIS2603
 * @version 1.0
 */
@Path("coordinadores")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class CoordinadorResource 
{
 private static final Logger LOGGER = Logger.getLogger(CoordinadorResource.class.getName());
   

    @Inject
    CoordinadorLogic coordinadorLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    /**
     * Crea una nueva coordinador con la informacion que se recibe en el cuerpo de
     * la petición y se regresa un objeto identico con un id auto-generado por
     * la base de datos.
     *
     * @param coordinador {@link CoordinadorDTO} - La coordinador que se desea
     * guardar.
     * @return JSON {@link CoordinadorDTO} - La coordinador guardada con el atributo
     * id autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe la coordinador.
     */
    @POST
    public CoordinadorDTO  createCoordinador(CoordinadorDTO coordinador) throws BusinessLogicException {
       LOGGER.log(Level.INFO, "CoordinadorResource createCoordinador: input: {0}", coordinador);
        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        CoordinadorEntity coordinadorEntity = coordinador.toEntity();
        // Invoca la lógica para crear la coordinador nueva
        CoordinadorEntity nuevoCoordinadorEntity = coordinadorLogic.createCoordinador(coordinadorEntity);
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        CoordinadorDTO nuevoCoordinadorDTO = new CoordinadorDTO(nuevoCoordinadorEntity);
        LOGGER.log(Level.INFO, "CoordinadorResource createCoordinador: output: {0}", nuevoCoordinadorDTO);
        return nuevoCoordinadorDTO;
    }
/**
     * Borra la coordinador con el id asociado recibido en la URL.
     *
     * @param coordinadoresId Identificador de la coordinador que se desea borrar.
     * Este debe ser una cadena de dígitos.
     * @return 
     */
    @DELETE
    @Path("{coordinadoresId: \\d+}")
    public void deleteCoordinador(@PathParam("coordinadoresId") Long coordinadoresId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "CoordinadorResource deleteCoordinador: input: {0}", coordinadoresId);
        if (coordinadorLogic.getCoordinador(coordinadoresId) == null) {
            throw new WebApplicationException("El recurso /coordinadores/" + coordinadoresId + " no existe.", 404);
        }
        coordinadorLogic.deleteCoordinador(coordinadoresId);
        LOGGER.info("CoordinadorResource deleteCoordinador: output: void");
    }
    
   /**
     * Actualiza el autor con el id recibido en la URL con la información que se
     * recibe en el cuerpo de la petición.
     *
     * @param CoordinadorId Identificador del autor que se desea actualizar. Este
     * debe ser una cadena de dígitos.
     * @param Coordinador {@link CoordinadorDetailDTO} El autor que se desea guardar.
     * @return JSON {@link CoordinadorDetailDTO} - El autor guardado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el autor a
     * actualizar.
     */
    @PUT
    @Path("{CoordinadorId: \\d+}")
    public CoordinadorDetailDTO updateCoordinador(@PathParam("CoordinadorId") Long CoordinadorId, CoordinadorDetailDTO Coordinador) {
        LOGGER.log(Level.INFO, "CoordinadorResource updateCoordinador: input: CoordinadorId: {0} , Coordinador: {1}", new Object[]{CoordinadorId, Coordinador.toString()});
        Coordinador.setId(CoordinadorId);
        if (coordinadorLogic.getCoordinador(CoordinadorId) == null)
        {
            throw new WebApplicationException("El recurso /Coordinadores/" + CoordinadorId + " no existe.", 404);
        }
        CoordinadorDetailDTO detailDTO = new CoordinadorDetailDTO(coordinadorLogic.updateCoordinador(CoordinadorId, Coordinador.toEntity()));
        LOGGER.log(Level.INFO, "CoordinadorResource updateCoordinador: output: {0}", detailDTO.toString());
        return detailDTO;
    }
    
     /**
     * Actualiza la coordinador con el id asociado recibido en la URL.
     *
     * @param coordinadoresId Identificador de la coordinador que se desea actualizar.
     * Este debe ser una cadena de dígitos.
     * @return 
     */
    @GET
    @Path("{coordinadoresId: \\d+}")
    public CoordinadorDetailDTO retornarCoordinador(@PathParam("coordinadoresId") Long coordinadoresId) {
        LOGGER.log(Level.INFO, "CoordinadorResource getCoordinador: input: {0}", coordinadoresId);
        CoordinadorEntity coordinadorEntity = coordinadorLogic.getCoordinador(coordinadoresId);
        if (coordinadorEntity == null) {
            throw new WebApplicationException("El recurso /coordinadores/" + coordinadoresId + " no existe.", 404);
        }
        CoordinadorDetailDTO detailDTO = new CoordinadorDetailDTO(coordinadorEntity);
        LOGGER.log(Level.INFO, "CoordinadorResource getCoordinador: output: {0}", detailDTO);
        return detailDTO;
    }
    
      /**
     * Actualiza la coordinador con el id asociado recibido en la URL.
     *
     * @param coordinadoresId Identificador de la coordinador que se desea actualizar.
     * Este debe ser una cadena de dígitos.
     * @return 
     */
    @GET    
    public List<CoordinadorDetailDTO> retornarCoordinador() {
        LOGGER.info("CoordinadorResource getCoordinadors: input: void");
        List<CoordinadorDetailDTO> listaCoordinadors = listEntity2DTO(coordinadorLogic.getCoordinadors());
        LOGGER.log(Level.INFO, "CoordinadorResource getCoordinadors: output: {0}", listaCoordinadors.toString());
        return listaCoordinadors;
               
    }   
      /**
     * Convierte una lista de CoordinadorEntity a una lista de CoordinadorDetailDTO.
     *
     * @param entityList Lista de CoordinadorEntity a convertir.
     * @return Lista de CoordinadorDetailDTO convertida.
     */
    private List<CoordinadorDetailDTO> listEntity2DTO(List<CoordinadorEntity> entityList) {
        List<CoordinadorDetailDTO> list = new ArrayList<>();
        for (CoordinadorEntity entity : entityList) {
            list.add(new CoordinadorDetailDTO(entity));
        }
        return list;
    }
}

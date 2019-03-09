/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.resources;

import co.edu.uniandes.csw.idiomas.dtos.AdministradorDTO;
import co.edu.uniandes.csw.idiomas.dtos.AdministradorDetailDTO;
import co.edu.uniandes.csw.idiomas.ejb.AdministradorLogic;
import co.edu.uniandes.csw.idiomas.entities.AdministradorEntity;
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
 * Clase que implementa el recurso "personas".
 *
 * @author ISIS2603
 * @version 1.0
 */
@Path("personas")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class PersonaResource {

    private static final Logger LOGGER = Logger.getLogger(AdministradorResource.class.getName());
     private static final String NO_EXISTE =" no existe." ;
    @Inject
    AdministradorLogic administradorLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    /**
     * Crea una nueva administrador con la informacion que se recibe en el cuerpo de
     * la petición y se regresa un objeto identico con un id auto-generado por
     * la base de datos.
     *
     * @param administrador {@link AdministradorDTO} - La administrador que se desea
     * guardar.
     * @return JSON {@link AdministradorDTO} - La administrador guardada con el atributo
     * id autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe la administrador.
     */
    @POST
    public AdministradorDTO createAdministrador(AdministradorDTO administrador) throws BusinessLogicException {
       LOGGER.log(Level.INFO, "AdministradorResource createAdministrador: input: {0}", administrador);
        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        AdministradorEntity administradorEntity = administrador.toEntity();
        // Invoca la lógica para crear la administrador nueva
        AdministradorEntity nuevoAdministradorEntity = administradorLogic.createAdministrador(administradorEntity);
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        AdministradorDTO nuevoAdministradorDTO = new AdministradorDTO(nuevoAdministradorEntity);
        LOGGER.log(Level.INFO, "AdministradorResource createAdministrador: output: {0}", nuevoAdministradorDTO);
        return nuevoAdministradorDTO;
    }

    /**
     * Borra la administrador con el id asociado recibido en la URL.
     *
     * @param administradoresId Identificador de la administrador que se desea borrar.
     * Este debe ser una cadena de dígitos.
     * @return 
     */
    @DELETE
    @Path("{administradoresId: \\d+}")
    public void deleteAdministrador(@PathParam("administradoresId") Long administradoresId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "AdministradorResource deleteAdministrador: input: {0}", administradoresId);
        if (administradorLogic.getAdministrador(administradoresId) == null) {
            throw new WebApplicationException("El recurso /administradores/" + administradoresId +NO_EXISTE, 404);
        }
        administradorLogic.deleteAdministrador(administradoresId);
        LOGGER.info("AdministradorResource deleteAdministrador: output: void");
    }
    
   /**
     * Actualiza el autor con el id recibido en la URL con la información que se
     * recibe en el cuerpo de la petición.
     *
     * @param AdministradorId Identificador del autor que se desea actualizar. Este
     * debe ser una cadena de dígitos.
     * @param Administrador {@link AdministradorDetailDTO} El autor que se desea guardar.
     * @return JSON {@link AdministradorDetailDTO} - El autor guardado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el autor a
     * actualizar.
     */
    @PUT
    @Path("{AdministradorId: \\d+}")
    public AdministradorDetailDTO updateAdministrador(@PathParam("AdministradorId") Long administradorId, AdministradorDetailDTO administrador) {
        LOGGER.log(Level.INFO, "AdministradorResource updateAdministrador: input: AdministradorId: {0} , Administrador: {1}", new Object[]{administradorId, administrador});
        administrador.setId(administradorId);
        if (administradorLogic.getAdministrador(administradorId) == null)
        {
            throw new WebApplicationException("El recurso /Administradores/" + administradorId +NO_EXISTE, 404);
        }
        AdministradorDetailDTO detailDTO = new AdministradorDetailDTO(administradorLogic.updateAdministrador(administradorId, administrador.toEntity()));
        LOGGER.log(Level.INFO, "AdministradorResource updateAdministrador: output: {0}", detailDTO);
        return detailDTO;
    }
    
     /**
     * Actualiza la administrador con el id asociado recibido en la URL.
     *
     * @param administradoresId Identificador de la administrador que se desea actualizar.
     * Este debe ser una cadena de dígitos.
     * @return 
     */
    @GET
    @Path("{administradoresId: \\d+}")
    public AdministradorDetailDTO retornarAdministrador(@PathParam("administradoresId") Long administradoresId) {
        LOGGER.log(Level.INFO, "AdministradorResource getAdministrador: input: {0}", administradoresId);
        AdministradorEntity administradorEntity = administradorLogic.getAdministrador(administradoresId);
        if (administradorEntity == null) {
            throw new WebApplicationException("El recurso /administradores/" + administradoresId +NO_EXISTE, 404);
        }
        AdministradorDetailDTO detailDTO = new AdministradorDetailDTO(administradorEntity);
        LOGGER.log(Level.INFO, "AdministradorResource getAdministrador: output: {0}", detailDTO);
        return detailDTO;
    }
    
      /**
     * Actualiza la administrador con el id asociado recibido en la URL.
     *
     * @param administradoresId Identificador de la administrador que se desea actualizar.
     * Este debe ser una cadena de dígitos.
     * @return 
     */
    @GET    
    public List<AdministradorDetailDTO> retornarAdministrador() {
        LOGGER.info("AdministradorResource getAdministradors: input: void");
        List<AdministradorDetailDTO> listaAdministradors = listEntity2DTO(administradorLogic.getAdministradors());
        LOGGER.log(Level.INFO, "AdministradorResource getAdministradors: output: {0}", listaAdministradors);
        return listaAdministradors;
               
    }   
      /**
     * Convierte una lista de AdministradorEntity a una lista de AdministradorDetailDTO.
     *
     * @param entityList Lista de AdministradorEntity a convertir.
     * @return Lista de AdministradorDetailDTO convertida.
     */
    private List<AdministradorDetailDTO> listEntity2DTO(List<AdministradorEntity> entityList) {
        List<AdministradorDetailDTO> list = new ArrayList<>();
        for (AdministradorEntity entity : entityList) {
            list.add(new AdministradorDetailDTO(entity));
        }
        return list;
    }
}

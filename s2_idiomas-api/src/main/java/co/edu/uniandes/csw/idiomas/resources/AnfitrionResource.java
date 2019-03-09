/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.resources;



import co.edu.uniandes.csw.idiomas.dtos.AdministradorDetailDTO;
import co.edu.uniandes.csw.idiomas.dtos.AnfitrionDTO;
import co.edu.uniandes.csw.idiomas.dtos.AnfitrionDetailDTO;
//import co.edu.uniandes.csw.idiomas.ejb.AnfitrionLogic;
//import co.edu.uniandes.csw.idiomas.entities.AnfitrionEntity;
import co.edu.uniandes.csw.idiomas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.idiomas.mappers.BusinessLogicExceptionMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
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

//    @Inject
//    AnfitrionLogic anfitrionLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

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
    public AnfitrionDetailDTO  createAnfitrion(AnfitrionDTO anfitrion) throws BusinessLogicException {
        AnfitrionDetailDTO  nuevo = new AnfitrionDetailDTO();
        return nuevo;
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
    public AnfitrionDetailDTO deleteAnfitrion(@PathParam("anfitrionesId") Long anfitrionesId) {
        AnfitrionDetailDTO  nuevo = new AnfitrionDetailDTO();
        return nuevo;
    }
    
    /**
     * Actualiza la anfitrion con el id asociado recibido en la URL.
     *
     * @param anfitrionesId Identificador de la anfitrion que se desea actualizar.
     * Este debe ser una cadena de dígitos.
     * @return 
     */
    @PUT
    @Path("{anfitrionesId: \\d+}")
    public AnfitrionDetailDTO updateAnfitrion(@PathParam("anfitrionesId") Long anfitrionesId) {
       AnfitrionDetailDTO  nuevo = new AnfitrionDetailDTO();
       if(nuevo == null)
        {
            throw new WebApplicationException();
        }
        return nuevo;
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
        AnfitrionDetailDTO  nuevo = new AnfitrionDetailDTO();
        return nuevo;
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
         List<AnfitrionDetailDTO> list = new ArrayList<>();
         if(list == null)
        {
            throw new WebApplicationException();
        }
        return list;         
    }   
}

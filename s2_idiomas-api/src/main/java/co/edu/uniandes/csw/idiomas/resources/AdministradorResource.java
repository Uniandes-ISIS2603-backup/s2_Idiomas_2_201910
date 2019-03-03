package co.edu.uniandes.csw.idiomas.resources;

import co.edu.uniandes.csw.idiomas.dtos.AdministradorDTO;
import co.edu.uniandes.csw.idiomas.dtos.AdministradorDetailDTO;
//import co.edu.uniandes.csw.idiomas.ejb.AdministradorLogic;
//import co.edu.uniandes.csw.idiomas.entities.AdministradorEntity;
import co.edu.uniandes.csw.idiomas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.idiomas.mappers.BusinessLogicExceptionMapper;
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

/**
 * Clase que implementa el recurso "administradores".
 *
 * @author ISIS2603
 * @version 1.0
 */
@Path("administradores")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped


/**
 *
 * @author estudiante
 */
public class AdministradorResource 
{
 private static final Logger LOGGER = Logger.getLogger(AdministradorResource.class.getName());

//    @Inject
//    AdministradorLogic administradorLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

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
    public AdministradorDetailDTO createAdministrador(AdministradorDTO administrador) throws BusinessLogicException {
       AdministradorDetailDTO  retorno = new AdministradorDetailDTO();
        return retorno;
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
    public AdministradorDetailDTO deleteAdministrador(@PathParam("administradoresId") Long administradoresId) {
        AdministradorDetailDTO  retorno = new AdministradorDetailDTO();
        return retorno;
    }
    
    /**
     * Actualiza la administrador con el id asociado recibido en la URL.
     *
     * @param administradoresId Identificador de la administrador que se desea actualizar.
     * Este debe ser una cadena de dígitos.
     * @return 
     */
    @PUT
    @Path("{administradoresId: \\d+}")
    public AdministradorDetailDTO updateAdministrador(@PathParam("administradoresId") Long administradoresId) {
         AdministradorDetailDTO  retorno = new AdministradorDetailDTO();
        return retorno;
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
         AdministradorDetailDTO  retorno = new AdministradorDetailDTO();
        return retorno;
    }
    
      /**
     * Actualiza la administrador con el id asociado recibido en la URL.
     *
     * @param administradoresId Identificador de la administrador que se desea actualizar.
     * Este debe ser una cadena de dígitos.
     * @return 
     */
    @GET    
    public AdministradorDetailDTO[] retornarAdministrador() {
        return new AdministradorDetailDTO[1];        
    }   
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.resources;



import co.edu.uniandes.csw.idiomas.dtos.PersonaDTO;
import co.edu.uniandes.csw.idiomas.dtos.AnfitrionDetailDTO;
import co.edu.uniandes.csw.idiomas.dtos.UsuarioDTO;
import co.edu.uniandes.csw.idiomas.dtos.UsuarioDetailDTO;
import co.edu.uniandes.csw.idiomas.ejb.PersonaLogic;
import co.edu.uniandes.csw.idiomas.ejb.UsuarioLogic;
import co.edu.uniandes.csw.idiomas.entities.PersonaEntity;
import co.edu.uniandes.csw.idiomas.entities.UsuarioEntity;
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
 * Clase que implementa el recurso "usuarios".
 *
 * @author ISIS2603
 * @version 1.0
 */
@Path("usuarios")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class UsuarioResource
{
    private static final Logger LOGGER = Logger.getLogger(UsuarioResource.class.getName());
     private final String NO_EXISTE =" no existe." ;

    @Inject
    PersonaLogic personaLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    /**
     * Crea una nueva persona con la informacion que se recibe en el cuerpo de
     * la petición y se regresa un objeto identico con un id auto-generado por
     * la base de datos.
     *
     * @param persona {@link PersonaDTO} - La persona que se desea
     * guardar.
     * @return JSON {@link PersonaDTO} - La persona guardada con el atributo
     * id autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe la persona.
     */
    @POST
    public PersonaDTO createPersona(PersonaDTO persona) throws BusinessLogicException {
       LOGGER.log(Level.INFO, "PersonaResource createPersona: input: {0}", persona);
        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        PersonaEntity personaEntity = persona.toEntity();
        // Invoca la lógica para crear la persona nueva
        PersonaEntity nuevoPersonaEntity = personaLogic.createPersona(personaEntity);
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        PersonaDTO nuevoPersonaDTO = new PersonaDTO(nuevoPersonaEntity);
        LOGGER.log(Level.INFO, "PersonaResource createPersona: output: {0}", nuevoPersonaDTO);
        return nuevoPersonaDTO;
    }

    /**
     * Borra la persona con el id asociado recibido en la URL.
     *
     * @param personaesId Identificador de la persona que se desea borrar.
     * Este debe ser una cadena de dígitos.
     * @return 
     */
    @DELETE
    @Path("{personaesId: \\d+}")
    public void deletePersona(@PathParam("personaesId") Long personaesId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "PersonaResource deletePersona: input: {0}", personaesId);
        if (personaLogic.getPersona(personaesId) == null) {
            throw new WebApplicationException("El recurso /personaes/" + personaesId + NO_EXISTE, 404);
        }
        personaLogic.deletePersona(personaesId);
        LOGGER.info("PersonaResource deletePersona: output: void");
    }
    
   /**
     * Actualiza el autor con el id recibido en la URL con la información que se
     * recibe en el cuerpo de la petición.
     *
     * @param PersonaId Identificador del autor que se desea actualizar. Este
     * debe ser una cadena de dígitos.
     * @param Persona {@link PersonaDTO} El autor que se desea guardar.
     * @return JSON {@link PersonaDTO} - El autor guardado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el autor a
     * actualizar.
     */
    @PUT
    @Path("{PersonaId: \\d+}")
    public PersonaDTO updatePersona(@PathParam("PersonaId") Long PersonaId, PersonaDTO Persona) {
        LOGGER.log(Level.INFO, "PersonaResource updatePersona: input: PersonaId: {0} , Persona: {1}", new Object[]{PersonaId, Persona});
        Persona.setId(PersonaId);
        if (personaLogic.getPersona(PersonaId) == null)
        {
            throw new WebApplicationException("El recurso /Personaes/" + PersonaId + NO_EXISTE, 404);
        }
        PersonaDTO detailDTO = new PersonaDTO(personaLogic.updatePersona(PersonaId, Persona.toEntity()));
        LOGGER.log(Level.INFO, "PersonaResource updatePersona: output: {0}", detailDTO);
        return detailDTO;
    }
    
     /**
     * Actualiza la persona con el id asociado recibido en la URL.
     *
     * @param personaesId Identificador de la persona que se desea actualizar.
     * Este debe ser una cadena de dígitos.
     * @return 
     */
    @GET
    @Path("{personaesId: \\d+}")
    public PersonaDTO retornarPersona(@PathParam("personaesId") Long personaesId) {
        LOGGER.log(Level.INFO, "PersonaResource getPersona: input: {0}", personaesId);
        PersonaEntity personaEntity = personaLogic.getPersona(personaesId);
        if (personaEntity == null) {
            throw new WebApplicationException("El recurso /personaes/" + personaesId + NO_EXISTE, 404);
        }
        PersonaDTO detailDTO = new PersonaDTO(personaEntity);
        LOGGER.log(Level.INFO, "PersonaResource getPersona: output: {0}", detailDTO);
        return detailDTO;
    }
    
      /**
     * Actualiza la persona con el id asociado recibido en la URL.
     *
     * @param personaesId Identificador de la persona que se desea actualizar.
     * Este debe ser una cadena de dígitos.
     * @return 
     */
    @GET    
    public List<PersonaDTO> retornarPersona() {
        LOGGER.info("PersonaResource getPersonas: input: void");
        List<PersonaDTO> listaPersonas = listEntity2DTO(personaLogic.getPersonas());
        LOGGER.log(Level.INFO, "PersonaResource getPersonas: output: {0}", listaPersonas);
        return listaPersonas;
               
    }   
      /**
     * Convierte una lista de PersonaEntity a una lista de PersonaDTO.
     *
     * @param entityList Lista de PersonaEntity a convertir.
     * @return Lista de PersonaDTO convertida.
     */
    private List<PersonaDTO> listEntity2DTO(List<PersonaEntity> entityList) {
        List<PersonaDTO> list = new ArrayList<>();
        for (PersonaEntity entity : entityList) {
            list.add(new PersonaDTO(entity));
        }
        return list;
    }
}

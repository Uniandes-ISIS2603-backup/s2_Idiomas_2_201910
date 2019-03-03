/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.resources;

import co.edu.uniandes.csw.idiomas.dtos.PersonaDTO;
//import co.edu.uniandes.csw.idiomas.ejb.PersonaLogic;
//import co.edu.uniandes.csw.idiomas.entities.PersonaEntity;
import co.edu.uniandes.csw.idiomas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.idiomas.mappers.BusinessLogicExceptionMapper;
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

    private static final Logger LOGGER = Logger.getLogger(PersonaResource.class.getName());

//    @Inject
//    PersonaLogic personaLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

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
       
        PersonaDTO personax = new PersonaDTO();
        personax.setNombre("JUAN");
        return personax;
    }

    /**
     * Borra la persona con el id asociado recibido en la URL.
     *
     * @param personasId Identificador de la persona que se desea borrar.
     * Este debe ser una cadena de dígitos.
     * @return 
     */
    @DELETE
    @Path("{personasId: \\d+}")
    public PersonaDTO deletePersona(@PathParam("personasId") Long personasId) {
        PersonaDTO persona = new PersonaDTO();
        persona.setNombre("JUAN");
        return persona;
    }
    
    /**
     * Actualiza la persona con el id asociado recibido en la URL.
     *
     * @param personasId Identificador de la persona que se desea actualizar.
     * Este debe ser una cadena de dígitos.
     * @return 
     */
    @PUT
    @Path("{personasId: \\d+}")
    public PersonaDTO updatePersona(@PathParam("personasId") Long personasId) {
        PersonaDTO persona = new PersonaDTO();
        persona.setNombre("JUAN");
        return persona; 
    }
    
     /**
     * Retorna la persona con el id asociado recibido en la URL.
     *
     * @param personasId Identificador de la persona que se desea actualizar.
     * Este debe ser una cadena de dígitos.
     * @return 
     */
    @GET
    @Path("{personasId: \\d+}")
    public PersonaDTO retornarPersona(@PathParam("personasId") Long personasId) {      
        PersonaDTO persona = new PersonaDTO();
        persona.setNombre("JUAN");
        return persona; 
    }
    
     /**
      * Retorna la lista de peronas
      * @return 
      */
    @GET    
    public PersonaDTO[] retornarPersona()
    {
        PersonaDTO[] a = new PersonaDTO[1];
        PersonaDTO persona = new PersonaDTO();
        persona.setNombre("JUAN");
        a[0] = persona;
        return a;        
    }
}

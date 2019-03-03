/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.resources;

import co.edu.uniandes.csw.idiomas.dtos.CoordinadorDTO;
import co.edu.uniandes.csw.idiomas.dtos.CoordinadorDetailDTO;
//import co.edu.uniandes.csw.idiomas.ejb.CoordinadorLogic;
//import co.edu.uniandes.csw.idiomas.entities.CoordinadorEntity;
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

//    @Inject
//    CoordinadorLogic coordinadorLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

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
    public CoordinadorDetailDTO createCoordinador(CoordinadorDTO coordinador) throws BusinessLogicException {
        CoordinadorDetailDTO nuevo = new CoordinadorDetailDTO();
        return nuevo;
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
    public CoordinadorDetailDTO deleteCoordinador(@PathParam("coordinadoresId") Long coordinadoresId) {
       CoordinadorDetailDTO nuevo = new CoordinadorDetailDTO();
        return nuevo;
    }
    
    /**
     * Actualiza la coordinador con el id asociado recibido en la URL.
     *
     * @param coordinadoresId Identificador de la coordinador que se desea actualizar.
     * Este debe ser una cadena de dígitos.
     * @return 
     */
    @PUT
    @Path("{coordinadoresId: \\d+}")
    public CoordinadorDetailDTO updateCoordinador(@PathParam("coordinadoresId") Long coordinadoresId) {
        CoordinadorDetailDTO nuevo = new CoordinadorDetailDTO();
         if(nuevo == null)
        {
            throw new WebApplicationException();
        }
        return nuevo;
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
        CoordinadorDetailDTO nuevo = new CoordinadorDetailDTO();
        return nuevo;
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
        
        List<CoordinadorDetailDTO> list = new ArrayList<>();
        
        if(list == null)
        {
            throw new WebApplicationException();
        }
        return list;        
    }   
}

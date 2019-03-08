/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.resources;

import co.edu.uniandes.csw.idiomas.dtos.EstadiaDTO;
import co.edu.uniandes.csw.idiomas.ejb.EstadiaLogic;
import co.edu.uniandes.csw.idiomas.entities.EstadiaEntity;
import co.edu.uniandes.csw.idiomas.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Clase que define los servicios de la clase estadia
 * @author g.cubillosb
 */
@Path("estadia")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class EstadiaResource {
    
    // ------------------------------------------------------------------------
    // Atributos
    // ------------------------------------------------------------------------
    
    /**
     * Atributo que representa el logger correspondiente de la clase. Para poder
     * enviar mensajes.
     */
    private static final Logger LOGGER = Logger.getLogger(EstadiaResource.class.getName());
    
    /**
     * Permite acceder a la lógica de la aplicación. Es una inyección de dependencias.
     */
    @Inject
    private EstadiaLogic estadiaLogic; 
    
    // ------------------------------------------------------------------------
    // Constructor
    // ------------------------------------------------------------------------
    
    // ------------------------------------------------------------------------
    // Métodos
    // ------------------------------------------------------------------------
    
    /**
     * Crea una nueva estadia con la informacion que se recibe en el cuerpo de
     * la petición y se regresa un objeto identico con un id auto-generado por
     * la base de datos.
     *
     * @param estadia {@link EstadiaDTO} - La estadia que se desea
     * guardar.
     * @return JSON {@link EstadiaDTO} - La estadia guardada con el atributo
     * id autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe la estadia.
     */
    @POST
    public EstadiaDTO createEstadia(EstadiaDTO estadia) throws BusinessLogicException 
    {
        LOGGER.log(Level.INFO, "EstadiaResource createEstadia: input: {0}", estadia);
        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        EstadiaEntity estadiaEntity = estadia.toEntity();
        // Invoca la lógica para crear la estadia nueva
        EstadiaEntity nuevoEstadiaEntity = estadiaLogic.createEstadia(estadiaEntity);
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        EstadiaDTO nuevoEstadiaDTO = new EstadiaDTO(nuevoEstadiaEntity);
        LOGGER.log(Level.INFO, "EstadiaResource createEstadia: output: {0}", nuevoEstadiaDTO);
        return nuevoEstadiaDTO;
    }

    /**
     * Busca y devuelve todas las estadias que existen en la aplicacion.
     *
     * @return JSONArray {@link EstadiaDetailDTO} - Las estadias
     * encontradas en la aplicación. Si no hay ninguna retorna una lista vacía.
     */
    @GET
    public List<EstadiaDTO> getEstadias() {
        LOGGER.info("EstadiaResource getEstadias: input: void");
        List<EstadiaDTO> listaEstadias = listEntity2DetailDTO(estadiaLogic.getEstadias());
        LOGGER.log(Level.INFO, "EstadiaResource getEstadias: output: {0}", listaEstadias);
        return listaEstadias;
    }

    /**
     * Busca la estadia con el id asociado recibido en la URL y la devuelve.
     *
     * @param estadiasId Identificador de la estadia que se esta buscando.
     * Este debe ser una cadena de dígitos.
     * @return JSON {@link EstadiaDetailDTO} - La estadia buscada
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la estadia.
     */
    @GET
    @Path("{estadiasId: \\d+}")
    public EstadiaDTO getEstadia(@PathParam("estadiasId") Long estadiasId)
    {
        LOGGER.log(Level.INFO, "EstadiaResource getEstadia: input: {0}", estadiasId);
        EstadiaEntity estadiaEntity = estadiaLogic.getEstadia(estadiasId);
        if (estadiaEntity == null) {
            throw new WebApplicationException("El recurso /estadias/" + estadiasId + " no existe.", 404);
        }
        EstadiaDTO detailDTO = new EstadiaDTO(estadiaEntity);
        LOGGER.log(Level.INFO, "EstadiaResource getEstadia: output: {0}", detailDTO);
        return detailDTO;
    }

    /**
     * Actualiza la estadia con el id recibido en la URL con la informacion
     * que se recibe en el cuerpo de la petición.
     *
     * @param estadiasId Identificador de la estadia que se desea
     * actualizar. Este debe ser una cadena de dígitos.
     * @param estadia {@link EstadiaDetailDTO} La estadia que se desea
     * guardar.
     * @return JSON {@link EstadiaDetailDTO} - La estadia guardada.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la estadia a
     * actualizar.
     */
    @PUT
    @Path("{estadiasId: \\d+}")
<<<<<<< Updated upstream
    public EstadiaDTO updateEstadia(@PathParam("estadiasId") Long estadiasId, EstadiaDTO estadia) throws BusinessLogicException 
=======
    public EstadiaDTO updateEstadia(@PathParam("estadiasId") Long estadiasId, EstadiaDTO estadia) 
>>>>>>> Stashed changes
    {
        LOGGER.log(Level.INFO, "EstadiaResource updateEstadia: input: id:{0} , estadia: {1}", new Object[]{estadiasId, estadia});
        estadia.setId(estadiasId);
        if (estadiaLogic.getEstadia(estadiasId) == null) {
            throw new WebApplicationException("El recurso /estadias/" + estadiasId + " no existe.", 404);
        }
        EstadiaDTO detailDTO = new EstadiaDTO(estadiaLogic.updateEstadia(estadiasId, estadia.toEntity()));
        LOGGER.log(Level.INFO, "EstadiaResource updateEstadia: output: {0}", detailDTO);
        return detailDTO;

    }

    /**
     * Borra la estadia con el id asociado recibido en la URL.
     *
     * @param estadiasId Identificador de la estadia que se desea borrar.
     * Este debe ser una cadena de dígitos.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se puede eliminar la estadia.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la estadia.
     */
    @DELETE
    @Path("{estadiasId: \\d+}")
    public void deleteEstadia(@PathParam("estadiasId") Long estadiasId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "EstadiaResource deleteEstadia: input: {0}", estadiasId);
        if (estadiaLogic.getEstadia(estadiasId) == null) {
            throw new WebApplicationException("El recurso /estadias/" + estadiasId + " no existe.", 404);
        }
        estadiaLogic.deleteEstadia(estadiasId);
        LOGGER.info("EstadiaResource deleteEstadia: output: void");
    }

    // TODO: GC Conectar Estadias con Comentarios y las otras clases.
//    /**
//     * Conexión con el servicio de libros para una estadia.
//     * {@link EstadiaBooksResource}
//     *
//     * Este método conecta la ruta de /estadias con las rutas de /books que
//     * dependen de la estadia, es una redirección al servicio que maneja el
//     * segmento de la URL que se encarga de los libros de una estadia.
//     *
//     * @param estadiasId El ID de la estadia con respecto a la cual se
//     * accede al servicio.
//     * @return El servicio de libros para esta estadia en paricular.
//     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
//     * Error de lógica que se genera cuando no se encuentra la estadia.
//     */
//    @Path("{estadiasId: \\d+}/books")
//    public Class<EstadiaBooksResource> getEstadiaBooksResource(@PathParam("estadiasId") Long estadiasId) {
//        if (estadiaLogic.getEstadia(estadiasId) == null) {
//            throw new WebApplicationException("El recurso /estadias/" + estadiasId + " no existe.", 404);
//        }
//        return EstadiaBooksResource.class;
//    }

    /**
     * Convierte una lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos EstadiaEntity a una lista de
     * objetos EstadiaDetailDTO (json)
     *
     * @param entityList corresponde a la lista de estadias de tipo Entity
     * que vamos a convertir a DTO.
     * @return la lista de estadias en forma DTO (json)
     */
    private List<EstadiaDTO> listEntity2DetailDTO(List<EstadiaEntity> entityList) {
        List<EstadiaDTO> list = new ArrayList<>();
        for (EstadiaEntity entity : entityList) {
            list.add(new EstadiaDTO(entity));
        }
        return list;
    }
}

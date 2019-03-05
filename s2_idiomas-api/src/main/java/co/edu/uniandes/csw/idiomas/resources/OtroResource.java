/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.resources;

import co.edu.uniandes.csw.idiomas.dtos.OtroDTO;
import co.edu.uniandes.csw.idiomas.ejb.OtroLogic;
import co.edu.uniandes.csw.idiomas.entities.OtroEntity;
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
 * Clase que define los servicios de la clase otro
 * @author g.cubillosb
 */
@Path("otro")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class OtroResource {
    
    // ------------------------------------------------------------------------
    // Atributos
    // ------------------------------------------------------------------------
    
    /**
     * Atributo que representa el logger correspondiente de la clase. Para poder
     * enviar mensajes.
     */
    private static final Logger LOGGER = Logger.getLogger(OtroResource.class.getName());
    
    /**
     * Permite acceder a la lógica de la aplicación. Es una inyección de dependencias.
     */
    @Inject
    private OtroLogic otroLogic; 
    
    // ------------------------------------------------------------------------
    // Constructor
    // ------------------------------------------------------------------------
    
    // ------------------------------------------------------------------------
    // Métodos
    // ------------------------------------------------------------------------
    
    /**
     * Crea una nueva otro con la informacion que se recibe en el cuerpo de
     * la petición y se regresa un objeto identico con un id auto-generado por
     * la base de datos.
     *
     * @param otro {@link OtroDTO} - La otro que se desea
     * guardar.
     * @return JSON {@link OtroDTO} - La otro guardada con el atributo
     * id autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe la otro.
     */
    @POST
    public OtroDTO createOtro(OtroDTO otro) throws BusinessLogicException 
    {
        LOGGER.log(Level.INFO, "OtroResource createOtro: input: {0}", otro);
        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        OtroEntity otroEntity = otro.toEntity();
        // Invoca la lógica para crear la otro nueva
        OtroEntity nuevoOtroEntity = otroLogic.createOtro(otroEntity);
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        OtroDTO nuevoOtroDTO = new OtroDTO(nuevoOtroEntity);
        LOGGER.log(Level.INFO, "OtroResource createOtro: output: {0}", nuevoOtroDTO);
        return nuevoOtroDTO;
    }

    /**
     * Busca y devuelve todas las otros que existen en la aplicacion.
     *
     * @return JSONArray {@link OtroDetailDTO} - Las otros
     * encontradas en la aplicación. Si no hay ninguna retorna una lista vacía.
     */
    @GET
    public List<OtroDTO> getOtros() {
        LOGGER.info("OtroResource getOtros: input: void");
        List<OtroDTO> listaOtros = listEntity2DetailDTO(otroLogic.getOtros());
        LOGGER.log(Level.INFO, "OtroResource getOtros: output: {0}", listaOtros);
        return listaOtros;
    }

    /**
     * Busca la otro con el id asociado recibido en la URL y la devuelve.
     *
     * @param otrosId Identificador de la otro que se esta buscando.
     * Este debe ser una cadena de dígitos.
     * @return JSON {@link OtroDetailDTO} - La otro buscada
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la otro.
     */
    @GET
    @Path("{otrosId: \\d+}")
    public OtroDTO getOtro(@PathParam("otrosId") Long otrosId)
    {
        LOGGER.log(Level.INFO, "OtroResource getOtro: input: {0}", otrosId);
        OtroEntity otroEntity = otroLogic.getOtro(otrosId);
        if (otroEntity == null) {
            throw new WebApplicationException("El recurso /otros/" + otrosId + " no existe.", 404);
        }
        OtroDTO detailDTO = new OtroDTO(otroEntity);
        LOGGER.log(Level.INFO, "OtroResource getOtro: output: {0}", detailDTO);
        return detailDTO;
    }

    /**
     * Actualiza la otro con el id recibido en la URL con la informacion
     * que se recibe en el cuerpo de la petición.
     *
     * @param otrosId Identificador de la otro que se desea
     * actualizar. Este debe ser una cadena de dígitos.
     * @param otro {@link OtroDetailDTO} La otro que se desea
     * guardar.
     * @return JSON {@link OtroDetailDTO} - La otro guardada.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la otro a
     * actualizar.
     */
    @PUT
    @Path("{otrosId: \\d+}")
    public OtroDTO updateOtro(@PathParam("otrosId") Long otrosId, OtroDTO otro)
    {
        LOGGER.log(Level.INFO, "OtroResource updateOtro: input: id:{0} , otro: {1}", new Object[]{otrosId, otro});
        otro.setId(otrosId);
        if (otroLogic.getOtro(otrosId) == null) {
            throw new WebApplicationException("El recurso /otros/" + otrosId + " no existe.", 404);
        }
        OtroDTO detailDTO = new OtroDTO(otroLogic.updateOtro(otrosId, otro.toEntity()));
        LOGGER.log(Level.INFO, "OtroResource updateOtro: output: {0}", detailDTO);
        return detailDTO;

    }

    /**
     * Borra la otro con el id asociado recibido en la URL.
     *
     * @param otrosId Identificador de la otro que se desea borrar.
     * Este debe ser una cadena de dígitos.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se puede eliminar la otro.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la otro.
     */
    @DELETE
    @Path("{otrosId: \\d+}")
    public void deleteOtro(@PathParam("otrosId") Long otrosId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "OtroResource deleteOtro: input: {0}", otrosId);
        if (otroLogic.getOtro(otrosId) == null) {
            throw new WebApplicationException("El recurso /otros/" + otrosId + " no existe.", 404);
        }
        otroLogic.deleteOtro(otrosId);
        LOGGER.info("OtroResource deleteOtro: output: void");
    }

    // TODO: GC Conectar Otros con Comentarios y las otras clases.
//    /**
//     * Conexión con el servicio de libros para una otro.
//     * {@link OtroBooksResource}
//     *
//     * Este método conecta la ruta de /otros con las rutas de /books que
//     * dependen de la otro, es una redirección al servicio que maneja el
//     * segmento de la URL que se encarga de los libros de una otro.
//     *
//     * @param otrosId El ID de la otro con respecto a la cual se
//     * accede al servicio.
//     * @return El servicio de libros para esta otro en paricular.
//     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
//     * Error de lógica que se genera cuando no se encuentra la otro.
//     */
//    @Path("{otrosId: \\d+}/books")
//    public Class<OtroBooksResource> getOtroBooksResource(@PathParam("otrosId") Long otrosId) {
//        if (otroLogic.getOtro(otrosId) == null) {
//            throw new WebApplicationException("El recurso /otros/" + otrosId + " no existe.", 404);
//        }
//        return OtroBooksResource.class;
//    }

    /**
     * Convierte una lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos OtroEntity a una lista de
     * objetos OtroDetailDTO (json)
     *
     * @param entityList corresponde a la lista de otros de tipo Entity
     * que vamos a convertir a DTO.
     * @return la lista de otros en forma DTO (json)
     */
    private List<OtroDTO> listEntity2DetailDTO(List<OtroEntity> entityList) {
        List<OtroDTO> list = new ArrayList<>();
        for (OtroEntity entity : entityList) {
            list.add(new OtroDTO(entity));
        }
        return list;
    }
}

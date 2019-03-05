/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.resources;

import co.edu.uniandes.csw.idiomas.dtos.EncuentroDTO;
import co.edu.uniandes.csw.idiomas.ejb.EncuentroLogic;
import co.edu.uniandes.csw.idiomas.entities.EncuentroEntity;
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
 * Clase que define los servicios de la clase encuentro
 * @author g.cubillosb
 */
@Path("encuentro")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class EncuentroResource {
    
    // ------------------------------------------------------------------------
    // Atributos
    // ------------------------------------------------------------------------
    
    /**
     * Atributo que representa el logger correspondiente de la clase. Para poder
     * enviar mensajes.
     */
    private static final Logger LOGGER = Logger.getLogger(EncuentroResource.class.getName());
    
    /**
     * Permite acceder a la lógica de la aplicación. Es una inyección de dependencias.
     */
    @Inject
    private EncuentroLogic encuentroLogic; 
    
    // ------------------------------------------------------------------------
    // Constructor
    // ------------------------------------------------------------------------
    
    // ------------------------------------------------------------------------
    // Métodos
    // ------------------------------------------------------------------------
    
    /**
     * Crea una nueva encuentro con la informacion que se recibe en el cuerpo de
     * la petición y se regresa un objeto identico con un id auto-generado por
     * la base de datos.
     *
     * @param encuentro {@link EncuentroDTO} - La encuentro que se desea
     * guardar.
     * @return JSON {@link EncuentroDTO} - La encuentro guardada con el atributo
     * id autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe la encuentro.
     */
    @POST
    public EncuentroDTO createEncuentro(EncuentroDTO encuentro) throws BusinessLogicException 
    {
        LOGGER.log(Level.INFO, "EncuentroResource createEncuentro: input: {0}", encuentro);
        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        EncuentroEntity encuentroEntity = encuentro.toEntity();
        // Invoca la lógica para crear la encuentro nueva
        EncuentroEntity nuevoEncuentroEntity = encuentroLogic.createEncuentro(encuentroEntity);
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        EncuentroDTO nuevoEncuentroDTO = new EncuentroDTO(nuevoEncuentroEntity);
        LOGGER.log(Level.INFO, "EncuentroResource createEncuentro: output: {0}", nuevoEncuentroDTO);
        return nuevoEncuentroDTO;
    }

    /**
     * Busca y devuelve todas las encuentros que existen en la aplicacion.
     *
     * @return JSONArray {@link EncuentroDetailDTO} - Las encuentros
     * encontradas en la aplicación. Si no hay ninguna retorna una lista vacía.
     */
    @GET
    public List<EncuentroDTO> getEncuentros() {
        LOGGER.info("EncuentroResource getEncuentros: input: void");
        List<EncuentroDTO> listaEncuentros = listEntity2DetailDTO(encuentroLogic.getEncuentros());
        LOGGER.log(Level.INFO, "EncuentroResource getEncuentros: output: {0}", listaEncuentros);
        return listaEncuentros;
    }

    /**
     * Busca la encuentro con el id asociado recibido en la URL y la devuelve.
     *
     * @param encuentrosId Identificador de la encuentro que se esta buscando.
     * Este debe ser una cadena de dígitos.
     * @return JSON {@link EncuentroDetailDTO} - La encuentro buscada
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la encuentro.
     */
    @GET
    @Path("{encuentrosId: \\d+}")
    public EncuentroDTO getEncuentro(@PathParam("encuentrosId") Long encuentrosId)
    {
        LOGGER.log(Level.INFO, "EncuentroResource getEncuentro: input: {0}", encuentrosId);
        EncuentroEntity encuentroEntity = encuentroLogic.getEncuentro(encuentrosId);
        if (encuentroEntity == null) {
            throw new WebApplicationException("El recurso /encuentros/" + encuentrosId + " no existe.", 404);
        }
        EncuentroDTO detailDTO = new EncuentroDTO(encuentroEntity);
        LOGGER.log(Level.INFO, "EncuentroResource getEncuentro: output: {0}", detailDTO);
        return detailDTO;
    }

    /**
     * Actualiza la encuentro con el id recibido en la URL con la informacion
     * que se recibe en el cuerpo de la petición.
     *
     * @param encuentrosId Identificador de la encuentro que se desea
     * actualizar. Este debe ser una cadena de dígitos.
     * @param encuentro {@link EncuentroDetailDTO} La encuentro que se desea
     * guardar.
     * @return JSON {@link EncuentroDetailDTO} - La encuentro guardada.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la encuentro a
     * actualizar.
     */
    @PUT
    @Path("{encuentrosId: \\d+}")
    public EncuentroDTO updateEncuentro(@PathParam("encuentrosId") Long encuentrosId, EncuentroDTO encuentro)
    {
        LOGGER.log(Level.INFO, "EncuentroResource updateEncuentro: input: id:{0} , encuentro: {1}", new Object[]{encuentrosId, encuentro});
        encuentro.setId(encuentrosId);
        if (encuentroLogic.getEncuentro(encuentrosId) == null) {
            throw new WebApplicationException("El recurso /encuentros/" + encuentrosId + " no existe.", 404);
        }
        EncuentroDTO detailDTO = new EncuentroDTO(encuentroLogic.updateEncuentro(encuentrosId, encuentro.toEntity()));
        LOGGER.log(Level.INFO, "EncuentroResource updateEncuentro: output: {0}", detailDTO);
        return detailDTO;

    }

    /**
     * Borra la encuentro con el id asociado recibido en la URL.
     *
     * @param encuentrosId Identificador de la encuentro que se desea borrar.
     * Este debe ser una cadena de dígitos.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se puede eliminar la encuentro.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la encuentro.
     */
    @DELETE
    @Path("{encuentrosId: \\d+}")
    public void deleteEncuentro(@PathParam("encuentrosId") Long encuentrosId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "EncuentroResource deleteEncuentro: input: {0}", encuentrosId);
        if (encuentroLogic.getEncuentro(encuentrosId) == null) {
            throw new WebApplicationException("El recurso /encuentros/" + encuentrosId + " no existe.", 404);
        }
        encuentroLogic.deleteEncuentro(encuentrosId);
        LOGGER.info("EncuentroResource deleteEncuentro: output: void");
    }

    // TODO: GC Conectar Encuentros con Comentarios y las otras clases.
//    /**
//     * Conexión con el servicio de libros para una encuentro.
//     * {@link EncuentroBooksResource}
//     *
//     * Este método conecta la ruta de /encuentros con las rutas de /books que
//     * dependen de la encuentro, es una redirección al servicio que maneja el
//     * segmento de la URL que se encarga de los libros de una encuentro.
//     *
//     * @param encuentrosId El ID de la encuentro con respecto a la cual se
//     * accede al servicio.
//     * @return El servicio de libros para esta encuentro en paricular.
//     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
//     * Error de lógica que se genera cuando no se encuentra la encuentro.
//     */
//    @Path("{encuentrosId: \\d+}/books")
//    public Class<EncuentroBooksResource> getEncuentroBooksResource(@PathParam("encuentrosId") Long encuentrosId) {
//        if (encuentroLogic.getEncuentro(encuentrosId) == null) {
//            throw new WebApplicationException("El recurso /encuentros/" + encuentrosId + " no existe.", 404);
//        }
//        return EncuentroBooksResource.class;
//    }

    /**
     * Convierte una lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos EncuentroEntity a una lista de
     * objetos EncuentroDetailDTO (json)
     *
     * @param entityList corresponde a la lista de encuentros de tipo Entity
     * que vamos a convertir a DTO.
     * @return la lista de encuentros en forma DTO (json)
     */
    private List<EncuentroDTO> listEntity2DetailDTO(List<EncuentroEntity> entityList) {
        List<EncuentroDTO> list = new ArrayList<>();
        for (EncuentroEntity entity : entityList) {
            list.add(new EncuentroDTO(entity));
        }
        return list;
    }
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.resources;

import co.edu.uniandes.csw.idiomas.entities.CalificacionEntity;
import co.edu.uniandes.csw.idiomas.dtos.CalificacionDTO;
import co.edu.uniandes.csw.idiomas.dtos.CalificacionDetailDTO;
import co.edu.uniandes.csw.idiomas.ejb.CalificacionActividadLogic;
import co.edu.uniandes.csw.idiomas.ejb.CalificacionAdministradorLogic;
import co.edu.uniandes.csw.idiomas.ejb.CalificacionCoordinadorLogic;
import co.edu.uniandes.csw.idiomas.ejb.CalificacionGrupoLogic;
import co.edu.uniandes.csw.idiomas.ejb.CalificacionLogic;
import co.edu.uniandes.csw.idiomas.exceptions.BusinessLogicException;
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
 * Clase que implementa el recurso "calificaciones".
 *
 * @author ISIS2603
 * @version 1.0
 */
@Path("calificaciones")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class CalificacionResource {

    private static final Logger LOGGER = Logger.getLogger(CalificacionResource.class.getName());

    @Inject
    private CalificacionLogic calificacionLogic;

    @Inject
    private CalificacionActividadLogic calificacionActividadLogic;
    
    @Inject
    private CalificacionAdministradorLogic calificacionAdministradorLogic;
    
    @Inject
    private CalificacionCoordinadorLogic calificacionCoordinadorLogic;
    
    @Inject
    private CalificacionGrupoLogic calificacionGrupoDeInteresLogic;
    
    /**
     * Crea un nuevo calificacion con la informacion que se recibe en el cuerpo de la
     * petición y se regresa un objeto identico con un id auto-generado por la
     * base de datos.
     *
     * @param calificacion {@link CalificacionDTO} - La calificacion que se desea guardar.
     * @return JSON {@link CalificacionDTO} - La calificacion guardada con el atributo id
     * autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe la calificacion o la calificacion (o el mensaje) están fuera de límites
     * o si la actividad/administrador/coordinador/grupo de interes ingresada es invalida.
     */
    @POST
    public CalificacionDTO createCalificacion(CalificacionDTO calificacion) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "CalificacionResource createCalificacion: input: {0}", calificacion);
        CalificacionDTO nuevoCalificacionDTO = new CalificacionDTO(calificacionLogic.createCalificacion(calificacion.toEntity()));
        LOGGER.log(Level.INFO, "CalificacionResource createCalificacion: output: {0}", nuevoCalificacionDTO);
        return nuevoCalificacionDTO;
    }

    /**
     * Busca y devuelve todas las calificaciones que existen en la aplicacion.
     *
     * @return JSONArray {@link CalificacionDetailDTO} - Las calificaciones encontradas en la
     * aplicación. Si no hay ninguna retorna una lista vacía.
     */
    @GET
    public List<CalificacionDetailDTO> getCalificaciones() {
        LOGGER.info("CalificacionResource getCalificaciones: input: void");
        List<CalificacionDetailDTO> listaCalificacions = listEntity2DetailDTO(calificacionLogic.getCalificaciones());
        LOGGER.log(Level.INFO, "CalificacionResource getCalificaciones: output: {0}", listaCalificacions);
        return listaCalificacions;
    }

    /**
     * Busca la calificacion con el id asociado recibido en la URL y la devuelve.
     *
     * @param calificacionesId Identificador de la calificacion que se esta buscando. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link CalificacionDetailDTO} - La calificacion buscada
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la calificacion.
     */
    @GET
    @Path("{calificacionesId: \\d+}")
    public CalificacionDetailDTO getCalificacion(@PathParam("calificacionesId") Long calificacionesId) {
        LOGGER.log(Level.INFO, "CalificacionResource getCalificacion: input: {0}", calificacionesId);
        CalificacionEntity calificacionEntity = calificacionLogic.getCalificacion(calificacionesId);
        if (calificacionEntity == null) {
            throw new WebApplicationException("El recurso /calificaciones/" + calificacionesId + " no existe.", 404);
        }
        CalificacionDetailDTO calificacionDetailDTO = new CalificacionDetailDTO(calificacionEntity);
        LOGGER.log(Level.INFO, "CalificacionResource getCalificacion: output: {0}", calificacionDetailDTO);
        return calificacionDetailDTO;
    }

    /**
     * Actualiza la calificacion con el id recibido en la URL con la información que se
     * recibe en el cuerpo de la petición.
     *
     * @param calificacionesId Identificador de la calificacion que se desea actualizar. Este debe
     * ser una cadena de dígitos.
     * @param calificacion {@link CalificacionDTO} La calificacion que se desea guardar.
     * @return JSON {@link CalificacionDetailDTO} - La calificacion guardada.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la calificacion a
     * actualizar.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se puede actualizar la calificacion.
     */
    @PUT
    @Path("{calificacionesId: \\d+}")
    public CalificacionDetailDTO updateCalificacion(@PathParam("calificacionesId") Long calificacionesId, CalificacionDetailDTO calificacion) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "CalificacionResource updateCalificacion: input: id: {0} , calificacion: {1}", new Object[]{calificacionesId, calificacion});
        calificacion.setId(calificacionesId);
        if (calificacionLogic.getCalificacion(calificacionesId) == null) {
            throw new WebApplicationException("El recurso /calificaciones/" + calificacionesId + " no existe.", 404);
        }
        CalificacionDetailDTO detailDTO = new CalificacionDetailDTO(calificacionLogic.updateCalificacion(calificacionesId, calificacion.toEntity()));
        LOGGER.log(Level.INFO, "CalificacionResource updateCalificacion: output: {0}", detailDTO);
        return detailDTO;
    }

    /**
     * Borra la calificacion con el id asociado recibido en la URL.
     *
     * @param calificacionesId Identificador de la calificacion que se desea borrar. Este debe ser
     * una cadena de dígitos.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la calificacion.
     */
    @DELETE
    @Path("{calificacionesId: \\d+}")
    public void deleteCalificacion(@PathParam("calificacionesId") Long calificacionesId) {
        LOGGER.log(Level.INFO, "CalificacionResource deleteCalificacion: input: {0}", calificacionesId);
        CalificacionEntity entity = calificacionLogic.getCalificacion(calificacionesId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /calificaciones/" + calificacionesId + " no existe.", 404);
        }
        if (entity.getActividad()!= null)
        {
            calificacionActividadLogic.removeActividad(calificacionesId);
        }
        else if(entity.getAdministrador()!= null)
        {
            calificacionAdministradorLogic.removeAdministrador(calificacionesId);
        }
        else if(entity.getCoordinador()!= null)
        {
            calificacionCoordinadorLogic.removeCoordinador(calificacionesId);
        }
        else if(entity.getGrupo()!= null)
        {
            calificacionGrupoDeInteresLogic.removeGrupoDeInteres(calificacionesId);
        }
        calificacionLogic.deleteCalificacion(calificacionesId);
        LOGGER.info("CalificacionResource deleteCalificacion: output: void");
    }

    /**
     * Conexión con el servicio de actividades para una calificacion. {@link ActividadResource}
     *
     * Este método conecta la ruta de /calificaciones con las rutas de /actividades que
     * dependen de la calificacion, es una redirección al servicio que maneja el segmento
     * de la URL que se encarga de las actividades.
     *
     * @param calificacionesId El ID dla calificacion con respecto al cual se accede al
     * servicio.
     * @return El servicio de Actividades para esa calificacion en paricular.\
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la calificacion.
     */
    @Path("{calificacionesId: \\d+}/actividades")
    public Class<ActividadResource> getActividadResource(@PathParam("calificacionesId") Long calificacionesId) {
        if (calificacionLogic.getCalificacion(calificacionesId) == null) {
            throw new WebApplicationException("El recurso /calificaciones/" + calificacionesId + "/actividades no existe.", 404);
        }
        return ActividadResource.class;
    }
    
    /**
     * Conexión con el servicio de administradores para una calificacion. {@link AdministradorResource}
     *
     * Este método conecta la ruta de /calificaciones con las rutas de /administradores que
     * dependen de la calificacion, es una redirección al servicio que maneja el segmento
     * de la URL que se encarga de los administradores.
     *
     * @param calificacionesId El ID dla calificacion con respecto al cual se accede al
     * servicio.
     * @return El servicio de Administradores para esa calificacion en paricular.\
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la calificacion.
     */
    @Path("{calificacionesId: \\d+}/administradores")
    public Class<AdministradorResource> getAdministradorResource(@PathParam("calificacionesId") Long calificacionesId) {
        if (calificacionLogic.getCalificacion(calificacionesId) == null) {
            throw new WebApplicationException("El recurso /calificaciones/" + calificacionesId + "/administradores no existe.", 404);
        }
        return AdministradorResource.class;
    }
    
    /**
     * Conexión con el servicio de coordinadores para una calificacion. {@link CoordinadorResource}
     *
     * Este método conecta la ruta de /calificaciones con las rutas de /coordinadores que
     * dependen de la calificacion, es una redirección al servicio que maneja el segmento
     * de la URL que se encarga de los coordinadores.
     *
     * @param calificacionesId El ID dla calificacion con respecto al cual se accede al
     * servicio.
     * @return El servicio de Coordinadores para esa calificacion en paricular.\
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la calificacion.
     */
    @Path("{calificacionesId: \\d+}/coordinadores")
    public Class<CoordinadorResource> getCoordinadorResource(@PathParam("calificacionesId") Long calificacionesId) {
        if (calificacionLogic.getCalificacion(calificacionesId) == null) {
            throw new WebApplicationException("El recurso /calificaciones/" + calificacionesId + "/coordinadores no existe.", 404);
        }
        return CoordinadorResource.class;
    }
    
    /**
     * Conexión con el servicio de grupos para una calificacion. {@link GrupoDeInteresResource}
     *
     * Este método conecta la ruta de /calificaciones con las rutas de /grupos que
     * dependen de la calificacion, es una redirección al servicio que maneja el segmento
     * de la URL que se encarga de los grupos.
     *
     * @param calificacionesId El ID dla calificacion con respecto al cual se accede al
     * servicio.
     * @return El servicio de GrupoDeIntereses para esa calificacion en paricular.\
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la calificacion.
     */
    @Path("{calificacionesId: \\d+}/grupos")
    public Class<GrupoDeInteresResource> getGrupoDeInteresResource(@PathParam("calificacionesId") Long calificacionesId) {
        if (calificacionLogic.getCalificacion(calificacionesId) == null) {
            throw new WebApplicationException("El recurso /calificaciones/" + calificacionesId + "/grupos no existe.", 404);
        }
        return GrupoDeInteresResource.class;
    }

    /**
     * Convierte una lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos CalificacionEntity a una lista de
     * objetos CalificacionDetailDTO (json)
     *
     * @param entityList corresponde a la lista de calificaciones de tipo Entity que
     * vamos a convertir a DTO.
     * @return la lista de calificaciones en forma DTO (json)
     */
    private List<CalificacionDetailDTO> listEntity2DetailDTO(List<CalificacionEntity> entityList) {
        List<CalificacionDetailDTO> list = new ArrayList<>();
        for (CalificacionEntity entity : entityList) {
            list.add(new CalificacionDetailDTO(entity));
        }
        return list;
    }
}
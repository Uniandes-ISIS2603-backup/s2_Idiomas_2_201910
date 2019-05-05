/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.resources;
import co.edu.uniandes.csw.idiomas.dtos.GrupoDeInteresDTO;
import co.edu.uniandes.csw.idiomas.ejb.UsuarioGrupoLogic;
import co.edu.uniandes.csw.idiomas.ejb.GrupoDeInteresLogic;
import co.edu.uniandes.csw.idiomas.entities.GrupoDeInteresEntity;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.WebApplicationException;

/**
 * Clase que implementa el recurso "actividades/{id}/comentariosUsuario".
 *
 * @author j.barbosaj
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsuarioGrupoResource {
    
    // ------------------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------------

    /**
     * Logger para las acciones de la clase.
     */
    private static final Logger LOGGER = Logger.getLogger(UsuarioGrupoResource.class.getName());

    /**
     * Variable para acceder a la persistencia de la aplicación. Es una
     * inyección de dependencias.
     */
    @Inject
    private UsuarioGrupoLogic usuarioGrupoLogic;

    /**
     * Variable para acceder a la persistencia de la aplicación. Es una
     * inyección de dependencias.
     */
    @Inject
    private GrupoDeInteresLogic GrupoLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    // ------------------------------------------------------------------------
    // Métodos
    // ------------------------------------------------------------------------
    
    /**
     * Asocia un libro existente con un autor existente
     *
     * @param usuarioId El ID del autor al cual se le va a asociar el libro
     * @param grupoId El ID del libro que se asocia
     * @return JSON {@link GrupoDTO} - El libro asociado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro.
     */
//    @POST
//    @Path("{usuarioId: \\d+}")
//    public GrupoDeInteresDTO addGrupo(@PathParam("usuarioId") Long usuarioId, @PathParam("grupoId") Long grupoId) {
//        LOGGER.log(Level.INFO, "UsuarioGrupoResource addGrupo: input: actividadesId {0} , comentariosUsuarioId {1}", new Object[]{usuarioId, grupoId});
//        if (GrupoLogic.getGrupoDeInteres(grupoId) == null) {
//            throw new WebApplicationException("El recurso /comentariosUsuario/" + grupoId + " no existe.", 404);
//        }
////        GrupoDeInteresDTO detailDTO = new GrupoDeInteresDTO(usuarioGrupoLogic.addGrupo(usuarioId, grupoId));
////        LOGGER.log(Level.INFO, "UsuarioGrupoResource addGrupo: output: {0}", detailDTO);
////        return detailDTO;
//    }

    /**
     * Busca y devuelve todos los libros que existen en un autor.
     *
     * @param actividadesId El ID del autor del cual se buscan los libros
     * @return JSONArray {@link GrupoDTO} - Los libros encontrados en el
     * autor. Si no hay ninguno retorna una lista vacía.
     */
//    @GET
//    public List<GrupoDTO> getGrupo(@PathParam("actividadesId") Long actividadesId) {
//        LOGGER.log(Level.INFO, "UsuarioGrupoResource getGrupo: input: {0}", actividadesId);
//        List<GrupoDTO> lista = comentariosUsuarioListEntity2DTO(usuarioGrupoLogic.getGrupo(actividadesId));
//        LOGGER.log(Level.INFO, "UsuarioGrupoResource getGrupo: output: {0}", lista);
//        return lista;
//    }

    /**
     * Busca y devuelve el libro con el ID recibido en la URL, relativo a un
     * autor.
     *
     * @param actividadesId El ID del autor del cual se busca el libro
     * @param comentariosUsuarioId El ID del libro que se busca
     * @return {@link GrupoDTO} - El libro encontrado en el autor.
     * @throws co.edu.uniandes.csw.comentariosUsuariotore.exceptions.BusinessLogicException
     * si el libro no está asociado al autor
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro.
     */
//    @GET
//    @Path("{comentariosUsuarioId: \\d+}")
//    public GrupoDTO getGrupo(@PathParam("actividadesId") Long actividadesId, @PathParam("comentariosUsuarioId") Long comentariosUsuarioId) throws BusinessLogicException {
//        LOGGER.log(Level.INFO, "UsuarioGrupoResource getGrupo: input: actividadesId {0} , comentariosUsuarioId {1}", new Object[]{actividadesId, comentariosUsuarioId});
//        if (GrupoLogic.getGrupo(comentariosUsuarioId) == null) {
//            throw new WebApplicationException("El recurso /comentariosUsuario/" + comentariosUsuarioId + " no existe.", 404);
//        }
//        GrupoDTO detailDTO = new GrupoDTO(usuarioGrupoLogic.getGrupo(actividadesId, comentariosUsuarioId));
//        LOGGER.log(Level.INFO, "UsuarioGrupoResource getGrupo: output: {0}", detailDTO);
//        return detailDTO;
//    }

    /**
     * Actualiza la lista de libros de un autor con la lista que se recibe en el
     * cuerpo
     *
     * @param actividadesId El ID del autor al cual se le va a asociar el libro
     * @param comentariosUsuario JSONArray {@link GrupoDTO} - La lista de libros que se
     * desea guardar.
     * @return JSONArray {@link GrupoDTO} - La lista actualizada.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro.
     */
//    @PUT
//    public List<GrupoDTO> replaceGrupo(@PathParam("actividadesId") Long actividadesId, List<GrupoDTO> comentariosUsuario) {
//        LOGGER.log(Level.INFO, "UsuarioGrupoResource replaceGrupo: input: actividadesId {0} , comentariosUsuario {1}", new Object[]{actividadesId, comentariosUsuario});
//        for (GrupoDTO Grupo : comentariosUsuario) {
//            if (GrupoLogic.getGrupo(Grupo.getId()) == null) {
//                throw new WebApplicationException("El recurso /comentariosUsuario/" + Grupo.getId() + " no existe.", 404);
//            }
//        }
//        List<GrupoDTO> lista = comentariosUsuarioListEntity2DTO(usuarioGrupoLogic.replaceGrupo(actividadesId, comentariosUsuarioListDTO2Entity(comentariosUsuario)));
//        LOGGER.log(Level.INFO, "UsuarioGrupoResource replaceGrupo: output: {0}", lista);
//        return lista;
//    }

    /**
     * Elimina la conexión entre el libro y e autor recibidos en la URL.
     *
     * @param actividadesId El ID del autor al cual se le va a desasociar el libro
     * @param comentariosUsuarioId El ID del libro que se desasocia
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro.
     */
    @DELETE
    @Path("{comentariosUsuarioId: \\d+}")
    public void removeGrupo(@PathParam("actividadesId") Long actividadesId, @PathParam("comentariosUsuarioId") Long comentariosUsuarioId) {
        LOGGER.log(Level.INFO, "UsuarioGrupoResource deleteGrupo: input: actividadesId {0} , comentariosUsuarioId {1}", new Object[]{actividadesId, comentariosUsuarioId});
        if (GrupoLogic.getGrupoDeInteres(comentariosUsuarioId) == null) {
            throw new WebApplicationException("El recurso /comentariosUsuario/" + comentariosUsuarioId + " no existe.", 404);
        }
        usuarioGrupoLogic.removeGrupo(actividadesId, comentariosUsuarioId);
        LOGGER.info("UsuarioGrupoResource deleteGrupo: output: void");
    }

    /**
     * Convierte una lista de GrupoEntity a una lista de GrupoDTO.
     *
     * @param entityList Lista de GrupoEntity a convertir.
     * @return Lista de GrupoDTO convertida.
     */
    private List<GrupoDeInteresDTO> comentariosUsuarioListEntity2DTO(List<GrupoDeInteresEntity> entityList) {
        List<GrupoDeInteresDTO> list = new ArrayList<>();
        for (GrupoDeInteresEntity entity : entityList) {
//            list.add(new GrupoDeInteresDTO(entity));
        }
        return list;
    }

    /**
     * Convierte una lista de GrupoDTO a una lista de GrupoEntity.
     *
     * @param dtos Lista de GrupoDTO a convertir.
     * @return Lista de GrupoEntity convertida.
     */
    private List<GrupoDeInteresEntity> comentariosUsuarioListDTO2Entity(List<GrupoDeInteresDTO> dtos) {
        List<GrupoDeInteresEntity> list = new ArrayList<>();
        for (GrupoDeInteresDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    }
}

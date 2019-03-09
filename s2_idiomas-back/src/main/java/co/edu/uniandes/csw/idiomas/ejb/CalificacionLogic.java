package co.edu.uniandes.csw.idiomas.ejb;


import co.edu.uniandes.csw.idiomas.entities.CalificacionEntity;
import co.edu.uniandes.csw.idiomas.persistence.CalificacionPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Clase que implementa la conexion con la persistencia para las calificaciones.
 *
 * @author jdruedaa
 */
@Stateless
public class CalificacionLogic {

    private static final Logger LOGGER = Logger.getLogger(CalificacionLogic.class.getName());

    @Inject
    private CalificacionPersistence calificacionPersistence;
    
      /**
     * Se encarga de crear un Author en la base de datos.
     *
     * @param calificacionEntity Objeto de CalificacionEntity con los datos nuevos
     * @return Objeto de CalificacionEntity con los datos nuevos y su ID.
     */
    public CalificacionEntity createCalificacion(CalificacionEntity calificacionEntity) {
        LOGGER.log(Level.INFO, "Inicia proceso de creación de la calificacion");
        CalificacionEntity newCalificacionEntity = calificacionPersistence.create(calificacionEntity);
        LOGGER.log(Level.INFO, "Termina proceso de creación de la calificacion");
        return newCalificacionEntity;
    }

    /**
     * Obtiene la lista de los registros de Author.
     *
     * @return Colección de objetos de AuthorEntity.
     */
    public List<CalificacionEntity> getCalificaciones() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los autores");
        List<CalificacionEntity> lista = calificacionPersistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todos los autores");
        return lista;
    }

    /**
     * Obtiene los datos de una instancia de Calificacion a partir de su ID.
     *
     * @param calificacionId Identificador de la instancia a consultar
     * @return Instancia de CalificacionEntity con los datos de la Calificacion consultada.
     */
    public CalificacionEntity getCalificacion(Long calificacionId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la calificacion con id = {0}", calificacionId);
        CalificacionEntity calificacionEntity = calificacionPersistence.find(calificacionId);
        if (calificacionEntity == null) {
            LOGGER.log(Level.SEVERE, "La editorial con el id = {0} no existe", calificacionId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar el calificacion con id = {0}", calificacionId);
        return calificacionEntity;
    }

    /**
     * Actualiza la información de una instancia de Author.
     *
     * @param authorsId Identificador de la instancia a actualizar
     * @param authorEntity Instancia de AuthorEntity con los nuevos datos.
     * @return Instancia de AuthorEntity con los datos actualizados.
     */
    public AuthorEntity updateAuthor(Long authorsId, AuthorEntity authorEntity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el autor con id = {0}", authorsId);
        AuthorEntity newAuthorEntity = persistence.update(authorEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el autor con id = {0}", authorsId);
        return newAuthorEntity;
    }

    /**
     * Elimina una instancia de Author de la base de datos.
     *
     * @param authorsId Identificador de la instancia a eliminar.
     * @throws BusinessLogicException si el autor tiene libros asociados.
     */
    public void deleteAuthor(Long authorsId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el autor con id = {0}", authorsId);
        List<BookEntity> books = getAuthor(authorsId).getBooks();
        if (books != null && !books.isEmpty()) {
            throw new BusinessLogicException("No se puede borrar el autor con id = " + authorsId + " porque tiene books asociados");
        }
        List<PrizeEntity> prizes = getAuthor(authorsId).getPrizes();
        if (prizes != null && !prizes.isEmpty()) {
            throw new BusinessLogicException("No se puede borrar el autor con id = " + authorsId + " porque tiene premios asociados");
        }
        persistence.delete(authorsId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el autor con id = {0}", authorsId);
    }
}
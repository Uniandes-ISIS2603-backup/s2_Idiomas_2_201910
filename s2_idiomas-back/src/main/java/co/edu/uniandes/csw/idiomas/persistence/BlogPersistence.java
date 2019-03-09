/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.persistence;

import co.edu.uniandes.csw.idiomas.entities.BlogEntity;
import co.edu.uniandes.csw.idiomas.entities.EstadiaEntity;
import co.edu.uniandes.csw.idiomas.entities.GrupoDeInteresEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author estudiante
 */
public class BlogPersistence {
            private static final Logger LOGGER = Logger.getLogger(BlogPersistence.class.getName());
    
    @PersistenceContext(unitName = "idiomasPU")
    protected EntityManager em; 
    
    /**
     * Método para persisitir la entidad en la base de datos.
     *
     * @param BlogEntity objeto Blog que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public BlogEntity create(BlogEntity BlogEntity) {
        LOGGER.log(Level.INFO, "Creando un GrupoDeInteres nuevo");        
        em.persist(BlogEntity);
        LOGGER.log(Level.INFO, "Saliendo de crear un GrupoDeInteres nuevo");
        return BlogEntity;
    }

    /**
     *
     * Borra una GrupoDeInteres de la base de datos recibiendo como argumento el id
     * de la GrupoDeInteres
     *
     * @param BlogId: id correspondiente a la Blog a borrar.
     */
    public void delete(Long BlogId) {
        LOGGER.log(Level.INFO, "Borrando Blog con id = {0}", BlogId);
        // Se hace uso de mismo método que esta explicado en public AdministradorEntity find(Long id) para obtener la Administrador a borrar.
        BlogEntity entity = em.find(BlogEntity.class, BlogId);
        /* Note que una vez obtenido el objeto desde la base de datos llamado "entity", volvemos hacer uso de un método propio del
         EntityManager para eliminar de la base de datos el objeto que encontramos y queremos borrar.
         Es similar a "delete from AdministradorEntity where id=id;" - "DELETE FROM table_nombre WHERE condition;" en SQL.*/
        em.remove(entity);
        LOGGER.log(Level.INFO, "Saliendo de borrar la Blog con id = {0}", BlogId);
    }
          /**
     * Actualiza una estadia.
     *
     * @param GrupoDeInteresEntity: la estadia que viene con los nuevos cambios.
     * Por ejemplo el nombre pudo cambiar. En ese caso, se haria uso del método
     * update.
     * @return un grupo con los cambios aplicados.
     */
    public BlogEntity update(BlogEntity pBlogEntity) {
        LOGGER.log(Level.INFO, "Actualizando Blog con id = {0}", pBlogEntity.getId());
        /* Note que hacemos uso de un método propio del EntityManager llamado merge() que recibe como argumento
        la editorial con los cambios, esto es similar a 
        "UPDATE table_name SET column1 = value1, column2 = value2, ... WHERE condition;" en SQL.
         */
        LOGGER.log(Level.INFO, "Saliendo de blog la grupoDeInteres con id = {0}", pBlogEntity.getId());
        return em.merge(pBlogEntity);
    }
        /**
     * Devuelve todas las estadias de la base de datos.
     *
     * @return una lista con todas las estadias que encuentre en la base de
     * datos, "select u from EstadiaEntity u" es como un "select * from
     * EstadiaEntity;" - "SELECT * FROM table_name" en SQL.
     */
    public List<BlogEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todas los Blog");
        // Se crea un query para buscar todas las estadias en la base de datos.
        TypedQuery query = em.createQuery("select u from Blog u", BlogEntity.class);
        // Se hace uso del método getResultList() que obtiene una lista de estadias.
        return query.getResultList();
    }
	
    /**
     * Busca si hay alguna estadia con el id que se envía de argumento
     *
     * @param pEstadiaId: id correspondiente a la estadia buscada.
     * @return una estadia.
     */
    public BlogEntity find(Long pBlogId) {
        LOGGER.log(Level.INFO, "Consultando grupoDeInteres con id={0}", pBlogId);
        /* Note que se hace uso del metodo "find" propio del EntityManager, el cual recibe como argumento 
        el tipo de la clase y el objeto que nos hara el filtro en la base de datos en este caso el "id"
        Suponga que es algo similar a "select * from EstadiaEntity where id=id;" - "SELECT * FROM table_name WHERE condition;" en SQL.
         */
        return em.find(BlogEntity.class, pBlogId);
    }
}

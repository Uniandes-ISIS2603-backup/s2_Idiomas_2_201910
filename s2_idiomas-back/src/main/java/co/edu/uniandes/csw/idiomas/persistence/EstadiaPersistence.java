/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.persistence;

import co.edu.uniandes.csw.idiomas.entities.EstadiaEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Clase que maneja la persistencia para Estadia. Se conecta a través Entity
 * Manager de javax.persistance con la base de datos SQL.
 *
 * @author g.cubillosb
 */
@Stateless
public class EstadiaPersistence {

    // ----------------------------------------------------------------------
    // Atributos 
    // ----------------------------------------------------------------------
    
    /**
     * Logger para las acciones de la clase.
     */
    private static final Logger LOGGER = Logger.getLogger(EstadiaPersistence.class.getName());

    /**
     * Entity manager para la clase.
     */
    @PersistenceContext(unitName = "idiomasPU")
    protected EntityManager em;

    // ----------------------------------------------------------------------
    // Métodos
    // ----------------------------------------------------------------------
    
    /**
     * Método para persistir la entidad en la base de datos.
     *
     * @param pEstadiaEntity Objeto estadia que se creará en la base de datos.
     * @return Devuelve la estadia creada con un id dado por la base de datos.
     */
    public EstadiaEntity create(EstadiaEntity pEstadiaEntity) {
        LOGGER.log(Level.INFO, "Creando una estadia nueva");
        em.persist(pEstadiaEntity);
        LOGGER.log(Level.INFO, "Saliendo de crear una estadia nueva");
        return pEstadiaEntity;
    }

    /**
     * Devuelve todas las estadias de la base de datos.
     *
     * @return una lista con todas las estadias que encuentre en la base de
     * datos, "select u from EstadiaEntity u" es como un "select * from
     * EstadiaEntity;" - "SELECT * FROM table_name" en SQL.
     */
    public List<EstadiaEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todas las estadias");
        // Se crea un query para buscar todas las estadias en la base de datos.
        TypedQuery query = em.createQuery("select u from EstadiaEntity u", EstadiaEntity.class);
        // Se hace uso del método getResultList() que obtiene una lista de estadias.
        return query.getResultList();
    }

    /**
     * Busca si hay alguna estadia con el id que se envía de argumento
     *
     * @param pEstadiaId: id correspondiente a la estadia buscada.
     * @return una estadia.
     */
    public EstadiaEntity find(Long pEstadiaId) {
        LOGGER.log(Level.INFO, "Consultando editorial con id={0}", pEstadiaId);
        /* Note que se hace uso del metodo "find" propio del EntityManager, el cual recibe como argumento 
        el tipo de la clase y el objeto que nos hara el filtro en la base de datos en este caso el "id"
        Suponga que es algo similar a "select * from EstadiaEntity where id=id;" - "SELECT * FROM table_name WHERE condition;" en SQL.
         */
        return em.find(EstadiaEntity.class, pEstadiaId);
    }

    /**
     * Actualiza una estadia.
     *
     * @param pEstadiaEntity: la estadia que viene con los nuevos cambios. Por
     * ejemplo el nombre pudo cambiar. En ese caso, se haria uso del método
     * update.
     * @return una estadia con los cambios aplicados.
     */
    public EstadiaEntity update(EstadiaEntity pEstadiaEntity) {
        LOGGER.log(Level.INFO, "Actualizando editorial con id = {0}", pEstadiaEntity.getId());
        /* Note que hacemos uso de un método propio del EntityManager llamado merge() que recibe como argumento
        la editorial con los cambios, esto es similar a 
        "UPDATE table_name SET column1 = value1, column2 = value2, ... WHERE condition;" en SQL.
         */
        LOGGER.log(Level.INFO, "Saliendo de actualizar la editorial con id = {0}", pEstadiaEntity.getId());
        return em.merge(pEstadiaEntity);
    }

    /**
     *
     * Borra una estadia de la base de datos recibiendo como argumento el id de
     * la estadia.
     *
     * @param pEstadiaId: id correspondiente a la estadia a borrar.
     */
    public void delete(Long pEstadiaId) {
        LOGGER.log(Level.INFO, "Borrando estadia con id = {0}", pEstadiaId);
        // Se hace uso de mismo método que esta explicado en public EstadiaEntity 
        // find(Long id) para obtener la editorial a borrar.
        EstadiaEntity entity = em.find(EstadiaEntity.class, pEstadiaId);
        /* Note que una vez obtenido el objeto desde la base de datos llamado 
         "entity", volvemos hacer uso de un método propio del
         EntityManager para eliminar de la base de datos el objeto que 
         encontramos y queremos borrar.
         Es similar a "delete from EditorialEntity where id=id;" - "DELETE FROM 
         table_name WHERE condition;" en SQL.*/
        em.remove(entity);
        LOGGER.log(Level.INFO, "Saliendo de borrar la estadia con id = {0}", pEstadiaId);
    }

    /**
     * Busca si hay alguna estadia con el nombre que se envía de argumento.
     *
     * @param pName: Nombre de la estadia que se está buscando
     * @return null si no existe ninguna estadia con el nombre del argumento. Si
     * existe alguna devuelve la primera.
     */
    public EstadiaEntity findByName(String pName) {
        LOGGER.log(Level.INFO, "Consultando estadia por nombre = {0}", pName);
        // Se crea un query para buscar editoriales con el nombre que recibe el método como argumento. ":name" es un placeholder que debe ser remplazado
        TypedQuery query = em.createQuery("Select e From EstadiaEntity e where e.nombre = :nombre", EstadiaEntity.class);
        // Se remplaza el placeholder ":name" con el valor del argumento 
        query = query.setParameter("nombre", pName);
        // Se invoca el query se obtiene la lista resultado
        List<EstadiaEntity> sameName = query.getResultList();
        EstadiaEntity result = null;
        if (!(sameName == null || sameName.isEmpty())) {
            result = sameName.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar estadia por nombre = {0}", pName);
        return result;
    }

}

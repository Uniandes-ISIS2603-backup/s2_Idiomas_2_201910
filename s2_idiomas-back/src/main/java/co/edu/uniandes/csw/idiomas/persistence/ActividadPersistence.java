/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.persistence;


import co.edu.uniandes.csw.idiomas.entities.ActividadEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Clase que maneja la persistencia para Actividad. Se conecta a través Entity
 * Manager de javax.persistance con la base de datos SQL.
 * @author g.cubillosb
 */
@Stateless
public class ActividadPersistence {
    

    // ----------------------------------------------------------------------
    // Atributos 
    // ----------------------------------------------------------------------
    
    /**
     * Logger para las acciones de la clase.
     */
    private static final Logger LOGGER = Logger.getLogger(ActividadPersistence.class.getName());
    
    /**
     * Entity manager para la clase.
     */
    @PersistenceContext (unitName = "idiomasPU")
    protected EntityManager em;
    
    // ----------------------------------------------------------------------
    // Métodos
    // ----------------------------------------------------------------------
    
   /**
     * Método para persistir la entidad en la base de datos.
     * 
     * @param pActividadEntity Objeto actividad que se creará en la base de datos.
     * @return Devuelve la actividad creada con un id dado por la base de datos.
     */
    public ActividadEntity create (ActividadEntity pActividadEntity)
    {
        LOGGER.log(Level.INFO, "Creando una actividad nueva");
        em.persist(pActividadEntity);
        LOGGER.log(Level.INFO, "Saliendo de crear una actividad nueva");
        return pActividadEntity;
    }
    
    /**
     * Devuelve todas las actividades de la base de datos.
     *
     * @return una lista con todas las actividades que encuentre en la base de
     * datos, "select u from ActividadEntity u" es como un "select * from
     * ActividadEntity;" - "SELECT * FROM table_name" en SQL.
     */
    public List<ActividadEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todas las actividades");
        // Se crea un query para buscar todas las actividades en la base de datos.
        TypedQuery query = em.createQuery("select u from ActividadEntity u", ActividadEntity.class);
        // Se hace uso del método getResultList() que obtiene una lista de actividades.
        return query.getResultList();
    }
	
    /**
     * Busca si hay alguna actividad con el id que se envía de argumento
     *
     * @param pActividadId: id correspondiente a la actividad buscada.
     * @return una actividad.
     */
    public ActividadEntity find(Long pActividadId) {
        LOGGER.log(Level.INFO, "Consultando actividad con id = {0}", pActividadId);
        /* Note que se hace uso del metodo "find" propio del EntityManager, el cual recibe como argumento 
        el tipo de la clase y el objeto que nos hara el filtro en la base de datos en este caso el "id"
        Suponga que es algo similar a "select * from ActividadEntity where id=id;" - "SELECT * FROM table_name WHERE condition;" en SQL.
         */
        return em.find(ActividadEntity.class, pActividadId);
    }

    /**
     * Actualiza una actividad.
     *
     * @param pActividadEntity: la actividad que viene con los nuevos cambios.
     * Por ejemplo el nombre pudo cambiar. En ese caso, se haria uso del método
     * update.
     * @return una actividad con los cambios aplicados.
     */
    public ActividadEntity update(ActividadEntity pActividadEntity) {
        LOGGER.log(Level.INFO, "Actualizando actividad con id = {0}", pActividadEntity.getId());
        /* Note que hacemos uso de un método propio del EntityManager llamado merge() que recibe como argumento
        la actividad con los cambios, esto es similar a 
        "UPDATE table_name SET column1 = value1, column2 = value2, ... WHERE condition;" en SQL.
         */
        LOGGER.log(Level.INFO, "Saliendo de actualizar la actividad con id = {0}", pActividadEntity.getId());
        return em.merge(pActividadEntity);
    }
	
    /**
     *
     * Borra una actividad de la base de datos recibiendo como argumento el id
     * de la actividad.
     *
     * @param pActividadId: id correspondiente a la actividad a borrar.
     */
    public void delete(Long pActividadId) {
        LOGGER.log(Level.INFO, "Borrando actividad con id = {0}", pActividadId);
        // Se hace uso de mismo método que esta explicado en public ActividadEntity 
        // find(Long id) para obtener la actividad a borrar.
        ActividadEntity entity = em.find(ActividadEntity.class, pActividadId);
        /* Note que una vez obtenido el objeto desde la base de datos llamado 
         "entity", volvemos hacer uso de un método propio del
         EntityManager para eliminar de la base de datos el objeto que 
         encontramos y queremos borrar.
         Es similar a "delete from EditorialEntity where id=id;" - "DELETE FROM 
         table_name WHERE condition;" en SQL.*/
        em.remove(entity);
        LOGGER.log(Level.INFO, "Saliendo de borrar la actividad con id = {0}", pActividadId);
    }
	
    /**
     * Busca si hay alguna actividad con el nombre que se envía de argumento.
     *
     * @param pName: Nombre de la actividad que se está buscando
     * @return null si no existe ninguna actividad con el nombre del argumento.
     * Si existe alguna devuelve la primera.
     */
    public ActividadEntity findByName(String pName) {
        LOGGER.log(Level.INFO, "Consultando actividad por nombre = {0}", pName);
        
        // Se crea un query para buscar actividades con el nombre que recibe el método como argumento. ":name" es un placeholder que debe ser remplazado
        TypedQuery query = em.createQuery("Select e From ActividadEntity e where e.nombre = :nombre", ActividadEntity.class);
        // Se remplaza el placeholder ":name" con el valor del argumento 
        query = query.setParameter("nombre", pName);
        // Se invoca el query se obtiene la lista resultado
        List<ActividadEntity> sameName = query.getResultList();
        ActividadEntity result = null;
        if (!(sameName == null || sameName.isEmpty())) {
            result = sameName.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar actividad por nombre = {0}", pName);
        return result;
    }
    
}

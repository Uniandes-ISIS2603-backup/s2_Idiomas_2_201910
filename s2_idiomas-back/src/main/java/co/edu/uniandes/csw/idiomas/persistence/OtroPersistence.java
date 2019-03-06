/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.persistence;

import co.edu.uniandes.csw.idiomas.entities.OtroEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Clase que maneja la persistencia para Otro. Se conecta a través Entity
 * Manager de javax.persistance con la base de datos SQL.
 * @author g.cubillosb
 */
@Stateless
public class OtroPersistence {
    
    // ----------------------------------------------------------------------
    // Atributos 
    // ----------------------------------------------------------------------
    
    /**
     * Logger para las acciones de la clase.
     */
    private static final Logger LOGGER = Logger.getLogger(OtroPersistence.class.getName());
    
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
     * @param pOtroEntity Objeto otro que se creará en la base de datos.
     * @return Devuelve la otro creada con un id dado por la base de datos.
     */
    public OtroEntity create (OtroEntity pOtroEntity)
    {
        LOGGER.log(Level.INFO, "Creando una otro nueva");
        em.persist(pOtroEntity);
        LOGGER.log(Level.INFO, "Saliendo de crear una otro nueva");
        return pOtroEntity;
    }
    
    /**
     * Devuelve todas las otros de la base de datos.
     *
     * @return una lista con todas las otros que encuentre en la base de
     * datos, "select u from OtroEntity u" es como un "select * from
     * OtroEntity;" - "SELECT * FROM table_name" en SQL.
     */
    public List<OtroEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todas las otros");
        // Se crea un query para buscar todas las otros en la base de datos.
        TypedQuery query = em.createQuery("select u from OtroEntity u", OtroEntity.class);
        // Se hace uso del método getResultList() que obtiene una lista de otros.
        return query.getResultList();
    }
	
    /**
     * Busca si hay alguna otro con el id que se envía de argumento
     *
     * @param pOtroId: id correspondiente a la otro buscada.
     * @return una otro.
     */
    public OtroEntity find(Long pOtroId) {
        LOGGER.log(Level.INFO, "Consultando editorial con id={0}", pOtroId);
        /* Note que se hace uso del metodo "find" propio del EntityManager, el cual recibe como argumento 
        el tipo de la clase y el objeto que nos hara el filtro en la base de datos en este caso el "id"
        Suponga que es algo similar a "select * from OtroEntity where id=id;" - "SELECT * FROM table_name WHERE condition;" en SQL.
         */
        return em.find(OtroEntity.class, pOtroId);
    }

    /**
     * Actualiza una otro.
     *
     * @param pOtroEntity: la otro que viene con los nuevos cambios.
     * Por ejemplo el nombre pudo cambiar. En ese caso, se haria uso del método
     * update.
     * @return una otro con los cambios aplicados.
     */
    public OtroEntity update(OtroEntity pOtroEntity) {
        LOGGER.log(Level.INFO, "Actualizando editorial con id = {0}", pOtroEntity.getId());
        /* Note que hacemos uso de un método propio del EntityManager llamado merge() que recibe como argumento
        la editorial con los cambios, esto es similar a 
        "UPDATE table_name SET column1 = value1, column2 = value2, ... WHERE condition;" en SQL.
         */
        LOGGER.log(Level.INFO, "Saliendo de actualizar la editorial con id = {0}", pOtroEntity.getId());
        return em.merge(pOtroEntity);
    }
	
    /**
     *
     * Borra una otro de la base de datos recibiendo como argumento el id
     * de la otro.
     *
     * @param pOtroId: id correspondiente a la otro a borrar.
     */
    public void delete(Long pOtroId) {
        LOGGER.log(Level.INFO, "Borrando otro con id = {0}", pOtroId);
        // Se hace uso de mismo método que esta explicado en public OtroEntity 
        // find(Long id) para obtener la editorial a borrar.
        OtroEntity entity = em.find(OtroEntity.class, pOtroId);
        /* Note que una vez obtenido el objeto desde la base de datos llamado 
         "entity", volvemos hacer uso de un método propio del
         EntityManager para eliminar de la base de datos el objeto que 
         encontramos y queremos borrar.
         Es similar a "delete from EditorialEntity where id=id;" - "DELETE FROM 
         table_name WHERE condition;" en SQL.*/
        em.remove(entity);
        LOGGER.log(Level.INFO, "Saliendo de borrar la otro con id = {0}", pOtroId);
    }
	
    /**
     * Busca si hay alguna otro con el nombre que se envía de argumento.
     *
     * @param pName: Nombre de la otro que se está buscando
     * @return null si no existe ninguna otro con el nombre del argumento.
     * Si existe alguna devuelve la primera.
     */
    public OtroEntity findByName(String pName) {
        LOGGER.log(Level.INFO, "Consultando otro por nombre = {0}", pName);
        // Se crea un query para buscar editoriales con el nombre que recibe el método como argumento. ":name" es un placeholder que debe ser remplazado
        TypedQuery query = em.createQuery("Select e From OtroEntity e where e.nombre = :nombre", OtroEntity.class);
        // Se remplaza el placeholder ":name" con el valor del argumento 
        query = query.setParameter("nombre", pName);
        // Se invoca el query se obtiene la lista resultado
        List<OtroEntity> sameName = query.getResultList();
        OtroEntity result;
        if (sameName == null) {
            result = null;
        } else if (sameName.isEmpty()) {
            result = null;
        } else {
            result = sameName.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar otro por nombre = {0}", pName);
        return result;
    }
    
}

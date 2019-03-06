/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.persistence;

import co.edu.uniandes.csw.idiomas.entities.EncuentroEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Clase que maneja la persistencia para Encuentro. Se conecta a través Entity
 * Manager de javax.persistance con la base de datos SQL.
 * @author g.cubillosb
 */
@Stateless
public class EncuentroPersistence {
    
    // ----------------------------------------------------------------------
    // Atributos 
    // ----------------------------------------------------------------------
    
    /**
     * Logger para las acciones de la clase.
     */
    private static final Logger LOGGER = Logger.getLogger(EncuentroPersistence.class.getName());
    
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
     * @param pEncuentroEntity Objeto encuentro que se creará en la base de datos.
     * @return Devuelve la encuentro creada con un id dado por la base de datos.
     */
    public EncuentroEntity create (EncuentroEntity pEncuentroEntity)
    {
        LOGGER.log(Level.INFO, "Creando una encuentro nueva");
        em.persist(pEncuentroEntity);
        LOGGER.log(Level.INFO, "Saliendo de crear una encuentro nueva");
        return pEncuentroEntity;
    }
    
    /**
     * Devuelve todas las encuentros de la base de datos.
     *
     * @return una lista con todas las encuentros que encuentre en la base de
     * datos, "select u from EncuentroEntity u" es como un "select * from
     * EncuentroEntity;" - "SELECT * FROM table_name" en SQL.
     */
    public List<EncuentroEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todas las encuentros");
        // Se crea un query para buscar todas las encuentros en la base de datos.
        TypedQuery query = em.createQuery("select u from EncuentroEntity u", EncuentroEntity.class);
        // Se hace uso del método getResultList() que obtiene una lista de encuentros.
        return query.getResultList();
    }
	
    /**
     * Busca si hay alguna encuentro con el id que se envía de argumento
     *
     * @param pEncuentroId: id correspondiente a la encuentro buscada.
     * @return una encuentro.
     */
    public EncuentroEntity find(Long pEncuentroId) {
        LOGGER.log(Level.INFO, "Consultando editorial con id={0}", pEncuentroId);
        /* Note que se hace uso del metodo "find" propio del EntityManager, el cual recibe como argumento 
        el tipo de la clase y el objeto que nos hara el filtro en la base de datos en este caso el "id"
        Suponga que es algo similar a "select * from EncuentroEntity where id=id;" - "SELECT * FROM table_name WHERE condition;" en SQL.
         */
        return em.find(EncuentroEntity.class, pEncuentroId);
    }

    /**
     * Actualiza una encuentro.
     *
     * @param pEncuentroEntity: la encuentro que viene con los nuevos cambios.
     * Por ejemplo el nombre pudo cambiar. En ese caso, se haria uso del método
     * update.
     * @return una encuentro con los cambios aplicados.
     */
    public EncuentroEntity update(EncuentroEntity pEncuentroEntity) {
        LOGGER.log(Level.INFO, "Actualizando editorial con id = {0}", pEncuentroEntity.getId());
        /* Note que hacemos uso de un método propio del EntityManager llamado merge() que recibe como argumento
        la editorial con los cambios, esto es similar a 
        "UPDATE table_name SET column1 = value1, column2 = value2, ... WHERE condition;" en SQL.
         */
        LOGGER.log(Level.INFO, "Saliendo de actualizar la editorial con id = {0}", pEncuentroEntity.getId());
        return em.merge(pEncuentroEntity);
    }
	
    /**
     *
     * Borra una encuentro de la base de datos recibiendo como argumento el id
     * de la encuentro.
     *
     * @param pEncuentroId: id correspondiente a la encuentro a borrar.
     */
    public void delete(Long pEncuentroId) {
        LOGGER.log(Level.INFO, "Borrando encuentro con id = {0}", pEncuentroId);
        // Se hace uso de mismo método que esta explicado en public EncuentroEntity 
        // find(Long id) para obtener la editorial a borrar.
        EncuentroEntity entity = em.find(EncuentroEntity.class, pEncuentroId);
        /* Note que una vez obtenido el objeto desde la base de datos llamado 
         "entity", volvemos hacer uso de un método propio del
         EntityManager para eliminar de la base de datos el objeto que 
         encontramos y queremos borrar.
         Es similar a "delete from EditorialEntity where id=id;" - "DELETE FROM 
         table_name WHERE condition;" en SQL.*/
        em.remove(entity);
        LOGGER.log(Level.INFO, "Saliendo de borrar la encuentro con id = {0}", pEncuentroId);
    }
	
    /**
     * Busca si hay alguna encuentro con el nombre que se envía de argumento.
     *
     * @param pName: Nombre de la encuentro que se está buscando
     * @return null si no existe ninguna encuentro con el nombre del argumento.
     * Si existe alguna devuelve la primera.
     */
    public EncuentroEntity findByName(String pName) {
        LOGGER.log(Level.INFO, "Consultando encuentro por nombre = {0}", pName);
        // Se crea un query para buscar editoriales con el nombre que recibe el método como argumento. ":name" es un placeholder que debe ser remplazado
        TypedQuery query = em.createQuery("Select e From EncuentroEntity e where e.nombre = :nombre", EncuentroEntity.class);
        // Se remplaza el placeholder ":name" con el valor del argumento 
        query = query.setParameter("nombre", pName);
        // Se invoca el query se obtiene la lista resultado
        List<EncuentroEntity> sameName = query.getResultList();
        EncuentroEntity result;
        if (sameName == null) {
            result = null;
        } else if (sameName.isEmpty()) {
            result = null;
        } else {
            result = sameName.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar encuentro por nombre = {0}", pName);
        return result;
    }
    
}

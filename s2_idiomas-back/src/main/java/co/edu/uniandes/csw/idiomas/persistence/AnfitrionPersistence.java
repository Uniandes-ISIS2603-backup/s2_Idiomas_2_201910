/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.persistence;

import co.edu.uniandes.csw.idiomas.entities.AnfitrionEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author j.barbosaj 201717575
 */
@Stateless
public class AnfitrionPersistence {
    private static final Logger LOGGER = Logger.getLogger(AnfitrionPersistence.class.getName());
    
    @PersistenceContext(unitName = "idiomasPU")
    protected EntityManager em; 
    
    /**
     * Método para persisitir la entidad en la base de datos.
     *
     * @param AnfitrionEntity objeto Anfitrion que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public AnfitrionEntity create(AnfitrionEntity AnfitrionEntity) {
        LOGGER.log(Level.INFO, "Creando una Anfitrion nueva");        
        em.persist(AnfitrionEntity);
        LOGGER.log(Level.INFO, "Saliendo de crear una Anfitrion nueva");
        return AnfitrionEntity;
    }

    /**
     *
     * Borra una Anfitrion de la base de datos recibiendo como argumento el id
     * de la Anfitrion
     *
     * @param AnfitrionsId: id correspondiente a la Anfitrion a borrar.
     */
    public void delete(Long AnfitrionsId) {
        LOGGER.log(Level.INFO, "Borrando Anfitrion con id = {0}", AnfitrionsId);
        // Se hace uso de mismo método que esta explicado en public AnfitrionEntity find(Long id) para obtener la Anfitrion a borrar.
        AnfitrionEntity entity = em.find(AnfitrionEntity.class, AnfitrionsId);
        /* Note que una vez obtenido el objeto desde la base de datos llamado "entity", volvemos hacer uso de un método propio del
         EntityManager para eliminar de la base de datos el objeto que encontramos y queremos borrar.
         Es similar a "delete from AnfitrionEntity where id=id;" - "DELETE FROM table_nombre WHERE condition;" en SQL.*/
        em.remove(entity);
        LOGGER.log(Level.INFO, "Saliendo de borrar la Anfitrion con id = {0}", AnfitrionsId);
    }

    /**
     * Busca si hay alguna Anfitrion con el nombre que se envía de argumento
     *
     * @param nombre: Nombre de la Anfitrion que se está buscando
     * @return null si no existe ninguna Anfitrion con el nombre del argumento.
     * Si existe alguna devuelve la primera.
     */
    public AnfitrionEntity findByName(String nombre) {
        LOGGER.log(Level.INFO, "Consultando Anfitrion por nombre ", nombre);
        // Se crea un query para buscar Anfitrions con el nombre que recibe el método como argumento. ":nombre" es un placeholder que debe ser remplazado
        TypedQuery query = em.createQuery("Select e From AnfitrionEntity e where e.nombre = :nombre", AnfitrionEntity.class);
        // Se remplaza el placeholder ":nombre" con el valor del argumento 
        query = query.setParameter("nombre", nombre);
        // Se invoca el query se obtiene la lista resultado
        List<AnfitrionEntity> sameName = query.getResultList();
        AnfitrionEntity result;
        if (sameName == null) {
            result = null;
        } else if (sameName.isEmpty()) {
            result = null;
        } else {
            result = sameName.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar Anfitrion por nombre ", nombre);
        return result;
    }
}

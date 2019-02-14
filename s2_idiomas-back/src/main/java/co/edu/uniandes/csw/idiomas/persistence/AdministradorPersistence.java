/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.persistence;

import co.edu.uniandes.csw.idiomas.entities.AdministradorEntity;
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
public class AdministradorPersistence {
    private static final Logger LOGGER = Logger.getLogger(AdministradorPersistence.class.getName());
    
    @PersistenceContext(unitName = "idiomasPU")
    protected EntityManager em; 
    
    /**
     * Método para persisitir la entidad en la base de datos.
     *
     * @param AdministradorEntity objeto Administrador que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public AdministradorEntity create(AdministradorEntity AdministradorEntity) {
        LOGGER.log(Level.INFO, "Creando una Administrador nueva");        
        em.persist(AdministradorEntity);
        LOGGER.log(Level.INFO, "Saliendo de crear una Administrador nueva");
        return AdministradorEntity;
    }

    /**
     *
     * Borra una Administrador de la base de datos recibiendo como argumento el id
     * de la Administrador
     *
     * @param AdministradorsId: id correspondiente a la Administrador a borrar.
     */
    public void delete(Long AdministradorsId) {
        LOGGER.log(Level.INFO, "Borrando Administrador con id = {0}", AdministradorsId);
        // Se hace uso de mismo método que esta explicado en public AdministradorEntity find(Long id) para obtener la Administrador a borrar.
        AdministradorEntity entity = em.find(AdministradorEntity.class, AdministradorsId);
        /* Note que una vez obtenido el objeto desde la base de datos llamado "entity", volvemos hacer uso de un método propio del
         EntityManager para eliminar de la base de datos el objeto que encontramos y queremos borrar.
         Es similar a "delete from AdministradorEntity where id=id;" - "DELETE FROM table_nombre WHERE condition;" en SQL.*/
        em.remove(entity);
        LOGGER.log(Level.INFO, "Saliendo de borrar la Administrador con id = {0}", AdministradorsId);
    }

    /**
     * Busca si hay alguna Administrador con el nombre que se envía de argumento
     *
     * @param nombre: Nombre de la Administrador que se está buscando
     * @return null si no existe ninguna Administrador con el nombre del argumento.
     * Si existe alguna devuelve la primera.
     */
    public AdministradorEntity findByName(String nombre) {
        LOGGER.log(Level.INFO, "Consultando Administrador por nombre ", nombre);
        // Se crea un query para buscar Administradors con el nombre que recibe el método como argumento. ":nombre" es un placeholder que debe ser remplazado
        TypedQuery query = em.createQuery("Select e From AdministradorEntity e where e.nombre = :nombre", AdministradorEntity.class);
        // Se remplaza el placeholder ":nombre" con el valor del argumento 
        query = query.setParameter("nombre", nombre);
        // Se invoca el query se obtiene la lista resultado
        List<AdministradorEntity> sameName = query.getResultList();
        AdministradorEntity result;
        if (sameName == null) {
            result = null;
        } else if (sameName.isEmpty()) {
            result = null;
        } else {
            result = sameName.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar Administrador por nombre ", nombre);
        return result;
    }
}

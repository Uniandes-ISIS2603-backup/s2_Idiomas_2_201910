/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.persistence;

import co.edu.uniandes.csw.idiomas.entities.UsuarioEntity;
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
public class UsuarioPersistence 
{
    private static final Logger LOGGER = Logger.getLogger(UsuarioPersistence.class.getName());
    
    @PersistenceContext(unitName = "idiomasPU")
    protected EntityManager em; 
    
    /**
     * Método para persisitir la entidad en la base de datos.
     *
     * @param UsuarioEntity objeto Usuario que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public UsuarioEntity create(UsuarioEntity UsuarioEntity) {
        LOGGER.log(Level.INFO, "Creando una Usuario nueva");        
        em.persist(UsuarioEntity);
        LOGGER.log(Level.INFO, "Saliendo de crear una Usuario nueva");
        return UsuarioEntity;
    }

    /**
     *
     * Borra una Usuario de la base de datos recibiendo como argumento el id
     * de la Usuario
     *
     * @param UsuariosId: id correspondiente a la Usuario a borrar.
     */
    public void delete(Long UsuariosId) {
        LOGGER.log(Level.INFO, "Borrando Usuario con id = {0}", UsuariosId);
        // Se hace uso de mismo método que esta explicado en public UsuarioEntity find(Long id) para obtener la Usuario a borrar.
        UsuarioEntity entity = em.find(UsuarioEntity.class, UsuariosId);
        /* Note que una vez obtenido el objeto desde la base de datos llamado "entity", volvemos hacer uso de un método propio del
         EntityManager para eliminar de la base de datos el objeto que encontramos y queremos borrar.
         Es similar a "delete from UsuarioEntity where id=id;" - "DELETE FROM table_nombre WHERE condition;" en SQL.*/
        em.remove(entity);
        LOGGER.log(Level.INFO, "Saliendo de borrar la Usuario con id = {0}", UsuariosId);
    }

    /**
     * Busca si hay alguna Usuario con el nombre que se envía de argumento
     *
     * @param nombre: Nombre de la Usuario que se está buscando
     * @return null si no existe ninguna Usuario con el nombre del argumento.
     * Si existe alguna devuelve la primera.
     */
    public UsuarioEntity findByName(String nombre) {
        LOGGER.log(Level.INFO, "Consultando Usuario por nombre ", nombre);
        // Se crea un query para buscar Usuarios con el nombre que recibe el método como argumento. ":nombre" es un placeholder que debe ser remplazado
        TypedQuery query = em.createQuery("Select e From UsuarioEntity e where e.nombre = :nombre", UsuarioEntity.class);
        // Se remplaza el placeholder ":nombre" con el valor del argumento 
        query = query.setParameter("nombre", nombre);
        // Se invoca el query se obtiene la lista resultado
        List<UsuarioEntity> sameName = query.getResultList();
        UsuarioEntity result;
        if (sameName == null) {
            result = null;
        } else if (sameName.isEmpty()) {
            result = null;
        } else {
            result = sameName.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar Usuario por nombre ", nombre);
        return result;
    }
}

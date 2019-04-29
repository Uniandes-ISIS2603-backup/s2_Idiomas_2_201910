/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.ejb;

import co.edu.uniandes.csw.idiomas.entities.UsuarioEntity;
import co.edu.uniandes.csw.idiomas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.idiomas.persistence.UsuarioPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author j.barbosaj 201717575
 */
@Stateless
public class UsuarioLogic 
{
     private static final Logger LOGGER = Logger.getLogger(UsuarioLogic.class.getName());

    @Inject
    private UsuarioPersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

    /**
     * Crea una usuario en la persistencia.
     *
     * @param usuarioEntity La entidad que representa la usuario a
     * persistir.
     * @return La entiddad de la usuario luego de persistirla.
     * @throws BusinessLogicException Si la usuario a persistir ya existe.
     */
    public UsuarioEntity createUsuario(UsuarioEntity usuarioEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de creación de la usuario");
       // Verifica la regla de negocio que dice que no puede haber un usuario con el nombre vacio
        if(usuarioEntity.getNombre().compareTo("")==0)
        {
            throw new BusinessLogicException("El Usuario no tiene nombre");
        }
        // Verifica la regla de negocio que dice que no puede haber un usuario con contraseña vacio
        else if(usuarioEntity.getContrasenia()==null)
        {
            throw new BusinessLogicException("El Usuario no tiene contraseña");
        }
        // Verifica la regla de negocio que dice que no puede haber dos usuario con el mismo nombre
        else if (persistence.findByName(usuarioEntity.getNombre()) != null) {
            throw new BusinessLogicException("Ya existe una Usuario con el nombre \"" + usuarioEntity.getNombre() + "\"");
        }
        // Invoca la persistencia para crear la usuario
        persistence.create(usuarioEntity);
        LOGGER.log(Level.INFO, "Termina proceso de creación de la usuario");
        return usuarioEntity;
    }

    /**
     *
     * Obtener todas las usuario existentes en la base de datos.
     *
     * @return una lista de usuario.
     */
    public List<UsuarioEntity> getUsuarios() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todas las usuario");
        // Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
        List<UsuarioEntity> usuario = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todas las usuario");
        return usuario;
    }

    /**
     *
     * Obtener una usuario por medio de su id.
     *
     * @param usuarioId: id de la usuario para ser buscada.
     * @return la usuario solicitada por medio de su id.
     */
    public UsuarioEntity getUsuario(Long usuarioId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la usuario con id = {0}", usuarioId);
        // Note que, por medio de la inyección de dependencias se llama al método "find(id)" que se encuentra en la persistencia.        
        UsuarioEntity usuarioEntity = persistence.find(usuarioId);
        if (usuarioEntity == null) {
            LOGGER.log(Level.SEVERE, "La usuario con el id = {0} no existe", usuarioId);
        }
        
        LOGGER.log(Level.INFO, "Termina proceso de consultar la usuario con id = {0}", usuarioId);
        return usuarioEntity;
    }
    
     /**
     *
     * Obtener una usuario por medio de su nombre.
     *
     * @param nombre: id de la usuario para ser buscada.
     * @return la usuario solicitada por medio de su id.
     */
    public UsuarioEntity getUsuarioNombre(String nombre) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la usuario con el nombre = {0}", nombre);
        // Note que, por medio de la inyección de dependencias se llama al método "find(id)" que se encuentra en la persistencia.        
        UsuarioEntity usuarioEntity = persistence.findByName(nombre);
        if (usuarioEntity == null) {
            LOGGER.log(Level.SEVERE, "La usuario con el id = {0} no existe", nombre);
        }
        
        LOGGER.log(Level.INFO, "Termina proceso de consultar la usuario con id = {0}", nombre);
        return usuarioEntity;
    }

    /**
     *
     * Actualizar una usuario.
     *
     * @param usuarioId: id de la usuario para buscarla en la base de
     * datos.
     * @param usuarioEntity: usuario con los cambios para ser actualizada,
     * por ejemplo el nombre.
     * @return la usuario con los cambios actualizados en la base de datos.
     */
    public UsuarioEntity updateUsuario(Long usuarioId, UsuarioEntity usuarioEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar la usuario con id = {0}", usuarioId);
        //verifica que el Usuario que se va a actualizar existe
        UsuarioEntity eusuarioEntity = persistence.find(usuarioId);
        if (eusuarioEntity == null) {
            throw new BusinessLogicException( "La usuario con el id = {0} no existe" + usuarioId);
        }
         // Verifica la regla de negocio que dice que no puede haber un usuario con el nombre vacio
        else if(usuarioEntity.getNombre().compareTo("")==0)
        {
            throw new BusinessLogicException("El Usuario no tiene nombre");
        }
        // Verifica la regla de negocio que dice que no puede haber un usuario con contraseña vacio
        else if(usuarioEntity.getContrasenia()==null)
        {
            throw new BusinessLogicException("El Usuario no tiene contraseña");
        }
        // Verifica la regla de negocio que dice que no puede haber dos usuario con el mismo nombre
        else if (persistence.findByName(usuarioEntity.getNombre()) != null) {
            throw new BusinessLogicException("Ya existe una Usuario con el nombre \"" + usuarioEntity.getNombre() + "\"");
        }
        // Note que, por medio de la inyección de dependencias se llama al método "update(entity)" que se encuentra en la persistencia.
        UsuarioEntity newEntity = persistence.update(usuarioEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar la usuario con id = {0}", usuarioEntity.getId());
        return newEntity;
    }

    /**
     * Borrar un usuario
     *
     * @param usuarioId: id de la usuario a borrar
     * @throws BusinessLogicException Si la usuario a eliminar tiene libros.
     */
    public void deleteUsuario(Long usuarioId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar la usuario con id = {0}", usuarioId);  
        if(persistence.find(usuarioId)== null)
        {
            throw new BusinessLogicException("el usuario no existe");
        }
        persistence.delete(usuarioId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar la usuario con id = {0}", usuarioId);
    }
}

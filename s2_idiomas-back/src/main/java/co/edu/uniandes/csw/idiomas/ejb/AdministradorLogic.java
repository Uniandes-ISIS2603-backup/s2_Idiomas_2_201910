/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.ejb;

import co.edu.uniandes.csw.idiomas.entities.AdministradorEntity;
import co.edu.uniandes.csw.idiomas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.idiomas.persistence.AdministradorPersistence;
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
public class AdministradorLogic {
    private static final Logger LOGGER = Logger.getLogger(AdministradorLogic.class.getName());

    @Inject
    private AdministradorPersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

    /**
     * Crea una administrador en la persistencia.
     *
     * @param administradorEntity La entidad que representa la administrador a
     * persistir.
     * @return La entiddad de la administrador luego de persistirla.
     * @throws BusinessLogicException Si la administrador a persistir ya existe.
     */
    public AdministradorEntity createAdministrador(AdministradorEntity administradorEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de creación de la administrador");
        // Verifica la regla de negocio que dice que no puede haber un administrador con el nombre vacio
        if(administradorEntity.getNombre().compareTo("")==0)
        {
            throw new BusinessLogicException("El Administrador no tiene nombre");
        }
        // Verifica la regla de negocio que dice que no puede haber un administrador con contraseña vacio
        else if(administradorEntity.getContrasenia()== null)
        {
            throw new BusinessLogicException("El Administrador no tiene contraseña");
        }
        // Verifica la regla de negocio que dice que no puede haber dos administrador con el mismo nombre
        else if (persistence.findByName(administradorEntity.getNombre()) != null) {
            throw new BusinessLogicException("Ya existe una Administrador con el nombre \"" + administradorEntity.getNombre() + "\"");
        }
        // Invoca la persistencia para crear la administrador
        persistence.create(administradorEntity);
        LOGGER.log(Level.INFO, "Termina proceso de creación de la administrador");
        return administradorEntity;
    }

    /**
     *
     * Obtener todas las administrador existentes en la base de datos.
     *
     * @return una lista de administrador.
     */
    public List<AdministradorEntity> getAdministradors() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todas las administrador");
        // Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
        List<AdministradorEntity> administrador = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todas las administrador");
        return administrador;
    }

    /**
     *
     * Obtener una administrador por medio de su id.
     *
     * @param administradorId: id de la administrador para ser buscada.
     * @return la administrador solicitada por medio de su id.
     */
    public AdministradorEntity getAdministrador(Long administradorId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la administrador con id = {0}", administradorId);
        // Note que, por medio de la inyección de dependencias se llama al método "find(id)" que se encuentra en la persistencia.
        AdministradorEntity administradorEntity = persistence.find(administradorId);
        if (administradorEntity == null) {
            LOGGER.log(Level.SEVERE, "La administrador con el id = {0} no existe", administradorId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar la administrador con id = {0}", administradorId);
        return administradorEntity;
    }

    /**
     *
     * Actualizar una administrador.
     *
     * @param administradorId: id de la administrador para buscarla en la base de
     * datos.
     * @param administradorEntity: administrador con los cambios para ser actualizada,
     * por ejemplo el nombre.
     * @return la administrador con los cambios actualizados en la base de datos.
     */
    public AdministradorEntity updateAdministrador(Long administradorId, AdministradorEntity administradorEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar la administrador con id = {0}", administradorId);
        LOGGER.log(Level.INFO, "Inicia proceso de creación de la administrador");
       //verifica que el Administrador que se va a actualizar existe
        AdministradorEntity eadministradorEntity = persistence.find(administradorId);
        if (eadministradorEntity == null) {
            throw new BusinessLogicException( "La administrador con el id = {0} no existe" + administradorId);
        }
         // Verifica la regla de negocio que dice que no puede haber un administrador con el nombre vacio
        else if(administradorEntity.getNombre().compareTo("")==0)
        {
            throw new BusinessLogicException("El Administrador no tiene nombre");
        }
        // Verifica la regla de negocio que dice que no puede haber un administrador con contraseña vacio
        else if(administradorEntity.getContrasenia()==null)
        {
            throw new BusinessLogicException("El Administrador no tiene contraseña");
        }
        // Verifica la regla de negocio que dice que no puede haber dos administrador con el mismo nombre
        else if (persistence.findByName(administradorEntity.getNombre()) != null) {
            throw new BusinessLogicException("Ya existe una Administrador con el nombre \"" + administradorEntity.getNombre() + "\"");
        }
        // Note que, por medio de la inyección de dependencias se llama al método "update(entity)" que se encuentra en la persistencia.
        AdministradorEntity newEntity = persistence.update(administradorEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar la administrador con id = {0}", administradorEntity.getId());
        return newEntity;
    }

    /**
     * Borrar un administrador
     *
     * @param administradorId: id de la administrador a borrar
     * @throws BusinessLogicException Si la administrador a eliminar tiene libros.
     */
    public void deleteAdministrador(Long administradorId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar la administrador con id = {0}", administradorId); 
        if(persistence.find(administradorId)== null)
        {
            throw new BusinessLogicException("el administrador no existe");
        }
        persistence.delete(administradorId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar la administrador con id = {0}", administradorId);
    }
}

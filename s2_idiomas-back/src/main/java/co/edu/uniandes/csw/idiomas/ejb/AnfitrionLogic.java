/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.ejb;

import co.edu.uniandes.csw.idiomas.entities.AnfitrionEntity;
import co.edu.uniandes.csw.idiomas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.idiomas.persistence.AnfitrionPersistence;
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
public class AnfitrionLogic
{
     private static final Logger LOGGER = Logger.getLogger(AnfitrionLogic.class.getName());

    @Inject
    private AnfitrionPersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

    /**
     * Crea una anfitrion en la persistencia.
     *
     * @param anfitrionEntity La entidad que representa la anfitrion a
     * persistir.
     * @return La entiddad de la anfitrion luego de persistirla.
     * @throws BusinessLogicException Si la anfitrion a persistir ya existe.
     */
    public AnfitrionEntity createAnfitrion(AnfitrionEntity anfitrionEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de creación de la anfitrion");
         // Verifica la regla de negocio que dice que no puede haber un anfitrion con el nombre vacio
        if(anfitrionEntity.getNombre().compareTo("")==0)
        {
            throw new BusinessLogicException("El Anfitrion no tiene nombre");
        }
        // Verifica la regla de negocio que dice que no puede haber un anfitrion con contraseña vacio
        else if(anfitrionEntity.getContrasenia()==null)
        {
            throw new BusinessLogicException("El Anfitrion no tiene contraseña");
        }
        // Verifica la regla de negocio que dice que no puede haber dos anfitrion con el mismo nombre
        if (persistence.findByName(anfitrionEntity.getNombre()) != null) {
            throw new BusinessLogicException("Ya existe una Anfitrion con el nombre \"" + anfitrionEntity.getNombre() + "\"");
        }
        // Invoca la persistencia para crear la anfitrion
        persistence.create(anfitrionEntity);
        LOGGER.log(Level.INFO, "Termina proceso de creación de la anfitrion");
        return anfitrionEntity;
    }

    /**
     *
     * Obtener todas las anfitrion existentes en la base de datos.
     *
     * @return una lista de anfitrion.
     */
    public List<AnfitrionEntity> getAnfitrions() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todas las anfitrion");
        // Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
        List<AnfitrionEntity> anfitrion = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todas las anfitrion");
        return anfitrion;
    }

    /**
     *
     * Obtener una anfitrion por medio de su id.
     *
     * @param anfitrionId: id de la anfitrion para ser buscada.
     * @return la anfitrion solicitada por medio de su id.
     */
    public AnfitrionEntity getAnfitrion(Long anfitrionId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la anfitrion con id = {0}", anfitrionId);
        // Note que, por medio de la inyección de dependencias se llama al método "find(id)" que se encuentra en la persistencia.
        AnfitrionEntity anfitrionEntity = persistence.find(anfitrionId);
        if (anfitrionEntity == null) {
            LOGGER.log(Level.SEVERE, "La anfitrion con el id = {0} no existe", anfitrionId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar la anfitrion con id = {0}", anfitrionId);
        return anfitrionEntity;
    }

    /**
     *
     * Actualizar una anfitrion.
     *
     * @param anfitrionId: id de la anfitrion para buscarla en la base de
     * datos.
     * @param anfitrionEntity: anfitrion con los cambios para ser actualizada,
     * por ejemplo el nombre.
     * @return la anfitrion con los cambios actualizados en la base de datos.
     */
    public AnfitrionEntity updateAnfitrion(Long anfitrionId, AnfitrionEntity anfitrionEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar la anfitrion con id = {0}", anfitrionId);
        //verifica que el Anfitrion que se va a actualizar existe
        AnfitrionEntity eanfitrionEntity = persistence.find(anfitrionId);
        if (eanfitrionEntity == null) {
            throw new BusinessLogicException( "La anfitrion con el id = {0} no existe" + anfitrionId);
        }
         // Verifica la regla de negocio que dice que no puede haber un anfitrion con el nombre vacio
        else if(anfitrionEntity.getNombre().compareTo("")==0)
        {
            throw new BusinessLogicException("El Anfitrion no tiene nombre");
        }
        // Verifica la regla de negocio que dice que no puede haber un anfitrion con contraseña vacio
        else if(anfitrionEntity.getContrasenia()==null)
        {
            throw new BusinessLogicException("El Anfitrion no tiene contraseña");
        }
        // Verifica la regla de negocio que dice que no puede haber dos anfitrion con el mismo nombre
        else if (persistence.findByName(anfitrionEntity.getNombre()) != null) {
            throw new BusinessLogicException("Ya existe una Anfitrion con el nombre \"" + anfitrionEntity.getNombre() + "\"");
        }
        // Note que, por medio de la inyección de dependencias se llama al método "update(entity)" que se encuentra en la persistencia.       
        AnfitrionEntity newEntity = persistence.update(anfitrionEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar la anfitrion con id = {0}", anfitrionEntity.getId());
        return newEntity;
    }

    /**
     * Borrar un anfitrion
     *
     * @param anfitrionId: id de la anfitrion a borrar
     * @throws BusinessLogicException Si la anfitrion a eliminar tiene libros.
     */
    public void deleteAnfitrion(Long anfitrionId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar la anfitrion con id = {0}", anfitrionId);     
        if(persistence.find(anfitrionId)== null)
        {
            throw new BusinessLogicException("el anfitrion no existe");
        }
        persistence.delete(anfitrionId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar la anfitrion con id = {0}", anfitrionId);
    }
}

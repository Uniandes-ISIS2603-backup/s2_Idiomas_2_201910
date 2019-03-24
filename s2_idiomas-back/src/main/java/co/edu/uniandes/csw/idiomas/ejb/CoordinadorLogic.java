/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.ejb;

import co.edu.uniandes.csw.idiomas.entities.CoordinadorEntity;
import co.edu.uniandes.csw.idiomas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.idiomas.persistence.CoordinadorPersistence;
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
public class CoordinadorLogic {
     private static final Logger LOGGER = Logger.getLogger(CoordinadorLogic.class.getName());

    @Inject
    private CoordinadorPersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

    /**
     * Crea una coordinador en la persistencia.
     *
     * @param coordinadorEntity La entidad que representa la coordinador a
     * persistir.
     * @return La entiddad de la coordinador luego de persistirla.
     * @throws BusinessLogicException Si la coordinador a persistir ya existe.
     */
    public CoordinadorEntity createCoordinador(CoordinadorEntity coordinadorEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de creación de la coordinador");
        // Verifica la regla de negocio que dice que no puede haber un coordinador con el nombre vacio
        if(coordinadorEntity.getNombre().compareTo("")==0)
        {
            throw new BusinessLogicException("El Coordinador no tiene nombre");
        }
        // Verifica la regla de negocio que dice que no puede haber un coordinador con contraseña vacio
        else if(coordinadorEntity.getContrasenia()==null)
        {
            throw new BusinessLogicException("El Coordinador no tiene contraseña");
        }
        // Verifica la regla de negocio que dice que no puede haber dos coordinador con el mismo nombre
        if (persistence.findByName(coordinadorEntity.getNombre()) != null) {
            throw new BusinessLogicException("Ya existe una Coordinador con el nombre \"" + coordinadorEntity.getNombre() + "\"");
        }
        // Invoca la persistencia para crear la coordinador
        persistence.create(coordinadorEntity);
        LOGGER.log(Level.INFO, "Termina proceso de creación de la coordinador");
        return coordinadorEntity;
    }

    /**
     *
     * Obtener todas las coordinador existentes en la base de datos.
     *
     * @return una lista de coordinador.
     */
    public List<CoordinadorEntity> getCoordinadors() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todas las coordinador");
        // Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
        List<CoordinadorEntity> coordinador = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todas las coordinador");
        return coordinador;
    }

    /**
     *
     * Obtener una coordinador por medio de su id.
     *
     * @param coordinadorId: id de la coordinador para ser buscada.
     * @return la coordinador solicitada por medio de su id.
     */
    public CoordinadorEntity getCoordinador(Long coordinadorId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la coordinador con id = {0}", coordinadorId);
        // Note que, por medio de la inyección de dependencias se llama al método "find(id)" que se encuentra en la persistencia.
        CoordinadorEntity coordinadorEntity = persistence.find(coordinadorId);
        if (coordinadorEntity == null) {
            LOGGER.log(Level.SEVERE, "La coordinador con el id = {0} no existe", coordinadorId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar la coordinador con id = {0}", coordinadorId);
        return coordinadorEntity;
    }

    /**
     *
     * Actualizar una coordinador.
     *
     * @param coordinadorId: id de la coordinador para buscarla en la base de
     * datos.
     * @param coordinadorEntity: coordinador con los cambios para ser actualizada,
     * por ejemplo el nombre.
     * @return la coordinador con los cambios actualizados en la base de datos.
     */
    public CoordinadorEntity updateCoordinador(Long coordinadorId, CoordinadorEntity coordinadorEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar la coordinador con id = {0}", coordinadorId);
       //verifica que el Coordinador que se va a actualizar existe
        CoordinadorEntity ecoodinadorEntity = persistence.find(coordinadorId);
        if (ecoodinadorEntity == null) {
            throw new BusinessLogicException( "La coodinador con el id = {0} no existe" + coordinadorId);
        }
         // Verifica la regla de negocio que dice que no puede haber un coodinador con el nombre vacio
        else if(coordinadorEntity.getNombre().compareTo("")==0)
        {
            throw new BusinessLogicException("El Coordinador no tiene nombre");
        }
        // Verifica la regla de negocio que dice que no puede haber un coodinador con contraseña vacio
        else if(coordinadorEntity.getContrasenia()==null)
        {
            throw new BusinessLogicException("El Coordinador no tiene contraseña");
        }
        // Verifica la regla de negocio que dice que no puede haber dos coodinador con el mismo nombre
        else if (persistence.findByName(coordinadorEntity.getNombre()) != null) {
            throw new BusinessLogicException("Ya existe una Coordinador con el nombre \"" + coordinadorEntity.getNombre() + "\"");
        }
        // Note que, por medio de la inyección de dependencias se llama al método "update(entity)" que se encuentra en la persistencia.
        CoordinadorEntity newEntity = persistence.update(coordinadorEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar la coordinador con id = {0}", coordinadorEntity.getId());
        return newEntity;
    }

    /**
     * Borrar un coordinador
     *
     * @param coordinadorId: id de la coordinador a borrar
     * @throws BusinessLogicException Si la coordinador a eliminar tiene libros.
     */
    public void deleteCoordinador(Long coordinadorId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar la coordinador con id = {0}", coordinadorId);    
        if(persistence.find(coordinadorId)== null)
        {
            throw new BusinessLogicException("el coordinador no existe");
        }
        persistence.delete(coordinadorId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar la coordinador con id = {0}", coordinadorId);
    }
}

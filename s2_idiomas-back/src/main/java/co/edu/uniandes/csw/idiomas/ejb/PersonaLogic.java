
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.ejb;

import co.edu.uniandes.csw.idiomas.entities.PersonaEntity;
import co.edu.uniandes.csw.idiomas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.idiomas.persistence.PersonaPersistence;
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
public class PersonaLogic
{
      private static final Logger LOGGER = Logger.getLogger(PersonaLogic.class.getName());

    @Inject
    private PersonaPersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

    /**
     * Crea una persona en la persistencia.
     *
     * @param personaEntity La entidad que representa la persona a
     * persistir.
     * @return La entiddad de la persona luego de persistirla.
     * @throws BusinessLogicException Si la persona a persistir ya existe.
     */
    public PersonaEntity createPersona(PersonaEntity personaEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de creación de la persona");
        // Verifica la regla de negocio que dice que no puede haber dos persona con el mismo nombre
        if (persistence.findByName(personaEntity.getNombre()) != null) {
            throw new BusinessLogicException("Ya existe una Persona con el nombre \"" + personaEntity.getNombre() + "\"");
        }
        // Invoca la persistencia para crear la persona
        persistence.create(personaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de creación de la persona");
        return personaEntity;
    }

    /**
     *
     * Obtener todas las persona existentes en la base de datos.
     *
     * @return una lista de persona.
     */
    public List<PersonaEntity> getPersonas() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todas las persona");
        // Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
        List<PersonaEntity> persona = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todas las persona");
        return persona;
    }

    /**
     *
     * Obtener una persona por medio de su id.
     *
     * @param personaId: id de la persona para ser buscada.
     * @return la persona solicitada por medio de su id.
     */
    public PersonaEntity getPersona(Long personaId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la persona con id = {0}", personaId);
        // Note que, por medio de la inyección de dependencias se llama al método "find(id)" que se encuentra en la persistencia.
        PersonaEntity personaEntity = persistence.find(personaId);
        if (personaEntity == null) {
            LOGGER.log(Level.SEVERE, "La persona con el id = {0} no existe", personaId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar la persona con id = {0}", personaId);
        return personaEntity;
    }

    /**
     *
     * Actualizar una persona.
     *
     * @param personaId: id de la persona para buscarla en la base de
     * datos.
     * @param personaEntity: persona con los cambios para ser actualizada,
     * por ejemplo el nombre.
     * @return la persona con los cambios actualizados en la base de datos.
     */
    public PersonaEntity updatePersona(Long personaId, PersonaEntity personaEntity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar la persona con id = {0}", personaId);
        // Note que, por medio de la inyección de dependencias se llama al método "update(entity)" que se encuentra en la persistencia.
        PersonaEntity newEntity = persistence.update(personaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar la persona con id = {0}", personaEntity.getId());
        return newEntity;
    }

    /**
     * Borrar un persona
     *
     * @param personaId: id de la persona a borrar
     * @throws BusinessLogicException Si la persona a eliminar tiene libros.
     */
    public void deletePersona(Long personaId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar la persona con id = {0}", personaId);        
        persistence.delete(personaId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar la persona con id = {0}", personaId);
    }
}


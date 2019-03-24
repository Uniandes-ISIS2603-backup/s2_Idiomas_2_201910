/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.ejb;

import co.edu.uniandes.csw.idiomas.entities.ComentarioEntity;
import co.edu.uniandes.csw.idiomas.entities.PersonaEntity;
import co.edu.uniandes.csw.idiomas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.idiomas.persistence.ComentarioPersistence;
import co.edu.uniandes.csw.idiomas.persistence.PersonaPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;

/**
 *
 * @author se.gamboa
 */
public class ComentarioPersonaLogic {
        /**
     * Logger para las asociaciones de la clase.
     */
    private static final Logger LOGGER = Logger.getLogger(ComentarioPersonaLogic.class.getName());

    /**
     * Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.
     */
    @Inject
    private ComentarioPersistence comentarioPersonaPersistence;

    /**
     * Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.
     */
    @Inject
    private PersonaPersistence PersonaPersistence;
    
    // -------------------------------------------------------------------------
    // Métodos
    // -------------------------------------------------------------------------

    /**
     * Asocia un ComentarioPersona existente a un Persona
     *
     * @param personaId Identificador de la instancia de Persona
     * @param comentariosId Identificador de la instancia de ComentarioPersona
     * @return Instancia de ComentarioPersonaEntity que fue asociada a Persona
     */
    public ComentarioEntity addComentarioPersona(Long personaId, Long comentariosId) {
        LOGGER.log(Level.INFO, "Inicia proceso de asociarle un comentario a la persona con id = {0}", personaId);
        PersonaEntity personaEntity = PersonaPersistence.find(personaId);
        ComentarioEntity comentarioPersonaEntity = comentarioPersonaPersistence.find(comentariosId);
        comentarioPersonaEntity.setAutor(personaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de asociarle un comentario a la persona con id = {0}", personaId);
        return comentarioPersonaPersistence.find(comentariosId);
    }

    /**
     * Obtiene una colección de instancias de ComentarioPersonaEntity asociadas a una
     * instancia de Persona
     *
     * @param PersonaesId Identificador de la instancia de Persona
     * @return Colección de instancias de ComentarioPersonaEntity asociadas a la instancia de
     * Persona
     */
    public List<ComentarioEntity> getComentarios(Long PersonaesId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los comentarios del Persona con id = {0}", PersonaesId);
        return PersonaPersistence.find(PersonaesId).getComentarioEntitys();
    }

    /**
     * Obtiene una instancia de ComentarioPersonaEntity asociada a una instancia de Persona
     *
     * @param PersonaesId Identificador de la instancia de Persona
     * @param comentariosId Identificador de la instancia de ComentarioPersona
     * @return La entidadd de Libro del Persona
     * @throws BusinessLogicException Si el libro no está asociado al Persona
     */
    public ComentarioEntity getComentarioPersona(Long PersonaesId, Long comentariosId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el libro con id = {0} del Persona con id = " + PersonaesId, comentariosId);
        List<ComentarioEntity> comentarios = PersonaPersistence.find(PersonaesId).getComentarioEntitys();
        ComentarioEntity comentarioPersonaEntity = comentarioPersonaPersistence.find(comentariosId);
        int index = comentarios.indexOf(comentarioPersonaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de consultar el libro con id = {0} del Persona con id = " + PersonaesId, comentariosId);
        if (index >= 0) {
            return comentarios.get(index);
        }
        throw new BusinessLogicException("El libro no está asociado al Persona");
    }

    /**
     * Remplaza las instancias de ComentarioPersona asociadas a una instancia de Persona
     *
     * @param PersonaId Identificador de la instancia de Persona
     * @param comentarios Colección de instancias de ComentarioPersonaEntity a asociar a instancia
     * de Persona
     * @return Nueva colección de ComentarioPersonaEntity asociada a la instancia de Persona
     */
    public List<ComentarioEntity> replaceComentarios(Long PersonaId, List<ComentarioEntity> comentarios) {
        LOGGER.log(Level.INFO, "Inicia proceso de reemplazar los comentarios asocidos al Persona con id = {0}", PersonaId);
        PersonaEntity PersonaEntity = PersonaPersistence.find(PersonaId);
        List<ComentarioEntity> comentarioPersonaList = comentarioPersonaPersistence.findAll();
        for (ComentarioEntity comentarioPersona : comentarioPersonaList) {
            if (comentarios.contains(comentarioPersona)) {
                if (!comentarioPersona.getAutor().equals(PersonaEntity)) {
                    comentarioPersona.setAutor(PersonaEntity);
                }
            } else {
                comentarioPersona.setAutor(null);
            }
        }
        PersonaEntity.setComentarioEntitys(comentarios);
        LOGGER.log(Level.INFO, "Termina proceso de reemplazar los comentarios asocidos al Persona con id = {0}", PersonaId);
        return PersonaEntity.getComentarioEntitys();
    }

    /**
     * Desasocia un ComentarioPersona existente de un Persona existente
     *
     * @param PersonaesId Identificador de la instancia de Persona
     * @param comentariosId Identificador de la instancia de ComentarioPersona
     */
    public void removeComentarioPersona(Long PersonaesId, Long comentariosId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar un libro del Persona con id = {0}", PersonaesId);
        ComentarioEntity comentarioPersonaEntity = comentarioPersonaPersistence.find(comentariosId);
        comentarioPersonaEntity.setAutor(null);
        LOGGER.log(Level.INFO, "Termina proceso de borrar un libro del Persona con id = {0}", PersonaesId);
    }
}

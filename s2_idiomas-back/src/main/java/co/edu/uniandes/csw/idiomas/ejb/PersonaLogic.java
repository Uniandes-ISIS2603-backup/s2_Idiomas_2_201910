/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.ejb;

import co.edu.uniandes.csw.idiomas.entities.PersonaEntity;
import co.edu.uniandes.csw.idiomas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.idiomas.persistence.PersonaPersistence;
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
     * Crea una perosna en la persistencia.
     *
     * @param perosnaEntity La entidad que representa la perosna a
     * persistir.
     * @return La entiddad de la perosna luego de persistirla.
     * @throws BusinessLogicException Si la perosna a persistir ya existe.
     */
    public PersonaEntity createPersona(PersonaEntity perosnaEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de creación de la perosna");
        // Verifica la regla de negocio que dice que no puede haber dos perosnaes con el mismo nombre
        if (persistence.findByNombre(perosnaEntity.getNombre()) != null) {
            throw new BusinessLogicException("Ya existe una Persona con el nombre \"" + perosnaEntity.getNombre() + "\"");
        }
        // Invoca la persistencia para crear la perosna
        persistence.create(perosnaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de creación de la perosna");
        return perosnaEntity;
    }
}

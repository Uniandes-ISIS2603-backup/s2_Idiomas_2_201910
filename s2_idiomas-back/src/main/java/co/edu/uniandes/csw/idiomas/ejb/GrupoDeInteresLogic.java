/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.ejb;

import co.edu.uniandes.csw.idiomas.entities.GrupoDeInteresEntity;
import co.edu.uniandes.csw.idiomas.entities.PersonaEntity;
import co.edu.uniandes.csw.idiomas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.idiomas.persistence.GrupoDeInteresPersistence;
import co.edu.uniandes.csw.idiomas.persistence.PersonaPersistence;
import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author estudiante
 */
@Stateless
public class GrupoDeInteresLogic {
    private static final Logger LOGGER = Logger.getLogger(GrupoDeInteresLogic.class.getName());

    @Inject
    private GrupoDeInteresPersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.
        /**
     * Crea una persona en la persistencia.
     *
     * @param grupoDeInteresEntity La entidad que representa la persona a
     * persistir.
     * @return La entiddad de la persona luego de persistirla.
     * @throws BusinessLogicException Si la persona a persistir ya existe.
     */
    public GrupoDeInteresEntity createGrupoDeInteres(GrupoDeInteresEntity grupoEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de creación de la grupo");
        // Verifica la regla de negocio que dice que no puede haber dos personas con el mismo nombre
        if (persistence.find(grupoEntity.getId()) != null) {
            throw new BusinessLogicException("Ya existe una Grupo con el nombre \"" + grupoEntity.getId() + "\"");
        }
        // Invoca la persistencia para crear la persona
        persistence.create(grupoEntity);
        LOGGER.log(Level.INFO, "Termina proceso de creación de la grupo");
        return grupoEntity;
    }
    /**
     *
     * Obtener todas las personas existentes en la base de datos.
     *
     * @return una lista de personas.
     */
    public List<GrupoDeInteresEntity> getGrupoDeInteres() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todas las personas");
        // Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
        List<GrupoDeInteresEntity> grupo = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todas las personas");
        return grupo;
    }

    /**
     *
     * Obtener una persona por medio de su id.
     *
     * @param grupoId: id de la persona para ser buscada.
     * @return la grupo solicitada por medio de su id.
     */
    public GrupoDeInteresEntity getGrupoDeInteres(Long grupoId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la persona con id = {0}", grupoId);
        // Note que, por medio de la inyección de dependencias se llama al método "find(id)" que se encuentra en la persistencia.
        GrupoDeInteresEntity grupoEntity = persistence.find(grupoId);
        if (grupoEntity == null) {
            LOGGER.log(Level.SEVERE, "La grupo con el id = {0} no existe", grupoId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar la grupo con id = {0}", grupoId);
        return grupoEntity;
    }

    /**
     *
     * Actualizar una persona.
     *
     * @param grupoId: id de la persona para buscarla en la base de
     * datos.
     * @param grupoEntity: persona con los cambios para ser actualizada,
     * por ejemplo el nombre.
     * @return la persona con los cambios actualizados en la base de datos.
     */
    public GrupoDeInteresEntity updateGrupoDeInteres(Long grupoId, GrupoDeInteresEntity grupoEntity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar la persona con id = {0}", grupoEntity);
        // Note que, por medio de la inyección de dependencias se llama al método "update(entity)" que se encuentra en la persistencia.
        GrupoDeInteresEntity newEntity = persistence.update(grupoEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar la grupoDeInteres con id = {0}", grupoEntity.getId());
        return newEntity;
    }

    /**
     * Borrar un persona
     *
     * @param grupoId: id de la persona a borrar
     * @throws BusinessLogicException Si la persona a eliminar tiene libros.
     */
    public void deleteGrupoDeInteres(Long grupoId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar la grupoDeInteres con id = {0}", grupoId);        
        persistence.delete(grupoId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar la grupoDeInteres con id = {0}", grupoId);
    }
}

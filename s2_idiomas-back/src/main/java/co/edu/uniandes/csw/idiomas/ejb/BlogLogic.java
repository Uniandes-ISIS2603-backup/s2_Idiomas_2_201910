/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.ejb;

import co.edu.uniandes.csw.idiomas.entities.BlogEntity;
import co.edu.uniandes.csw.idiomas.entities.BlogEntity;
import co.edu.uniandes.csw.idiomas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.idiomas.persistence.BlogPersistence;
import co.edu.uniandes.csw.idiomas.persistence.BlogPersistence;
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
public class BlogLogic {
        private static final Logger LOGGER = Logger.getLogger(BlogLogic.class.getName());

    @Inject
    private BlogPersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.
        /**
     * Crea una persona en la persistencia.
     *
     * @param BlogEntity La entidad que representa la persona a
     * persistir.
     * @return La entiddad de la persona luego de persistirla.
     * @throws BusinessLogicException Si la persona a persistir ya existe.
     */
    public BlogEntity createBlog(BlogEntity blogEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de creación de la grupo");
        // Verifica la regla de negocio que dice que no puede haber dos personas con el mismo nombre
        if (persistence.find(blogEntity.getId()) != null) {
            throw new BusinessLogicException("Ya existe una Grupo con el nombre \"" + blogEntity.getId() + "\"");
        }
        // Invoca la persistencia para crear la persona
        persistence.create(blogEntity);
        LOGGER.log(Level.INFO, "Termina proceso de creación de la grupo");
        return blogEntity;
    }
    /**
     *
     * Obtener todas las personas existentes en la base de datos.
     *
     * @return una lista de personas.
     */
    public List<BlogEntity> getBlog() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todas las personas");
        // Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
        List<BlogEntity> blog = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todas las personas");
        return blog;
    }

    /**
     *
     * Obtener una persona por medio de su id.
     *
     * @param grupoId: id de la persona para ser buscada.
     * @return la grupo solicitada por medio de su id.
     */
    public BlogEntity getBlog(Long blogId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la persona con id = {0}", blogId);
        // Note que, por medio de la inyección de dependencias se llama al método "find(id)" que se encuentra en la persistencia.
        BlogEntity blogEntity = persistence.find(blogId);
        if (blogEntity == null) {
            LOGGER.log(Level.SEVERE, "La grupo con el id = {0} no existe", blogId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar la grupo con id = {0}", blogId);
        return blogEntity;
    }

    /**
     *
     * Actualizar una persona.
     *
     * @param blogId: id de la persona para buscarla en la base de
     * datos.
     * @param blogEntity: persona con los cambios para ser actualizada,
     * por ejemplo el nombre.
     * @return la persona con los cambios actualizados en la base de datos.
     */
    public BlogEntity updateBlog(Long grupoId, BlogEntity blogEntity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar la persona con id = {0}", blogEntity);
        // Note que, por medio de la inyección de dependencias se llama al método "update(entity)" que se encuentra en la persistencia.
        BlogEntity newEntity = persistence.update(blogEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar la Blog con id = {0}", blogEntity.getId());
        return newEntity;
    }

    /**
     * Borrar un persona
     *
     * @param grupoId: id de la persona a borrar
     * @throws BusinessLogicException Si la persona a eliminar tiene libros.
     */
    public void deleteBlog(Long blogId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar la Blog con id = {0}", blogId);        
        persistence.delete(blogId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar la Blog con id = {0}", blogId);
    }
}

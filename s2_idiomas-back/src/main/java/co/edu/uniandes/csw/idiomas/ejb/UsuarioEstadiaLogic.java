/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.ejb;

import co.edu.uniandes.csw.idiomas.entities.ActividadEntity;
import co.edu.uniandes.csw.idiomas.entities.UsuarioEntity;
import co.edu.uniandes.csw.idiomas.entities.EstadiaEntity;
import co.edu.uniandes.csw.idiomas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.idiomas.persistence.UsuarioPersistence;
import co.edu.uniandes.csw.idiomas.persistence.EstadiaPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;

/**
 * Clase que implementa la conexion con la persistencia para la relación entre
 * la entidad de Usuario y Estadia.
 * @author g.cubillosb
 */
public class UsuarioEstadiaLogic {
    
private static final Logger LOGGER = Logger.getLogger(UsuarioEstadiaLogic.class.getName());

    @Inject
    private EstadiaPersistence estadiaUsuarioPersistence;

    @Inject
    private UsuarioPersistence usuarioPersistence;

    /**
     * Asocia un Estadia existente a un Usuario
     *
     * @param usuariosId Identificador de la instancia de Usuario
     * @param estadiasId Identificador de la instancia de Estadia
     * @return Instancia de EstadiaEntity que fue asociada a Usuario
     */
    public EstadiaEntity addEstadia(Long usuariosId, Long estadiasId) {
        LOGGER.log(Level.INFO, "Inicia proceso de asociarle un estadia a la usuario con id = {0}", usuariosId);
        UsuarioEntity usuarioEntity = usuarioPersistence.find(usuariosId);
        EstadiaEntity estadiaUsuarioEntity = estadiaUsuarioPersistence.find(estadiasId);
        estadiaUsuarioEntity.getAsistentes().add(usuarioEntity);
        LOGGER.log(Level.INFO, "Termina proceso de asociarle un libro al usuario con id = {0}", usuariosId);
        return estadiaUsuarioPersistence.find(estadiasId);
    }

    /**
     * Obtiene una colección de instancias de EstadiaEntity asociadas a una
     * instancia de Usuario
     *
     * @param usuariosId Identificador de la instancia de Usuario
     * @return Colección de instancias de EstadiaEntity asociadas a la instancia de
     * Usuario
     */
    public List<EstadiaEntity> getEstadias(Long usuariosId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los estadias del usuario con id = {0}", usuariosId);
        return usuarioPersistence.find(usuariosId).getEstadias();
    }

    /**
     * Obtiene una instancia de EstadiaEntity asociada a una instancia de Usuario
     *
     * @param usuariosId Identificador de la instancia de Usuario
     * @param estadiasId Identificador de la instancia de Estadia
     * @return La entidadd de Libro del usuario
     * @throws BusinessLogicException Si el libro no está asociado al usuario
     */
    public EstadiaEntity getEstadia(Long usuariosId, Long estadiasId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el libro con id = {0} del usuario con id = " + usuariosId, estadiasId);
        List<EstadiaEntity> estadias = usuarioPersistence.find(usuariosId).getEstadias();
        EstadiaEntity estadiaUsuarioEntity = estadiaUsuarioPersistence.find(estadiasId);
        int index = estadias.indexOf(estadiaUsuarioEntity);
        LOGGER.log(Level.INFO, "Termina proceso de consultar el libro con id = {0} del usuario con id = " + usuariosId, estadiasId);
        if (index >= 0) {
            return estadias.get(index);
        }
        throw new BusinessLogicException("El libro no está asociado al usuario");
    }

    /**
     * Remplaza las instancias de Estadia asociadas a una instancia de Usuario
     *
     * @param usuarioId Identificador de la instancia de Usuario
     * @param estadias Colección de instancias de EstadiaEntity a asociar a instancia
     * de Usuario
     * @return Nueva colección de EstadiaEntity asociada a la instancia de Usuario
     */
    public List<EstadiaEntity> replaceEstadias(Long usuarioId, List<EstadiaEntity> estadias) {
        LOGGER.log(Level.INFO, "Inicia proceso de reemplazar los estadias asocidos al usuario con id = {0}", usuarioId);
        UsuarioEntity usuarioEntity = usuarioPersistence.find(usuarioId);
        List<EstadiaEntity> estadiaUsuarioList = estadiaUsuarioPersistence.findAll();
        for (EstadiaEntity estadiaUsuario : estadiaUsuarioList) {
            if (estadias.contains(estadiaUsuario)) {
                if (!estadiaUsuario.getAsistentes().contains(usuarioEntity)) {
                    estadiaUsuario.getAsistentes().add(usuarioEntity);
                }
            } else {
                estadiaUsuario.getAsistentes().remove(usuarioEntity);
            }
        }
        usuarioEntity.setEstadias(estadias);
        LOGGER.log(Level.INFO, "Termina proceso de reemplazar los estadias asocidos al usuario con id = {0}", usuarioId);
        return usuarioEntity.getEstadias();
    }

    /**
     * Desasocia un Estadia existente de un Usuario existente
     *
     * @param usuariosId Identificador de la instancia de Usuario
     * @param estadiasId Identificador de la instancia de Estadia
     */
    public void removeEstadia(Long usuariosId, Long estadiasId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar un libro del usuario con id = {0}", usuariosId);
        UsuarioEntity usuarioEntity = usuarioPersistence.find(usuariosId);
        EstadiaEntity estadiaUsuarioEntity = estadiaUsuarioPersistence.find(estadiasId);
        estadiaUsuarioEntity.getAsistentes().remove(usuarioEntity);
        LOGGER.log(Level.INFO, "Termina proceso de borrar un libro del usuario con id = {0}", usuariosId);
    }
}

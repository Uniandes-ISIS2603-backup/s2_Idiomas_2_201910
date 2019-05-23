/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.ejb;

import co.edu.uniandes.csw.idiomas.entities.CalificacionEntity;
import co.edu.uniandes.csw.idiomas.entities.GrupoDeInteresEntity;
import co.edu.uniandes.csw.idiomas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.idiomas.persistence.CalificacionPersistence;
import co.edu.uniandes.csw.idiomas.persistence.GrupoDeInteresPersistence;
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
public class GrupoDeInteresCalificacionLogic {
    
        private static final Logger LOGGER = Logger.getLogger(CalificacionGrupoLogic.class.getName());
    
    @Inject
    private CalificacionPersistence calificacionPersistence;
    
    @Inject
    private GrupoDeInteresPersistence grupoDeInteresPersistence;
    
    /**
     * Remplazar books de una editorial
     *
     * @param calificaciones Lista de libros que serán los de la editorial.
     * @param grupoId El id de la editorial que se quiere actualizar.
     * @return La lista de libros actualizada.
     */
    public List<CalificacionEntity> replaceCalificacion(Long grupoId, List<CalificacionEntity> calificaciones) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar la editorial con id = {0}", grupoId);
       GrupoDeInteresEntity grupoEntity = grupoDeInteresPersistence.find(grupoId);
        List<CalificacionEntity> calificacionList = calificacionPersistence.findAll();
        for (CalificacionEntity calificacion : calificacionList) {
            if (calificaciones.contains(calificacion)) {
                calificacion.setGrupo(grupoEntity);
            } else if (calificacion.getGrupo() != null && calificacion.getGrupo().equals(grupoEntity)) {
                calificacion.setGrupo(null);
            }
        }
        LOGGER.log(Level.INFO, "Termina proceso de actualizar la editorial con id = {0}", grupoId);
        return calificaciones;
    }
        /**
     * Retorna un book asociado a una editorial
     *
     * @param grupoId El id de la editorial a buscar.
     * @param booksId El id del libro a buscar
     * @return El libro encontrado dentro de la editorial.
     * @throws BusinessLogicException Si el libro no se encuentra en la
     * editorial
     */
    public CalificacionEntity getCalificacion(Long grupoId, Long calificacionesId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la calificacion con id = {0} de la editorial con id = " + grupoId, calificacionesId);
        List<CalificacionEntity> calificaciones = grupoDeInteresPersistence.find(grupoId).getCalificaciones();
        CalificacionEntity calificacionEntity = calificacionPersistence.find(calificacionesId);
        int index = calificaciones.indexOf(calificacionEntity);
        LOGGER.log(Level.INFO, "Termina proceso de consultar el libro con id = {0} de la editorial con id = " + grupoId, calificacionesId);
        if (index >= 0) {
            return calificaciones.get(index);
        }
        throw new BusinessLogicException("La calificacion no está asociada a el Grupo");
    }
       /**
     * Agregar un book a la editorial
     *
     * @param booksId El id libro a guardar
     * @param editorialsId El id de la editorial en la cual se va a guardar el
     * libro.
     * @return El libro creado.
     */
    public CalificacionEntity addCalificacion(Long calificacionesId, Long grupoId) {
        LOGGER.log(Level.INFO, "Inicia proceso de agregarle una calificacion al grupo con id = {0}", grupoId);
        GrupoDeInteresEntity grupoEntity = grupoDeInteresPersistence.find(grupoId);
        CalificacionEntity calificacionEntity = calificacionPersistence.find(grupoId);
        calificacionEntity.setGrupo(grupoEntity);
        LOGGER.log(Level.INFO, "Termina proceso de agregarle una calificacion al grupo con id = {0}", grupoId);
        return calificacionEntity;
    }
    
}

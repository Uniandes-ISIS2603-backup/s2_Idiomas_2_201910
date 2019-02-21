/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.ejb;

import co.edu.uniandes.csw.idiomas.entities.ComentarioCalificacionEntity;
import co.edu.uniandes.csw.idiomas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.idiomas.persistence.ComentarioCalificacionPersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author se.gamboa
 */
@Stateless
public class ComentarioCalificacionLogic {
    
    @Inject
    private ComentarioCalificacionPersistence persistence;
    
    /**
     * Creac un comentario de tipo Calificacion
     * @param entidad
     * @return entidad creada.
     * @throws BusinessLogicException 
     */
    public ComentarioCalificacionEntity createCalifComment(ComentarioCalificacionEntity entidad)throws BusinessLogicException
    {
        if(entidad.getTitulo() == null)
        {
            throw new BusinessLogicException("El titulo del comentario no puede ser null");
        }
        if(entidad.getTitulo().length()== 0)
        {
            throw new BusinessLogicException("El titulo debe contener al menos un caracter");
        }
        entidad = persistence.create(entidad);
        return entidad;
    }
}

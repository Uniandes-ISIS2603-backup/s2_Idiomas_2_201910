/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.ejb;

import co.edu.uniandes.csw.idiomas.entities.ComentarioActividadEntity;
import co.edu.uniandes.csw.idiomas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.idiomas.persistence.ComentarioActividadPersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author se.gamboa
 */
@Stateless
public class ComentarioActividadLogic {
    @Inject
    private ComentarioActividadPersistence persistence;
    /**
     * Creac un comentario de tipo blog
     * @param entidad
     * @return
     * @throws BusinessLogicException 
     */
    public ComentarioActividadEntity createActivityComment(ComentarioActividadEntity entidad)throws BusinessLogicException
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

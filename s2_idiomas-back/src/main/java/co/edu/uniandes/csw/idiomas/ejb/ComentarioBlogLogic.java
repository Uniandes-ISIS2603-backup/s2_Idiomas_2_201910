/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.ejb;

import co.edu.uniandes.csw.idiomas.entities.ComentarioBlogEntity;
import co.edu.uniandes.csw.idiomas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.idiomas.persistence.ComentarioBlogPersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author se.gamboa
 */
@Stateless
public class ComentarioBlogLogic {
    @Inject
    private ComentarioBlogPersistence persistence;
    /**
     * Creac un comentario de tipo blog
     * @param entidad
     * @return
     * @throws BusinessLogicException 
     */
    public ComentarioBlogEntity createBlogComment(ComentarioBlogEntity entidad)throws BusinessLogicException
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

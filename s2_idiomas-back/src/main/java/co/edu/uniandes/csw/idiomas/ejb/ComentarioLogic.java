/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.ejb;

import co.edu.uniandes.csw.idiomas.entities.ComentarioEntity;
import co.edu.uniandes.csw.idiomas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.idiomas.persistence.ComentarioPersistence;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author se.gamboa
 */
@Stateless
public class ComentarioLogic {
   @Inject
    private ComentarioPersistence persistence;
   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * Creac un comentario de tipo Calificacion
     *
     * @param entidad
     * @return entidad creada.
     * @throws BusinessLogicException
     */
    public ComentarioEntity createComment(ComentarioEntity entidad) throws BusinessLogicException, ParseException {
        Date date1 = sdf.parse("2018-12-31");
        if (entidad.getTexto() == null) {
            throw new BusinessLogicException("El texto del comentario no puede ser null");
        }
        if (entidad.getTexto().length() == 0) {
            throw new BusinessLogicException("El texto debe contener al menos un caracter");
        }
        if (entidad.getFecha().before(date1)) {
            throw new BusinessLogicException("la fecha no es aceptada");
        }
        entidad = persistence.create(entidad);
        return entidad;
    } 
    
    public void deleteComment(Long commentId) throws BusinessLogicException {
        persistence.delete(commentId);
    }
    
    public ComentarioEntity getComment(Long comentarioId){
        ComentarioEntity comentario = persistence.find(comentarioId);
        return comentario;
    }
}

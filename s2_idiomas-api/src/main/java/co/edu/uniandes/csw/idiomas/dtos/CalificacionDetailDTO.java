/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.dtos;

import co.edu.uniandes.csw.idiomas.entities.CalificacionEntity;
import co.edu.uniandes.csw.idiomas.entities.ComentarioEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jdruedaa
 */
public class CalificacionDetailDTO extends CalificacionDTO implements Serializable {
    private List<ComentarioDTO> comentarios;
    
    public CalificacionDetailDTO()
    {
        
    }
    
    public CalificacionDetailDTO(CalificacionEntity cal)
    {
        super(cal);
        if(cal!=null)
        {
            comentarios = new ArrayList<>();
            for(ComentarioEntity entityComentario : cal.getComentarios())
            {
                comentarios.add(entityComentario.toDTO());
            }
        }
    }

    /**
     * @return the comentarios
     */
    public List<ComentarioDTO> getComentarios() {
        return comentarios;
    }

    /**
     * @param comentarios the comentarios to set
     */
    public void setComentarios(List<ComentarioDTO> comentarios) {
        this.comentarios = comentarios;
    }
    
    public CalificacionEntity toEntity()
    {
        CalificacionEntity cal = super.toEntity();
        if(comentarios != null)
        {
            List<ComentarioEntity> comentariosEntity = new ArrayList<>();
            for(ComentarioDTO dtoComentario : comentarios)
            {
                comentariosEntity.add(dtoComentario.toEntity());
            }
        }
        cal.setComentarios(comentariosEntity);
        return cal;
    }
}

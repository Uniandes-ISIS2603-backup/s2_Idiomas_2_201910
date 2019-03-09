/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.dtos;

import co.edu.uniandes.csw.idiomas.entities.CalificacionEntity;
import co.edu.uniandes.csw.idiomas.entities.ComentarioCalificacionEntity;
import co.edu.uniandes.csw.idiomas.entities.ComentarioEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jdruedaa
 */
public class CalificacionDetailDTO extends CalificacionDTO implements Serializable {
    private List<ComentarioCalificacionDTO> comentarios;
    
    public CalificacionDetailDTO()
    {
        
    }
    
    public CalificacionDetailDTO(CalificacionEntity cal)
    {
        super(cal);
        if(cal!=null)
        {
            comentarios = new ArrayList<>();
            
        }
    }

    /**
     * @return the comentarios
     */
    public List<ComentarioCalificacionDTO> getComentarios() {
        return comentarios;
    }

    /**
     * @param comentarios the comentarios to set
     */
    public void setComentarios(List<ComentarioCalificacionDTO> comentarios) {
        this.comentarios = comentarios;
    }
    
    @Override
    public CalificacionEntity toEntity()
    {
        CalificacionEntity cal = super.toEntity();
        if(comentarios != null)
        {
            List<ComentarioCalificacionEntity> comentariosEntity = new ArrayList<>();
            for(ComentarioCalificacionDTO dtoComentario : comentarios)
            {
                comentariosEntity.add(dtoComentario.toEntity());
            }
            cal.setComentarios(comentariosEntity);
        }
//        cal.setComentariosEntity(comentariosEntity);
        return cal;
    }
}

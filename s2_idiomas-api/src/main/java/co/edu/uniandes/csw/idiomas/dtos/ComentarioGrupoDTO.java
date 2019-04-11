/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.dtos;

import co.edu.uniandes.csw.idiomas.entities.ComentarioGrupoEntity;
import java.io.Serializable;

/**
 *
 * @author se.gamboa
 */
public class ComentarioGrupoDTO extends ComentarioDTO implements Serializable {

    /**
     * Constructor de ComentarioActividadDTO
     *
     * @param entity recibe la informacion del comentario.
     */
    public ComentarioGrupoDTO(ComentarioGrupoEntity entity) {
        super(entity);
        ///s
    }
    
    public ComentarioGrupoDTO(){
        super();
    }


    @Override
    public ComentarioGrupoEntity toEntity() {
        ComentarioGrupoEntity entity = new ComentarioGrupoEntity();
        entity.setTitulo(this.getTitulo());
        entity.setFecha(this.getFecha());
        entity.setTexto(this.getTexto());
        return entity;
    }
}

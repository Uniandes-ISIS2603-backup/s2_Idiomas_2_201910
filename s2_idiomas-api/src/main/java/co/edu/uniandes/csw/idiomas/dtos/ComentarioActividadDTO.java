/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.dtos;

import co.edu.uniandes.csw.idiomas.entities.ComentarioActividadEntity;
import java.io.Serializable;

/**
 *
 * @author se.gamboa
 * sada
 */
public class ComentarioActividadDTO extends ComentarioDTO implements Serializable{
    

    /**
     * Constructor de ComentarioActividadDTO
     *
     * @param entity recibe el titulo del comentario.
     */
    public ComentarioActividadDTO(ComentarioActividadEntity entity) {
        super(entity);
    }
    
    public ComentarioActividadDTO(){
        super();
    }

    /**
     * Convierte un objeto DTO a una Entidad.
     *
     * @return entidad convertida.
     */
    @Override
    public ComentarioActividadEntity toEntity() {
        ComentarioActividadEntity entity = new ComentarioActividadEntity();
        entity.setTitulo(this.getTitulo());
        entity.setFecha(this.getFecha());
        entity.setTexto(this.getTexto());
        return entity;
    }
}

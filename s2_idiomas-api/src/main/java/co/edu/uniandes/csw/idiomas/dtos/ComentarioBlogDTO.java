/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.dtos;

import co.edu.uniandes.csw.idiomas.entities.ComentarioBlogEntity;
import java.io.Serializable;

/**
 *
 * @author se.gamboa
 */
public class ComentarioBlogDTO extends ComentarioDTO implements Serializable {


    /**
     * Constructor de ComentarioBlogDTO
     *
     * @param entity recibe el titulo del comentario.
     */
    public ComentarioBlogDTO(ComentarioBlogEntity entity) {
        super(entity);
        //a
    }
    
    public ComentarioBlogDTO(){
        super();
    }


    /**
     * Convierte un objeto DTO a una Entidad.
     *
     * @return entidad convertida.
     */
    @Override
    public ComentarioBlogEntity toEntity() {
        ComentarioBlogEntity entity = new ComentarioBlogEntity();
        entity.setTitulo(this.getTitulo());
        entity.setFecha(this.getFecha());
        entity.setTexto(this.getTexto());
        return entity;
    }
}

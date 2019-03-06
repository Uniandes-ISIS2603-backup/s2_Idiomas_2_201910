/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.dtos;

import co.edu.uniandes.csw.idiomas.entities.ComentarioCalificacionEntity;
import co.edu.uniandes.csw.idiomas.entities.ComentarioEntity;
import java.io.Serializable;

/**
 *
 * @author se.gamboa
 */
public class ComentarioCalificacionDTO extends ComentarioDTO implements Serializable {

    private String titulo;

    /**
     * Constructor de ComentarioCalificacionDTO
     *
     * @param entity recibe el titulo del comentario.
     * @param entity2 entidad del comentario del que hereda.
     */
    public ComentarioCalificacionDTO(ComentarioCalificacionEntity entity, ComentarioEntity entity2) {
        super(entity2);
        if (entity != null) {
            this.titulo = entity.getTitulo();
        }
    }
    
    public ComentarioCalificacionDTO(){
        
    }

    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Convierte un objeto DTO a una Entidad.
     *
     * @return entidad convertida.
     */
    public ComentarioCalificacionEntity toEntityC() {
        ComentarioCalificacionEntity entity = new ComentarioCalificacionEntity();
        entity.setTitulo(this.titulo);
        return entity;
    }
}

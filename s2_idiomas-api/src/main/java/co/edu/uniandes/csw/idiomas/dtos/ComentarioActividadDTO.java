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
    
    private String titulo;

    /**
     * Constructor de ComentarioActividadDTO
     *
     * @param entity recibe el titulo del comentario.
     */
    public ComentarioActividadDTO(ComentarioActividadEntity entity) {
        super(entity);
        if (entity != null) {
            this.titulo = entity.getTitulo();
        }
    }
    
    public ComentarioActividadDTO(){
        super();
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
    @Override
    public ComentarioActividadEntity toEntity() {
        ComentarioActividadEntity entity = new ComentarioActividadEntity();
        entity.setTitulo(this.titulo);
        entity.setFecha(this.getFecha());
        entity.setTexto(this.getTexto());
        entity.setAutor(this.getAutor().toEntity());
        return entity;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.dtos;

import co.edu.uniandes.csw.idiomas.entities.ComentarioCalificacionEntity;
import java.io.Serializable;

/**
 *
 * @author se.gamboa
 */
public class ComentarioCalificacionDTO extends ComentarioDTO implements Serializable {

    private String titulo;
    private CalificacionDTO calificacion;

    /**
     * Constructor de ComentarioCalificacionDTO
     *
     * @param entity recibe el titulo del comentario.
     */
    public ComentarioCalificacionDTO(ComentarioCalificacionEntity entity) {
        super(entity);
        if (entity != null) {
            this.titulo = entity.getTitulo();
            this.calificacion = new CalificacionDTO(entity.getCalificaciones());
        }
    }
    
    public ComentarioCalificacionDTO(){
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
    public ComentarioCalificacionEntity toEntity() {
        ComentarioCalificacionEntity entity = new ComentarioCalificacionEntity();
        entity.setTitulo(this.getTitulo());
        entity.setFecha(this.getFecha());
        entity.setTexto(this.getTexto());
        entity.setAutor(this.getAutor().toEntity());
        entity.setCalificaciones(this.calificacion.toEntity());
        return entity;
    }

    /**
     * @return the calificacion
     */
    public CalificacionDTO getCalificacion() {
        return calificacion;
    }

    /**
     * @param calificacion the calificacion to set
     */
    public void setCalificacion(CalificacionDTO calificacion) {
        this.calificacion = calificacion;
    }
}

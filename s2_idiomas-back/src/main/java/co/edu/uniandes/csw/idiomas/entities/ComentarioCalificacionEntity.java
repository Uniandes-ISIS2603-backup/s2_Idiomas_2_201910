/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author se.gamboa
 */
@Entity
public class ComentarioCalificacionEntity extends ComentarioEntity implements Serializable{

    private String titulo;

    @PodamExclude
    @ManyToOne
    private CalificacionEntity calificaciones;
    /**
     * Constructor vacío de comentarioCalificacionEntity.
     */
    public ComentarioCalificacionEntity() {

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
     * @return the calificaciones
     */
    public CalificacionEntity getCalificaciones() {
        return calificaciones;
    }

    /**
     * @param calificaciones the calificaciones to set
     */
    public void setCalificaciones(CalificacionEntity calificaciones) {
        this.calificaciones = calificaciones;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author jd.ruedaa
 */
@Entity
public class CalificacionActividadEntity extends BaseEntity implements Serializable{
    @Id
    private Long id;
    private Integer calificacion;
    private String mensaje;
    @ManyToOne
    private ActividadEntity activ;
    @PodamExclude
    @OneToMany(mappedBy = "calificaciones")
    private List<ComentarioCalificacionEntity> comentarios = new ArrayList<>();
    
    @Override
    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }
    @Override
    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the calificacion
     */
    public Integer getCalificacion() {
        return calificacion;
    }

    /**
     * @param calificacion the calificacion to set
     */
    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }

    /**
     * @return the mensaje
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * @param mensaje the mensaje to set
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    /**
     * @return the activ
     */
    public ActividadEntity getActiv() {
        return activ;
    }

    /**
     * @param activ the activ to set
     */
    public void setActiv(ActividadEntity activ) {
        this.activ = activ;
    }

    /**
     * @return the comentarios
     */
    public List<ComentarioCalificacionEntity> getComentarios() {
        return comentarios;
    }

    /**
     * @param comentarios the comentarios to set
     */
    public void setComentarios(List<ComentarioCalificacionEntity> comentarios) {
        this.comentarios = comentarios;
    }
    
    public CalificacionActividadEntity()
    {
        // Constructor vacío
    }
    
}

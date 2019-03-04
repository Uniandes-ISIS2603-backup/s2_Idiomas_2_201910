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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 * Clase que representa una actividad en la persistencia y permite su
 * serialización.
 * @author g.cubillosb
 */
@Entity
public class ActividadEntity extends BaseEntity implements Serializable{
    
    // -------------------------------------------------------------------
    // Atributos
    // -------------------------------------------------------------------
    
    /**
     * Atributo que representa el nombre de la actividad.
     */
    private String nombre;
    
    /**
     * Atributo que representa la fecha de la actividad.
     */
    private String fecha;
    
    /**
     * Atributo que modela la descripción de la actividad.
     */
    private String descripcion;
    
    /**
     * Atributo que representa la motivación de la actividad.
     */
    private String motivacion;
    
    /**
     * Atributo que representa los comentarios de la actividad.
     */
    @PodamExclude
    @OneToMany(mappedBy = "actividad")
    private List<ComentarioActividadEntity> comentarios = new ArrayList<>();
    
    /**
     * Atributo que representa los asistentes de la actividad.
     */
    @PodamExclude
    @ManyToMany(mappedBy = "actividades")
    private List<UsuarioEntity> asistentes = new ArrayList<>();
    
    /**
     * Atributo que representa los coordinadores de una actividad.
     */
    @ManyToMany
    private List<CoordinadorEntity> coordinadores = new ArrayList<>();
    
//    /**
//     * Atributo que representa la calificacion de la actividad.
//     */
//    @PodamExclude
//    @OneToOne
//    private CalificacionEntity calificacion;
    
    // ------------------------------------------------------------------
    // Constructor
    // ------------------------------------------------------------------
    
    /**
     * Constructor vacío.
     */
    public ActividadEntity ()
    {
        
    }
    
    // ------------------------------------------------------------------
    // Métodos
    // ------------------------------------------------------------------

    /**
     * @return the fecha
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the motivacion
     */
    public String getMotivacion() {
        return motivacion;
    }

    /**
     * @param motivacion the motivacion to set
     */
    public void setMotivacion(String motivacion) {
        this.motivacion = motivacion;
    }

    /**
     * @return the comentarios
     */
    public List<ComentarioActividadEntity> getComentarios() {
        return comentarios;
    }

    /**
     * @param comentarios the comentarios to set
     */
    public void setComentarios(List<ComentarioActividadEntity> comentarios) {
        this.comentarios = comentarios;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the asistentes
     */
    public List<UsuarioEntity> getAsistentes() {
        return asistentes;
    }

    /**
     * @param asistentes the asistentes to set
     */
    public void setAsistentes(List<UsuarioEntity> asistentes) {
        this.asistentes = asistentes;
    }

//    /**
//     * @return the calificacion
//     */
//    public CalificacionEntity getCalificacion() {
//        return calificacion;
//    }
//
//    /**
//     * @param calificacion the calificacion to set
//     */
//    public void setCalificacion(CalificacionEntity calificacion) {
//        this.calificacion = calificacion;
//    }
    
}

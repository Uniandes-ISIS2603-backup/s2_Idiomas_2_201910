/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.entities;

import co.edu.uniandes.csw.idiomas.podam.DateStrategy;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamExclude;
import uk.co.jemos.podam.common.PodamStrategyValue;

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
    @Temporal(TemporalType.DATE)
    @PodamStrategyValue(DateStrategy.class)
    private Date fecha;
    
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
    @OneToMany(mappedBy = "actividad", cascade = CascadeType.ALL)
    private List<ComentarioActividadEntity> comentarios = new ArrayList<>();
    
    /**
     * Atributo que representa los asistentes de la actividad.
     */
    @PodamExclude
    @ManyToMany(mappedBy = "actividades", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<UsuarioEntity> asistentes = new ArrayList<>();
    
    /**
     * Atributo que representa los coordinadores de una actividad.
     */
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
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
    
    // ------------------------------------------------------------------
    // Métodos
    // ------------------------------------------------------------------

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

    /**
     * @return the coordinadores
     */
    public List<CoordinadorEntity> getCoordinadores() {
        return coordinadores;
    }

    /**
     * @param coordinadores the coordinadores to set
     */
    public void setCoordinadores(List<CoordinadorEntity> coordinadores) {
        this.coordinadores = coordinadores;
    }

    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
}

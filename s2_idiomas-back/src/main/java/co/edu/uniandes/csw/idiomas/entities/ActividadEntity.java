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
import javax.persistence.OneToMany;
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
    private String name;
    
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
    private List<ComentarioActividadEntity> comentarios = new ArrayList<ComentarioActividadEntity>();
    
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
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
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
    
}

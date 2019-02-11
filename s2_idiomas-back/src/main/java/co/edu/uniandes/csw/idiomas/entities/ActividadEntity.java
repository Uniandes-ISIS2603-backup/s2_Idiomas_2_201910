/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.entities;

import java.io.Serializable;

/**
 *
 * @author g.cubillosb
 */
public class ActividadEntity extends BaseEntity implements Serializable{
    
    
    
    // -------------------------------------------------------------------
    // Atributos
    // -------------------------------------------------------------------
    
    /**
     * Atributo que representa la fecha de la actividad
     */
    private String fecha;
    
    /**
     * Atributo que modela la descripción de la actividad
     */
    private String descripcion;
    
    /**
     * Atributo que representa la motivación de la actividad
     */
    private String motivacion;
    
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
    
}

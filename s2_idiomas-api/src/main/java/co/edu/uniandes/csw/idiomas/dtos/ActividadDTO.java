/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.dtos;

import co.edu.uniandes.csw.idiomas.entities.ActividadEntity;
import java.io.Serializable;

/**
 * ActividadDTO Objeto de transferencia de datos de la actividad. Los DTO contienen
 * las representaciones de los JSON que se transfieren entre el cliente y el
 * servidor.
 * @author g.cubillosb
 */
public class ActividadDTO implements Serializable{
    
    // -----------------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------------
    
    /**
     * Atributo que representa el identificador
     */
    private Long id;
    /**
     * Atributo que representa el nombre
     */
    private String nombre;
    
    /**
     * Atributo que contiene la fecha
     */
    private String fecha;
    
    /**
     * Atributo que contiene la descripcion de la actividad
     */
    private String descripcion;
    
    /**
     * Atributo que contiene la motivacion de la actvidad
     */
    private String motivacion;
    
    // -----------------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------------
    
    /**
     * Constructor vacio de una actividad
     */
    public ActividadDTO ()
    {
        
    }
    
    /**
     * Crea un objeto ActividadDTO a partir de un objeto ActividadEntity.
     *
     * @param pActividadEntity Entidad ActividadEntity desde la cual se va a crear el
     * nuevo objeto.
     *
     */
    public ActividadDTO(ActividadEntity pActividadEntity) {
        if (pActividadEntity != null) {
            this.descripcion = pActividadEntity.getDescripcion();
            this.fecha = pActividadEntity.getFecha();
            this.motivacion = pActividadEntity.getMotivacion();
            this.nombre = pActividadEntity.getNombre();
             
        }
    }
            
    
    // ----------------------------------------------------------------------
    // Metodos
    // ----------------------------------------------------------------------

    /**
     * Convierte un objeto ActividadDTO a ActividadEntity.
     *
     * @return Nueva objeto ActividadEntity.
     *
     */
    public ActividadEntity toEntity() {
        ActividadEntity actividadEntity = new ActividadEntity();
        actividadEntity.setDescripcion(this.getDescripcion());
        actividadEntity.setFecha(this.getFecha());
        actividadEntity.setMotivacion(this.getMotivacion());
        actividadEntity.setNombre(this.getNombre());
        return actividadEntity;
    }
    
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
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }
    
    
}

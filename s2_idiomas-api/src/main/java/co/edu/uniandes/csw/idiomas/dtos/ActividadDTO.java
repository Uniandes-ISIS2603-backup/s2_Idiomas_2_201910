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
     * Atributo que representa el Id de la actividad
     */
    private Long id;
    
    /**
     * Atributo que representa el nombre de la actividad.
     */
    private String nombre;
    
    /**
     * Atributo que contiene la fecha.
     */
    private String fecha;
    
    /**
     * Atributo que contiene la descripcion de la actividad.
     */
    private String descripcion;
    
    /**
     * Atributo que contiene la motivacion de la actvidad
     */
    private String motivacion;
    
    /**
     * Atributo que contiene la calificación de la actividad
     */
    private CalificacionDTO calificacion;
    
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
     * Convertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param pActividadEntity: Es la entidad que se va a convertir a DTO
     */
    public ActividadDTO(ActividadEntity pActividadEntity) 
    {
        if (pActividadEntity != null) 
        {
            this.descripcion = pActividadEntity.getDescripcion();
            this.fecha = pActividadEntity.getFecha();
            this.motivacion = pActividadEntity.getMotivacion();
            this.nombre = pActividadEntity.getNombre();
            if (pActividadEntity.getCalificacion() != null)
            {
                this.calificacion = new CalificacionDTO(pActividadEntity.getCalificacion());
            }
            else
            {
                this.calificacion = null;
            }
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
    
    /**
     * Convertir DTO a Entity
     *
     * @return Un Entity con los valores del DTO
     */
    public ActividadEntity toEntity() 
    {
        ActividadEntity actividadEntity = new ActividadEntity();
        actividadEntity.setNombre(this.getNombre());
        actividadEntity.setDescripcion(this.getDescripcion());
        actividadEntity.setMotivacion(this.getMotivacion());
        actividadEntity.setFecha(this.getFecha());
        
        return actividadEntity;
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

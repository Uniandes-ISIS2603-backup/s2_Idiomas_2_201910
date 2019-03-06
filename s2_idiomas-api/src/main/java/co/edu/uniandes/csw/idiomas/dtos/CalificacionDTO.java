/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.dtos;

import co.edu.uniandes.csw.idiomas.entities.CalificacionEntity;
import java.io.Serializable;
import javax.inject.Inject;

/**
 *
 * @author jdruedaa
 */
public class CalificacionDTO implements Serializable {

    

    private Integer calificacion;
    private String mensaje;
    
    public CalificacionDTO()
    {
        
    }
    
    public CalificacionDTO(CalificacionEntity cal)
    {
        if(cal != null)
        {
            this.calificacion = cal.getCalificacion();
            this.mensaje = cal.getMensaje();
        }
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
    
    @Inject
    public CalificacionEntity toEntity()
    {
        CalificacionEntity cal = new CalificacionEntity();
        cal.setCalificacion(calificacion);
        cal.setMensaje(mensaje);
        return cal;
    }
    
}

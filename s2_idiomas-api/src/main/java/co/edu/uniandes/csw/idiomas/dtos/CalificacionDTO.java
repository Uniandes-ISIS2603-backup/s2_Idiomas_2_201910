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
<<<<<<< Updated upstream

    

    private Integer calificacion;
    private String mensaje;
=======
    private Integer cantidadParaActualizar;
    private Double calificacionAcumulado;
    private Integer calificacion;
    private Integer numeroDeCalificaciones;
>>>>>>> Stashed changes
    
    public CalificacionDTO()
    {
        
    }
    
    public CalificacionDTO(CalificacionEntity cal)
    {
        if(cal != null)
        {
<<<<<<< Updated upstream
            this.calificacion = cal.getCalificacion();
            this.mensaje = cal.getMensaje();
=======
//            this.calificacion = cal.getCalificacion();
>>>>>>> Stashed changes
        }
    }

    /**
<<<<<<< Updated upstream
=======
     * @return the cantidadParaActualizar
     */
    public Integer getCantidadParaActualizar() {
        return cantidadParaActualizar;
    }

    /**
     * @param cantidadParaActualizar the cantidadParaActualizar to set
     */
    public void setCantidadParaActualizar(Integer cantidadParaActualizar) {
        this.cantidadParaActualizar = cantidadParaActualizar;
    }

    /**
     * @return the calificacionAcumulado
     */
    public Double getCalificacionAcumulado() {
        return calificacionAcumulado;
    }

    /**
     * @param calificacionAcumulado the calificacionAcumulado to set
     */
    public void setCalificacionAcumulado(Double calificacionAcumulado) {
        this.calificacionAcumulado = calificacionAcumulado;
    }

    /**
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
    
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
=======

    /**
     * @return the numeroDeCalificaciones
     */
    public Integer getNumeroDeCalificaciones() {
        return numeroDeCalificaciones;
    }

    /**
     * @param numeroDeCalificaciones the numeroDeCalificaciones to set
     */
    public void setNumeroDeCalificaciones(Integer numeroDeCalificaciones) {
        this.numeroDeCalificaciones = numeroDeCalificaciones;
    }    
>>>>>>> Stashed changes
    
    @Inject
    public CalificacionEntity toEntity()
    {
        CalificacionEntity cal = new CalificacionEntity();
<<<<<<< Updated upstream
        cal.setCalificacion(calificacion);
        cal.setMensaje(mensaje);
=======
//        cal.setCalificacion(calificacion);
>>>>>>> Stashed changes
        return cal;
    }
    
}

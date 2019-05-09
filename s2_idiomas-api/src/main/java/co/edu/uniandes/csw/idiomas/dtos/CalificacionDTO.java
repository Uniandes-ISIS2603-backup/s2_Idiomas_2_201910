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

    Long id;
    Integer calificacion;
    String mensaje;
    
    private ActividadDTO actividad;
    private AdministradorDTO administrador;
    private CoordinadorDTO coordinador;
    private GrupoDeInteresDTO grupoDeInteres;
    
    public CalificacionDTO()
    {
        
    }
    
    public CalificacionDTO(CalificacionEntity cal)
    {
        if(cal != null)
        {
            this.id = cal.getId();
            this.calificacion = cal.getCalificacion();
            this.mensaje = cal.getMensaje();
            if (cal.getActividad() != null) {
                this.actividad = new ActividadDTO(cal.getActividad());
            } else {
                this.actividad = null;
            }
            if (cal.getAdministrador() != null) {
                this.administrador = new AdministradorDTO(cal.getAdministrador());
            } else {
                this.administrador = null;
            }
            if (cal.getCoordinador() != null) {
                this.coordinador = new CoordinadorDTO(cal.getCoordinador());
            } else {
                this.coordinador = null;
            }
            if (cal.getGrupo() != null) {
                this.grupoDeInteres = new GrupoDeInteresDTO(cal.getGrupo());
            } else {
                this.grupoDeInteres = null;
            }
        }
    }
    
     @Inject
    public CalificacionEntity toEntity()
    {
        CalificacionEntity cal = new CalificacionEntity();
        cal.setId(id);
        cal.setCalificacion(calificacion);
        cal.setMensaje(mensaje);
        if (getActividad() != null)
        {
            cal.setActividad(getActividad().toEntity());
        }
        else if (getAdministrador() != null)
        {
            cal.setAdministrador(getAdministrador().toEntity());
        }
        else if (getCoordinador() != null)
        {
            cal.setCoordinador(getCoordinador().toEntity());
        }
        else if (getGrupoDeInteres() != null)
        {
            cal.setGrupo(getGrupoDeInteres().toEntity());
        }
        return cal;
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
     * @return the actividad
     */
    public ActividadDTO getActividad() {
        return actividad;
    }

    /**
     * @param actividad the actividad to set
     */
    public void setActividad(ActividadDTO actividad) {
        this.actividad = actividad;
    }

    /**
     * @return the administrador
     */
    public AdministradorDTO getAdministrador() {
        return administrador;
    }

    /**
     * @param administrador the administrador to set
     */
    public void setAdministrador(AdministradorDTO administrador) {
        this.administrador = administrador;
    }

    /**
     * @return the coordinador
     */
    public CoordinadorDTO getCoordinador() {
        return coordinador;
    }

    /**
     * @param coordinador the coordinador to set
     */
    public void setCoordinador(CoordinadorDTO coordinador) {
        this.coordinador = coordinador;
    }

    /**
     * @return the grupoDeInteres
     */
    public GrupoDeInteresDTO getGrupoDeInteres() {
        return grupoDeInteres;
    }

    /**
     * @param grupoDeInteres the grupoDeInteres to set
     */
    public void setGrupoDeInteres(GrupoDeInteresDTO grupoDeInteres) {
        this.grupoDeInteres = grupoDeInteres;
    }
    
}

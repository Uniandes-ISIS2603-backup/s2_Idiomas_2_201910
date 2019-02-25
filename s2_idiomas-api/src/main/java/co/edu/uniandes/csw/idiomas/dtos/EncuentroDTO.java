/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.dtos;

import java.io.Serializable;

/**
 * EncuentroDTO Objeto de transferencia de datos del encuentro. Los DTO 
 * contienen las representaciones de los JSON que se transfieren entre el 
 * cliente y el servidor.
 * @author g.cubillosb
 */
public class EncuentroDTO extends ActividadDTO implements Serializable{
    
    // -------------------------------------------------------------------------
    // Atributos
    // -------------------------------------------------------------------------
    
    /**
     * Atributo que representa un lugar
     */
    private String lugar;
    
    /**
     * Atributo que representa el número máximo de asistentes
     */
    private Integer numeroMaxAsistentes;
    
    /**
     * Atributo que representa si el encuentro fue aprobado
     */
    private Boolean aprobado;
    
    // -------------------------------------------------------------------------
    // Constructor
    // -------------------------------------------------------------------------
    
    /**
     * Constructor vacío
     */
    public EncuentroDTO () 
    {
        super();
    }
    
    /**
     * Crea un objeto EncuentroDTO con la información pasada por parámetro.
     * 
     * @param pLugar El lugar en el que se va a realizar el encuentro.
     * @param pNumeroMaxAsistentes El máximo número de asistentes al encuentro.
     * @param pAprobado El estado de aprobación del encuentro.
     */
    public EncuentroDTO (String pLugar, Integer pNumeroMaxAsistentes, Boolean pAprobado)
    {
        lugar = pLugar;
        numeroMaxAsistentes = pNumeroMaxAsistentes;
        aprobado = pAprobado;
    }
    
    // -------------------------------------------------------------------------
    // Métodos
    // -------------------------------------------------------------------------

    /**
     * @return the lugar
     */
    public String getLugar() {
        return lugar;
    }

    /**
     * @param lugar the lugar to set
     */
    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    /**
     * @return the numeroMaxAsistentes
     */
    public Integer getNumeroMaxAsistentes() {
        return numeroMaxAsistentes;
    }

    /**
     * @param numeroMaxAsistentes the numeroMaxAsistentes to set
     */
    public void setNumeroMaxAsistentes(Integer numeroMaxAsistentes) {
        this.numeroMaxAsistentes = numeroMaxAsistentes;
    }

    /**
     * @return the aprobado
     */
    public Boolean getAprobado() {
        return aprobado;
    }

    /**
     * @param aprobado the aprobado to set
     */
    public void setAprobado(Boolean aprobado) {
        this.aprobado = aprobado;
    }
    
}

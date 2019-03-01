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
 * Clase que representa un encuentro en la persistencia y permite su
 * serialización.
 * @author g.cubillosb
 */
@Entity
public class EncuentroEntity extends ActividadEntity implements Serializable
{
    
    // -------------------------------------------------------------------
    // Atributos
    // -------------------------------------------------------------------
    
    /**
     * Atributo que representa el lugar del encuentro.
     */
    private String lugar;
    
    /**
     * Atributo que representa el número máximo de asistentes del encuentro.
     */
    private Integer numeroMaxAsistentes;
    
    /**
     * Atributo que modela el estado de aprobación del encuentro.
     */
    private Boolean aprobado;
        
    /**
     * Atributo que representa los comentarios de la actividad.
     */
    // TODO: GC Se define como actividad o como encuentro
    @PodamExclude
    @OneToMany(mappedBy = "actividad")
    private List<ComentarioActividadEntity> comentarios = new ArrayList<ComentarioActividadEntity>();
    
    // ------------------------------------------------------------------
    // Constructor
    // ------------------------------------------------------------------
    
    /**
     * Constructor vacío.
     */
    public EncuentroEntity ()
    {
        
    }
    
    // ------------------------------------------------------------------
    // Métodos
    // ------------------------------------------------------------------

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

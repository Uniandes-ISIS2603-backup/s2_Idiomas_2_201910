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
 * Clase que representa una estadia en la persistencia y permite su
 * serialización.
 * @author g.cubillosb
 */
@Entity
public class EstadiaEntity extends ActividadEntity implements Serializable{
    
    // -------------------------------------------------------------------
    // Atributos
    // -------------------------------------------------------------------
    
    /**
     * Atributo que representa el pais de la estadia.
     */
    private String pais;
    
    /**
     * Atributo que representa los comentarios de la estadia.
     */
    // TODO: GC Se define como actividad o como estadia
    @PodamExclude
    @OneToMany(mappedBy = "actividad")
    private List<ComentarioActividadEntity> comentarios = new ArrayList<>();
    
    // ------------------------------------------------------------------
    // Constructor
    // ------------------------------------------------------------------
    
    /**
     * Constructor vacío.
     */
    public EstadiaEntity ()
    {
        
    }
    
    // ------------------------------------------------------------------
    // Métodos
    // ------------------------------------------------------------------

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
     * @return the pais
     */
    public String getPais() {
        return pais;
    }

    /**
     * @param pais the pais to set
     */
    public void setPais(String pais) {
        this.pais = pais;
    }
    
}

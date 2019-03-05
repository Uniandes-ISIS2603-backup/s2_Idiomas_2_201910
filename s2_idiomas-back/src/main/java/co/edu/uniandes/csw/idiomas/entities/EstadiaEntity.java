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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
     * Atributo que representa el anfitrion de la estadia.
     */
    @PodamExclude
    @ManyToOne
    private AnfitrionEntity anfitrion;
    
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

    /**
     * @return the anfitrion
     */
    public AnfitrionEntity getAnfitrion() {
        return anfitrion;
    }

    /**
     * @param anfitrion the anfitrion to set
     */
    public void setAnfitrion(AnfitrionEntity anfitrion) {
        this.anfitrion = anfitrion;
    }
    
}

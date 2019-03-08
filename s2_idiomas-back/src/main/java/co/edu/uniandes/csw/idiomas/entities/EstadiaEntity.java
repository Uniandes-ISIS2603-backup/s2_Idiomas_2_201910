/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
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
    
    /**
     * Atributo que representa los asistentes de la actividad.
     */
    @PodamExclude
    @ManyToMany(mappedBy = "estadias", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<UsuarioEntity> asistentesEstadia = new ArrayList<>();
    // ------------------------------------------------------------------
    // Constructor
    // ------------------------------------------------------------------
    
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
    
    /**
     * Equals de la clase
     */
    @Override
    public boolean equals(Object obj)
    {
        if(!super.equals(obj))
        {
            return false;
        }
        EstadiaEntity fobj = (EstadiaEntity) obj;
        return anfitrion.equals(fobj.getAnfitrion()) && pais.equals(fobj.getPais());
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 19 * hash + Objects.hashCode(this.pais);
        hash = 19 * hash + Objects.hashCode(this.anfitrion);
        return hash;
    }

    /**
     * @return the asistentesEstadia
     */
    public List<UsuarioEntity> getAsistentesEstadia() {
        return asistentesEstadia;
    }

    /**
     * @param asistentesEstadia the asistentesEstadia to set
     */
    public void setAsistentesEstadia(List<UsuarioEntity> asistentesEstadia) {
        this.asistentesEstadia = asistentesEstadia;
    }
}

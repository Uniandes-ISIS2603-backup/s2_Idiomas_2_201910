/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Clase que representa un chat en la persistencia y permite su
 * serialización.
 * @author g.cubillosb
 */
@Entity
@DiscriminatorValue("C")
public class ChatEntity extends ActividadEntity implements Serializable{
    
    // -------------------------------------------------------------------
    // Atributos
    // -------------------------------------------------------------------
    
    /**
     * Atributo que representa la medio del chat.
     */
    private String medio;
    
    // ------------------------------------------------------------------
    // Constructor
    // ------------------------------------------------------------------
    
    // ------------------------------------------------------------------
    // Métodos
    // ------------------------------------------------------------------

    /**
     * @return the medio
     */
    public String getMedio() {
        return medio;
    }

    /**
     * @param medio the medio to set
     */
    public void setMedio(String medio) {
        this.medio = medio;
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
        ChatEntity fobj = (ChatEntity) obj;
        return getMedio().equals(fobj.getMedio());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.medio);
        return hash;
    }

    
    
}

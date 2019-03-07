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
 * Clase que representa un chat en la persistencia y permite su
 * serialización.
 * @author g.cubillosb
 */
@Entity
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
    
    /**
     * Constructor vacío.
     */
    public ChatEntity ()
    {
        
    }
    
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
        if(medio.equals(fobj.getMedio()))
        {
            return true;
        }
        return false;
    }

    
    
}

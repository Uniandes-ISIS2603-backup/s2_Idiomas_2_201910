/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.entities;

import java.io.Serializable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 * @author se.gamboa
 */
@Entity
@DiscriminatorValue("B")
public class ComentarioBlogEntity extends ComentarioEntity implements Serializable{
    
    
    /**
     * Constructor vacío de comentarioBlogEntity.
     */
    public ComentarioBlogEntity()
    {
        
    }


    
    
}

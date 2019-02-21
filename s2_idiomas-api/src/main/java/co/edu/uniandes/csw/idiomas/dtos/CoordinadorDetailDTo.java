/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.dtos;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author estudiante
 */
public class CoordinadorDetailDTo 
{
    private List<ActividadDTO> activiadesCoordinadas;
    
    public CoordinadorDetailDTo()
    {
        
    }

    public List<ActividadDTO> getActiviadesCoordinadas() 
    {
        return activiadesCoordinadas;
    }

    public void setActiviadesCoordinadas(List<ActividadDTO> activiadesCoordinadas) 
    {
        this.activiadesCoordinadas = activiadesCoordinadas;
    }
}

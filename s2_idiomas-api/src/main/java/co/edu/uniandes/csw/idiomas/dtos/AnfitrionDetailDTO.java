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
public class AnfitrionDetailDTO 
{
    private List<AnfitrionDTO> estadias;
    
    public AnfitrionDetailDTO()
    {
        
    }

    public List<AnfitrionDTO> getEstadias() 
    {
        return estadias;
    }

    public void setEstadias(List<AnfitrionDTO> estadias) 
    {
        this.estadias = estadias;
    }
}

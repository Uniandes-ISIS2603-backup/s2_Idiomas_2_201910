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
public class UsuarioDetailDTO
{
   private List<ActividadDTO> activiadesProgramadas;
    
    public UsuarioDetailDTO()
    {
        
    }

    public List<ActividadDTO> getActiviadesProgramadas() 
    {
        return activiadesProgramadas;
    }

    public void setActiviadesProgramadas(List<ActividadDTO> activiadesProgramadas) 
    {
        this.activiadesProgramadas = activiadesProgramadas;
    }
}

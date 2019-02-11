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
   private List<AdministradorDTO> activiadesProgramadas;
    
    public UsuarioDetailDTO()
    {
        
    }

    public List<AdministradorDTO> getActiviadesProgramadas() 
    {
        return activiadesProgramadas;
    }

    public void setActiviadesProgramadas(List<AdministradorDTO> activiadesProgramadas) 
    {
        this.activiadesProgramadas = activiadesProgramadas;
    }
}

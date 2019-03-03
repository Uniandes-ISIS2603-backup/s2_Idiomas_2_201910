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
public class AdministradorDetailDTO  extends AdministradorDTO implements Serializable{
    
    
    private List<GrupoDeInteresDTO> gruposAdministrados;
    
    public AdministradorDetailDTO()
    {
        
    }

    public List<GrupoDeInteresDTO> getGruposAdministrados() 
    {
        return gruposAdministrados;
    }

    public void setGruposAdministrados(List<GrupoDeInteresDTO> gruposAdministrados) 
    {
        this.gruposAdministrados = gruposAdministrados;
    }
            }

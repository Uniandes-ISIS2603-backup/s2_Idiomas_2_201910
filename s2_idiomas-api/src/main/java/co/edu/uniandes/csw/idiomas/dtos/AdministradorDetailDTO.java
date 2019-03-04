/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.dtos;

import co.edu.uniandes.csw.idiomas.entities.AdministradorEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Josealejandro Barbosa
 */
public class AdministradorDetailDTO  extends AdministradorDTO implements Serializable{
    
    
    private List<GrupoDeInteresDTO> gruposAdministrados;
    
    
    
    public AdministradorDetailDTO()
    {
        super();
    }
    
    /**
     * Crea un objeto AdministradorDetailDTO a partir de un objeto AdministradorEntity
     * incluyendo los atributos de AdministradorDTO.
     *
     * @param administradorEntity Entidad AdministradorEntity desde la cual se va a crear el
     * nuevo objeto.
     *
     */
    public AdministradorDetailDTO(AdministradorEntity administradorEntity) {
        super(administradorEntity);
        if (administradorEntity != null) {
            gruposAdministrados = new ArrayList<>();           
        }
    }
    
    /**
     * Convierte un objeto AdministradorDetailDTO a AdministradorEntity incluyendo los
     * atributos de AdministradorDTO.
     *
     * @return Nueva objeto AdministradorEntity.
     *
     */
    @Override
    public AdministradorEntity toEntity() {
        AdministradorEntity administradorEntity = super.toEntity();
        if (gruposAdministrados != null) {
            //falta grupo Entity y la relacion
//            administradorEntity.set
        }
       
        return administradorEntity;
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

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
 * @author le.perezl
 */
public class GrupoDeInteresDetailDTO extends GrupoDeInteresDTO implements Serializable{
    
    private List<GrupoDeInteresDTO> usuariosGrupo;
    private List<GrupoDeInteresDTO> actividadesGrupo;
    private List<GrupoDeInteresDTO> comentariosGrupo;
    
    public GrupoDeInteresDetailDTO()
    {
        
    }

    public List<GrupoDeInteresDTO> getComentariosGrupo() {
        return comentariosGrupo;
    }

    public void setComentariosGrupo(List<GrupoDeInteresDTO> comentariosGrupo) {
        this.comentariosGrupo = comentariosGrupo;
    }
    
       
    public List<GrupoDeInteresDTO> getUsuariosGrupo() 
    {
        return usuariosGrupo;
    }

    public void setUsuariosGrupo(List<GrupoDeInteresDTO> pUsuariosGrupo) 
    {
        this.usuariosGrupo = pUsuariosGrupo;
    }
    
    public List<GrupoDeInteresDTO> getActividadesGrupo() {
        return actividadesGrupo;
    }

    public void setActividadesGrupo(List<GrupoDeInteresDTO> actividadesGrupo) {
        this.actividadesGrupo = actividadesGrupo;
    }

 }


    
    





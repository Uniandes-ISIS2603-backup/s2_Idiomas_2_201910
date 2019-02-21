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
    
    private List<UsuarioDTO> usuariosGrupo;
    private List<ActividadDTO> actividadesGrupo;
    private List<ComentarioGrupoDTO> comentariosGrupo;
    
    public GrupoDeInteresDetailDTO()
    {
        
    }

    public List<ComentarioGrupoDTO> getComentariosGrupo() {
        return comentariosGrupo;
    }

    public void setComentariosGrupo(List<ComentarioGrupoDTO> comentariosGrupo) {
        this.comentariosGrupo = comentariosGrupo;
    }
    
       
    public List<UsuarioDTO> getUsuariosGrupo() 
    {
        return usuariosGrupo;
    }

    public void setUsuariosGrupo(List<UsuarioDTO> pUsuariosGrupo) 
    {
        this.usuariosGrupo = pUsuariosGrupo;
    }
    
    public List<ActividadDTO> getActividadesGrupo() {
        return actividadesGrupo;
    }

    public void setActividadesGrupo(List<ActividadDTO> actividadesGrupo) {
        this.actividadesGrupo = actividadesGrupo;
    }

 }


    
    





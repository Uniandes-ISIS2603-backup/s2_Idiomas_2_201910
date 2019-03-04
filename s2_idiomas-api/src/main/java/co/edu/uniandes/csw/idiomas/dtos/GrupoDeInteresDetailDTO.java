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
    /**
     * Lista de usuarioDTO que contiene los usuarios del grupo
     */
    private List<UsuarioDTO> usuariosGrupo;
    /**
     * Lista de ActividadDTO que contiene las actividades del grupo
     */
    private List<ActividadDTO> actividadesGrupo;
    /**
     * lista de comentarioDTO que contiene los comentarios del grupo
     */
    private List<ComentarioGrupoDTO> comentariosGrupo;
    
    /**
     * constructor vacio
     */
    public GrupoDeInteresDetailDTO()
    {
        
    }

    /**
     * @return comentarios del grupo
    */
    public List<ComentarioGrupoDTO> getComentariosGrupo() {
        return comentariosGrupo;
    }
    /**
     * @param comentarios del grupo
    */
    public void setComentariosGrupo(List<ComentarioGrupoDTO> comentariosGrupo) {
        this.comentariosGrupo = comentariosGrupo;
    }
    
    /**
     * @return usuarios del grupo
    */   
    public List<UsuarioDTO> getUsuariosGrupo() 
    {
        return usuariosGrupo;
    }
    /**
     * @param Usuarios del grupo
    */
    public void setUsuariosGrupo(List<UsuarioDTO> pUsuariosGrupo) 
    {
        this.usuariosGrupo = pUsuariosGrupo;
    }
    /**
     * @return Actividades del grupo
    */
    public List<ActividadDTO> getActividadesGrupo() {
        return actividadesGrupo;
    }
    /**
     * @param Actividades del grupo
    */
    public void setActividadesGrupo(List<ActividadDTO> actividadesGrupo) {
        this.actividadesGrupo = actividadesGrupo;
    }

 }


    
    





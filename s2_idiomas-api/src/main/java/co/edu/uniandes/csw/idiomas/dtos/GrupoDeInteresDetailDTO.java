/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.dtos;

import co.edu.uniandes.csw.idiomas.entities.ActividadEntity;
import co.edu.uniandes.csw.idiomas.entities.CalificacionEntity;
import co.edu.uniandes.csw.idiomas.entities.GrupoDeInteresEntity;
import co.edu.uniandes.csw.idiomas.entities.UsuarioEntity;
import java.io.Serializable;
import java.util.ArrayList;
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
    private List<ComentarioDTO> comentariosGrupo;
      /**
     * lista de comentarioDTO que contiene los comentarios del grupo
     */
    private List<CalificacionDTO> calificacionesGrupo;
    
    /**
     * constructor vacio
     */
    public GrupoDeInteresDetailDTO()
    {
        
    }
    public GrupoDeInteresDetailDTO(GrupoDeInteresEntity grupoEntity){
       super(grupoEntity);
       if (grupoEntity !=null){
//           if(grupoEntity.getUsuariosGrupo()!=null){
//               usuariosGrupo= new ArrayList<>();
//               for(UsuarioEntity entityUsuario: grupoEntity.getUsuariosGrupo()){
//                   usuariosGrupo.add(new UsuarioDTO(entityUsuario));
//               }
//           }
//           if(grupoEntity.getActividadesGrupo()!=null){
//               actividadesGrupo= new ArrayList<>();
//               for(ActividadEntity entityActividad: grupoEntity.getActividadesGrupo()){
//                   actividadesGrupo.add(new ActividadDTO(entityActividad));
//               }
//           }
           if(grupoEntity.getCalificaciones()!=null){
               ArrayList<Object> calificacionesGrupo = new ArrayList<>();
               for(CalificacionEntity entityCalificacion: grupoEntity.getCalificaciones()){
                   calificacionesGrupo.add(new CalificacionDTO(entityCalificacion));
               }
           }
       }
    }

    /**
     * @return comentarios del grupo
    */
    
    @Override
    public GrupoDeInteresEntity toEntity(){
        GrupoDeInteresEntity grupoEntity= super.toEntity();
//        if(usuariosGrupo !=null){
//            List<UsuarioEntity> usuariosEntity= new ArrayList<>();
//            for(UsuarioDTO dtoUsuario: usuariosGrupo){
//                usuariosEntity.add(dtoUsuario.toEntity());
//            }
//            grupoEntity.setUsuariosGrupo(usuariosEntity);
//        }
//        if(actividadesGrupo !=null){
//            List<ActividadEntity> actividadesEntity= new ArrayList<>();
//            for(ActividadDTO dtoActividad: actividadesGrupo){
//                actividadesEntity.add(dtoActividad.toEntity());
//            }
//            grupoEntity.setActividadesGrupo(actividadesEntity);
//        }
        if(calificacionesGrupo !=null){
            List<CalificacionEntity> calificacionesEntity= new ArrayList<>();
            for(CalificacionDTO dtoCalificacion:calificacionesGrupo){
                calificacionesEntity.add(dtoCalificacion.toEntity());
            }
            grupoEntity.setCalificaciones(calificacionesEntity);
        }
        return grupoEntity;
    }
    
    public List<ComentarioDTO> getComentariosGrupo() {
        return comentariosGrupo;
    }
    /**
     * @param comentarios del grupo
    */
    public void setComentariosGrupo(List<ComentarioDTO> comentariosGrupo) {
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


    
    





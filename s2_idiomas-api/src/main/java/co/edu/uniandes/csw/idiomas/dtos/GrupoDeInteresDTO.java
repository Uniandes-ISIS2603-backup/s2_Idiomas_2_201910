/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.dtos;

import co.edu.uniandes.csw.idiomas.entities.GrupoDeInteresEntity;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * AdministradorDTO Objeto de transferencia de datos de Administradores. Los DTO
 * contienen las representaciones de los JSON que se transfieren entre el
 * cliente y el servidor.
 *
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "idioma": String,
 *      "administrador": string
 *      "calificacion" : number
 *      "blog":number
 *      
 *   }
 * </pre> Por ejemplo un GrupoDeInteres se representa asi:<br>
 *
 * <pre>
 *
 *   {
 *      "idioma": Ingles,
 *      "administrador": "XYZ",
 *      "calificacion": 5,
 *      "blog":1,

 *   }
 *
 * </pre>
 *
 *
 * @author le.perezl
 */
public class GrupoDeInteresDTO implements Serializable{
    private Long id;
    /**
     * idioma del grupo
     */
    String idioma;
    /**
     * administrador del grupo
     */
    String administrador;
    /**
     * calificacion del grupo
     */
//    Long calificacion;
    /**
     * blog del grupo
     */
    Long blog;
    
    public GrupoDeInteresDTO(){
        
    }
    
    public GrupoDeInteresDTO(GrupoDeInteresEntity grupoEntity){
        if(grupoEntity !=null){
           this.id = grupoEntity.getId();
           this.idioma= grupoEntity.getIdioma();
           this.administrador= grupoEntity.getAdministrador();
//           this.blog=grupoEntity.getBlog();
                   
        }
            
    }
    public GrupoDeInteresEntity toEntity(){
        //crear variable del tipo
        //hacer traspaso de los datos
        GrupoDeInteresEntity grupoEntity = new GrupoDeInteresEntity();
        grupoEntity.setId(this.id);
        grupoEntity.setAdministrador(this.administrador);
        grupoEntity.setIdioma(this.idioma);
//        grupoEntity.setBlog(this.blog);
        return null;
        
    }
        /**
     * Devuelve el Idioma del grupo.
     *
     * @return the id
     */
    public String getIdioma() {
        return idioma;
    }
        
       /**
     * Devuelve el Idioma del grupo.
     *
     * @return the id
     */
    public Long getBlog() {
        return blog;
    }

    /**
     * Modifica el Admin dl grupo.
     *
     * @param admin the admin to set
     */
    public void setAdministrador (String pAdmin) {
        this.administrador = pAdmin;
    }
 
    public String getAdministrador() {
        return administrador;
    }

    /**
     * Modifica la califiacion del grupo.
     *
     * @param calificacion the calificacion to set
     */
//    public void setCalificacion(Long pCalificacion) {
//        this.calificacion = pCalificacion;
//    }
    
      /**
     * retorna la califiacion del grupo.
     *
     */
//    public Long getCalificacion() {
//       return calificacion;
//    }



    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
}

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }
   
}

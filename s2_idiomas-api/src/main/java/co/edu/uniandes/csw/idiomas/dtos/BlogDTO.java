/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.dtos;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author estudiante
 */
public class BlogDTO {
    String texto;
    String imagen;
    String video;
    Long grupoDeInteres;
    
    public BlogDTO(){
        
    }
        /**
     * Devuelve el Idioma del grupo.
     *
     * @return the id
     */
    public String getTexto() {
        return texto;
    }
        
       /**
     * Devuelve el Idioma del grupo.
     *
     * @return the id
     */
    public String getImagen() {
        return imagen;
    }

    /**
     * Modifica el Admin dl grupo.
     *
     * @param admin the admin to set
     */
    public void setTexto (String pTexto) {
        this.texto = pTexto;
    }
 
    public String getVideo() {
        return video;
    }

    /**
     * Modifica la califiacion del grupo.
     *
     * @param calificacion the calificacion to set
     */
    public void setImagen(String pImagen) {
        this.imagen = pImagen;
    }
       /**
     * Modifica la califiacion del grupo.
     *
     * @param calificacion the calificacion to set
     */
    public void setVideo(String pVideo) {
        this.video = pVideo;
    }
      /**
     * retorna la califiacion del grupo.
     *
     */
    public Long getGrupoDeInteres() {
       return grupoDeInteres;
    }
    
    public void setGrupoDeInteres(Long pGrupo) {
        this.grupoDeInteres = pGrupo;
    }


    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
}



}
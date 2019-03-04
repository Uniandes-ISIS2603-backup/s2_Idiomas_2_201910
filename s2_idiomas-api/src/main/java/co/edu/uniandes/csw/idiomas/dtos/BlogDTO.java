/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.dtos;

import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author Luis Perez le.perezl
 */
public class BlogDTO implements Serializable{
    /**
     * texto del blog
     */
    String texto;
    /**
     * imagen del blog
     */
    String imagen;
    /**
     * video del blog
     */
    String video;
    /**
     * grupo dueño del blog
     */
    Long grupoDeInteres;
    
    /**
     * Constructor
     */
    public BlogDTO(){
        
    }
        /**
     * Devuelve el Texto del Blog.
     *
     * @return the id
     */
    public String getTexto() {
        return texto;
    }
        
       /**
     * Devuelve la imagen del Blog.
     *
     * @return the id
     */
    public String getImagen() {
        return imagen;
    }

    /**
     * Modifica el Texto dl Blog.
     *
     * @param pTexto the texto to set
     */
    public void setTexto (String pTexto) {
        this.texto = pTexto;
    }
    /**
     * 
     * @return video del blog
     */
    public String getVideo() {
        return video;
    }

    /**
     * Modifica la Imagen del Blog.
     *
     * @param pImagen the Imagen to set
     */
    public void setImagen(String pImagen) {
        this.imagen = pImagen;
    }
       /**
     * Modifica la video del Blog.
     *
     * @param pVideo the video to set
     */
    public void setVideo(String pVideo) {
        this.video = pVideo;
    }
      /**
     * retorna El GrupoDeInteres del Blog.
     *@return grupoDeInteres
     */
    public Long getGrupoDeInteres() {
       return grupoDeInteres;
    }
    /**
     * cambia el grupo del blog
     * @param pGrupo del blog
     */
    public void setGrupoDeInteres(Long pGrupo) {
        this.grupoDeInteres = pGrupo;
    }


    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
}



}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.entities;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author estudiante
 */
@Entity
public class BlogEntity extends BaseEntity implements Serializable{
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
    
    public BlogEntity(){
        
    }
    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public Long getGrupoDeInteres() {
        return grupoDeInteres;
    }

    public void setGrupoDeInteres(Long grupoDeInteres) {
        this.grupoDeInteres = grupoDeInteres;
    }

}

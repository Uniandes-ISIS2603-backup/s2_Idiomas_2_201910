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
 * @author Luis Perez le.perezl
 */
public class BlogDetailDTO extends BlogDTO implements Serializable {
    /**
     * Lista de comentarioDTO del Blog
     */
    private List<ComentarioBlogDTO> comentarioBlog;

    /**
     *@return comentarios del blog
    */
    public List<ComentarioBlogDTO> getComentarioBlog() {
        return comentarioBlog;
    }

    /**
     * Cambia los comentarios del blog
     * @param comentarioBlog del Blog
     */
    public void setComentarioBlog(List<ComentarioBlogDTO> comentarioBlog) {
        this.comentarioBlog = comentarioBlog;
    }
    
    
}

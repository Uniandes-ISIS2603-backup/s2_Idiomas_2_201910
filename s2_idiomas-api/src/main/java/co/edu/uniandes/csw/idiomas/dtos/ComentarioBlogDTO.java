/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.dtos;

import co.edu.uniandes.csw.idiomas.entities.ComentarioBlogEntity;
import co.edu.uniandes.csw.idiomas.entities.ComentarioEntity;
import java.io.Serializable;

/**
 *
 * @author se.gamboa
 */
public class ComentarioBlogDTO extends ComentarioDTO implements Serializable {

    private String titulo;

    /**
     * Constructor de ComentarioBlogDTO
     *
     * @param entity recibe el titulo del comentario.
     * @param entity2 recibe la informacion general de un comentario.
     */
    public ComentarioBlogDTO(ComentarioBlogEntity entity, ComentarioEntity entity2) {
        super(entity2);
        if (entity != null) {
            this.titulo = entity.getTitulo();
        }
    }

    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Convierte un objeto DTO a una Entidad.
     *
     * @return entidad convertida.
     */
    public ComentarioBlogEntity toEntityB() {
        ComentarioBlogEntity entity = new ComentarioBlogEntity();
        entity.setTitulo(this.titulo);
        return entity;
    }
}

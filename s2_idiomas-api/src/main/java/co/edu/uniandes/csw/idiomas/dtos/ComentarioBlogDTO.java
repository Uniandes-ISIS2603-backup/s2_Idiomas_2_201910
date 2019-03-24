/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.dtos;

import co.edu.uniandes.csw.idiomas.entities.ComentarioBlogEntity;
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
     */
    public ComentarioBlogDTO(ComentarioBlogEntity entity) {
        super(entity);
        if (entity != null) {
            this.titulo = entity.getTitulo();
        }
    }
    
    public ComentarioBlogDTO(){
        super();
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
    @Override
    public ComentarioBlogEntity toEntity() {
        ComentarioBlogEntity entity = new ComentarioBlogEntity();
        entity.setTitulo(this.titulo);
        entity.setFecha(this.getFecha());
        entity.setTexto(this.getTexto());
        entity.setAutor(this.getAutor().toEntity());
        return entity;
    }
}

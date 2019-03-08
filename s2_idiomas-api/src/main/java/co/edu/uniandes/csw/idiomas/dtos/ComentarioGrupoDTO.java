/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.dtos;

import co.edu.uniandes.csw.idiomas.entities.ComentarioEntity;
import co.edu.uniandes.csw.idiomas.entities.ComentarioGrupoEntity;
import java.io.Serializable;

/**
 *
 * @author se.gamboa
 */
public class ComentarioGrupoDTO extends ComentarioDTO implements Serializable {

    private String titulo;

    /**
     * Constructor de ComentarioActividadDTO
     *
     * @param entity recibe la informacion del comentario.
     */
    public ComentarioGrupoDTO(ComentarioGrupoEntity entity) {
        super(entity);
        if (entity != null) {
            this.titulo = entity.getTitulo();
        }
    }
    
    public ComentarioGrupoDTO(){
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

    @Override
    public ComentarioGrupoEntity toEntity() {
        ComentarioGrupoEntity entity = new ComentarioGrupoEntity();
        entity.setTitulo(this.titulo);
        entity.setFecha(this.getFecha());
        entity.setTexto(this.getTexto());
        entity.setAutor(this.getAutor().toEntity());
        return entity;
    }
}

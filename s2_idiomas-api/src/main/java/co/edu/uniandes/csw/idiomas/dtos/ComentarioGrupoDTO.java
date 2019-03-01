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
     * @param entity2 recibe la informacion de un comentario general.
     * @param entity recibe la informacion del comentario.
     */
    public ComentarioGrupoDTO(ComentarioEntity entity2, ComentarioGrupoEntity entity) {
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

    public ComentarioGrupoEntity toEntityG() {
        ComentarioGrupoEntity entity = new ComentarioGrupoEntity();
        entity.setTitulo(this.titulo);
        return entity;
    }
}

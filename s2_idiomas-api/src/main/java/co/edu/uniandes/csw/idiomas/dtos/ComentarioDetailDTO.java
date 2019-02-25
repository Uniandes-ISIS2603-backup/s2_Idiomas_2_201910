/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.dtos;

import co.edu.uniandes.csw.idiomas.entities.ComentarioEntity;
import co.edu.uniandes.csw.idiomas.entities.PersonaEntity;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author se.gamboa
 */
public class ComentarioDetailDTO extends ComentarioDTO implements Serializable {

    private PersonaEntity autor;

    public ComentarioDetailDTO(ComentarioEntity entity) {
        super(entity);
        if (entity != null) {
            this.autor = entity.getAutor();
        }

    }

    public ComentarioEntity toEntityD() {
        ComentarioEntity entity = new ComentarioEntity();
        entity.setAutor(this.getAutor());
        return entity;
    }

    /**
     * @return the autor
     */
    public PersonaEntity getAutor() {
        return autor;
    }

    /**
     * @param autor the autor to set
     */
    public void setAutor(PersonaEntity autor) {
        this.autor = autor;
    }
}

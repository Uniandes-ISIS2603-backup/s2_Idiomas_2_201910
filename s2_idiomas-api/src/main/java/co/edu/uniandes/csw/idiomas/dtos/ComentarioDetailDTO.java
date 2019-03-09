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

    private PersonaDTO autor;

    public ComentarioDetailDTO(ComentarioEntity entity) {
        super(entity);
        if (entity != null) {
            if (entity.getAutor() != null) {
                this.autor = new PersonaDTO(entity.getAutor());
            }
        }

    }

    public ComentarioDetailDTO() {
        super();
    }

    @Override
    public ComentarioEntity toEntity() {
        ComentarioEntity entity = super.toEntity();
        if (autor != null) {
            entity.setAutor(this.getAutor().toEntity());
        }
        return entity;
    }

    /**
     * @return the autor
     */
    @Override
    public PersonaDTO getAutor() {
        return autor;
    }

    /**
     * @param autor the autor to set
     */
    @Override
    public void setAutor(PersonaDTO autor) {
        this.autor = autor;
    }
}

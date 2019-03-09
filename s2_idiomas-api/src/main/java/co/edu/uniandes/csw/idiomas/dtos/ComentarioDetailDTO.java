package co.edu.uniandes.csw.idiomas.dtos;

import co.edu.uniandes.csw.idiomas.entities.ComentarioEntity;
import java.io.Serializable;

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

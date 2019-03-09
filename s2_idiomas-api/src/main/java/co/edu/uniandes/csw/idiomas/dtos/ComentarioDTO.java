package co.edu.uniandes.csw.idiomas.dtos;

import co.edu.uniandes.csw.idiomas.entities.ComentarioEntity;
import co.edu.uniandes.csw.idiomas.entities.PersonaEntity;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author se.gamboa
 */
public class ComentarioDTO implements Serializable {

    /**
     * @return the autor
     */
    public PersonaDTO getAutor() {
        return autor;
    }

    /**
     * @param autor the autor to set
     */
    public void setAutor(PersonaDTO autor) {
        this.autor = autor;
    }

    private String texto;
    private Date fecha;
    private Long id;
    private PersonaDTO autor;

    /**
     * Constructor de ComentarioActividadDTO
     *
     * @param entity recibe la informacion del comentario.
     */
    public ComentarioDTO(ComentarioEntity entity) {
        if (entity != null) {
            this.texto = entity.getTexto();
            this.fecha = entity.getFecha();
            this.id = entity.getId();
            this.autor = new PersonaDTO(entity.getAutor());
        }
    }
    
    public ComentarioDTO(){
        
    }

    /**
     * @return the texto
     */
    public String getTexto() {
        return texto;
    }

    /**
     * @param texto the texto to set
     */
    public void setTexto(String texto) {
        this.texto = texto;
    }

    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Convierte un objeto DTO a una Entidad.
     *
     * @return entidad convertida.
     */
    public ComentarioEntity toEntity() {
        ComentarioEntity entity = new ComentarioEntity();
        entity.setFecha(this.getFecha());
        entity.setTexto(this.getTexto());
        entity.setId(this.getId());
        entity.setAutor(this.getAutor().toEntity());
        return entity;
    }

}

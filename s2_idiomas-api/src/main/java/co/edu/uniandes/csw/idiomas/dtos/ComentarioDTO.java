/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.dtos;

import co.edu.uniandes.csw.idiomas.entities.ComentarioEntity;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Santiago Gamboa
 */
public class ComentarioDTO implements Serializable {

    private String texto;
    private Date fecha;
    private Long id;

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
        }
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
        entity.setFecha(this.fecha);
        entity.setTexto(this.texto);
        entity.setId(this.id);
        return entity;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author se.gamboa
 */
@Entity
public class ComentarioEntity extends BaseEntity implements Serializable {

    private String texto;

    @Temporal(TemporalType.DATE)
    private Date fecha;

    @ManyToOne
    private PersonaEntity autor;

    /**
     * Constructor vacío de ComentarioEntity.
     */
    public ComentarioEntity() {

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

    /**
     * @return the comments
     */
    //public List<ComentarioEntity> getComments() {
    //    return comments;
    //}
    /**
     * @param comments the comments to set
     */
    //public void setComments(List<ComentarioEntity> comments) {
    //     this.comments = comments;
    // }
}

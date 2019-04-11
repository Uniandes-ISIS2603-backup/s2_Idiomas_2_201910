/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.entities;

import co.edu.uniandes.csw.idiomas.podam.DateStrategy;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.DiscriminatorValue;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.InheritanceType;
import javax.persistence.Inheritance;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;  
import uk.co.jemos.podam.common.PodamExclude;
import uk.co.jemos.podam.common.PodamStrategyValue;

/**
 *
 * @author se.gamboa
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "typeofcomentario", discriminatorType = DiscriminatorType.CHAR)
@DiscriminatorValue("C")

public class ComentarioEntity extends BaseEntity implements Serializable {

    private String texto;
    private String titulo;

    @Temporal(TemporalType.DATE)
    @PodamStrategyValue(DateStrategy.class)
    private Date fecha;

    @PodamExclude
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author jd.ruedaa
 */
@Entity
public class CalificacionEntity extends BaseEntity implements Serializable{
    @Id
    private Long id;
    private Integer calificacion;
    private String mensaje;
    @ManyToOne
    private ActividadEntity activ;
    @ManyToOne
    private AdministradorEntity admin;
    @ManyToOne
    private CoordinadorEntity coord;
    @ManyToOne
    private GrupoDeInteresEntity grupo;
    @PodamExclude
    @OneToMany(mappedBy = "calificaciones")
    private List<ComentarioEntity> comentarios = new ArrayList<>();
    
    @Override
    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }
    @Override
    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the calificacion
     */
    public Integer getCalificacion() {
        return calificacion;
    }

    /**
     * @param calificacion the calificacion to set
     */
    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }

    /**
     * @return the mensaje
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * @param mensaje the mensaje to set
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    
    /**
     * @return the grupo
     */
    public GrupoDeInteresEntity getGrupo() {
        return grupo;
    }

    /**
     * @param grupo the grupo to set
     */
    public void setGrupo(GrupoDeInteresEntity grupo) {
        this.grupo = grupo;
    }

    /**
     * @return the comentarios
     */
    public List<ComentarioEntity> getComentarios() {
        return comentarios;
    }

    /**
     * @param comentarios the comentarios to set
     */
    public void setComentarios(List<ComentarioEntity> comentarios) {
        this.comentarios = comentarios;
    }
    
    public CalificacionEntity()
    {
        // Constructor vacío
    }

    /**
     * @return the activ
     */
    public ActividadEntity getActiv() {
        return activ;
    }

    /**
     * @param activ the activ to set
     */
    public void setActiv(ActividadEntity activ) {
        this.activ = activ;
    }

    /**
     * @return the admin
     */
    public AdministradorEntity getAdmin() {
        return admin;
    }

    /**
     * @param admin the admin to set
     */
    public void setAdmin(AdministradorEntity admin) {
        this.admin = admin;
    }

    /**
     * @return the coord
     */
    public CoordinadorEntity getCoord() {
        return coord;
    }

    /**
     * @param coord the coord to set
     */
    public void setCoord(CoordinadorEntity coord) {
        this.coord = coord;
    }
    
}

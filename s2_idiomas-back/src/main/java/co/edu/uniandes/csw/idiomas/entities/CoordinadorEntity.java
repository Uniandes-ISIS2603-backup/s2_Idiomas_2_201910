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
import javax.persistence.ManyToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author j.barbosaj
 */
@Entity
public class CoordinadorEntity  extends BaseEntity implements Serializable
{
    Long contrasenia;
    String nombre;    
    
    @PodamExclude
    @ManyToMany(mappedBy = "cordinadores")
    private List<ActividadEntity> actividadesCoordinadas = new ArrayList<>();
    /**
     * Connstructor vacio de un Entity
     */
    public CoordinadorEntity()
    {
        
    }
    
    /**
     * Retorna la contrasenia de un Entity
     * @return contrasenia la contrseña
     */
    public Long getContrasenia() {
        return contrasenia;
    }
    
    /**
     * Asigna una contrasenia a un Entity
     * @param contrasenia 
     */
    public void setContrasenia(Long contrasenia) {
        this.contrasenia = contrasenia;
    }

    /**
     * Retorna el nombre del Entity
     * @return nombre -el nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Asigna un nombre al Entity
     * @param nombre 
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<ActividadEntity> getActividadesCoordinadas() {
        return actividadesCoordinadas;
    }

    public void setActividadesCoordinadas(List<ActividadEntity> actividadesCoordinadas) {
        this.actividadesCoordinadas = actividadesCoordinadas;
    }
    
    
}

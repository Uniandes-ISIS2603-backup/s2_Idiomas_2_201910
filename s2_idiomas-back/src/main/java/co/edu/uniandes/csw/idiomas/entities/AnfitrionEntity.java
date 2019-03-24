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
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author j.barbosaj
 */
@Entity
public class AnfitrionEntity extends BaseEntity implements Serializable {
    String nombre;
    Long contrasenia;
    String pais;
    String ciudad;
    String direccion;
    
    @PodamExclude
    @OneToMany(mappedBy = "anfitrion")
    private List<EstadiaEntity> estadias = new ArrayList<>();

    public List<EstadiaEntity> getEstadias() {
        return estadias;
    }

    public void setEstadias(List<EstadiaEntity> estadias) {
        this.estadias = estadias;
    }

    /**
     * Connstructor vacio de un Entity
     */
    public AnfitrionEntity()
    {
        //contructor vacio
    }
    /**
     * retorna el nombre 
     * @return nombre -el nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Asigna un nombre 
     * @param nombre 
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * retrona la contrasenia
     * @return contrasenia -la contrasenia
     */
    public Long getContrasenia() {
        return contrasenia;
    }

    /**
     * Asigna una contrasenia
     * @param contrasenia 
     */
    public void setContrasenia(Long contrasenia) {
        this.contrasenia = contrasenia;
    }

    /**
     * Retorna el pais
     * @return pais -el pais
     */
    public String getPais() {
        return pais;
    }

    /**
     * Asigna el pais
     * @param pais 
     */
    public void setPais(String pais) {
        this.pais = pais;
    }

    /**
     * Retorna la ciudad
     * @return ciudad -la ciudad
     */
    public String getCiudad() {
        return ciudad;
    }

    /**
     * Asigna la ciudad
     * @param ciudad 
     */
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    /**
     * Retorna la direccion
     * @return direcion -la direcion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Asigna la direccion
     * @param direccion 
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    
}

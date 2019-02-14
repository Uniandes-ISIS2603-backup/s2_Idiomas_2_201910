/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.entities;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author j.barbosaj
 */
@Entity
public class AnfitrionEntity extends BaseEntity implements Serializable {
    String nombre;
    Long contraseña;
    String pais;
    String ciudad;
    String direccion;

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
     * retrona la contraseña
     * @return contraseña -la contraseña
     */
    public Long getContrasenia() {
        return contraseña;
    }

    /**
     * Asigna una contraseña
     * @param contraseña 
     */
    public void setContraseña(Long contraseña) {
        this.contraseña = contraseña;
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.dtos;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * AnfitrionDTO Objeto de transferencia de datos de Anfitriones. Los DTO
 * contienen las representaciones de los JSON que se transfieren entre el
 * cliente y el servidor.
 *
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "id": number,
 *      "nombre": string
 *      "contraseña" : string
 *      "pais": string
 *      "ciudad": string
 *      "direccion": string
 *   }
 * </pre> Por ejemplo una anfitrion se representa asi:<br>
 *
 * <pre>
 *
 *   {
 *      "id": 1,
 *      "nombre": juan
 *      "contraseña" : 1234
 *      "pais": francia
 *      "ciudad": paris
 *      "direccion": car 12 - 2
 *   }
 *
 * </pre>
 *
 * @author j.barbosa
 */
public class AnfitrionDTO {
    Long id;
    String nombre;
    Long contraseña;
    String pais;
    String ciudad;
    String direccion;
    
    
    /**
     * Constructor por defecto
     */
    public AnfitrionDTO() {
    }

    /**
     * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param anfitrionEntity: Es la entidad que se va a convertir a DTO
     */
//    public AnfitrionDTO(AnfitrionEntity anfitrionEntity) {
//        if (anfitrionEntity != null) {
//            this.id = anfitrionEntity.getId();
//            this.nombre = anfitrionEntity.getNombre();
//        }
//    }

    /**
     * Devuelve el ID de la anfitrion.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Modifica el ID de la anfitrion.
     *
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Devuelve el nombre de la anfitrion.
     *
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Modifica el nombre de la anfitrion.
     *
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
     /**
     * Devuelve el ciudad de la anfitrion.
     *
     * @return the ciudad
     */
    public String getCiudad() {
        return ciudad;
    }

    /**
     * Modifica el ciudad de la anfitrion.
     *
     * @param ciudad the ciudad to set
     */
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
    
     /**
     * Devuelve el pais de la anfitrion.
     *
     * @return the pais
     */
    public String getPais() {
        return pais;
    }

    /**
     * Modifica el pais de la anfitrion.
     *
     * @param pais the pais to set
     */
    public void setPais(String pais) {
        this.pais = pais;
    }
    
     /**
     * Devuelve el direccion de la anfitrion.
     *
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Modifica el direccion de la anfitrion.
     *
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

//    /**
//     * Convertir DTO a Entity
//     *
//     * @return Un Entity con los valores del DTO
//     */
//    public AnfitrionEntity toEntity() {
//        AnfitrionEntity anfitrionEntity = new AnfitrionEntity();
//        anfitrionEntity.setId(this.id);
//        anfitrionEntity.setNombre(this.nombre);
//            aqui falta lo de direccion y pais pero tu yo del pasado le dio pereza
//        return anfitrionEntity;
//    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}

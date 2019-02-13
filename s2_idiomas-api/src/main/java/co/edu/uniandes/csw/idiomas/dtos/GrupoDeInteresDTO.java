/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.dtos;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * AdministradorDTO Objeto de transferencia de datos de Administradores. Los DTO
 * contienen las representaciones de los JSON que se transfieren entre el
 * cliente y el servidor.
 *
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "idioma": String,
 *      "administrador": string
 *      "calificacion" : number
 *   }
 * </pre> Por ejemplo un GrupoDeInteres se representa asi:<br>
 *
 * <pre>
 *
 *   {
 *      "idioma": Ingles,
 *      "administrador": "XYZ",
 *      "calificacion": 5,

 *   }
 *
 * </pre>
 *
 *
 * @author le.perezl
 */
public class GrupoDeInteresDTO {
    String idioma;
    String administrador;
    Long calificacion;
    
    public GrupoDeInteresDTO(){
        
    }
        /**
     * Devuelve el ID de la administrador.
     *
     * @return the id
     */
    public String getIdioma() {
        return idioma;
    }

    /**
     * Modifica el ID de la administrador.
     *
     * @param id the id to set
     */
    public void setAdministrador (String pAdmin) {
        this.administrador = pAdmin;
    }

    /**
     * Devuelve el nombre de la administrador.
     *
     * @return the nombre
     */
    public String getAdministrador() {
        return administrador;
    }

    /**
     * Modifica el nombre de la administrador.
     *
     * @param nombre the nombre to set
     */
    public void setCalificacion(Long pCalificacion) {
        this.calificacion = pCalificacion;
    }
    
    public Long getCalificacion() {
       return calificacion;
    }



    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
}

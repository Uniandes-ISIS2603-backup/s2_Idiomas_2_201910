/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.dtos;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * PersonaDTO Objeto de transferencia de datos de Personaes. Los DTO
 * contienen las representaciones de los JSON que se transfieren entre el
 * cliente y el servidor.
 *
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "id": number,
 *      "nombre": string
 *      "contraseña" : string
 *   }
 * </pre> Por ejemplo una persona se representa asi:<br>
 *
 * <pre>
 *
 *   {
 *      "id": 1,
 *      "nombre": juan
 *      "contraseña" : 1234
 *   }
 *
 * </pre>
 *
 * @author j.barbosa
 */
public class PersonaDTO 
{
    Long id;
    String nombre;
    Long contraseña;
    
    
    /**
     * Constructor por defecto
     */
    public PersonaDTO() {
    }

    /**
     * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param personaEntity: Es la entidad que se va a convertir a DTO
     */
//    public PersonaDTO(PersonaEntity personaEntity) {
//        if (personaEntity != null) {
//            this.id = personaEntity.getId();
//            this.nombre = personaEntity.getName();
//        }
//    }

    /**
     * Devuelve el ID de la persona.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Modifica el ID de la persona.
     *
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Devuelve el nombre de la persona.
     *
     * @return the nombre
     */
    public String getName() {
        return nombre;
    }

    /**
     * Modifica el nombre de la persona.
     *
     * @param nombre the nombre to set
     */
    public void setName(String nombre) {
        this.nombre = nombre;
    }

//    /**
//     * Convertir DTO a Entity
//     *
//     * @return Un Entity con los valores del DTO
//     */
//    public PersonaEntity toEntity() {
//        PersonaEntity personaEntity = new PersonaEntity();
//        personaEntity.setId(this.id);
//        personaEntity.setName(this.nombre);
//        return personaEntity;
//    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}

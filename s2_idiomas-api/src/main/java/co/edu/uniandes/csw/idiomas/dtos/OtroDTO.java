/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.dtos;

import co.edu.uniandes.csw.idiomas.entities.OtroEntity;
import java.io.Serializable;

/**
 * OtroDTO Objeto de transferencia de datos de otro. Los DTO 
 * contienen las representaciones de los JSON que se transfieren entre el 
 * cliente y el servidor.
 * @author g.cubillosb
 */
public class OtroDTO extends ActividadDTO implements Serializable{
    
    // -------------------------------------------------------------------------
    // Atributos
    // -------------------------------------------------------------------------
    
    // -------------------------------------------------------------------------
    // Constructor
    // -------------------------------------------------------------------------
    
    /**
     * Constructor vacío
     */
    public OtroDTO ()
    {
        super();
    }
    
    /**
     * Convertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param pOtroEntity: Es la entidad que se va a convertir a DTO
     */
    public OtroDTO(OtroEntity pOtroEntity) 
    {
        super(pOtroEntity);
    }
    
    // -------------------------------------------------------------------------
    // Métodos
    // -------------------------------------------------------------------------
    
    /**
     * Convertir DTO a Entity
     *
     * @return Un Entity con los valores del DTO
     */
    @Override
    public OtroEntity toEntity() 
    {
        OtroEntity otroEntity = new OtroEntity();
        otroEntity.setId(this.getId());
        otroEntity.setNombre(this.getNombre());
        otroEntity.setDescripcion(this.getDescripcion());
        otroEntity.setMotivacion(this.getMotivacion());
        otroEntity.setFecha(this.getFecha());
        
        return otroEntity;
    }
}

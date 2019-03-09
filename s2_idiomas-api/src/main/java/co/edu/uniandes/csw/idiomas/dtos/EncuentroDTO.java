/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.dtos;

import co.edu.uniandes.csw.idiomas.entities.EncuentroEntity;
import java.io.Serializable;

/**
 * EncuentroDTO Objeto de transferencia de datos del encuentro. Los DTO 
 * contienen las representaciones de los JSON que se transfieren entre el 
 * cliente y el servidor.
 * @author g.cubillosb
 */
public class EncuentroDTO extends ActividadDTO implements Serializable{
    
    // -------------------------------------------------------------------------
    // Atributos
    // -------------------------------------------------------------------------
    
    /**
     * Atributo que representa un lugar
     */
    private String lugar;
    
    /**
     * Atributo que representa el número máximo de asistentes
     */
    private Integer numeroMaxAsistentes;
    
    /**
     * Atributo que representa si el encuentro fue aprobado
     */
    private Boolean aprobado;
    
    // -------------------------------------------------------------------------
    // Constructor
    // -------------------------------------------------------------------------
    
    /**
     * Constructor vacío
     */
    public EncuentroDTO () 
    {
        super();
    }
    
    /**
     * Convertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param pEncuentroEntity: Es la entidad que se va a convertir a DTO
     */
    public EncuentroDTO(EncuentroEntity pEncuentroEntity) 
    {
        super(pEncuentroEntity);
        if (pEncuentroEntity != null) 
        {
            this.aprobado = pEncuentroEntity.getAprobado();
            this.lugar = pEncuentroEntity.getLugar();
            this.numeroMaxAsistentes = pEncuentroEntity.getNumeroMaxAsistentes();
        }
    }
    
    // -------------------------------------------------------------------------
    // Métodos
    // -------------------------------------------------------------------------

    /**
     * @return the lugar
     */
    public String getLugar() {
        return lugar;
    }

    /**
     * @param lugar the lugar to set
     */
    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    /**
     * @return the numeroMaxAsistentes
     */
    public Integer getNumeroMaxAsistentes() {
        return numeroMaxAsistentes;
    }

    /**
     * @param numeroMaxAsistentes the numeroMaxAsistentes to set
     */
    public void setNumeroMaxAsistentes(Integer numeroMaxAsistentes) {
        this.numeroMaxAsistentes = numeroMaxAsistentes;
    }

    /**
     * @return the aprobado
     */
    public Boolean getAprobado() {
        return aprobado;
    }

    /**
     * @param aprobado the aprobado to set
     */
    public void setAprobado(Boolean aprobado) {
        this.aprobado = aprobado;
    }
    
    /**
     * Convertir DTO a Entity
     *
     * @return Un Entity con los valores del DTO
     */
    @Override
    public EncuentroEntity toEntity() 
    {
        EncuentroEntity encuentroEntity = new EncuentroEntity();
        encuentroEntity.setId(this.getId());
        encuentroEntity.setNombre(this.getNombre());
        encuentroEntity.setDescripcion(this.getDescripcion());
        encuentroEntity.setMotivacion(this.getMotivacion());
        encuentroEntity.setFecha(this.getFecha());
        encuentroEntity.setAprobado(this.getAprobado());
        encuentroEntity.setLugar(this.getLugar());
        encuentroEntity.setNumeroMaxAsistentes(this.getNumeroMaxAsistentes());
        
        return encuentroEntity;
    }
    
}

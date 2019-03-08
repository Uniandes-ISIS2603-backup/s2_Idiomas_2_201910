/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.dtos;

import co.edu.uniandes.csw.idiomas.entities.EstadiaEntity;
import java.io.Serializable;

/**
 * EstadiaDTO Objeto de transferencia de datos de la estadia. Los DTO contienen
 * las representaciones de los JSON que se transfieren entre el cliente y el
 * servidor.
 * @author g.cubillosb
 */
public class EstadiaDTO extends ActividadDTO implements Serializable{
    
    // -------------------------------------------------------------------
    // Atributos
    // -------------------------------------------------------------------
    
    /**
     * Atributo que indica el pais en el que se va a realizar la estadia.
     */
    private String pais;
    
    /**
     * Atributo que indica el anfitrion de la estadia.
     */
    private AnfitrionDTO anfitrion;
    
    //---------------------------------------------------------------------
    // Constructor
    // --------------------------------------------------------------------
    
    /**
     * Constructor vacio
     */
    public EstadiaDTO ()
    {
        super();
    }
    
    /**
     * Convertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param pEstadiaEntity: Es la entidad que se va a convertir a DTO
     */
    public EstadiaDTO(EstadiaEntity pEstadiaEntity) 
    {
        super(pEstadiaEntity);
        if (pEstadiaEntity != null) 
        {
            this.pais = pEstadiaEntity.getPais();
        }
    }
    
    // ---------------------------------------------------------------------
    // Métodos
    // ---------------------------------------------------------------------

    /**
     * @return the pais
     */
    public String getPais() {
        return pais;
    }

    /**
     * @param pais the pais to set
     */
    public void setPais(String pais) {
        this.pais = pais;
    }
    
    /**
     * Convertir DTO a Entity
     *
     * @return Un Entity con los valores del DTO
     */
    @Override
    public EstadiaEntity toEntity() 
    {
        EstadiaEntity estadiaEntity = new EstadiaEntity();
        estadiaEntity.setNombre(this.getNombre());
        estadiaEntity.setDescripcion(this.getDescripcion());
        estadiaEntity.setMotivacion(this.getMotivacion());
        estadiaEntity.setFecha(this.getFecha());
        estadiaEntity.setPais(this.getPais());
        
        return estadiaEntity;
    }

    /**
     * @return the anfitrion
     */
    public AnfitrionDTO getAnfitrion() {
        return anfitrion;
    }

    /**
     * @param anfitrion the anfitrion to set
     */
    public void setAnfitrion(AnfitrionDTO anfitrion) {
        this.anfitrion = anfitrion;
    }
    
}

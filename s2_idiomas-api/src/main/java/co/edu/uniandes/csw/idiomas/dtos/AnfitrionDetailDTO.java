/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.dtos;

import co.edu.uniandes.csw.idiomas.entities.AnfitrionEntity;
import co.edu.uniandes.csw.idiomas.entities.EstadiaEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author estudiante
 */
public class AnfitrionDetailDTO extends AnfitrionDTO implements Serializable
{
    private List<EstadiaDTO> estadias;
    
    public AnfitrionDetailDTO()
    {
        super();
    }
    
    public AnfitrionDetailDTO(AnfitrionEntity anfitrionEntity)
    {
        super(anfitrionEntity);
        if (anfitrionEntity != null)
        {
            estadias = new ArrayList<>();
            for (EstadiaEntity  entityEstadias : anfitrionEntity.getEstadias()) {
                //falta arreglar estadia DTO
//                estadias.add(new EstadiaDTO(entityEstadias));
            }
                    
        }
    }

    public List<EstadiaDTO> getEstadias() 
    {
        return estadias;
    }

    public void setEstadias(List<EstadiaDTO> estadias) 
    {
        this.estadias = estadias;
    }
    
     /**
     * Convierte un objeto AuthorDetailDTO a AnfitrionEntity incluyendo los
     * atributos de AuthorDTO.
     *
     * @return Nueva objeto AnfitrionEntity.
     *
     */
    @Override
    public AnfitrionEntity toEntity() {
        AnfitrionEntity authorEntity = super.toEntity();
        if (estadias != null) {
            List<EstadiaEntity> estadiasEntity = new ArrayList<>();
            for (EstadiaDTO dtoEstadia : estadias) {
                
                //falta arreglar ActividadDtio
//                estadiasEntity.add(dtoEstadia.toEntity());
            }
            authorEntity.setEstadias(estadiasEntity);
        }
        
        return authorEntity;
    }
}

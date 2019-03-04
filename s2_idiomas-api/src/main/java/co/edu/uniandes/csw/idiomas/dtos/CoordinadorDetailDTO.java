/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.dtos;

import co.edu.uniandes.csw.idiomas.entities.ActividadEntity;
import co.edu.uniandes.csw.idiomas.entities.CoordinadorEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author estudiante
 */
public class CoordinadorDetailDTO extends CoordinadorDTO implements Serializable
{
    private List<ActividadDTO> activiadesCoordinadas;
    
    public CoordinadorDetailDTO()
    {
        super();
    }
    
    public CoordinadorDetailDTO(CoordinadorEntity coordinadorEntity)
    {
        super(coordinadorEntity);
        if (coordinadorEntity != null) {
            activiadesCoordinadas = new ArrayList<>();
            for (ActividadEntity entityActividades : coordinadorEntity.getActividadesCoordinadas() ){
//                esto ya es repetitivo XD
//                activiadesCoordinadas.add(new ActividadDTO(entityActividades));
            }            
        }
    }

    public List<ActividadDTO> getActiviadesCoordinadas() 
    {
        return activiadesCoordinadas;
    }

    public void setActiviadesCoordinadas(List<ActividadDTO> activiadesCoordinadas) 
    {
        this.activiadesCoordinadas = activiadesCoordinadas;
    }
    
     /**
     * Convierte un objeto CoordinadorDetailDTO a CoordinadorEntity incluyendo los
     * atributos de CoordinadorDTO.
     *
     * @return Nueva objeto CoordinadorEntity.
     *
     */
    @Override
    public CoordinadorEntity toEntity() {
        CoordinadorEntity authorEntity = super.toEntity();
        if (activiadesCoordinadas != null) {
            List<ActividadEntity> actividadesEntity = new ArrayList<>();
            for (ActividadDTO dtoActividad : activiadesCoordinadas) {
//                lo mismo XD
//                actividadesEntity.add(dtoActividad.toEntity());
            }
            authorEntity.setActividadesCoordinadas(actividadesEntity);
        }
        
        return authorEntity;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.dtos;

import co.edu.uniandes.csw.idiomas.entities.ActividadEntity;
import co.edu.uniandes.csw.idiomas.entities.UsuarioEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author estudiante
 */
public class UsuarioDetailDTO extends UsuarioDTO implements Serializable
{
   private List<ActividadDTO> activiadesProgramadas;
    
    public UsuarioDetailDTO()
    {
        super();
    }
    
    public UsuarioDetailDTO(UsuarioEntity usuarioEntity)
    {
        super(usuarioEntity);
        if (usuarioEntity != null) {
            activiadesProgramadas = new ArrayList<>();
            for (ActividadEntity entityActividades : usuarioEntity.getActividades()) {
//                actividad DTO necesita ser arreglada
//                activiadesProgramadas.add(new ActividadDTO(entityActividades));
//                falta la otra relacion
            }
            
        }
    }

    public List<ActividadDTO> getActiviadesProgramadas() 
    {
        return activiadesProgramadas;
    }

    public void setActiviadesProgramadas(List<ActividadDTO> activiadesProgramadas) 
    {
        this.activiadesProgramadas = activiadesProgramadas;
    }
     /**
     * Convierte un objeto AuthorDetailDTO a UsuarioEntity incluyendo los
     * atributos de AuthorDTO.
     *
     * @return Nueva objeto UsuarioEntity.
     *
     */
    @Override
    public UsuarioEntity toEntity() {
        UsuarioEntity authorEntity = super.toEntity();
        if (activiadesProgramadas != null) {
            List<ActividadEntity> activiadesProgramadasEntity = new ArrayList<>();
            for (ActividadDTO dtoActividad : activiadesProgramadas) {
//                falta acomodar actividad
//                activiadesProgramadasEntity.add(dtoActividad.toEntity());
            }
            authorEntity.setActividades(activiadesProgramadasEntity);
        }        
        return authorEntity;
    }
    
}

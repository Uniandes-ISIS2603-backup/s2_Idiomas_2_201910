package co.edu.uniandes.csw.idiomas.dtos;

import co.edu.uniandes.csw.idiomas.entities.ActividadEntity;
import co.edu.uniandes.csw.idiomas.entities.GrupoDeInteresEntity;
import co.edu.uniandes.csw.idiomas.entities.UsuarioEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author j.barbosa
 */
public class UsuarioDetailDTO extends UsuarioDTO implements Serializable
{
    private List<ActividadDTO> activiadesProgramadas;
    private List<GrupoDeInteresDTO> gruposAlosQuePertenece;
    
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
            gruposAlosQuePertenece = new ArrayList<>();
            for(GrupoDeInteresEntity entityGrupo : usuarioEntity.getGrupos()){
                
                
            }
            
        }
    }

    public List<GrupoDeInteresDTO> getGruposAlosQuePertenece() {
        return gruposAlosQuePertenece;
    }

    public void setGruposAlosQuePertenece(List<GrupoDeInteresDTO> gruposAlosQuePertenece) {
        this.gruposAlosQuePertenece = gruposAlosQuePertenece;
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
        if (gruposAlosQuePertenece != null) {
            List<GrupoDeInteresEntity> grupos = new ArrayList<>();
            for (GrupoDeInteresDTO dtoGrupo : gruposAlosQuePertenece) {
//                falta acomodar actividad
//                grupos.add(dtoActividad.toEntity());
            }
            authorEntity.setGrupos(grupos);
        }
        return authorEntity;
    }
    
}

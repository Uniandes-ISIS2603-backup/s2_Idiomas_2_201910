/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.dtos;

import co.edu.uniandes.csw.idiomas.entities.EstadiaEntity;
import co.edu.uniandes.csw.idiomas.entities.UsuarioEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que extiende de {@link EstadiaDTO} para manejar las relaciones entre
 * EstadiaDTO y otros DTOs. Para el contenido de una estadia ir a la
 * documentación de {@link EstadiaDTO}
 * @author g.cubillosb
 */
public class EstadiaDetailDTO extends EstadiaDTO implements Serializable {
    
    // ------------------------------------------------------------------------
    // Atributos
    // ------------------------------------------------------------------------
    
    /**
     * Lista de tipo UsuarioDTO contiene los usuarios que están asociados con
     * esta estadia.
     */
    private List<UsuarioDTO> usuario;
    
    // ------------------------------------------------------------------------
    // Constructor
    // ------------------------------------------------------------------------
    
    /**
     * Constructor vacío
     */
    public EstadiaDetailDTO () 
    {
        super();
    }
    
    /**
     * Crea un objeto EstadiaDetailDTO a partir de un objeto EstadiaEntity
     * incluyendo los atributos de EstadiaDTO.
     *
     * @param pEstadiaEntity Entidad EstadiaEntity desde la cual se va a crear el
     * nuevo objeto.
     *
     */
    public EstadiaDetailDTO(EstadiaEntity pEstadiaEntity) {
        super(pEstadiaEntity);
        if (pEstadiaEntity != null) 
        {
            usuario = new ArrayList<>();
            for (UsuarioEntity entityUsuario : pEstadiaEntity.getAsistentes()) {
                usuario.add(new UsuarioDTO(entityUsuario));
            }
        }
    }
    
    // ------------------------------------------------------------------------
    // Métodos
    // ------------------------------------------------------------------------

    /**
     * Convierte un objeto EstadiaDetailDTO a EstadiaEntity incluyendo los
     * atributos de EstadiaDTO.
     *
     * @return Nueva objeto EstadiaEntity.
     *
     */
    @Override
    public EstadiaEntity toEntity() {
        EstadiaEntity estadiaEntity = super.toEntity();
        if (getUsuario() != null) {
            List<UsuarioEntity> usuarioEntity = new ArrayList<>();
            for (UsuarioDTO dtoUsuario : getUsuario()) {
                usuarioEntity.add(dtoUsuario.toEntity());
            }
            estadiaEntity.setAsistentes(usuarioEntity);
        }
        return estadiaEntity;
    }

    /**
     * @return the usuario
     */
    public List<UsuarioDTO> getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(List<UsuarioDTO> usuario) {
        this.usuario = usuario;
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.dtos;

import java.util.List;

/**
 * 
 * @author g.cubillosb
 */
public class EstadiaDetailDTO extends EstadiaDTO {
 
    // ------------------------------------------------------------------------
    // Atributos
    // ------------------------------------------------------------------------
    
    /**
     * Lista de tipo UsuarioDTO que contiene los usuarios asociados con esta
     * estadia
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
        
    }

    // ------------------------------------------------------------------------
    // Métodos
    // ------------------------------------------------------------------------
    
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

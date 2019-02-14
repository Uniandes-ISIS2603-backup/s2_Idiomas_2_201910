/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.dtos;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Santiago Gamboa
 */
public class ComentarioDetailDTO extends ComentarioDTO implements Serializable{
    
    
    private List<ComentarioDTO> comentarios;
    
    public ComentarioDetailDTO()
    {
        
    }
}

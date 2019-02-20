/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.dtos;

/**
 * ChatDTO Objeto de transferencia de datos del chat. Los DTO contienen
 * las representaciones de los JSON que se transfieren entre el cliente y el
 * servidor.
 * @author g.cubillosb
 */
public class ChatDTO extends ActividadDTO{
    
    // -------------------------------------------------------------------
    // Atributos
    // -------------------------------------------------------------------
    
    /**
     * Atributo que indica el medio del chat
     */
    private String medio;
    
    //---------------------------------------------------------------------
    // Constructor
    // --------------------------------------------------------------------
    
    /**
     * Constructor vacio
     */
    public ChatDTO ()
    {
        
    }
    
    // --------------------------------------------------------------------
    // Métodos
    // --------------------------------------------------------------------

    /**
     * @return the medio
     */
    public String getMedio() {
        return medio;
    }

    /**
     * @param medio the medio to set
     */
    public void setMedio(String medio) {
        this.medio = medio;
    }
    
}

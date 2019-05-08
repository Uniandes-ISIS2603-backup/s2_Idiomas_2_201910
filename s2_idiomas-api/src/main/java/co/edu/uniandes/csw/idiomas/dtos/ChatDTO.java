/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.dtos;

import co.edu.uniandes.csw.idiomas.entities.ChatEntity;
import java.io.Serializable;

/**
 * ChatDTO Objeto de transferencia de datos del chat. Los DTO contienen
 * las representaciones de los JSON que se transfieren entre el cliente y el
 * servidor.
 * @author g.cubillosb
 */
public class ChatDTO extends ActividadDTO implements Serializable{
    
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
        super();
    }
    
    /**
     * Convertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param pChatEntity: Es la entidad que se va a convertir a DTO
     */
    public ChatDTO(ChatEntity pChatEntity) 
    {
        if (pChatEntity != null) 
        {
            this.id = pChatEntity.getId();
            this.descripcion = pChatEntity.getDescripcion();
            this.fecha = pChatEntity.getFecha();
            this.motivacion = pChatEntity.getMotivacion();
            this.nombre = pChatEntity.getNombre();
            this.pTipo = pChatEntity.getSubTypeId();
            this.medio = pChatEntity.getMedio();
        }
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
    
    /**
     * Convertir DTO a Entity
     *
     * @return Un Entity con los valores del DTO
     */
    @Override
    public ChatEntity toEntity() 
    {
        ChatEntity chatEntity = new ChatEntity();
        chatEntity.setId(this.getId());
        chatEntity.setNombre(this.getNombre());
        chatEntity.setDescripcion(this.getDescripcion());
        chatEntity.setMotivacion(this.getMotivacion());
        chatEntity.setFecha(this.getFecha());
        chatEntity.setMedio(this.getMedio());
        
        return chatEntity;
    }
    
}

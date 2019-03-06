/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.dtos;

import co.edu.uniandes.csw.idiomas.entities.ComentarioActividadEntity;
import co.edu.uniandes.csw.idiomas.entities.ComentarioEntity;
import java.io.Serializable;

/**
 *
 * @author se.gamboa
 */
public class ComentarioActividadDTO extends ComentarioDTO implements Serializable{
    
    private String titulo;
    
    /**
     * Constructor de ComentarioActividadDTO
     * @param entity recibe la informacion del comentario.
     * @param entity2 recibe la informacion de un comentario general.
     */
    public ComentarioActividadDTO(ComentarioActividadEntity entity, ComentarioEntity entity2)
    {
        super(entity2);
        if (entity != null) {
            this.titulo = entity.getTitulo();
        }
    }
    
    public ComentarioActividadDTO(){
        
    }

    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    /**
     * Convierte un objeto DTO a una Entidad.
     * @return entidad convertida.
     */
    public ComentarioActividadEntity toEntityA() {
        ComentarioActividadEntity entity = new ComentarioActividadEntity();
        entity.setTitulo(this.titulo);
        return entity;
    }
}

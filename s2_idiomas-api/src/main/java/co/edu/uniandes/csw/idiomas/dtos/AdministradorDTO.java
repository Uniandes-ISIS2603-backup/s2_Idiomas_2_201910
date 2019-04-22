package co.edu.uniandes.csw.idiomas.dtos;

import co.edu.uniandes.csw.idiomas.entities.AdministradorEntity;
import java.io.Serializable;

/**
 * AdministradorDTO Objeto de transferencia de datos de Administradores. Los DTO
 * contienen las representaciones de los JSON que se transfieren entre el
 * cliente y el servidor.
 *
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "id": number,
 *      "nombre": string
 *      "contrasenia" : string
 *   }
 * </pre> Por ejemplo una administrador se representa asi:<br>
 *
 * <pre>
 *
 *   {
 *      "id": 1,
 *      "nombre": juan
 *      "contrasenia" : 1234
 *   }
 *
 * </pre>
 *
 * @author j.barbosa
 */
public class AdministradorDTO implements Serializable
{
    /**
     * el id del administrador
     */
    Long id;
    /**
     * el nombre del administrador
     */
    String nombre;
    /**
     * contraseña del administrador
     */
    Long contrasenia;


    
    
    /**
     * Constructor por defecto
     */
    public AdministradorDTO() 
    {
        //constructor por defecto
    }

    /**
     * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param administradorEntity: Es la entidad que se va a convertir a DTO
     */
    public AdministradorDTO(AdministradorEntity administradorEntity) {
        if (administradorEntity != null) {
            this.id = administradorEntity.getId();
            this.nombre = administradorEntity.getNombre();
            this.contrasenia = administradorEntity.getContrasenia();
        }
    }

    /**
     * Devuelve el ID de la administrador.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Modifica el ID de la administrador.
     *
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Devuelve el nombre de la administrador.
     *
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Modifica el nombre de la administrador.
     *
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
     /**
     * Devuelve el contrasenia de la administrador.
     *
     * @return the contrasenia
     */
    public Long getContrasenia() {
        return contrasenia;
    }
    
    /**
     * 
     * Modifica la conrasenia del administrador.
     *
     *   
     * @param contrasenia 
     */
    public void setContrasenia(Long contrasenia) {
        this.contrasenia = contrasenia;
    }

    /**
     * Convertir DTO a Entity
     *
     * @return Un Entity con los valores del DTO
     */
    public AdministradorEntity toEntity() {
        AdministradorEntity administradorEntity = new AdministradorEntity();
        administradorEntity.setId(this.id);
        administradorEntity.setNombre(this.nombre);
        administradorEntity.setContrasenia(this.contrasenia);
        return administradorEntity;
    }

    @Override
    public String toString()
    {
        return "{id:"+id.toString()+","+"nombre:"+nombre+","+"contrasenia"+contrasenia.toString()+"}";
    }
}

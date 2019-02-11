/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.dtos;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

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
 *      "contraseña" : string
 *   }
 * </pre> Por ejemplo una administrador se representa asi:<br>
 *
 * <pre>
 *
 *   {
 *      "id": 1,
 *      "nombre": juan
 *      "contraseña" : 1234
 *   }
 *
 * </pre>
 *
 * @author j.barbosa
 */
public class CoordinadorDTO {
    
}

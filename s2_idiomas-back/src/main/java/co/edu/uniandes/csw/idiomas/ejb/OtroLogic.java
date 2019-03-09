/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.ejb;

import co.edu.uniandes.csw.idiomas.entities.OtroEntity;
import co.edu.uniandes.csw.idiomas.entities.ComentarioActividadEntity;
import co.edu.uniandes.csw.idiomas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.idiomas.persistence.OtroPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Clase que implementa la conexion con la persistencia para la entidad de
 * Otro.
 *
 * @author g.cubillosb
 */
@Stateless
public class OtroLogic {
    
    // -------------------------------------------------------------------------
    // Atributos
    // -------------------------------------------------------------------------
    
    /**
     * Logger para las acciones de la clase.
     */
    private static final Logger LOGGER = Logger.getLogger(OtroLogic.class.getName());

    /**
     * Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.
     */
    @Inject
    private OtroPersistence persistence; 

    // -------------------------------------------------------------------------
    // Métodos
    // -------------------------------------------------------------------------
    
    /**
     * Crea una otro en la persistencia.
     *
     * @param otroEntity La entidad que representa la otro a
     * persistir.
     * @return La entiddad de la otro luego de persistirla.
     * @throws BusinessLogicException Si la otro a persistir ya existe.
     */
    public OtroEntity createOtro(OtroEntity otroEntity) throws BusinessLogicException 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de creación de la otro");
        
        // Verifica la regla de negocio que dice que el nombre del otro no puede ser vacío.
        if (!validateName(otroEntity.getNombre()))
        {
            throw new BusinessLogicException("El nombre es inválido.");
        }
        
        // Verifica la regla de negocio que dice que una otro debe tener un coordinador.
        // TODO: GC Conectar con Coordinador.
//        if (otroEntity.getCoordinadores().isEmpty())
//        {
//            throw new BusinessLogicException("La otro debe tener un coordinador.");
//        }

        // Verifica la regla de negocio que dice que una otro no puede ser idéntica a otra otro.
        if (persistence.findByName(otroEntity.getNombre()) != null &&
                persistence.findByName(otroEntity.getNombre()).equals(otroEntity))
        {
            throw new BusinessLogicException("La otro ya existe.");
        }
        
        // Invoca la persistencia para crear la otro
        persistence.create(otroEntity);
        LOGGER.log(Level.INFO, "Termina proceso de creación de la otro");
        return otroEntity;
    }

    /**
     *
     * Obtener todas las otros existentes en la base de datos.
     *
     * @return una lista de otros.
     */
    public List<OtroEntity> getOtros() 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todas las otros");
        // Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
        List<OtroEntity> otros = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todas las otros");
        return otros;
    }

    /**
     *
     * Obtener una otro por medio de su id.
     *
     * @param otrosId: id de la otro para ser buscada.
     * @return la otro solicitada por medio de su id.
     */
    public OtroEntity getOtro(Long otrosId) 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la otro con id = {0}", otrosId);
        // Note que, por medio de la inyección de dependencias se llama al método "find(id)" que se encuentra en la persistencia.
        OtroEntity otroEntity = persistence.find(otrosId);
        if (otroEntity == null) {
            LOGGER.log(Level.SEVERE, "La otro con el id = {0} no existe", otrosId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar la otro con id = {0}", otrosId);
        return otroEntity;
    }

    /**
     *
     * Actualizar una otro.
     *
     * @param pOtrosId: id de la otro para buscarla en la base de
     * datos.
     * @param otroEntity: otro con los cambios para ser actualizada,
     * por ejemplo el nombre.
     * @return la otro con los cambios actualizados en la base de datos.
     */
    public OtroEntity updateOtro(Long  pOtrosId, OtroEntity otroEntity) throws BusinessLogicException 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar la otro con id = {0}",  pOtrosId);
        
        // Verifica la regla de negocio que dice que el nombre del otro no puede ser vacío.
        if (!validateName(otroEntity.getNombre()))
        {
            throw new BusinessLogicException("El nombre es inválido.");
        }
        
        // Verifica la regla de negocio que dice que una otro debe tener un coordinador.
        // TODO: GC Conectar con Coordinador.
//        if (otroEntity.getCoordinadores().isEmpty())
//        {
//            throw new BusinessLogicException("La otro debe tener un coordinador.");
//        }

        // Verifica la regla de negocio que dice que una otro no puede ser idéntica a otra otro.
        if (persistence.findByName(otroEntity.getNombre()) != null &&
                persistence.findByName(otroEntity.getNombre()).equals(otroEntity))
        {
            throw new BusinessLogicException("La otro ya existe.");
        }
        // Note que, por medio de la inyección de dependencias se llama al método "update(entity)" que se encuentra en la persistencia.
        OtroEntity newEntity = persistence.update(otroEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar la otro con id = {0}", otroEntity.getId());
        return newEntity;
    }

    /**
     * Borrar un otro
     *
     * @param pOtrosId: id de la otro a borrar
     * @throws BusinessLogicException Si la otro a eliminar tiene libros.
     */
    public void deleteOtro(Long pOtrosId) throws BusinessLogicException 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar la otro con id = {0}", pOtrosId);
        // Note que, por medio de la inyección de dependencias se llama al método "delete(id)" que se encuentra en la persistencia.
        List<ComentarioActividadEntity> comentarios = getOtro(pOtrosId).getComentarios();
        if (comentarios != null && !comentarios.isEmpty()) {
            throw new BusinessLogicException("No se puede borrar la otro con id = " + pOtrosId + " porque tiene comentarios asociados");
        }
        persistence.delete(pOtrosId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar la otro con id = {0}", pOtrosId);
    }
    
    /**
     * Verifica que el nombre no sea invalido.
     *
     * @param pNombre a verificar
     * @return true si el nombre es valido.
     */
    private boolean validateName(String pNombre) 
    {
        return !(pNombre == null || pNombre.isEmpty());
    }
    

}

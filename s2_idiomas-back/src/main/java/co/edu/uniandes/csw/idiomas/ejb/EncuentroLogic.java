/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.ejb;

import co.edu.uniandes.csw.idiomas.entities.EncuentroEntity;
import co.edu.uniandes.csw.idiomas.entities.ComentarioActividadEntity;
import co.edu.uniandes.csw.idiomas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.idiomas.persistence.EncuentroPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Clase que implementa la conexion con la persistencia para la entidad de
 * Encuentro.
 *
 * @author g.cubillosb
 */
@Stateless
public class EncuentroLogic {
    
    // -------------------------------------------------------------------------
    // Atributos
    // -------------------------------------------------------------------------
    
    /**
     * Logger para las acciones de la clase.
     */
    private static final Logger LOGGER = Logger.getLogger(EncuentroLogic.class.getName());

    /**
     * Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.
     */
    @Inject
    private EncuentroPersistence persistence; 

    // -------------------------------------------------------------------------
    // Métodos
    // -------------------------------------------------------------------------
    
    /**
     * Crea una encuentro en la persistencia.
     *
     * @param encuentroEntity La entidad que representa la encuentro a
     * persistir.
     * @return La entiddad de la encuentro luego de persistirla.
     * @throws BusinessLogicException Si la encuentro a persistir ya existe.
     */
    public EncuentroEntity createEncuentro(EncuentroEntity encuentroEntity) throws BusinessLogicException 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de creación de la encuentro");
        
        // Verifica la regla de negocio que dice que el nombre del encuentro no puede ser vacío.
        if (!validateName(encuentroEntity.getNombre()))
        {
            throw new BusinessLogicException("El nombre es inválido.");
        }
        
        // Verifica la regla de negocio que dice que una encuentro debe tener un coordinador.
//        if (encuentroEntity.getCoordinadores().isEmpty())
//        {
//            throw new BusinessLogicException("La encuentro debe tener un coordinador.");
//        }

        // Verifica la regla de negocio que dice que una encuentro no puede ser idéntica a otra encuentro.
        if (persistence.findByName(encuentroEntity.getNombre())!= null &&
                persistence.findByName(encuentroEntity.getNombre()).equals(encuentroEntity))
        {
            throw new BusinessLogicException("La encuentro ya existe.");
        }
        
        // Verifica la regla de negocio que dice que el lugar del encuentro no puede ser vacío.
        if (!validateName(encuentroEntity.getLugar()))
        {
            throw new BusinessLogicException("El lugar es inválido: " + encuentroEntity.getLugar());
        }
        
        // Verifica la regla de negocio que dice que el encuentro debe tener un 
        //número máximo de asistentes definido. Este número es un entero positivo mayor que cero.
        if (!validateNumber(encuentroEntity.getNumeroMaxAsistentes()))
        {
            throw new BusinessLogicException("El número de asistentes es inválido.");
        }
        
        // Invoca la persistencia para crear la encuentro
        persistence.create(encuentroEntity);
        LOGGER.log(Level.INFO, "Termina proceso de creación de la encuentro");
        return encuentroEntity;
    }

    /**
     *
     * Obtener todas las encuentros existentes en la base de datos.
     *
     * @return una lista de encuentros.
     */
    public List<EncuentroEntity> getEncuentros() 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todas las encuentros");
        // Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
        List<EncuentroEntity> encuentros = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todas las encuentros");
        return encuentros;
    }

    /**
     *
     * Obtener una encuentro por medio de su id.
     *
     * @param encuentrosId: id de la encuentro para ser buscada.
     * @return la encuentro solicitada por medio de su id.
     */
    public EncuentroEntity getEncuentro(Long encuentrosId) 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la encuentro con id = {0}", encuentrosId);
        // Note que, por medio de la inyección de dependencias se llama al método "find(id)" que se encuentra en la persistencia.
        EncuentroEntity encuentroEntity = persistence.find(encuentrosId);
        if (encuentroEntity == null) {
            LOGGER.log(Level.SEVERE, "La encuentro con el id = {0} no existe", encuentrosId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar la encuentro con id = {0}", encuentrosId);
        return encuentroEntity;
    }

    /**
     *
     * Actualizar una encuentro.
     *
     * @param pEncuentrosId: id de la encuentro para buscarla en la base de
     * datos.
     * @param encuentroEntity: encuentro con los cambios para ser actualizada,
     * por ejemplo el nombre.
     * @return la encuentro con los cambios actualizados en la base de datos.
     */
    public EncuentroEntity updateEncuentro(Long  pEncuentrosId, EncuentroEntity encuentroEntity) throws BusinessLogicException 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar la encuentro con id = {0}",  pEncuentrosId);
        
        // Verifica la regla de negocio que dice que el nombre del encuentro no puede ser vacío.
        if (!validateName(encuentroEntity.getNombre()))
        {
            throw new BusinessLogicException("El nombre es inválido.");
        }
        
        // Verifica la regla de negocio que dice que una encuentro debe tener un coordinador.
//        if (encuentroEntity.getCoordinadores().isEmpty())
//        {
//            throw new BusinessLogicException("La encuentro debe tener un coordinador.");
//        }

        // Verifica la regla de negocio que dice que una encuentro no puede ser idéntica a otra encuentro.
        if (persistence.findByName(encuentroEntity.getNombre())!= null &&
                persistence.findByName(encuentroEntity.getNombre()).equals(encuentroEntity))
        {
            throw new BusinessLogicException("La encuentro ya existe.");
        }
        
        // Verifica la regla de negocio que dice que el lugar del encuentro no puede ser vacío.
        if (!validateName(encuentroEntity.getLugar()))
        {
            throw new BusinessLogicException("El lugar es inválido.");
        }
        
        // Verifica la regla de negocio que dice que el encuentro debe tener un 
        //número máximo de asistentes definido. Este número es un entero positivo mayor que cero.
        if (!validateNumber(encuentroEntity.getNumeroMaxAsistentes()))
        {
            throw new BusinessLogicException("El número de asistentes es inválido.");
        }
        
        // Note que, por medio de la inyección de dependencias se llama al método "update(entity)" que se encuentra en la persistencia.
        EncuentroEntity newEntity = persistence.update(encuentroEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar la encuentro con id = {0}", encuentroEntity.getId());
        return newEntity;
    }

    /**
     * Borrar un encuentro
     *
     * @param pEncuentrosId: id de la encuentro a borrar
     * @throws BusinessLogicException Si la encuentro a eliminar tiene libros.
     */
    public void deleteEncuentro(Long pEncuentrosId) throws BusinessLogicException 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar la encuentro con id = {0}", pEncuentrosId);
        // Note que, por medio de la inyección de dependencias se llama al método "delete(id)" que se encuentra en la persistencia.
        List<ComentarioActividadEntity> comentarios = getEncuentro(pEncuentrosId).getComentarios();
        if (comentarios != null && !comentarios.isEmpty()) {
            throw new BusinessLogicException("No se puede borrar la encuentro con id = " + pEncuentrosId + " porque tiene comentarios asociados");
        }
        persistence.delete(pEncuentrosId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar la encuentro con id = {0}", pEncuentrosId);
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
    
    /**
     * Verifica que el nombre no sea invalido.
     *
     * @param pNumero a verificar
     * @return true si el nombre es valido.
     */
    private boolean validateNumber(Integer pNumero) 
    {
        return !(pNumero == null || pNumero <= 0);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.ejb;

import co.edu.uniandes.csw.idiomas.entities.EstadiaEntity;
import co.edu.uniandes.csw.idiomas.entities.ComentarioActividadEntity;
import co.edu.uniandes.csw.idiomas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.idiomas.persistence.EstadiaPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Clase que implementa la conexion con la persistencia para la entidad de
 * Estadia.
 *
 * @author g.cubillosb
 */
@Stateless
public class EstadiaLogic {
    
    // -------------------------------------------------------------------------
    // Atributos
    // -------------------------------------------------------------------------
    
    /**
     * Logger para las acciones de la clase.
     */
    private static final Logger LOGGER = Logger.getLogger(EstadiaLogic.class.getName());

    /**
     * Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.
     */
    @Inject
    private EstadiaPersistence persistence; 

    // -------------------------------------------------------------------------
    // Métodos
    // -------------------------------------------------------------------------
    
    /**
     * Crea una estadia en la persistencia.
     *
     * @param estadiaEntity La entidad que representa la estadia a
     * persistir.
     * @return La entiddad de la estadia luego de persistirla.
     * @throws BusinessLogicException Si la estadia a persistir ya existe.
     */
    public EstadiaEntity createEstadia(EstadiaEntity estadiaEntity) throws BusinessLogicException 
    {
        if(estadiaEntity != null)
        {
        LOGGER.log(Level.INFO, "Inicia proceso de creación de la estadia");
        
        // Verifica la regla de negocio que dice que el nombre del estadia no puede ser vacío.
        if (!validateName(estadiaEntity.getNombre()))
        {
            throw new BusinessLogicException("El nombre es inválido.");
        }
        
        // Verifica la regla de negocio que dice que una estadia debe tener un coordinador.
        // TODO: GC Conectar con Coordinador.
//        if (estadiaEntity.getCoordinadores().isEmpty())
//        {
//            throw new BusinessLogicException("La estadia debe tener un coordinador.");
//        }

        // Verifica la regla de negocio que dice que una estadia no puede ser idéntica a otra estadia.
        if (persistence.findByName(estadiaEntity.getNombre()) != null &&
                persistence.findByName(estadiaEntity.getNombre()).equals(estadiaEntity))
        {
            throw new BusinessLogicException("La estadia ya existe.");
        }
        
        // Verifica la regla de negocio que dice que el pais del estadia no puede ser vacío.
        if (!validateName(estadiaEntity.getPais()))
        {
            throw new BusinessLogicException("El lugar es inválido: ");
        }
        
        // Verifica la regla de negocio que dice que una estadia debe tener un anfitrion.
        // TODO: GC Conectar con Anfitrion.
//        if (estadiaEntity.getAnfitrion().isEmpty())
//        {
//            throw new BusinessLogicException("La estadia debe tener un anfitrion.");
//        }
        
        // Verifica la regla de negocio que dice que una estadia no puede tener el mismo id.
        if (persistence.find(estadiaEntity.getId()) != null)
        {
            throw new BusinessLogicException("Ya existe una estadia con ese id: " +
                    estadiaEntity.getId());
        }
        }
        
        // Invoca la persistencia para crear la estadia
        persistence.create(estadiaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de creación de la estadia");
        return estadiaEntity;
    
    }

    /**
     *
     * Obtener todas las estadias existentes en la base de datos.
     *
     * @return una lista de estadias.
     */
    public List<EstadiaEntity> getEstadias() 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todas las estadias");
        // Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
        List<EstadiaEntity> estadias = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todas las estadias");
        return estadias;
    }

    /**
     *
     * Obtener una estadia por medio de su id.
     *
     * @param estadiasId: id de la estadia para ser buscada.
     * @return la estadia solicitada por medio de su id.
     */
    public EstadiaEntity getEstadia(Long estadiasId) 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la estadia con id = {0}", estadiasId);
        // Note que, por medio de la inyección de dependencias se llama al método "find(id)" que se encuentra en la persistencia.
        EstadiaEntity estadiaEntity = persistence.find(estadiasId);
        if (estadiaEntity == null) {
            LOGGER.log(Level.SEVERE, "La estadia con el id = {0} no existe", estadiasId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar la estadia con id = {0}", estadiasId);
        return estadiaEntity;
    }

    /**
     *
     * Actualizar una estadia.
     *
     * @param pEstadiasId: id de la estadia para buscarla en la base de
     * datos.
     * @param estadiaEntity: estadia con los cambios para ser actualizada,
     * por ejemplo el nombre.
     * @return la estadia con los cambios actualizados en la base de datos.
     */
    public EstadiaEntity updateEstadia(Long  pEstadiasId, EstadiaEntity estadiaEntity) throws BusinessLogicException 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar la estadia con id = {0}",  pEstadiasId);
        
        // Verifica la regla de negocio que dice que el nombre del estadia no puede ser vacío.
        if (!validateName(estadiaEntity.getNombre()))
        {
            throw new BusinessLogicException("El nombre es inválido.");
        }
        
        // Verifica la regla de negocio que dice que una estadia debe tener un coordinador.
        // TODO: GC Conectar con Coordinador.
//        if (estadiaEntity.getCoordinadores().isEmpty())
//        {
//            throw new BusinessLogicException("La estadia debe tener un coordinador.");
//        }

        // Verifica la regla de negocio que dice que una estadia no puede ser idéntica a otra estadia.
        if (persistence.findByName(estadiaEntity.getNombre())!= null &&
                persistence.findByName(estadiaEntity.getNombre()).equals(estadiaEntity))
        {
            throw new BusinessLogicException("La estadia ya existe.");
        }
        
        // Verifica la regla de negocio que dice que el pais del estadia no puede ser vacío.
        if (!validateName(estadiaEntity.getPais()))
        {
            throw new BusinessLogicException("El lugar es inválido: ");
        }
        
        // Verifica la regla de negocio que dice que una estadia debe tener un anfitrion.
        // TODO: GC Conectar con Anfitrion.
//        if (estadiaEntity.getAnfitrion().isEmpty())
//        {
//            throw new BusinessLogicException("La estadia debe tener un anfitrion.");
//        }
        
        // Note que, por medio de la inyección de dependencias se llama al método "update(entity)" que se encuentra en la persistencia.
        EstadiaEntity newEntity = persistence.update(estadiaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar la estadia con id = {0}", estadiaEntity.getId());
        return newEntity;
    }

    /**
     * Borrar un estadia
     *
     * @param pEstadiasId: id de la estadia a borrar
     * @throws BusinessLogicException Si la estadia a eliminar tiene libros.
     */
    public void deleteEstadia(Long pEstadiasId) throws BusinessLogicException 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar la estadia con id = {0}", pEstadiasId);
        // Note que, por medio de la inyección de dependencias se llama al método "delete(id)" que se encuentra en la persistencia.
        List<ComentarioActividadEntity> comentarios = getEstadia(pEstadiasId).getComentarios();
        if (comentarios != null && !comentarios.isEmpty()) {
            throw new BusinessLogicException("No se puede borrar la estadia con id = " + pEstadiasId + " porque tiene comentarios asociados");
        }
        persistence.delete(pEstadiasId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar la estadia con id = {0}", pEstadiasId);
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

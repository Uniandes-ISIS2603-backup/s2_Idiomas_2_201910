/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.persistence;

import co.edu.uniandes.csw.idiomas.entities.AdministradorEntity;
import co.edu.uniandes.csw.idiomas.entities.EstadiaEntity;
import co.edu.uniandes.csw.idiomas.entities.GrupoDeInteresEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author estudiante
 */
@Stateless
public class GrupoDeInteresPersistence {
        private static final Logger LOGGER = Logger.getLogger(GrupoDeInteresPersistence.class.getName());
    
    @PersistenceContext(unitName = "idiomasPU")
    protected EntityManager em; 
    
    /**
     * Método para persisitir la entidad en la base de datos.
     *
     * @param GrupoDeInteresEntity objeto Administrador que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public GrupoDeInteresEntity create(GrupoDeInteresEntity pGrupoDeInteresEntity) {
        LOGGER.log(Level.INFO, "Creando un pGrupoDeInteres nuevo");        
        em.persist(pGrupoDeInteresEntity);
        LOGGER.log(Level.INFO, "Saliendo de crear un pGrupoDeInteres nuevo");
        return pGrupoDeInteresEntity;
    }

    /**
     *
     * Borra una GrupoDeInteres de la base de datos recibiendo como argumento el id
     * de la GrupoDeInteres
     *
     * @param GrupoDeInteresId: id correspondiente a la Administrador a borrar.
     */
    public void delete(Long pGrupoDeInteresId) {
        LOGGER.log(Level.INFO, "Borrando GrupoDeInteres con id = {0}", pGrupoDeInteresId);
        // Se hace uso de mismo método que esta explicado en public AdministradorEntity find(Long id) para obtener la Administrador a borrar.
        GrupoDeInteresEntity entity = em.find(GrupoDeInteresEntity.class, pGrupoDeInteresId);
        /* Note que una vez obtenido el objeto desde la base de datos llamado "entity", volvemos hacer uso de un método propio del
         EntityManager para eliminar de la base de datos el objeto que encontramos y queremos borrar.
         Es similar a "delete from AdministradorEntity where id=id;" - "DELETE FROM table_nombre WHERE condition;" en SQL.*/
        em.remove(entity);
        LOGGER.log(Level.INFO, "Saliendo de borrar la GrupoDeInteres con id = {0}", pGrupoDeInteresId);
    }
    /**
     * Actualiza una estadia.
     *
     * @param GrupoDeInteresEntity: la estadia que viene con los nuevos cambios.
     * Por ejemplo el nombre pudo cambiar. En ese caso, se haria uso del método
     * update.
     * @return un grupo con los cambios aplicados.
     */
    public GrupoDeInteresEntity update(GrupoDeInteresEntity pGrupoDeInteresEntity) {
        LOGGER.log(Level.INFO, "Actualizando editorial con id = {0}", pGrupoDeInteresEntity.getId());
        /* Note que hacemos uso de un método propio del EntityManager llamado merge() que recibe como argumento
        la editorial con los cambios, esto es similar a 
        "UPDATE table_name SET column1 = value1, column2 = value2, ... WHERE condition;" en SQL.
         */
        LOGGER.log(Level.INFO, "Saliendo de actualizar la grupoDeInteres con id = {0}", pGrupoDeInteresEntity.getId());
        return em.merge(pGrupoDeInteresEntity);
    }
        /**
     * Devuelve todas las estadias de la base de datos.
     *
     * @return una lista con todas las estadias que encuentre en la base de
     * datos, "select u from EstadiaEntity u" es como un "select * from
     * EstadiaEntity;" - "SELECT * FROM table_name" en SQL.
     */
    public List<GrupoDeInteresEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todas los grupoDeInteres");
        // Se crea un query para buscar todas las estadias en la base de datos.
        TypedQuery query = em.createQuery("select u from GrupoDeInteresEntity u", GrupoDeInteresEntity.class);
        // Se hace uso del método getResultList() que obtiene una lista de estadias.
        return query.getResultList();
    }
	
    /**
     * Busca si hay alguna estadia con el id que se envía de argumento
     *
     * @param pEstadiaId: id correspondiente a la estadia buscada.
     * @return una estadia.
     */
    public GrupoDeInteresEntity find(Long pGrupoDeInteresId) {
        LOGGER.log(Level.INFO, "Consultando grupoDeInteres con id={0}", pGrupoDeInteresId);
        /* Note que se hace uso del metodo "find" propio del EntityManager, el cual recibe como argumento 
        el tipo de la clase y el objeto que nos hara el filtro en la base de datos en este caso el "id"
        Suponga que es algo similar a "select * from EstadiaEntity where id=id;" - "SELECT * FROM table_name WHERE condition;" en SQL.
         */
        return em.find(GrupoDeInteresEntity.class, pGrupoDeInteresId);
    }
//
//    /**
//     * Busca si hay alguna Administrador con el nombre que se envía de argumento
//     *
//     * @param nombre: Nombre de la Administrador que se está buscando
//     * @return null si no existe ninguna Administrador con el nombre del argumento.
//     * Si existe alguna devuelve la primera.
//     */
//    public GrupoDeInteresEntity findByName(String idioma) {
//        LOGGER.log(Level.INFO, "Consultando GrupoDeInteres por nombre ", idioma);
//        // Se crea un query para buscar Administradors con el nombre que recibe el método como argumento. ":nombre" es un placeholder que debe ser remplazado
//        TypedQuery query = em.createQuery("Select e From GrupoDeInteresEntity e where e.nombre = :nombre", GrupoDeInteresEntity.class);
//        // Se remplaza el placeholder ":nombre" con el valor del argumento 
//        query = query.setParameter("idioma", idioma);
//        // Se invoca el query se obtiene la lista resultado
//        List<GrupoDeInteresEntity> sameIdioma = query.getResultList();
//        GrupoDeInteresEntity result;
//        if (sameIdioma == null) {
//            result = null;
//        } else if (sameIdioma.isEmpty()) {
//            result = null;
//        } else {
//            result = sameIdioma.get(0);
//        }
//        LOGGER.log(Level.INFO, "Saliendo de consultar GrupoDeInteres por idioma ", sameIdioma);
//        return result;
//    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.test.logic;

import co.edu.uniandes.csw.idiomas.ejb.UsuarioEstadiaLogic;
import co.edu.uniandes.csw.idiomas.ejb.EstadiaLogic;
import co.edu.uniandes.csw.idiomas.entities.UsuarioEntity;
import co.edu.uniandes.csw.idiomas.entities.EstadiaEntity;
import co.edu.uniandes.csw.idiomas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.idiomas.persistence.UsuarioPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 * Pruebas de logica de la relacion Usuario - Estadia
 * @author g.cubillosb
 */
@RunWith(Arquillian.class)
public class UsuarioEstadiaLogicTest {

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private UsuarioEstadiaLogic usuarioEstadiaLogic;

    @Inject
    private EstadiaLogic estadiaUsuarioLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private UsuarioEntity usuario = new UsuarioEntity();
    private List<EstadiaEntity> data = new ArrayList<>();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(UsuarioEntity.class.getPackage())
                .addPackage(EstadiaEntity.class.getPackage())
                .addPackage(UsuarioEstadiaLogic.class.getPackage())
                .addPackage(UsuarioPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * Configuración inicial de la prueba.
     */
    @Before
    public void configTest() {
        try {
            utx.begin();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * Limpia las tablas que están implicadas en la prueba.
     */
    private void clearData() {
        em.createQuery("delete from UsuarioEntity").executeUpdate();
        em.createQuery("delete from EstadiaEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {

        usuario = factory.manufacturePojo(UsuarioEntity.class);
        usuario.setId(1L);
        usuario.setEstadias(new ArrayList<>());
        em.persist(usuario);

        for (int i = 0; i < 3; i++) {
            EstadiaEntity entity = factory.manufacturePojo(EstadiaEntity.class);
            entity.setAsistentesEstadia(new ArrayList<>());
            entity.getAsistentesEstadia().add(usuario);
            em.persist(entity);
            data.add(entity);
            usuario.getEstadias().add(entity);
        }
    }

    /**
     * Prueba para asociar un usuario a un estadia.
     *
     *
     */
//    @Test
//    public void addEstadiaTest() throws BusinessLogicException {
//        EstadiaEntity newEstadia = factory.manufacturePojo(EstadiaEntity.class);
//        estadiaUsuarioLogic.createEstadia(newEstadia);
//        EstadiaEntity estadiaUsuarioEntity = usuarioEstadiaLogic.addEstadia(usuario.getId(), newEstadia.getId());
//        Assert.assertNotNull(estadiaUsuarioEntity);
//
//        Assert.assertEquals(estadiaUsuarioEntity.getId(), newEstadia.getId());
//        Assert.assertEquals(estadiaUsuarioEntity.getAnfitrion(), newEstadia.getAnfitrion());
//        Assert.assertEquals(estadiaUsuarioEntity.getAsistentesEstadia(), newEstadia.getAsistentesEstadia());
//
//        EstadiaEntity lastEstadia = usuarioEstadiaLogic.getEstadia(usuario.getId(), newEstadia.getId());
//
//        Assert.assertEquals(estadiaUsuarioEntity.getId(), newEstadia.getId());
//        Assert.assertEquals(estadiaUsuarioEntity.getAnfitrion(), newEstadia.getAnfitrion());
//        Assert.assertEquals(estadiaUsuarioEntity.getAsistentesEstadia(), newEstadia.getAsistentesEstadia());
//    }

    /**
     * Prueba para consultar la lista de Estadias de un usuario.
     */
//    @Test
//    public void getEstadiasTest() {
//        List<EstadiaEntity> estadiaUsuarioEntities = usuarioEstadiaLogic.getEstadias(usuario.getId());
//
//        Assert.assertEquals(data.size(), estadiaUsuarioEntities.size());
//
//        for (int i = 0; i < data.size(); i++) {
//            Assert.assertTrue(estadiaUsuarioEntities.contains(data.get(0)));
//        }
//    }

    /**
     * Prueba para cpnsultar un estadia de un usuario.
     *
     * @throws co.edu.uniandes.csw.estadiaUsuariostore.exceptions.BusinessLogicException
     */
//    @Test
//    public void getEstadiaTest() throws BusinessLogicException {
//        EstadiaEntity estadiaUsuarioEntity = data.get(0);
//        EstadiaEntity estadiaUsuario = usuarioEstadiaLogic.getEstadia(usuario.getId(), estadiaUsuarioEntity.getId());
//        Assert.assertNotNull(estadiaUsuario);
//
//        Assert.assertEquals(estadiaUsuarioEntity.getId(), estadiaUsuario.getId());
//        Assert.assertEquals(estadiaUsuarioEntity.getAnfitrion(), estadiaUsuario.getAnfitrion());
//        Assert.assertEquals(estadiaUsuarioEntity.getAsistentesEstadia(), estadiaUsuario.getAsistentesEstadia());
//    }

    /**
     * Prueba para actualizar los estadias de un usuario.
     *
     * @throws co.edu.uniandes.csw.estadiaUsuariostore.exceptions.BusinessLogicException
     */
//    @Test
//    public void replaceEstadiasTest() throws BusinessLogicException {
//        List<EstadiaEntity> nuevaLista = new ArrayList<>();
//        for (int i = 0; i < 3; i++) {
//            EstadiaEntity entity = factory.manufacturePojo(EstadiaEntity.class);
//            entity.setAsistentesEstadia(new ArrayList<>());
//            entity.getAsistentesEstadia().add(usuario);
//            estadiaUsuarioLogic.createEstadia(entity);
//            nuevaLista.add(entity);
//        }
//        usuarioEstadiaLogic.replaceEstadias(usuario.getId(), nuevaLista);
//        List<EstadiaEntity> estadiaUsuarioEntities = usuarioEstadiaLogic.getEstadias(usuario.getId());
//        for (EstadiaEntity aNuevaLista : nuevaLista) {
//            Assert.assertTrue(estadiaUsuarioEntities.contains(aNuevaLista));
//        }
//    }

    /**
     * Prueba desasociar un estadia con un usuario.
     *
     */
//    @Test
//    public void removeEstadiaTest() {
//        for (EstadiaEntity estadiaUsuario : data) {
//            usuarioEstadiaLogic.removeEstadia(usuario.getId(), estadiaUsuario.getId());
//        }
//        Assert.assertTrue(usuarioEstadiaLogic.getEstadias(usuario.getId()).isEmpty());
//    }
}

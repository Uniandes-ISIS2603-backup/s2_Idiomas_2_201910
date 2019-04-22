/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.test.logic;

import co.edu.uniandes.csw.idiomas.ejb.UsuarioLogic;
import co.edu.uniandes.csw.idiomas.entities.UsuarioEntity;
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
 *
 * @author j.barbosaj 201717575
 */
@RunWith(Arquillian.class)
public class UsuarioLogicTest {
     /**
     * Atributo que representa los datos aleatorios a ser creados.
     */
    private PodamFactory factory = new PodamFactoryImpl();

    /**
     * Inyección de dependencias con UsuarioLogic.
     */
    @Inject
    private UsuarioLogic usuarioLogic;    
   
    
    /**
     * Contexto de persistencia que se va a utilizar para acceder a la base 
     * de datos.
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * Variable para marcar las transacciones del "em" (Entity Manager) cuando
     * se crean/borran datos.
     */
    @Inject
    private UserTransaction utx;

    private List<UsuarioEntity> data = new ArrayList<>();
    
  
    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(UsuarioEntity.class.getPackage())
                .addPackage(UsuarioLogic.class.getPackage())
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
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++)
        {
            UsuarioEntity usuario = factory.manufacturePojo(UsuarioEntity.class);
            em.persist(usuario);          
            data.add(usuario);
        }       
    }
    
     /**
     * Prueba para crear un Usuario.
     * @throws co.edu.uniandes.csw.idiomas.exceptions.BusinessLogicException
     */
    @Test
    public void createUsuarioTest() throws BusinessLogicException {
         UsuarioEntity newEntity = factory.manufacturePojo(UsuarioEntity.class);
        
         //crear un admin sin problemas de logica
         UsuarioEntity result = usuarioLogic.createUsuario(newEntity);
         Assert.assertEquals(newEntity, result);
         Assert.assertEquals(newEntity.getId(), result.getId());
         
         
         //crear un admin con problemas de logica en el nombre
        UsuarioEntity newEntity1 =  factory.manufacturePojo(UsuarioEntity.class);
        newEntity1.setNombre("");
        try{
        usuarioLogic.createUsuario(newEntity1);
        }
        catch(BusinessLogicException e)
        {
            Assert.assertEquals(e.getMessage(),"El Usuario no tiene nombre");
        }
        
         //crear un admin con problemas de logica en la contraseña
        UsuarioEntity newEntity2 =  factory.manufacturePojo(UsuarioEntity.class);
        newEntity2.setContrasenia(null);
        try{
        usuarioLogic.createUsuario(newEntity2);
        }
        catch(BusinessLogicException e)
        {
            Assert.assertEquals(e.getMessage(),"El Usuario no tiene contraseña");
        }
        
         //crear un admin con problemas de logica de nombre repetido
        UsuarioEntity newEntity3 =  factory.manufacturePojo(UsuarioEntity.class);
        newEntity3.setNombre("miNombre");
        try{
        usuarioLogic.createUsuario(newEntity3);
        usuarioLogic.createUsuario(newEntity3);
        }
        catch(BusinessLogicException e)
        {
            Assert.assertEquals(e.getMessage(),"Ya existe una Usuario con el nombre \"miNombre\"");
        }
         
         
    }
    
    /**
     * Prueva obtener la lista de usuarioes
     */
    @Test
    public void getUsuariosTest()
    {
        List<UsuarioEntity> lista = usuarioLogic.getUsuarios();
        Assert.assertNotNull( lista );
        Assert.assertEquals(lista.size(), data.size());  
    }
    
    /**
     * Prueva obtener un usuario
     */
    @Test
    public void getUsuarioTest()
    {
        Long elIdDeAlguien = data.get(0).getId();
        Assert.assertNotNull(usuarioLogic.getUsuario(elIdDeAlguien));
        
        Assert.assertNull(usuarioLogic.getUsuario(new Long(1000)));
       
    }
    
     /**
     * Prueba para actualizar un Usuario.
     * @throws co.edu.uniandes.csw.idiomas.exceptions.BusinessLogicException
     */
    @Test
    public void updateUsuarioTest() throws BusinessLogicException {
         UsuarioEntity newEntity = factory.manufacturePojo(UsuarioEntity.class);
         Long elIdDeAlguien = data.get(0).getId();
         newEntity.setId(elIdDeAlguien);
        UsuarioEntity antes = usuarioLogic.getUsuario(elIdDeAlguien);
         //update un admin sin problemas de logica
         UsuarioEntity result = usuarioLogic.updateUsuario(elIdDeAlguien, newEntity);
         Assert.assertNotEquals(antes.getNombre(), result.getNombre());
         Assert.assertEquals(antes.getId(), result.getId());
         
         //update un admin con problema en el nombre
        UsuarioEntity newEntity1 =  factory.manufacturePojo(UsuarioEntity.class);
        newEntity1.setNombre("");
        try{
        usuarioLogic.updateUsuario(elIdDeAlguien, newEntity1);
        }
        catch(BusinessLogicException e)
        {
            Assert.assertEquals(e.getMessage(),"El Usuario no tiene nombre");
        }
        
         //crear un admin con problemas de logica en la contraseña
        UsuarioEntity newEntity2 =  factory.manufacturePojo(UsuarioEntity.class);
        newEntity2.setContrasenia(null);
        try{
          usuarioLogic.updateUsuario(elIdDeAlguien, newEntity2);
        }
        catch(BusinessLogicException e)
        {
            Assert.assertEquals(e.getMessage(),"El Usuario no tiene contraseña");
        }
        
         //crear un admin con problemas de logica de nombre repetido
        UsuarioEntity newEntity3 =  factory.manufacturePojo(UsuarioEntity.class);
        newEntity3.setNombre("miNombre");
        try{
        usuarioLogic.updateUsuario(elIdDeAlguien, newEntity3);;
        usuarioLogic.updateUsuario(elIdDeAlguien, newEntity3);;
        }
        catch(BusinessLogicException e)
        {
            Assert.assertEquals(e.getMessage(),"Ya existe una Usuario con el nombre \"miNombre\"");
        }     
         
    }
    
    @Test
    public void deleteUsuarioTest() throws BusinessLogicException 
    {
         Long elIdDeAlguien = data.get(0).getId();
         usuarioLogic.deleteUsuario(elIdDeAlguien);
         Assert.assertNull(usuarioLogic.getUsuario(elIdDeAlguien));
         Assert.assertNotEquals(data.size(), usuarioLogic.getUsuarios().size());
         
         try{
             usuarioLogic.deleteUsuario(new Long(1000));
         }
         catch (BusinessLogicException e)
         {
             Assert.assertEquals(e.getMessage(), "el usuario no existe");
         }
    }
}

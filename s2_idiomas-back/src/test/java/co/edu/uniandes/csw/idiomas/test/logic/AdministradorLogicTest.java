/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.test.logic;

import co.edu.uniandes.csw.idiomas.ejb.AdministradorLogic;
import co.edu.uniandes.csw.idiomas.entities.AdministradorEntity;
import co.edu.uniandes.csw.idiomas.entities.UsuarioEntity;
import co.edu.uniandes.csw.idiomas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.idiomas.persistence.AdministradorPersistence;
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
public class AdministradorLogicTest {
     /**
     * Atributo que representa los datos aleatorios a ser creados.
     */
    private PodamFactory factory = new PodamFactoryImpl();

    /**
     * Inyección de dependencias con AdministradorLogic.
     */
    @Inject
    private AdministradorLogic usuarioLogic;    
   
    
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

    private List<AdministradorEntity> data = new ArrayList<>();
    
  
    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(AdministradorEntity.class.getPackage())
                .addPackage(AdministradorLogic.class.getPackage())
                .addPackage(AdministradorPersistence.class.getPackage())
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
        em.createQuery("delete from AdministradorEntity").executeUpdate();     
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++)
        {
            AdministradorEntity usuario = factory.manufacturePojo(AdministradorEntity.class);
            em.persist(usuario);          
            data.add(usuario);
        }       
    }
    
     /**
     * Prueba para crear un Administrador.
     * @throws co.edu.uniandes.csw.idiomas.exceptions.BusinessLogicException
     */
    @Test
    public void createAdministradorTest() throws BusinessLogicException {
         AdministradorEntity newEntity = factory.manufacturePojo(AdministradorEntity.class);
        
         //crear un admin sin problemas de logica
         AdministradorEntity result = usuarioLogic.createAdministrador(newEntity);
         Assert.assertEquals(newEntity, result);
         Assert.assertEquals(newEntity.getId(), result.getId());
         
         
         //crear un admin con problemas de logica en el nombre
        AdministradorEntity newEntity1 =  factory.manufacturePojo(AdministradorEntity.class);
        newEntity1.setNombre("");
        try{
        usuarioLogic.createAdministrador(newEntity1);
        }
        catch(BusinessLogicException e)
        {
            Assert.assertEquals(e.getMessage(),"El Administrador no tiene nombre");
        }
        
         //crear un admin con problemas de logica en la contraseña
        AdministradorEntity newEntity2 =  factory.manufacturePojo(AdministradorEntity.class);
        newEntity2.setContrasenia(null);
        try{
        usuarioLogic.createAdministrador(newEntity2);
        }
        catch(BusinessLogicException e)
        {
            Assert.assertEquals(e.getMessage(),"El Administrador no tiene contraseña");
        }
        
         //crear un admin con problemas de logica de nombre repetido
        AdministradorEntity newEntity3 =  factory.manufacturePojo(AdministradorEntity.class);
        newEntity3.setNombre("miNombre");
        try{
        usuarioLogic.createAdministrador(newEntity3);
        usuarioLogic.createAdministrador(newEntity3);
        }
        catch(BusinessLogicException e)
        {
            Assert.assertEquals(e.getMessage(),"Ya existe una Administrador con el nombre \"miNombre\"");
        }
         
         
    }
    
    /**
     * Prueva obtener la lista de administradores
     */
    @Test
    public void getAdministradorsTest()
    {
        List<AdministradorEntity> lista = usuarioLogic.getAdministradors();
        Assert.assertNotNull( lista );
        Assert.assertEquals(lista.size(), data.size());  
    }
    
    /**
     * Prueva obtener un administrador
     */
    @Test
    public void getAdministradorTest()
    {
        Long elIdDeAlguien = data.get(0).getId();
        Assert.assertNotNull(usuarioLogic.getAdministrador(elIdDeAlguien));
        
        Assert.assertNull(usuarioLogic.getAdministrador(new Long(1000)));
       
    }
    
     /**
     * Prueba para actualizar un Administrador.
     * @throws co.edu.uniandes.csw.idiomas.exceptions.BusinessLogicException
     */
    @Test
    public void updateAdministradorTest() throws BusinessLogicException {
         AdministradorEntity newEntity = factory.manufacturePojo(AdministradorEntity.class);
         Long elIdDeAlguien = data.get(0).getId();
         newEntity.setId(elIdDeAlguien);
        AdministradorEntity antes = usuarioLogic.getAdministrador(elIdDeAlguien);
         //update un admin sin problemas de logica
         AdministradorEntity result = usuarioLogic.updateAdministrador(elIdDeAlguien, newEntity);
         Assert.assertNotEquals(antes.getNombre(), result.getNombre());
         Assert.assertEquals(antes.getId(), result.getId());
         
         //update un admin con problema en el nombre
        AdministradorEntity newEntity1 =  factory.manufacturePojo(AdministradorEntity.class);
        newEntity1.setNombre("");
        try{
        usuarioLogic.updateAdministrador(elIdDeAlguien, newEntity1);
        }
        catch(BusinessLogicException e)
        {
            Assert.assertEquals(e.getMessage(),"El Administrador no tiene nombre");
        }
        
         //crear un admin con problemas de logica en la contraseña
        AdministradorEntity newEntity2 =  factory.manufacturePojo(AdministradorEntity.class);
        newEntity2.setContrasenia(null);
        try{
          usuarioLogic.updateAdministrador(elIdDeAlguien, newEntity2);
        }
        catch(BusinessLogicException e)
        {
            Assert.assertEquals(e.getMessage(),"El Administrador no tiene contraseña");
        }
        
         //crear un admin con problemas de logica de nombre repetido
        AdministradorEntity newEntity3 =  factory.manufacturePojo(AdministradorEntity.class);
        newEntity3.setNombre("miNombre");
        try{
        usuarioLogic.updateAdministrador(elIdDeAlguien, newEntity3);;
        usuarioLogic.updateAdministrador(elIdDeAlguien, newEntity3);;
        }
        catch(BusinessLogicException e)
        {
            Assert.assertEquals(e.getMessage(),"Ya existe una Administrador con el nombre \"miNombre\"");
        }     
         
    }
    
    @Test
    public void deleteAdministradorTest() throws BusinessLogicException 
    {
         Long elIdDeAlguien = data.get(0).getId();
         usuarioLogic.deleteAdministrador(elIdDeAlguien);
         Assert.assertNull(usuarioLogic.getAdministrador(elIdDeAlguien));
         Assert.assertNotEquals(data.size(), usuarioLogic.getAdministradors().size());
         
         try{
             usuarioLogic.deleteAdministrador(new Long(1000));
         }
         catch (BusinessLogicException e)
         {
             Assert.assertEquals(e.getMessage(), "el administrador no existe");
         }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.test.logic;

import co.edu.uniandes.csw.idiomas.ejb.AnfitrionLogic;
import co.edu.uniandes.csw.idiomas.entities.AnfitrionEntity;
import co.edu.uniandes.csw.idiomas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.idiomas.persistence.AnfitrionPersistence;
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
public class AnfitrionLogicTest {
      /**
     * Atributo que representa los datos aleatorios a ser creados.
     */
    private PodamFactory factory = new PodamFactoryImpl();

    /**
     * Inyección de dependencias con AnfitrionLogic.
     */
    @Inject
    private AnfitrionLogic anfitrionLogic;    
   
    
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

    private List<AnfitrionEntity> data = new ArrayList<>();
    
  
    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(AnfitrionEntity.class.getPackage())
                .addPackage(AnfitrionLogic.class.getPackage())
                .addPackage(AnfitrionPersistence.class.getPackage())
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
        em.createQuery("delete from AnfitrionEntity").executeUpdate();     
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++)
        {
            AnfitrionEntity anfitrion = factory.manufacturePojo(AnfitrionEntity.class);
            em.persist(anfitrion);          
            data.add(anfitrion);
        }       
    }
    
     /**
     * Prueba para crear un Anfitrion.
     * @throws co.edu.uniandes.csw.idiomas.exceptions.BusinessLogicException
     */
    @Test
    public void createAnfitrionTest() throws BusinessLogicException {
         AnfitrionEntity newEntity = factory.manufacturePojo(AnfitrionEntity.class);
        
         //crear un admin sin problemas de logica
         AnfitrionEntity result = anfitrionLogic.createAnfitrion(newEntity);
         Assert.assertEquals(newEntity, result);
         Assert.assertEquals(newEntity.getId(), result.getId());
         
         
         //crear un admin con problemas de logica en el nombre
        AnfitrionEntity newEntity1 =  factory.manufacturePojo(AnfitrionEntity.class);
        newEntity1.setNombre("");
        try{
        anfitrionLogic.createAnfitrion(newEntity1);
        }
        catch(BusinessLogicException e)
        {
            Assert.assertEquals(e.getMessage(),"El Anfitrion no tiene nombre");
        }
        
         //crear un admin con problemas de logica en la contraseña
        AnfitrionEntity newEntity2 =  factory.manufacturePojo(AnfitrionEntity.class);
        newEntity2.setContrasenia(null);
        try{
        anfitrionLogic.createAnfitrion(newEntity2);
        }
        catch(BusinessLogicException e)
        {
            Assert.assertEquals(e.getMessage(),"El Anfitrion no tiene contraseña");
        }
        
         //crear un admin con problemas de logica de nombre repetido
        AnfitrionEntity newEntity3 =  factory.manufacturePojo(AnfitrionEntity.class);
        newEntity3.setNombre("miNombre");
        try{
        anfitrionLogic.createAnfitrion(newEntity3);
        anfitrionLogic.createAnfitrion(newEntity3);
        }
        catch(BusinessLogicException e)
        {
            Assert.assertEquals(e.getMessage(),"Ya existe una Anfitrion con el nombre \"miNombre\"");
        }
         
         
    }
    
    /**
     * Prueva obtener la lista de anfitriones
     */
    @Test
    public void getAnfitrionsTest()
    {
        List<AnfitrionEntity> lista = anfitrionLogic.getAnfitrions();
        Assert.assertNotNull( lista );
        Assert.assertEquals(lista.size(), data.size());  
    }
    
    /**
     * Prueva obtener un anfitrion
     */
    @Test
    public void getAnfitrionTest()
    {
        Long elIdDeAlguien = data.get(0).getId();
        Assert.assertNotNull(anfitrionLogic.getAnfitrion(elIdDeAlguien));
        
        Assert.assertNull(anfitrionLogic.getAnfitrion(new Long(1000)));
       
    }
    
     /**
     * Prueba para actualizar un Anfitrion.
     * @throws co.edu.uniandes.csw.idiomas.exceptions.BusinessLogicException
     */
    @Test
    public void updateAnfitrionTest() throws BusinessLogicException {
         AnfitrionEntity newEntity = factory.manufacturePojo(AnfitrionEntity.class);
         Long elIdDeAlguien = data.get(0).getId();
         newEntity.setId(elIdDeAlguien);
        AnfitrionEntity antes = anfitrionLogic.getAnfitrion(elIdDeAlguien);
         //update un admin sin problemas de logica
         AnfitrionEntity result = anfitrionLogic.updateAnfitrion(elIdDeAlguien, newEntity);
         Assert.assertNotEquals(antes.getNombre(), result.getNombre());
         Assert.assertEquals(antes.getId(), result.getId());
         
         //update un admin con problema en el nombre
        AnfitrionEntity newEntity1 =  factory.manufacturePojo(AnfitrionEntity.class);
        newEntity1.setNombre("");
        try{
        anfitrionLogic.updateAnfitrion(elIdDeAlguien, newEntity1);
        }
        catch(BusinessLogicException e)
        {
            Assert.assertEquals(e.getMessage(),"El Anfitrion no tiene nombre");
        }
        
         //crear un admin con problemas de logica en la contraseña
        AnfitrionEntity newEntity2 =  factory.manufacturePojo(AnfitrionEntity.class);
        newEntity2.setContrasenia(null);
        try{
          anfitrionLogic.updateAnfitrion(elIdDeAlguien, newEntity2);
        }
        catch(BusinessLogicException e)
        {
            Assert.assertEquals(e.getMessage(),"El Anfitrion no tiene contraseña");
        }
        
         //crear un admin con problemas de logica de nombre repetido
        AnfitrionEntity newEntity3 =  factory.manufacturePojo(AnfitrionEntity.class);
        newEntity3.setNombre("miNombre");
        try{
        anfitrionLogic.updateAnfitrion(elIdDeAlguien, newEntity3);;
        anfitrionLogic.updateAnfitrion(elIdDeAlguien, newEntity3);;
        }
        catch(BusinessLogicException e)
        {
            Assert.assertEquals(e.getMessage(),"Ya existe una Anfitrion con el nombre \"miNombre\"");
        }     
         
    }
    
    @Test
    public void deleteAnfitrionTest() throws BusinessLogicException 
    {
         Long elIdDeAlguien = data.get(0).getId();
         anfitrionLogic.deleteAnfitrion(elIdDeAlguien);
         Assert.assertNull(anfitrionLogic.getAnfitrion(elIdDeAlguien));
         Assert.assertNotEquals(data.size(), anfitrionLogic.getAnfitrions().size());
         
         try{
             anfitrionLogic.deleteAnfitrion(new Long(1000));
         }
         catch (BusinessLogicException e)
         {
             Assert.assertEquals(e.getMessage(), "el anfitrion no existe");
         }
    }
}

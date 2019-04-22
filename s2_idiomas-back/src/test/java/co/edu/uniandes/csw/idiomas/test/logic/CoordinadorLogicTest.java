/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.test.logic;

import co.edu.uniandes.csw.idiomas.ejb.CoordinadorLogic;
import co.edu.uniandes.csw.idiomas.entities.CoordinadorEntity;
import co.edu.uniandes.csw.idiomas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.idiomas.persistence.CoordinadorPersistence;
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
public class CoordinadorLogicTest 
{
     /**
     * Atributo que representa los datos aleatorios a ser creados.
     */
    private PodamFactory factory = new PodamFactoryImpl();

    /**
     * Inyección de dependencias con CoordinadorLogic.
     */
    @Inject
    private CoordinadorLogic coordinadorLogic;    
   
    
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

    private List<CoordinadorEntity> data = new ArrayList<>();
    
  
    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CoordinadorEntity.class.getPackage())
                .addPackage(CoordinadorLogic.class.getPackage())
                .addPackage(CoordinadorPersistence.class.getPackage())
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
        em.createQuery("delete from CoordinadorEntity").executeUpdate();     
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++)
        {
            CoordinadorEntity coordinador = factory.manufacturePojo(CoordinadorEntity.class);
            em.persist(coordinador);          
            data.add(coordinador);
        }       
    }
    
     /**
     * Prueba para crear un Coordinador.
     * @throws co.edu.uniandes.csw.idiomas.exceptions.BusinessLogicException
     */
    @Test
    public void createCoordinadorTest() throws BusinessLogicException {
         CoordinadorEntity newEntity = factory.manufacturePojo(CoordinadorEntity.class);
        
         //crear un admin sin problemas de logica
         CoordinadorEntity result = coordinadorLogic.createCoordinador(newEntity);
         Assert.assertEquals(newEntity, result);
         Assert.assertEquals(newEntity.getId(), result.getId());
         
         
         //crear un admin con problemas de logica en el nombre
        CoordinadorEntity newEntity1 =  factory.manufacturePojo(CoordinadorEntity.class);
        newEntity1.setNombre("");
        try{
        coordinadorLogic.createCoordinador(newEntity1);
        }
        catch(BusinessLogicException e)
        {
            Assert.assertEquals(e.getMessage(),"El Coordinador no tiene nombre");
        }
        
         //crear un admin con problemas de logica en la contraseña
        CoordinadorEntity newEntity2 =  factory.manufacturePojo(CoordinadorEntity.class);
        newEntity2.setContrasenia(null);
        try{
        coordinadorLogic.createCoordinador(newEntity2);
        }
        catch(BusinessLogicException e)
        {
            Assert.assertEquals(e.getMessage(),"El Coordinador no tiene contraseña");
        }
        
         //crear un admin con problemas de logica de nombre repetido
        CoordinadorEntity newEntity3 =  factory.manufacturePojo(CoordinadorEntity.class);
        newEntity3.setNombre("miNombre");
        try{
        coordinadorLogic.createCoordinador(newEntity3);
        coordinadorLogic.createCoordinador(newEntity3);
        }
        catch(BusinessLogicException e)
        {
            Assert.assertEquals(e.getMessage(),"Ya existe una Coordinador con el nombre \"miNombre\"");
        }
         
         
    }
    
    /**
     * Prueva obtener la lista de coordinadores
     */
    @Test
    public void getCoordinadorsTest()
    {
        List<CoordinadorEntity> lista = coordinadorLogic.getCoordinadors();
        Assert.assertNotNull( lista );
        Assert.assertEquals(lista.size(), data.size());  
    }
    
    /**
     * Prueva obtener un coordinador
     */
    @Test
    public void getCoordinadorTest()
    {
        Long elIdDeAlguien = data.get(0).getId();
        Assert.assertNotNull(coordinadorLogic.getCoordinador(elIdDeAlguien));
        
        Assert.assertNull(coordinadorLogic.getCoordinador(new Long(1000)));
       
    }
    
     /**
     * Prueba para actualizar un Coordinador.
     * @throws co.edu.uniandes.csw.idiomas.exceptions.BusinessLogicException
     */
    @Test
    public void updateCoordinadorTest() throws BusinessLogicException {
         CoordinadorEntity newEntity = factory.manufacturePojo(CoordinadorEntity.class);
         Long elIdDeAlguien = data.get(0).getId();
         newEntity.setId(elIdDeAlguien);
        CoordinadorEntity antes = coordinadorLogic.getCoordinador(elIdDeAlguien);
         //update un admin sin problemas de logica
         CoordinadorEntity result = coordinadorLogic.updateCoordinador(elIdDeAlguien, newEntity);
         Assert.assertNotEquals(antes.getNombre(), result.getNombre());
         Assert.assertEquals(antes.getId(), result.getId());
         
         //update un admin con problema en el nombre
        CoordinadorEntity newEntity1 =  factory.manufacturePojo(CoordinadorEntity.class);
        newEntity1.setNombre("");
        try{
        coordinadorLogic.updateCoordinador(elIdDeAlguien, newEntity1);
        }
        catch(BusinessLogicException e)
        {
            Assert.assertEquals(e.getMessage(),"El Coordinador no tiene nombre");
        }
        
         //crear un admin con problemas de logica en la contraseña
        CoordinadorEntity newEntity2 =  factory.manufacturePojo(CoordinadorEntity.class);
        newEntity2.setContrasenia(null);
        try{
          coordinadorLogic.updateCoordinador(elIdDeAlguien, newEntity2);
        }
        catch(BusinessLogicException e)
        {
            Assert.assertEquals(e.getMessage(),"El Coordinador no tiene contraseña");
        }
        
         //crear un admin con problemas de logica de nombre repetido
        CoordinadorEntity newEntity3 =  factory.manufacturePojo(CoordinadorEntity.class);
        newEntity3.setNombre("miNombre");
        try{
        coordinadorLogic.updateCoordinador(elIdDeAlguien, newEntity3);;
        coordinadorLogic.updateCoordinador(elIdDeAlguien, newEntity3);;
        }
        catch(BusinessLogicException e)
        {
            Assert.assertEquals(e.getMessage(),"Ya existe una Coordinador con el nombre \"miNombre\"");
        }     
         
    }
    
    @Test
    public void deleteCoordinadorTest() throws BusinessLogicException 
    {
         Long elIdDeAlguien = data.get(0).getId();
         coordinadorLogic.deleteCoordinador(elIdDeAlguien);
         Assert.assertNull(coordinadorLogic.getCoordinador(elIdDeAlguien));
         Assert.assertNotEquals(data.size(), coordinadorLogic.getCoordinadors().size());
         
         try{
             coordinadorLogic.deleteCoordinador(new Long(1000));
         }
         catch (BusinessLogicException e)
         {
             Assert.assertEquals(e.getMessage(), "el coordinador no existe");
         }
    }
}

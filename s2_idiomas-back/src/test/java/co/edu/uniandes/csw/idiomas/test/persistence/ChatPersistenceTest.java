/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.idiomas.test.persistence;

import co.edu.uniandes.csw.idiomas.entities.ChatEntity;
import co.edu.uniandes.csw.idiomas.persistence.ChatPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.junit.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 * Pruebas de persistencia de Chat
 *
 * @chat g.cubillosb
 */
@RunWith(Arquillian.class)
public class ChatPersistenceTest {

    /**
     * Inyecta la dependencia de ChatPersistence.
     */
    @Inject
    private ChatPersistence chatPersistence;

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
    UserTransaction utx;

    /**
     * Lista de los datos de prueba.
     */
    private List<ChatEntity> data = new ArrayList<>();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ChatEntity.class.getPackage())
                .addPackage(ChatPersistence.class.getPackage())
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
            em.joinTransaction();
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
        em.createQuery("delete from ChatEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            ChatEntity entity = factory.manufacturePojo(ChatEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Prueba para crear un Chat.
     */
    @Test
    public void createChatTest() {
        PodamFactory factory = new PodamFactoryImpl();
        ChatEntity newEntity = factory.manufacturePojo(ChatEntity.class);
        ChatEntity result = chatPersistence.create(newEntity);

        Assert.assertNotNull(result);

        ChatEntity entity = em.find(ChatEntity.class, result.getId());

        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
    }

    /**
     * Prueba para consultar la lista de Chats.
     */
    @Test
    public void getChatsTest() {
        List<ChatEntity> list = chatPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (ChatEntity ent : list) {
            boolean found = false;
            for (ChatEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Chat.
     */
    @Test
    public void getChatTest() {
        ChatEntity entity = data.get(0);
        ChatEntity newEntity = chatPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getNombre(), newEntity.getNombre());
        Assert.assertEquals(entity.getFecha(), newEntity.getFecha());
    }

    /**
     * Prueba para actualizar un Chat.
     */
    @Test
    public void updateChatTest() {
        ChatEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        ChatEntity newEntity = factory.manufacturePojo(ChatEntity.class);

        newEntity.setId(entity.getId());

        chatPersistence.update(newEntity);

        ChatEntity resp = em.find(ChatEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getNombre(), resp.getNombre());
    }

    /**
     * Prueba para eliminar un Chat.
     */
    @Test
    public void deleteChatTest() {
        ChatEntity entity = data.get(0);
        chatPersistence.delete(entity.getId());
        ChatEntity deleted = em.find(ChatEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}

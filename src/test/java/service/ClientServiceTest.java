/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import db.ClientDAO;
import java.util.ArrayList;
import model.Client;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author Teodora
 */
public class ClientServiceTest {
    private static ClientService cs;
    private static ClientDAO cdao;
    private static int testNumber;
    public ClientServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        cs = new ClientService();
        cdao = new ClientDAO();
        testNumber = 0;
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of login method, of class ClientService.
     */
    @Test
    public void testRegistrationNameFromDB() {
        testNumber++;
        System.out.println("testRegistrationNameFromDB");
        System.out.println(testNumber);
        boolean result = cs.register("Teodora501", "Sakotic998", "123");
        boolean expResult = true;
        assertEquals(expResult, result);
        
        Client dbClient = cdao.getOne(testNumber);
        assertEquals("Teodora501", dbClient.getName());
    }
    
    @Test
    public void testRegistrationUsernameFromDB() {
        testNumber++;
        System.out.println("testRegistrationUsernameFromDB");
        System.out.println(testNumber);
        boolean result = cs.register("Teodora", "Sakotic501", "123");
        boolean expResult = true;
        assertEquals(expResult, result);
        
        Client dbClient = cdao.getOne(testNumber);
        assertEquals("Sakotic501", dbClient.getUsername());
    }
    
    @Test
    public void testRegistrationPasswordFromDB() {
        testNumber++;
        System.out.println("testRegistrationPasswordFromDB");
        System.out.println(testNumber);
        boolean result = cs.register("Teodora", "Sakotic502", "1234");
        boolean expResult = true;
        assertEquals(expResult, result);
        
        Client dbClient = cdao.getOne(testNumber);
        assertEquals("1234", dbClient.getPassword());
    }
    
    @Test
    public void testRegistrationWithoutName() {
        boolean result = cs.register("", "Sakotic503", "123");
        
        boolean expResult = false;
        assertEquals(expResult, result);
        
        for(Client x : cdao.getAll()){
            if(x.getName().equals("")){
                result = false;
                break;
            }
        }
        if(!result){
            testNumber++;
        }
        assertEquals(!expResult, result);
    }
    
    @Test
    public void testRegistrationWithoutUsername() {
        boolean result = cs.register("Teodora", "", "123");
        
        boolean expResult = false;
        assertEquals(expResult, result);
        
        for(Client x : cdao.getAll()){
            if(x.getUsername().equals("")){
                result = false;
                break;
            }
        }
        if(!result){
            testNumber++;
        }
        assertEquals(!expResult, result);
    }
    
    @Test
    public void testRegistrationWithoutPassword() {
        boolean result = cs.register("Teodora", "Sakotic505", "");
        boolean expResult = false;
        assertEquals(expResult, result);
        
        for(Client x : cdao.getAll()){
            if(x.getPassword().equals("")){
                result = false;
                break;
            }
        }
        
        if(!result){
            testNumber++;
        }
        assertEquals(!expResult, result);
    }
    
    @Test
    public void testRegistrationDuplicateUsername() {
        testNumber++;
        cs.register("Teodora", "Sakotic506", "123");
        boolean result = cs.register("Teodora", "Sakotic506", "123");
        boolean expResult = false;
        assertEquals(expResult, result);
    }
    
    @Test
    public void testLoginExistingUserNotNull() {
        testNumber++;
        cdao.insertOne(new Client(testNumber,"Teodora", "Teaa", "123"));
        Client loggedClient = cs.login("Teaa", "123");
        assertNotEquals(null, loggedClient);
    }
    
    @Test
    public void testLoginExistingUserIsThatUser() {
        testNumber++;
        cdao.insertOne(new Client(testNumber,"Teodora508", "Teaa501", "123"));
        Client loggedClient = cs.login("Teaa501", "123");
        assertEquals(loggedClient.getUsername(), "Teaa501");
        assertEquals(loggedClient.getName(), "Teodora508");
        assertEquals(loggedClient.getPassword(), "123");
        
    }
    
    @Test
    public void testLoginNonExistingUser() {
        Client loggedClient = cs.login("aaa", "aaa");
        assertEquals(null, loggedClient);
    }
        
    @Test
    public void testDeleteUser() {
        testNumber++;
        Client c = new Client(testNumber,"Teodora510", "Teaa511", "123");
        cdao.insertOne(c);
        cs.deleteUser(c);
        boolean result = true;
        for(Client x: cdao.getAll()){
            if(x.getId() == testNumber){
                result = false;
            }
        }
        assertEquals(true, result);
    }
    
    @Test
    public void testDeleteUserNull() {
        Client c = null;
        boolean result = cs.deleteUser(c);
        assertEquals(false, result);
    }
    
    
    @Test
    public void testUpdateInfoName() {
        testNumber++;
        System.out.println(testNumber);
        Client c = new Client(25,"Teodora", "Teaa512", "123");
        cdao.insertOne(c);
        boolean result = false;
        cs.updateInfo(c, "Ivan", "123", "123");
        for(Client x: cdao.getAll()){
            if(x.getId() == testNumber){
                if(x.getName().equals("Ivan") && x.getPassword().equals("123"))
                result = true;
            }
        }
        assertEquals(true, result);
    }

    @Test
    public void testUpdateInfoPass() {
        testNumber++;
        System.out.println(testNumber);
        Client c = new Client(25,"Teodora", "Teaa513", "123");
        cdao.insertOne(c);
        boolean result = false;
        cs.updateInfo(c, "Teodora", "123", "456");
        for(Client x: cdao.getAll()){
            if(x.getId() == testNumber){
                if(x.getName().equals("Teodora") && x.getPassword().equals("456"))
                result = true;
            }
        }
        assertEquals(true, result);
    }


    
}

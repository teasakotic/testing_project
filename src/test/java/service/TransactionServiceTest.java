/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import db.ClientDAO;
import db.DeliveryServiceDAO;
import db.ShopItemDAO;
import db.TransactionDAO;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import model.Client;
import model.DeliveryService;
import model.ShopItem;
import model.Transaction;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Teodora
 */
public class TransactionServiceTest {
    private static TransactionDAO tsdao;
    private static ShopItemDAO sidao;
    private static ClientDAO cdao;
    private static DeliveryServiceDAO dsdao;
    private static ClientService clientService;
    private static TransactionService service;
    private static PromotionService promoService;
    private static ShopItemService itemService;
    private static int testNumber;
    private static int othersTestNumber;
    public TransactionServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        dsdao = new DeliveryServiceDAO();
        tsdao = new TransactionDAO();
        sidao = new ShopItemDAO();
        cdao = new ClientDAO();
        clientService = new ClientService();
        itemService = new ShopItemService();
        service = new TransactionService(itemService);
        promoService = new PromotionService();
        testNumber = 0;
        othersTestNumber = 0;
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
     * Test of completeTransaction method, of class TransactionService.
     */
    @Test
    public void testCompleteTransaction() {
        testNumber++;
        othersTestNumber++;
        Client c = new Client(othersTestNumber,"Teodora501","Teodora501","Teodora501");
        DeliveryService d = new DeliveryService(othersTestNumber,"DS",50.0f,55.0f);
        ShopItem s = new ShopItem(othersTestNumber,"ItemShop", 350.0f, 30);
        cdao.insertOne(c);
        dsdao.insertOne(d);
        sidao.insertOne(s);
        service.completeTransaction(c, d, s, 30, 100.0f);
        Transaction transaction = tsdao.getOne(testNumber);
        assertEquals(transaction.getClientId(), (int) c.getId());
        assertEquals(transaction.getDeliveryServiceId(), (int) d.getId());
        assertEquals(transaction.getShopItemId(), (int) s.getId());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testCompleteTransactionClientNullId() {
        othersTestNumber++;
        Client c = new Client(null,"Teodora502","Teodora502","Teodora502");
        DeliveryService d = new DeliveryService(othersTestNumber,"DS",50.0f,55.0f);
        ShopItem s = new ShopItem(othersTestNumber,"ItemShop", 350.0f, 30);
        cdao.insertOne(c);
        dsdao.insertOne(d);
        sidao.insertOne(s);
        service.completeTransaction(c, d, s, 30, 100.0f);

    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testCompleteTransactionDSNullId() {
        
        othersTestNumber++;
        Client c = new Client(othersTestNumber,"Teodora503","Teodora503","Teodora503");
        DeliveryService d = new DeliveryService(null,"DS",50.0f,55.0f);
        ShopItem s = new ShopItem(othersTestNumber,"ItemShop", 350.0f, 30);
        cdao.insertOne(c);
        dsdao.insertOne(d);
        sidao.insertOne(s);
        service.completeTransaction(c, d, s, 30, 100.0f);

    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testCompleteTransactionShopItemNullId() {
        
        othersTestNumber++;
        Client c = new Client(othersTestNumber,"Teodora504","Teodora504","Teodora504");
        DeliveryService d = new DeliveryService(othersTestNumber,"DS",50.0f,55.0f);
        ShopItem s = new ShopItem(null,"ItemShop", 350.0f, 30);
        cdao.insertOne(c);
        dsdao.insertOne(d);
        sidao.insertOne(s);
        service.completeTransaction(c, d, s, 30, 100.0f);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testCompleteTransactionDistanceZero() {
        
        othersTestNumber++;
        Client c = new Client(othersTestNumber,"Teodora505","Teodora505","Teodora505");
        DeliveryService d = new DeliveryService(othersTestNumber,"DS",50.0f,55.0f);
        ShopItem s = new ShopItem(othersTestNumber,"ItemShop", 350.0f, 30);
        cdao.insertOne(c);
        dsdao.insertOne(d);
        sidao.insertOne(s);
        service.completeTransaction(c, d, s, 30,0.0f);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testCompleteTransactionAmountZero() {
        
        othersTestNumber++;
        Client c = new Client(othersTestNumber,"Teodora506","Teodora506","Teodora506");
        DeliveryService d = new DeliveryService(othersTestNumber,"DS",50.0f,55.0f);
        ShopItem s = new ShopItem(othersTestNumber,"ItemShop", 350.0f, 30);
        cdao.insertOne(c);
        dsdao.insertOne(d);
        sidao.insertOne(s);
        service.completeTransaction(c, d, s, 0, 100.0f);
    }
    
    @Test
    public void testCompleteTransactionDistanceOne() {
        testNumber++;
        othersTestNumber++;
        Client c = new Client(othersTestNumber,"Teodora507","Teodora507","Teodora507");
        DeliveryService d = new DeliveryService(othersTestNumber,"DS",50.0f,55.0f);
        ShopItem s = new ShopItem(othersTestNumber,"ItemShop", 350.0f, 30);
        cdao.insertOne(c);
        dsdao.insertOne(d);
        sidao.insertOne(s);
        service.completeTransaction(c, d, s, 30,1.0f);
        Transaction transaction = tsdao.getOne(testNumber);
        assertEquals(transaction.getClientId(), (int) c.getId());
        assertEquals(transaction.getDeliveryServiceId(), (int) d.getId());
        assertEquals(transaction.getShopItemId(), (int) s.getId());
    }
    
    @Test
    public void testCompleteTransactionAmountOne() {
        testNumber++;
        othersTestNumber++;
        Client c = new Client(othersTestNumber,"Teodora508","Teodora508","Teodora508");
        DeliveryService d = new DeliveryService(othersTestNumber,"DS",50.0f,55.0f);
        ShopItem s = new ShopItem(othersTestNumber,"ItemShop", 350.0f, 30);
        cdao.insertOne(c);
        dsdao.insertOne(d);
        sidao.insertOne(s);
        service.completeTransaction(c, d, s, 1, 100.0f);
        Transaction transaction = tsdao.getOne(testNumber);
        assertEquals(transaction.getClientId(), (int) c.getId());
        assertEquals(transaction.getDeliveryServiceId(), (int) d.getId());
        assertEquals(transaction.getShopItemId(), (int) s.getId());
    }
    
    @Test
    public void calculatePrice30ItemsWithPromo() {
        testNumber++;
        othersTestNumber++;
        Client c = new Client(othersTestNumber,"Teodora509","Teodora509","Teodora509");
        DeliveryService d = new DeliveryService(othersTestNumber,"DS",10.0f,10.0f);
        ShopItem s = new ShopItem(othersTestNumber,"ItemShop", 100.0f, 300);
        cdao.insertOne(c);
        dsdao.insertOne(d);
        sidao.insertOne(s);
        service.completeTransaction(c, d, s, 30, 100.0f); //90*30 + 20 - (350/100*20) + 100*100*10 - 1000
        Transaction transaction = tsdao.getOne(testNumber);
        float expectedPrice = 101020.0f;
        assertEquals(expectedPrice,transaction.getTotalPrice(),0.2);
    }
    
    @Test
    public void calculatePrice60ItemsWithPromo() {
        testNumber++;
        othersTestNumber++;
        Client c = new Client(othersTestNumber,"Teodora510","Teodora510","Teodora510");
        DeliveryService d = new DeliveryService(othersTestNumber,"DS",10.0f,10.0f);
        ShopItem s = new ShopItem(othersTestNumber,"ItemShop", 100.0f, 300);
        cdao.insertOne(c);
        dsdao.insertOne(d);
        sidao.insertOne(s);
        service.completeTransaction(c, d, s, 60, 100.0f); //80*60 + 20 - (350/100*20) + 100*100*10 - 1000
        Transaction transaction = tsdao.getOne(testNumber);
        float expectedPrice = 103750.0f;
        assertEquals(expectedPrice,transaction.getTotalPrice(),0.2);
    }
    
    @Test
    public void calculatePrice10ItemsWithPromo() {
        testNumber++;
        othersTestNumber++;
        Client c = new Client(othersTestNumber,"Teodora511","Teodora511","Teodora511");
        DeliveryService d = new DeliveryService(othersTestNumber,"DS",10.0f,10.0f);
        ShopItem s = new ShopItem(othersTestNumber,"ItemShop", 100.0f, 300);
        cdao.insertOne(c);
        dsdao.insertOne(d);
        sidao.insertOne(s);
        service.completeTransaction(c, d, s, 10, 100.0f); //10*30 + 20 - (350/100*20) + 100*100*10 - 1000
        Transaction transaction = tsdao.getOne(testNumber);
        float expectedPrice = 99250.0f;
        assertEquals(expectedPrice,transaction.getTotalPrice(),0.2);
    }
    
    @Test
    public void getRecentTransactionsOldTransaction(){
        testNumber++;
        othersTestNumber++;
        LocalDate localDate = LocalDate.now().minusDays(31);
        ZoneId defaultZoneId = ZoneId.systemDefault();
        Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
        
        ShopItem newItem = new ShopItem(othersTestNumber, "PopularniArtikal", 299.0f, 100);
        sidao.insertOne(newItem);
        Transaction t = new Transaction(testNumber, 1000.0f, 10, date , othersTestNumber, othersTestNumber, othersTestNumber, 0.0f);
        cdao.insertOne(new Client(othersTestNumber,"Teodora519","Teodora519","Teodora519"));
        dsdao.insertOne(new DeliveryService(othersTestNumber,"name",20.0f,20.0f));
        tsdao.insertOne(t);
        boolean result = true;
        ArrayList<Transaction> transactionList = service.getRecentTransactions();
        for( Transaction x: transactionList){
            if(x.getId() != null){
                if(Objects.equals(x.getId(), t.getId())){
                    System.out.println(x.getId() + " ID Transakcije");
                    System.out.println(x.getDate()+ " Date Transakcije");
                    result = false;
                }
            }
            
        }
        assertEquals(true, result);
    }
    
        @Test
    public void getRecentTransactions(){
        testNumber++;
        othersTestNumber++;
        LocalDate localDate = LocalDate.now().minusDays(1);
        ZoneId defaultZoneId = ZoneId.systemDefault();
        Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
        
        ShopItem newItem = new ShopItem(othersTestNumber, "PopularniArtikal", 299.0f, 100);
        sidao.insertOne(newItem);
        Transaction t = new Transaction(testNumber, 1000.0f, 10, date , othersTestNumber, othersTestNumber, othersTestNumber, 0.0f);
        cdao.insertOne(new Client(othersTestNumber,"Teodora520","Teodora520","Teodora520"));
        dsdao.insertOne(new DeliveryService(othersTestNumber,"name",20.0f,20.0f));
        tsdao.insertOne(t);
        boolean result = false;
        ArrayList<Transaction> transactionList = service.getRecentTransactions();
        for( Transaction x: transactionList){
            if(Objects.equals(x.getId(), t.getId())){
                result = true;
            }
        }
        assertEquals(true, result);
    }
}



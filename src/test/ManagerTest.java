
package test;

import com.company.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ManagerTest {

    private Manager manager;
    private Order completeOrder;
    private SharedObject sharedObject;
    private CsvProducer csvProducer;
    private ServerGUI serverGUI;

    @BeforeEach
    void setUp() throws InvalidItemNameException {
        Order orderSimpleItem = new Order();
        Order orderDoubleItem = new Order();
        Order orderTwoItems = new Order();
        completeOrder = new Order();

        Item itemChoc = new Item("Hot Chocolate", "A hot chocolate", "Hot",2.50, 3,20);
        Item itemSandwich = new Item("Ham Sandwich","A ham sandwich","Sandwiches",5.0, 2, 15);

        List<Item> simpleItemChoc = new ArrayList<>();
        List<Item> doubleItemChoc = new ArrayList<>();
        List<Item> twoItems = new ArrayList<>();

        simpleItemChoc.add(itemChoc);
        doubleItemChoc.add(itemChoc);
        doubleItemChoc.add(itemChoc);
        twoItems.add(itemChoc);
        twoItems.add(itemSandwich);

        orderSimpleItem.setItems(simpleItemChoc);
        orderDoubleItem.setItems(doubleItemChoc);
        orderTwoItems.setItems(twoItems);

        completeOrder.setCustomerID(1);
        completeOrder.setTimestamp("2016-11-09T11:44:44.797");
        completeOrder.setName("Peter Parker");
        completeOrder.setDiscountPrice((float) 7.50);
        completeOrder.setItems(twoItems);



        sharedObject = new SharedObject();
        manager = new Manager(sharedObject);

        try {
            csvProducer = new CsvProducer(sharedObject, manager.getMenu());
        } catch (IOException e) {
            e.printStackTrace();
        }

        serverGUI = new ServerGUI(manager, csvProducer);

    }

    @Test
    void testCopyOrder() {
        Order copy = Manager.copyOrder(completeOrder);
        assertNotSame(completeOrder, copy);
    }


    @Test
    void testAddServers() {
        manager.addServers(3);
        assertEquals(3, manager.getServers().size());
    }

    @Test
    void tesRemoveServers() {
        manager.removeServers(1);
        assertEquals(2, manager.getServers().size());
    }

    @Test
    void testRemoveServer() {
        manager.removeServer( manager.getServers().get(1));
        assertEquals(1, manager.getServers().size());
    }


    @Test
    void testAddBaristas() {
        manager.addBaristas(3);
        assertEquals(3, manager.getBaristas().size());
    }


    @Test
    void testAddProcessedOrder() {
        manager.addProcessedOrder(completeOrder);
        assertEquals(1, manager.getProcessedOrders().size());
    }

    @Test
    void testRegisterObserver() {
        manager.registerObserver(serverGUI);
        assertEquals(1, manager.getObservers().size());
    }

    @Test
    void testRemoveObserver() {
        manager.registerObserver(serverGUI);
        manager.removeObserver(serverGUI);
        assertEquals(0, manager.getObservers().size());
    }

}


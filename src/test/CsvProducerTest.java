package test;

import com.company.exceptions.InvalidItemNameException;
import com.company.model.Item;
import com.company.model.Manager;
import com.company.model.Order;
import com.company.model.SharedObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.company.producer.CsvProducer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class CsvProducerTest {


    private CsvProducer csvProducer;
    private Order completeOrder;
    private SharedObject sharedObject;
    private Manager manager;

    @BeforeEach
    void setUp() throws InvalidItemNameException {

        sharedObject = new SharedObject();
        manager = new Manager(sharedObject);
        completeOrder = new Order();

        Item itemChoc = new Item("Hot Chocolate", "A hot chocolate", "Hot", 2.50, 3, 20);
        Item itemSandwich = new Item("Ham Sandwich", "A ham sandwich", "Sandwiches", 5.0, 2, 15);

        List<Item> twoItems = new ArrayList<>();
        twoItems.add(itemChoc);
        twoItems.add(itemSandwich);

        completeOrder.setCustomerID(1);
        completeOrder.setTimestamp("2016-11-09T11:44:44.797");
        completeOrder.setName("Peter Parker");
        completeOrder.setDiscountPrice((float) 7.50);
        completeOrder.setItems(twoItems);

        try {
            csvProducer = new CsvProducer(sharedObject, manager.getMenu());
        } catch (IOException e) {
            e.printStackTrace();
        }

        sharedObject.setManager(manager);
    }

    @Test
    void testAddOrderToNormalOrdersSH() {
        manager.removeServers(0);
        //manager.removeBaristas(0);
        csvProducer.addOrderToNormalOrdersSH(completeOrder);
        assertEquals(1, sharedObject.getNormalOrders().size());
    }

    @Test
    void testSetProcessingSpeed() {
        csvProducer.setProcessingSpeed(5);
        assertEquals(5, csvProducer.getProcessingSpeed());
    }


}

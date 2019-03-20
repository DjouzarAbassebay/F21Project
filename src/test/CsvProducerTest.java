
package test;

import com.company.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class CsvProducerTest {


    private CsvProducer csvProducer;
    private Order completeOrder;
    private SharedObject sharedObject;

    @BeforeEach
    void setUp() throws InvalidItemNameException {

        sharedObject = new SharedObject();
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
        csvProducer = new CsvProducer(sharedObject);
    }

    @Test
    void testaddOrderToSharedObject() throws IOException {
        csvProducer.addOrderToSharedObject(completeOrder);
        assertEquals(1, csvProducer.getSharedObject().getOrders().size());
    }
}


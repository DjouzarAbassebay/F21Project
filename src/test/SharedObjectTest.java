package test;

import com.company.InvalidItemNameException;
import com.company.Item;
import com.company.Order;
import com.company.SharedObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class SharedObjectTest {

    private Order Order1;
    private Order Order2;
    private SharedObject sharedObject;

    @BeforeEach
    void setUp() throws InvalidItemNameException {

        sharedObject = new SharedObject();
        Order1= new Order();
        Order2= new Order();
        Item itemChoc = new Item("Hot Chocolate", "A hot chocolate", "Hot", 2.50, 3, 20);
        Item itemSandwich = new Item("Ham Sandwich", "A ham sandwich", "Sandwiches", 5.0, 2, 15);
        List<Item> twoItems = new ArrayList<>();
        twoItems.add(itemChoc);
        twoItems.add(itemSandwich);
        Order1.setCustomerID(1);
        Order1.setTimestamp("2016-11-09T11:44:44.797");
        Order1.setName("Peter Parker");
        Order1.setDiscountPrice((float) 7.50);
        Order1.setItems(twoItems);

        Order2.setCustomerID(2);
        Order2.setTimestamp("2016-11-09T11:44:44.797");
        Order2.setName("Peter Parker");
        Order2.setDiscountPrice((float) 7.50);
        Order2.setItems(twoItems);

    }
    @Test
    void testAddOrder(){


       sharedObject.addOrder(Order1);
        assertEquals(1, sharedObject.getOrders().size());
    }

    @Test
    void testGetNextOrderRemove()
    {

        sharedObject.addOrder(Order1);
        sharedObject.addOrder(Order2);
        sharedObject.getNextOrder();
        assertEquals(1, sharedObject.getOrders().size());


    }

    @Test
    void testGetNextorderValue()
    {
        sharedObject.addOrder(Order1);
        sharedObject.addOrder(Order2);
        sharedObject.getNextOrder();
        assertTrue(sharedObject.getOrders().contains(Order2));
    }

}



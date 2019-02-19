package test;

import com.company.InvalidItemNameException;
import com.company.Item;
import com.company.Manager;
import com.company.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ManagerTest {

    private Manager managerWithCurrentOrder;
    private Order completeOrder;

    @BeforeEach
    void setUp() throws InvalidItemNameException {
        Order orderSimpleItem = new Order();
        Order orderDoubleItem = new Order();
        Order orderTwoItems = new Order();
        completeOrder = new Order();

        Item itemChoc = new Item("Hot Chocolate", "A hot chocolate", "Beverage",2.50, 3);
        Item itemSandwich = new Item("Ham Sandwich","A ham sandwich","Food",5.0, 2);
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
        completeOrder.setDiscountPrice((float) 7.50);
        completeOrder.setItems(twoItems);

        managerWithCurrentOrder = new Manager();

        managerWithCurrentOrder.setCurrentOrder(completeOrder);


    }

    @Test
    void testCopyOrder() {
        Order copy = Manager.copyOrder(completeOrder);
        assertEquals(completeOrder, copy);
        assertNotSame(completeOrder, copy);
    }


    @Test
    void testValidateCurrentOrder() {
        managerWithCurrentOrder.validateCurrentOrder();
        List<Order> orders = managerWithCurrentOrder.getOrders();
        Order lastOrder = orders.get(orders.size()-1);
        assertEquals(1, orders.size());
        assertSame(completeOrder, lastOrder);
        assertNotEquals(completeOrder, lastOrder);
        assertEquals(1, lastOrder.getCustomerID());

    }


}
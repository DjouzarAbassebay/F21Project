package test;

import com.company.Item;
import com.company.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OrderTest {

    Order emptyOrder;
    Order orderSimpleItem;
    Order orderDoubleItem;
    Order orderTwoItems;

    Item itemChoc;


    @BeforeEach
    void setUp() {
        emptyOrder = new Order();
        orderSimpleItem = new Order();
        orderDoubleItem = new Order();
        orderTwoItems = new Order();

        itemChoc = new Item("Hot Chocolate","Beverage",(float)2.50, 3);
        Item itemSandwich = new Item("Ham Sandwich","Food",(float)5.0, 2);
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
    }


    @Test
    void testAddItem(){
        emptyOrder.addItem(itemChoc);
        assertEquals(1, emptyOrder.getItems().size());
        assertEquals((float) 2.50, emptyOrder.getPrice());
    }


    @Test
    void testRemoveItem(){
        orderSimpleItem.removeItem(itemChoc);
        assertEquals(0, orderSimpleItem.getItems().size());
        assertEquals(0, orderSimpleItem.getPrice());
    }

    @Test
    void testRemoveItemWhenDouble() {
        orderDoubleItem.removeItem(itemChoc);
        assertEquals(1, orderDoubleItem.getItems().size());
        assertEquals((float) 2.50, orderDoubleItem.getPrice());
    }

    @Test
    void testRemoveItemWhenNotIn() {
        emptyOrder.removeItem(itemChoc);
        assertEquals(0, emptyOrder.getItems().size());
        assertEquals(0, emptyOrder.getPrice());
    }


    @Test
    void testApplyDiscount() {
        fail("Not implemented yet");
    }


    @Test
    void testCalculatePrice() {
        orderTwoItems.calculatePrice();
        assertEquals(7.50, orderTwoItems.getPrice());
    }

    @Test
    void testCalculatePriceEmpty() {
        emptyOrder.calculatePrice();
        assertEquals(0, emptyOrder.getPrice());
    }
}

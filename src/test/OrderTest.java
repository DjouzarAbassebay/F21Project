package test;

import com.company.InvalidItemNameException;
import com.company.Item;
import com.company.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    private Order emptyOrder;
    private Order orderSimpleItem;
    private Order orderDoubleItem;
    private Order orderTwoItems;

    private Item itemChoc;


    @BeforeEach
    void setUp() throws InvalidItemNameException {
        emptyOrder = new Order();
        orderSimpleItem = new Order();
        orderDoubleItem = new Order();
        orderTwoItems = new Order();

        itemChoc = new Item("Hot Chocolate", "A hot chocolate", "Beverage",2.50, 3);
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
    }


    @Test
    void testAddItem(){
        emptyOrder.addItem(itemChoc);
        assertEquals(1, emptyOrder.getItems().size());
        assertEquals(2.50, emptyOrder.getDiscountPrice());
    }

    @Test
    void testRemoveItem(){
        orderSimpleItem.removeItem(0);
        assertEquals(0, orderSimpleItem.getItems().size());
        assertEquals(0, orderSimpleItem.getDiscountPrice());
    }

    @Test
    void testRemoveItemWhenDouble() {
        orderDoubleItem.removeItem(0);
        assertEquals(1, orderDoubleItem.getItems().size());
        assertEquals((float) 2.50, orderDoubleItem.getDiscountPrice());
    }

    @Test
    void testRemoveItemWhenNotIn() {
        emptyOrder.removeItem(0);
        assertEquals(0, emptyOrder.getItems().size());
        assertEquals(0, emptyOrder.getDiscountPrice());
    }


    @Test
    void testApplyDiscount() {
        fail("Not implemented yet");
    }


    @Test
    void testCalculatePrice() {
        orderTwoItems.calculatePrice();
        assertEquals(7.50, orderTwoItems.getInitialPrice());
    }

    @Test
    void testCalculatePriceEmpty() {
        emptyOrder.calculatePrice();
        assertEquals(0, emptyOrder.getInitialPrice());
    }
}

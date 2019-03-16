/*package test;

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

    // Discount for a menu : 1 sandwich, 1 pastry, 1 cold drink
    private Order orderMenu; // 1 sandwich, 1 pastry, 1 cold drink
    private Order orderMenuPlus; // 1 sandwich, 1 pastry, 1 cold drink + 1 sandwich

    // Discount for 3 sandwiches
    private Order orderSandwiches;  // 3 sandwiches
    private Order orderSandwichesPlus;  // 3 sandwiches + 1 sandwich

    // Discount for 3 pastries
    private Order orderPastries;  // 3 pastries
    private Order orderPastriesPlus;  // 3 pastries + 1 pastry

    // Discount for 4 hot drinks or more
    private Order orderHotDrinks;  // 4 hot drinks
    private Order orderHotDrinksPlus; // 4 hot drinks + 1 hot drink
    private Order orderMixDrinks; // 4 drinks + 1 cold drink

    private Item itemChoc;


    @BeforeEach
    void setUp() throws InvalidItemNameException {

        emptyOrder = new Order();
        orderSimpleItem = new Order();
        orderDoubleItem = new Order();
        orderTwoItems = new Order();

        orderMenu = new Order();
        orderMenuPlus = new Order();

        orderSandwiches = new Order();
        orderSandwichesPlus = new Order();

        orderPastries = new Order();
        orderPastriesPlus = new Order();

        orderHotDrinks = new Order();
        orderHotDrinksPlus = new Order();
        orderMixDrinks = new Order();

        itemChoc = new Item("Hot Chocolate", "A hot chocolate", "Hot",2.50, 3, 20);

        Item itemSandwich = new Item("Ham Sandwich","A ham sandwich","Sandwiches",5.0, 2, 25);
        Item itemPastry = new Item("Muffins","Chocolate muffins","Pastry",2.0, 3, 15);
        Item itemColdDrink = new Item("Orange juice","An orange juice","Cold",1.0, 5, 10);
        Item itemHotDrink = new Item("Lemon tea","A lemon tea","Hot",5.0, 4, 15);

        List<Item> simpleItemChoc = new ArrayList<>();
        List<Item> doubleItemChoc = new ArrayList<>();
        List<Item> twoItems = new ArrayList<>();

        List<Item> menuItems = new ArrayList<>();
        List<Item> menuPlusItems = new ArrayList<>();

        List<Item> sandwichesItems = new ArrayList<>();
        List<Item> sandwichesPlusItems = new ArrayList<>();

        List<Item> pastriesItems = new ArrayList<>();
        List<Item> pastriesPlusItems = new ArrayList<>();

        List<Item> hotDrinksItems = new ArrayList<>();
        List<Item> hotDrinksPlusItems = new ArrayList<>();
        List<Item> mixDrinksItems = new ArrayList<>();

        simpleItemChoc.add(itemChoc);
        doubleItemChoc.add(itemChoc);
        doubleItemChoc.add(itemChoc);
        twoItems.add(itemChoc);
        twoItems.add(itemSandwich);

        // For the menu discount
        menuItems.add(itemSandwich);
        menuItems.add(itemPastry);
        menuItems.add(itemColdDrink);

        menuPlusItems.add(itemSandwich);
        menuPlusItems.add(itemPastry);
        menuPlusItems.add(itemColdDrink);
        menuPlusItems.add(itemSandwich);

        // For the "3 sandwiches" discount
        sandwichesItems.add(itemSandwich);
        sandwichesItems.add(itemSandwich);
        sandwichesItems.add(itemSandwich);

        sandwichesPlusItems.add(itemSandwich);
        sandwichesPlusItems.add(itemSandwich);
        sandwichesPlusItems.add(itemSandwich);
        sandwichesPlusItems.add(itemSandwich);

        // For the "3 pastries" discount
        pastriesItems.add(itemPastry);
        pastriesItems.add(itemPastry);
        pastriesItems.add(itemPastry);

        pastriesPlusItems.add(itemPastry);
        pastriesPlusItems.add(itemPastry);
        pastriesPlusItems.add(itemPastry);
        pastriesPlusItems.add(itemPastry);

        // For the "4 hot drinks or more" discount
        hotDrinksItems.add(itemHotDrink);
        hotDrinksItems.add(itemHotDrink);
        hotDrinksItems.add(itemHotDrink);
        hotDrinksItems.add(itemHotDrink);

        hotDrinksPlusItems.add(itemHotDrink);
        hotDrinksPlusItems.add(itemHotDrink);
        hotDrinksPlusItems.add(itemHotDrink);
        hotDrinksPlusItems.add(itemHotDrink);
        hotDrinksPlusItems.add(itemHotDrink);

        mixDrinksItems.add(itemHotDrink);
        mixDrinksItems.add(itemHotDrink);
        mixDrinksItems.add(itemHotDrink);
        mixDrinksItems.add(itemHotDrink);
        mixDrinksItems.add(itemColdDrink);

        orderSimpleItem.setItems(simpleItemChoc);
        orderDoubleItem.setItems(doubleItemChoc);
        orderTwoItems.setItems(twoItems);

        orderMenu.setItems(menuItems);
        orderMenuPlus.setItems(menuPlusItems);

        orderSandwiches.setItems(sandwichesItems);
        orderSandwichesPlus.setItems(sandwichesPlusItems);

        orderPastries.setItems(pastriesItems);
        orderPastriesPlus.setItems(pastriesPlusItems);

        orderHotDrinks.setItems(hotDrinksItems);
        orderHotDrinksPlus.setItems(hotDrinksPlusItems);
        orderMixDrinks.setItems(mixDrinksItems);

    }


    @Test
    void testAddItem(){
        emptyOrder.addItem(itemChoc);
        assertEquals(1, emptyOrder.getItems().size());
        assertEquals(2.50, emptyOrder.getInitialPrice());
        assertEquals(2, emptyOrder.getItems().get(0).getStock());
    }

    @Test
    void testRemoveItem(){
        orderSimpleItem.removeItem(0);
        assertEquals(0, orderSimpleItem.getItems().size());
        assertEquals(0, orderSimpleItem.getDiscountPrice());
        assertEquals(4, itemChoc.getStock());
    }

    @Test
    void testRemoveItemWhenDouble() {
        orderDoubleItem.removeItem(0);
        assertEquals(1, orderDoubleItem.getItems().size());
        assertEquals((float) 2.50, orderDoubleItem.getDiscountPrice());
        assertEquals(4, itemChoc.getStock());
    }



    @Test
    void testApplyDiscountForOneMenu() {
        orderMenu.calculatePrice();
        orderMenu.applyDiscount();
        assertEquals(7.0, orderMenu.getDiscountPrice());
    }

    @Test
    void testApplyDiscountForMenuPlus() {
        orderMenuPlus.calculatePrice();
        orderMenuPlus.applyDiscount();
        assertEquals(13.0, orderMenuPlus.getDiscountPrice());
    }

    @Test
    void testApplyDiscountForThreeSandwiches() {
        orderSandwiches.calculatePrice();
        orderSandwiches.applyDiscount();
        assertEquals(13.0, orderSandwiches.getDiscountPrice());
    }

    @Test
    void testApplyDiscountForThreeSandwichesPlus() {
        orderSandwichesPlus.calculatePrice();
        orderSandwichesPlus.applyDiscount();
        assertEquals(20.0, orderSandwichesPlus.getDiscountPrice());
    }

    @Test
    void testApplyDiscountForThreePastries() {
        orderPastries.calculatePrice();
        orderPastries.applyDiscount();
        assertEquals(5.0, orderPastries.getDiscountPrice());
    }

    @Test
    void testApplyDiscountForThreePastriesPlus() {
        orderPastriesPlus.calculatePrice();
        orderPastriesPlus.applyDiscount();
        assertEquals(8.0, orderPastriesPlus.getDiscountPrice());
    }

    @Test
    void testApplyDiscountForHotDrinks() {
        orderHotDrinks.calculatePrice();
        orderHotDrinks.applyDiscount();
        assertEquals(18.0, orderHotDrinks.getDiscountPrice());
    }

    @Test
    void testApplyDiscountForHotDrinksPlus() {
        orderHotDrinksPlus.calculatePrice();
        orderHotDrinksPlus.applyDiscount();
        assertEquals(22.5, orderHotDrinksPlus.getDiscountPrice());
    }

    @Test
    void testApplyDiscountForMixDrinks() {
        orderMixDrinks.calculatePrice();
        orderMixDrinks.applyDiscount();
        assertEquals(21.0, orderMixDrinks.getDiscountPrice());
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
*/
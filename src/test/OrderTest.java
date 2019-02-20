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

    // Discount for a menu : 1 sandwich, 1 pastry, 1 cold drink
    private Order orderMenu; // 1 sandwich, 1 pastry, 1 cold drink
    private Order orderMenuTwice; // (1 sandwich, 1 pastry, 1 cold drink) x2
    private Order orderMenuPlus; // 1 sandwich, 1 pastry, 1 cold drink + 1 sandwich

    // Discount for 3 sandwiches
    private Order orderSandwiches;  // 3 sandwiches
    private Order orderSandwichesTwice;  // (3 sandwiches) x2
    private Order orderSandwichesPlus;  // 3 sandwiches + 1 sandwich

    // Discount for 3 pastries
    private Order orderPastries;  // 3 pastries
    private Order orderPastriesTwice;  // (3 pastries) x2
    private Order orderPastriesPlus;  // 3 pastries + 1 pastry

    // Discount for 4 drinks or more
    private Order orderColdDrinks;  // 4 cold drinks
    private Order orderHotDrinks;  // 4 hot drinks
    private Order orderMixDrinks;  // 4 drinks (mix cold and hot)
    private Order orderMixDrinksPlus; // 4 drinks (mix cold and hot) + 1 drink (whatever hot)

    // Order with all types of discounts :
    // 1 menu + 3 sandwiches + 3 pastries + 4 drinks
    private Order orderAllDiscounts;

    private Item itemChoc;


    @BeforeEach
    void setUp() throws InvalidItemNameException {

        emptyOrder = new Order();
        orderSimpleItem = new Order();
        orderDoubleItem = new Order();
        orderTwoItems = new Order();

        orderMenu = new Order();
        orderMenuTwice = new Order();
        orderMenuPlus = new Order();

        orderSandwiches = new Order();
        orderSandwichesTwice = new Order();
        orderSandwichesPlus = new Order();

        orderPastries = new Order();
        orderPastriesTwice = new Order();
        orderPastriesPlus = new Order();

        orderColdDrinks = new Order();
        orderHotDrinks = new Order();
        orderMixDrinks = new Order();
        orderMixDrinksPlus = new Order();

        orderAllDiscounts = new Order();

        itemChoc = new Item("Hot Chocolate", "A hot chocolate", "Hot",2.50, 3);

        Item itemSandwich = new Item("Ham Sandwich","A ham sandwich","Sandwiches",5.0, 2);
        Item itemPastry = new Item("Muffins","Chocolate muffins","Pastry",2.0, 3);
        Item itemColdDrink = new Item("Orange juice","An orange juice","Cold",1.5, 5);
        Item itemHotDrink = new Item("Lemon tea","A lemon tea","Hot",5.0, 4);

        List<Item> simpleItemChoc = new ArrayList<>();
        List<Item> doubleItemChoc = new ArrayList<>();
        List<Item> twoItems = new ArrayList<>();

        List<Item> menuItems = new ArrayList<>();
        List<Item> menuTwiceItems = new ArrayList<>();
        List<Item> menuPlusItems = new ArrayList<>();

        List<Item> sandwichesItems = new ArrayList<>();
        List<Item> sandwichesTwiceItems = new ArrayList<>();
        List<Item> sandwichesPlusItems = new ArrayList<>();

        List<Item> pastriesItems = new ArrayList<>();
        List<Item> pastriesTwiceItems = new ArrayList<>();
        List<Item> pastriesPlusItems = new ArrayList<>();

        List<Item> coldDrinksItems = new ArrayList<>();
        List<Item> hotDrinksItems = new ArrayList<>();
        List<Item> mixDrinksItems = new ArrayList<>();
        List<Item> mixDrinksPlusItems = new ArrayList<>();

        List<Item> allDiscountsItems = new ArrayList<>();

        simpleItemChoc.add(itemChoc);
        doubleItemChoc.add(itemChoc);
        doubleItemChoc.add(itemChoc);
        twoItems.add(itemChoc);
        twoItems.add(itemSandwich);

        // For the menu discount
        menuItems.add(itemSandwich);
        menuItems.add(itemPastry);
        menuItems.add(itemHotDrink);

        menuTwiceItems.add(itemSandwich);
        menuTwiceItems.add(itemPastry);
        menuTwiceItems.add(itemHotDrink);
        menuTwiceItems.add(itemSandwich);
        menuTwiceItems.add(itemPastry);
        menuTwiceItems.add(itemHotDrink);

        menuPlusItems.add(itemSandwich);
        menuPlusItems.add(itemPastry);
        menuPlusItems.add(itemHotDrink);
        menuPlusItems.add(itemSandwich);

        // For the "3 sandwiches" discount
        sandwichesItems.add(itemSandwich);
        sandwichesItems.add(itemSandwich);
        sandwichesItems.add(itemSandwich);

        sandwichesTwiceItems.add(itemSandwich);
        sandwichesTwiceItems.add(itemSandwich);
        sandwichesTwiceItems.add(itemSandwich);
        sandwichesTwiceItems.add(itemSandwich);
        sandwichesTwiceItems.add(itemSandwich);
        sandwichesTwiceItems.add(itemSandwich);

        sandwichesPlusItems.add(itemSandwich);
        sandwichesPlusItems.add(itemSandwich);
        sandwichesPlusItems.add(itemSandwich);
        sandwichesPlusItems.add(itemSandwich);

        // For the "3 pastries" discount
        pastriesItems.add(itemPastry);
        pastriesItems.add(itemPastry);
        pastriesItems.add(itemPastry);

        pastriesTwiceItems.add(itemPastry);
        pastriesTwiceItems.add(itemPastry);
        pastriesTwiceItems.add(itemPastry);
        pastriesTwiceItems.add(itemPastry);
        pastriesTwiceItems.add(itemPastry);
        pastriesTwiceItems.add(itemPastry);

        pastriesPlusItems.add(itemPastry);
        pastriesPlusItems.add(itemPastry);
        pastriesPlusItems.add(itemPastry);
        pastriesPlusItems.add(itemPastry);

        // For the "4 drinks or more" discount
        coldDrinksItems.add(itemColdDrink);
        coldDrinksItems.add(itemColdDrink);
        coldDrinksItems.add(itemColdDrink);
        coldDrinksItems.add(itemColdDrink);

        hotDrinksItems.add(itemHotDrink);
        hotDrinksItems.add(itemHotDrink);
        hotDrinksItems.add(itemHotDrink);
        hotDrinksItems.add(itemHotDrink);

        mixDrinksItems.add(itemHotDrink);
        mixDrinksItems.add(itemColdDrink);
        mixDrinksItems.add(itemHotDrink);
        mixDrinksItems.add(itemColdDrink);

        mixDrinksPlusItems.add(itemHotDrink);
        mixDrinksPlusItems.add(itemColdDrink);
        mixDrinksPlusItems.add(itemHotDrink);
        mixDrinksPlusItems.add(itemColdDrink);
        mixDrinksPlusItems.add(itemHotDrink);

        // For all the discounts
        allDiscountsItems.add(itemSandwich);
        allDiscountsItems.add(itemPastry);
        allDiscountsItems.add(itemColdDrink);
        allDiscountsItems.add(itemSandwich);
        allDiscountsItems.add(itemSandwich);
        allDiscountsItems.add(itemSandwich);
        allDiscountsItems.add(itemPastry);
        allDiscountsItems.add(itemPastry);
        allDiscountsItems.add(itemPastry);
        allDiscountsItems.add(itemColdDrink);
        allDiscountsItems.add(itemColdDrink);
        allDiscountsItems.add(itemColdDrink);
        allDiscountsItems.add(itemColdDrink);


        orderSimpleItem.setItems(simpleItemChoc);
        orderDoubleItem.setItems(doubleItemChoc);
        orderTwoItems.setItems(twoItems);

        orderMenu.setItems(menuItems);
        orderMenuTwice.setItems(menuTwiceItems);
        orderMenuPlus.setItems(menuPlusItems);

        orderSandwiches.setItems(sandwichesItems);
        orderSandwichesTwice.setItems(sandwichesTwiceItems);
        orderSandwichesPlus.setItems(sandwichesPlusItems);

        orderPastries.setItems(pastriesItems);
        orderPastriesTwice.setItems(pastriesTwiceItems);
        orderPastriesPlus.setItems(pastriesPlusItems);

        orderColdDrinks.setItems(coldDrinksItems);
        orderHotDrinks.setItems(hotDrinksItems);
        orderMixDrinks.setItems(mixDrinksItems);
        orderMixDrinksPlus.setItems(mixDrinksPlusItems);

        orderAllDiscounts.setItems(allDiscountsItems);

    }


    @Test
    void testAddItem(){
        emptyOrder.addItem(itemChoc);
        assertEquals(1, emptyOrder.getItems().size());
        assertEquals(2.50, emptyOrder.getInitialPrice());
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

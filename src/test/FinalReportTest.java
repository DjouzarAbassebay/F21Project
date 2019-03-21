
package test;

import com.company.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FinalReportTest {

    private Order orderSimpleItem;
    private Item itemChoc;
    private Manager manager;
    private FinalReport report;

    private SharedObject sharedObject;


    @BeforeEach
    void setUp() throws InvalidItemNameException {

        orderSimpleItem = new Order();
        sharedObject = new SharedObject();
        manager = new Manager(sharedObject);
        sharedObject.setManager(manager);
        report = new FinalReport(sharedObject, manager.getMenu());

        itemChoc = new Item("Expresso", "A hot chocolate", "Hot",2.50, 3,30);
        List<Item> simpleItemChoc = new ArrayList<>();
        simpleItemChoc.add(itemChoc);

        orderSimpleItem.setItems(simpleItemChoc);

        orderSimpleItem.calculatePrice();

        orderSimpleItem.setCustomerID(1);
        orderSimpleItem.setTimestamp("2016-11-09T11:44:44.797");
        orderSimpleItem.setName("Peter Parker");
        orderSimpleItem.setDiscountPrice((float) 0);
        orderSimpleItem.setItems(simpleItemChoc);

        sharedObject.addOrderFromNormalOrders(orderSimpleItem);

    }


    @Test
    void testValidateIncome() {
        report.calculateIncome();
        assertEquals(2.50, report.getIncome());

    }

    @Test
    void testVariationStock() {
        String variation ="";
        variation = report.calculateVariationClass("Hot_001");
        assertEquals("Hot_001 : 0.0", variation);
    }

    }


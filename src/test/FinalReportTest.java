package test;

import com.company.InvalidItemNameException;
import com.company.Item;
import com.company.Manager;
import com.company.Order;
import com.company.FinalReport;
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
    private LinkedList<Order> listOrders;


    @BeforeEach
    void setUp() throws InvalidItemNameException {

        listOrders = new LinkedList<>();
        orderSimpleItem = new Order();
        manager = new Manager();
        report = new FinalReport(manager);
        itemChoc = new Item("Expresso", "A hot chocolate", "Hot",2.50, 3,30);
        List<Item> simpleItemChoc = new ArrayList<>();
        simpleItemChoc.add(itemChoc);

        orderSimpleItem.setItems(simpleItemChoc);

        orderSimpleItem.calculatePrice();
        listOrders.add(orderSimpleItem);

        manager.setOrders(listOrders);
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
        assertEquals("Hot_001 : 1.0", variation);

    }

    }



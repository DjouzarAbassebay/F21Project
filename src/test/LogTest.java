package test;

import com.company.exceptions.InvalidItemNameException;
import com.company.model.Item;
import com.company.model.Order;
import com.company.outputs.Log;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class LogTest {


    private Log logger;
    private Order Order1;

    {
        try {
            logger = new Log();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @BeforeEach
    public void setUp() throws InvalidItemNameException, IOException {
        Order1= new Order();
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
    }

    @Test
    void test_new_oder_log() throws IOException {
        File file = new File(".\\log.txt");
        logger.log_order_new(Order1);
        assertNotEquals(0,file.length());
    }

    @Test
    void test_oder_process_log() throws IOException {
        File file = new File(".\\log.txt");
        logger.log_order_server(Order1, 1);
        assertNotEquals(0,file.length());
    }

    @Test
    void test_barista_log() throws IOException {
        File file = new File(".\\log.txt");
        logger.log_barista(1, 1);
        assertNotEquals(0,file.length());
    }
}
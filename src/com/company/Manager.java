package Package;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Manager {
    private static String ordersPath;

    private static String menuPath;

    private static String reportPath;

    public Map<Object, Item> menu = new HashMap<Object, Item> ();

    public List<Order> orders = new ArrayList<Order> ();

    public void initializeOrders() {
    }

    public void initializeMenu() {
    }

    public void generateReport() {
    }

    public void newOrder() {
    }

    static String getOrdersPath() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return ordersPath;
    }

    static String getMenuPath() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return menuPath;
    }

    public void cancelOrder() {
    }

    public void validateOrder() {
    }

    public void updateStock() {
    }

    static void setMenuPath(String value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        menuPath = value;
    }

    static void setOrdersPath(String value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        ordersPath = value;
    }

    static String getReportPath() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return reportPath;
    }

    static void setReportPath(String value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        reportPath = value;
    }

}

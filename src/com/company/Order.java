package Package;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private int customerID;

    private int timestamp;

    private float price;

    public List<Item> items = new ArrayList<Item> ();

    public void setPrice() {
    }

    public void applyDiscount() {
    }

    public void addItem(Item item) {
    }

    public void removeItem(Item item) {
    }

    int getCustomerID() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.customerID;
    }

    void setCustomerID(int value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.customerID = value;
    }

    int getTimestamp() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.timestamp;
    }

    void setTimestamp(int value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.timestamp = value;
    }

    float getPrice() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.price;
    }

    void setPrice(float value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.price = value;
    }

}

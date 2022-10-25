package model;

import org.json.JSONObject;

import java.util.UUID;

// Represents a skincare item
public class SkincareItem {

    private String brand;           // the brand name of the item
    private String name;            // the name of the item
    private int quantity;           // the quantity of the item
    private double size;            // the size(in fl. oz.) of the item
    private SkincareItemType type;  // the type of the item
    private double cost;            // the buying price(in dollars) of the item
    private double price;           // the selling price(in dollars) of the item
    private String itemID;          // the auto-generated unique ID of the item using UUID from Java Library
    private String description;     // the description of the item
    private boolean isInStock;      // checks whether the item is in stock

    // EFFECTS: constructs a skincare item
    //          if quantity >= 0 then quantity of item is set to quantity, otherwise quantity is zero
    //          isInStock = true if quantity > 0, otherwise false
    public SkincareItem(String brand, String name, int quantity, double size, SkincareItemType type, double cost,
                        double price, String description) {
        this.brand = brand;
        this.name = name;
        this.size = size;
        this.type = type;
        this.cost = cost;
        this.price = price;
        this.itemID = UUID.randomUUID().toString();
        this.description = description;
        if (quantity >= 0) {
            this.quantity = quantity;
        } else {
            this.quantity = 0;
        }
        if (quantity > 0) {
            isInStock = true;
        }
    }

    // EFFECTS: constructs a skincare item for DataReader wit item ID
    //          if quantity >= 0 then quantity of item is set to quantity, otherwise quantity is zero
    //          isInStock = true if quantity > 0, otherwise false
    public SkincareItem(String brand, String name, int quantity, double size, SkincareItemType type, double cost,
                        double price, String itemID, String description) {
        this.brand = brand;
        this.name = name;
        this.size = size;
        this.type = type;
        this.cost = cost;
        this.price = price;
        this.itemID = itemID;
        this.description = description;
        if (quantity >= 0) {
            this.quantity = quantity;
        } else {
            this.quantity = 0;
        }
        if (quantity > 0) {
            isInStock = true;
        }
    }

    // EFFECTS: returns brand name of a skincare item
    public String getBrand() {
        return brand;
    }

    // EFFECTS: returns name of a skincare item
    public String getName() {
        return name;
    }

    // EFFECTS: returns quantity of a skincare item
    public int getQuantity() {
        return quantity;
    }

    // EFFECTS: returns size of a skincare item
    public double getSize() {
        return size;
    }

    // EFFECTS: returns type of skincare item
    public SkincareItemType getType() {
        return type;
    }

    // EFFECTS: returns buying price of a skincare item
    public double getCost() {
        return cost;
    }

    // EFFECTS: returns selling price of a skincare item
    public double getPrice() {
        return price;
    }

    // EFFECTS: returns unique ID of a skincare item
    public String getItemID() {
        return itemID;
    }

    // EFFECTS: returns description of a skincare item
    public String getDescription() {
        return description;
    }

    // EFFECTS: returns true if quantity > 0, false if quantity = 0
    public boolean inStock() {
        return isInStock;
    }

    // MODIFIES: this
    // EFFECTS: sets brand name of a skincare item
    public void setBrand(String brand) {
        this.brand = brand;
    }

    // MODIFIES: this
    // EFFECTS: sets name of a skincare item
    public void setName(String name) {
        this.name = name;
    }

    // MODIFIES: this
    // EFFECTS: sets size of a skincare item
    public void setSize(double size) {
        this.size = size;
    }

    // MODIFIES: this
    // EFFECTS: sets quantity of a skincare item
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // MODIFIES: this
    // EFFECTS: sets type of skincare item
    public void setType(SkincareItemType type) {
        this.type = type;
    }

    // MODIFIES: this
    // EFFECTS: sets buying price of a skincare item
    public void setCost(double cost) {
        this.cost = cost;
    }

    // MODIFIES: this
    // EFFECTS: sets selling price of a skincare item
    public void setPrice(double price) {
        this.price = price;
    }

    // MODIFIES: this
    // EFFECTS: sets description of a skincare item
    public void setDescription(String description) {
        this.description = description;
    }

    // MODIFIES: this
    // EFFECTS: sets unique ID of a skincare item
    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    // MODIFIES: this
    // EFFECTS: updated item with given fields
    public void updateItem(String brand, String name, SkincareItemType type, double size, int quantity, double cost,
                           double price, String description) {
        this.brand = brand;
        this.name = name;
        this.size = size;
        this.type = type;
        this.cost = cost;
        this.price = price;
        this.description = description;
        this.quantity = quantity;
        EventLog.getInstance().logEvent(new Event(
                "Item ID:" + getItemID() + ", Item edited and updated to the inventory."));
    }

    // MODIFIES: this
    // EFFECTS: sets a skincare item to be out of stock
    public void notInStock() {
        isInStock = false;
    }

    // MODIFIES: this
    // EFFECTS: sets a skincare item to be in stock
    public void nowInStock() {
        isInStock = true;
    }

    // REQUIRES: n >= 0
    // MODIFIES: this
    // EFFECTS: adds n to quantity and sets isInStock to true if quantity was 0 before adding n
    public void addQuantity(int n) {
        if (quantity == 0) {
            quantity += n;
            nowInStock();
        } else {
            quantity += n;
        }
    }

    // REQUIRES: 0 <= n && n <= quantity
    // MODIFIES: this
    // EFFECTS: removes n from quantity and sets isInStock to false if quantity is 0 after removing n
    public void removeQuantity(int n) {
        if (quantity == n) {
            quantity -= n;
            notInStock();
        } else {
            quantity -= n;
        }
    }

    // EFFECTS: converts this to JSONObject
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Brand name", brand);
        json.put("Item name", name);
        json.put("Item quantity", quantity);
        json.put("Item size", size);
        json.put("Item type", type);
        json.put("Item cost", cost);
        json.put("Item price", price);
        json.put("Item ID", itemID);
        json.put("Item description", description);
        return json;
    }
}

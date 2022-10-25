package model;


import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Represents a list of skincare items
public class Inventory implements Writable {

    private List<SkincareItem> inventory;

    // EFFECTS: constructs an empty list of skincare item
    public Inventory() {
        inventory = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds item to inventory
    public void addItem(SkincareItem item) {
        inventory.add(item);
        EventLog.getInstance().logEvent(new Event(
                "Item ID:" + item.getItemID() + ", Item added to the inventory."));
    }

    // REQUIRES: item is already in inventory
    // MODIFIES: this
    // EFFECTS: removes item from inventory
    public void removeItem(SkincareItem item) {
        inventory.remove(item);
    }

    // MODIFIES: item
    // EFFECTS: adds n to item quantity in inventory
    public void addItemQuantity(SkincareItem item, int n) {
        item.addQuantity(n);
    }

    // MODIFIES: item
    // EFFECTS: removes n from item quantity in inventory
    public void removeItemQuantity(SkincareItem item, int n) {
        item.removeQuantity(n);
    }

    // MODIFIES: item
    // EFFECTS: edits item brand name to a given newBrandName in inventory
    public void editItemBrand(SkincareItem item, String newBrandName) {
        item.setBrand(newBrandName);
    }

    // MODIFIES: item
    // EFFECTS: edits item name to a given newName in inventory
    public void editItemName(SkincareItem item, String newName) {
        item.setName(newName);
    }

    // MODIFIES: item
    // EFFECTS: edits item size to a given newSize in inventory
    public void editItemSize(SkincareItem item, double newSize) {
        item.setSize(newSize);
    }

    // MODIFIES: item
    // EFFECTS: edits item type to a given newType in inventory
    public void editItemType(SkincareItem item, SkincareItemType newType) {
        item.setType(newType);
    }

    // MODIFIES: item
    // EFFECTS: edits item cost to a given newCost in inventory
    public void editItemCost(SkincareItem item, double newCost) {
        item.setCost(newCost);
    }

    // MODIFIES: item
    // EFFECTS: edits item price to a given newPrice in inventory
    public void editItemPrice(SkincareItem item, double newPrice) {
        item.setPrice(newPrice);
    }

    // MODIFIES: item
    // EFFECTS: edits item description to a given newDescription in inventory
    public void editItemDescription(SkincareItem item, String newDescription) {
        item.setDescription(newDescription);
    }

    // EFFECTS: returns item corresponding to a given id
    public SkincareItem getItemByID(String id) {
        for (SkincareItem item : inventory) {
            if (item.getItemID().equals(id)) {
                return item;
            }
        }
        return null;
    }

    // EFFECTS: returns item corresponding to a given name
    public SkincareItem getItemByName(String name) {
        for (SkincareItem item : inventory) {
            if (item.getName().equals(name)) {
                return item;
            }
        }
        return null;
    }

    // REQUIRES: 0 <= n <= getInventorySize()
    // EFFECTS: returns item corresponding to a given indexNum
    public SkincareItem getItemByIndex(int indexNum) {
        return inventory.get(indexNum);
    }

    // EFFECTS: returns size of inventory
    public int getInventorySize() {
        return inventory.size();
    }

    // EFFECTS: returns inventory
    public List<SkincareItem> getInventory() {
        return inventory;
    }

    // EFFECTS: returns true if inventory contains given item, otherwise, returns false
    public boolean containsItem(SkincareItem item) {
        for (SkincareItem lookUp : inventory) {
            if (lookUp.equals(item)) {
                return true;
            }
        }
        return false;
    }

    // EFFECTS: converts this to JSONObject
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("inventory", itemsToJson());
        return json;
    }

    // EFFECTS: returns SkincareItems in inventory as a JSON array
    private JSONArray itemsToJson() {
        JSONArray items = new JSONArray();
        for (SkincareItem item : inventory) {
            items.put(item.toJson());
        }
        return items;
    }
}

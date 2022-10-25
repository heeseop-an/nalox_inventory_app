package ui;

import model.Inventory;
import model.SkincareItem;
import model.SkincareItemType;
import persistence.DataReader;
import persistence.DataWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// Skincare item inventory application
public class InventoryApp {

    public static final String FILE_LOCATION = "./data/inventory.json";
    private Inventory inventory;
    private Scanner input;
    private DataWriter dataWriter;
    private DataReader dataReader;

    // EFFECTS: runs the inventory application
    public InventoryApp() throws FileNotFoundException {
        dataWriter = new DataWriter(FILE_LOCATION);
        dataReader = new DataReader(FILE_LOCATION);
        runInventory();
    }

    // This method references code from this https://github.students.cs.ubc.ca/CPSC210/TellerApp.git
    // MODIFIES: this
    // EFFECTS: processes user input
    public void runInventory() {
        System.out.println("\nWelcome!");
        boolean keepGoing = true;
        String command;
        init();
        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();
            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("\nHave a great day!");
    }

    // MODIFIES: this
    // EFFECTS: initializes empty inventory
    private void init() {
        inventory = new Inventory();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // This method references code from this https://github.students.cs.ubc.ca/CPSC210/TellerApp.git
    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> add an item");
        System.out.println("\tr -> remove an item");
        System.out.println("\tf -> find an item");
        System.out.println("\te -> edit an item");
        System.out.println("\tv -> view the list of items");
        System.out.println("\tl -> load the list of items");
        System.out.println("\ts -> save the list of items");
        System.out.println("\tq -> quit");
    }

    // EFFECTS: displays menu of fields of item to edit
    private void displayItemFields() {
        System.out.println("\nSelect from:");
        System.out.println("\tb -> edit brand name");
        System.out.println("\tn -> edit item name");
        System.out.println("\tt -> edit item type");
        System.out.println("\ts -> edit item size");
        System.out.println("\tq -> edit item quantity");
        System.out.println("\tc -> edit item cost");
        System.out.println("\tp -> edit item price");
        System.out.println("\td -> edit item description");
    }

    // This method references code from this https://github.students.cs.ubc.ca/CPSC210/TellerApp.git
    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("a")) {
            doAddItem();
        } else if (command.equals("r")) {
            doRemoveItem();
        } else if (command.equals("f")) {
            doGetItem();
        } else if (command.equals("v")) {
            printItems();
        } else if (command.equals("e")) {
            doEditItem();
        } else if (command.equals("l")) {
            doLoadItems();
        } else if (command.equals("s")) {
            doSaveItems();
        } else {
            System.out.println("Selection not valid");
        }
    }

    // MODIFIES: this
    // EFFECTS: adds item to inventory
    private void doAddItem() {
        System.out.print("Enter brand name: ");
        String brandName = input.next();
        System.out.print("Enter item name: ");
        String itemName = input.next();
        System.out.print("Enter item quantity: ");
        int itemQuantity = input.nextInt();
        System.out.print("Enter item size(fl. oz.): ");
        double itemSize = input.nextDouble();
        System.out.println("Enter one of item type from below(Case Sensitive): ");
        System.out.println("FACE, BODY, SUNSCREEN, EYES, LIP, MATERNITY");
        SkincareItemType itemType = SkincareItemType.valueOf(input.next());
        System.out.print("Enter item cost: $");
        double itemCost = input.nextDouble();
        System.out.print("Enter item price: $");
        double itemPrice = input.nextDouble();
        System.out.print("Enter item description: ");
        String itemDescription = input.next();
        SkincareItem newItem = new SkincareItem(brandName, itemName, itemQuantity, itemSize, itemType, itemCost,
                itemPrice, itemDescription);
        inventory.addItem(newItem);
        System.out.println("\nFollowing item has been added to the inventory");
        printItem(newItem);
    }

    // MODIFIES: this
    // EFFECTS: removes item from inventory
    private void doRemoveItem() {
        String selection;
        SkincareItem foundItem = doGetItem();
        System.out.println("\nWould you like to remove the item? Enter 'y' or 'n' for Yes/No");
        selection = input.next();
        selection = selection.toLowerCase();
        if (selection.equals("y")) {
            System.out.println("\nFollowing item has been successfully removed from the inventory");
            printItem(foundItem);
            inventory.removeItem(foundItem);
        }
    }

    // This method references code from this https://github.students.cs.ubc.ca/CPSC210/TellerApp.git
    // EFFECTS: returns and prints item
    private SkincareItem doGetItem() {
        String selection = "";
        while (!(selection.equals("n") || selection.equals("i"))) {
            System.out.println("Press 'n' to search item by name or 'i' to search item by ID");
            selection = input.next();
            selection = selection.toLowerCase();
        }
        if (selection.equals("n")) {
            System.out.println("Enter the item name: ");
            String itemName = input.next();
            SkincareItem foundItem = inventory.getItemByName(itemName);
            printItem(foundItem);
            return foundItem;
        } else {
            System.out.println("Enter the item ID: ");
            String itemID = input.next();
            SkincareItem foundItem = inventory.getItemByID(itemID);
            printItem(foundItem);
            return foundItem;
        }
    }

    // MODIFIES: selectedItem
    // EFFECTS: edits selectedItem
    private void doEditItem() {
        String selection;
        SkincareItem selectedItem = doGetItem();
        displayItemFields();
        selection = input.next();
        if (selection.equals("b")) {
            doEditItemBrand(selectedItem);
        } else if (selection.equals("n")) {
            doEditItemName(selectedItem);
        } else if (selection.equals("t")) {
            doEditItemType(selectedItem);
        } else if (selection.equals("s")) {
            doEditItemSize(selectedItem);
        } else if (selection.equals("q")) {
            doEditItemQuantity(selectedItem);
        } else if (selection.equals("c")) {
            doEditItemCost(selectedItem);
        } else if (selection.equals("p")) {
            doEditItemPrice(selectedItem);
        } else if (selection.equals("d")) {
            doEditItemDescription(selectedItem);
        } else {
            System.out.println("Selection not valid");
        }
    }

    // MODIFIES: item
    // EFFECTS: edits brand name of a given item
    private void doEditItemBrand(SkincareItem item) {
        System.out.println("Enter new brand name: ");
        String newBrandName = input.next();
        inventory.editItemBrand(item, newBrandName);
        System.out.println("Following item's brand name has been updated");
        printItem(item);
    }

    // MODIFIES: item
    // EFFECTS: edits name of a given item
    private void doEditItemName(SkincareItem item) {
        System.out.println("Enter new item name: ");
        String newItemName = input.next();
        inventory.editItemName(item, newItemName);
        System.out.println("Following item's name has been updated");
        printItem(item);
    }

    // MODIFIES: item
    // EFFECTS: edits size of a given item
    private void doEditItemSize(SkincareItem item) {
        System.out.println("Enter new item size(fl. oz.): ");
        double newItemSize = input.nextDouble();
        inventory.editItemSize(item, newItemSize);
        System.out.println("Following item's name has been updated");
        printItem(item);
    }

    // MODIFIES: item
    // EFFECTS: edits quantity of a given item
    private void doEditItemQuantity(SkincareItem item) {
        String selection;
        System.out.println("Press 'a' to add item quantity or 'r' to remove item quantity");
        selection = input.next();
        if (selection.equals("a")) {
            System.out.println("Enter amount to add: ");
            int amountToAdd = input.nextInt();
            inventory.addItemQuantity(item, amountToAdd);
            System.out.println("Following item's quantity has been updated");
            printItem(item);
        } else {
            System.out.println("Enter amount to remove: ");
            int amountToRemove = input.nextInt();
            inventory.removeItemQuantity(item, amountToRemove);
            System.out.println("Following item's quantity has been updated");
            printItem(item);
        }
    }

    // MODIFIES: item
    // EFFECTS: edits type of given item
    private void doEditItemType(SkincareItem item) {
        System.out.println("Enter new item type from one of below(Case Sensitive): ");
        System.out.println("FACE, BODY, SUNSCREEN, EYES, LIP, MATERNITY");
        SkincareItemType newItemType = SkincareItemType.valueOf(input.next());
        inventory.editItemType(item, newItemType);
        System.out.println("Following item's type has been updated");
        printItem(item);
    }

    // MODIFIES: item
    // EFFECTS: edits cost of a given item
    private void doEditItemCost(SkincareItem item) {
        System.out.println("Enter new item cost: $");
        double newItemCost = input.nextDouble();
        inventory.editItemCost(item, newItemCost);
        System.out.println("Following item's cost has been updated");
        printItem(item);
    }

    // MODIFIES: item
    // EFFECTS: edits price of a given item
    private void doEditItemPrice(SkincareItem item) {
        System.out.println("Enter new item price: $");
        double newItemPrice = input.nextDouble();
        inventory.editItemPrice(item, newItemPrice);
        System.out.println("Following item's price has been updated");
        printItem(item);
    }

    // MODIFIES: item
    // EFFECTS: edits description of a given item
    private void doEditItemDescription(SkincareItem item) {
        System.out.println("Enter new item description: ");
        String newItemDescription = input.next();
        inventory.editItemDescription(item, newItemDescription);
        System.out.println("Following item's description has been updated");
        printItem(item);
    }

    // This method references code from this https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    // MODIFIES: this
    // EFFECTS: loads inventory from JSON file
    private void doLoadItems() {
        try {
            inventory = dataReader.read();
            System.out.println("Loaded inventory from " + FILE_LOCATION);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + FILE_LOCATION);
        }
    }

    // This method references code from this https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    // EFFECTS: saves inventory to JSON file
    private void doSaveItems() {
        try {
            dataWriter.open();
            dataWriter.write(inventory);
            dataWriter.close();
            System.out.println("Saved inventory to " + FILE_LOCATION);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + FILE_LOCATION);
        }
    }

    // EFFECTS: prints items in the inventory to the screen
    private void printItems() {
        if (inventory.getInventorySize() == 0) {
            System.out.println("No items in the inventory");
        } else {
            for (int i = 0; i < inventory.getInventorySize(); i++) {
                SkincareItem item = inventory.getItemByIndex(i);
                printItem(item);
            }
        }
    }

    // EFFECTS: prints item to the screen
    private void printItem(SkincareItem item) {
        System.out.println("\nBrand Name: " + item.getBrand());
        System.out.println("Item Name: " + item.getName());
        System.out.println("Item ID: " + item.getItemID());
        System.out.println("Item Type: " + item.getType().toString());
        System.out.println("Item Size: " + item.getSize() + "fl. oz.");
        System.out.println("Item Quantity: " + item.getQuantity());
        System.out.println("Item Cost: $" + item.getCost());
        System.out.println("Item Price: $" + item.getPrice());
        System.out.println("Item Description: " + item.getDescription());
    }
}

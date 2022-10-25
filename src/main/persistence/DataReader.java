package persistence;

import model.Inventory;
import model.SkincareItem;
import model.SkincareItemType;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;


// This class references code from this https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
// Represents a reader that reads inventory from JSON file
public class DataReader {

    private String fileLocation;

    // EFFECTS: constructs reader to read from source file
    public DataReader(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    // EFFECTS: reads inventory from JSON file and returns it
    // throws IOException if an error occurs reading data from file
    public Inventory read() throws IOException {
        String savedData = readFile(fileLocation);
        JSONObject savedJson = new JSONObject(savedData);
        return parseInventory(savedJson);
    }

    // EFFECTS: reads source JSON file as string and returns it
    private String readFile(String fileLocation) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(fileLocation), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();
    }

    // EFFECTS: parses inventory from JSON file and returns it
    private Inventory parseInventory(JSONObject savedJson) {
        Inventory inventory = new Inventory();
        addItems(inventory, savedJson);
        return inventory;
    }

    // MODIFIES: inventory
    // EFFECTS: parses SkincareItems from JSON object and adds them to inventory
    private void addItems(Inventory inventory, JSONObject savedJson) {
        JSONArray savedItems = savedJson.getJSONArray("inventory");
        for (Object savedItem : savedItems) {
            JSONObject parsedItem = (JSONObject) savedItem;
            addItem(inventory, parsedItem);
        }
    }

    // MODIFIES: inventory
    // EFFECTS: parses SkincareItem from JSON object and adds it to Inventory
    private void addItem(Inventory inventory, JSONObject parsedItem) {
        String brand = parsedItem.getString("Brand name");
        String name = parsedItem.getString("Item name");
        int quantity = parsedItem.getInt("Item quantity");
        double size = parsedItem.getDouble("Item size");
        SkincareItemType type = SkincareItemType.valueOf(parsedItem.getString("Item type"));
        double cost = parsedItem.getDouble("Item cost");
        double price = parsedItem.getDouble("Item price");
        String itemID = parsedItem.getString("Item ID");
        String description = parsedItem.getString("Item description");
        SkincareItem item = new SkincareItem(brand, name, quantity, size, type, cost, price, itemID, description);
        inventory.addItem(item);
    }
}

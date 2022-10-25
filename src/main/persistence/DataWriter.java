package persistence;

import model.Inventory;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

// This class references code from this https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
// Represents a writer that writes JSON representation of inventory to file
public class DataWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String fileLocation;

    // EFFECTS: constructs writer to write to fileLocation
    public DataWriter(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if fileLocation file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(fileLocation);
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of inventory to file
    public void write(Inventory inventory) {
        JSONObject json = inventory.toJson();
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    public void saveToFile(String json) {
        writer.print(json);
    }
}

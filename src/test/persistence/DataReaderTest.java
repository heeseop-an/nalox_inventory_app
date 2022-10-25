package persistence;

import model.Inventory;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class DataReaderTest {

    private DataReader testDataReader;
    private Inventory testInventory;

    @Test
    public void testReaderNoFileFound() {
        testDataReader = new DataReader("./data/testReaderNoFileFound.json");
        try {
            testInventory = testDataReader.read();
            fail("Exception is expected");
        } catch (IOException exception) {
            // expected
        }
    }

    @Test
    public void testReaderEmptyInventory() {
        testDataReader = new DataReader("./data/testReaderEmptyInventory.json");
        try {
            testInventory = testDataReader.read();
            assertEquals(0, testInventory.getInventorySize());
        } catch (IOException exception) {
            fail("ERROR: Couldn't read from file");
        }
    }

    @Test
    public void testReaderInventoryWithItems() {
        testDataReader = new DataReader("./data/testReaderInventoryWithItems.json");
        try {
            testInventory = testDataReader.read();
            assertEquals(3, testInventory.getInventorySize());
            assertEquals(18, testInventory.getItemByName("Body Cream").getSize());
            assertEquals(10, testInventory.getItemByIndex(0).getQuantity());
        } catch (IOException e) {
            fail("ERROR: Couldn't read from file");
        }
    }
}
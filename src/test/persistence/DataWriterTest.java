package persistence;

import model.Inventory;
import model.SkincareItem;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static model.SkincareItemType.*;
import static org.junit.jupiter.api.Assertions.*;

public class DataWriterTest {

    private DataWriter testDataWriter;
    private DataReader testJasonReader;
    private Inventory testInventory;
    private SkincareItem testItem1;
    private SkincareItem testItem2;
    private SkincareItem testItem3;

    @Test
    public void testWriterInvalidFile() {
        try {
            testInventory = new Inventory();
            testDataWriter = new DataWriter("./data/my\0illegal:fileName.json");
            testDataWriter.open();
            fail("IOException was expected");
        } catch (IOException exception) {
            // expected
        }
    }

    @Test
    void testWriterEmptyInventory() {
        try {
            testInventory = new Inventory();
            testDataWriter = new DataWriter("./data/testWriterEmptyInventory.json");
            testDataWriter.open();
            testDataWriter.write(testInventory);
            testDataWriter.close();
            testJasonReader = new DataReader("./data/testWriterEmptyInventory.json");
            testInventory = testJasonReader.read();
            assertEquals(0, testInventory.getInventorySize());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterInventoryWithItems() {
        try {
            testInventory = new Inventory();
            testItem1 = new SkincareItem("Neutrogena", "Facial Cream", 10, 1.70, FACE,
                    8.00, 17.01, "good facial cream");
            testItem2 = new SkincareItem("CeraVe", "Body Cream", 0, 18, BODY,
                    6.00, 16.80, "good body cream");
            testItem3 = new SkincareItem("CeraVe", "Sunscreen", 20, 1.7, SUNSCREEN,
                    8.00, 13.97, "good sunscreen");
            testInventory.addItem(testItem1);
            testInventory.addItem(testItem2);
            testInventory.addItem(testItem3);
            testDataWriter = new DataWriter("./data/testWriterInventoryWithItems.json");
            testDataWriter.open();
            testDataWriter.write(testInventory);
            testDataWriter.close();
            testJasonReader = new DataReader("./data/testWriterInventoryWithItems.json");
            testInventory = testJasonReader.read();
            assertEquals(3, testInventory.getInventorySize());
            assertEquals(18, testInventory.getItemByName("Body Cream").getSize());
            assertEquals(10, testInventory.getItemByIndex(0).getQuantity());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}

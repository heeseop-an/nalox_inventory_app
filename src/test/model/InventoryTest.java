package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import static model.SkincareItemType.*;

// Tests for Inventory class
public class InventoryTest {

    private Inventory testInventory;
    private SkincareItem testItem1;
    private SkincareItem testItem2;
    private SkincareItem testItem3;
    private SkincareItem testItem4;
    private SkincareItem testItem5;
    private SkincareItem testItem6;

    @BeforeEach
    public void setUp() {
        testInventory = new Inventory();
        testItem1 = new SkincareItem("Neutrogena", "Facial Cream", 10, 1.70, FACE,
                8.00, 17.01, "good facial cream");
        testItem2 = new SkincareItem("CeraVe", "Body Cream", 0, 18, BODY,
                6.00, 16.80, "good body cream");
        testItem3 = new SkincareItem("CeraVe", "Sunscreen", 20, 1.7, SUNSCREEN,
                8.00, 13.97, "good sunscreen");
        testItem4 = new SkincareItem("CeraVe", "Eye Cream", 100, 3.8, EYES,
                8.00, 17.01, "good eye cream");
        testItem5 = new SkincareItem("Neutrogena", "Lip Cream", 10, 1.5, LIP,
                8.00, 17.01, "good lip cream");
        testItem6 = new SkincareItem("CeraVe", "Stretch Mark", 10, 9.0, MATERNITY,
                8.00, 17.01, "good stretch mark cream");
    }

    @Test
    public void testAddItemToInventory() {
        testInventory.addItem(testItem1);
        testInventory.addItem(testItem2);
        testInventory.addItem(testItem3);

        assertEquals(3, testInventory.getInventorySize());
        assertEquals(3, testInventory.getInventory().size());
        assertTrue(testInventory.containsItem(testItem1));
        assertTrue(testInventory.containsItem(testItem2));
        assertTrue(testInventory.containsItem(testItem3));
    }

    @Test
    public void testRemoveItemToInventory() {
        testInventory.addItem(testItem1);
        testInventory.addItem(testItem2);
        testInventory.addItem(testItem3);

        assertEquals(3, testInventory.getInventorySize());
        testInventory.removeItem(testItem2);
        assertEquals(2, testInventory.getInventorySize());
        assertFalse(testInventory.containsItem(testItem2));
    }

    @Test
    public void testAddQuantityOfItem() {
        testInventory.addItem(testItem2);
        testInventory.addItem(testItem5);

        testInventory.addItemQuantity(testInventory.getItemByName("Body Cream"), 30);
        testInventory.addItemQuantity(testInventory.getItemByName("Lip Cream"), 2);

        assertEquals(30, testInventory.getItemByName("Body Cream").getQuantity());
        assertEquals(12, testInventory.getItemByName("Lip Cream").getQuantity());
    }

    @Test
    public void testRemoveQuantityOfItem() {
        testInventory.addItem(testItem4);
        testInventory.addItem(testItem6);

        testInventory.removeItemQuantity(testInventory.getItemByName("Eye Cream"), 50);
        testInventory.removeItemQuantity(testInventory.getItemByName("Stretch Mark"), 10);

        assertEquals(50, testInventory.getItemByName("Eye Cream").getQuantity());
        assertEquals(0, testInventory.getItemByName("Stretch Mark").getQuantity());
    }

    @Test
    public void testEditItemBrand() {
        testInventory.addItem(testItem4);
        testInventory.addItem(testItem6);

        testInventory.editItemBrand(testInventory.getItemByID(testItem4.getItemID()), "Test Brand Name");
        testInventory.editItemBrand(testInventory.getItemByID(testItem6.getItemID()), "Test Brand Name2");

        assertEquals("Test Brand Name", testInventory.getItemByID(testItem4.getItemID()).getBrand());
        assertEquals("Test Brand Name2", testInventory.getItemByID(testItem6.getItemID()).getBrand());
    }

    @Test
    public void testEditItemName() {
        testInventory.addItem(testItem3);
        testInventory.editItemName(testInventory.getItemByID(testItem3.getItemID()), "Test Item Name");
        assertEquals("Test Item Name", testInventory.getItemByID(testItem3.getItemID()).getName());
    }

    @Test
    public void testEditItemSize() {
        testInventory.addItem(testItem4);
        testInventory.editItemSize(testInventory.getItemByID(testItem4.getItemID()), 10.3);
        assertEquals(10.3, testInventory.getItemByID(testItem4.getItemID()).getSize());
    }

    @Test
    public void testEditItemBType() {
        testInventory.addItem(testItem5);
        testInventory.editItemType(testInventory.getItemByID(testItem5.getItemID()), FACE);
        assertEquals(FACE, testInventory.getItemByID(testItem5.getItemID()).getType());
    }

    @Test
    public void testEditItemCost() {
        testInventory.addItem(testItem6);
        testInventory.editItemCost(testInventory.getItemByID(testItem6.getItemID()), 2);
        assertEquals(2, testInventory.getItemByID(testItem6.getItemID()).getCost());
    }

    @Test
    public void testEditItemPrice() {
        testInventory.addItem(testItem1);
        testInventory.editItemPrice(testInventory.getItemByID(testItem1.getItemID()), 30);
        assertEquals(30, testInventory.getItemByID(testItem1.getItemID()).getPrice());
    }

    @Test
    public void testEditItemDescription() {
        testInventory.addItem(testItem2);
        testInventory.editItemDescription(testInventory.getItemByID(testItem2.getItemID()),
                "The best body cream out there!");
        assertEquals("The best body cream out there!",
                testInventory.getItemByID(testItem2.getItemID()).getDescription());
    }

    @Test
    public void testGetItemByID() {
        SkincareItem testItem7 = new SkincareItem("CeraVe", "Stretch Mark", 10, 9.0,
                MATERNITY, 8.00, 17.01, "good stretch mark cream");
        testItem7.setItemID("e4a2fc49-f3c0-443d-ac4c-9486ec8eba23");
        testInventory.addItem(testItem7);
        assertEquals(testItem7, testInventory.getItemByID("e4a2fc49-f3c0-443d-ac4c-9486ec8eba23"));
        assertNull(testInventory.getItemByID("asd7c382-8fc6-468c-b2c6-6ac778d214a3"));
    }

    @Test
    public void testGetItemByName() {
        testInventory.addItem(testItem3);
        testInventory.addItem(testItem5);

        assertEquals(testItem3, testInventory.getItemByName("Sunscreen"));
        assertNull(testInventory.getItemByName("good cream"));
    }

    @Test
    public void testGetItemByIndex() {
        testInventory.addItem(testItem1);
        testInventory.addItem(testItem3);

        assertEquals(testItem1, testInventory.getItemByIndex(0));
        assertEquals(testItem3, testInventory.getItemByIndex(1));
    }
}

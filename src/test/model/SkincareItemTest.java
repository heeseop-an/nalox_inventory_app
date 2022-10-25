package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static model.SkincareItemType.*;
import static org.junit.jupiter.api.Assertions.*;

// Tests for SkincareItem Class
class SkincareItemTest {
    private SkincareItem testItem1;
    private SkincareItem testItem2;
    private SkincareItem testItem3;
    private SkincareItem testItem4;
    private SkincareItem testItem5;
    private SkincareItem testItem6;
    private SkincareItem testItem7;

    @BeforeEach
    public void setUp() {
        testItem1 = new SkincareItem("Neutrogena", "Facial Cream", 10, 1.70, FACE,
                8.00, 17.01, "good facial cream");
        testItem2 = new SkincareItem("CeraVe", "Body Cream", 0, 18, BODY,
                6.00, 16.80, "good body cream");
        testItem3 = new SkincareItem("CeraVe", "Sunscreen", 20, 1.7, SUNSCREEN,
                8.00, 13.97, "good sunscreen");
        testItem4 = new SkincareItem("CeraVe", "Eye Repair Cream", 100, 3.8, EYES,
                8.00, 17.01, "good eye cream");
        testItem5 = new SkincareItem("Neutrogena", "Lip Cream", -5, 1.5, LIP,
                8.00, 17.01, "good lip cream");
        testItem6 = new SkincareItem("ABC", "lotion", 10, 1.5, BODY,
                8.00, 17.01, "AB32412","gooood");
        testItem7 = new SkincareItem("Brand name", "item name", -3, 1.5, FACE,
                8.00, 17.01, "EFDS2412","great!");
    }

    @Test
    public void testConstructor() {
        assertEquals("Neutrogena", testItem1.getBrand());
        assertEquals("Body Cream", testItem2.getName());
        assertEquals(100, testItem4.getQuantity());
        assertEquals(0, testItem5.getQuantity());
        assertEquals(1.7, testItem3.getSize());
        assertEquals(BODY, testItem2.getType());
        assertEquals(8, testItem3.getCost());
        assertEquals(16.80, testItem2.getPrice());
        assertEquals("good eye cream", testItem4.getDescription());
        assertTrue(testItem1.inStock());
        assertFalse(testItem2.inStock());
    }

    @Test
    public void testConstructorForJson() {
        assertEquals("ABC", testItem6.getBrand());
        assertEquals("Brand name", testItem7.getBrand());
        assertEquals("lotion", testItem6.getName());
        assertEquals(0, testItem7.getQuantity());
        assertEquals("AB32412", testItem6.getItemID());
        assertEquals("EFDS2412", testItem7.getItemID());
        assertTrue(testItem6.inStock());
        assertFalse(testItem7.inStock());

    }

    @Test
    public void testAddItemQuantity() {
        assertTrue(testItem1.inStock());
        assertFalse(testItem2.inStock());

        testItem1.addQuantity(5);
        testItem2.addQuantity(20);

        assertEquals(15, testItem1.getQuantity());
        assertEquals(20, testItem2.getQuantity());

        assertTrue(testItem1.inStock());
        assertTrue(testItem1.inStock());
    }

    @Test
    public void testRemoveItemQuantity() {
        assertTrue(testItem4.inStock());
        assertTrue(testItem3.inStock());

        testItem4.removeQuantity(50);
        testItem3.removeQuantity(20);

        assertEquals(50, testItem4.getQuantity());
        assertEquals(0, testItem3.getQuantity());

        assertTrue(testItem4.inStock());
        assertFalse(testItem3.inStock());
    }

    @Test
    public void testSetItemQuantity() {
        assertTrue(testItem1.inStock());
        assertFalse(testItem2.inStock());

        testItem1.setQuantity(10);
        testItem2.setQuantity(30);

        assertEquals(10, testItem1.getQuantity());
        assertEquals(30, testItem2.getQuantity());
        assertTrue(testItem1.inStock());
        assertTrue(testItem1.inStock());
    }

    @Test
    public void testUpdateItem() {
        assertTrue(testItem1.inStock());
        assertFalse(testItem2.inStock());

        testItem1.updateItem("CeraVe", "Body Cream", BODY, 18, 2,6.00, 16.80,
                "good body cream");

        assertEquals(2, testItem1.getQuantity());
        assertEquals(16.8, testItem1.getPrice());
        assertEquals("CeraVe", testItem1.getBrand());
        assertEquals("Body Cream", testItem1.getName());
        assertEquals(BODY, testItem1.getType());
        assertEquals(18, testItem1.getSize());
        assertEquals(6, testItem1.getCost());
        assertTrue(testItem1.inStock());
    }
}
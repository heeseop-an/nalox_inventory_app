package ui;

import java.io.FileNotFoundException;

// Runs InventoryApp
public class MainUI {
    public static void main(String[] args) {
        try {
            new InventoryApp();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }
}

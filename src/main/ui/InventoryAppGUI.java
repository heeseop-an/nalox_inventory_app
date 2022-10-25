package ui;


import model.Event;
import model.EventLog;
import model.Inventory;
import persistence.DataReader;
import persistence.DataWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

// Represents a main window for InventoryApp GUI
public class InventoryAppGUI {

    private static final int MAIN_WINDOW_WIDTH = 400;
    private static final int MAIN_WINDOW_HEIGHT = 600;
    public static final Color MAIN_COLOR = new Color(50, 52, 52, 255);
    private JFrame mainWindow;
    private JButton addItemButton;
    private JButton viewItemsButton;
    private JButton loadDataFileButton;
    private JButton saveDataAndQuitButton;
    private Inventory inventory = new Inventory();
    private DataReader dataReader;
    private DataWriter dataWriter;

    //EFFECTS: sets logo and buttons on mainWindow
    public InventoryAppGUI() {
        mainWindow = new JFrame("Skincare Item Inventory");
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setLayout(new BoxLayout(mainWindow.getContentPane(), BoxLayout.Y_AXIS));
        mainWindow.getContentPane().setBackground(MAIN_COLOR);
        mainWindow.setSize(MAIN_WINDOW_WIDTH, MAIN_WINDOW_HEIGHT);
        createLogo();
        createAddItemButton();
        createViewItemsButton();
        createLoadDataFileButton();
        createSaveAndQuitButton();
        buttonHandler();
        mainWindow.setLocationRelativeTo(null);
        mainWindow.setVisible(true);
    }

    // EFFECTS: adds logo image to the top of the main window
    public void createLogo() {
        ImageIcon logo = new ImageIcon("./data/logo.png");
        Image logoImage = logo.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        logo = new ImageIcon(logoImage);
        JLabel logoLabel = new JLabel(logo);
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainWindow.add(logoLabel);
    }

    // EFFECTS: creates add item button
    public void createAddItemButton() {
        ImageIcon addItemIcon = new ImageIcon("./data/addItemButton.png");
        Image img = addItemIcon.getImage();
        Image resizedAddItemImg = img.getScaledInstance(300, 50, Image.SCALE_SMOOTH);
        addItemIcon = new ImageIcon(resizedAddItemImg);
        addItemButton = new JButton(addItemIcon);
        addItemButton.setBorderPainted(false);
        addItemButton.setFocusPainted(false);
        addItemButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainWindow.add(addItemButton);
    }

    // EFFECTS: creates view items button
    public void createViewItemsButton() {
        ImageIcon viewItemsIcon = new ImageIcon("./data/viewItemsButton.png");
        Image img = viewItemsIcon.getImage();
        Image resizedViewItemsImg = img.getScaledInstance(300, 50, Image.SCALE_SMOOTH);
        viewItemsIcon = new ImageIcon(resizedViewItemsImg);
        viewItemsButton = new JButton(viewItemsIcon);
        viewItemsButton.setBorderPainted(false);
        viewItemsButton.setFocusPainted(false);
        viewItemsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainWindow.add(viewItemsButton);
    }

    // EFFECTS: creates load data file button
    public void createLoadDataFileButton() {
        ImageIcon loadDataFileIcon = new ImageIcon("./data/loadDataFileButton.png");
        Image img = loadDataFileIcon.getImage();
        Image resizedLoadDataFileImg = img.getScaledInstance(300, 50, Image.SCALE_SMOOTH);
        loadDataFileIcon = new ImageIcon(resizedLoadDataFileImg);
        loadDataFileButton = new JButton(loadDataFileIcon);
        loadDataFileButton.setBorderPainted(false);
        loadDataFileButton.setFocusPainted(false);
        loadDataFileButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainWindow.add(loadDataFileButton);
    }

    // EFFECTS: creates save data and quit button
    public void createSaveAndQuitButton() {
        ImageIcon saveDataAndQuitIcon = new ImageIcon("./data/saveDataAndQuitButton.png");
        Image img = saveDataAndQuitIcon.getImage();
        Image resizedSaveDataAndQuitImg = img.getScaledInstance(300, 50, Image.SCALE_SMOOTH);
        saveDataAndQuitIcon = new ImageIcon(resizedSaveDataAndQuitImg);
        saveDataAndQuitButton = new JButton(saveDataAndQuitIcon);
        saveDataAndQuitButton.setBorderPainted(false);
        saveDataAndQuitButton.setFocusPainted(false);
        saveDataAndQuitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainWindow.add(saveDataAndQuitButton);
    }

    // EFFECTS: initiates button actions
    public void buttonHandler() {
        addItem();
        viewItems();
        loadDataFile();
        saveDataAndQuit();
    }

    // MODIFIES: inventory
    // EFFECTS: handles add item button by initiating SkincareItemAdderGUI
    public void addItem() {
        addItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SkincareItemAdderGUI(inventory);
            }
        });
    }

    // EFFECTS: handles view item button by initiating InventoryGUI
    public void viewItems() {
        viewItemsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new InventoryGUI(inventory, mainWindow);
            }
        });
    }

    // MODIFIES: inventory
    // EFFECTS: loads saved inventory from JSON file
    public void loadDataFile() {
        loadDataFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dataReader = new DataReader(InventoryApp.FILE_LOCATION);
                try {
                    inventory = dataReader.read();
                    if (inventory.getInventorySize() != 0) {
                        JOptionPane.showMessageDialog(mainWindow, "DATA LOADED SUCCESSFULLY.");
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(mainWindow, "NO DATA LOADED.");
                    inventory = new Inventory();
                }
            }
        });
    }

    // EFFECTS: saves inventory data and quits the application
    public void saveDataAndQuit() {
        saveDataAndQuitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dataWriter = new DataWriter(InventoryApp.FILE_LOCATION);
                try {
                    dataWriter.open();
                } catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(mainWindow, "ERROR");
                }
                dataWriter.write(inventory);
                dataWriter.close();
                printLogs(EventLog.getInstance());
                JOptionPane.showMessageDialog(mainWindow, "DATA SAVED SUCCESSFULLY. HAVE A GREAT DAY!");
                mainWindow.dispatchEvent(new WindowEvent(mainWindow, WindowEvent.WINDOW_CLOSING));
            }
        });
    }

    // EFFECTS: prints out event logs on console
    public void printLogs(EventLog el) {
        for (Event next : el) {
            System.out.println(next.getDate() + " - " + next.getDescription());
        }
    }
}

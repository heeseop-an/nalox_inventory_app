package ui;

import model.Inventory;
import model.SkincareItem;
import model.SkincareItemType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

// Represents graphical user interface for adding SkincareItem to inventory
public class SkincareItemAdderGUI {

    private static final int FIELD_COLUMN = 20;
    private static final int ITEM_ADDER_WINDOW_WIDTH = 400;
    private static final int ITEM_ADDER_WINDOW_HEIGHT = 600;
    private JFrame skincareItemAdderWindow;
    private JPanel skincareItemAdderPanel;
    private JTextField brandNameField;
    private JTextField nameField;
    private JComboBox<SkincareItemType> typeField;
    private JTextField sizeField;
    private JTextField quantityField;
    private JTextField costField;
    private JTextField priceField;
    private JTextField descriptionField;

    // EFFECTS: sets item fields and add button on skincareItemAdderWindow
    public SkincareItemAdderGUI(Inventory inventory) {
        skincareItemAdderWindow = new JFrame("Add Item");
        skincareItemAdderWindow.setSize(ITEM_ADDER_WINDOW_WIDTH, ITEM_ADDER_WINDOW_HEIGHT);
        skincareItemAdderWindow.setLayout(new BoxLayout(skincareItemAdderWindow.getContentPane(), BoxLayout.Y_AXIS));
        skincareItemAdderWindow.setLocationRelativeTo(null);
        skincareItemAdderWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        skincareItemAdderPanel = (JPanel) skincareItemAdderWindow.getContentPane();

        addItemFields();
        createAddButton(inventory);

        skincareItemAdderPanel.setVisible(true);
        skincareItemAdderWindow.setVisible(true);
    }

    // EFFECTS: helps to add item fields to skincareItemAdderPanel
    private void addItemFields() {
        brandNameField = itemBrandName();
        nameField = itemName();
        typeField = itemType();
        sizeField = itemSize();
        quantityField = itemQuantity();
        costField = itemCost();
        priceField = itemPrice();
        descriptionField = itemDescription();
    }

    // EFFECTS: adds item brand name JTextField to skincareItemAdderPanel
    private JTextField itemBrandName() {
        JLabel brandNameLabel = new JLabel("Enter Item Brand:");
        JTextField brandName = new JTextField(FIELD_COLUMN);

        skincareItemAdderPanel.add(brandNameLabel);
        skincareItemAdderPanel.add(brandName);

        return brandName;
    }

    // EFFECTS: adds item name JTextField to skincareItemAdderPanel
    private JTextField itemName() {
        JLabel nameLabel = new JLabel("Enter Item Name:");
        JTextField name = new JTextField(FIELD_COLUMN);

        skincareItemAdderPanel.add(nameLabel);
        skincareItemAdderPanel.add(name);

        return name;
    }

    // EFFECTS: adds item type JComboBox to skincareItemAdderPanel
    private JComboBox<SkincareItemType> itemType() {
        JLabel typeLabel = new JLabel("Select Item Type:");
        JComboBox<SkincareItemType> typeOption = new JComboBox<>();
        typeOption.setModel(new DefaultComboBoxModel<>(SkincareItemType.values()));

        skincareItemAdderPanel.add(typeLabel);
        skincareItemAdderPanel.add(typeOption);

        return typeOption;
    }

    // EFFECTS: adds item size JTextField to skincareItemAdderPanel
    private JTextField itemSize() {
        JLabel sizeLabel = new JLabel("Enter Item Size(fl. oz.):");
        JTextField size = new JTextField(FIELD_COLUMN);

        skincareItemAdderPanel.add(sizeLabel);
        skincareItemAdderPanel.add(size);

        return size;
    }

    // EFFECTS: adds item quantity JTextField to skincareItemAdderPanel
    private JTextField itemQuantity() {
        JLabel quantityLabel = new JLabel("Enter Item Quantity:");
        JTextField quantity = new JTextField(FIELD_COLUMN);

        skincareItemAdderPanel.add(quantityLabel);
        skincareItemAdderPanel.add(quantity);

        return quantity;
    }

    // EFFECTS: adds item cost JTextField to skincareItemAdderPanel
    private JTextField itemCost() {
        JLabel costLabel = new JLabel("Enter Item Cost: $");
        JTextField cost = new JTextField(FIELD_COLUMN);

        skincareItemAdderPanel.add(costLabel);
        skincareItemAdderPanel.add(cost);

        return cost;
    }

    // EFFECTS: adds item price JTextField to skincareItemAdderPanel
    private JTextField itemPrice() {
        JLabel priceLabel = new JLabel("Enter Item Price: $");
        JTextField price = new JTextField(FIELD_COLUMN);

        skincareItemAdderPanel.add(priceLabel);
        skincareItemAdderPanel.add(price);

        return price;
    }

    // EFFECTS: adds item description JTextField to skincareItemAdderPanel
    private JTextField itemDescription() {
        JLabel descriptionLabel = new JLabel("Enter Item Description:");
        JTextField description = new JTextField(FIELD_COLUMN);

        skincareItemAdderPanel.add(descriptionLabel);
        skincareItemAdderPanel.add(description);

        return description;
    }

    // MODIFIES: inventory
    // EFFECTS: creates add button and handles button by adding input item
    private void createAddButton(Inventory inventory) {
        JButton addButton = new JButton("ADD");
        skincareItemAdderPanel.add(addButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String brandName = brandNameField.getText();
                String name = nameField.getText();
                int quantity = Integer.parseInt(quantityField.getText());
                double size = Double.parseDouble(sizeField.getText());
                SkincareItemType type = (SkincareItemType) typeField.getSelectedItem();
                double cost = Double.parseDouble(costField.getText());
                double price = Double.parseDouble(priceField.getText());
                String description = descriptionField.getText();

                SkincareItem newItem =
                        new SkincareItem(brandName, name, quantity, size, type, cost, price, description);
                inventory.addItem(newItem);

                JOptionPane.showMessageDialog(skincareItemAdderWindow, "ITEM HAS BEEN SUCCESSFULLY ADDED.");

                skincareItemAdderWindow.dispatchEvent(new WindowEvent(skincareItemAdderWindow,
                        WindowEvent.WINDOW_CLOSING));
            }
        });
    }
}

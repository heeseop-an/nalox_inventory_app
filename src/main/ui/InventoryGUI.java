package ui;

import model.Inventory;
import model.SkincareItem;
import model.SkincareItemType;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


// Represents user interface for viewing list of items
public class InventoryGUI {

    private static final int INVENTORY_WINDOW_WIDTH = 1300;
    private static final int INVENTORY_WINDOW_HEIGHT = 700;
    private static final int INVENTORY_PANE_WIDTH = 900;
    private static final int INVENTORY_PANE_HEIGHT = 625;
    private static final int INVENTORY_PANE_X_POS = 375;
    private static final int INVENTORY_PANE_Y_POS = 25;
    private static final int FIELD_COLUMN = 1000;
    private JFrame inventoryWindow;
    private JTable itemTable;
    private JScrollPane inventoryPane;
    private JTextField idField;
    private JTextField brandField;
    private JTextField nameField;
    private JTextField typeField;
    private JTextField sizeField;
    private JTextField quantityField;
    private JTextField costField;
    private JTextField priceField;
    private JTextField descriptionField;
    private JButton updateItemButton;


    // EFFECTS: sets item fields, update button and list of items on inventoryWindow
    public InventoryGUI(Inventory inventory, JFrame mainWindow) {
        inventoryWindow = new JFrame("Inventory");

        String[] columnNames = new String[]{"Item ID", "Brand Name", "Item Name", "Item Type", "Item Size",
                "Item Quantity", "Item Cost", "Item Price", "Item Description"};
        String[][] data = new String[][]{};
        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
        List<SkincareItem> items = inventory.getInventory();
        if (items.size() == 0) {
            JOptionPane.showMessageDialog(mainWindow, "NO ITEMS IN THE INVENTORY.");
        } else {
            for (SkincareItem i : items) {
                String[] newData = new String[]{i.getItemID(), i.getBrand(), i.getName(), i.getType().toString(),
                        Double.toString(i.getSize()), Integer.toString(i.getQuantity()), Double.toString(i.getCost()),
                        Double.toString(i.getPrice()), i.getDescription()};
                tableModel.addRow(newData);
            }

            itemTable = new JTable(tableModel);
            TableRowSorter<TableModel> sorter = new TableRowSorter<>(itemTable.getModel());
            itemTable.setRowSorter(sorter);
            addItemFields();
            itemClickAction(tableModel);
            createUpdateButton(tableModel, inventory);
            setWindowProperty();
        }
    }

    // EFFECTS: helper to set inventoryWindow property
    private void setWindowProperty() {
        inventoryPane = new JScrollPane(itemTable);
        inventoryPane.setBounds(INVENTORY_PANE_X_POS, INVENTORY_PANE_Y_POS,
                INVENTORY_PANE_WIDTH, INVENTORY_PANE_HEIGHT);
        inventoryWindow.add(inventoryPane);
        inventoryWindow.setSize(INVENTORY_WINDOW_WIDTH, INVENTORY_WINDOW_HEIGHT);
        inventoryWindow.setLocationRelativeTo(null);
        inventoryWindow.setLayout(null);
        inventoryWindow.setVisible(true);
    }

    // EFFECTS: helper to add item fields
    private void addItemFields() {
        createIdField();
        createBrandField();
        createNameField();
        createTypeField();
        createSizeField();
        createQuantityField();
        createCostField();
        createPriceField();
        createDescriptionField();
    }

    // EFFECTS: handles click action by showing fields value of clicked item
    private void itemClickAction(DefaultTableModel tableModel) {
        itemTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int rowNumber = itemTable.getSelectedRow();
                idField.setText((String) tableModel.getValueAt(rowNumber, 0));
                brandField.setText((String) tableModel.getValueAt(rowNumber, 1));
                nameField.setText((String) tableModel.getValueAt(rowNumber, 2));
                typeField.setText((String) tableModel.getValueAt(rowNumber, 3));
                sizeField.setText((String) tableModel.getValueAt(rowNumber, 4));
                quantityField.setText((String) tableModel.getValueAt(rowNumber, 5));
                costField.setText((String) tableModel.getValueAt(rowNumber, 6));
                priceField.setText((String) tableModel.getValueAt(rowNumber, 7));
                descriptionField.setText((String) tableModel.getValueAt(rowNumber, 8));
            }
        });
    }

    // EFFECTS: creates update button
    private void createUpdateButton(DefaultTableModel tableModel, Inventory inventory) {
        updateItemButton = new JButton("UPDATE");
        updateItemButton.setBounds(200, 550, 100, 20);
        inventoryWindow.add(updateItemButton);
        updateItemButtonAction(tableModel, inventory);
    }

    // EFFECTS: handles update button by editing fields of selected item
    private void updateItemButtonAction(DefaultTableModel tableModel, Inventory inventory) {
        updateItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rowNumber = itemTable.getSelectedRow();
                String selectedItemID = (String) tableModel.getValueAt(rowNumber, 0);
                tableModel.setValueAt(brandField.getText(), rowNumber, 1);
                tableModel.setValueAt(nameField.getText(), rowNumber, 2);
                tableModel.setValueAt(typeField.getText(), rowNumber, 3);
                tableModel.setValueAt(sizeField.getText(), rowNumber, 4);
                tableModel.setValueAt(quantityField.getText(), rowNumber, 5);
                tableModel.setValueAt(costField.getText(), rowNumber, 6);
                tableModel.setValueAt(priceField.getText(), rowNumber, 7);
                tableModel.setValueAt(descriptionField.getText(), rowNumber, 8);
                updateSelectedItem(tableModel, inventory, selectedItemID, rowNumber);
            }
        });
    }

    // MODIFIES: inventory and selectedItem
    // EFFECTS: helper to save item with updated fields
    private void updateSelectedItem(DefaultTableModel tableModel, Inventory inventory, String selectedItemID,
                                    int rowNumber) {
        SkincareItem selectedItem = inventory.getItemByID(selectedItemID);
        selectedItem.updateItem(
                (String) tableModel.getValueAt(rowNumber, 1),
                (String) tableModel.getValueAt(rowNumber, 2),
                SkincareItemType.valueOf((String) tableModel.getValueAt(rowNumber, 3)),
                Double.parseDouble((String) tableModel.getValueAt(rowNumber, 4)),
                Integer.parseInt((String) tableModel.getValueAt(rowNumber, 5)),
                Double.parseDouble((String) tableModel.getValueAt(rowNumber, 6)),
                Double.parseDouble((String) tableModel.getValueAt(rowNumber, 7)),
                (String) tableModel.getValueAt(rowNumber, 8));
    }

    // EFFECTS: creates item id field
    private void createIdField() {
        idField = new JTextField(FIELD_COLUMN);
        idField.setBounds(120, 75, 200, 20);
        JLabel idFieldLabel = new JLabel("ID:");
        idFieldLabel.setBounds(25, 75, 150, 20);
        inventoryWindow.add(idFieldLabel);
        inventoryWindow.add(idField);
    }

    // EFFECTS: creates item brand name field
    private void createBrandField() {
        brandField = new JTextField(FIELD_COLUMN);
        brandField.setBounds(120, 125, 200, 20);
        JLabel brandFieldLabel = new JLabel("Brand:");
        brandFieldLabel.setBounds(25, 125, 150, 20);
        inventoryWindow.add(brandFieldLabel);
        inventoryWindow.add(brandField);
    }

    // EFFECTS: creates item name field
    private void createNameField() {
        nameField = new JTextField(FIELD_COLUMN);
        nameField.setBounds(120, 175, 200, 20);
        JLabel nameFieldLabel = new JLabel("Name:");
        nameFieldLabel.setBounds(25, 175, 150, 20);
        inventoryWindow.add(nameFieldLabel);
        inventoryWindow.add(nameField);
    }

    // EFFECTS: creates item type field
    private void createTypeField() {
        typeField = new JTextField(FIELD_COLUMN);
        typeField.setBounds(120, 225, 200, 20);
        JLabel typeFieldLabel = new JLabel("Type:");
        typeFieldLabel.setBounds(25, 225, 150, 20);
        inventoryWindow.add(typeFieldLabel);
        inventoryWindow.add(typeField);
    }

    // EFFECTS: creates item size field
    private void createSizeField() {
        sizeField = new JTextField(FIELD_COLUMN);
        sizeField.setBounds(120, 275, 200, 20);
        JLabel sizeFieldLabel = new JLabel("Size:");
        sizeFieldLabel.setBounds(25, 275, 150, 20);
        inventoryWindow.add(sizeFieldLabel);
        inventoryWindow.add(sizeField);
    }

    // EFFECTS: creates item quantity field
    private void createQuantityField() {
        quantityField = new JTextField(FIELD_COLUMN);
        quantityField.setBounds(120, 325, 200, 20);
        JLabel quantityFieldLabel = new JLabel("Quantity:");
        quantityFieldLabel.setBounds(25, 325, 150, 20);
        inventoryWindow.add(quantityFieldLabel);
        inventoryWindow.add(quantityField);
    }

    // EFFECTS: creates item cost field
    private void createCostField() {
        costField = new JTextField(FIELD_COLUMN);
        costField.setBounds(120, 375, 200, 20);
        JLabel costFieldLabel = new JLabel("Cost:");
        costFieldLabel.setBounds(25, 375, 150, 20);
        inventoryWindow.add(costFieldLabel);
        inventoryWindow.add(costField);
    }

    // EFFECTS: creates item price field
    private void createPriceField() {
        priceField = new JTextField(FIELD_COLUMN);
        priceField.setBounds(120, 425, 200, 20);
        JLabel priceFieldLabel = new JLabel("Price:");
        priceFieldLabel.setBounds(25, 425, 150, 20);
        inventoryWindow.add(priceFieldLabel);
        inventoryWindow.add(priceField);
    }

    // EFFECTS: creates item description field
    private void createDescriptionField() {
        descriptionField = new JTextField(FIELD_COLUMN);
        descriptionField.setBounds(120, 475, 200, 20);
        JLabel descriptionFieldLabel = new JLabel("Description:");
        descriptionFieldLabel.setBounds(25, 475, 150, 20);
        inventoryWindow.add(descriptionFieldLabel);
        inventoryWindow.add(descriptionField);
    }
}

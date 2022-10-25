# Skin Care Product Inventory

Skin Care Product Inventory Application enables users to keep track of inventories of skin care products being sold on a marketplace. Since the inventory application is the Skin Care Product specific, it is suitable for sellers who are selling items in such category. 

Back in the day, I was selling skin care products on Amazon and I remember I was shocked how an inventory software can have many amazing features such as AI sales forecasting and it can benefit one's business in many ways. Although my skill lacks to program such feature, I thought it would be fun to build a simple inventory program on my own taking this opportunity.

### User Stories

As a user, I want to be able to:

- add an item to the inventory
- edit an item's brand, name, quantity, size, type, cost, price, and description
- view the list of items in the inventory with sorting function
- optionally load the list of items in the saved inventory
- automatically save the list of items in the inventory when I quit the application
- print out event logs on console after I quite the application

### Phase 4: Task 2

Sample log:

- Mon Nov 22 20:58:06 PST 2021 - Item ID:a56a7c73-aeb5-4a7e-a06d-8dbeba151d4f, Item added to the inventory.
- Mon Nov 22 20:58:06 PST 2021 - Item ID:d1e3952f-9dc2-4983-b005-e24bc9919584, Item added to the inventory.
- Mon Nov 22 20:58:06 PST 2021 - Item ID:934f6bd9-f287-45b0-aa81-69000b6ac2db, Item added to the inventory.
- Mon Nov 22 20:58:06 PST 2021 - Item ID:558eaf25-a72f-41a0-9790-e84b4c4c8994, Item added to the inventory.
- Mon Nov 22 20:58:06 PST 2021 - Item ID:166039d9-77fc-4967-9a09-e230d33a8bc1, Item added to the inventory.
- Mon Nov 22 20:58:06 PST 2021 - Item ID:ca8a6bee-b138-40b4-b91e-b4f63e1697f3, Item added to the inventory.
- Mon Nov 22 20:58:14 PST 2021 - Item ID:ca8a6bee-b138-40b4-b91e-b4f63e1697f3, Item edited and updated to the inventory.
- Mon Nov 22 20:58:22 PST 2021 - Item ID:166039d9-77fc-4967-9a09-e230d33a8bc1, Item edited and updated to the inventory.
- Mon Nov 22 20:58:30 PST 2021 - Item ID:ee81b839-9e37-4a42-a2c1-b39fff182086, Item added to the inventory.

The sample log shows events related to Skincare Item with unique item ID that enables to identify which item has been logged. 
The first six items have been loaded from file and added to the inventory.
Then the items with ID: ca8a6bee-b138-40b4-b91e-b4f63e1697f3 and ID:166039d9-77fc-4967-9a09-e230d33a8bc1 were edited and updated to the inventory.
The last item was not loaded but added by the user with unique ID which was not seen from the loaded items.

### Phase 4: Task 3

Designs to be improved in the future:

- Coupling is very high in ```InventoryAppGUI``` class and ```InventoryApp``` class since components on panel such as JTextField, JButton, and JTable are manually placed on the window using ```setBounds()``` method. Change in one component will cause a riffle effect because others need to be changed manually in order to keep spacing between components. Inconsistency in formatting will not be caught until I run the GUI. So this design flaw needs to be improved in order to avoid tedious work.
- Method level cohesion can be improved on ```InventoryAppGUI()``` in ```InventoryApp``` class that Inventory JTable is being created inside a constructor method. Creating JTable can be extracted and handled in a separate method in order for the constructor method to keep Single Responsibility Principle.


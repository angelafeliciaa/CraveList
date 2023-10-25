package ui;

import model.Restaurant;
import model.wishlist.RestaurantWishlist;

import javax.swing.*;
//import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

// This class represents what happens when the display wishlist button is clicked from the main menu.
public class DisplayWishlist extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;
    private RestaurantWishlist restaurantList;

    // MODIFIES: this, restaurantList
    // EFFECTS: displays restaurants in the restaurantList
    public DisplayWishlist(RestaurantWishlist restaurantList) {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.restaurantList = restaurantList;

        table = new JTable();
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Restaurant Name");
        tableModel.addColumn("Rating");
        tableModel.addColumn("Category");

        // DISPLAY TO UI EACH RESTAURANT IN WISHLIST
        for (Restaurant r: restaurantList.getRestaurantWishlist()) {
            tableModel.addRow(new Object[]{ r.getName(), r.getRating(), r.getCategory()});
        }
        table.setModel(tableModel);
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

    }
}

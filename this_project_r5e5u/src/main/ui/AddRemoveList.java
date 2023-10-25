package ui;

import model.Restaurant;
import model.RestaurantCategory;
import model.wishlist.RestaurantWishlist;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

// This class represents what happens when the add/remove restaurant button is clicked from the main menu
public class AddRemoveList extends JPanel {
    private JLabel[] labels;

    private static final String addString = "Add";
    private static final String removeString = "Remove";
    private JButton removeButton;
    private JButton addButton;
    private JTextField restaurantName;
    private JSpinner restaurantCategory;
    private JTextField restaurantRating;
    private RestaurantWishlist restaurantList;

    private JPanel buttonPane;
    private JPanel containerPanel;
    private JPanel namePane;
    private JPanel ratingPane;
    private JPanel categoryPane;

    private JTable table;
    private DefaultTableModel tableModel;
    private int row;

    private AddListener addListener;


    // MODIFIES: this, restaurantList
    // EFFECTS: constructs an add or remove panel with add and remove buttons
    public AddRemoveList(RestaurantWishlist restaurantList) {
        super(new BorderLayout());
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.restaurantList = restaurantList;

        table = new JTable();
        tableModel = new DefaultTableModel();
        addColumns();

        for (Restaurant r : restaurantList.getRestaurantWishlist()) {
            tableModel.addRow(new Object[]{r.getName(), r.getRating(), r.getCategory()});
        }

        table.setModel(tableModel);
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);

        // to know which row the user clicked on
        table.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) {
                tableMouseClicked(e);
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);

        setupButtons();
        setupLabels();
        setupContainerPanel();
        add(scrollPane, BorderLayout.CENTER);
        add(containerPanel, BorderLayout.PAGE_END);

    }

    // MODIFIES: this
    // EFFECTS: add three columns to the table
    private void addColumns() {
        tableModel.addColumn("Restaurant Name");
        tableModel.addColumn("Rating");
        tableModel.addColumn("Category");
    }

    // MODIFIES: this
    // EFFECTS: disables remove button if list is empty
    private void tableMouseClicked(MouseEvent e) {
        row = table.getSelectedRow();
        if (row == -1) {
            removeButton.setEnabled(false);
        } else {
            removeButton.setEnabled(true);
        }
    }

    // MODIFIES: this
    // EFFECTS: sets up the add and remove buttons
    void setupButtons() {
        addButton = new JButton(addString);
        addListener = new AddListener(addButton);
        addButton.setActionCommand(addString);
        addButton.addActionListener(addListener);
        addButton.setEnabled(false);

        removeButton = new JButton(removeString);
        removeButton.setActionCommand(removeString);
        removeButton.addActionListener(new RemoveListener());
        removeButton.setEnabled(false);
    }

    // MODIFIES: this
    // EFFECTS: sets up the labels for the text fields
    void setupLabels() {

        String[] labelStrings = {
                "Name: ",
                "Rating: ",
                "Category: ",
        };

        labels = new JLabel[labelStrings.length];

        //Associate label/field pairs, add everything,
        //and lay it out.
        for (int i = 0; i < labelStrings.length; i++) {
            labels[i] = new JLabel(labelStrings[i]);
        }
        setupFields();

    }

    // MODIFIES: this
    // EFFECTS: sets up the restaurant name and rating text fields, and the category dropdown list
    // Spinner referenced from TextInputDemo
    void setupFields() {
        restaurantName = new JTextField(10);
        restaurantName.getDocument().addDocumentListener(addListener);

        restaurantRating = new JTextField(10);
        restaurantRating.getDocument().addDocumentListener(addListener);

        restaurantCategory = new JSpinner(new SpinnerListModel(RestaurantCategory.values()));
        // displays the current value of the spinner
        JComponent spinnerEditor = restaurantCategory.getEditor();
        ((JSpinner.DefaultEditor) spinnerEditor).getTextField();

    }


//    // From TextInputDemo
//    public JFormattedTextField getTextField(JSpinner spinner) {
//        JComponent editor = spinner.getEditor();
//        if (editor instanceof JSpinner.DefaultEditor) {
//            return ((JSpinner.DefaultEditor) editor).getTextField();
//        } else {
//            System.err.println("Unexpected editor type: "
//                    + spinner.getEditor().getClass()
//                    + " isn't a descendant of DefaultEditor");
//            return null;
//        }
//    }


    // MODIFIES: this
    // EFFECTS: sets up the container panel that holds all the other panels
    void setupContainerPanel() {
        //Create a panel that uses BoxLayout.
        // container Panel to hold all the panels
        containerPanel = new JPanel();
        containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.Y_AXIS));
        containerPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        setupPanels();
        containerPanel.add(namePane);
        containerPanel.add(ratingPane);
        containerPanel.add(categoryPane);
        containerPanel.add(buttonPane);
    }

    // MODIFIES: this
    // EFFECTS: sets up the panel for the buttons and text fields
    void setupPanels() {

        namePane = new JPanel();
        namePane.setLayout(new BoxLayout(namePane, BoxLayout.X_AXIS));
        namePane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        namePane.add(labels[0]);
        namePane.add(Box.createHorizontalStrut(5));
        namePane.add(restaurantName);

        ratingPane = new JPanel();
        ratingPane.setLayout(new BoxLayout(ratingPane, BoxLayout.X_AXIS));
        ratingPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        ratingPane.add(labels[1]);
        ratingPane.add(Box.createHorizontalStrut(5));
        ratingPane.add(restaurantRating);

        categoryPane = new JPanel();
        categoryPane.setLayout(new BoxLayout(categoryPane, BoxLayout.X_AXIS));
        categoryPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        categoryPane.add(labels[2]);
        categoryPane.add(Box.createHorizontalStrut(5));
        categoryPane.add(restaurantCategory);

        buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.X_AXIS));
        buttonPane.add(removeButton);
        buttonPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        buttonPane.add(addButton);

    }



    // This class represents what happens when the remove button is clicked.
    class RemoveListener implements ActionListener {

        // MODIFIES: this (AddRemoveList)
        // EFFECTS: removes the restaurant selected by the user
        public void actionPerformed(ActionEvent e) {

            // Referenced from ListDemo
            int index = table.getSelectedRow();

            if (index == -1) { //Nobody's left, disable removing.
                removeButton.setEnabled(false);

            } else {
                removeButton.setEnabled(true);
            }

            // Remove restaurant object from table row (in ui)
            tableModel.removeRow(index);

            // Remove restaurant object from restaurantWishlist
            Restaurant restaurantToBeRemoved = restaurantList.getRestaurantWishlist().get(index);
            restaurantList.removeRestaurant(restaurantToBeRemoved);

        }
    }


    // This class represents what action will be implemented based on what the user enters in the text field
    class AddListener implements ActionListener, DocumentListener {
        private JButton button;

        // MODIFIES: this
        // EFFECTS: constructs an AddListener with a button
        public AddListener(JButton button) {
            this.button = button;
        }

        // MODIFIES: this, restaurantList (AddRemoveList)
        // EFFECTS: adds the restaurant entered by the user into the restaurant wishlist
        public void actionPerformed(ActionEvent e) {

            // Add restaurant object to restaurantWishlist
            Restaurant restaurant = new Restaurant(restaurantName.getText(),
                    Double.parseDouble(restaurantRating.getText()),
                    (RestaurantCategory) restaurantCategory.getValue());
            restaurantList.addRestaurant(restaurant);

            // Add restaurant to table (in ui)
            tableModel.addRow(new Object[]{restaurant.getName(),
                    restaurant.getRating(), restaurant.getCategory()});


            //Reset the text field.
            restaurantName.requestFocusInWindow();
            restaurantName.setText("");
            restaurantRating.requestFocusInWindow();
            restaurantRating.setText("");
            restaurantCategory.setValue(RestaurantCategory.values()[0]);
        }


        // MODIFIES: this (AddRemoveList)
        // EFFECTS: handles empty text field if there was an insert to the document
        @Override
        public void insertUpdate(DocumentEvent e) {
            handleEmptyTextField();
        }

        // MODIFIES: this (AddRemoveList)
        // EFFECTS: handles empty text field if there was anything removed to from the document
        @Override
        public void removeUpdate(DocumentEvent e) {
            handleEmptyTextField();
        }

        // EFFECTS: do nothing (not applicable)
        @Override
        public void changedUpdate(DocumentEvent e) {
            // do nothing
        }
    }


    // MODIFIES: addButton
    // EFFECTS: disables the add restaurant button if the user enters an empty string
    private void handleEmptyTextField() {
        if (restaurantName.getText().isEmpty() || restaurantRating.getText().isEmpty()) {
            addButton.setEnabled(false);
        } else {
            addButton.setEnabled(true);
        }
    }





}

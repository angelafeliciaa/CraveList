package ui;


import model.EventLog;
import model.Event;
import model.wishlist.RestaurantWishlist;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.io.FileNotFoundException;
import java.io.IOException;


// This class represents the main menu of the GUI
public class MainMenu extends JPanel implements ActionListener {

    private JLabel label;
    private JFrame frame;
    private JPanel panel;
    private JLabel imageLabel;
    private JPanel containerPanel;
    private RestaurantWishlist restaurantList;

    private JsonWriter jsonWriter = new JsonWriter(JSON_STORE);
    private JsonReader jsonReader = new JsonReader(JSON_STORE);
    private static final String JSON_STORE = "./data/restaurantWishlist.json";


    // MODIFIES: this
    // EFFECTS: creates the main menu panel
    public MainMenu() {

        panel = new JPanel();
        restaurantList = new RestaurantWishlist();

        // Create a JFrame window
        frame = new JFrame("User Input Example");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setSize(4000, 1500);

        addImage();
        makeContainerPanel();

        frame.add(containerPanel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Restaurant Review");
        frame.pack();
        setupButtons();
        frame.setVisible(true);

        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                for (Event e : EventLog.getInstance()) {
                    System.out.println(e);
                }
                System.exit(0);
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: loads the image file and creates a JLabel to hold the image
    public void addImage() {
        // Load the image file and create an ImageIcon object
        ImageIcon imageIcon = new ImageIcon("data/restaurantImg.png");

        // Resize the image to a smaller size
        Image image = imageIcon.getImage();
        Image scaledImage = image.getScaledInstance(300, 400, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        // Create a JLabel to hold the image
        imageLabel = new JLabel(scaledIcon);
    }

    // MODIFIES: this
    // EFFECTS: creates a container panel to hold the image and the main panel
    public void makeContainerPanel() {
        containerPanel = new JPanel(new BorderLayout());
        containerPanel.add(imageLabel, BorderLayout.WEST);
        containerPanel.add(panel, BorderLayout.CENTER);
    }


    // EFFECTS: do nothing
    @Override
    public void actionPerformed(ActionEvent e) {
        // do nothing
    }

    // MODIFIES: this
    // EFFECTS: setup buttons for the main menu
    public void setupButtons() {
        JButton viewRestaurantWishlistButton = new JButton("View restaurant wishlist");
        viewRestaurantWishlistButton.addActionListener(viewRestaurantWishlistAction);

        JButton addRestaurantButton = new JButton("Add/remove restaurant to wishlist");
        addRestaurantButton.addActionListener(addRestaurantAction);

        JButton saveWishlistButton = new JButton("Save restaurant wishlist to file");
        saveWishlistButton.addActionListener(saveWishlistAction);

        JButton loadWishlistButton = new JButton("Load restaurant wishlist from file");
        loadWishlistButton.addActionListener(loadWishlistAction);

        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new GridLayout(0, 1));
        label = new JLabel("Welcome! What would you like to do today?");
        panel.add(label);
        panel.add(viewRestaurantWishlistButton);
        panel.add(addRestaurantButton);
        panel.add(saveWishlistButton);
        panel.add(loadWishlistButton);

    }

    // MODIFIES: this
    // EFFECTS: Code to perform when "View restaurant wishlist" button is clicked, opens a
    // new panel to display the wishlist
    ActionListener viewRestaurantWishlistAction = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            JFrame frame = new JFrame("WishList");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            //Create and set up the content pane.
            DisplayWishlist newContentPane = new DisplayWishlist(restaurantList);
            newContentPane.setOpaque(true); //content panes must be opaque
            frame.setContentPane(newContentPane);

            //Display the window.
            frame.pack();
            frame.setVisible(true);
        }
    };

    // MODIFIES: this
    // EFFECTS: Code to perform when "Add/remove restaurant wishlist" button is clicked,
    // opens a new panel to allow adding and removing to wishlist
    ActionListener addRestaurantAction = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            JFrame frame = new JFrame("Add or Remove Restaurant");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            //Create and set up the content pane.
            JComponent newContentPane = new AddRemoveList(restaurantList);
            newContentPane.setOpaque(true); //content panes must be opaque
            frame.setContentPane(newContentPane);

            //Display the window.
            frame.pack();
            frame.setVisible(true);

        }
    };


    // MODIFIES: this
    // EFFECTS: code to perform when the save to file button is clicked, saves the wishlist to json file
    ActionListener saveWishlistAction = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            // save json file
            try {
                jsonWriter.open();
                jsonWriter.write(restaurantList);
                jsonWriter.close();
                JOptionPane.showMessageDialog(null, "Saved to " + JSON_STORE);
            } catch (FileNotFoundException fileNotFoundException) {
                JOptionPane.showMessageDialog(null, "Unable to write to file: " + JSON_STORE);
            }
        }
    };

    // MODIFIES: this
    // EFFECTS: code to perform when the load from file button is clicked, loads the wishlist from json file
    ActionListener loadWishlistAction = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            // load file json
            try {
                restaurantList = jsonReader.read();
                JOptionPane.showMessageDialog(null, "Loaded from " + JSON_STORE);
            } catch (IOException ioException) {
                JOptionPane.showMessageDialog(null, "Failed to write to file: " + JSON_STORE);
            }
        }
    };



}

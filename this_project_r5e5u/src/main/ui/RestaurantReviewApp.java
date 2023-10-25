package ui;

import model.Restaurant;
import model.RestaurantCategory;
import model.wishlist.RestaurantWishlist;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// Represents the restaurant review application
public class RestaurantReviewApp {

    private final Scanner scanner;
    private RestaurantWishlist w1;
    private JsonWriter jsonWriter = new JsonWriter(JSON_STORE);
    private JsonReader jsonReader = new JsonReader(JSON_STORE);
    private static final String JSON_STORE = "./data/restaurantWishlist.json";


    // EFFECTS: Runs the restaurant review app
    public RestaurantReviewApp() {
        scanner = new Scanner(System.in);
        w1 = new RestaurantWishlist();
        runRestaurant();
    }

    // MODIFIES: this
    // EFFECTS: processes the user's input
    // SOURCE: TellerApp example
    private void runRestaurant() {
        boolean running = true;
        int command;


        while (running) {
            displayMenu();
            command = scanner.nextInt();
            System.out.println("You selected " + command);

            if (command == 0) {
                running = false;
            } else {
                processInput(command);
            }
        }
    }

    // EFFECTS: displays menu of options for user to choose from
    private void displayMenu() {
        System.out.println("\nGood afternoon!");
        System.out.println("What would you like to do today? (1 or 0) ");
        System.out.println("\t1. View, add or remove restaurant wishlist");
        //System.out.println("\t2. View, add, remove or rate visited restaurants");
        System.out.println("\t2. Load restaurant wishlist from file");
        System.out.println("\t3. Save restaurant wishlist to file");
        System.out.println("\n0 to quit");
    }


    // MODIFIES: this
    // EFFECTS: processes the user input
    // SOURCE: TellerApp example
    private void processInput(int command) {
        if (command == 1) {
            doRestaurantWishlist();
        } else if (command == 2) {
            loadRestaurantWishlist();
        } else if (command == 3) {
            saveRestaurantWishlist();
        } else {
            System.out.println("Invalid selection!");
        }
    }


    // MODIFIES: this
    // EFFECTS: runs the operations in restaurant wishlist
    private void doRestaurantWishlist() {
        boolean keepGoing = true;
        int options;

        while (keepGoing) {
            displayWishlistMenu();

            options = scanner.nextInt();
            System.out.println("You selected " + options);

            if (options == 0) {
                keepGoing = false;
            } else if (options == 1) {
                displayWishlist();
            } else if (options == 2) {
                doAddRestaurantWishlist();
            } else if (options == 3) {
                if (w1.isEmpty()) {
                    keepGoing = false;
                    System.out.println("No more restaurants to be removed!");
                } else {
                    doRemoveRestaurantWishlist();
                }
            } else {
                System.out.println("Invalid option!");
            }
        }
    }

    // EFFECTS: Displays menu of options for user to choose
    private void displayWishlistMenu() {
        System.out.println("\nOPTIONS: (0 to quit)");
        System.out.println("1. View wishlist");
        System.out.println("2. Add a restaurant to your wishlist?");
        System.out.println("3. Remove a restaurant from your wishlist?");
    }

    // MODIFIES: this
    // EFFECTS: adds the user input restaurant to restaurant wishlist
    private void doAddRestaurantWishlist() {
        String inputRestaurantName;
        double inputRating;
        int inputCategory;


        System.out.println("Enter restaurant name:");
        scanner.nextLine();
        inputRestaurantName = scanner.nextLine();

        System.out.println("Enter restaurant rating (1-5) :");
        inputRating = scanner.nextDouble();

        displayCategories();
        System.out.println("Enter a category (1-8) :");
        inputCategory = scanner.nextInt();

        if (inputCategory > 8 || inputCategory < 1) {
            System.out.println("Invalid category number!");
        }

        w1.addRestaurant(new Restaurant(inputRestaurantName, inputRating, assignCategory(inputCategory)));
        displayWishlist();
    }


    // EFFECTS: converts user input category of type integer into of type RestaurantCategory
    private RestaurantCategory assignCategory(int inputCategory) {
        RestaurantCategory category;
        RestaurantCategory[] values = RestaurantCategory.values();
        category = values[inputCategory - 1];
        return category;
    }


    // EFFECTS: displays the restaurant wishlist
    private void displayWishlist() {
        if (w1.getRestaurantWishlist().size() == 0) {
            System.out.println("Wishlist empty!");
        } else {
            System.out.println("\nRESTAURANT WISHLIST");
            System.out.println("--------------------\n");
            System.out.printf("%-20s %-10s %-20s\n", "Restaurant", "Rating", "Category");
            for (Restaurant r : w1.getRestaurantWishlist()) {
                System.out.printf("%-20s %-10s %-20s\n", r.getName(), r.getRating(), r.getCategory());
            }
        }

        // 10 - r.getName().length() = add x white spaces
    }

    // EFFECTS: displays all the different restaurant categories
    private void displayCategories() {
        System.out.println("1. " + RestaurantCategory.CAFE);
        System.out.println("2. " + RestaurantCategory.CHINESE);
        System.out.println("3. " + RestaurantCategory.DESSERT);
        System.out.println("4. " + RestaurantCategory.ITALIAN);
        System.out.println("5. " + RestaurantCategory.JAPANESE);
        System.out.println("6. " + RestaurantCategory.KOREAN);
        System.out.println("7. " + RestaurantCategory.SEAFOOD);
        System.out.println("8. " + RestaurantCategory.WESTERN);
    }


    // MODIFIES: this
    // EFFECTS: removes user input restaurant from wishlist, if restaurant is not in wishlist, print error message
    private void doRemoveRestaurantWishlist() {
        String inputRestaurantNameToRemove;
        double inputRatingToRemove;
        int inputCategoryToRemove;
        Restaurant restaurantToRemove;

        displayWishlist();
        System.out.println("\nEnter restaurant name to remove: ");
        scanner.nextLine();
        inputRestaurantNameToRemove = scanner.nextLine();

        System.out.println("Enter rating of restaurant to be removed: ");
        inputRatingToRemove = scanner.nextDouble();

        displayCategories();
        System.out.println("Enter category of restaurant to be removed (1-8): ");
        inputCategoryToRemove = scanner.nextInt();


        RestaurantCategory categoryToRemove = assignCategory(inputCategoryToRemove);
        restaurantToRemove = new Restaurant(inputRestaurantNameToRemove, inputRatingToRemove, categoryToRemove);
        if (w1.contains(restaurantToRemove)) {
            w1.removeRestaurant(restaurantToRemove);
            System.out.println("Successfully removed " + inputRestaurantNameToRemove + " from wishlist!");
        } else {
            System.out.println(inputRestaurantNameToRemove + " with rating "
                    + inputRatingToRemove + " and category " + categoryToRemove + " is not in wishlist!");
        }
    }

    // EFFECTS: saves the restaurant wishlist to file
    // Referenced from JsonSerializationDemo
    private void saveRestaurantWishlist() {
        try {
            jsonWriter.open();
            jsonWriter.write(w1);
            jsonWriter.close();
            System.out.println("Saved to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads restaurant wishlist from file
    // Referenced from JsonSerializationDemo
    private void loadRestaurantWishlist() {
        try {
            w1 = jsonReader.read();
            System.out.println("Loaded from" + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Failed to read from file " + JSON_STORE);
        }
    }
}



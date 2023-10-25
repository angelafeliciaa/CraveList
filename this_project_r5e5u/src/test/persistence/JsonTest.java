package persistence;

import model.Restaurant;
import model.RestaurantCategory;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Represents test for checking restaurant object
public class JsonTest {

    protected void checkThingy(Restaurant restaurant, String name, double rating, RestaurantCategory category) {
            assertEquals(name, restaurant.getName());
            assertEquals(rating, restaurant.getRating());
            assertEquals(category, restaurant.getCategory());
    }

}

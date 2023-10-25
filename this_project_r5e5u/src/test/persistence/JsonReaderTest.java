package persistence;

import model.Restaurant;
import model.RestaurantCategory;
import model.wishlist.RestaurantWishlist;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// Represents tests for the JsonReader class
// Heavily referenced from JsonSerializationDemo
public class JsonReaderTest extends JsonTest {

    @Test
    void testReadNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            RestaurantWishlist rw = reader.read();
            fail("Expected IOException");
        } catch (IOException e) {
            System.out.println("Error loading file");
        }
    }

    @Test
    void testReaderEmptyRestaurantWishlist() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyWishlist.json");
        try {
            RestaurantWishlist rw = reader.read();
            //assertEquals("My work room", rw.getName());
            assertEquals(0, rw.length());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralRestaurantWishlist() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralRestaurantWishlist.json");
        try {
            RestaurantWishlist rw = reader.read();
            //assertEquals("My restaurant wishlist", rw.getName());
            List<Restaurant> restaurants = rw.getRestaurantWishlist();
            assertEquals(2, restaurants.size());
            checkThingy(restaurants.get(0), "Miku Sushi", 4.0,  RestaurantCategory.JAPANESE);
            checkThingy(restaurants.get(1),"Sulbing", 5.0, RestaurantCategory.DESSERT);
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }


}

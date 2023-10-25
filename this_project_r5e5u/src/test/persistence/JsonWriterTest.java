package persistence;

import model.Restaurant;
import model.RestaurantCategory;
import model.wishlist.RestaurantWishlist;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// Represents tests for the JsonWriter class
public class JsonWriterTest extends JsonTest{

    @Test
    void testWriterInvalidFile() {
        try {
            RestaurantWishlist rw = new RestaurantWishlist();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            RestaurantWishlist rw = new RestaurantWishlist();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWishlist.json");
            writer.open();
            writer.write(rw);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWishlist.json");
            rw = reader.read();
            //assertEquals("My work room", rw.getName());
            assertEquals(0, rw.length());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            RestaurantWishlist rw = new RestaurantWishlist();
            rw.addRestaurant(new Restaurant("Elisa Steak", 4.5,  RestaurantCategory.WESTERN));
            rw.addRestaurant(new Restaurant("Tavola", 3.5, RestaurantCategory.ITALIAN));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralRestaurantWishlist.json");
            writer.open();
            writer.write(rw);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralRestaurantWishlist.json");
            rw = reader.read();
            //assertEquals("My work room", rw.getName());
            List<Restaurant> restaurants = rw.getRestaurantWishlist();
            assertEquals(2, restaurants.size());
            checkThingy(restaurants.get(0), "Elisa Steak", 4.5, RestaurantCategory.WESTERN);
            checkThingy(restaurants.get(1), "Tavola", 3.5 , RestaurantCategory.ITALIAN);

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}

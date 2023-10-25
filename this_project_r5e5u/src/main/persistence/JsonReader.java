package persistence;

import model.Restaurant;
import model.RestaurantCategory;
import model.wishlist.RestaurantWishlist;
import org.json.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;


// Represents a reader that reads restaurant wishlist from JSON data stored in file
// Heavily referenced from JsonSerializationDemo
public class JsonReader {

    private String source;

    // EFFECTS: constructs a reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads restaurant wishlist from file and returns it
    // Throws IOException if any error occurs while trying to read data from file
    public RestaurantWishlist read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseRestaurantWishlist(jsonObject);
    }


    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses restaurant wishlist from JSON object and returns it
    private RestaurantWishlist parseRestaurantWishlist(JSONObject jsonObject) {
        //String name = jsonObject.getString("name");
        RestaurantWishlist rw = new RestaurantWishlist();
        addRestaurants(rw, jsonObject);
        return rw;
    }

    // MODIFIES: restaurantWishlist
    // EFFECTS: parses restaurants from JSON object and adds them to restaurant wishlist
    private void addRestaurants(RestaurantWishlist rw, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Restaurant Wishlist");
        for (Object json : jsonArray) {
            JSONObject nextRestaurant = (JSONObject) json;
            addRestaurant(rw, nextRestaurant);
        }
    }

    // MODIFIES: restaurantWishlist
    // EFFECTS: parses a restaurant from JSON object and adds it to workroom
    private void addRestaurant(RestaurantWishlist rw, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        RestaurantCategory category = RestaurantCategory.valueOf(jsonObject.getString("category"));
        double rating = jsonObject.getDouble("rating");
        Restaurant restaurant = new Restaurant(name, rating, category);
        rw.addRestaurant(restaurant);
    }

}

package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.Objects;

// This class represents a restaurant
public class Restaurant implements Writable {

    private String name;
    private double rating;
    private RestaurantCategory category;

    // EFFECTS: Constructs a restaurant with a name, rating and category of type RestaurantCategory
    public Restaurant(String name, double rating, RestaurantCategory category) {
        this.name = name;
        this.rating = rating;
        this.category = category;
    }

    public String getName() {
        return this.name;
    }

    public double getRating() {
        return this.rating;
    }

    public RestaurantCategory getCategory() {
        return this.category;
    }

    // EFFECTS: returns true if a restaurant has the same name, rating and category as another restaurant
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Restaurant that = (Restaurant) o;
        return Double.compare(that.rating, rating) == 0 && Objects.equals(name, that.name) && category == that.category;
    }

    // EFFECTS: returns a hashcode value for the object
    @Override
    public int hashCode() {
        return Objects.hash(name, rating, category);
    }

    // EFFECTS: converts the object name, rating and category to a json object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("rating", rating);
        json.put("category", category);
        return json;
    }
}

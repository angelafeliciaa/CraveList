package model.wishlist;

import model.Event;
import model.EventLog;
import model.Restaurant;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// Represents a restaurant wishlist that we can add/ remove restaurants from,

public class RestaurantWishlist implements Writable {
    private ArrayList<Restaurant> restaurantWishlist;


    // EFFECTS: constructs an empty list of restaurant names
    public RestaurantWishlist() {
        restaurantWishlist = new ArrayList<>();

    }

    // MODIFIES: this
    // EFFECTS: adds a restaurant name to the restaurant wishlist
    public ArrayList<Restaurant> addRestaurant(Restaurant r) {
        restaurantWishlist.add(r);
        EventLog.getInstance().logEvent(new Event("Added " + r.getName() + " to wishlist"));
        return restaurantWishlist;
    }

    // MODIFIES: this
    // EFFECTS: search for given restaurant in the restaurant wishlist and removes it, if not found then do nothing
    public ArrayList<Restaurant> removeRestaurant(Restaurant r) {
        restaurantWishlist.remove(r);
        EventLog.getInstance().logEvent(new Event("Removed " + r.getName() + " from wishlist"));
        return restaurantWishlist;
    }

    // EFFECTS: returns true if given restaurant is in restaurant wishlist
    public boolean contains(Restaurant r) {
        return restaurantWishlist.contains(r);
    }

    // EFFECTS: returns the restaurant list
    public ArrayList<Restaurant> getRestaurantWishlist() {
        return restaurantWishlist;
    }

    // EFFECTS: returns number of restaurants in restaurant wishlist
    public int length() {
        return restaurantWishlist.size();
    }

    // EFFECTS: returns true if restaurant wishlist is empty, false otherwise
    public boolean isEmpty() {
        if (restaurantWishlist.size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    // EFFECTS: converts the restaurant wishlist to a json object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Restaurant Wishlist", thingiesToJson());
        return json;
    }

    // EFFECTS: returns things in this restaurant wishlist as a JSON array
    private JSONArray thingiesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Restaurant r : restaurantWishlist) {
            jsonArray.put(r.toJson());
        }

        return jsonArray;
    }


}

package model;

import model.wishlist.RestaurantWishlist;
import model.Restaurant;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

// Represents tests for the restaurantWishlist class
class RestaurantWishlistTest {

    // Unit tests for the restaurantWishlist class

    private RestaurantWishlist wishlist1;
    private Restaurant tavola;
    private Restaurant koiSushi;
    private Restaurant nori;

    @BeforeEach
    public void runBefore() {
        wishlist1 = new RestaurantWishlist();
        tavola = new Restaurant("Tavola", 4.0, RestaurantCategory.ITALIAN);
        koiSushi = new Restaurant("Koi Sushi", 4.4, RestaurantCategory.JAPANESE);
        nori = new Restaurant("Nori", 3.2, RestaurantCategory.JAPANESE);

    }

    @Test
    public void testConstructor(){
        assertEquals(0, wishlist1.length());
    }

    @Test
    public void testAddOneRestaurant(){
        wishlist1.addRestaurant(tavola);
        assertEquals(1, wishlist1.length());
    }

    @Test
    public void testAddMultipleRestaurants() {
        wishlist1.addRestaurant(koiSushi);
        wishlist1.addRestaurant(tavola);
        wishlist1.addRestaurant(nori);
        assertEquals(3, wishlist1.length());
        assertFalse(wishlist1.isEmpty());
        assertTrue(wishlist1.contains(koiSushi));
        assertTrue(wishlist1.contains(tavola));
        assertTrue(wishlist1.contains(nori));
    }

    @Test
    public void testRemoveOneRestaurant(){
        wishlist1.addRestaurant(tavola);
        wishlist1.removeRestaurant(nori);
        assertEquals(1, wishlist1.length());
        wishlist1.removeRestaurant(tavola);
        assertEquals(0, wishlist1.length());
        assertTrue(wishlist1.isEmpty());
    }

    @Test
    public void testRemoveMultipleRestaurants(){
        wishlist1.addRestaurant(tavola);
        wishlist1.addRestaurant(nori);
        wishlist1.addRestaurant(koiSushi);

        wishlist1.removeRestaurant(tavola);
        assertEquals(2, wishlist1.length());
        assertFalse(wishlist1.contains(tavola));

        wishlist1.removeRestaurant(koiSushi);
        assertFalse(wishlist1.contains(koiSushi));

        wishlist1.removeRestaurant(nori);
        assertFalse(wishlist1.contains(nori));
        assertTrue(wishlist1.isEmpty());
    }

    @Test
    public void testGetRestaurantWishlist() {
        wishlist1.getRestaurantWishlist();

        wishlist1.addRestaurant(tavola);
        assertEquals(1, wishlist1.getRestaurantWishlist().size());

        wishlist1.addRestaurant(koiSushi);
        wishlist1.addRestaurant(nori);
        assertEquals(3, wishlist1.getRestaurantWishlist().size());
    }


}
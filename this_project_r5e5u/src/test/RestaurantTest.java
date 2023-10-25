import model.Restaurant;
import model.RestaurantCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

// Represents tests for the Restaurant class
public class RestaurantTest {
    private String name;
    private double rating;
    private RestaurantCategory category;
    Restaurant r1;
    Restaurant r2;
    Restaurant r3;
    Restaurant r4;
    Restaurant r5;
    Restaurant r6;
    Restaurant r7;
    Restaurant r8;
    Restaurant r9;
    Restaurant r10;
    Restaurant r11;
    Restaurant r12;

    @BeforeEach
    public void runBefore() {
        r1 = new Restaurant("Tavola", 4.0, RestaurantCategory.ITALIAN);
        r2 = new Restaurant("Nori", 3, RestaurantCategory.ITALIAN);
        r3 = null;
        r4 = new Restaurant("Sulbing", 4, RestaurantCategory.DESSERT);
        r5 = new Restaurant("Nori", 3, RestaurantCategory.ITALIAN);

        r6 = new Restaurant(r1.getName(), r1.getRating(), RestaurantCategory.ITALIAN);

        r7 = new Restaurant(r1.getName(), 5, RestaurantCategory.ITALIAN);
        r8 = new Restaurant("Boathouse", r1.getRating(), RestaurantCategory.ITALIAN);
        r9 = new Restaurant(r1.getName(), r1.getRating(), RestaurantCategory.SEAFOOD);

        r10 = new Restaurant(r1.getName(), 5, RestaurantCategory.SEAFOOD);
        r11 = new Restaurant("Boathouse", r1.getRating(), RestaurantCategory.SEAFOOD);
        r12 = new Restaurant("Boathouse", 5, RestaurantCategory.SEAFOOD);

    }

    @Test
    public void testConstructor() {
        assertEquals("Tavola", r1.getName());
        assertEquals(4, r1.getRating());
        assertEquals(RestaurantCategory.ITALIAN, r1.getCategory());

        assertEquals("Nori", r2.getName());
        assertEquals(3, r2.getRating());
        assertEquals(RestaurantCategory.ITALIAN, r2.getCategory());

    }

    @Test
    public void testEquals() {
        assertTrue(r1.equals(r1));
        assertTrue(r2.equals(r2));

        assertFalse(r1.equals(r2));
        assertFalse(r1.equals("hello"));
        assertFalse(r1.equals(r3));

        assertTrue(r1.equals(r6));
        assertFalse(r1.equals(r7));
        assertFalse(r1.equals(r8));
        assertFalse(r1.equals(r9));
        assertFalse(r1.equals(r10));
        assertFalse(r1.equals(r11));
        assertFalse(r1.equals(r12));
    }

    @Test
    public void testHashcode() {
        assertNotEquals(r1.hashCode(), r2.hashCode());
    }



}

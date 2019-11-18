package empapp;

import locations.Location;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocationTest {

    @Test
    void testCalculateDistance() {
        Location location1 =
                new Location("Budapest, Óbuda",  	47.497912,19.040235);
        Location location2 =
                new Location("Zsámbék",  	 	47.5473984, 18.7213733);

        assertEquals(24.56, location1.calculateDistanceFrom(location2),0.05);
    }
}

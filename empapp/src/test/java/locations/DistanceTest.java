package locations;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

public class DistanceTest {

    private static DynamicTest convertDistanceToDynamicTest(Distance d) {
        return dynamicTest("Calculate distance between " + d.getLocation1().getName() + " and " +
                        d.getLocation2().getName(),
                () -> assertEquals(d.getDistance(),
                        d.getLocation1().calculateDistanceFrom(d.getLocation2()), 0.5));
    }

    @TestFactory
    Stream<DynamicTest> testCalculateDistance() {
        return Stream.of(
                new Distance(new Location("A", 1.0, 1.0),
                    new Location("B", 2.0, 2.0),
                    157),
                new Distance(new Location("C", 2.0, 2.0),
                        new Location("D", 4.0, 4.0),
                        314)
                )
                .map(DistanceTest::convertDistanceToDynamicTest);
    }
}

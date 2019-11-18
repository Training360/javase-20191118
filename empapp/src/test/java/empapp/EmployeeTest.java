package empapp;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeTest {

    public EmployeeTest() {
        System.out.println("Constructor");
    }

    @Test
    @DisplayName("Test get age")
    void testGetAge() {
        assertEquals(49,
                new Employee("John Doe", 1970).getAge(2019));
        // Mindig assertEquals-t használjunk!
//        assertTrue(new Employee("John Doe", 1970).getAge(2019) == 48);
    }

    @Test
    void testGetAgeWithNegative() {
        assertThrows(IllegalArgumentException.class, () -> new Employee("John Doe", -200));
    }

}

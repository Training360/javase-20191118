package empapp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeeTest {

    @Test
    void testGetAge() {
        assertEquals(48,
                new Employee("John Doe", 1970).getAge(2019));
    }
}

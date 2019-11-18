package empapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class EmployeeHierarchicalTest {

    Employee employee;

    @SimpleTest
    @Nested
    class WithYearOfBirth1970 {

        @BeforeEach
        void init() {
            employee = new Employee("John Doe", 1970);
        }
        @Test
        void testAge() {
            // ...
        }
    }

    @PerformanceTest
    @Nested
    class WithYearOfBirth2000 {
        @BeforeEach
        void init() {
            employee = new Employee("John Doe", 2000);
        }
        @Test
        void testAge() {
            // ...
        }
    }
}

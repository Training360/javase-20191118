package empapp;

import org.junit.jupiter.api.Test;
import static empapp.EmployeeAssert.assertThat;

//import static org.assertj.core.api.Assertions.assertThat;

public class EmployeeAssertJTest {

    @Test
    void testGetAge() {
//        assertThat(new Employee("John Doe", 1970).getAge(2000))
//                //.as(" age ")
//                .withFailMessage("ROSSZ")
//                .isEqualTo(29);

        assertThat(new Employee("John Doe", 1970))
                .hasName("John Doe");
    }
}

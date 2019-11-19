package empapp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.Extensions;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    EmployeeRepository employeeRepository;

    @InjectMocks
    EmployeeService employeeService;

    @Test
    void testCreateEmployee() {
        var result = employeeService.createEmployee("John Doe",
                1970);

        assertTrue(result);
    }

    @Test
    void testCreateEmployeeAlreadyExists() {
        when(employeeRepository.findEmployeeByName(anyString()))
                .thenReturn(Optional.of(new Employee(null, 0)));

        var result = employeeService.createEmployee("John Doe",
                1970);

        assertFalse(result);
    }

    @Test
    void testCreateEmployeeCallSaveEmployee() {
        employeeService.createEmployee("John Doe", 1970);

//        ArgumentCaptor<Employee> captor = ArgumentCaptor.forClass(Employee.class);
//        verify(employeeRepository).saveEmployee(captor.capture());
//
//        var employee = captor.getValue();
//        assertEquals("John Doe", employee.getName());
//        assertEquals(1970, employee.getYearOfBirth());

        verify(employeeRepository).saveEmployee(argThat(e -> e.getName().equals("John Doe") &&
                e.getYearOfBirth() == 1970));

    }

    @Test
    void testCreateEmployeeCallSaveEmployeeWithSpaces() {
        employeeService.createEmployee("     John Doe    ", 1970);

        verify(employeeRepository).saveEmployee(argThat(e -> e.getName().equals("John Doe")));

    }
}

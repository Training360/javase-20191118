package empapp;

import java.util.Optional;

public interface EmployeeRepository {
    Optional<Employee> findEmployeeByName(String name);

    void saveEmployee(Employee employee);
}

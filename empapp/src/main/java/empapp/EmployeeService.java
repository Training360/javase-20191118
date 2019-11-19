package empapp;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public boolean createEmployee(String name, int yearOfBirth) {
        name = name.trim();
        Optional<Employee> employee = employeeRepository
                .findEmployeeByName(name);
        if (employee.isPresent()) {
            return false;
        }
        else {
            employeeRepository.saveEmployee(
                    new Employee(name, yearOfBirth));
            return true;
        }
    }

    public Optional<Employee> findEmployeeBy(Predicate<Employee> criteria, List<Employee> employees) {
        for (Employee employee: employees) {
            if (criteria.test(employee)) {
                return Optional.of(employee);
            }
        }
       return Optional.empty();
    }

    public Optional<Employee> findEmployeeByNamePrefix(String namePrefix, List<Employee> employees) {
        return findEmployeeBy(employee -> employee.getName().startsWith(namePrefix), employees);
    }

    public Optional<Employee> findEmployeeByYearOfBirth(int yearOfBirth, List<Employee> employees) {
        return findEmployeeBy(employee -> employee.getYearOfBirth() == yearOfBirth, employees);
    }

    public void sort(List<Employee> employees) {
        employees.sort(Comparator.comparing(Employee::getName));
    }

}

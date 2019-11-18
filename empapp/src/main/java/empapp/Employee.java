package empapp;

public class Employee {

    private String name;
    private int yearOfBirth;

    public Employee(String name, int yearOfBirth) {
        if (yearOfBirth < 0) {
            throw new IllegalArgumentException("Must be positive");
        }
        this.name = name;
        this.yearOfBirth = yearOfBirth;
    }

    public int getAge(int atYear) {
        return atYear - yearOfBirth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }
}

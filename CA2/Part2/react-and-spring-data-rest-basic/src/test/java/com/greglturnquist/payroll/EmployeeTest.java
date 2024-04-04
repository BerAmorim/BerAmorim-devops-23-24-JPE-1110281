package com.greglturnquist.payroll;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    @Test
    void testEmployeeConstructorWithValidArguments() {
        // Arrange
        String firstName = "John";
        String lastName = "Doe";
        String description = "Developer";
        String jobTitle = "Senior Developer";
        int jobYears = 5;
        String email = "john.doe@example.com";

        // Act
        Employee employee = new Employee(firstName, lastName, description, jobTitle, jobYears, email);

        // Assert
        assertEquals(firstName, employee.getFirstName());
        assertEquals(lastName, employee.getLastName());
        assertEquals(description, employee.getDescription());
        assertEquals(jobTitle, employee.getJobTitle());
        assertEquals(jobYears, employee.getJobYears());
        assertEquals(email, employee.getEmail());
    }

    @Test
    void testEmployeeConstructorWithInvalidArguments() {
        // Arrange
        String invalidFirstName = null; // invalid because it's null

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            new Employee(invalidFirstName, "Doe", "Developer", "Senior Developer", 5, "john.doe@example.com");
        });
    }

    @Test
    void testValidateArgumentsWithInvalidEmail() {
        // Arrange
        Employee employee = new Employee("Jane", "Doe", "Manager", "Project Manager", 10, "jane.doe@example.com");

        // Act
        boolean isValid = employee.validateArguments("Jane", "Doe", "Manager", "Project Manager", 10, "");

        // Assert
        assertFalse(isValid);
    }

    @Test
    void testEqualsAndHashCode() {
        // Arrange
        Employee employee1 = new Employee("John", "Doe", "Developer", "Senior Developer", 5, "john.doe@example.com");
        Employee employee2 = new Employee("John", "Doe", "Developer", "Senior Developer", 5, "john.doe@example.com");

        // Act & Assert
        assertEquals(employee1, employee2, "Employee objects with the same field values should be equal");
        assertEquals(employee1.hashCode(), employee2.hashCode(), "Employee objects with the same field values should have the same hash code");
    }

    @Test
    void testSetAndGetMethods() {
        // Arrange
        Employee employee = new Employee("John", "Doe", "Developer", "Senior Developer", 5, "john.doe@example.com");
        String updatedFirstName = "Jane";
        String updatedLastName = "Smith";
        String updatedDescription = "Lead Developer";
        String updatedJobTitle = "Lead Developer";
        int updatedJobYears = 10;
        String updatedEmail = "jane.smith@example.com";

        // Act
        employee.setFirstName(updatedFirstName);
        employee.setLastName(updatedLastName);
        employee.setDescription(updatedDescription);
        employee.setJobTitle(updatedJobTitle);
        employee.setJobYears(updatedJobYears);
        employee.setEmail(updatedEmail);

        // Assert
        assertEquals(updatedFirstName, employee.getFirstName());
        assertEquals(updatedLastName, employee.getLastName());
        assertEquals(updatedDescription, employee.getDescription());
        assertEquals(updatedJobTitle, employee.getJobTitle());
        assertEquals(updatedJobYears, employee.getJobYears());
        assertEquals(updatedEmail, employee.getEmail());
    }

    @Test
    void testEmployeeId() {
        // Arrange
        Employee employee = new Employee("John", "Doe", "Developer", "Senior Developer", 5, "john.doe@example.com");
        Long id = 1L;

        // Act
        employee.setId(id);

        // Assert
        assertEquals(id, employee.getId());
    }

    @Test
    void testToString() {
        // Arrange
        Employee employee = new Employee("John", "Doe", "Developer", "Senior Developer", 5, "john.doe@example.com");

        // Act
        String employeeString = employee.toString();

        // Assert
        String expectedString = "Employee{id=null, firstName='John', lastName='Doe', description='Developer', jobTitle='Senior Developer', jobYears='5', email='john.doe@example.com'}";
        assertEquals(expectedString, employeeString);
    }

    @Test
    void testValidationWithAllValidArguments() {
        // Arrange
        String firstName = "John";
        String lastName = "Doe";
        String description = "Developer";
        String jobTitle = "Senior Developer";
        int jobYears = 5;
        String email = "john.doe@example.com";
        Employee employee = new Employee(firstName, lastName, description, jobTitle, jobYears, email);

        // Act
        boolean isValid = employee.validateArguments(firstName, lastName, description, jobTitle, jobYears, email);

        // Assert
        assertTrue(isValid);
    }

    @Test
    void testValidateArgumentsWithEmptyEmail() {
        // Arrange
        Employee employee = new Employee("Jane", "Doe", "Manager", "Project Manager", 10, "jane.doe@example.com");

        // Act
        boolean isValid = employee.validateArguments("Jane", "Doe", "Manager", "Project Manager", 10, "");

        // Assert
        assertFalse(isValid);
    }

    @Test
    void testValidateArgumentsWithEmailWithoutAtSymbol() {
        // Arrange
        Employee employee = new Employee("Jane", "Doe", "Manager", "Project Manager", 10, "jane.doe@example.com");

        // Act
        boolean isValid = employee.validateArguments("Jane", "Doe", "Manager", "Project Manager", 10, "janedoeexample.com");

        // Assert
        assertFalse(isValid);
    }

    @Test
    void testValidateArgumentsWithEmailWithoutDomain() {
        // Arrange
        Employee employee = new Employee("Jane", "Doe", "Manager", "Project Manager", 10, "jane.doe@example.com");

        // Act
        boolean isValid = employee.validateArguments("Jane", "Doe", "Manager", "Project Manager", 10, "jane.doe@");

        // Assert
        assertFalse(isValid);
    }
}
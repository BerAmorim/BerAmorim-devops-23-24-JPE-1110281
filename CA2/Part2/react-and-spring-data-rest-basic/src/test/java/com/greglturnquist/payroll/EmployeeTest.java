package com.greglturnquist.payroll;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EmployeeTest {

    private static Validator validator;

    @BeforeAll
    static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }


    @Test
    void testEmployeeCreation() {
        // Arrange
        Employee employee = new Employee();
        employee.setFirstName("Frodo");

        //Act
        employee.setJobYears(5);

        // Assert
        assertNotNull(employee.getJobYears());
        assertTrue(employee.getJobYears() instanceof Integer);
    }

    // tests to validate the email with arrange act assert


    @Test
    void testEmptyEmail() {
        // Arrange
        Employee employee = new Employee();
        employee.setFirstName("Frodo");
        employee.setJobYears(5);
        employee.setEmail(" "); // This should fail the @Email validation

        // Act
        Set<ConstraintViolation<Employee>> violations = validator.validate(employee);

        // Assert
        assertFalse( "Invalid email format should result in a constraint violation.", violations.isEmpty());

        // Optionally, check if the violation is specifically for the email field
        boolean hasEmailViolation = violations.stream()
                .anyMatch(violation -> violation.getPropertyPath().toString().equals("email"));
        assertTrue(hasEmailViolation, "There should be a violation for the email field.");
    }

    @Test
    void testInvalidEmail() {
        // Arrange
        Employee employee = new Employee();
        employee.setFirstName("Frodo");
        employee.setJobYears(5);
        employee.setEmail("gfdfgdfg"); // This should fail the @Email validation

        // Act
        Set<ConstraintViolation<Employee>> violations = validator.validate(employee);

        // Assert
        assertFalse( "Invalid email format should result in a constraint violation.", violations.isEmpty());

        // Optionally, check if the violation is specifically for the email field
        boolean hasEmailViolation = violations.stream()
                .anyMatch(violation -> violation.getPropertyPath().toString().equals("email"));
        assertTrue(hasEmailViolation, "There should be a violation for the email field.");
    }

    @Test
    void testEmailSuccessfully() {
        // Arrange
        Employee employee = new Employee();
        employee.setEmail("ber@gmail.com"); // This should pass the @Email validation
        employee.setFirstName("Frodo");
        employee.setJobYears(5);

        // Act
        Set<ConstraintViolation<Employee>> violations = validator.validate(employee);

        // Inspect violations
        violations.forEach(violation -> System.out.println(violation.getMessage()));

        // Assert
        assertTrue(violations.isEmpty(), "Expected no validation errors");
    }


}
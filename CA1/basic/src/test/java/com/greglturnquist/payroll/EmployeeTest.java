package com.greglturnquist.payroll;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

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

}
package com.akande.employee_api.service;

import com.akande.employee_api.dto.EmployeeRequest;
import com.akande.employee_api.dto.EmployeeResponse;
import com.akande.employee_api.model.Employee;
import com.akande.employee_api.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.akande.employee_api.exception.DuplicateEmployeeException;
import com.akande.employee_api.exception.EmployeeNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @Test
    void saveEmployee_shouldSaveEmployeeSuccessfully() {

        EmployeeRequest request = new EmployeeRequest();
        request.setFirstName("Kenny");
        request.setLastName("Akande");
        request.setEmail("kenny@example.com");
        request.setPhoneNumber("08123456789");

        when(employeeRepository.findByEmail(request.getEmail()))
                .thenReturn(Optional.empty());

        Employee savedEmployee = new Employee();
        savedEmployee.setId("123");
        savedEmployee.setFirstName(request.getFirstName());
        savedEmployee.setLastName(request.getLastName());
        savedEmployee.setEmail(request.getEmail());
        savedEmployee.setPhoneNumber(request.getPhoneNumber());

        when(employeeRepository.save(any(Employee.class)))
                .thenReturn(savedEmployee);

        EmployeeResponse response = employeeService.saveEmployee(request);

        assertNotNull(response);
        assertEquals("123", response.getId());
        assertEquals("Kenny", response.getFirstName());
        assertEquals("Akande", response.getLastName());
        assertEquals("kenny@example.com", response.getEmail());
        assertEquals("08123456789", response.getPhoneNumber());

        verify(employeeRepository).findByEmail(request.getEmail());
        verify(employeeRepository).save(any(Employee.class));
    }

    @Test
    void saveEmployee_shouldThrowExceptionWhenEmailAlreadyExists() {

        EmployeeRequest request = new EmployeeRequest();
        request.setFirstName("Kenny");
        request.setLastName("Akande");
        request.setEmail("kenny@example.com");
        request.setPhoneNumber("08123456789");

        Employee existingEmployee = new Employee();
        existingEmployee.setId("1");
        existingEmployee.setEmail("kenny@example.com");

        when(employeeRepository.findByEmail(request.getEmail()))
                .thenReturn(Optional.of(existingEmployee));

        assertThrows(
                DuplicateEmployeeException.class,
                () -> employeeService.saveEmployee(request)
        );

        verify(employeeRepository).findByEmail(request.getEmail());
        verify(employeeRepository, never()).save(any(Employee.class));
    }

    @Test
    void getEmployeeById_shouldReturnEmployee() {

        Employee employee = new Employee();
        employee.setId("123");
        employee.setFirstName("Kenny");
        employee.setLastName("Akande");
        employee.setEmail("kenny@example.com");
        employee.setPhoneNumber("08123456789");

        when(employeeRepository.findById("123"))
                .thenReturn(Optional.of(employee));

        EmployeeResponse response = employeeService.getEmployeeById("123");

        assertNotNull(response);
        assertEquals("123", response.getId());
        assertEquals("Kenny", response.getFirstName());
        assertEquals("Akande", response.getLastName());
        assertEquals("kenny@example.com", response.getEmail());
        assertEquals("08123456789", response.getPhoneNumber());

        verify(employeeRepository).findById("123");
    }

    @Test
    void getEmployeeById_shouldThrowExceptionWhenEmployeeDoesNotExist() {

        when(employeeRepository.findById("123"))
                .thenReturn(Optional.empty());

        assertThrows(
                EmployeeNotFoundException.class,
                () -> employeeService.getEmployeeById("123")
        );

        verify(employeeRepository).findById("123");
    }

    @Test
    void updateEmployee_shouldUpdateEmployeeSuccessfully() {

        EmployeeRequest request = new EmployeeRequest();
        request.setFirstName("John");
        request.setLastName("Smith");
        request.setEmail("john@example.com");
        request.setPhoneNumber("08012345678");

        Employee existingEmployee = new Employee();
        existingEmployee.setId("123");
        existingEmployee.setFirstName("Kenny");
        existingEmployee.setLastName("Akande");
        existingEmployee.setEmail("kenny@example.com");
        existingEmployee.setPhoneNumber("08123456789");

        when(employeeRepository.findById("123"))
                .thenReturn(Optional.of(existingEmployee));

        when(employeeRepository.save(any(Employee.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        EmployeeResponse response = employeeService.updateEmployee("123", request);

        assertNotNull(response);
        assertEquals("John", response.getFirstName());
        assertEquals("Smith", response.getLastName());
        assertEquals("john@example.com", response.getEmail());
        assertEquals("08012345678", response.getPhoneNumber());

        verify(employeeRepository).findById("123");
        verify(employeeRepository).save(existingEmployee);
    }

    @Test
    void updateEmployee_shouldThrowExceptionWhenEmployeeDoesNotExist() {

        EmployeeRequest request = new EmployeeRequest();
        request.setFirstName("John");
        request.setLastName("Smith");
        request.setEmail("john@example.com");
        request.setPhoneNumber("08012345678");

        when(employeeRepository.findById("123"))
                .thenReturn(Optional.empty());

        assertThrows(
                EmployeeNotFoundException.class,
                () -> employeeService.updateEmployee("123", request)
        );

        verify(employeeRepository).findById("123");
        verify(employeeRepository, never()).save(any(Employee.class));
    }

}
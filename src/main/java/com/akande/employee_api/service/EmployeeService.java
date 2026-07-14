package com.akande.employee_api.service;

import com.akande.employee_api.model.Employee;
import com.akande.employee_api.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import com.akande.employee_api.exception.EmployeeNotFoundException;
import com.akande.employee_api.dto.EmployeeRequest;
import com.akande.employee_api.dto.EmployeeResponse;
import com.akande.employee_api.exception.DuplicateEmployeeException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import com.akande.employee_api.dto.EmployeePatchRequest;


import java.util.Optional;
import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public EmployeeResponse saveEmployee(EmployeeRequest request) {

        if (employeeRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new DuplicateEmployeeException(
                    "An employee with this email already exists."
            );
        }

        Employee employee = new Employee();

        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setEmail(request.getEmail());
        employee.setPhoneNumber(request.getPhoneNumber());

        Employee savedEmployee = employeeRepository.save(employee);

        return mapToEmployeeResponse(savedEmployee);
    }

    public Page<EmployeeResponse> getAllEmployees(
            int page,
            int size,
            String sortBy,
            String direction
    ) {

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        return employeeRepository
                .findAll(pageable)
                .map(this::mapToEmployeeResponse);
    }

    public Page<EmployeeResponse> searchEmployees(
            String keyword,
            int page,
            int size,
            String sortBy,
            String direction
    ) {

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        return employeeRepository
                .searchEmployees(keyword, pageable)
                .map(this::mapToEmployeeResponse);

    }

    public EmployeeResponse getEmployeeById(String id) {

        Employee employee = employeeRepository
                .findById(id)
                .orElseThrow(() ->
                        new EmployeeNotFoundException("Employee not found with id: " + id));

        return mapToEmployeeResponse(employee);
    }

    public EmployeeResponse updateEmployee(String id, EmployeeRequest request) {

        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() ->
                        new EmployeeNotFoundException("Employee not found with id: " + id));

        existingEmployee.setFirstName(request.getFirstName());
        existingEmployee.setLastName(request.getLastName());
        existingEmployee.setEmail(request.getEmail());
        existingEmployee.setPhoneNumber(request.getPhoneNumber());

        Employee updatedEmployee = employeeRepository.save(existingEmployee);

        return mapToEmployeeResponse(updatedEmployee);
    }

    public EmployeeResponse patchEmployee(
            String id,
            EmployeePatchRequest request
    ) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() ->
                        new EmployeeNotFoundException(
                                "Employee not found with id: " + id
                        )
                );

        if (request.getFirstName() != null) {
            employee.setFirstName(request.getFirstName());
        }

        if (request.getLastName() != null) {
            employee.setLastName(request.getLastName());
        }

        if (request.getEmail() != null) {

            employeeRepository.findByEmail(request.getEmail())
                    .ifPresent(existing -> {

                        if (!existing.getId().equals(id)) {
                            throw new DuplicateEmployeeException(
                                    "An employee with this email already exists."
                            );
                        }

                    });

            employee.setEmail(request.getEmail());
        }

        if (request.getPhoneNumber() != null) {
            employee.setPhoneNumber(request.getPhoneNumber());
        }

        Employee updated = employeeRepository.save(employee);

        return mapToEmployeeResponse(updated);

    }

    public void deleteEmployee(String id) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() ->
                        new EmployeeNotFoundException("Employee not found with id: " + id));

        employeeRepository.delete(employee);
    }

    private EmployeeResponse mapToEmployeeResponse(Employee employee) {

        EmployeeResponse response = new EmployeeResponse();

        response.setId(employee.getId());
        response.setFirstName(employee.getFirstName());
        response.setLastName(employee.getLastName());
        response.setEmail(employee.getEmail());
        response.setPhoneNumber(employee.getPhoneNumber());
        response.setCreatedAt(employee.getCreatedAt());
        response.setUpdatedAt(employee.getUpdatedAt());

        return response;
    }

}
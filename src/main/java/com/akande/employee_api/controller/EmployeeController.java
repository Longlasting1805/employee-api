package com.akande.employee_api.controller;

import com.akande.employee_api.model.Employee;
import com.akande.employee_api.service.EmployeeService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.akande.employee_api.exception.EmployeeNotFoundException;
import com.akande.employee_api.dto.EmployeeRequest;
import com.akande.employee_api.dto.EmployeeResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.data.domain.Page;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public EmployeeResponse saveEmployee(@Valid @RequestBody EmployeeRequest employee) {
        return employeeService.saveEmployee(employee);
    }

    @GetMapping
    public Page<EmployeeResponse> getAllEmployees(

            @RequestParam(defaultValue = "0") int page,

            @RequestParam(defaultValue = "5") int size

    ) {

        return employeeService.getAllEmployees(page, size);

    }

    @GetMapping("/{id}")
    public EmployeeResponse  getEmployeeById(@PathVariable String id) {
        return employeeService.getEmployeeById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public EmployeeResponse updateEmployee(
            @PathVariable String id,
            @Valid @RequestBody EmployeeRequest employee) {

        return employeeService.updateEmployee(id, employee);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable String id) {

        employeeService.deleteEmployee(id);
    }


}
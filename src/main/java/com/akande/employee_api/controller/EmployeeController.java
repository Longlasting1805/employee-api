package com.akande.employee_api.controller;

import com.akande.employee_api.model.Employee;
import com.akande.employee_api.service.EmployeeService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.akande.employee_api.exception.EmployeeNotFoundException;
import com.akande.employee_api.dto.EmployeeRequest;
import com.akande.employee_api.dto.EmployeeResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public EmployeeResponse saveEmployee(@Valid @RequestBody EmployeeRequest employee) {
        return employeeService.saveEmployee(employee);
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable String id) {
        return employeeService.getEmployeeById(id);
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable String id,
                                   @RequestBody Employee employee) {

        return employeeService.updateEmployee(id, employee);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable String id) {

        employeeService.deleteEmployee(id);
    }


}
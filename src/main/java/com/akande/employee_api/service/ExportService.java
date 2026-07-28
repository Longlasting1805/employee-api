package com.akande.employee_api.service;

import com.akande.employee_api.model.Employee;
import com.akande.employee_api.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExportService {

    private final EmployeeRepository employeeRepository;

    public ExportService(EmployeeRepository employeeRepository) {

        this.employeeRepository = employeeRepository;

    }

    public String exportEmployeesToCsv() {

        List<Employee> employees = employeeRepository.findAll();

        StringBuilder csv = new StringBuilder();

        csv.append("First Name,Last Name,Email,Phone Number\n");

        for (Employee employee : employees) {

            csv.append(employee.getFirstName()).append(",")

                    .append(employee.getLastName()).append(",")

                    .append(employee.getEmail()).append(",")

                    .append(employee.getPhoneNumber())

                    .append("\n");

        }

        return csv.toString();

    }

}
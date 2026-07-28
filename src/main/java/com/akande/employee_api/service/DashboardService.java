package com.akande.employee_api.service;

import com.akande.employee_api.dto.DashboardStatsResponse;
import com.akande.employee_api.model.Employee;
import com.akande.employee_api.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.List;

import com.akande.employee_api.dto.MonthlyRegistrationResponse;
import com.akande.employee_api.dto.RecentEmployeeResponse;

import java.time.Month;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DashboardService {

    private final EmployeeRepository employeeRepository;

    public DashboardService(EmployeeRepository employeeRepository) {

        this.employeeRepository = employeeRepository;

    }

    public DashboardStatsResponse getDashboardStats() {

        List<Employee> employees = employeeRepository.findAll();

        long totalEmployees = employees.size();

        LocalDate today = LocalDate.now();

        long employeesToday = employees.stream()

                .filter(employee -> employee.getCreatedAt() != null)

                .filter(employee ->
                        employee.getCreatedAt()
                                .toLocalDate()
                                .equals(today)
                )

                .count();

        long employeesThisMonth = employees.stream()

                .filter(employee -> employee.getCreatedAt() != null)

                .filter(employee -> {

                    LocalDate createdDate =
                            employee.getCreatedAt().toLocalDate();

                    return createdDate.getMonth() == today.getMonth()

                            && createdDate.getYear() == today.getYear();

                })

                .count();

        String newestEmployee = employees.stream()

                .filter(employee -> employee.getCreatedAt() != null)

                .max(Comparator.comparing(Employee::getCreatedAt))

                .map(employee -> employee.getFirstName() + " " + employee.getLastName())

                .orElse("-");

        Map<Month, Long> monthlyMap = employees.stream()

                .filter(employee -> employee.getCreatedAt() != null)

                .collect(Collectors.groupingBy(

                        employee -> employee.getCreatedAt().getMonth(),

                        Collectors.counting()

                ));

        List<MonthlyRegistrationResponse> monthlyRegistrations =

                new java.util.ArrayList<>();

        for (Month month : Month.values()) {

            monthlyRegistrations.add(

                    new MonthlyRegistrationResponse(

                            month.name().substring(0, 3),

                            monthlyMap.getOrDefault(month, 0L)

                    )

            );

        }

        List<RecentEmployeeResponse> recentEmployees = employees.stream()

                .sorted(

                        Comparator.comparing(Employee::getCreatedAt)

                                .reversed()

                )

                .limit(5)

                .map(employee ->

                        new RecentEmployeeResponse(

                                employee.getId(),

                                employee.getFirstName() + " " + employee.getLastName(),

                                employee.getEmail(),

                                employee.getPhoneNumber()

                        )

                )

                .toList();

        return new DashboardStatsResponse(

                totalEmployees,

                employeesToday,

                employeesThisMonth,

                newestEmployee,

                monthlyRegistrations,

                recentEmployees

        );

    }

}
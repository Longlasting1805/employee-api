package com.akande.employee_api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class DashboardStatsResponse {

    private long totalEmployees;

    private long employeesToday;

    private long employeesThisMonth;

    private String newestEmployee;

    private List<MonthlyRegistrationResponse> monthlyRegistrations;

    private List<RecentEmployeeResponse> recentEmployees;

}
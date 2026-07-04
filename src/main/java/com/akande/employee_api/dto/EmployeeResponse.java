package com.akande.employee_api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeResponse {

    private String id;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;
}
package com.akande.employee_api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RecentEmployeeResponse {

    private String id;

    private String fullName;

    private String email;

    private String phoneNumber;

}
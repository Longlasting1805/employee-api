package com.akande.employee_api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MonthlyRegistrationResponse {

    private String month;

    private long count;

}
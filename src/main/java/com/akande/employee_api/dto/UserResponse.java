package com.akande.employee_api.dto;

import com.akande.employee_api.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserResponse {

    private String id;

    private String firstName;

    private String lastName;

    private String email;

    private Role role;

}
package com.akande.employee_api.mapper;

import com.akande.employee_api.dto.EmployeeRequest;
import com.akande.employee_api.dto.EmployeeResponse;
import com.akande.employee_api.model.Employee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    Employee toEntity(EmployeeRequest request);

    EmployeeResponse toResponse(Employee employee);

}
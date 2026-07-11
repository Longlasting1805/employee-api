package com.akande.employee_api.repository;

import com.akande.employee_api.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface EmployeeRepository extends MongoRepository<Employee, String> {

    Optional<Employee> findByEmail(String email);

    @Query("{}")
    Page<Employee> findAll(Pageable pageable);

    @Query("""
    {
      "$or":[
        {"firstName":{"$regex":?0,"$options":"i"}},
        {"lastName":{"$regex":?0,"$options":"i"}},
        {"email":{"$regex":?0,"$options":"i"}}
      ]
    }
    """)
    Page<Employee> searchEmployees(String keyword, Pageable pageable);
}
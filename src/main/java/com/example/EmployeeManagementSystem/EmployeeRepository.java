package com.example.EmployeeManagementSystem;

import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends CouchbaseRepository<Employee, String> {
    Optional<Employee> findByName(Employee manager);
}








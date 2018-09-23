package com.mindtree.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.entity.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, String> {

}

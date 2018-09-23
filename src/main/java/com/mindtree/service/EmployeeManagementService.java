package com.mindtree.service;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.mindtree.controller.ResponseDetail;
import com.mindtree.dao.EmployeeRepository;
import com.mindtree.entity.Employee;
import com.mindtree.exceptions.EmployeeNotFoundException;
import com.mindtree.exceptions.InvalidRequestException;

@Service
public class EmployeeManagementService {
	
	@Autowired
	private EmployeeRepository repository;
	
	public ResponseDetail addEmployee(Employee employee) throws Exception {
		try {
			repository.save(employee);
			return new ResponseDetail(HttpStatus.OK.value(),
					"Success", "Employee data inserted successfully", null);
		}
		catch (Exception ex) {
			throw new InvalidRequestException("Input data mismatch");
		}
	}

	public ResponseDetail deleteEmployee(String empId)
			throws Exception {
		try {
			repository.delete(empId);
			return new ResponseDetail(HttpStatus.OK.value(), "Success",
					"Employee data deleted successfully", null);
		} 
		catch (Exception ex) {
			throw new Exception("There is some issue at server side. Please check the log");
		}
	}

	public ResponseDetail getAllEmployees() {
		Iterable<Employee> employeeItr = repository.findAll();
		List<Employee> employees = new ArrayList<Employee>();
		employeeItr.forEach(employees ::add);
		if (employees.isEmpty()) {
			throw new EmployeeNotFoundException("No Employees exsist");
		}
		return new ResponseDetail(HttpStatus.OK.value(),
				"Success", "[]", employees);
	}

	public ResponseDetail getEmployee(String empId) {
		Employee employee = repository.findOne(empId);
		List<Employee> employees = new ArrayList<Employee>();
		employees.add(employee);
		if (employee == null) {
			throw new EmployeeNotFoundException("The Employee doesnt exsist");
		}
		return new ResponseDetail(HttpStatus.OK.value(),
				"Success", "[]", employees);
	}

	public ResponseDetail authenticateUser(String userName, String password) {
		Employee employee = repository.findOne(userName);
		ResponseDetail responseDetail = null;
		List<Employee> employees = new ArrayList<Employee>();
		if (employee.getPassword().equalsIgnoreCase(password)) {
			employees.add(employee);
			responseDetail = new ResponseDetail(HttpStatus.OK.value(),
					"Success", "Employee has authenticated successfully", employees);
		} else {
			responseDetail = new ResponseDetail(HttpStatus.NOT_FOUND.value(),
					"Success", "Invalid Username and Password", employees);
		}
		return responseDetail;
	}

}

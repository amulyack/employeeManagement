package com.mindtree.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.entity.Employee;
import com.mindtree.exceptions.InvalidRequestException;
import com.mindtree.service.EmployeeManagementService;

@RestController
public class EmployeeManagementController {
	
	@Autowired
	private EmployeeManagementService service;

	@RequestMapping("/EmpMgt")
	public ResponseEntity<String> startPage() {
		return new ResponseEntity<String>("Welcome to Employee Management", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/EmpMgt/addEmp", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDetail> addEmployee(@RequestBody Employee employee) throws Exception {
		ResponseDetail response = service.addEmployee(employee);
		return new ResponseEntity<ResponseDetail>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/EmpMgt/deleteEmp/{empId}", method = RequestMethod.DELETE)
	public ResponseEntity<ResponseDetail> deleteEmployee(@PathVariable String empId) throws Exception {
		ResponseDetail response = service.deleteEmployee(empId);
		return new ResponseEntity<ResponseDetail>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/EmpMgt/getAllEmpDetails", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDetail> getAllEmployees() {
		ResponseDetail response = service.getAllEmployees();
		return new ResponseEntity<ResponseDetail>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/EmpMgt/getByEmpId/{empId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDetail> getEmployee(@PathVariable String empId) {
		ResponseDetail response = service.getEmployee(empId);
		return new ResponseEntity<ResponseDetail>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/EmpMgt/checkLogin", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDetail> checkLogin(@RequestBody LoginDetails loginDetails) {
		ResponseDetail response = service.authenticateUser(loginDetails.getUsername(), loginDetails.getPassword());
		return new ResponseEntity<ResponseDetail>(response, HttpStatus.OK);
	}
	 
}

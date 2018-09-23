package com.mindtree.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.mindtree.entity.Employee;
import com.mindtree.exceptions.InvalidRequestException;
import com.mindtree.service.EmployeeManagementService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeManagementControllerTest {
	
	@MockBean
	private EmployeeManagementService serviceMock;
	
	@Autowired
	private EmployeeManagementController controller;
	
	@Test
	public void testAddEmployee() {
		Employee employee = new Employee();
		try {
			ResponseDetail response = new ResponseDetail(200, "Success", "Employee data inserted successfully", null);
			Mockito.when(serviceMock.addEmployee(Mockito.any(Employee.class))).thenReturn(response);
			ResponseEntity<ResponseDetail> responseEntity = controller.addEmployee(employee);
			Assert.assertNotNull(responseEntity);
			Assert.assertNotNull(responseEntity.getBody());
			Assert.assertEquals(200, responseEntity.getStatusCodeValue());
			Assert.assertEquals("OK", responseEntity.getStatusCode().name());
			Assert.assertEquals(200, responseEntity.getBody().getStatusCode());
			Assert.assertEquals("Success", responseEntity.getBody().getStatus());
			Assert.assertEquals("Employee data inserted successfully", responseEntity.getBody().getMessage());
			Assert.assertNotNull(responseEntity.getBody().getData());
			Assert.assertTrue(responseEntity.getBody().getData().isEmpty());
		} 
		catch (Exception ex) {
			Assert.fail();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testAddEmployeeForException() {
		Employee employee = new Employee();
		try {
			Mockito.when(serviceMock.addEmployee(Mockito.any(Employee.class))).thenThrow(InvalidRequestException.class);
			controller.addEmployee(employee);
			Assert.fail();
		} 
		catch (Exception ex) {
			Assert.assertNotNull(ex);
		}
	}

	@Test
	public void testDeleteEmployee() {
		try {
			ResponseDetail response = new ResponseDetail(200, "Success", "Employee data deleted successfully", null);
			Mockito.when(serviceMock.deleteEmployee(Mockito.anyString())).thenReturn(response);
			ResponseEntity<ResponseDetail> responseEntity = controller.deleteEmployee("abc");
			Assert.assertNotNull(responseEntity);
			Assert.assertNotNull(responseEntity.getBody());
			Assert.assertEquals(200, responseEntity.getStatusCodeValue());
			Assert.assertEquals("OK", responseEntity.getStatusCode().name());
			Assert.assertEquals(200, responseEntity.getBody().getStatusCode());
			Assert.assertEquals("Success", responseEntity.getBody().getStatus());
			Assert.assertEquals("Employee data deleted successfully", responseEntity.getBody().getMessage());
			Assert.assertNotNull(responseEntity.getBody().getData());
			Assert.assertTrue(responseEntity.getBody().getData().isEmpty());
		} 
		catch (Exception ex) {
			Assert.fail();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testDeleteEmployeeForException() {
		try {
			Mockito.when(serviceMock.deleteEmployee(Mockito.anyString())).thenThrow(InvalidRequestException.class);
			controller.deleteEmployee("abc");
			Assert.fail();
		} 
		catch (Exception ex) {
			Assert.assertNotNull(ex);
		}
	}
	
	@Test
	public void testGetAllEmployees() {
		List<Employee> employees = new ArrayList<Employee>();
		Employee employee1 = new Employee("emp1", "paswrd1", "ABC", "abc@mail.com", "17/09/2000", "Female", "when", "then");
		Employee employee2 = new Employee("emp2", "paswrd2", "XYZ", "xyz@mail.com", "18/09/2000", "Male", "where", "there");
		employees.add(employee1);
		employees.add(employee2);
		ResponseDetail response = new ResponseDetail(200, "Success", "[]", employees);
		Mockito.when(serviceMock.getAllEmployees()).thenReturn(response);
		ResponseEntity<ResponseDetail> responseEntity = controller.getAllEmployees();
		Assert.assertNotNull(responseEntity);
		Assert.assertNotNull(responseEntity.getBody());
		Assert.assertEquals(200, responseEntity.getStatusCodeValue());
		Assert.assertEquals("OK", responseEntity.getStatusCode().name());
		Assert.assertEquals(200, responseEntity.getBody().getStatusCode());
		Assert.assertEquals("Success", responseEntity.getBody().getStatus());
		Assert.assertEquals("[]", responseEntity.getBody().getMessage());
		Assert.assertNotNull(responseEntity.getBody().getData());
		Assert.assertFalse(responseEntity.getBody().getData().isEmpty());
		Assert.assertEquals(2, responseEntity.getBody().getData().size());
		Assert.assertEquals("emp1",responseEntity.getBody().getData().get(0).getUsername());
		Assert.assertEquals("paswrd1",responseEntity.getBody().getData().get(0).getPassword());
		Assert.assertEquals("ABC",responseEntity.getBody().getData().get(0).getFullName());
		Assert.assertEquals("abc@mail.com",responseEntity.getBody().getData().get(0).getEmailID());
		Assert.assertEquals("17/09/2000",responseEntity.getBody().getData().get(0).getDateOfBirth());
		Assert.assertEquals("Female",responseEntity.getBody().getData().get(0).getGender());
		Assert.assertEquals("when",responseEntity.getBody().getData().get(0).getSecurityQuestion());
		Assert.assertEquals("then",responseEntity.getBody().getData().get(0).getSecurityAnswer());
		Assert.assertEquals("emp2",responseEntity.getBody().getData().get(1).getUsername());
		Assert.assertEquals("paswrd2",responseEntity.getBody().getData().get(1).getPassword());
		Assert.assertEquals("XYZ",responseEntity.getBody().getData().get(1).getFullName());
		Assert.assertEquals("xyz@mail.com",responseEntity.getBody().getData().get(1).getEmailID());
		Assert.assertEquals("18/09/2000",responseEntity.getBody().getData().get(1).getDateOfBirth());
		Assert.assertEquals("Male",responseEntity.getBody().getData().get(1).getGender());
		Assert.assertEquals("where",responseEntity.getBody().getData().get(1).getSecurityQuestion());
		Assert.assertEquals("there",responseEntity.getBody().getData().get(1).getSecurityAnswer());
	}
	
	@Test
	public void testGetEmployee() {
		List<Employee> employees = new ArrayList<Employee>();
		Employee employee = new Employee("emp1", "paswrd1", "ABC", "abc@mail.com", "17/09/2000", "Female", "when", "then");
		employees.add(employee);
		ResponseDetail response = new ResponseDetail(200, "Success", "[]", employees);
		Mockito.when(serviceMock.getEmployee(Mockito.anyString())).thenReturn(response);
		ResponseEntity<ResponseDetail> responseEntity = controller.getEmployee("emp1");
		Assert.assertNotNull(responseEntity);
		Assert.assertNotNull(responseEntity.getBody());
		Assert.assertEquals(200, responseEntity.getStatusCodeValue());
		Assert.assertEquals("OK", responseEntity.getStatusCode().name());
		Assert.assertEquals(200, responseEntity.getBody().getStatusCode());
		Assert.assertEquals("Success", responseEntity.getBody().getStatus());
		Assert.assertEquals("[]", responseEntity.getBody().getMessage());
		Assert.assertNotNull(responseEntity.getBody().getData());
		Assert.assertFalse(responseEntity.getBody().getData().isEmpty());
		Assert.assertEquals(1, responseEntity.getBody().getData().size());
		Assert.assertEquals("emp1",responseEntity.getBody().getData().get(0).getUsername());
		Assert.assertEquals("paswrd1",responseEntity.getBody().getData().get(0).getPassword());
		Assert.assertEquals("ABC",responseEntity.getBody().getData().get(0).getFullName());
		Assert.assertEquals("abc@mail.com",responseEntity.getBody().getData().get(0).getEmailID());
		Assert.assertEquals("17/09/2000",responseEntity.getBody().getData().get(0).getDateOfBirth());
		Assert.assertEquals("Female",responseEntity.getBody().getData().get(0).getGender());
		Assert.assertEquals("when",responseEntity.getBody().getData().get(0).getSecurityQuestion());
		Assert.assertEquals("then",responseEntity.getBody().getData().get(0).getSecurityAnswer());
	}
	
	@Test
	public void testCheckLoginForAuthenticUser() {
		List<Employee> employees = new ArrayList<Employee>();
		Employee employee = new Employee("emp1", "paswrd1", "ABC", "abc@mail.com", "17/09/2000", "Female", "when", "then");
		employees.add(employee);
		ResponseDetail response = new ResponseDetail(200, "Success", "Employee has authenticated successfully", employees);
		Mockito.when(serviceMock.authenticateUser(Mockito.anyString(), Mockito.anyString())).thenReturn(response);
		LoginDetails loginDetails = new LoginDetails();
		ResponseEntity<ResponseDetail> responseEntity = controller.checkLogin(loginDetails);
		Assert.assertNotNull(responseEntity);
		Assert.assertNotNull(responseEntity.getBody());
		Assert.assertEquals(200, responseEntity.getStatusCodeValue());
		Assert.assertEquals("OK", responseEntity.getStatusCode().name());
		Assert.assertEquals(200, responseEntity.getBody().getStatusCode());
		Assert.assertEquals("Success", responseEntity.getBody().getStatus());
		Assert.assertEquals("Employee has authenticated successfully", responseEntity.getBody().getMessage());
		Assert.assertNotNull(responseEntity.getBody().getData());
		Assert.assertFalse(responseEntity.getBody().getData().isEmpty());
		Assert.assertEquals(1, responseEntity.getBody().getData().size());
		Assert.assertEquals("emp1",responseEntity.getBody().getData().get(0).getUsername());
		Assert.assertEquals("paswrd1",responseEntity.getBody().getData().get(0).getPassword());
		Assert.assertEquals("ABC",responseEntity.getBody().getData().get(0).getFullName());
		Assert.assertEquals("abc@mail.com",responseEntity.getBody().getData().get(0).getEmailID());
		Assert.assertEquals("17/09/2000",responseEntity.getBody().getData().get(0).getDateOfBirth());
		Assert.assertEquals("Female",responseEntity.getBody().getData().get(0).getGender());
		Assert.assertEquals("when",responseEntity.getBody().getData().get(0).getSecurityQuestion());
		Assert.assertEquals("then",responseEntity.getBody().getData().get(0).getSecurityAnswer());
	}
	
	@Test
	public void testCheckLoginForNonAuthenticUser() {
		ResponseDetail response = new ResponseDetail(404, "Success", "Invalid Username and Password", null);
		Mockito.when(serviceMock.authenticateUser(Mockito.anyString(), Mockito.anyString())).thenReturn(response);
		LoginDetails loginDetails = new LoginDetails();
		ResponseEntity<ResponseDetail> responseEntity = controller.checkLogin(loginDetails);
		Assert.assertNotNull(responseEntity);
		Assert.assertNotNull(responseEntity.getBody());
		Assert.assertEquals(200, responseEntity.getStatusCodeValue());
		Assert.assertEquals("OK", responseEntity.getStatusCode().name());
		Assert.assertEquals(404, responseEntity.getBody().getStatusCode());
		Assert.assertEquals("Success", responseEntity.getBody().getStatus());
		Assert.assertEquals("Invalid Username and Password", responseEntity.getBody().getMessage());
		Assert.assertNotNull(responseEntity.getBody().getData());
		Assert.assertTrue(responseEntity.getBody().getData().isEmpty());
	}
}

package com.mindtree.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.mindtree.controller.ResponseDetail;
import com.mindtree.dao.EmployeeRepository;
import com.mindtree.entity.Employee;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeManagementServiceTest {
	
	@MockBean
	private EmployeeRepository repositoryMock;
	
	@Autowired
	private EmployeeManagementService service;
	
	@Test
	public void testAddEmployee() {
		Employee employee = new Employee();
		try {
			Mockito.when(repositoryMock.save(Mockito.any(Employee.class))).thenReturn(null);
			ResponseDetail response = service.addEmployee(employee);
			Assert.assertNotNull(response);
			Assert.assertEquals(200, response.getStatusCode());
			Assert.assertEquals("Success", response.getStatus());
			Assert.assertEquals("Employee data inserted successfully", response.getMessage());
			Assert.assertNotNull(response.getData());
			Assert.assertTrue(response.getData().isEmpty());
		} 
		catch (Exception ex) {
			Assert.fail();
		}
	}

	@Test
	public void testDeleteEmployee() {
		try {
			Mockito.doNothing().when(repositoryMock).delete(Mockito.anyString());
			ResponseDetail response = service.deleteEmployee("abc");
			Assert.assertNotNull(response);
			Assert.assertEquals(200, response.getStatusCode());
			Assert.assertEquals("Success", response.getStatus());
			Assert.assertEquals("Employee data deleted successfully", response.getMessage());
			Assert.assertNotNull(response.getData());
			Assert.assertTrue(response.getData().isEmpty());
		} 
		catch (Exception ex) {
			Assert.fail();
		}
	}
	
	@Test
	public void testGetAllEmployees() {
		Employee employee1 = new Employee("emp1", "paswrd1", "ABC", "abc@mail.com", "17/09/2000", "Female", "when", "then");
		Employee employee2 = new Employee("emp2", "paswrd2", "XYZ", "xyz@mail.com", "18/09/2000", "Male", "where", "there");
		List<Employee> employees = new ArrayList<Employee>();
		employees.add(employee1);
		employees.add(employee2);
		Mockito.when(repositoryMock.findAll()).thenReturn(employees);
		ResponseDetail response = service.getAllEmployees();
		Assert.assertNotNull(response);
		Assert.assertEquals(200, response.getStatusCode());
		Assert.assertEquals("Success", response.getStatus());
		Assert.assertEquals("[]", response.getMessage());
		Assert.assertNotNull(response.getData());
		Assert.assertFalse(response.getData().isEmpty());
		Assert.assertEquals(2, response.getData().size());
		Assert.assertEquals("emp1",response.getData().get(0).getUsername());
		Assert.assertEquals("paswrd1",response.getData().get(0).getPassword());
		Assert.assertEquals("ABC",response.getData().get(0).getFullName());
		Assert.assertEquals("abc@mail.com",response.getData().get(0).getEmailID());
		Assert.assertEquals("17/09/2000",response.getData().get(0).getDateOfBirth());
		Assert.assertEquals("Female",response.getData().get(0).getGender());
		Assert.assertEquals("when",response.getData().get(0).getSecurityQuestion());
		Assert.assertEquals("then",response.getData().get(0).getSecurityAnswer());
		Assert.assertEquals("emp2",response.getData().get(1).getUsername());
		Assert.assertEquals("paswrd2",response.getData().get(1).getPassword());
		Assert.assertEquals("XYZ",response.getData().get(1).getFullName());
		Assert.assertEquals("xyz@mail.com",response.getData().get(1).getEmailID());
		Assert.assertEquals("18/09/2000",response.getData().get(1).getDateOfBirth());
		Assert.assertEquals("Male",response.getData().get(1).getGender());
		Assert.assertEquals("where",response.getData().get(1).getSecurityQuestion());
		Assert.assertEquals("there",response.getData().get(1).getSecurityAnswer());
	}
	
	@Test
	public void testGetEmployee() {
		Employee employee = new Employee("emp1", "paswrd1", "ABC", "abc@mail.com", "17/09/2000", "Female", "when", "then");
		Mockito.when(repositoryMock.findOne(Mockito.anyString())).thenReturn(employee);
		ResponseDetail response = service.getEmployee("emp1");
		Assert.assertNotNull(response);
		Assert.assertEquals(200, response.getStatusCode());
		Assert.assertEquals("Success", response.getStatus());
		Assert.assertEquals("[]", response.getMessage());
		Assert.assertNotNull(response.getData());
		Assert.assertFalse(response.getData().isEmpty());
		Assert.assertEquals(1, response.getData().size());
		Assert.assertEquals("emp1",response.getData().get(0).getUsername());
		Assert.assertEquals("paswrd1",response.getData().get(0).getPassword());
		Assert.assertEquals("ABC",response.getData().get(0).getFullName());
		Assert.assertEquals("abc@mail.com",response.getData().get(0).getEmailID());
		Assert.assertEquals("17/09/2000",response.getData().get(0).getDateOfBirth());
		Assert.assertEquals("Female",response.getData().get(0).getGender());
		Assert.assertEquals("when",response.getData().get(0).getSecurityQuestion());
		Assert.assertEquals("then",response.getData().get(0).getSecurityAnswer());
	}
	
	@Test
	public void testCheckLoginForAuthenticUser() {
		List<Employee> employees = new ArrayList<Employee>();
		Employee employee = new Employee("emp1", "paswrd1", "ABC", "abc@mail.com", "17/09/2000", "Female", "when", "then");
		employees.add(employee);
		Mockito.when(repositoryMock.findOne(Mockito.anyString())).thenReturn(employee);
		ResponseDetail response = service.authenticateUser("emp1","paswrd1");
		Assert.assertNotNull(response);
		Assert.assertEquals(200, response.getStatusCode());
		Assert.assertEquals("Success", response.getStatus());
		Assert.assertEquals("Employee has authenticated successfully", response.getMessage());
		Assert.assertNotNull(response.getData());
		Assert.assertFalse(response.getData().isEmpty());
		Assert.assertEquals(1, response.getData().size());
		Assert.assertEquals("emp1",response.getData().get(0).getUsername());
		Assert.assertEquals("paswrd1",response.getData().get(0).getPassword());
		Assert.assertEquals("ABC",response.getData().get(0).getFullName());
		Assert.assertEquals("abc@mail.com",response.getData().get(0).getEmailID());
		Assert.assertEquals("17/09/2000",response.getData().get(0).getDateOfBirth());
		Assert.assertEquals("Female",response.getData().get(0).getGender());
		Assert.assertEquals("when",response.getData().get(0).getSecurityQuestion());
		Assert.assertEquals("then",response.getData().get(0).getSecurityAnswer());
	}
	
	@Test
	public void testCheckLoginForNonAuthenticUser() {
		List<Employee> employees = new ArrayList<Employee>();
		Employee employee = new Employee("emp1", "paswrd1", "ABC", "abc@mail.com", "17/09/2000", "Female", "when", "then");
		employees.add(employee);
		Mockito.when(repositoryMock.findOne(Mockito.anyString())).thenReturn(employee);
		ResponseDetail response = service.authenticateUser("emp1","paswrd2");
		Assert.assertNotNull(response);
		Assert.assertEquals(404, response.getStatusCode());
		Assert.assertEquals("Success", response.getStatus());
		Assert.assertEquals("Invalid Username and Password", response.getMessage());
		Assert.assertNotNull(response.getData());
		Assert.assertTrue(response.getData().isEmpty());
	}

}

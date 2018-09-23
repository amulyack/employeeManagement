package com.mindtree;


import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.mindtree.controller.EmployeeManagementController;
import com.mindtree.controller.ResponseDetail;
import com.mindtree.entity.Employee;
import com.mindtree.exceptions.EmployeeNotFoundException;
import com.mindtree.exceptions.InvalidRequestException;
import com.mindtree.service.EmployeeManagementService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = EmployeeManagementController.class, secure = false)
public class EmployeeManagementTest {
	
	@MockBean
	private EmployeeManagementService serviceMock;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void testAddEmployee() {
		try {
			String content = "{\"username\": \"emp1\",\"password\": \"paswrd1\",\"fullName\": \"ABC\",\"emailID\": \"abc@mail.com\",\"dateOfBirth\": \"24/09/1994\",\"gender\": \"Female\",\"securityQuestion\": \"when\",\"securityAnswer\": \"then\"}";
			ResponseDetail response = new ResponseDetail(200, "Success",
					"Employee data inserted successfully", null);
			Mockito.when(serviceMock.addEmployee(Mockito.any(Employee.class)))
					.thenReturn(response);
			RequestBuilder requestBuilder = MockMvcRequestBuilders
					.post("/EmpMgt/addEmp").accept(MediaType.APPLICATION_JSON)
					.content(content).contentType(MediaType.APPLICATION_JSON);
			MvcResult result = mockMvc.perform(requestBuilder).andReturn();
			Assert.assertNotNull(result);
			Assert.assertNotNull(result.getResponse());
			Assert.assertEquals(200, result.getResponse().getStatus());
			JSONAssert
					.assertEquals(
							"{ \"statusCode\": 200, \"status\": \"Success\", \"message\": \"Employee data inserted successfully\", \"data\": []}",
							result.getResponse().getContentAsString(), false);
		} catch (Exception ex) {
			Assert.fail();
		}
	}
	
	@Test
	public void testAddEmployeeForBadRequestException() {
		try {
			String content = "{\"username\": \"emp1\",\"password\": \"paswrd1\",\"fullName\": \"ABC\",\"emailID\": \"abc@mail.com\",\"dateOfBirth\": \"24/09/1994\",\"gender\": \"Female\",\"securityQuestion\": \"when\",\"securityAnswer\": \"then\"}";
			Mockito.when(serviceMock.addEmployee(Mockito.any(Employee.class)))
					.thenThrow(new InvalidRequestException("Input data mismatch"));
			RequestBuilder requestBuilder = MockMvcRequestBuilders
					.post("/EmpMgt/addEmp").accept(MediaType.APPLICATION_JSON)
					.content(content).contentType(MediaType.APPLICATION_JSON);
			MvcResult result = mockMvc.perform(requestBuilder).andReturn();
			Assert.assertNotNull(result);
			Assert.assertNotNull(result.getResponse());
			Assert.assertEquals(400, result.getResponse().getStatus());
			JSONAssert
					.assertEquals(
							"{ \"statusCode\": 400, \"status\": \"Failure\", \"message\": \"Input data mismatch\", \"data\": []}",
							result.getResponse().getContentAsString(), false);
		} 
		catch (Exception ex) {
			Assert.fail();
		}
	}

	@Test
	public void testDeleteEmployee() {
		try {
			ResponseDetail response = new ResponseDetail(200, "Success",
					"Employee data deleted successfully", null);
			Mockito.when(serviceMock.deleteEmployee(Mockito.anyString()))
					.thenReturn(response);
			RequestBuilder requestBuilder = MockMvcRequestBuilders
					.delete("/EmpMgt/deleteEmp/emp1").accept(MediaType.APPLICATION_JSON);
			MvcResult result = mockMvc.perform(requestBuilder).andReturn();
			Assert.assertNotNull(result);
			Assert.assertNotNull(result.getResponse());
			Assert.assertEquals(200, result.getResponse().getStatus());
			JSONAssert
			.assertEquals(
					"{ \"statusCode\": 200, \"status\": \"Success\", \"message\": \"Employee data deleted successfully\", \"data\": []}",
					result.getResponse().getContentAsString(), false);
		} 
		catch (Exception ex) {
			Assert.fail();
		}
	}
	
	@Test
	public void testDeleteEmployeeForException() {
		try {
			Mockito.when(serviceMock.deleteEmployee(Mockito.anyString()))
					.thenThrow(new Exception("There is some issue at server side. Please check the log"));
			RequestBuilder requestBuilder = MockMvcRequestBuilders
					.delete("/EmpMgt/deleteEmp/emp1").accept(MediaType.APPLICATION_JSON);
			MvcResult result = mockMvc.perform(requestBuilder).andReturn();
			Assert.assertNotNull(result);
			Assert.assertNotNull(result.getResponse());
			Assert.assertEquals(500, result.getResponse().getStatus());
			JSONAssert
			.assertEquals(
					"{ \"statusCode\": 500, \"status\": \"Failure\", \"message\": \"There is some issue at server side. Please check the log\", \"data\": []}",
					result.getResponse().getContentAsString(), false);
		} 
		catch (Exception ex) {
			Assert.fail();
		}
	}
	
	@Test
	public void testGetAllEmployees() {
		try {
			List<Employee> employees = new ArrayList<Employee>();
			Employee employee1 = new Employee("emp1", "paswrd1", "ABC", "abc@mail.com", "17/09/2000", "Female", "when", "then");
			Employee employee2 = new Employee("emp2", "paswrd2", "XYZ", "xyz@mail.com", "18/09/2000", "Male", "where", "there");
			employees.add(employee1);
			employees.add(employee2);
			ResponseDetail response = new ResponseDetail(200, "Success",
					"[]", employees);
			Mockito.when(serviceMock.getAllEmployees())
					.thenReturn(response);
			RequestBuilder requestBuilder = MockMvcRequestBuilders
					.get("/EmpMgt/getAllEmpDetails").accept(MediaType.APPLICATION_JSON);
			MvcResult result = mockMvc.perform(requestBuilder).andReturn();
			Assert.assertNotNull(result);
			Assert.assertNotNull(result.getResponse());
			Assert.assertEquals(200, result.getResponse().getStatus());
			JSONAssert
			.assertEquals(
					"{ \"statusCode\": 200, \"status\": \"Success\", \"message\": \"[]\", \"data\": [{\"username\": \"emp1\",\"password\": \"paswrd1\",\"fullName\": \"ABC\",\"emailID\": \"abc@mail.com\",\"dateOfBirth\": \"17/09/2000\",\"gender\": \"Female\",\"securityQuestion\": \"when\",\"securityAnswer\": \"then\"}, {\"username\": \"emp2\",\"password\": \"paswrd2\",\"fullName\": \"XYZ\",\"emailID\": \"xyz@mail.com\",\"dateOfBirth\": \"18/09/2000\",\"gender\": \"Male\",\"securityQuestion\": \"where\",\"securityAnswer\": \"there\"}]}",
					result.getResponse().getContentAsString(), false);
		} 
		catch (Exception ex) {
			Assert.fail();
		}
	}
	
	@Test
	public void testGetAllEmployeesForException() {
		try {
			Mockito.when(serviceMock.getAllEmployees())
					.thenThrow(new EmployeeNotFoundException("No Employees exsist"));
			RequestBuilder requestBuilder = MockMvcRequestBuilders
					.get("/EmpMgt/getAllEmpDetails").accept(MediaType.APPLICATION_JSON);
			MvcResult result = mockMvc.perform(requestBuilder).andReturn();
			Assert.assertNotNull(result);
			Assert.assertNotNull(result.getResponse());
			Assert.assertEquals(404, result.getResponse().getStatus());
			JSONAssert
			.assertEquals(
					"{ \"statusCode\": 404, \"status\": \"Failure\", \"message\": \"No Employees exsist\", \"data\": []}",
					result.getResponse().getContentAsString(), false);
		} 
		catch (Exception ex) {
			Assert.fail();
		}
	}
	
	@Test
	public void testGetEmployee() {
		try {
			List<Employee> employees = new ArrayList<Employee>();
			Employee employee1 = new Employee("emp1", "paswrd1", "ABC", "abc@mail.com", "17/09/2000", "Female", "when", "then");
			employees.add(employee1);
			ResponseDetail response = new ResponseDetail(200, "Success",
					"[]", employees);
			Mockito.when(serviceMock.getEmployee(Mockito.anyString()))
					.thenReturn(response);
			RequestBuilder requestBuilder = MockMvcRequestBuilders
					.get("/EmpMgt/getByEmpId/emp1").accept(MediaType.APPLICATION_JSON);
			MvcResult result = mockMvc.perform(requestBuilder).andReturn();
			Assert.assertNotNull(result);
			Assert.assertNotNull(result.getResponse());
			Assert.assertEquals(200, result.getResponse().getStatus());
			JSONAssert
			.assertEquals(
					"{ \"statusCode\": 200, \"status\": \"Success\", \"message\": \"[]\", \"data\": [{\"username\": \"emp1\",\"password\": \"paswrd1\",\"fullName\": \"ABC\",\"emailID\": \"abc@mail.com\",\"dateOfBirth\": \"17/09/2000\",\"gender\": \"Female\",\"securityQuestion\": \"when\",\"securityAnswer\": \"then\"}]}",
					result.getResponse().getContentAsString(), false);
		} 
		catch (Exception ex) {
			Assert.fail();
		}
	}
	
	@Test
	public void testGetEmployeeForException() {
		try {
			Mockito.when(serviceMock.getAllEmployees())
					.thenThrow(new EmployeeNotFoundException("The Employee doesnt exsist"));
			RequestBuilder requestBuilder = MockMvcRequestBuilders
					.get("/EmpMgt/getAllEmpDetails").accept(MediaType.APPLICATION_JSON);
			MvcResult result = mockMvc.perform(requestBuilder).andReturn();
			Assert.assertNotNull(result);
			Assert.assertNotNull(result.getResponse());
			Assert.assertEquals(404, result.getResponse().getStatus());
			JSONAssert
			.assertEquals(
					"{ \"statusCode\": 404, \"status\": \"Failure\", \"message\": \"The Employee doesnt exsist\", \"data\": []}",
					result.getResponse().getContentAsString(), false);
		} 
		catch (Exception ex) {
			Assert.fail();
		}
	}
	
	@Test
	public void testCheckLoginForAuthenticUser() {
		try {
			String content = "{\"username\": \"emp1\",\"password\": \"paswrd1\"}";
			List<Employee> employees = new ArrayList<Employee>();
			Employee employee1 = new Employee("emp1", "paswrd1", "ABC", "abc@mail.com", "17/09/2000", "Female", "when", "then");
			employees.add(employee1);
			ResponseDetail response = new ResponseDetail(200, "Success",
					"Employee has authenticated successfully", employees);
			Mockito.when(serviceMock.authenticateUser(Mockito.anyString(), Mockito.anyString()))
					.thenReturn(response);
			RequestBuilder requestBuilder = MockMvcRequestBuilders
					.post("/EmpMgt/checkLogin").accept(MediaType.APPLICATION_JSON).content(content).contentType(MediaType.APPLICATION_JSON);
			MvcResult result = mockMvc.perform(requestBuilder).andReturn();
			Assert.assertNotNull(result);
			Assert.assertNotNull(result.getResponse());
			Assert.assertEquals(200, result.getResponse().getStatus());
			JSONAssert
			.assertEquals(
					"{ \"statusCode\": 200, \"status\": \"Success\", \"message\": \"Employee has authenticated successfully\", \"data\": [{\"username\": \"emp1\",\"password\": \"paswrd1\",\"fullName\": \"ABC\",\"emailID\": \"abc@mail.com\",\"dateOfBirth\": \"17/09/2000\",\"gender\": \"Female\",\"securityQuestion\": \"when\",\"securityAnswer\": \"then\"}]}",
					result.getResponse().getContentAsString(), false);
		} 
		catch (Exception ex) {
			Assert.fail();
		}
	}
	
	@Test
	public void testCheckLoginForNonAuthenticUser() {
		try {
			String content = "{\"username\": \"emp1\",\"password\": \"paswrd1\"}";
			ResponseDetail response = new ResponseDetail(404, "Success",
					"Invalid Username and Password", null);
			Mockito.when(serviceMock.authenticateUser(Mockito.anyString(), Mockito.anyString()))
					.thenReturn(response);
			RequestBuilder requestBuilder = MockMvcRequestBuilders
					.post("/EmpMgt/checkLogin").accept(MediaType.APPLICATION_JSON).content(content).contentType(MediaType.APPLICATION_JSON);
			MvcResult result = mockMvc.perform(requestBuilder).andReturn();
			Assert.assertNotNull(result);
			Assert.assertNotNull(result.getResponse());
			Assert.assertEquals(200, result.getResponse().getStatus());
			JSONAssert
			.assertEquals(
					"{ \"statusCode\": 404, \"status\": \"Success\", \"message\": \"Invalid Username and Password\", \"data\": []}",
					result.getResponse().getContentAsString(), false);
		} 
		catch (Exception ex) {
			Assert.fail();
		}
	}
}


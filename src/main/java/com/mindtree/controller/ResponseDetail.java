package com.mindtree.controller;

import java.util.ArrayList;
import java.util.List;

import com.mindtree.entity.Employee;

public class ResponseDetail {
	
	private int statusCode;
	private String status;
	private String message;
	private List<Employee> data;
	
	public ResponseDetail() {
		
	}
	
	public ResponseDetail(int statusCode, String status, String message,
			List<Employee> data) {
		super();
		this.statusCode = statusCode;
		this.status = status;
		this.message = message;
		if (data == null) {
			this.data = new ArrayList<Employee>();
		} else {
			this.data = data;
		}
	}
	
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<Employee> getData() {
		return data;
	}
	public void setData(List<Employee> data) {
		this.data = data;
	}

}

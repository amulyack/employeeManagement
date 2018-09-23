package com.mindtree.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Employee {

	@Id
	private String username;
	private String password;
	private String fullName;
	private String emailID;
	private String dateOfBirth;
	private String gender;
	private String securityQuestion;
	private String securityAnswer;
	
	public Employee() {
		
	}
	
	public Employee(String username, String password, String fullName,
			String emailID, String dateOfBirth, String gender,
			String securityQuestion, String securityAnswer) {
		super();
		this.username = username;
		this.password = password;
		this.fullName = fullName;
		this.emailID = emailID;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.securityQuestion = securityQuestion;
		this.securityAnswer = securityAnswer;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getEmailID() {
		return emailID;
	}
	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getSecurityQuestion() {
		return securityQuestion;
	}
	public void setSecurityQuestion(String securityQuestion) {
		this.securityQuestion = securityQuestion;
	}
	public String getSecurityAnswer() {
		return securityAnswer;
	}
	public void setSecurityAnswer(String securityAnswer) {
		this.securityAnswer = securityAnswer;
	}
}

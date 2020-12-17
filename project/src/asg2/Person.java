package asg2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bc.ext.DatabaseInfo;


public class Person {
	

	private String personCode;
	private String lastName;
	private String firstName;
	private Address address;
	private String[] emailAddress;
	
	

	public String getPersonCode() {
		return personCode;
	}

	public Person(String personCode, String lastName, String firstName, Address address, String[] emailAddress) {
	 	this.personCode = personCode;
		this.lastName = lastName;
		this.firstName = firstName;
		this.address = address;
		this.emailAddress = emailAddress;
	}
	

	public void setPersonCode(String personCode) {
		this.personCode = personCode;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String[] getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String[] emailAddress) {
		this.emailAddress = emailAddress;
	}
	

	public Address getAddress() {
		return address;
	}


	public void setAddress(Address address) {
		this.address = address;
	}
	
	public Person(String personCode, String lastName, String firstName) {
	 	this.personCode = personCode;
		this.lastName = lastName;
		this.firstName = firstName;

	}



}


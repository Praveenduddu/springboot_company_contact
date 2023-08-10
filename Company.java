package de.zeroco.companycontact.entity;

import org.springframework.stereotype.Component;

@Component
public class Company {

	private int id;
	private String name;
	private String email;
	private long number;
	private String address;
	
	public Company() {
		
	}
	
	public Company(int id, String name, String email, long number, String address) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.number = number;
		this.address = address;
	}

	public Company(String name, String email, long number, String address) {
		this.name = name;
		this.email = email;
		this.number = number;
		this.address = address;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getNumber() {
		return number;
	}

	public void setNumber(long number) {
		this.number = number;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
}

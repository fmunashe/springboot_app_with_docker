package com.example.experiment.models;

import net.bytebuddy.utility.RandomString;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "suppliers")
public class Supplier {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	@NotBlank(message = "required")
	private String tradeName;
	@NotBlank(message = "required")
	private String contact_person;
	@NotBlank(message = "required")
	@Email
	private String email;
	@NotBlank(message = "required")
	private String phone;
	@NotBlank(message = "required")
	private String address;
	@NotBlank(message = "required")
	private String country;
	
	public Supplier() {
	}

	public Supplier(long id, String trade_name, String contact_person, String email, String phone, String address,
			String country) {
		this.id = id;
		this.tradeName = trade_name;
		this.contact_person = contact_person;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.country = country;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTradeName() {
		return tradeName;
	}

	public void setTradeName(String trade_name) {
		this.tradeName = trade_name;
	}

	public String getContact_person() {
		return contact_person;
	}

	public void setContact_person(String contact_person) {
		this.contact_person = contact_person;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
}

package com.niit.collaboration.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name="c_user")
@Component
public class User extends BaseDomain {

	@Id
	private String id;
	private String name;
	private String email;
	private String dob;
	private String mob_no;
	private String password;
	private char isOnline;
	private String gender;
	private String role;
	private String Address;
	private char status;
	
    @Transient
    private MultipartFile image;
    
	public MultipartFile getImage() {
		return image;
	}
	public void setImage(MultipartFile image) {
		this.image = image;
	}

	
	public String getId() {
	return id;
}



public void setId(String id) {
	this.id = id;
}



	public char getStatus() {
		return status;
	}



	public void setStatus(char status) {
		this.status = status;
	}



	public String getAddress() {
		return Address;
	}



	public void setAddress(String address) {
		Address = address;
	}



	


	public User() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
		Date date = new Date();
		
	dob = dateFormat.format(date);
				System.out.println("dateofbirth : " + dob);
	}
	
	
	
	
	public char getIsOnline() {
		return isOnline;
	}



	public void setIsOnline(char isOnline) {
		this.isOnline = isOnline;
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


	public String getMob_no() {
		return mob_no;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public void setMob_no(String mob_no) {
		this.mob_no = mob_no;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}	
		
	
}

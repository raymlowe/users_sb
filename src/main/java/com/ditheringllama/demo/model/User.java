package com.ditheringllama.demo.model;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="app_auth_user")
public class User{

	@Id
	@Column(name="id")
	private String id;
	
	@Column(name="user_id")
	private String userId;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="email")
	private String email;
	
	@Column(name="phone")
	private String phone;
	
	@Column(name="user_type")
	private String userType;
	
	@Column(name="is_active")
	private String isActive;
	
	//Users is the owner of this relationship
	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinTable(
		name="app_auth_user_group",
		joinColumns = {@JoinColumn(name="user_id")},
		inverseJoinColumns = {@JoinColumn(name="group_id")})
	private List<Group> group;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFirstname() {
		return firstName;
	}

	public void setFirstname(String first_name) {
		this.firstName = first_name;
	}

	public String getLastname() {
		return lastName;
	}

	public void setLastname(String last_name) {
		this.lastName = last_name;
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

	public String getUsertype() {
		return userType;
	}

	public void setUsertype(String userType) {
		this.userType = userType;
	}

	public List<Group> getGroup() {
		return group;
	}

	public void setGroup(List<Group> group) {
		this.group = group;
	}

	public String getIsactive() {
		return isActive;
	}

	public void setIsactive(String isActive) {
		this.isActive = isActive;
	}
}

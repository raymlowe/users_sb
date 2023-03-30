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
@Table(name = "app_auth_group")
public class Group{
	
	@Id
	@Column(name="id")
	private String id;
	
	@Column(name="group_name")
	private String groupName;
	
	@Column(name="group_description")
	private String groupDescription;
	
	//Users is the owner of this relationship - adding a user here does nothing
	@ManyToMany(mappedBy = "group")
	private List<User> user;
	
	//Group is the owner of this relationship
	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinTable(
		name="app_auth_group_role",
		joinColumns = {@JoinColumn(name="group_id")},
		inverseJoinColumns = {@JoinColumn(name="role_id")})
	private List<Role> role;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupDescription() {
		return groupDescription;
	}

	public void setGroupDescription(String groupDescription) {
		this.groupDescription = groupDescription;
	}

	public List<User> getUser() {
		return user;
	}

	public void setUser(List<User> user) {
		this.user = user;
	}

	public List<Role> getRole() {
		return role;
	}

	public void setRole(List<Role> role) {
		this.role = role;
	}

}

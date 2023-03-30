package com.ditheringllama.demo.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="app_auth_role")
public class Role{
	
	@Id
	@Column(name="id")
	private String id;
	
	@Column(name="role_name")
	private String roleName;
	
	@Column(name="role_description")
	private String roleDescription;
	
	//Group is the owner of this relationship
	@ManyToMany(mappedBy = "role")
	private List<Group> group;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDescription() {
		return roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

	public List<Group> getGroup() {
		return group;
	}

	public void setGroup(List<Group> group) {
		this.group = group;
	}
}

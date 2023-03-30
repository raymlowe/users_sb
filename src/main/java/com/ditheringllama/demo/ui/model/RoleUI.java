package com.ditheringllama.demo.ui.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RoleUI {
	
	@JsonProperty("id")
	private String id;
	
	@JsonProperty("roleName")
	private String roleName;
	
	@JsonProperty("roleDescription")
	private String roleDescription;

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

}

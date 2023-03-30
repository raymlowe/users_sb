package com.ditheringllama.demo.utils;

import com.ditheringllama.demo.model.Role;
import com.ditheringllama.demo.ui.model.RoleUI;

public class RoleUIObjectTransform {
	public static RoleUI convertToRoleUI(Role role) {
		RoleUI roleUI = new RoleUI();
		roleUI.setId(role.getId());
		roleUI.setRoleName(role.getRoleName().toUpperCase());
		roleUI.setRoleDescription(role.getRoleDescription());
		return roleUI;
	}
	
	public static Role convertUIToRole(RoleUI roleUI) {
		Role role = new Role();
		role.setId(roleUI.getId());
		role.setRoleName(roleUI.getRoleName().toUpperCase());
		role.setRoleDescription(roleUI.getRoleDescription());
		return role;
	}
}

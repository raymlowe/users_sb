package com.ditheringllama.demo.service.impl;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ditheringllama.demo.dao.RoleDAO;
import com.ditheringllama.demo.model.Role;
import com.ditheringllama.demo.service.RoleService;
import com.ditheringllama.demo.ui.model.RoleUI;
import com.ditheringllama.demo.utils.GeneralUtils;
import com.ditheringllama.demo.utils.RoleUIObjectTransform;



@Component("roleService")
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDAO roleDAO;

	@Override
	public Role saveRole(Role role) {
		try {
			roleDAO.save(role);
		} catch (Exception e) {

		}
		return role;
	}

	@Override
	public RoleUI saveRole(RoleUI roleUI) {
		// Validate Role Name
		// If you attempt to save a role with no ID and an existing groupName - reject
		if (roleUI.getId() == "" || roleUI.getId() == null) {
			Role role = null;
			try {
				role = roleDAO.getByName(roleUI.getRoleName().toUpperCase());
			} catch (Exception e) {

			}
			if (role != null) {
				// User tried to insert a group with the same name as an
				// existing group without providing the ID
				// This is not allowed
				return null;
			}
		}

		// Create the role
		if (roleUI.getId() == "" || roleUI.getId() == null) {
			String UUID = GeneralUtils.generateUUID();
			roleUI.setId(UUID);
		}
		try {
			Role saveRole = RoleUIObjectTransform.convertUIToRole(roleUI);
			roleDAO.save(saveRole);
		} catch (Exception e) {

		}
		return roleUI;
	}

	@Override
	public List<Role> getAllRoles() {
		List<Role> roles = new ArrayList<>();
		try {
			roles = roleDAO.getAllRoles();
		} catch (Exception e) {

		}
		return roles;
	}

	@Override
	public List<RoleUI> getAllRolesUI() {
		List<Role> roles = new ArrayList<>();
		List<RoleUI> rolesUIs = new ArrayList<>();
		try {
			roles = roleDAO.getAllRoles();
		} catch (Exception e) {

		}
		for (Role role : roles) {
			RoleUI uiOject = RoleUIObjectTransform.convertToRoleUI(role);
			rolesUIs.add(uiOject);
		}
		return rolesUIs;
	}

	@Override
	public Role getRoleById(String id) {
		try {
			Role role = roleDAO.get(id);
			return role;
		} catch (Exception e) {

		}
		return null;
	}

	@Override
	public boolean deleteRole(String id) {
		try {
			return roleDAO.delete(id);
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public RoleUI updateRole(RoleUI roleUI) {
		roleDAO.updateRole(roleUI);
		return roleUI;
	}

}

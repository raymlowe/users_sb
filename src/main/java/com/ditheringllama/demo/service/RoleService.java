package com.ditheringllama.demo.service;

import java.util.List;

import com.ditheringllama.demo.model.Role;
import com.ditheringllama.demo.ui.model.RoleUI;



public interface RoleService {

	/**
	 * 
	 * @param role
	 * @return
	 */
	public Role saveRole(Role role);
	
	/**
	 * 
	 * @param roleUI
	 * @return
	 */
	public RoleUI saveRole(RoleUI roleUI);
	
	/**
	 * 
	 * @return
	 */
	public List<Role> getAllRoles();
	
	/**
	 * 
	 * @return
	 */
	public List<RoleUI> getAllRolesUI();
	
	/**
	 * 
	 * @param roleName
	 * @return
	 */
	public Role getRoleById(String id);
	
	/**
	 * 
	 * @param role
	 * @return
	 */
	public boolean deleteRole(String id);
	
	/**
	 * 
	 * @param roleUI
	 * @return
	 */
	public RoleUI updateRole(RoleUI roleUI);
}

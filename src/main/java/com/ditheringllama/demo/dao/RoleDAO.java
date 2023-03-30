package com.ditheringllama.demo.dao;

import java.util.List;

import com.ditheringllama.demo.model.Role;
import com.ditheringllama.demo.ui.model.RoleUI;

public interface RoleDAO {

	/**
	 * 
	 * @param roleId
	 * @return
	 */
	public Role get(String id);
	
	/**
	 * 
	 * @param roleName
	 * @return
	 */
	public Role getByName(String roleName);
	
	/**
	 * 
	 * @return
	 */
	public List<Role> getAllRoles();
	
	/**
	 * 
	 * @param role
	 */
	public void save (Role role);
	
	/**
	 * 
	 * @param role
	 */
	public boolean delete(final String id);
	
	/**
	 * 
	 * @param roleUI
	 * @return
	 */
	public boolean updateRole(RoleUI roleUI);
}

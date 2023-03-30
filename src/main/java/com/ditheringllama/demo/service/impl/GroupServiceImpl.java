package com.ditheringllama.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ditheringllama.demo.dao.GroupDAO;
import com.ditheringllama.demo.dao.RoleDAO;
import com.ditheringllama.demo.model.Group;
import com.ditheringllama.demo.model.Role;
import com.ditheringllama.demo.service.GroupService;
import com.ditheringllama.demo.ui.model.GroupUI;
import com.ditheringllama.demo.ui.model.RoleUI;
import com.ditheringllama.demo.utils.GeneralUtils;
import com.ditheringllama.demo.utils.GroupUIObjectTransform;
import com.ditheringllama.demo.utils.RoleUIObjectTransform;



@Component("groupService")
public class GroupServiceImpl implements GroupService {

	@Autowired
	GroupDAO groupDAO;
	
	@Autowired 
	RoleDAO roleDAO;

	@Override
	public Group saveGroup(Group group) {
		try {
			groupDAO.save(group);
		} catch (Exception e) {

		}
		return group;
	}

	@Override
	public GroupUI saveGroup(GroupUI groupUI) {
		// Validate Group Name
		//If you attempt to save a group with no ID and an existing groupName - reject
		if (groupUI.getId() == "" || groupUI.getId() == null) {
			Group group  = null;
			try {
				group = groupDAO.getByName(groupUI.getGroupName().toUpperCase());
			} catch (Exception e) {
				
			}
			if (group != null) {
				//If a group with this name is found, and no ID was provided
				//Then we are trying to create a new group with the same name
				//This is not allowed
				return null;
			}
			
		}

		if (groupUI.getId() == "" || groupUI.getId() == null) {
			String UUID = GeneralUtils.generateUUID();
			groupUI.setId(UUID);
		}
		try {
			Group saveGroup = GroupUIObjectTransform.convertUIToGroup(groupUI);
			groupDAO.save(saveGroup);
		} catch (Exception e) {

		}
		return groupUI;
	}

	@Override
	public List<Group> getAllGroups() {
		List<Group> returnGroups = new ArrayList<>();
		try {
			returnGroups = groupDAO.getAllGroups();
		} catch (Exception e) {

		}
		return returnGroups;
	}

	@Override
	public List<GroupUI> getAllGroupsUI() {
		List<Group> returnGroups = new ArrayList<>();
		List<GroupUI> returnGroupUIs = new ArrayList<>();
		try {
			returnGroups = groupDAO.getAllGroups();
		} catch (Exception e) {

		}
		for (Group grp : returnGroups) {
			GroupUI uiObject = GroupUIObjectTransform.convertToGroupUI(grp);
			returnGroupUIs.add(uiObject);
		}
		return returnGroupUIs;
	}

	@Override
	public Group getGroupById(String id) {
		try {
			Group returnGroup = groupDAO.get(id);
			return returnGroup;
		} catch (Exception e) {

		}
		return null;
	}
	
	@Override
	public GroupUI getGroupUIById(String id) {
		try {
			Group returnGroup = groupDAO.get(id);
			GroupUI returnGroupUI = GroupUIObjectTransform.convertToGroupUI(returnGroup);
			return returnGroupUI;
		} catch (Exception e) {
			
		}
		return null;
	}


	@Override
	public List<RoleUI> addRoleToGroup(String groupId, String roleId) {
		Group group = new Group();
		List<RoleUI> roleUIs = new ArrayList<>();
		try {
			group = groupDAO.get(groupId);
			List<Role> groupRoles = group.getRole();
			Role role = roleDAO.get(roleId);
			groupRoles.add(role);
			groupDAO.save(group);
		}catch (Exception e) {
			
		}
		if(group != null) {
			List<Role> roles = group.getRole();
			for(Role role : roles) {
				RoleUI roleUI = RoleUIObjectTransform.convertToRoleUI(role);
				roleUIs.add(roleUI);
			}
		}
		return roleUIs;
	}

	@Override
	public List<RoleUI> removeRoleFromGroup(String groupId, String roleId) {
		Group group = new Group();
		List<RoleUI> roleUIs = new ArrayList<>();
		List<Role> acceptedRoles = new ArrayList<>();
		try {
			group=groupDAO.get(groupId);
			List<Role> existingRoles = group.getRole();
			for(Role role:existingRoles) {
				if(!(role.getId().equalsIgnoreCase(roleId))) {
					acceptedRoles.add(role);
				}
			}
			group.setRole(acceptedRoles);
			groupDAO.save(group);
		}catch(Exception e) {
			
		}
		//Handle Return
		if(group != null) {
			List<Role> roles = group.getRole();
			for(Role role : roles) {
				RoleUI roleUI = RoleUIObjectTransform.convertToRoleUI(role);
				roleUIs.add(roleUI);
			}
		}
		return roleUIs;
	}
	
	@Override
	public boolean deleteGroup(String id) {
		try {
			return groupDAO.delete(id);
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<RoleUI> getRoleUIsForGroup(String id) {
		List<RoleUI> returnRoleUI = new ArrayList<>();
		try {
			Group grp = groupDAO.get(id);
			List<Role> roles = grp.getRole();
			for(Role role : roles) {
				returnRoleUI.add(RoleUIObjectTransform.convertToRoleUI(role));
			}
			return returnRoleUI;
			
		}catch (Exception e) {
			
		}
		return returnRoleUI;
	}

	@Override
	public List<RoleUI> getAddableRolesForGroup(String id) {
		List<RoleUI> returnList = new ArrayList<>();
		Group group = groupDAO.get(id);
		List<Role> allRoles = roleDAO.getAllRoles();
		
		for(Role role : allRoles) {
			Boolean matched = false;
			for(Role groupRole : group.getRole()) {
				if(role.getId().equalsIgnoreCase(groupRole.getId())) {
					matched = true;
				}
			}
			if(matched == false) {
				returnList.add(RoleUIObjectTransform.convertToRoleUI(role));
			}
				
		}
		return returnList;
	}

	@Override
	public GroupUI updateGroup(GroupUI groupUI) {
		groupDAO.updateGroup(groupUI);
		return groupUI;
	}

}
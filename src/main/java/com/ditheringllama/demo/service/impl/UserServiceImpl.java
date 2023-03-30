package com.ditheringllama.demo.service.impl;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ditheringllama.demo.dao.GroupDAO;
import com.ditheringllama.demo.dao.UserDAO;
import com.ditheringllama.demo.model.Group;
import com.ditheringllama.demo.model.Role;
import com.ditheringllama.demo.model.User;
import com.ditheringllama.demo.service.UserService;
import com.ditheringllama.demo.ui.model.GroupUI;
import com.ditheringllama.demo.ui.model.UserUI;
import com.ditheringllama.demo.utils.GeneralUtils;
import com.ditheringllama.demo.utils.GroupUIObjectTransform;
import com.ditheringllama.demo.utils.UserUIObjectTransform;



@Component("userService")
public class UserServiceImpl implements UserService{

	@Autowired
	UserDAO userDAO;
	
	@Autowired
	GroupDAO groupDAO;
	
	@Override
	public User saveUser(User user) {
		try {
			userDAO.saveUser(user);
		}catch(Exception e) {
			//do nothing
		}
		return user;
	}
	
	/**
	 * Sample Path: http://localhost:8080/orbeonrpt/user/create
	 * Sample Payload: {"userId":"ralowe","firstName":"Raymond","lastName":"Lowe","email":"raymond.lowe@gov.bc.ca","phone":"999-8888-7777","userType":"IDIR","isActive":"Y"}
	 * HEADER: content-type : application/json
	 */
	@Override
	public UserUI saveUser (UserUI userUI) {
		//Validate User Name
		//TODO
		if(this.getUserByUserId(userUI.getUserId())!=null) {
			//Tried to add a user that already exists
			return userUI;
		}
				
				
		if(userUI.getId() == "" || userUI.getId() == null) {
			String UUID = GeneralUtils.generateUUID();
			userUI.setId(UUID);
		}
		try {
			User saveuser = UserUIObjectTransform.convertToUser(userUI);
			String userID = saveuser.getUserId();
			saveuser.setUserId(userID.toUpperCase());
			userDAO.saveUser(saveuser);
		}catch(Exception e) {
			
		}
		return userUI;
	}
	
	/**
	 * 
	 */
	@Override
	public UserUI updateUser(UserUI userUI) {
		userDAO.updateUser(userUI);
		return userUI;
	}

	@Override
	public User getUserByUserId(String userId) {
		try {
			return userDAO.getUser(userId);
		}catch (Exception e) {
			
		}
		return null;
	}
	
	@Override
	public UserUI getUserUIByUserId(String userId) {
		try {
			User user = userDAO.getUser(userId);
			UserUI userUI = UserUIObjectTransform.converToUserUI(user);
			return userUI;
		}catch (Exception e) {
			
		}
		return null;
	}

	@Override
	public List<GroupUI> getUserGroupUIs(String userId) {
		//User IDs are always stored in uppercase
		userId = userId.toUpperCase();
		List<GroupUI> groupUIList = new ArrayList<>();
		
		try {
			User user = userDAO.getUser(userId);
			for(Group group : user.getGroup()) {
				GroupUI groupUI = GroupUIObjectTransform.convertToGroupUI(group);
				groupUIList.add(groupUI);
			}
		}catch(Exception e) {
			
		}
		return groupUIList;
	}
	
	@Override
	public List<String> getUserGroupNames(String userId) {
		userId = userId.toUpperCase();
		List<String> groupNames = new ArrayList<>();
		
		try {
			User user = userDAO.getUser(userId);
			for(Group group : user.getGroup()) {
				GroupUI groupUI = GroupUIObjectTransform.convertToGroupUI(group);
				groupNames.add(groupUI.getGroupName());
			}
		} catch(Exception e) {
			
		}
		return groupNames;
	}
	

	@Override
	public List<String> getAmalgamatedRoles(String userId) {
		userId = userId.toUpperCase();
		User user = null;
		List<String> roleNames = new ArrayList<>();
		
		try {
			user = userDAO.getUser(userId);
			for(Group group : user.getGroup()) {
				List<Role> roles = group.getRole();
				for(Role role : roles) {
					roleNames.add(role.getRoleName());
				}
			}
		}catch(HibernateException he) {
			//If there is an error, do nothing
		}catch(Exception e) {
			//If there is an error, do nothing
		}
		return roleNames;
	}

	@Override
	public List<GroupUI> addGroupToUser(String userId, String groupId) {
		User user = new User();
		List<GroupUI> groupUIs = new ArrayList<>();
		try {
			user = userDAO.get(userId);
			Group groupToBeAdded = groupDAO.get(groupId);
			List<Group> userGroups = user.getGroup();
			userGroups.add(groupToBeAdded);
			userDAO.saveUser(user);
		}catch (Exception e) {
			
		}
		//Handle Return
		if(user != null) {
			List<Group> groups = user.getGroup();
			for(Group group : groups) {
				GroupUI groupUI = GroupUIObjectTransform.convertToGroupUI(group);
				groupUIs.add(groupUI);
			}
		}

		return groupUIs;
	}

	@Override
	public List<GroupUI> removeGroupFromUser(String userId, String groupId) {
		User user = new User();
		List<GroupUI> groupUIs = new ArrayList<>();
		List<Group> acceptedGroups = new ArrayList<>();
		try {
			user=userDAO.get(userId);
			List<Group> existingGroups = user.getGroup();
			for(Group group : existingGroups) {
				if(!(group.getId().equalsIgnoreCase(groupId))){
					acceptedGroups.add(group);
				}
			}
			user.setGroup(acceptedGroups);
			userDAO.saveUser(user);
			
		}catch(Exception e) {
			
		}
		//Handle Return
		if(user != null) {
			List<Group> groups = user.getGroup();
			for(Group group : groups) {
				GroupUI groupUI = GroupUIObjectTransform.convertToGroupUI(group);
				groupUIs.add(groupUI);
			}
		}
		return groupUIs;
	}

	@Override
	public boolean deleteUser(String id) {
		try {
			return userDAO.delete(id);
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<UserUI> getAllUsers() {
		List<UserUI> userUIs = new ArrayList<>();
		try {
			List<User> users = userDAO.getAllUsers();
			for(User user : users) {
				UserUI ui = UserUIObjectTransform.converToUserUI(user);
				userUIs.add(ui);
			}
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
		return userUIs;
	}

	@Override
	public List<GroupUI> getAddableGroups(String userId) {
		List<GroupUI> returnList = new ArrayList<>();
		User user = userDAO.getUser(userId);
		List<Group> allGroups = groupDAO.getAllGroups();
		
		for(Group group : allGroups) {
			Boolean matched = false;
			for(Group userGroup : user.getGroup()) {
				if(group.getId().equalsIgnoreCase(userGroup.getId())) {
					matched = true;
				}
			}
			//If this group does not belong to the user
			if(matched == false) {
				returnList.add(GroupUIObjectTransform.convertToGroupUI(group));
			}
		}

		return returnList;
	}

	@Override
	public User get(String id) {
		return userDAO.get(id);
	}

}

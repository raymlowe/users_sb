package com.ditheringllama.demo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ditheringllama.demo.service.UserService;
import com.ditheringllama.demo.ui.model.GroupUI;
import com.ditheringllama.demo.ui.model.UserUI;
import com.ditheringllama.demo.utils.GeneralUtils;


@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/ping", method = RequestMethod.GET)
	public ResponseEntity<String> alive(HttpServletRequest request){
		return ResponseEntity.ok().body("Alive");
	}
	
	@RequestMapping(value = "/test", method = RequestMethod.POST)
	public ResponseEntity<String> test(@RequestBody  String testVar){
		return ResponseEntity.ok().body(testVar);
	}
	
	@RequestMapping(value = "/getAllUsers", method = RequestMethod.GET)
	public ResponseEntity<List<UserUI>> getAllUsers(ModelAndView model) throws IOException{
		List<UserUI> returnList = new ArrayList<>();
		returnList = userService.getAllUsers();
		return ResponseEntity.ok().body(returnList);
	}
	
	/**
	 * Path: http://localhost:8080/orbeonrpt/user/create
	 * Headers:
	 * 		content-type:application/json
	 * Body:
	 * 		raw/JSON
	 * 		{"userId":"ralowe","firstName":"Raymond","lastName":"Lowe","email":"raymond.lowe@gov.bc.ca","phone":"999-8888-7777","userType":"IDIR","isActive":"Y"}
	 * @param userUI
	 * @return
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<UserUI> create(@RequestBody(required=false) UserUI userUI){
		//We need to ensure the userID.id is null for create new
		userUI.setId(null);
		UserUI savedUser = userService.saveUser(userUI);
		return ResponseEntity.ok().body(savedUser);
	}
	
	/**
	 * Path: http://localhost:8080/orbeonrpt/user/update
	 * Headers:
	 * 		content-type:application/json
	 * Body:
	 * 		raw/JSON
	 * 		{"id":"D3B818AD2C264BA7950F3D7CFBD4018D","userId":"ralowe","firstName":"Raymond","lastName":"Lowe","email":"raymond.lowe@gov.bc.ca","phone":"999-8888-7777","userType":"IDIR","isActive":"Y"}
	 * @param userUI
	 * @return
	 */
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public ResponseEntity<UserUI> update(@RequestBody(required=true) UserUI userUI){
		UserUI savedUser = userService.updateUser(userUI);
		return ResponseEntity.ok().body(savedUser);
	}
	
	/**
	 * Sample GET request:
	 * http://localhost:8080/orbeonrpt/user/getGroups?userId=ralowe
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/getGroups", method = RequestMethod.GET)
	public ResponseEntity<String> getGroupsForUser(@RequestParam(required=true) String userId){
		List<GroupUI> groupList = userService.getUserGroupUIs(userId);
		String returnStr = GeneralUtils.convertListToJsonObject(groupList);
		return ResponseEntity.ok().body(returnStr);
	}
	
	/**
	 * Sample GET request:
	 * http://localhost:8080/orbeonrpt/user/getGroupNames?userId=ralowe
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/getGroupNames", method = RequestMethod.GET)
	public ResponseEntity<String> getGroupNamesForUser(@RequestParam(required=true) String userId){
		List<String> groupList = userService.getUserGroupNames(userId);
		String returnStr = GeneralUtils.convertListToJsonObject(groupList);
		return ResponseEntity.ok().body(returnStr);
	}
	
	/**
	 * Sample GET request:
	 * http://localhost:8080/orbeonrpt/user/getAmalgamatedRoles?userId=ralowe
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/getAmalgamatedRoles", method = RequestMethod.GET)
	public ResponseEntity<String> getAmalgamatedRoles(@RequestParam(required=true) String userId){
		List<String> amalgamatedRoles = userService.getAmalgamatedRoles(userId);
		String returnStr = GeneralUtils.convertListToJsonObject(amalgamatedRoles);
		return ResponseEntity.ok().body(returnStr);
	}
	
	/**
	 * 
	 * http://localhost:8080/orbeonrpt/user/addUserToGroup/4CCBF6FA1CF0719750824D4F75E6E7BE/3B42FA2CD2CF42ADAFE78949EACA4F14
	 * 
	 * @param userId
	 * @param groupId
	 * @return
	 */
	@RequestMapping(value = "/addUserToGroup/{userId}/{groupId}", method = RequestMethod.GET)
	public ResponseEntity<String> addUserToGroup(@PathVariable(required=true) String userId, @PathVariable(required=true) String groupId){
		List<GroupUI> groupUIs = userService.addGroupToUser(userId, groupId);
		String returnStr = GeneralUtils.convertListToJsonObject(groupUIs);
		return ResponseEntity.ok().body(returnStr);
	}
	
	/**
	 * 
	 * http://localhost:8080/orbeonrpt/user/removeUserFromGroup/4CCBF6FA1CF0719750824D4F75E6E7BE/3B42FA2CD2CF42ADAFE78949EACA4F14
	 * 
	 * @param userId
	 * @param groupId
	 * @return
	 */
	@RequestMapping(value = "/removeUserFromGroup/{userId}/{groupId}", method = RequestMethod.GET)
	public ResponseEntity<String> removeUserFronGroup(@PathVariable(required=true) String userId, @PathVariable(required=true) String groupId){
		List<GroupUI> groupUIs = userService.removeGroupFromUser(userId, groupId);
		String returnStr = GeneralUtils.convertListToJsonObject(groupUIs);
		return ResponseEntity.ok().body(returnStr);
	}
	
	/**
	 * Sample DELETE:
	 * http://localhost:8080/orbeonrpt/user/delete?id=696BD9C76F414312BD581A1784ACE4BF
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public ResponseEntity<String> delete(@RequestParam(required=true) String userId){
		Boolean result = userService.deleteUser(userId);
		String returnStr = String.valueOf(result);
		return ResponseEntity.ok().body(returnStr);
	}

}

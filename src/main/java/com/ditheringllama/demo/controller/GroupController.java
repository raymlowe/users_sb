package com.ditheringllama.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ditheringllama.demo.service.GroupService;
import com.ditheringllama.demo.ui.model.GroupUI;
import com.ditheringllama.demo.ui.model.RoleUI;
import com.ditheringllama.demo.utils.GeneralUtils;

@Controller
@RequestMapping("/group")
public class GroupController {
	@Autowired
	GroupService groupService;
	
	@RequestMapping(value = "/ping", method = RequestMethod.GET)
	public ResponseEntity<String> alive(HttpServletRequest request){
		return ResponseEntity.ok().body("Alive");
	}
	
	/**
	 * Path:	http://localhost:8080/orbeonrpt/group/create
	 * Header:	content-type:application/json
	 * Body:	raw/JSON
	 * 			{"groupName":"sysadmin", "groupDescription":"System Administrators"}
	 * 
	 * @param groupUI
	 * @return
	 */
	@RequestMapping(value="/create", method = RequestMethod.POST)
	public ResponseEntity<GroupUI> create (@RequestBody(required=false) GroupUI groupUI){
		GroupUI savedGroup = groupService.saveGroup(groupUI);
		return ResponseEntity.ok().body(savedGroup);
	}
	
	/**
	 * Sample GET request:
	 * http://localhost:8080/orbeonrpt/group/getAll
	 * 
	 * @return
	 */
	@RequestMapping(value="/getAll", method = RequestMethod.GET)
	public ResponseEntity<String> getAll(){
		List<GroupUI> groups = groupService.getAllGroupsUI();
		String returnStr = GeneralUtils.convertListToJsonObject(groups);
		return ResponseEntity.ok().body(returnStr);
	}
	
	/**
	 * 
	 * http://localhost:8080/orbeonrpt/group/addRoleToGroup/D7654D4E2E204C6BB8B30C06EB0A8530/22B813901AE0463F9C6BC205ADC94A19
	 * 
	 * @param groupId
	 * @param roleId
	 * @return
	 */
	@RequestMapping(value = "/addRoleToGroup/{groupId}/{roleId}", method = RequestMethod.GET)
	public ResponseEntity<String> addRoleToGroup(@PathVariable(required=true) String groupId, @PathVariable(required=true) String roleId){
		List<RoleUI> roleUIs = groupService.addRoleToGroup(groupId, roleId);
		String returnStr = GeneralUtils.convertListToJsonObject(roleUIs);
		return ResponseEntity.ok().body(returnStr);
	}
	
	/**
	 * 
	 * http://localhost:8080/orbeonrpt/group/removeRoleFromGroup/D7654D4E2E204C6BB8B30C06EB0A8530/22B813901AE0463F9C6BC205ADC94A19
	 * 
	 * @param groupId
	 * @param roleId
	 * @return
	 */
	@RequestMapping(value = "/removeRoleFromGroup/{groupId}/{roleId}", method = RequestMethod.GET)
	public ResponseEntity<String> removeUserFronGroup(@PathVariable(required=true) String groupId, @PathVariable(required=true) String roleId){
		List<RoleUI> roleUIs = groupService.removeRoleFromGroup(groupId, roleId);
		String returnStr = GeneralUtils.convertListToJsonObject(roleUIs);
		return ResponseEntity.ok().body(returnStr);
	}
	
	/**
	 * Sample DELETE request:
	 * http://localhost:8080/orbeonrpt/group/delete?id=696BD9C76F414312BD581A1784ACE4BF
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value="delete", method = RequestMethod.DELETE)
	public ResponseEntity<String> delete(@RequestParam(required=true) String id){
		Boolean success = groupService.deleteGroup(id);
		String returnStr = String.valueOf(success);
		return ResponseEntity.ok().body(returnStr);
	}
	
	/**
	 * Path: http://localhost:8080/orbeonrpt/group/update
	 * Headers:
	 * 		content-type:application/json
	 * Body:
	 * 		raw/JSON
	 * 		{"id": "0F9E563E197046CDA4F527FDE1ABCE38", "groupName":"sysadmin", "groupDescription":"System Administrators"}
	 * @param roleUI
	 * @return
	 */
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public ResponseEntity<GroupUI> update(@RequestBody(required=true) GroupUI groupUI){
		GroupUI savedGroup = groupService.updateGroup(groupUI);
		return ResponseEntity.ok().body(savedGroup);
	}
}

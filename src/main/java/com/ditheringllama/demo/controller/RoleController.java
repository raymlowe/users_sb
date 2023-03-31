package com.ditheringllama.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ditheringllama.demo.service.RoleService;
import com.ditheringllama.demo.ui.model.RoleUI;
import com.ditheringllama.demo.utils.GeneralUtils;

@Controller
@RequestMapping("/role")
public class RoleController {
	@Autowired
	RoleService roleService;
	
	@RequestMapping(value = "/ping", method = RequestMethod.GET)
	public ResponseEntity<String> alive(HttpServletRequest request){
		return ResponseEntity.ok().body("Alive");
	}
	
	/**
	 * Path:	http://localhost:8080/orbeonrpt/role/create
	 * Header:	content-type:application/json
	 * Body:	raw/JSON
	 * 			for NEW
	 * 			{"roleName":"viewer", "roleDescription":"Can See It All"}
	 * 			OR for UPDATE
	 * 			{"id": "0F9E563E197046CDA4F527FDE1ABCE38", "roleName":"viewer", "roleDescription":"Can See It All"}
	 * 
	 * @param groupUI
	 * @return
	 */
	@RequestMapping(value="/create", method = RequestMethod.POST)
	public ResponseEntity<RoleUI> create (@RequestBody(required=false) RoleUI roleUI){
		RoleUI savedRole = roleService.saveRole(roleUI);
		return ResponseEntity.ok().body(savedRole);
	}
	
	/**
	 * Sample GET request
	 * http://localhost:8080/orbeonrpt/role/getAll
	 * 
	 * @return
	 */
	@RequestMapping(value="/getAll", method = RequestMethod.GET)
	public ResponseEntity<String> getAll(){
		List<RoleUI> roles = roleService.getAllRolesUI();
		String returnStr = GeneralUtils.convertListToJsonObject(roles);
		return ResponseEntity.ok().body(returnStr);
	}
	
	/**
	 * Sample DELETE request
	 * http://localhost:8080/orbeonrpt/role/delete?id=696BD9C76F414312BD581A1784ACE4BF
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value="delete", method = RequestMethod.DELETE)
	public ResponseEntity<String> delete(@RequestParam(required=true) String id){
		Boolean success = roleService.deleteRole(id);
		String returnStr = String.valueOf(success);
		return ResponseEntity.ok().body(returnStr);
		
	}
	
	/**
	 * Path: http://localhost:8080/orbeonrpt/role/update
	 * Headers:
	 * 		content-type:application/json
	 * Body:
	 * 		raw/JSON
	 * 		{"id": "0F9E563E197046CDA4F527FDE1ABCE38", "roleName":"viewer", "roleDescription":"Can See It All"}
	 * @param roleUI
	 * @return
	 */
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public ResponseEntity<RoleUI> update(@RequestBody(required=true) RoleUI roleUI){
		RoleUI savedRole = roleService.updateRole(roleUI);
		return ResponseEntity.ok().body(savedRole);
	}

}

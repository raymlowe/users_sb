package com.ditheringllama.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ditheringllama.demo.service.UserService;
import com.ditheringllama.demo.ui.model.UserUI;

@Controller
@RequestMapping("/ty/user")
public class UserControllerTY {

	@Autowired
	UserService userService;

	@RequestMapping(value = "/ping", method = RequestMethod.GET)
	public ResponseEntity<String> alive(HttpServletRequest request) {
		return ResponseEntity.ok().body("Alive");
	}

	@GetMapping("/display")
	public String showAllUsers(Model model) {
		List<UserUI> users = userService.getAllUsers();
		model.addAttribute("listUser", users);
		return "DisplayUsers"; // view
	}

	@GetMapping(value = "/create")
	public String createNewUser(Model model) {
		UserUI user = new UserUI();
		model.addAttribute("user", user);
		return "CreateUser"; // view
	}

	@PostMapping("/save")
	public String saveNewUser (@ModelAttribute("user") UserUI userUI) {
		//We need to ensure the userID.id is null for create
		userUI.setId(null);
		userService.saveUser(userUI);
		//return "redirect:/ty/user/display";	//redirect
		return "CreateUser"; // view
	}
}

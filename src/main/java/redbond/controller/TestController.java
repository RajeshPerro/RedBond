package redbond.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import redbond.service.UserService;
import redbond.service.UserServiceIml;

@Controller

public class TestController {
	
	String email = "rajeshghosh.cpp@gamil.com";
	@Autowired
	private UserService userService;
	@Autowired
	private UserServiceIml usrSerImpl;
	
	@GetMapping("/all-user")
	public String allUser(HttpServletRequest request)
	{
		request.setAttribute("users", usrSerImpl.findAll());
		return "success";
	}
	
	@GetMapping("/all-data")
	public String findbyEmail(HttpServletRequest request)
	{
		request.setAttribute("dataByEmail", userService.findUserByEmail(email));
		return "success";
	}
}

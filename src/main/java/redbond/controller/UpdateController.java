package redbond.controller;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import redbond.service.UserService;

@Controller
public class UpdateController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/editprofile", method = RequestMethod.GET)
	public String updateTask(@RequestParam int id, HttpServletRequest request){
		request.setAttribute("userInfo", userService.findOneUser(id));
		
		return "/admin/success";
	}
}

package redbond.controller;

import java.lang.reflect.Method;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import redbond.model.User;
import redbond.repository.UserRepository;
import redbond.service.UserService;

@Controller
public class UpdateController {
	@Autowired
	private UserService userService;
	@Autowired
	private UserRepository userrepo;
	
//	@RequestMapping(value="/editprofile", method = RequestMethod.GET)
//	public String updateTask(@RequestParam int id, HttpServletRequest request){
//		request.setAttribute("userInfo", userService.findOneUser(id));
//		
//		return "/admin/profileupdate";
//	}
	@RequestMapping(value="/editprofile", method = RequestMethod.GET)
	public ModelAndView updateTask(@RequestParam int id, HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		//User user = new User();
		modelAndView.addObject("userInfo", userService.findOneUser(id));
		modelAndView.setViewName("/admin/profileupdate");
		return modelAndView;
	}
	
	@RequestMapping(value = "/editprofile", method = RequestMethod.POST)
	public ModelAndView updateUser(User userInfo, HttpServletRequest request)
	{
		int id = 0;
		String name, phone, bloodgroup, country,city,latitude,longitude;
		
		id= Integer.parseInt(request.getParameter("id"));
		name = 	request.getParameter("name");
		phone = request.getParameter("phone");
		bloodgroup = request.getParameter("bloodgroup");
		country = request.getParameter("country");
		city = request.getParameter("city");
		latitude = request.getParameter("latitude");
		longitude = request.getParameter("longitude");
		
		ModelAndView modelAndView = new ModelAndView();
		userInfo.setDate_created(new Date());
		if(userService.findOneUser(id)!=null)
		{
			System.out.println("In controller--->"+"Name--->>>>"+name+"id--->>>>"+id);
			userService.updateName(name, phone, bloodgroup, country, city, latitude, longitude, id);
			modelAndView.addObject("successMessage", "");
			modelAndView.addObject("userInfo", new User());
		}
		modelAndView.setViewName("/admin/profileupdate");
		return modelAndView;
	}

}

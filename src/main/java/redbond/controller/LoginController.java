package redbond.controller;

import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorController;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import redbond.model.User;
import redbond.service.UserService;


@RestController
@Scope("session")
public class LoginController implements ErrorController{
	
	@Autowired
	private UserService userService;
	
	 private static final String PATH = "/error";

	    @RequestMapping("/error")
	    public ModelAndView error() {
	    	ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName("error");
			return modelAndView;
	    }

	    @Override
	    public String getErrorPath() {
	        return PATH;
	    }
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public ModelAndView login(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}
	
	
	@RequestMapping(value="/registration", method = RequestMethod.GET)
	public ModelAndView registration(){
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("registration");
		return modelAndView;
	}
	
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		User userExists = userService.findUserByEmail(user.getEmail());
		if (userExists != null) {
			bindingResult
					.rejectValue("email", "error.user",
							"There is already a user registered with the email provided");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("registration");
		} else {
			user.setDate_created(new Date());
			userService.saveUser(user);
			modelAndView.addObject("successMessage", "User has been registered successfully");
			modelAndView.addObject("user", new User());
			modelAndView.setViewName("index");
			
		}
		return modelAndView;
	}
	
	@RequestMapping(value="/admin/home", method = RequestMethod.GET)
	public ModelAndView home(){
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("userId", + user.getId());
		modelAndView.addObject("userName", "Welcome " + user.getName());
		modelAndView.addObject("userEmail", user.getEmail());
		modelAndView.addObject("userblood", user.getBloodgroup());
		modelAndView.addObject("userphone", user.getPhone());
		modelAndView.addObject("userCountryCity", user.getCity().toUpperCase()+", "+ user.getCountry());
		
		modelAndView.setViewName("admin/home");
		return modelAndView;
	}
	
	@RequestMapping(value="/forgotpass", method = RequestMethod.GET)
	public ModelAndView forgotPassword()
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("forgotpass");
		
		return modelAndView;
	}
	
	@RequestMapping(value="/forgotpass", method = RequestMethod.POST)
	public ModelAndView changePassword(HttpServletRequest request)
	{
		String email;
		email = request.getParameter("email");
		
		ModelAndView modelAndView = new ModelAndView();
		if (userService.findUserByEmail(email) == null) {
			
			
			modelAndView.addObject("error", "Email is invalid, please verify!");
			modelAndView.setViewName("forgotpass");
			
		}
		else{
			modelAndView.addObject("successMessage", "Done.!");
			
		}
		
		modelAndView.setViewName("forgotpass");
		
		return modelAndView;
	}
}

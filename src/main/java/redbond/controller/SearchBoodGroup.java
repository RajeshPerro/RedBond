package redbond.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import redbond.service.UserServiceIml;



@Controller
public class SearchBoodGroup {
	
	@Autowired
	private UserServiceIml usrSerImpl;
	
	
	@RequestMapping(value = "/bloodgroup", method = RequestMethod.POST)
	public ModelAndView bloodSearch(HttpServletRequest request, HttpServletResponse res) throws Exception
	{
		
		String bloodgroup = request.getParameter("bloodgroup");
		
		ModelAndView modelAndView = new ModelAndView();
		
		request.setAttribute("matchBlood", usrSerImpl.findByBloodgroup(bloodgroup));
		modelAndView.setViewName("searchresult");
		return modelAndView;
	}

}

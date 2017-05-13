package redbond.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import redbond.model.User;
import redbond.service.UserServiceIml;



@Controller
public class SearchBoodGroup {
	
	@Autowired
	private UserServiceIml usrSerImpl;
	
	public List<User> matchdata;
	String bloodgroup;
//	@RequestMapping(value = "/bloodgroup", method = RequestMethod.GET)
//	public ModelAndView bloodSearch(HttpServletRequest request, HttpServletResponse res) throws Exception
//	{
//		
//		 bloodgroup = request.getParameter("bloodgroup");
//		
//		ModelAndView modelAndView = new ModelAndView();
//		matchdata = usrSerImpl.findByBloodgroup(bloodgroup);
//		modelAndView.addObject("matchBlood",matchdata);
//		modelAndView.setViewName("searchresult");
//		return modelAndView;
//	}
	
	@RequestMapping(value = "/bloodgroup", method = RequestMethod.GET)
	public String findBloodgroup(HttpServletRequest request)
	{
		
		bloodgroup = request.getParameter("bloodgroup");
		
		matchdata = usrSerImpl.findByBloodgroup(bloodgroup);
		String jsonData = new Gson().toJson(matchdata);
		//obj.put("jsonData", list);
		System.out.println("JSON DATA--->>> "+jsonData);
		request.setAttribute("matchBlood", jsonData);
		return "success";
	}
}

package com.shizm.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.shizm.model.User;
import com.shizm.service.IUserService;


@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private IUserService userService;

	
	@RequestMapping(value = "/list", method = { RequestMethod.GET })
	public ModelAndView list() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/admin/user/list");
		return mv;
	}
	
	@RequestMapping(value = "/getUsers", method = { RequestMethod.GET })
	@ResponseBody
	public  List<User> getUsers(@RequestBody User user) {
		List<User> users = new ArrayList<User>();
		return userService.getUsers();		
	}

}

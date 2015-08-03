package com.shizm.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.shizm.model.User;
import com.shizm.service.IUserService;
import com.shizm.service.impl.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private IUserService userService;

	
	@RequestMapping(value = "/list", method = { RequestMethod.GET })
	public ModelAndView list() {
		ModelAndView mv = new ModelAndView();
		List<User> users = new ArrayList<User>();
		mv.setViewName("/user/list");
		return mv;
	}

}

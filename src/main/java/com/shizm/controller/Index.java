package com.shizm.controller;


import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.shizm.model.User;

@Controller
public class Index extends BaseController {
	@RequestMapping(value = { "", "/","/index" })
	public ModelAndView index(@CookieValue(value="uutoken", defaultValue="") String uutoken,HttpSession session) {
		ModelAndView mv = new ModelAndView();
		User user=(User)session.getAttribute("loginUser");
		System.out.println(user);
		mv.addObject("uutoken", uutoken);
		mv.setViewName("/index");
		return mv;
	}

	@RequestMapping(value = "/login", method = { RequestMethod.GET })
	public ModelAndView login() {
		ModelAndView mv = new ModelAndView();
		User user=new User();
		mv.addObject("user", user);
		mv.setViewName("user/login");
		return mv;
	}
}

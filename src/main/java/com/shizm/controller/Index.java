package com.shizm.controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.shizm.model.User;
import com.shizm.service.IUserService;

@Controller
public class Index extends BaseController {
	@Autowired
	private IUserService userService;
	
	@RequestMapping(value = { "", "/","/index" })
	public ModelAndView index(@CookieValue(value="uutoken", defaultValue="") String uutoken,HttpSession session) {
		ModelAndView mv = new ModelAndView();
		User user=(User)session.getAttribute("loginUser");
		System.out.println(user);
		mv.addObject("uutoken", uutoken);
		mv.setViewName("/index");
		return mv;
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register() {
		return "/register";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "/login";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView register(@ModelAttribute("form") User user) {
		System.out.println(user);
		userService.saveUser(user);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/index");
		return mv;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(@ModelAttribute("form") User user, HttpSession session) {
		User dbUser = userService.getUser(user);
		ModelAndView mv = new ModelAndView();

		if (dbUser != null) {
			session.setAttribute("loginUser", user);
			mv.setViewName("redirect:/index");
		} else {
			mv.addObject("error", "用户名或密码错误");
			mv.setViewName("redirect:/login");
		}
		return mv;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logout(HttpSession session) {
		session.removeAttribute("loginUser");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/login");
		return mv;
	}
}

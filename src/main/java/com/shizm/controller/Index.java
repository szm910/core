package com.shizm.controller;


import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.shizm.model.User;

@Controller
public class Index extends BaseController {
	@RequestMapping(value = { "", "/","/index" })
	public ModelAndView index(@CookieValue(value="uutoken", defaultValue="") String uutoken) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("uutoken", uutoken);
		mv.setViewName("/index");
		return mv;
	}

	@RequestMapping(value = "/login", method = { RequestMethod.GET })
	public ModelAndView login() {
		ModelAndView mv = new ModelAndView();
		User user=new User();
		mv.addObject("user", user);
		mv.setViewName("/login");
		return mv;
	}

	@RequestMapping(value = "/login", method = { RequestMethod.POST })
	public String  login(HttpServletResponse response,@ModelAttribute("user") User user) throws IOException {
		System.out.println("用户名登录："+user);
		String uuid=UUID.randomUUID().toString();
		Cookie cookie=new Cookie("uutoken", uuid);
		response.addCookie(cookie);
		return "index";
	}
}

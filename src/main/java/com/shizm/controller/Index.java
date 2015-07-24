package com.shizm.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Index extends BaseController {
	@RequestMapping(value = { "", "/","/index" })
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/index");
		return mv;
	}

	@RequestMapping(value = "/login", method = { RequestMethod.GET })
	public ModelAndView login() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/login");
		return mv;
	}

	@RequestMapping(value = "/login", method = { RequestMethod.POST })
	public ModelAndView  login(@RequestParam("userName") String userName, @RequestParam("passWord") String passWord) {
		System.out.println("用户名："+userName);
		System.out.println("密码："+passWord);
//		try {
//			response.sendRedirect("index");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return null;
	}
}

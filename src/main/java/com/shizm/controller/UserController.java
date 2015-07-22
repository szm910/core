package com.shizm.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.shizm.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
	 
    @Resource(name="userService")
    private UserService service;
    
    @RequestMapping(value="/manager",method=RequestMethod.GET)
    public ModelAndView hello2(){
        ModelAndView mv = new ModelAndView();
        mv.addObject("message", "HelloMVC");
        mv.setViewName("users");
        return mv;
    }
    
    @RequestMapping(value="/count",method=RequestMethod.GET)
    public ModelAndView count(){
        
        int c = service.userCount();
        
        ModelAndView mv = new ModelAndView();
        mv.addObject("message", c);
        mv.setViewName("users");
        return mv;
    }
}

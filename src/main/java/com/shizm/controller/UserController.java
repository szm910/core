package com.shizm.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.shizm.model.User;
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
    @RequestMapping(value="/add",method=RequestMethod.GET)
    public ModelAndView addUser(){
    	
        User user=new User();
        user.name="test";
        service.saveUser(user);
        
        ModelAndView mv = new ModelAndView();
        mv.addObject("message", user);
        mv.setViewName("users");
        return mv;
    }
    
    @RequestMapping(value="/update",method=RequestMethod.GET)
    public ModelAndView update(String id){
        User user=new User();
        user.setId(id);
        user.setName("testt2");
        service.saveUser(user);
        ModelAndView mv = new ModelAndView();
        mv.addObject("message", user);
        mv.setViewName("users");
        return mv;
    }
    
}
package com.shizm.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.shizm.model.User;
import com.shizm.service.UserService;

@Controller
@RequestMapping("user")
public class UserController {
	 
    @Resource(name="userService")
    private UserService userService;
    
    @RequestMapping(value="/register",method=RequestMethod.GET)
    public String register(){
    	return "user/register";
    }
    
    @RequestMapping(value="/login",method=RequestMethod.GET)
    public String login(){
    	return "user/login";
    }
    
    
    @RequestMapping(value="/register",method=RequestMethod.POST)
    public ModelAndView register(@ModelAttribute("form") User user){
    	System.out.println(user);
    	userService.saveUser(user);
    	ModelAndView mv=new ModelAndView();
    	mv.setViewName("redirect:/index");
    	return mv;
    }
    
    @RequestMapping(value="/login",method=RequestMethod.POST)
    public ModelAndView login(@ModelAttribute("form") User user,HttpSession session){
    	User dbUser=userService.getUser(user);
    	ModelAndView mv=new ModelAndView();
    
    	if(dbUser!=null){
    		session.setAttribute("loginUser", user);
    		mv.setViewName("redirect:/index");
    	}else{
    		mv.addObject("error", "用户名或密码错误");
    		mv.setViewName("user/login");
    	}
    	return mv;
    }
   
    
    @RequestMapping(value="/list", method = {RequestMethod.GET})
    public ModelAndView list(){
    	ModelAndView mv=new ModelAndView();
    	List<User> users=new ArrayList<User>();
    	User user=new User();
    	user.setAge(10);
    	user.setName("石增敏");
    	user.setPassword("123");
    	User user1=new User();
    	user1.setAge(10);
    	user1.setName("sdf11");
    	user1.setPassword("123");
    	users.add(user1);
    	users.add(user);
    	mv.addObject("users",users);
    	mv.setViewName("user/list");
    	return mv;
    }
    
}

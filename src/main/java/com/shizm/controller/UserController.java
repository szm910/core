package com.shizm.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
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
    private UserService service;
    
    @RequestMapping(value="/manager",method=RequestMethod.GET)
    public ModelAndView hello2(){
        ModelAndView mv = new ModelAndView();
        mv.addObject("message", "HelloMVC");
        mv.setViewName("user/users");
        return mv;
    }
    
    @RequestMapping(value="/count",method=RequestMethod.GET)
    public ModelAndView count(){
        
        int c = service.userCount();
        
        ModelAndView mv = new ModelAndView();
        mv.addObject("message", c);
        mv.setViewName("user/users");
        return mv;
    }
    @RequestMapping(value="/add",method=RequestMethod.GET)
    public ModelAndView addUser(){
        User user=new User();
        user.setName("test");
        service.saveUser(user);
        
        ModelAndView mv = new ModelAndView();
        mv.addObject("message", user);
        mv.setViewName("user/users");
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
        mv.setViewName("user/users");
        return mv;
    }
    
    @RequestMapping(value="/display/{name:\\w+}-{age:\\d+}", method = {RequestMethod.GET})
    public ModelAndView display(@PathVariable(value="name") String name, @PathVariable(value="age") Integer age){
    	ModelAndView mv=new ModelAndView();
    	mv.addObject("name",name);
    	mv.addObject("age",age);
    	mv.setViewName("user/display");
    	return mv;
    }
    @RequestMapping(value="/list", method = {RequestMethod.GET})
    public ModelAndView list(){
    	ModelAndView mv=new ModelAndView();
    	List<User> users=new ArrayList<User>();
    	User user=new User();
    	user.setAge(10);
    	user.setName("师曾敏");
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

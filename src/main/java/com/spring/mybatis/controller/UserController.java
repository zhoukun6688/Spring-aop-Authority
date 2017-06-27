package com.spring.mybatis.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.mybatis.model.User;
import com.spring.mybatis.service.UserService;
import com.spring.security.ValidatePermission;

@Controller
@RequestMapping("/users")
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Resource
	private UserService userService;
    
	@RequestMapping("/loginSuccess")
	public String loginSuccess(HttpSession session){
	
		logger.info("登录成功");
	    
		return "../index";
		
	}
	
	@ValidatePermission(authority="create")
	@RequestMapping("/create")
	@ResponseBody
	public List<String> createUser(){
		userService.addUser(null);
        List<String> list=Arrays.asList("新增用户信息成功");
		return list;
		
	}
	
	@ValidatePermission(authority="query")
	@RequestMapping("/query")
	@ResponseBody
	public List<User> retrieveUser(){
		List<User> list=new ArrayList<User>();
		list.add(userService.getUser(1));
		return list;
		
	}
	
	@ValidatePermission(authority="update")
	@ResponseBody
	@RequestMapping("/update")
	public List<String> updateUser(){
		userService.updateUser(null);
		List<String> list=Arrays.asList("更新用户信息成功");
	    
		return list;
		
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	@ValidatePermission(authority="delete")
	public List<String> deleteUser(){
		List<String> list=Arrays.asList("删除用户信息成功");
		list.contains("iang");

		return list;
	}

	@RequestMapping(value = "/query1"/*,produces ="application/json" + ";charset=utf-8"*/)
	@ResponseBody
	public Date query1(){

		return new Date();

	}
	
	

}

package com.spring.mybatis.controller;

import com.spring.mybatis.model.Role;
import com.spring.mybatis.model.User;
import com.spring.mybatis.service.RoleService;
import com.spring.mybatis.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Resource
	private UserService userService;
	@Resource
	private RoleService roleService;
	
	//登陆页面
		@RequestMapping("/login")
		public String login(Model model)throws Exception{
			return "login";
		}
		
		//登陆提交
		//account：用户登录名，pwd：密码
		@RequestMapping("/loginsubmit")
		public String loginsubmit(HttpSession session,String account,String pwd)throws Exception{
			User user=userService.getUserByAccount(account);
			if(user!=null){
				Role role = roleService.selectRoleByUserId(user.getId());
				//向session记录用户身份信息
				session.setAttribute("userLoginName", account);
				if (role!=null){
					session.setAttribute("roleID",role.getId());
				}
				return "success";
			}
			
			return "redirect:login";
		}
		
		//退出
		@RequestMapping("/logout")
		public String logout(HttpSession session)throws Exception{
			
			//session过期
			session.invalidate();
			
			return "redirect:login";
		}
		@RequestMapping("/accessDenied")
		public String accessDenied(HttpSession session)throws Exception{
			
			return "error";
		}
		
	
	

}

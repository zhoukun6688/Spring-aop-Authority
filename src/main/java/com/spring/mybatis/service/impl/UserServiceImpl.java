package com.spring.mybatis.service.impl;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.spring.mybatis.dao.UserMapper;
import com.spring.mybatis.model.User;
import com.spring.mybatis.service.UserService;
import com.spring.security.ValidatePermission;

@Service
public class UserServiceImpl implements UserService {
	
	@Resource
	private UserMapper userMapper;
	
	
	
	@Override
	public User getUser(int userId) {
		User user=new User();
		user.setAccount("测试用户");
		user.setCreated(new Date());
		user.setId(888888);
		user.setPassword("admin@2015");
		user.setStatus("active");
		user.setUsername("妹纸");
		return user;
		//return userMapper.selectByPrimaryKey(userId);
	}

	@Override
	public void addUser(User user) {
		System.out.println("新增用户成功");
		//userMapper.insert(user);
	}

	@Override
	public void deleteUser(int userId) {
		System.out.println("删除用户成功");
		//userMapper.deleteByPrimaryKey(userId);
	}

	@Override
	
	public void updateUser(User user) {
		System.out.println("更新用户成功");
		//userMapper.updateByPrimaryKeySelective(user);
	}

	@Override
	public User getUserByAccount(String account) {
		
		return userMapper.getUserByAccount(account);
	}

}

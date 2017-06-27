package com.spring.mybatis.service;

import javax.servlet.http.HttpSession;

public interface CheckAuthorityService {
	 boolean checkAccess(String flag, HttpSession session);
}

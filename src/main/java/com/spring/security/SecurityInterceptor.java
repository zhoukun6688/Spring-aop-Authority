package com.spring.security;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.spring.mybatis.model.Result;
import com.spring.utils.JsonUtils;

public class SecurityInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object obj) throws Exception {
		
		HttpSession session = request.getSession();
		if (session.getAttribute("userLoginName") == null) {
			if ("POST".equalsIgnoreCase(request.getMethod())) {
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.write(JsonUtils.objectToJson(new Result(false, "未登录！")));
				out.flush();
				out.close();
			} else {
				response.sendRedirect(request.getContextPath() + "/login");
			}
			return false;
		} else {
			return true;
		}
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object obj, ModelAndView model)
			throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object obj, Exception ex)
			throws Exception {

	}

}

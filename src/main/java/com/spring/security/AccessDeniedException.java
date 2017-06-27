package com.spring.security;


/**
 * 自定义异常类
 * @author zhangpeng
 * @date 2017年4月7日
 */
public class AccessDeniedException extends RuntimeException  {
	 public AccessDeniedException(String message) {
	        super(message);
	    }
}

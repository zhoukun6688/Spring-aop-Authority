package com.spring.security;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;


import org.aspectj.lang.reflect.MethodSignature;

import com.spring.mybatis.service.CheckAuthorityService;
import com.spring.mybatis.service.RoleService;
import com.spring.mybatis.service.UserService;



public class PermissionAspect {
	@Resource
	private CheckAuthorityService checkAuthorityService;
    private static final Logger logger= LoggerFactory.getLogger(PermissionAspect.class);
    public void doBefore(JoinPoint jp) throws IOException{
        //System.out.println("log PermissionAspect Before method: " + jp.getTarget().getClass().getName() + "." + jp.getSignature().getName());
        Method soruceMethod = getSourceMethod(jp);
        if(soruceMethod!=null){
            ValidatePermission oper = soruceMethod.getAnnotation(ValidatePermission.class);
            if (oper != null) {
                String flag= oper.authority();
                Object[] args = jp.getArgs();
                HttpServletRequest request =((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
                HttpSession session =request.getSession(); 
                boolean status = checkAuthorityService.checkAccess(flag,session);
                if(status){
                	return ;//正常
                }else{
                	throw new AccessDeniedException("无权操作该功能!");
                }
            }
        }
    }
    
    
    private Method getSourceMethod(JoinPoint jp){
        Method proxyMethod = ((MethodSignature) jp.getSignature()).getMethod();
        try {
            return jp.getTarget().getClass().getMethod(proxyMethod.getName(), proxyMethod.getParameterTypes());
        } catch (NoSuchMethodException e) {
            logger.info("出错了",e);
        } catch (SecurityException e) {
            logger.info("出错了",e);
        }
        return null;
    }
}

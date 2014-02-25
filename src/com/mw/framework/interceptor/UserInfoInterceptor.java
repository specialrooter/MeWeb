package com.mw.framework.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @Project SpringMVC
 * @Copyright © 2008-2013 SPRO Technology Consulting Limited. All rights reserved.
 * @fileName com.cf.core.interceptor.UserInfoInterceptor
 * @Version 1.0.0
 * @author Allan Ai
 * @time 2013-6-2
 *
 */
public class UserInfoInterceptor implements HandlerInterceptor {

 
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2,
			Exception arg3) throws Exception {
		//System.out.println("UserInfoInterceptor After completion handle");  
	}
 
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2,
			ModelAndView arg3) throws Exception {
		 //System.out.println("UserInfoInterceptor Post-handle");
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//System.out.println("UserInfoInterceptor Pre-handle");
		
		//throw new Exception("用户没有该操作的权限！");
		return true;
	}

}

package com.biyi.blog.util;

import javax.servlet.http.HttpServletRequest;

import com.biyi.blog.service.bean.UserSessionBean;

public class WebUtil {
	
	public static final String USER_SESSION_BEAN = "userSessionBean";
	
	public static final String ERROR = "error";

	public static final String NULL_FIELD = "未知";
	
	public static void setUserSessionBean(HttpServletRequest request, UserSessionBean userSessionBean){
		request.getSession(true).setAttribute(USER_SESSION_BEAN, userSessionBean);
	}
	
	public static UserSessionBean getUserSessionBean(HttpServletRequest request){
		return (UserSessionBean)request.getSession(true).getAttribute(USER_SESSION_BEAN);
	}

	public static boolean checkUserSessionBean(HttpServletRequest request) {
		return getUserSessionBean(request) != null;
	}

}

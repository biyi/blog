package com.biyi.blog.filter;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.biyi.blog.util.WebUtil;
import com.biyi.util.StringUtil;

public class LoginFilter implements Filter {
	
	public static Log log = LogFactory.getLog(LoginFilter.class);
	
	/**
	 * 未登录重定向url
	 */
	private String redirectUrl;
	/**
	 * 不用判断是否登录的url
	 */
	private Set<String> allowUrls;
	
	/**
	 * 出错重定向的url
	 */
	private String errorUrl;
	
	private String startWithUrl;

	public void destroy() {
		
	}
	/**
	 * 是否是不用判断是否登录的url
	 */
	private boolean isAllowUrl(String url){
		return allowUrls.contains(url) || !url.startsWith(startWithUrl);
	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)arg0;
		HttpServletResponse response = (HttpServletResponse)arg1;
		String url = request.getRequestURI();
		boolean isAllowUrl = isAllowUrl(url);
		boolean isLogin = WebUtil.checkUserSessionBean(request);
		if(isAllowUrl || isLogin){
			//可以访问
			try {
				arg2.doFilter(arg0, arg1);
			} catch (Exception e) {
				e.printStackTrace();
				log.error("服务器异常", e);
				//网页出错了
				response.sendRedirect(errorUrl);
			}
		}else{
			//网页未登录
			response.sendRedirect(redirectUrl);
		}
	}
	
	public void init(FilterConfig arg0) throws ServletException {
		String contextPath = StringUtil.URLRSlash(arg0.getServletContext().getContextPath());
		redirectUrl = contextPath + arg0.getInitParameter("redirectUrl");
		errorUrl = contextPath + arg0.getInitParameter("errorUrl");
		startWithUrl = contextPath + StringUtil.URLRSlash("admin");
		allowUrls = new HashSet<String>();
		String urls = arg0.getInitParameter("allowUrls");
		if(!StringUtil.isEmpty(urls)){
			String[] us = urls.split("\r?\n");
			for(String u: us){
				allowUrls.add(contextPath + u.trim());
			}
		}
		log.error("redirectUrl:" + redirectUrl);
		log.error("allowUrls:" + allowUrls);
		log.error("errorUrl:" + errorUrl);
	}

}

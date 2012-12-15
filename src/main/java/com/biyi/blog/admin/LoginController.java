package com.biyi.blog.admin;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.biyi.blog.controller.BaseController;
import com.biyi.blog.dao.vo.User;
import com.biyi.blog.service.IUserService;
import com.biyi.blog.service.bean.UserSessionBean;
import com.biyi.blog.service.exception.ServiceException;
import com.biyi.blog.util.WebUtil;

@Controller
@RequestMapping(value = "/admin/login/*")
public class LoginController extends BaseController {
	
	public static final Log logger = LogFactory.getLog(LoginController.class);
	
	@Autowired
	private IUserService userService;
	
	@RequestMapping(value = "login")
	public String login(
			User user, 
			Boolean isRememberMe,
			HttpServletRequest request
			){
		
		try {
			UserSessionBean userSessionBean = userService.tryLogin(user);
			WebUtil.setUserSessionBean(request, userSessionBean);
		} catch (ServiceException e) {
			logger.error("login error", e);
			request.setAttribute(WebUtil.ERROR, e.getMessage());
			return "admin/login/preLogin";
		}
		
		return "redirect:/admin/login/index.do";
	}
	
	@RequestMapping(value = "index")
	public void index(HttpServletRequest request){
		
	}
			

}

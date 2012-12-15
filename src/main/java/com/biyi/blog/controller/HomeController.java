package com.biyi.blog.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.biyi.blog.dao.vo.Category;
import com.biyi.blog.service.ICategoryService;
import com.biyi.blog.service.IUserService;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "/*")
public class HomeController {
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private ICategoryService categoryService;
	
	private static final Log logger = LogFactory.getLog(HomeController.class);
	
	@RequestMapping(value = "home")
	public String home(HttpServletRequest request) {
		
		List<Category> categorys = categoryService.getValidCategorys();
		request.setAttribute("categorys", categorys);
		
		return "home/home";
	}
	
}

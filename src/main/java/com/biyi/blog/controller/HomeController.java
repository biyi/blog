package com.biyi.blog.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.biyi.blog.dao.vo.User;
import com.biyi.blog.service.IUserService;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "/home/*")
public class HomeController {
	
	@Autowired
	private IUserService userService;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "index")
	public String home() {
		
		User user = userService.getUserById(1);
		System.out.println(user.getName());
		return "home/index";
	}
	
}

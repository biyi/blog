package com.biyi.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
class BaseController {

	@RequestMapping("*")
	public void defaultMethod(){
		//默认方法,可以直接访问jsp
	}

}
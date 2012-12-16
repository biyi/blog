package com.biyi.blog.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.biyi.blog.controller.BaseController;
import com.biyi.blog.dao.vo.Category;
import com.biyi.blog.service.ICategoryService;
import com.biyi.blog.service.exception.ServiceException;
import com.biyi.blog.util.WebUtil;

@Controller
@RequestMapping(value = "/admin/category/*")
public class CategoryController extends BaseController {
	
	public static final Log logger = LogFactory.getLog(CategoryController.class);
	
	@Autowired
	private ICategoryService categoryService;
	
	@RequestMapping(value = "list")
	public String list(HttpServletRequest request){
		
		List<Category> categorys = categoryService.getAllCategorys();
		request.setAttribute("categorys", categorys);
		
		
		return "admin/category/list";
	}
	
	@RequestMapping(value = "preAdd")
	public void preAdd(@ModelAttribute("category")Category category, ModelMap model){
		
		model.addAttribute("category", category);
		
	}
	
	@RequestMapping(value = "add")
	public String add(@ModelAttribute("category")Category category, 
			HttpServletRequest request){
		
		Integer userId = WebUtil.getUserId(request);
		try {
			category.setUserId(userId);
			categoryService.tryAddCategory(category);
			return "redirect:/admin/category/list.do";
		} catch (ServiceException e) {
			logger.error("login error", e);
			request.setAttribute(WebUtil.ERROR, e.getMessage());
			return "admin/category/preAdd";
		}
	}
	
	@RequestMapping(value = "preUpdate")
	public String preUpdate(Integer id,
			ModelMap model,
			HttpServletRequest request){
		
		Category category = categoryService.getCategoryById(id);
		
		if(category == null){
			request.setAttribute(WebUtil.ERROR, "找不到指定的分类");
			return "forward:/admin/category/list.do";
		}
		
		model.addAttribute("category", category);
		
		return "admin/category/preUpdate";
	}
	
	@RequestMapping(value = "update")
	public String update(@ModelAttribute("category")Category category, 
			HttpServletRequest request){
		
		Integer userId = WebUtil.getUserId(request);
		category.setUserId(userId);
		try {
			categoryService.tryUpdateCategory(category);
			return "redirect:/admin/category/list.do";
		} catch (ServiceException e) {
			logger.error("login error", e);
			request.setAttribute(WebUtil.ERROR, e.getMessage());
			return "admin/category/preUpdate";
		}
	}
			

}

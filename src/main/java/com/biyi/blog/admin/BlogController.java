package com.biyi.blog.admin;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.biyi.blog.dao.vo.Blog;
import com.biyi.blog.dao.vo.Category;
import com.biyi.blog.service.IBlogService;
import com.biyi.blog.service.ICategoryService;
import com.biyi.blog.service.bean.BlogQueryBean;
import com.biyi.blog.service.exception.ServiceException;
import com.biyi.blog.util.PageBean;
import com.biyi.blog.util.WebUtil;

@Controller
@RequestMapping(value = "/admin/blog/*")
public class BlogController {
	
	public static final Log logger = LogFactory.getLog(BlogController.class);
	
	@Autowired
	private IBlogService blogService;
	
	@Autowired
	private ICategoryService categoryService;
	
	@RequestMapping(value = "list")
	public String list(@ModelAttribute PageBean pageBean,
			HttpServletRequest request){
		
		Integer userId = WebUtil.getUserId(request);
		
		BlogQueryBean queryBean = new BlogQueryBean();
		queryBean.setUserId(userId);
		
		pageBean.setPageLength(25);
		
		List<Blog> blogs = blogService.queryBlogs(queryBean, pageBean);
		request.setAttribute("blogs", blogs);
		
		
		return "admin/blog/list";
	}
	
	@RequestMapping(value = "preAdd")
	public void preAdd(@ModelAttribute("blog")Blog blog, ModelMap model,
			HttpServletRequest request){
		
		List<Category> categorys = categoryService.getValidCategorys();
		request.setAttribute("categorys", categorys);
		
		Integer userId = WebUtil.getUserId(request);
		blog.setUserId(userId);
		
		model.addAttribute("blog", blog);
		
	}
	
	@RequestMapping(value = "add")
	public String add(@ModelAttribute("blog")Blog blog, 
			@RequestParam("categoryId") Set<Integer> categoryIds,
			HttpServletRequest request){
		
		System.out.println(blog.getUserId());
		
		Integer userId = WebUtil.getUserId(request);
		blog.setUserId(userId);
		
		try {
			blogService.tryAddBlog(blog, categoryIds);
			return "redirect:/admin/blog/list.do";
		} catch (ServiceException e) {
			logger.error("login error", e);
			request.setAttribute(WebUtil.ERROR, e.getMessage());
			return "admin/blog/preAdd";
		}
	}
	
	@RequestMapping(value = "preUpdate")
	public String preUpdate(Integer id,
			ModelMap model,
			HttpServletRequest request){
		
		List<Category> categorys = categoryService.getValidCategorys();
		request.setAttribute("categorys", categorys);
		
		Set<Integer> blogCategoryIds = categoryService.getCategoryIdsByBlogId(id);
		request.setAttribute("blogCategoryIds", blogCategoryIds);
		
		Integer userId = WebUtil.getUserId(request);
		
		Blog blog = blogService.getBlogById(id);
		
		if(blog == null || !userId.equals(blog.getUserId())){
			request.setAttribute(WebUtil.ERROR, "找不到指定的博客");
			return "forward:/admin/blog/list.do";
		}
		
		model.addAttribute("blog", blog);
		
		return "admin/blog/preUpdate";
	}
	
	@RequestMapping(value = "update")
	public String update(@ModelAttribute("blog")Blog blog, 
			@RequestParam("categoryId") Set<Integer> categoryIds,
			HttpServletRequest request){
		
		Integer userId = WebUtil.getUserId(request);
		blog.setUserId(userId);
		
		try {
			blogService.tryUpdateBlog(blog, categoryIds);
			return "redirect:/admin/blog/list.do";
		} catch (ServiceException e) {
			logger.error("login error", e);
			request.setAttribute(WebUtil.ERROR, e.getMessage());
			return "admin/blog/preUpdate";
		}
	}

}

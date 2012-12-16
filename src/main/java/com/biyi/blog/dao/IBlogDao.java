package com.biyi.blog.dao;

import java.util.List;

import com.biyi.blog.dao.vo.Blog;
import com.biyi.blog.service.bean.BlogQueryBean;

public interface IBlogDao{
	
	public List<Blog> queryByExample(Blog instance, Integer startPage, Integer pageLength);
	
	public boolean existByExample(Blog instance);
	
	public int countByExample(Blog instance);
	
	public int addBlog(Blog instance);
	
	public int updateBlogById(Blog instance);
	
	public Blog getBlogById(Integer id);
	
	public int deleteBlogById(Integer id);

	public int countBlogs(BlogQueryBean queryBean);

	public List<Blog> queryBlogs(BlogQueryBean queryBean, Integer startIndex, Integer pageLength);
}
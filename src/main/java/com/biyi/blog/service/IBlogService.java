package com.biyi.blog.service;

import java.util.Collection;
import java.util.List;

import com.biyi.blog.dao.vo.Blog;
import com.biyi.blog.service.bean.BlogQueryBean;
import com.biyi.blog.util.PageBean;

public interface IBlogService {
	
	Blog getBlogById(Integer id);

	/**
	 * 查询博客
	 */
	List<Blog> queryBlogs(BlogQueryBean queryBean, PageBean pageBean);

	/**
	 * 添加博客
	 */
	void tryAddBlog(Blog blog, Collection<Integer> categoryIds);

	/**
	 * 修改博客
	 */
	void tryUpdateBlog(Blog blog, Collection<Integer> categoryIds);

}

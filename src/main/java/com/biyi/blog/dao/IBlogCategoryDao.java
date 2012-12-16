package com.biyi.blog.dao;

import java.util.List;

import com.biyi.blog.dao.vo.BlogCategory;

public interface IBlogCategoryDao{
	
	public List<BlogCategory> queryByExample(BlogCategory instance, Integer startPage, Integer pageLength);
	
	public boolean existByExample(BlogCategory instance);
	
	public int countByExample(BlogCategory instance);
	
	public int addBlogCategory(BlogCategory instance);
	
	public int updateBlogCategoryById(BlogCategory instance);
	
	public BlogCategory getBlogCategoryById(Integer id);
	
	public int deleteBlogCategoryById(Integer id);

	/**
	 * 给博文分类
	 */
	public int addBlogCategory(Integer blogId, Integer categoryId);

	/**
	 * 获取博文的分类
	 */
	public List<BlogCategory> getBlogCategoryByBlogId(Integer id);

	/**
	 * 删除分类
	 */
	public int deleteBlogCategory(Integer blogId, Integer categoryId);
}
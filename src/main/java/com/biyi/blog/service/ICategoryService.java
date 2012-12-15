package com.biyi.blog.service;

import java.util.List;

import com.biyi.blog.dao.vo.Category;

public interface ICategoryService {
	
	public Category getCategoryById(Integer id);
	
	/**
	 * 获取可以展示的分类
	 */
	public List<Category> getValidCategorys();

	/**
	 * 获取所有分类
	 */
	public List<Category> getAllCategorys();

	/**
	 * 添加分类
	 */
	public void tryAddCategory(Category category);

	/**
	 * 修改分类
	 */
	public void tryUpdateCategory(Category category);

}

package com.biyi.blog.dao;

import java.util.List;

import com.biyi.blog.dao.vo.Category;

public interface ICategoryDao{
	
	public List<Category> queryByExample(Category instance, Integer startPage, Integer pageLength);
	
	public boolean existByExample(Category instance);
	
	public int countByExample(Category instance);
	
	public int addCategory(Category instance);
	
	public int updateCategoryById(Category instance);
	
	public Category getCategoryById(Integer id);
	
	public int deleteCategoryById(Integer id);
	
	/**
	 * 根据状态获取分类
	 */
	public List<Category> getCategoryByStatus(Integer status);

	/**
	 * 是否存在相同分类名
	 */
	public boolean existName(Integer id, String name);

	/**
	 * 添加分类下的博文数量
	 */
	public int addCategoryCount(Integer categoryId, int i);
}
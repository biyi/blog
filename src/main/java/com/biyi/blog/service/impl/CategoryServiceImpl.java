package com.biyi.blog.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biyi.blog.dao.IBlogCategoryDao;
import com.biyi.blog.dao.ICategoryDao;
import com.biyi.blog.dao.vo.BlogCategory;
import com.biyi.blog.dao.vo.Category;
import com.biyi.blog.service.ICategoryService;
import com.biyi.blog.service.exception.ServiceException;
import com.biyi.util.StringUtil;

@Service
public class CategoryServiceImpl extends BaseServiceImpl implements ICategoryService {
	
	@Autowired
	private ICategoryDao categoryDao;
	
	@Autowired
	private IBlogCategoryDao blogCategoryDao;

	public List<Category> getValidCategorys() {
		return categoryDao.getCategoryByStatus(Category.STATUS_NORMAL);
	}

	public List<Category> getAllCategorys() {
		return categoryDao.queryByExample(new Category(), null, null);
	}

	public void tryAddCategory(Category category) {
		if(category == null || StringUtil.isEmpty(category.getName())){
			throw new ServiceException("分类信息不得为空");
		}
		
		if(categoryDao.existName(null, category.getName())){
			throw new ServiceException("已有相同分类名");
		}
		
		categoryDao.addCategory(category);
	}

	public void tryUpdateCategory(Category category) {
		if(category == null || StringUtil.isEmpty(category.getName()) || category.getId() == null){
			throw new ServiceException("分类信息不得为空");
		}
		
		Integer id = category.getId();
		
		Category instance = categoryDao.getCategoryById(id);
		if(instance == null || !instance.getUserId().equals(category.getUserId())){
			throw new ServiceException("找不到指定分类信息");
		}
		
		if(categoryDao.existName(id, category.getName())){
			throw new ServiceException("已有相同分类名");
		}
		
		categoryDao.updateCategoryById(category);
	}

	public Category getCategoryById(Integer id) {
		return categoryDao.getCategoryById(id);
	}

	public Set<Integer> getCategoryIdsByBlogId(Integer id) {
		List<BlogCategory> blogCategorys = blogCategoryDao.getBlogCategoryByBlogId(id);
		Set<Integer> ids = new HashSet<Integer>();
		if(blogCategorys != null && !blogCategorys.isEmpty()){
			for(BlogCategory blogCategory: blogCategorys){
				ids.add(blogCategory.getCategoryId());
			}
		}
		return ids;
	}

}

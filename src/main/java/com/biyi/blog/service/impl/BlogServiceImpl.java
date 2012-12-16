package com.biyi.blog.service.impl;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biyi.blog.dao.IBlogCategoryDao;
import com.biyi.blog.dao.IBlogDao;
import com.biyi.blog.dao.ICategoryDao;
import com.biyi.blog.dao.vo.Blog;
import com.biyi.blog.dao.vo.BlogCategory;
import com.biyi.blog.service.IBlogService;
import com.biyi.blog.service.bean.BlogQueryBean;
import com.biyi.blog.service.exception.ServiceException;
import com.biyi.blog.util.PageBean;
import com.biyi.util.StringUtil;

@Service
public class BlogServiceImpl extends BaseServiceImpl implements IBlogService {
	
	@Autowired
	private IBlogDao blogDao;
	
	@Autowired
	private IBlogCategoryDao blogCategoryDao;
	
	@Autowired
	private ICategoryDao categoryDao;

	public List<Blog> queryBlogs(BlogQueryBean queryBean, PageBean pageBean) {
		int totalResult = blogDao.countBlogs(queryBean);
		pageBean.setTotalResult(totalResult);
		
		if(totalResult >= 0){
			return blogDao.queryBlogs(queryBean, pageBean.getStartIndex(), pageBean.getPageLength());
		}
		return null;
	}

	public Blog getBlogById(Integer id) {
		return blogDao.getBlogById(id);
	}

	public void tryAddBlog(Blog blog, Collection<Integer> categoryIds) {
		
		if(StringUtil.isEmpty(blog.getTitle())){
			throw new ServiceException("标题不得为空");
		}
		
		Date now = new Date();
		blog.setCreateTime(now);
		blog.setUpdateTime(now);
		
		int blogId = blogDao.addBlog(blog);
		
		if(categoryIds != null && !categoryIds.isEmpty()){
			for(Integer categoryId: categoryIds){
				blogCategoryDao.addBlogCategory(blogId, categoryId);
				categoryDao.addCategoryCount(categoryId, 1);
			}
		}else{
			blogCategoryDao.addBlogCategory(blogId, -1);
		}
	}

	public void tryUpdateBlog(Blog blog, Collection<Integer> categoryIds) {
		
		Integer id = blog.getId();
		if(id == null){
			throw new ServiceException("找不到指定的博客");
		}
		
		Blog instance = blogDao.getBlogById(id);
		
		if(instance == null || !blog.getUserId().equals(instance.getUserId())){
			throw new ServiceException("找不到指定的博客");
		}
		
		if(StringUtil.isEmpty(blog.getTitle())){
			throw new ServiceException("标题不得为空");
		}
		
		blog.setUpdateTime(new Date());
		
		blogDao.updateBlogById(blog);
		
		List<BlogCategory> blogCategorys = blogCategoryDao.getBlogCategoryByBlogId(id);
		
		Set<Integer> existCategoryIds = new HashSet<Integer>();
		if(blogCategorys != null && !blogCategorys.isEmpty()){
			for(BlogCategory blogCategory: blogCategorys){
				existCategoryIds.add(blogCategory.getCategoryId());
			}
		}
		
		Set<Integer> addCategoryIds = new HashSet<Integer>();
		if(categoryIds != null && !categoryIds.isEmpty()){
			addCategoryIds.addAll(categoryIds);
		}else{
			addCategoryIds.add(-1);
		}
		
		for(Integer addCategoryId: addCategoryIds){
			if(!existCategoryIds.remove(addCategoryId)){
				blogCategoryDao.addBlogCategory(id, addCategoryId);
				categoryDao.addCategoryCount(addCategoryId, 1);
			}
		}
		
		for(Integer existCategoryId: existCategoryIds){
			blogCategoryDao.deleteBlogCategory(id, existCategoryId);
			categoryDao.addCategoryCount(existCategoryId, -1);
		}
	}

}

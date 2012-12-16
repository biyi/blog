package com.biyi.blog.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import com.biyi.blog.dao.IBlogCategoryDao;
import com.biyi.blog.dao.vo.BlogCategory;

@Component
public class BlogCategoryDaoImpl extends BaseDaoImpl implements IBlogCategoryDao{
	
	public List<BlogCategory> queryByExample(BlogCategory instance, Integer startPage, Integer pageLength){
		StringBuilder sb = new StringBuilder("select * from `blog_category` where 1 = 1");
		if(instance.getId() != null){
			BlogCategory vo = getBlogCategoryById(instance.getId());
			if(vo != null){
				List<BlogCategory> result = new ArrayList<BlogCategory>(1);
				result.add(vo);
				return result;
			}
		}
		List<Object> params = new ArrayList<Object>();
		if(instance.getCategoryId() != null){
			sb.append(" and `category_id` = ?");
			params.add(instance.getCategoryId());
		}
		if(instance.getBlogId() != null){
			sb.append(" and `blog_id` = ?");
			params.add(instance.getBlogId());
		}
		if(startPage == null && pageLength != null){
			sb.append(" limit ?");
			params.add(pageLength);
		}else if(startPage != null && pageLength != null){
			sb.append(" limit ?,?");
			params.add(startPage);
			params.add(pageLength);
		}else if(startPage != null && pageLength == null){
			throw new IllegalArgumentException("no page length");
		}
		String sql = sb.toString();
		return getJdbcTemplate().query(sql, params.toArray(), new BlogCategory());
	}
	
	public int countByExample(BlogCategory instance){
		StringBuilder sb = new StringBuilder("select count(*) from `blog_category` where 1 = 1");
		if(instance.getId() != null){
			if(existByExample(instance))
				return 1;
		}
		List<Object> params = new ArrayList<Object>();
		if(instance.getCategoryId() != null){
			sb.append(" and `category_id` = ?");
			params.add(instance.getCategoryId());
		}
		if(instance.getBlogId() != null){
			sb.append(" and `blog_id` = ?");
			params.add(instance.getBlogId());
		}
		String sql = sb.toString();
		return getJdbcTemplate().queryForInt(sql, params.toArray());
	}
	
	public boolean existByExample(BlogCategory instance){
		if(instance.getId() != null){
			BlogCategory vo = getBlogCategoryById(instance.getId());
			if(vo != null){
				return true;
			}
			return false;
		}
		StringBuilder sb = new StringBuilder("select 1 from `blog_category` where 1 = 1");
		List<Object> params = new ArrayList<Object>();
		if(instance.getCategoryId() != null){
			sb.append(" and `category_id` = ?");
			params.add(instance.getCategoryId());
		}
		if(instance.getBlogId() != null){
			sb.append(" and `blog_id` = ?");
			params.add(instance.getBlogId());
		}
		sb.append(" limit 1");
		String sql = sb.toString();
		List<Integer> result = getJdbcTemplate().query(sql, params.toArray(), new SingleColumnRowMapper<Integer>(Integer.class));
		if(result != null && result.size() > 0){
			return true;
		}
		return false;
	}
	
	public int addBlogCategory(BlogCategory instance){
		StringBuilder sb = new StringBuilder("insert into `blog_category`(");
		StringBuilder sb1 = new StringBuilder();
		final List<Object> params = new ArrayList<Object>();
		if(instance.getCategoryId() != null){
			sb.append("`category_id`,");
			sb1.append("?,");
			params.add(instance.getCategoryId());
		}
		if(instance.getBlogId() != null){
			sb.append("`blog_id`,");
			sb1.append("?,");
			params.add(instance.getBlogId());
		}
		if(params.size() == 0)
			throw new RuntimeException("no parameters set");
		sb.deleteCharAt(sb.length() - 1);
		sb1.deleteCharAt(sb1.length() - 1);
		sb.append(")values(").append(sb1).append(")");
		final String sql = sb.toString();
		KeyHolder keyHolder = new GeneratedKeyHolder();
		int result = getJdbcTemplate().update(new PreparedStatementCreator(){

			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql, new String[] {"id"});
		        for(int i = 0;i < params.size(); i++){
		        	ps.setObject(i + 1, params.get(i));
		        }
		        return ps;
			}
			
		}, keyHolder);
		instance.setId(keyHolder.getKey().intValue());
		return result;
	}
	
	public int updateBlogCategoryById(BlogCategory instance){
		if(instance.getId() == null){
			throw new NullPointerException("no primary key");
		}
		StringBuilder sb = new StringBuilder("update `blog_category` set");
		List<Object> params = new ArrayList<Object>();
		if(instance.getCategoryId() != null){
			sb.append(" `category_id` = ?,");
			params.add(instance.getCategoryId());
		}
		if(instance.getBlogId() != null){
			sb.append(" `blog_id` = ?,");
			params.add(instance.getBlogId());
		}
		if(params.size() == 0)
			throw new RuntimeException("no parameters set");
		sb.deleteCharAt(sb.length() - 1);
		sb.append(" where `id` = ?");
		params.add(instance.getId());
		String sql = sb.toString();
		return getJdbcTemplate().update(sql, params.toArray());
	}
	
	public BlogCategory getBlogCategoryById(Integer id){
		String sql = "select * from `blog_category` where `id` = ?";
		List<BlogCategory> instances = getJdbcTemplate().query(sql, new Object[] {id}, new BlogCategory());
		if(instances != null && instances.size() > 0)
			return instances.get(0);
		return null; 
	}
	
	public int deleteBlogCategoryById(Integer id){
		String sql = "delete from `blog_category` where `id` = ?";
		return getJdbcTemplate().update(sql, new Object[] {id});
	}

	public int addBlogCategory(Integer blogId, Integer categoryId) {
		BlogCategory instance = new BlogCategory();
		instance.setBlogId(blogId);
		instance.setCategoryId(categoryId);
		return addBlogCategory(instance);
	}

	public List<BlogCategory> getBlogCategoryByBlogId(Integer blogId) {
		BlogCategory instance = new BlogCategory();
		instance.setBlogId(blogId);
		return queryByExample(instance, null, null);
	}

	public int deleteBlogCategory(Integer blogId, Integer categoryId) {
		String sql = "delete from blog_category where blog_id = ? and category_id = ?";
		return getJdbcTemplate().update(sql, new Object[] {blogId, categoryId});
	}
}
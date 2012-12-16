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

import com.biyi.blog.dao.IBlogDao;
import com.biyi.blog.dao.vo.Blog;
import com.biyi.blog.service.bean.BlogQueryBean;

@Component
public class BlogDaoImpl extends BaseDaoImpl implements IBlogDao{
	
	public List<Blog> queryByExample(Blog instance, Integer startPage, Integer pageLength){
		StringBuilder sb = new StringBuilder("select * from `blog` where 1 = 1");
		if(instance.getId() != null){
			Blog vo = getBlogById(instance.getId());
			if(vo != null){
				List<Blog> result = new ArrayList<Blog>(1);
				result.add(vo);
				return result;
			}
		}
		List<Object> params = new ArrayList<Object>();
		if(instance.getContent() != null){
			sb.append(" and `content` = ?");
			params.add(instance.getContent());
		}
		if(instance.getTitle() != null){
			sb.append(" and `title` = ?");
			params.add(instance.getTitle());
		}
		if(instance.getUpdateTime() != null){
			sb.append(" and `update_time` = ?");
			params.add(instance.getUpdateTime());
		}
		if(instance.getStatus() != null){
			sb.append(" and `status` = ?");
			params.add(instance.getStatus());
		}
		if(instance.getDescription() != null){
			sb.append(" and `description` = ?");
			params.add(instance.getDescription());
		}
		if(instance.getKeyword() != null){
			sb.append(" and `keyword` = ?");
			params.add(instance.getKeyword());
		}
		if(instance.getCreateTime() != null){
			sb.append(" and `create_time` = ?");
			params.add(instance.getCreateTime());
		}
		if(instance.getUserId() != null){
			sb.append(" and `user_id` = ?");
			params.add(instance.getUserId());
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
		return getJdbcTemplate().query(sql, params.toArray(), new Blog());
	}
	
	public int countByExample(Blog instance){
		StringBuilder sb = new StringBuilder("select count(*) from `blog` where 1 = 1");
		if(instance.getId() != null){
			if(existByExample(instance))
				return 1;
		}
		List<Object> params = new ArrayList<Object>();
		if(instance.getContent() != null){
			sb.append(" and `content` = ?");
			params.add(instance.getContent());
		}
		if(instance.getTitle() != null){
			sb.append(" and `title` = ?");
			params.add(instance.getTitle());
		}
		if(instance.getUpdateTime() != null){
			sb.append(" and `update_time` = ?");
			params.add(instance.getUpdateTime());
		}
		if(instance.getStatus() != null){
			sb.append(" and `status` = ?");
			params.add(instance.getStatus());
		}
		if(instance.getDescription() != null){
			sb.append(" and `description` = ?");
			params.add(instance.getDescription());
		}
		if(instance.getKeyword() != null){
			sb.append(" and `keyword` = ?");
			params.add(instance.getKeyword());
		}
		if(instance.getCreateTime() != null){
			sb.append(" and `create_time` = ?");
			params.add(instance.getCreateTime());
		}
		if(instance.getUserId() != null){
			sb.append(" and `user_id` = ?");
			params.add(instance.getUserId());
		}
		String sql = sb.toString();
		return getJdbcTemplate().queryForInt(sql, params.toArray());
	}
	
	public boolean existByExample(Blog instance){
		if(instance.getId() != null){
			Blog vo = getBlogById(instance.getId());
			if(vo != null){
				return true;
			}
			return false;
		}
		StringBuilder sb = new StringBuilder("select 1 from `blog` where 1 = 1");
		List<Object> params = new ArrayList<Object>();
		if(instance.getContent() != null){
			sb.append(" and `content` = ?");
			params.add(instance.getContent());
		}
		if(instance.getTitle() != null){
			sb.append(" and `title` = ?");
			params.add(instance.getTitle());
		}
		if(instance.getUpdateTime() != null){
			sb.append(" and `update_time` = ?");
			params.add(instance.getUpdateTime());
		}
		if(instance.getStatus() != null){
			sb.append(" and `status` = ?");
			params.add(instance.getStatus());
		}
		if(instance.getDescription() != null){
			sb.append(" and `description` = ?");
			params.add(instance.getDescription());
		}
		if(instance.getKeyword() != null){
			sb.append(" and `keyword` = ?");
			params.add(instance.getKeyword());
		}
		if(instance.getCreateTime() != null){
			sb.append(" and `create_time` = ?");
			params.add(instance.getCreateTime());
		}
		if(instance.getUserId() != null){
			sb.append(" and `user_id` = ?");
			params.add(instance.getUserId());
		}
		sb.append(" limit 1");
		String sql = sb.toString();
		List<Integer> result = getJdbcTemplate().query(sql, params.toArray(), new SingleColumnRowMapper<Integer>(Integer.class));
		if(result != null && result.size() > 0){
			return true;
		}
		return false;
	}
	
	public int addBlog(Blog instance){
		StringBuilder sb = new StringBuilder("insert into `blog`(");
		StringBuilder sb1 = new StringBuilder();
		final List<Object> params = new ArrayList<Object>();
		if(instance.getContent() != null){
			sb.append("`content`,");
			sb1.append("?,");
			params.add(instance.getContent());
		}
		if(instance.getTitle() != null){
			sb.append("`title`,");
			sb1.append("?,");
			params.add(instance.getTitle());
		}
		if(instance.getUpdateTime() != null){
			sb.append("`update_time`,");
			sb1.append("?,");
			params.add(instance.getUpdateTime());
		}
		if(instance.getStatus() != null){
			sb.append("`status`,");
			sb1.append("?,");
			params.add(instance.getStatus());
		}
		if(instance.getDescription() != null){
			sb.append("`description`,");
			sb1.append("?,");
			params.add(instance.getDescription());
		}
		if(instance.getKeyword() != null){
			sb.append("`keyword`,");
			sb1.append("?,");
			params.add(instance.getKeyword());
		}
		if(instance.getCreateTime() != null){
			sb.append("`create_time`,");
			sb1.append("?,");
			params.add(instance.getCreateTime());
		}
		if(instance.getUserId() != null){
			sb.append("`user_id`,");
			sb1.append("?,");
			params.add(instance.getUserId());
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
	
	public int updateBlogById(Blog instance){
		if(instance.getId() == null){
			throw new NullPointerException("no primary key");
		}
		StringBuilder sb = new StringBuilder("update `blog` set");
		List<Object> params = new ArrayList<Object>();
		if(instance.getContent() != null){
			sb.append(" `content` = ?,");
			params.add(instance.getContent());
		}
		if(instance.getTitle() != null){
			sb.append(" `title` = ?,");
			params.add(instance.getTitle());
		}
		if(instance.getUpdateTime() != null){
			sb.append(" `update_time` = ?,");
			params.add(instance.getUpdateTime());
		}
		if(instance.getStatus() != null){
			sb.append(" `status` = ?,");
			params.add(instance.getStatus());
		}
		if(instance.getDescription() != null){
			sb.append(" `description` = ?,");
			params.add(instance.getDescription());
		}
		if(instance.getKeyword() != null){
			sb.append(" `keyword` = ?,");
			params.add(instance.getKeyword());
		}
		if(instance.getCreateTime() != null){
			sb.append(" `create_time` = ?,");
			params.add(instance.getCreateTime());
		}
		if(instance.getUserId() != null){
			sb.append(" `user_id` = ?,");
			params.add(instance.getUserId());
		}
		if(params.size() == 0)
			throw new RuntimeException("no parameters set");
		sb.deleteCharAt(sb.length() - 1);
		sb.append(" where `id` = ?");
		params.add(instance.getId());
		String sql = sb.toString();
		return getJdbcTemplate().update(sql, params.toArray());
	}
	
	public Blog getBlogById(Integer id){
		String sql = "select * from `blog` where `id` = ?";
		List<Blog> instances = getJdbcTemplate().query(sql, new Object[] {id}, new Blog());
		if(instances != null && instances.size() > 0)
			return instances.get(0);
		return null; 
	}
	
	public int deleteBlogById(Integer id){
		String sql = "delete from `blog` where `id` = ?";
		return getJdbcTemplate().update(sql, new Object[] {id});
	}

	public int countBlogs(BlogQueryBean queryBean) {
		Integer userId = queryBean.getUserId();
		Blog instance = new Blog();
		instance.setUserId(userId);
		return countByExample(instance);
	}

	public List<Blog> queryBlogs(BlogQueryBean queryBean, Integer startIndex, Integer pageLength) {
		Integer userId = queryBean.getUserId();
		Blog instance = new Blog();
		instance.setUserId(userId);
		return queryByExample(instance, startIndex, pageLength);
	}
}
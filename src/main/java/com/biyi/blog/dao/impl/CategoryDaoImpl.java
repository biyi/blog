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

import com.biyi.blog.dao.ICategoryDao;
import com.biyi.blog.dao.exception.DaoException;
import com.biyi.blog.dao.vo.Category;

@Component
public class CategoryDaoImpl extends BaseDaoImpl implements ICategoryDao{
	
	public List<Category> queryByExample(Category instance, Integer startPage, Integer pageLength){
		StringBuilder sb = new StringBuilder("select * from `category` where 1 = 1");
		if(instance.getId() != null){
			Category vo = getCategoryById(instance.getId());
			if(vo != null){
				List<Category> result = new ArrayList<Category>(1);
				result.add(vo);
				return result;
			}
		}
		List<Object> params = new ArrayList<Object>();
		if(instance.getUserId() != null){
			sb.append(" and `user_id` = ?");
			params.add(instance.getUserId());
		}
		if(instance.getOrderId() != null){
			sb.append(" and `order_id` = ?");
			params.add(instance.getOrderId());
		}
		if(instance.getCount() != null){
			sb.append(" and `count` = ?");
			params.add(instance.getCount());
		}
		if(instance.getStatus() != null){
			sb.append(" and `status` = ?");
			params.add(instance.getStatus());
		}
		if(instance.getName() != null){
			sb.append(" and `name` = ?");
			params.add(instance.getName());
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
		return getJdbcTemplate().query(sql, params.toArray(), new Category());
	}
	
	public int countByExample(Category instance){
		StringBuilder sb = new StringBuilder("select count(*) from `category` where 1 = 1");
		if(instance.getId() != null){
			if(existByExample(instance))
				return 1;
		}
		List<Object> params = new ArrayList<Object>();
		if(instance.getUserId() != null){
			sb.append(" and `user_id` = ?");
			params.add(instance.getUserId());
		}
		if(instance.getOrderId() != null){
			sb.append(" and `order_id` = ?");
			params.add(instance.getOrderId());
		}
		if(instance.getCount() != null){
			sb.append(" and `count` = ?");
			params.add(instance.getCount());
		}
		if(instance.getStatus() != null){
			sb.append(" and `status` = ?");
			params.add(instance.getStatus());
		}
		if(instance.getName() != null){
			sb.append(" and `name` = ?");
			params.add(instance.getName());
		}
		String sql = sb.toString();
		return getJdbcTemplate().queryForInt(sql, params.toArray());
	}
	
	public boolean existByExample(Category instance){
		if(instance.getId() != null){
			Category vo = getCategoryById(instance.getId());
			if(vo != null){
				return true;
			}
			return false;
		}
		StringBuilder sb = new StringBuilder("select 1 from `category` where 1 = 1");
		List<Object> params = new ArrayList<Object>();
		if(instance.getUserId() != null){
			sb.append(" and `user_id` = ?");
			params.add(instance.getUserId());
		}
		if(instance.getOrderId() != null){
			sb.append(" and `order_id` = ?");
			params.add(instance.getOrderId());
		}
		if(instance.getCount() != null){
			sb.append(" and `count` = ?");
			params.add(instance.getCount());
		}
		if(instance.getStatus() != null){
			sb.append(" and `status` = ?");
			params.add(instance.getStatus());
		}
		if(instance.getName() != null){
			sb.append(" and `name` = ?");
			params.add(instance.getName());
		}
		sb.append(" limit 1");
		String sql = sb.toString();
		List<Integer> result = getJdbcTemplate().query(sql, params.toArray(), new SingleColumnRowMapper<Integer>(Integer.class));
		if(result != null && result.size() > 0){
			return true;
		}
		return false;
	}
	
	public int addCategory(Category instance){
		StringBuilder sb = new StringBuilder("insert into `category`(");
		StringBuilder sb1 = new StringBuilder();
		final List<Object> params = new ArrayList<Object>();
		if(instance.getUserId() != null){
			sb.append("`user_id`,");
			sb1.append("?,");
			params.add(instance.getUserId());
		}
		if(instance.getOrderId() != null){
			sb.append("`order_id`,");
			sb1.append("?,");
			params.add(instance.getOrderId());
		}
		if(instance.getCount() != null){
			sb.append("`count`,");
			sb1.append("?,");
			params.add(instance.getCount());
		}
		if(instance.getStatus() != null){
			sb.append("`status`,");
			sb1.append("?,");
			params.add(instance.getStatus());
		}
		if(instance.getName() != null){
			sb.append("`name`,");
			sb1.append("?,");
			params.add(instance.getName());
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
	
	public int updateCategoryById(Category instance){
		if(instance.getId() == null){
			throw new NullPointerException("no primary key");
		}
		StringBuilder sb = new StringBuilder("update `category` set");
		List<Object> params = new ArrayList<Object>();
		if(instance.getUserId() != null){
			sb.append(" `user_id` = ?,");
			params.add(instance.getUserId());
		}
		if(instance.getOrderId() != null){
			sb.append(" `order_id` = ?,");
			params.add(instance.getOrderId());
		}
		if(instance.getCount() != null){
			sb.append(" `count` = ?,");
			params.add(instance.getCount());
		}
		if(instance.getStatus() != null){
			sb.append(" `status` = ?,");
			params.add(instance.getStatus());
		}
		if(instance.getName() != null){
			sb.append(" `name` = ?,");
			params.add(instance.getName());
		}
		if(params.size() == 0)
			throw new RuntimeException("no parameters set");
		sb.deleteCharAt(sb.length() - 1);
		sb.append(" where `id` = ?");
		params.add(instance.getId());
		String sql = sb.toString();
		return getJdbcTemplate().update(sql, params.toArray());
	}
	
	public Category getCategoryById(Integer id){
		String sql = "select * from `category` where `id` = ?";
		List<Category> instances = getJdbcTemplate().query(sql, new Object[] {id}, new Category());
		if(instances != null && instances.size() > 0)
			return instances.get(0);
		return null; 
	}
	
	public int deleteCategoryById(Integer id){
		String sql = "delete from `category` where `id` = ?";
		return getJdbcTemplate().update(sql, new Object[] {id});
	}

	public List<Category> getCategoryByStatus(Integer status) {
		if(!Category.STATUS_HIDDEN.equals(status) && !Category.STATUS_NORMAL.equals(status)){
			throw new DaoException("非正常的分类状态");
		}
		String sql = "select * from category where status = ? order by order_id";
		return getJdbcTemplate().query(sql, new Object[]{status}, new Category());
	}

	public boolean existName(Integer id, String name) {
		if(id == null){
			String sql = "select exists(select * from category where name = ?)";
			Integer result = getJdbcTemplate().queryForInt(sql, new Object[] {name});
			return result > 0;
		}
		String sql = "select exists(select * from category where name = ? and id != ?)";
		Integer result = getJdbcTemplate().queryForInt(sql, new Object[] {name, id});
		return result > 0;
	}

	public int addCategoryCount(Integer categoryId, int count) {
		String sql = "update category set count = count + ? where id = ?";
		return getJdbcTemplate().update(sql, new Object[] {count, categoryId});
	}
}
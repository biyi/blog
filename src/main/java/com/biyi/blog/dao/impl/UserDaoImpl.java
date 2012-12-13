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

import com.biyi.blog.dao.IUserDao;
import com.biyi.blog.dao.vo.User;

@Component
public class UserDaoImpl extends BaseDaoImpl implements IUserDao {
	
	public List<User> queryByExample(User instance, Integer startPage, Integer pageLength){
		StringBuilder sb = new StringBuilder("select * from `user` where 1 = 1");
		if(instance.getId() != null){
			User vo = getUserById(instance.getId());
			if(vo != null){
				List<User> result = new ArrayList<User>(1);
				result.add(vo);
				return result;
			}
		}
		List<Object> params = new ArrayList<Object>();
		if(instance.getUserName() != null){
			sb.append(" and `user_name` = ?");
			params.add(instance.getUserName());
		}
		if(instance.getPassword() != null){
			sb.append(" and `password` = ?");
			params.add(instance.getPassword());
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
		return getJdbcTemplate().query(sql, params.toArray(), new User());
	}
	
	public int countByExample(User instance){
		StringBuilder sb = new StringBuilder("select count(*) from `user` where 1 = 1");
		if(instance.getId() != null){
			if(existByExample(instance))
				return 1;
		}
		List<Object> params = new ArrayList<Object>();
		if(instance.getUserName() != null){
			sb.append(" and `user_name` = ?");
			params.add(instance.getUserName());
		}
		if(instance.getPassword() != null){
			sb.append(" and `password` = ?");
			params.add(instance.getPassword());
		}
		if(instance.getName() != null){
			sb.append(" and `name` = ?");
			params.add(instance.getName());
		}
		String sql = sb.toString();
		return getJdbcTemplate().queryForInt(sql, params.toArray());
	}
	
	public boolean existByExample(User instance){
		if(instance.getId() != null){
			User vo = getUserById(instance.getId());
			if(vo != null){
				return true;
			}
			return false;
		}
		StringBuilder sb = new StringBuilder("select 1 from `user` where 1 = 1");
		List<Object> params = new ArrayList<Object>();
		if(instance.getUserName() != null){
			sb.append(" and `user_name` = ?");
			params.add(instance.getUserName());
		}
		if(instance.getPassword() != null){
			sb.append(" and `password` = ?");
			params.add(instance.getPassword());
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
	
	public int addUser(User instance){
		StringBuilder sb = new StringBuilder("insert into `user`(");
		StringBuilder sb1 = new StringBuilder();
		final List<Object> params = new ArrayList<Object>();
		if(instance.getUserName() != null){
			sb.append("`user_name`,");
			sb1.append("?,");
			params.add(instance.getUserName());
		}
		if(instance.getPassword() != null){
			sb.append("`password`,");
			sb1.append("?,");
			params.add(instance.getPassword());
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
	
	public int updateUserById(User instance){
		if(instance.getId() == null){
			throw new NullPointerException("no primary key");
		}
		StringBuilder sb = new StringBuilder("update `user` set");
		List<Object> params = new ArrayList<Object>();
		if(instance.getUserName() != null){
			sb.append(" `user_name` = ?,");
			params.add(instance.getUserName());
		}
		if(instance.getPassword() != null){
			sb.append(" `password` = ?,");
			params.add(instance.getPassword());
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
	
	public User getUserById(Integer id){
		String sql = "select * from `user` where `id` = ?";
		List<User> instances = getJdbcTemplate().query(sql, new Object[] {id}, new User());
		if(instances != null && instances.size() > 0)
			return instances.get(0);
		return null; 
	}
	
	public int deleteUserById(Integer id){
		String sql = "delete from `user` where `id` = ?";
		return getJdbcTemplate().update(sql, new Object[] {id});
	}

}

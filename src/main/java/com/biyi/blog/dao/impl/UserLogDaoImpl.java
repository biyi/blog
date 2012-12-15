package com.biyi.blog.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.List;
import java.util.ArrayList;
import com.biyi.blog.dao.vo.UserLog;
import com.biyi.blog.dao.IUserLogDao;


import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

@Component
public class UserLogDaoImpl extends BaseDaoImpl implements IUserLogDao{
	
	public List<UserLog> queryByExample(UserLog instance, Integer startPage, Integer pageLength){
		StringBuilder sb = new StringBuilder("select * from `user_log` where 1 = 1");
		if(instance.getId() != null){
			UserLog vo = getUserLogById(instance.getId());
			if(vo != null){
				List<UserLog> result = new ArrayList<UserLog>(1);
				result.add(vo);
				return result;
			}
		}
		List<Object> params = new ArrayList<Object>();
		if(instance.getMessage() != null){
			sb.append(" and `message` = ?");
			params.add(instance.getMessage());
		}
		if(instance.getOperate() != null){
			sb.append(" and `operate` = ?");
			params.add(instance.getOperate());
		}
		if(instance.getObjectId() != null){
			sb.append(" and `object_id` = ?");
			params.add(instance.getObjectId());
		}
		if(instance.getCreateTime() != null){
			sb.append(" and `create_time` = ?");
			params.add(instance.getCreateTime());
		}
		if(instance.getUserId() != null){
			sb.append(" and `user_id` = ?");
			params.add(instance.getUserId());
		}
		if(instance.getIp() != null){
			sb.append(" and `ip` = ?");
			params.add(instance.getIp());
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
		return getJdbcTemplate().query(sql, params.toArray(), new UserLog());
	}
	
	public int countByExample(UserLog instance){
		StringBuilder sb = new StringBuilder("select count(*) from `user_log` where 1 = 1");
		if(instance.getId() != null){
			if(existByExample(instance))
				return 1;
		}
		List<Object> params = new ArrayList<Object>();
		if(instance.getMessage() != null){
			sb.append(" and `message` = ?");
			params.add(instance.getMessage());
		}
		if(instance.getOperate() != null){
			sb.append(" and `operate` = ?");
			params.add(instance.getOperate());
		}
		if(instance.getObjectId() != null){
			sb.append(" and `object_id` = ?");
			params.add(instance.getObjectId());
		}
		if(instance.getCreateTime() != null){
			sb.append(" and `create_time` = ?");
			params.add(instance.getCreateTime());
		}
		if(instance.getUserId() != null){
			sb.append(" and `user_id` = ?");
			params.add(instance.getUserId());
		}
		if(instance.getIp() != null){
			sb.append(" and `ip` = ?");
			params.add(instance.getIp());
		}
		String sql = sb.toString();
		return getJdbcTemplate().queryForInt(sql, params.toArray());
	}
	
	public boolean existByExample(UserLog instance){
		if(instance.getId() != null){
			UserLog vo = getUserLogById(instance.getId());
			if(vo != null){
				return true;
			}
			return false;
		}
		StringBuilder sb = new StringBuilder("select 1 from `user_log` where 1 = 1");
		List<Object> params = new ArrayList<Object>();
		if(instance.getMessage() != null){
			sb.append(" and `message` = ?");
			params.add(instance.getMessage());
		}
		if(instance.getOperate() != null){
			sb.append(" and `operate` = ?");
			params.add(instance.getOperate());
		}
		if(instance.getObjectId() != null){
			sb.append(" and `object_id` = ?");
			params.add(instance.getObjectId());
		}
		if(instance.getCreateTime() != null){
			sb.append(" and `create_time` = ?");
			params.add(instance.getCreateTime());
		}
		if(instance.getUserId() != null){
			sb.append(" and `user_id` = ?");
			params.add(instance.getUserId());
		}
		if(instance.getIp() != null){
			sb.append(" and `ip` = ?");
			params.add(instance.getIp());
		}
		sb.append(" limit 1");
		String sql = sb.toString();
		List<Integer> result = getJdbcTemplate().query(sql, params.toArray(), new SingleColumnRowMapper<Integer>(Integer.class));
		if(result != null && result.size() > 0){
			return true;
		}
		return false;
	}
	
	public int addUserLog(UserLog instance){
		StringBuilder sb = new StringBuilder("insert into `user_log`(");
		StringBuilder sb1 = new StringBuilder();
		final List<Object> params = new ArrayList<Object>();
		if(instance.getMessage() != null){
			sb.append("`message`,");
			sb1.append("?,");
			params.add(instance.getMessage());
		}
		if(instance.getOperate() != null){
			sb.append("`operate`,");
			sb1.append("?,");
			params.add(instance.getOperate());
		}
		if(instance.getObjectId() != null){
			sb.append("`object_id`,");
			sb1.append("?,");
			params.add(instance.getObjectId());
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
		if(instance.getIp() != null){
			sb.append("`ip`,");
			sb1.append("?,");
			params.add(instance.getIp());
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
	
	public int updateUserLogById(UserLog instance){
		if(instance.getId() == null){
			throw new NullPointerException("no primary key");
		}
		StringBuilder sb = new StringBuilder("update `user_log` set");
		List<Object> params = new ArrayList<Object>();
		if(instance.getMessage() != null){
			sb.append(" `message` = ?,");
			params.add(instance.getMessage());
		}
		if(instance.getOperate() != null){
			sb.append(" `operate` = ?,");
			params.add(instance.getOperate());
		}
		if(instance.getObjectId() != null){
			sb.append(" `object_id` = ?,");
			params.add(instance.getObjectId());
		}
		if(instance.getCreateTime() != null){
			sb.append(" `create_time` = ?,");
			params.add(instance.getCreateTime());
		}
		if(instance.getUserId() != null){
			sb.append(" `user_id` = ?,");
			params.add(instance.getUserId());
		}
		if(instance.getIp() != null){
			sb.append(" `ip` = ?,");
			params.add(instance.getIp());
		}
		if(params.size() == 0)
			throw new RuntimeException("no parameters set");
		sb.deleteCharAt(sb.length() - 1);
		sb.append(" where `id` = ?");
		params.add(instance.getId());
		String sql = sb.toString();
		return getJdbcTemplate().update(sql, params.toArray());
	}
	
	public UserLog getUserLogById(Integer id){
		String sql = "select * from `user_log` where `id` = ?";
		List<UserLog> instances = getJdbcTemplate().query(sql, new Object[] {id}, new UserLog());
		if(instances != null && instances.size() > 0)
			return instances.get(0);
		return null; 
	}
	
	public int deleteUserLogById(Integer id){
		String sql = "delete from `user_log` where `id` = ?";
		return getJdbcTemplate().update(sql, new Object[] {id});
	}

	public int addUserLog(int userId, Integer operateId) {
		UserLog userLog = new UserLog();
		userLog.setUserId(userId);
		userLog.setOperate(operateId);
		return addUserLog(userLog);
	}

	public int addUserLog(int userId, Integer operateId, String message) {
		UserLog userLog = new UserLog();
		userLog.setUserId(userId);
		userLog.setOperate(operateId);
		userLog.setMessage(message);
		return addUserLog(userLog);
	}
}
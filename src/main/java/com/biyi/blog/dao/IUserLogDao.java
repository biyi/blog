package com.biyi.blog.dao;

import java.util.List;
import com.biyi.blog.dao.vo.UserLog;

public interface IUserLogDao{
	
	public List<UserLog> queryByExample(UserLog instance, Integer startPage, Integer pageLength);
	
	public boolean existByExample(UserLog instance);
	
	public int countByExample(UserLog instance);
	
	public int addUserLog(UserLog instance);
	
	public int updateUserLogById(UserLog instance);
	
	public UserLog getUserLogById(Integer id);
	
	public int deleteUserLogById(Integer id);

	/**
	 * 记录用户日志
	 */
	public int addUserLog(int userId, Integer operateId);

	/**
	 * 记录用户日志
	 */
	public int addUserLog(int userId, Integer operateId, String message);
}
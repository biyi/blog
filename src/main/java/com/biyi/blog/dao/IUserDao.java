package com.biyi.blog.dao;

import java.util.List;
import com.biyi.blog.dao.vo.User;

public interface IUserDao{
	
	public List<User> queryByExample(User instance, Integer startPage, Integer pageLength);
	
	public boolean existByExample(User instance);
	
	public int countByExample(User instance);
	
	public int addUser(User instance);
	
	public int updateUserById(User instance);
	
	public User getUserById(Integer id);
	
	public int deleteUserById(Integer id);

	/**
	 * 根据用户名获取用户信息
	 */
	public User getUserByUserName(String userName);
}
package com.biyi.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biyi.blog.dao.IUserDao;
import com.biyi.blog.dao.vo.User;
import com.biyi.blog.service.IUserService;

@Service
public class UserServiceImpl extends BaseServiceImpl implements IUserService{
	
	@Autowired
	private IUserDao userDao;

	public User getUserById(Integer id) {
		return userDao.getUserById(id);
	}

}

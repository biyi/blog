package com.biyi.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biyi.blog.dao.IUserDao;
import com.biyi.blog.dao.IUserLogDao;
import com.biyi.blog.dao.vo.User;
import com.biyi.blog.dao.vo.UserLog;
import com.biyi.blog.service.IUserService;
import com.biyi.blog.service.bean.UserSessionBean;
import com.biyi.blog.service.exception.ServiceException;
import com.biyi.util.MD5Util;
import com.biyi.util.StringUtil;

@Service
public class UserServiceImpl extends BaseServiceImpl implements IUserService{
	
	@Autowired
	private IUserDao userDao;
	
	@Autowired
	private IUserLogDao userLogDao;

	public UserSessionBean tryLogin(User user) {
		
		if(user == null || 
				StringUtil.isEmpty(user.getUserName()) || StringUtil.isEmpty(user.getPassword())){
			throw new ServiceException("登录信息不得为空");
		}
		
		String password = MD5Util.password(user.getPassword());
		
		String userName = user.getUserName();
		
		User instance = userDao.getUserByUserName(userName);
		
		if(instance == null){
			userLogDao.addUserLog(-1, UserLog.OPERATE_LOGIN_FAIL_USER_NOT_EXSIT);
			throw new ServiceException("帐号或密码错误");
		}
		
		if(!password.equals(instance.getPassword())){
			userLogDao.addUserLog(-1, UserLog.OPERATE_LOGIN_FAIL_PASSWORD_IS_ERROR, user.getPassword());
			throw new ServiceException("帐号或密码错误");
		}
		
		Integer userId = instance.getId();
		
		
		userLogDao.addUserLog(userId, UserLog.OPERATE_LOGIN_SUCCESS);
		
		UserSessionBean userSessionBean = new UserSessionBean();
		userSessionBean.setId(userId);
		userSessionBean.setName(instance.getName());
		return userSessionBean;
	}

}

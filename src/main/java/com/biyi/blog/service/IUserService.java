package com.biyi.blog.service;

import com.biyi.blog.dao.vo.User;
import com.biyi.blog.service.bean.UserSessionBean;

public interface IUserService {
	
	/**
	 * 登录
	 */
	public UserSessionBean tryLogin(User user);

}

package com.biyi.blog.dao.vo;
import java.util.Date;

import java.sql.ResultSet;
import java.sql.SQLException;


import org.springframework.jdbc.core.RowMapper;

public class UserLog implements RowMapper<UserLog>{
	/**
	 * 登录成功
	 */
	public static Integer OPERATE_LOGIN_SUCCESS = 1001;
	/**
	 * 登录不成功-用户不存在
	 */
	public static Integer OPERATE_LOGIN_FAIL_USER_NOT_EXSIT = 1002;
	/**
	 * 登录不成功-密码不正确
	 */
	public static Integer OPERATE_LOGIN_FAIL_PASSWORD_IS_ERROR = 1003;
	
	private Integer id;
	
	private String message;
	
	private Integer operate;
	
	private Integer objectId;
	
	private Date createTime;
	
	private Integer userId;
	
	private Long ip;
	
	public Integer getId(){
		return id;
	}
	public void setId(Integer id){
		this.id = id;
	}
	
	public String getMessage(){
		return message;
	}
	public void setMessage(String message){
		this.message = message;
	}
	
	public Integer getOperate(){
		return operate;
	}
	public void setOperate(Integer operate){
		this.operate = operate;
	}
	
	public Integer getObjectId(){
		return objectId;
	}
	public void setObjectId(Integer objectId){
		this.objectId = objectId;
	}
	
	public Date getCreateTime(){
		return createTime;
	}
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	
	public Integer getUserId(){
		return userId;
	}
	public void setUserId(Integer userId){
		this.userId = userId;
	}
	
	public Long getIp(){
		return ip;
	}
	public void setIp(Long ip){
		this.ip = ip;
	}
	public UserLog mapRow(ResultSet rs, int rowNum) throws SQLException {
		UserLog instance = new UserLog();
		instance.setId(rs.getInt("id"));
		instance.setMessage(rs.getString("message"));
		instance.setOperate(rs.getInt("operate"));
		instance.setObjectId(rs.getInt("object_id"));
		instance.setCreateTime(rs.getTimestamp("create_time"));
		instance.setUserId(rs.getInt("user_id"));
		instance.setIp(rs.getLong("ip"));
		return instance;
	}
}
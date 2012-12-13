package com.biyi.blog.dao.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class User implements RowMapper<User>{
	
	private Integer id;
	
	private String userName;
	
	private String password;
	
	private String name;
	
	public Integer getId(){
		return id;
	}
	public void setId(Integer id){
		this.id = id;
	}
	
	public String getUserName(){
		return userName;
	}
	public void setUserName(String userName){
		this.userName = userName;
	}
	
	public String getPassword(){
		return password;
	}
	public void setPassword(String password){
		this.password = password;
	}
	
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name = name;
	}
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User instance = new User();
		instance.setId(rs.getInt("id"));
		instance.setUserName(rs.getString("user_name"));
		instance.setPassword(rs.getString("password"));
		instance.setName(rs.getString("name"));
		return instance;
	}
}
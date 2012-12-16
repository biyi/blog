package com.biyi.blog.dao.vo;

import java.sql.ResultSet;
import java.sql.SQLException;


import org.springframework.jdbc.core.RowMapper;

public class BlogCategory implements RowMapper<BlogCategory>{
	private Integer id;
	
	private Integer categoryId;
	
	private Integer blogId;
	
	public Integer getId(){
		return id;
	}
	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getCategoryId(){
		return categoryId;
	}
	public void setCategoryId(Integer categoryId){
		this.categoryId = categoryId;
	}
	
	public Integer getBlogId(){
		return blogId;
	}
	public void setBlogId(Integer blogId){
		this.blogId = blogId;
	}
	public BlogCategory mapRow(ResultSet rs, int rowNum) throws SQLException {
		BlogCategory instance = new BlogCategory();
		instance.setId(rs.getInt("id"));
		instance.setCategoryId(rs.getInt("category_id"));
		instance.setBlogId(rs.getInt("blog_id"));
		return instance;
	}
}
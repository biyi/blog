package com.biyi.blog.dao.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.biyi.blog.util.WebUtil;

public class Category implements RowMapper<Category>{
	
	public static final Integer STATUS_NORMAL = 1;
	
	public static final Integer STATUS_HIDDEN = 0;
	
	public static final Integer[] STATUSS = {STATUS_NORMAL, STATUS_HIDDEN};
	
	public static final String[] STATUS_TEXTS = {"正常", "隐藏"};
	
	/**
	 * 未分类的id
	 */
	public static final Integer ID_OTHER_ID = 1000;
	
	private Integer id;
	
	private Integer orderId;
	
	private Integer count;
	
	private Integer status;
	
	private String name;
	
	public Integer getId(){
		return id;
	}
	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getOrderId(){
		return orderId;
	}
	public void setOrderId(Integer orderId){
		this.orderId = orderId;
	}
	
	public Integer getCount(){
		return count;
	}
	public void setCount(Integer count){
		this.count = count;
	}
	
	public Integer getStatus(){
		return status;
	}
	public String getFormatStatus(){
		
		for(int i = 0; i < STATUSS.length; i++){
			if(STATUSS[i].equals(status)){
				return STATUS_TEXTS[i];
			}
		}
		
		return WebUtil.NULL_FIELD;
	}
	public void setStatus(Integer status){
		this.status = status;
	}
	
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name = name;
	}
	public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
		Category instance = new Category();
		instance.setId(rs.getInt("id"));
		instance.setOrderId(rs.getInt("order_id"));
		instance.setCount(rs.getInt("count"));
		instance.setStatus(rs.getInt("status"));
		instance.setName(rs.getString("name"));
		return instance;
	}
}
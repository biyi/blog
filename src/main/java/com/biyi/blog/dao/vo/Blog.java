package com.biyi.blog.dao.vo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

import com.biyi.blog.util.WebUtil;
import com.biyi.util.DateUtil;

public class Blog implements RowMapper<Blog>{
	
	public static final Integer STATUS_NORMAL = 1;
	
	public static final Integer STATUS_HIDDEN = 0;
	
	public static final Integer[] STATUSS = {STATUS_NORMAL, STATUS_HIDDEN};
	
	public static final String[] STATUS_TEXTS = {"正常", "隐藏"};
	
	private Integer id;
	
	private String content;
	
	private String title;
	
	private Date updateTime;
	
	private String description;
	
	private String keyword;
	
	private Date createTime;
	
	private Integer userId;
	
	private Integer status;
	
	public Integer getId(){
		return id;
	}
	public void setId(Integer id){
		this.id = id;
	}
	
	public String getContent(){
		return content;
	}
	public void setContent(String content){
		this.content = content;
	}
	
	public String getTitle(){
		return title;
	}
	public void setTitle(String title){
		this.title = title;
	}
	
	public Date getUpdateTime(){
		return updateTime;
	}
	public String getFormatUpdateTime(){
		return DateUtil.miniteFormat(updateTime);
	}
	public void setUpdateTime(Date updateTime){
		this.updateTime = updateTime;
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
	
	public String getDescription(){
		return description;
	}
	public void setDescription(String description){
		this.description = description;
	}
	
	public String getKeyword(){
		return keyword;
	}
	public void setKeyword(String keyword){
		this.keyword = keyword;
	}
	
	public Date getCreateTime(){
		return createTime;
	}
	public String getFormatCreateTime(){
		return DateUtil.miniteFormat(createTime);
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
	public Blog mapRow(ResultSet rs, int rowNum) throws SQLException {
		Blog instance = new Blog();
		instance.setId(rs.getInt("id"));
		instance.setContent(rs.getString("content"));
		instance.setTitle(rs.getString("title"));
		instance.setUpdateTime(rs.getTimestamp("update_time"));
		instance.setStatus(rs.getInt("status"));
		instance.setDescription(rs.getString("description"));
		instance.setKeyword(rs.getString("keyword"));
		instance.setCreateTime(rs.getTimestamp("create_time"));
		instance.setUserId(rs.getInt("user_id"));
		return instance;
	}
}
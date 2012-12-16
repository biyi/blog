<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%><%@page import="com.biyi.util.*,com.biyi.blog.util.*,java.util.*,com.biyi.blog.dao.vo.*,com.biyi.blog.service.bean.*" %>
<%
String leftPath = request.getContextPath();
String leftBasePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+leftPath+"/";
String leftRequestURI = request.getRequestURI();
String leftCategoryList = "", leftCategoryAdd = "";
String leftBlogList = "", leftBlogAdd = "";
if(leftRequestURI.startsWith(leftPath + "/jsp/admin/category/list")){
	leftCategoryList = "active";
}else if(leftRequestURI.startsWith(leftPath + "/jsp/admin/category/preAdd")){
	leftCategoryAdd = "active";
}else if(leftRequestURI.startsWith(leftPath + "/jsp/admin/category/preUpdate")){
	leftCategoryList = "active";
}else if(leftRequestURI.startsWith(leftPath + "/jsp/admin/blog/list")){
	leftBlogList = "active";
}else if(leftRequestURI.startsWith(leftPath + "/jsp/admin/blog/preAdd")){
	leftBlogAdd = "active";
}else if(leftRequestURI.startsWith(leftPath + "/jsp/admin/blog/preUpdate")){
	leftBlogList = "active";
}
%>
<div class="span3">
  <div class="well sidebar-nav">
    <ul class="nav nav-list">
      <li class="nav-header">分类管理</li>
      <li class="<%=leftCategoryList%>"><a href="<%=leftBasePath %>admin/category/list.do">浏览分类</a></li>
      <li class="<%=leftCategoryAdd%>"><a href="<%=leftBasePath %>admin/category/preAdd.do">添加分类</a></li>
      <li class="nav-header">博文管理</li>
      <li class="<%=leftBlogList%>"><a href="<%=leftBasePath %>admin/blog/list.do">浏览博文</a></li>
      <li class="<%=leftBlogAdd%>"><a href="<%=leftBasePath %>admin/blog/preAdd.do">添加博文</a></li>
      <li class="nav-header">其他管理</li>
      <li><a href="#">Link</a></li>
      <li><a href="#">Link</a></li>
      <li><a href="#">Link</a></li>
    </ul>
  </div><!--/.well -->
</div><!--/span-->
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%><%@page import="com.biyi.util.*,com.biyi.blog.util.*,java.util.*,com.biyi.blog.dao.vo.*,com.biyi.blog.service.bean.*" %>
<%
String bannerPath = request.getContextPath();
String bannerBasePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+bannerPath+"/";
UserSessionBean bannerUserSessionBean = WebUtil.getUserSessionBean(request);
String bannerRequestURI = request.getRequestURI();
String bannerIndexClass = "", bannerAdminClass = "", bannerAboutClass = "";
if(bannerRequestURI.startsWith(bannerPath + "/jsp/admin/")){
	bannerAdminClass = "active";
}else{
	bannerIndexClass = "active";
}
%>
<div class="navbar navbar-inverse navbar-fixed-top">
  <div class="navbar-inner">
    <div class="container">
      <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </a>
      <a class="brand" href="<%=bannerBasePath%>">&nbsp;&nbsp;&nbsp;</a>
      <div class="nav-collapse collapse">
      	<%
      	if(bannerUserSessionBean != null){
      		%>
      		<p class="navbar-text pull-right">欢迎登录， <a href="#" class="navbar-link"><%=bannerUserSessionBean.getName() %></a></p>
      		<%
      	}
      	%>
        <ul class="nav">
          <li class="<%=bannerIndexClass%>"><a href="<%=bannerBasePath%>">首页</a></li>
          <%
          if(bannerUserSessionBean != null){
        	  %>
        	  <li class="<%=bannerAdminClass%>"><a href="<%=bannerBasePath%>admin/login/index.do">后台</a></li>
        	  <%
          }
          %>
          <li class="<%=bannerAboutClass%>"><a href="#about">关于</a></li>
        </ul>
      </div><!--/.nav-collapse -->
    </div>
  </div>
</div>
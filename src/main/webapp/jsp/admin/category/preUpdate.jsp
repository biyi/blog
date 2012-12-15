<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%><%@page import="com.biyi.util.*,com.biyi.blog.util.*,java.util.*,com.biyi.blog.dao.vo.*,com.biyi.blog.service.bean.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
UserSessionBean userSessionBean = WebUtil.getUserSessionBean(request);
String message = (String)request.getAttribute(WebUtil.ERROR);
Category category = (Category)request.getAttribute("category");
%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>Bootstrap, from Twitter</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Le styles -->
    <link href="<%=basePath %>css/bootstrap.css" rel="stylesheet">
    <style type="text/css">
      body {
        padding-top: 60px;
        padding-bottom: 40px;
      }
      .sidebar-nav {
        padding: 9px 0;
      }
    </style>
    <link href="<%=basePath %>css/bootstrap-responsive.css" rel="stylesheet">

    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
  </head>

  <body>

    <div class="navbar navbar-inverse navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container-fluid">
          <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </a>
          <a class="brand" href="#">&nbsp;&nbsp;&nbsp;</a>
          <div class="nav-collapse collapse">
            <p class="navbar-text pull-right">
              欢迎登录， <a href="#" class="navbar-link"><%=userSessionBean.getName() %></a>
            </p>
            <ul class="nav">
              <li class="active"><a href="#">首页</a></li>
              <li><a href="#about">关于</a></li>
            </ul>
          </div><!--/.nav-collapse -->
        </div>
      </div>
    </div>

    <div class="container-fluid">
      <div class="row-fluid">
        <div class="span3">
          <div class="well sidebar-nav">
            <ul class="nav nav-list">
              <li class="nav-header">分类管理</li>
              <li><a href="<%=basePath %>admin/category/list.do">浏览分类</a></li>
              <li><a href="<%=basePath %>admin/category/preAdd.do">添加分类</a></li>
              <li class="active"><a href="<%=basePath %>admin/category/preAdd.do">修改分类</a></li>
              <li class="nav-header">博文管理</li>
              <li><a href="#">Link</a></li>
              <li><a href="#">Link</a></li>
              <li><a href="#">Link</a></li>
              <li><a href="#">Link</a></li>
              <li><a href="#">Link</a></li>
              <li><a href="#">Link</a></li>
              <li class="nav-header">其他管理</li>
              <li><a href="#">Link</a></li>
              <li><a href="#">Link</a></li>
              <li><a href="#">Link</a></li>
            </ul>
          </div><!--/.well -->
        </div><!--/span-->
        <div class="span9">
        	<form class="form-horizontal" action="<%=basePath%>admin/category/update.do" method="post">
        		<%
        		if(!StringUtil.isEmpty(message)){
        			%>
        			<div class="alert">
    					<button type="button" class="close" data-dismiss="alert">&times;</button>
    					<strong>警告!</strong> <%=message %>
    				</div><%
        		}
        		%>
        		<input type="hidden" value="<%=category.getId() %>" name="id"/>
				<div class="control-group">
			    	<label class="control-label" for="name">分类名</label>
			    	<div class="controls">
			    		<input type="text" name="name" id="name" value="<%=category.getName()%>" placeholder="分类名">
			    	</div>
			    </div>
			    <div class="control-group">
			    	<div class="controls">
			    		<button type="submit" class="btn">确 定</button>
			    	</div>
			    </div>
			 </form>
        </div><!--/span-->
      </div><!--/row-->

      <hr>

      <footer>
        <p>&copy; Company 2012</p>
      </footer>

    </div><!--/.fluid-container-->

    <!-- Le javascript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="<%=basePath %>js/jquery.min.js"></script>
	<script src="<%=basePath %>js/bootstrap.min.js"></script>

  </body>
</html>

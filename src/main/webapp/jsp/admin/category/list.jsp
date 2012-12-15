<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%><%@page import="com.biyi.util.*,com.biyi.blog.util.*,java.util.*,com.biyi.blog.dao.vo.*,com.biyi.blog.service.bean.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
UserSessionBean userSessionBean = WebUtil.getUserSessionBean(request);
List<Category> categorys = (List<Category>)request.getAttribute("categorys");
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
              <li class="active"><a href="<%=basePath %>admin/category/list.do">浏览分类</a></li>
              <li><a href="<%=basePath %>admin/category/preAdd.do">添加分类</a></li>
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
        	<div class="row-fluid">
        		<div class="span11">
        			<table class="table table-striped">
		        		<thead>
		        			<tr>
		        				<th>#</th>
		        				<th>分类名</th>
		        				<th>数量</th>
		        				<th>位置</th>
		        				<th>状态</th>
		        				<th>操作</th>
		        			</tr>
		        		</thead>
		        		<%
		        		if(categorys != null && !categorys.isEmpty()){
		        			for(Category category: categorys){
		        				%>
		        				<tr>
		        					<td><%=category.getId() %></td>
		        					<td><%=category.getName() %></td>
		        					<td><%=category.getCount() %></td>
		        					<td><%=category.getOrderId() %></td>
		        					<td><%=category.getFormatStatus() %></td>
		        					<td><a href="<%=basePath %>admin/category/preUpdate.do?id=<%=category.getId()%>">修改</a></td>
		        				</tr>
		        				<%
		        			}
		        		}
		        		%>
		    		</table>
        		</div>
        	</div>
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

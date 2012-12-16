<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%><%@page import="com.biyi.util.*,com.biyi.blog.util.*,java.util.*,com.biyi.blog.dao.vo.*,com.biyi.blog.service.bean.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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

    <%@include file="../../common/banner.jsp" %>

    <div class="container-fluid">
      <div class="row-fluid">
        <%@include file="../common/left.jsp" %>
        <div class="span9">
        	<form class="form-horizontal" action="<%=basePath%>admin/category/add.do" method="post">
        		<%
        		if(!StringUtil.isEmpty(message)){
        			%>
        			<div class="alert">
    					<button type="button" class="close" data-dismiss="alert">&times;</button>
    					<strong>警告!</strong> <%=message %>
    				</div><%
        		}
        		%>
				<div class="control-group">
			    	<label class="control-label" for="name">分类名</label>
			    	<div class="controls">
			    		<input type="text" name="name" id="name"  value="<%=StringUtil.noNull(category.getName())%>" placeholder="分 类 名">
			    	</div>
			    </div>
			    <div class="control-group">
			    	<label class="control-label" for="status">状态</label>
			    	<div class="controls">
			    		<select name="status">
						  <option value="1">正常</option>
						  <option value="0">隐藏</option>
						</select>
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

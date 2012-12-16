<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%><%@page import="com.biyi.util.*,com.biyi.blog.util.*,java.util.*,com.biyi.blog.dao.vo.*,com.biyi.blog.service.bean.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String message = (String)request.getAttribute(WebUtil.ERROR);
Blog blog = (Blog)request.getAttribute("blog");
List<Category> categorys = (List<Category>)request.getAttribute("categorys");
Set<Integer> blogCategoryIds = (Set<Integer>)request.getAttribute("blogCategoryIds");
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
    <link href="<%=basePath %>css/bootstrap-wysihtml5.css" rel="stylesheet" >
    <link href="<%=basePath %>css/bootstrap.css" rel="stylesheet">
    <style type="text/css">
      body {
        padding-top: 60px;
        padding-bottom: 40px;
      }
      .sidebar-nav {
        padding: 9px 0;
      }
      #content{
      	height: 600px;
      	width: 90%;
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
        	<form action="<%=basePath%>admin/blog/update.do" method="post">
        		<%
        		if(!StringUtil.isEmpty(message)){
        			%>
        			<div class="alert">
    					<button type="button" class="close" data-dismiss="alert">&times;</button>
    					<strong>警告!</strong> <%=message %>
    				</div><%
        		}
        		%>
        		<input type="hidden" value="<%=blog.getId() %>" name="id"/>
				<fieldset>
					<legend>修改博文</legend>
					
					<label for="title">标题名</label>
					<input type="text" name="title" id="title"  value="<%=StringUtil.noNull(blog.getTitle())%>" placeholder="标 题 名">
					
					<label for="keyword">关键词</label>
					<input type="text" name="keyword" id="keyword"  value="<%=StringUtil.noNull(blog.getKeyword())%>" placeholder="关 键 词">
					
					<label for="description">简介</label>
					<input type="text" name="description" id="description"  value="<%=StringUtil.noNull(blog.getDescription())%>" placeholder="简 介">
					
					<label for="status">状态</label>
					<select name="status">
			    		<option value="0">隐藏</option>
						<option value="1">正常</option>
					</select>
					
					<label for="status">状态</label>
					<%
					if(categorys != null && !categorys.isEmpty()){
						for(Category category: categorys){
							String checked = "";
							if(blogCategoryIds.contains(category.getId())){
								checked = "checked='checked'";
							}
							%>
							<label class="checkbox inline">
								<input <%=checked %> type="checkbox" name="categoryId" value="<%=category.getId()%>"> <%=category.getName() %>
							</label>
							<%
						}
					}
					%>
					
					<label for="content">内容</label>
					<textarea id="content" name="content" placeholder="内 容"><%=StringUtil.noNull(blog.getContent())%></textarea>
					
					
					<label class="checkbox">
						<input type="checkbox"> Check me out
					</label>
					<button type="submit" class="btn">确 定</button>
				</fieldset>
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
    <script src="<%=basePath %>js/wysihtml5-0.3.0.min.js"></script>
    <script src="<%=basePath %>js/jquery.min.js"></script>
	<script src="<%=basePath %>js/bootstrap.min.js"></script>
	<script src="<%=basePath %>js/bootstrap-wysihtml5.js"></script>
	<script type="text/javascript">
		$('#content').wysihtml5();
	</script>
  </body>
</html>

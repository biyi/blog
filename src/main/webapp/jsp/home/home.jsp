<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%><%@page import="com.biyi.util.*,com.biyi.blog.util.*,java.util.*,com.biyi.blog.dao.vo.*,com.biyi.blog.service.bean.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
List<Category> categorys = (List<Category>)request.getAttribute("categorys");
List<Blog> blogs = (List<Blog>)request.getAttribute("blogs");
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
    </style>
    <link href="<%=basePath %>css/bootstrap-responsive.css" rel="stylesheet">

    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
  </head>

  <body>

    <%@include file="../common/banner.jsp" %>

    <div class="container">

      <!-- Main hero unit for a primary marketing message or call to action -->
      <div class="hero-unit">
		<p><strong>“以我多年的上网经验，这应该是某个人的博客。元芳你怎么看？”</strong></p>
		<p><strong>“这浓浓的山寨气息，这些随意转载的网文，无不显示博主是个低阶码农。”</strong></p>
		</div>

	<div class="span12">
		<div class="row">
			<div class="span8">
				<div class="row-fluid">
					<div class="span12">
						<div class="offset1">
							
							<%if(blogs != null && !blogs.isEmpty()){
								
								for(int i = 0; i < blogs.size(); i++){
									Blog blog = blogs.get(i);
									%>
									<div class="row">
										<h3><%=blog.getTitle() %></h3>
										<p><%=blog.getDescription() %><a class="btn btn-link" href="#">查看更多</a></p>
									</div>
									<%
									if(i < (blogs.size() - 1)){
										%><hr><%
									}
								}
							} %>
							
						</div>

					</div>	
				</div>	

			</div>	
			<div class="span4">
				<blockquote>
					<ul class="unstyled">
						<%
						if(categorys != null && !categorys.isEmpty()){
							for(Category category: categorys){
								%><li><a href=""><%=category.getName() %>(<%=category.getCount() %>)</a></li><%
							}
						}
						%>
						<li><a href="">其他分类</a></li>
					</ul>	
				</blockquote>
			</div>

		</div>		
	</div>


      <!-- Example row of columns -->
      
      <hr>

      <footer>
		<div class="container">
        <p style="text-align:center">&copy; Company 2012</p>
		</div>
	  </footer>

    </div> <!-- /container -->

    <!-- Le javascript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="<%=basePath %>js/jquery.min.js"></script>
	<script src="<%=basePath %>js/bootstrap.min.js"></script>

  </body>
</html>
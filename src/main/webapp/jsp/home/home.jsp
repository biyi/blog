<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%><%@page import="com.biyi.util.*,com.biyi.blog.util.*,java.util.*,com.biyi.blog.dao.vo.*,com.biyi.blog.service.bean.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
        <div class="container">
          <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </a>
          <a class="brand" href="#">&nbsp;&nbsp;&nbsp;</a>
          <div class="nav-collapse collapse">
            <ul class="nav">
              <li class="active"><a href="#">首页</a></li>
              <li><a href="#about">关于</a></li>
            </ul>
          </div><!--/.nav-collapse -->
        </div>
      </div>
    </div>

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
							<div class="row">
								<h3>memcache相关的总结</h3>
								<p>memcached   是以LiveJournal   旗下Danga Interactive   公司的Brad Fitzpatric   为首开发的一款软件。现在
已成为mixi、hatena、Facebook、Vox、LiveJournal等众多服务中提高Web应用扩展性的重要因素。<a class="btn btn-link" href="#">查看更多</a></p>
							</div>

							<hr>

							<div class="row">
								<h3>memcache相关的总结</h3>
								<p>memcached   是以LiveJournal   旗下Danga Interactive   公司的Brad Fitzpatric   为首开发的一款软件。现在
已成为mixi、hatena、Facebook、Vox、LiveJournal等众多服务中提高Web应用扩展性的重要因素。<a class="btn btn-link" href="#">查看更多</a></p>
							</div>

							<hr>

							<div class="row">
								<h3>memcache相关的总结</h3>
								<p>memcached   是以LiveJournal   旗下Danga Interactive   公司的Brad Fitzpatric   为首开发的一款软件。现在
已成为mixi、hatena、Facebook、Vox、LiveJournal等众多服务中提高Web应用扩展性的重要因素。<a class="btn btn-link" href="#">查看更多</a></p>
							</div>

							<hr>

							<div class="row">
								<h3>memcache相关的总结</h3>
								<p>memcached   是以LiveJournal   旗下Danga Interactive   公司的Brad Fitzpatric   为首开发的一款软件。现在
已成为mixi、hatena、Facebook、Vox、LiveJournal等众多服务中提高Web应用扩展性的重要因素。<a class="btn btn-link" href="#">查看更多</a></p>
							</div>
							<hr>

							<div class="row">
								<h3>memcache相关的总结</h3>
								<p>memcached   是以LiveJournal   旗下Danga Interactive   公司的Brad Fitzpatric   为首开发的一款软件。现在
已成为mixi、hatena、Facebook、Vox、LiveJournal等众多服务中提高Web应用扩展性的重要因素。<a class="btn btn-link" href="#">查看更多</a></p>
							</div>


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
						}else{
							%><li><a href="">似乎没有分类(0)</a></li><%
						}
						%>
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
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0">
<link rel="stylesheet" href="static/css/bootstrap.min.css" />
<link rel="stylesheet" href="static/css/bootstrap-theme.min.css" />
<script type="text/javascript" src="static/js/jquery.min.js"></script>
<script type="text/javascript" src="static/js/bootstrap.min.js"></script>
<title>Music List</title>

<style type="text/css">
body { padding-top: 5px; }
height {
	height: 300
}
a:hover,
a:focus {
  color: #23527c;
  text-decoration: none;
}
</style>

</head>
<body>
    <div class="container">
		<div id="carousel-example-generic" class="carousel slide"
	        data-ride="carousel">
	        <ol class="carousel-indicators">
	            <li data-target="#carousel-example-generic" data-slide-to="0"
	                class="active"></li>
	            <li data-target="#carousel-example-generic" data-slide-to="1"></li>
	            <li data-target="#carousel-example-generic" data-slide-to="2"></li>
	        </ol>
	        <div class="carousel-inner"
	            style="background-image: url(static/img/4.jpg); background-repeat: repeat-x;">
	            <div class="item active">
	                <img src="static/img/123.jpg" alt="...">
	                <div class="carousel-caption">
	                    <h4>hello</h4>
	                </div>
	            </div>
	            <div class="item"
	                style="background-image: url(static/img/5.jpg); background-repeat: repeat-x;">
	                <img src="static/img/5.jpg" alt="...">
	                <div class="carousel-caption">
	                    <h4>hello</h4>
	                </div>
	            </div>
	            <div class="item"
	                style="background-image: url(static/img/6.jpg); background-repeat: repeat-x;">
	                <img src="static/img/6.jpg" alt="...">
	                <div class="carousel-caption">
	                    <h4>hello</h4>
	                </div>
	            </div>
	        </div>
	        <a class="left carousel-control" href="#carousel-example-generic"
	            data-slide="prev"> <span class="glyphicon glyphicon-chevron-left"></span>
	        </a> <a class="right carousel-control" href="#carousel-example-generic"
	            data-slide="next"> <span
	            class="glyphicon glyphicon-chevron-right"></span>
	        </a>
	    </div>
	    <div>
	       <div>
	           <a href="#" class="glyphicon pull-right" data-show-count="false" data-size="large">更多+</a>
	           <div class="page-header">
	               <h2><a href="#" class="text-info">热门推荐</a><small>   <a href="#" >摇滚</a> | <a href="#" >流行</a></small></h2>
			    </div>
			    
	       </div>
        </div>

    </div>
</body>
</html>
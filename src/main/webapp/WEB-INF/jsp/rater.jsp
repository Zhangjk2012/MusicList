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
<link rel="stylesheet" href="styles/style.css">
<link rel="stylesheet" href="styles/divers.css">
<link rel="stylesheet" href="styles/core.css">
<link href="styles/pagination.css" rel="stylesheet" type="text/css">

<style type="text/css">
    #wrapper{
	    width: 900px;
	    margin: 20px auto;
	    text-align:center;
	    height:105px;
	    line-height:105px;
	    overflow:hidden;
	    vertical-align:middle;
	}
</style>
<title>榜单介绍</title>
</head>
<body>
        <div id="nav" class="m-subnav j-tflag">
	        <div class="wrap f-pr">
	            <ul class="nav">
	                <li><a hidefocus="true" href="main.html" class=""><em>首页</em></a></li>
	                <li><a hidefocus="true" href="/discover/toplist" class="z-slt"><em>电台列表</em></a></li>
	                <li><a hidefocus="true" href="/discover/toplist" class="z-slt"><em>背景介绍</em></a></li>
	                <li><a hidefocus="true" href="/discover/toplist" class="z-slt"><em>评委介绍</em></a></li>
	                <li><a hidefocus="true" href="/discover/toplist" class="z-slt"><em>近期活动</em></a></li>
	                <li><a hidefocus="true" href="/discover/toplist" class="z-slt"><em>联系我们</em></a></li>
	            </ul>
	        </div>
	    </div>
        <div class="g-bd4 f-cb p-share">
           <div class="g-mn4">
            <div class="g-mn4c">
                <div class="g-wrap6">
                    <div class="n-songtb">
                        <div class="u-title u-title-1 f-cb">
	                        <h3>
	                            <span class="f-ff2">${rater.name }</span>
	                        </h3>
	                    </div>
	                    <div>
	                        <div>
	                            <div class="j-flag">
	                                <div style="padding: 5px;">
	                                   ${rater.content }
	                                </div>
	                            </div>
	                            <div class="j-flag"></div>
	                        </div>
	                    </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="g-ft">
		<div class="m-ft">
			<div class="wrap">
			     <div class="copy">
			         <p>合作单位:</p>
			         <div id="partners"> 
			         </div>
			     </div>
			 </div>
		</div>
	</div>
</body>

    <script src="static/js/jquery-1.8.2.min.js"></script>
<!--     <script src="static/js/pagination.min.js"></script>
    <script type="text/javascript" src="js/paybutton.js" ></script>
 -->    <script type="text/javascript" src="static/js/common.js" ></script>
</html>
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
<title>热门推荐</title>

<style type="text/css">
#s-list .m-cvrlst li {
    width: 154px;
}

#s-list .m-cvrlst  .u-cover-1 {
    width: 154px;
}
#wrapper{
    width: 900px;
    margin: 20px auto;
}
</style>
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
    <input type="hidden" id="songcount" value="${songcount}"/>
    <div class="g-bd" id="m-disc-pl-c">
        <div class="g-wrap p-pl f-pr">
            <div class="u-title f-cb">
                <h3>
                    <span class="f-ff2 d-flag">热门推荐</span>
                </h3>
            </div>
            <ul class="m-cvrlst f-cb" id="musicList">
            </ul>
        </div>
	</div>
	<div id="wrapper">
	   <div id="Pagination" class=""></div>
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
    <script src="static/js/pagination.min.js"></script>
    <script src="static/js/common.js"></script>
    <script src="static/js/hotsonglist.js"></script>
</html>
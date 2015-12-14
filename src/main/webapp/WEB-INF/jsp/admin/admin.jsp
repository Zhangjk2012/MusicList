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
<title>后台管理页面</title>
<link rel="stylesheet" type="text/css" href="static/easyui/themes/bootstrap/easyui.css"/>
<link rel="stylesheet" type="text/css" href="static/easyui/themes/icon.css"/>
<script type="text/javascript" src="static/js/jquery.min.js"></script>
<script type="text/javascript" src="static/easyui/jquery.easyui.min.js"></script>
<style type="text/css">
        .easyui-accordion ul
        {
            list-style-type: none;
            margin: 0px;
            padding: 10px;
        }
        .easyui-accordion ul li
        {
            padding: 0px;
        }
        .easyui-accordion ul li a
        {
            line-height: 24px;
        }
        .easyui-accordion ul li div
        {
            margin: 2px 0px;
            padding-left: 10px;
            padding-top: 2px;
        }
        .easyui-accordion ul li div.hover
        {
            border: 1px dashed #99BBE8;
            background: #E0ECFF;
            cursor: pointer;
        }
        .easyui-accordion ul li div.hover a
        {
            color: #416AA3;
        }
        .easyui-accordion ul li div.selected
        {
            border: 1px solid #99BBE8;
            background: #E0ECFF;
            cursor: default;
        }
        .easyui-accordion ul li div.selected a
        {
            color: #416AA3;
            font-weight: bold;
        }
</style>

<script type="text/javascript">
$(function () {
    InitLeftMenu();
    $('body').layout();
})

function InitLeftMenu() {
    $('.easyui-accordion li a').click(function () {
        var tabTitle = $(this).text();
        var url = $(this).attr("href");
        addTab(tabTitle, url);
        $('.easyui-accordion li div').removeClass("selected");
        $(this).parent().addClass("selected");
    }).hover(function () {
        $(this).parent().addClass("hover");
    }, function () {
        $(this).parent().removeClass("hover");
    });
}

function addTab(subtitle, url) {
    if (!$('#tabs').tabs('exists', subtitle)) {
        $('#tabs').tabs('add', {
            title: subtitle,
            content: createFrame(url),
            closable: true,
            width: $('#mainPanle').width() - 10,
            height: $('#mainPanle').height() - 26
        });
    } else {
        $('#tabs').tabs('select', subtitle);
    }
}

function createFrame(url) {
    var s = '<iframe name="mainFrame" scrolling="no" frameborder="0"  src="' + url + '" style="width:100%;height:100%;"></iframe>';
    return s;
}
</script>

</head>
<body class="easyui-layout">
	<div region="north" style="height: 120px;">
		<header style="height:100px;">
			<h1>音乐榜管理系统</h1>
			<div style="float: right">
				<span >欢迎登录：${username}.</span> 
				<a href="#">退出</a>
			</div>
		</header>
	</div>

	<div region="west" title="导航菜单" split="true" style="width: 220px">
	       <div class="easyui-accordion" style="width:210px;height:400px">
		        <div title="系统管理" style="overflow:auto;padding:10px;">
		          <ul>
		          <li><div>
                        <a target="mainFrame" href="Product/Default.htm">网站设置</a></div></li>
		              <li><div>
                        <a target="mainFrame" href="Product/Default.htm">歌手管理</a></div></li>
		              <li><div>
                        <a target="mainFrame" href="Product/Default.htm">专辑管理</a></div></li>
		              <li><div>
                        <a target="mainFrame" href="Product/Default.htm">歌曲管理</a></div></li>
		              <li><div>
                        <a target="mainFrame" href="Product/Default.htm">新碟管理</a></div></li>
		              <li><div>
                        <a target="mainFrame" href="Product/Default.htm">投票管理</a></div></li>
		              <li><div>
                        <a target="mainFrame" href="Product/Default.htm">推荐歌曲管理</a></div></li>
		              <li><div>
                        <a target="mainFrame" href="Product/Default.htm">标题栏管理</a></div></li>
		          </ul>
		        </div>
		    </div>
	</div>

	<div region="center" id="mainPanle">
	   
	   <div id="tabs" class="easyui-tabs" fit="true" border="false">
            <div title="主页" style="padding: 20px;" id="home">
                <h1>
                    Welcome...</h1>
            </div>
        </div>
	   
	</div>

	<!-- <div region="south" style="height: 50px;">
		<center>
			<h3>页面底部</h3>
		</center>
	</div> -->
</body>
</html>
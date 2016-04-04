<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>热门推荐</title>
<link rel="stylesheet" type="text/css" href="static/easyui/themes/bootstrap/easyui.css" />
<link rel="stylesheet" type="text/css" href="static/easyui/themes/icon.css" />
<link rel="stylesheet" type="text/css" href="static/uploadify/uploadify.css" />
<style type="text/css">
	.f1{
        width:200px;
    }
</style>
</head>
<body>
    <table id="songlist"></table>
    <div id="songListWin" iconCls="icon-save" title="歌曲列表">
        <table id="selectsonglist"></table>
        <div id="tb" style="padding:5px;">
            <div style="padding:0 0 0 7px;color:#333;">
                歌曲名称：<input type="text" class="easyui-textbox" id="songName" style="width:110px;">
                <a plain="true" onclick="searchSongs()" class="easyui-linkbutton" icon="icon-search">查 询</a>
            </div>
        </div>
        <div id="songListWinFooter" style="text-align:center;padding:5px">
            <a href="javascript:void(0)" plain="true" icon="icon-ok" class="easyui-linkbutton" onclick="addSongsOK()">确 定</a>
            <a href="javascript:void(0)" plain="true" icon="icon-cancel" class="easyui-linkbutton" onclick="cancelAddSong()">取 消</a>
        </div>
    </div>
    <script type="text/javascript" src="static/js/jquery.min.js"></script>
    <script type="text/javascript" src="static/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="static/easyui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="static/uploadify/swfobject.js"></script>
    <script type="text/javascript" src="static/uploadify/jquery.uploadify.min.js"></script>
    <script type="text/javascript" src="js/hotsong.js"></script>
</body>
</html>
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
<title>专辑管理</title>
<link rel="stylesheet" type="text/css" href="static/easyui/themes/bootstrap/easyui.css" />
<link rel="stylesheet" type="text/css" href="static/easyui/themes/icon.css" />
<link rel="stylesheet" type="text/css" href="static/uploadify/uploadify.css" />
<style type="text/css">
	.f1{
        width:200px;
    }
    html,body {
        height:100%;
    }
    body{
        height:100%;
    }
    .div1 {
        padding:5px;
        height: 45%;
    }
</style>
</head>
<body>
    <div class="div1">
		<table id="albumlist"></table>
    </div>
    <div class="div1">
        <table id="songlist"></table>
    </div>
	<div id="win" iconCls="icon-save" title="专辑信息">
        <form id="albumform" action="admin/addAlbum" data-options="novalidate:true" method="post" enctype="multipart/form-data">
            <table>
                <tr>
                    <td>专辑名称:</td>
                    <td><input name="name" data-options="required:true" class="f1 easyui-textbox"></input></td>
                </tr>
                <tr>
                    <td>歌手:</td>
                    <td>
                        <input class="f1 easyui-combobox" data-options="required:true,editable:false" id="singer" name="singer">
                    </td>
                </tr>
                <tr>
                    <td>发行日期:</td>
                    <td>
                    <input name="publishTime" data-options="required:true,editable:false" class="f1 easyui-datebox"></input></td>
                </tr>
                <tr>
                    <td>发行公司:</td>
                    <td>
                    <input name="publishCompany" data-options="multiline:true" style="height:60px" class="f1 easyui-textbox"></input></td>
                </tr>
                <tr>
                    <td>简介:</td>
                    <td><input name="briefIntroduction" data-options="multiline:true" style="height:60px" class="f1 easyui-textbox"></input></td>
                </tr>
                <tr>
                    <td>专辑图片:</td>
                    <td><img id="img" class="f1" height="100" src=""/></td>
                </tr>
                <tr>
                    <td>上传图片:</td>
                    <td>
						<div>
                            <input type="hidden" id="picture" name="picture" />
                            <div id="fileQueue"></div>
                            <input id="file_upload" name="file_upload" type="file" multiple="multiple">
                        </div>
					</td>
					<td>
						<a href="javascript:void(0)" class="easyui-linkbutton" id="btnUpload"
                                onclick="javascript: $('#file_upload').uploadify('upload', '*')">上传</a>
                        <a href="javascript:void(0)" class="easyui-linkbutton" id="btnCancelUpload"
                            onclick="javascript:clearUplaoder()">取消</a>
					</td>
                </tr>
            </table>
        </form>
	    <div id="addFooter" style="text-align:center;padding:5px">
	    	<a href="javascript:void(0)" icon="icon-ok" class="easyui-linkbutton" onclick="submitForm()">保存</a>
	    	<a href="javascript:void(0)" icon="icon-cancel" class="easyui-linkbutton" onclick="closeWin()">取消</a>
	    </div>
	</div>
	<div id="updateWin" iconCls="icon-edit" title="修改专辑信息">
        <form id="updateform" action="admin/updateAlbum" data-options="novalidate:true" method="post" enctype="multipart/form-data">
            <table>
                <tr>
                    <td>专辑名称:</td>
                    <td>
                    <input name="id" type="hidden"></input>
                    <input name="name" data-options="required:true" class="f1 easyui-textbox"></input></td>
                </tr>
                <tr>
                    <td>歌手:</td>
                    <td>
                        <input class="f1 easyui-combobox" data-options="required:true,editable:false" id="updateSinger" name="singer">
                    </td>
                </tr>
                <tr>
                    <td>发行日期:</td>
                    <td>
                    <input name="publishTime" data-options="required:true,editable:false" class="f1 easyui-datebox"></input></td>
                </tr>
                <tr>
                    <td>发行公司:</td>
                    <td>
                    <input name="publishCompany" data-options="multiline:true" style="height:60px" class="f1 easyui-textbox"></input></td>
                </tr>
                <tr>
                    <td>简介:</td>
                    <td><input name="briefIntroduction" data-options="multiline:true" style="height:60px" class="f1 easyui-textbox"></input></td>
                </tr>
                <tr>
                    <td>专辑图片:</td>
                    <td><img id="updateImg" class="f1" height="100" src=""/></td>
                </tr>
                <tr>
                    <td>上传图片:</td>
                    <td>
						<div>
                            <input type="hidden" id="updatePicture" name="picture" />
                            <div id="updateQueue"></div>
                            <input id="update_upload" name="update_upload" type="file" multiple="multiple">
                        </div>
					</td>
					<td>
						<a href="javascript:void(0)" class="easyui-linkbutton" id="btnUpload"
                                onclick="javascript: $('#update_upload').uploadify('upload', '*')">上传</a>
                        <a href="javascript:void(0)" class="easyui-linkbutton" id="btnCancelUpload"
                            onclick="javascript:clearUpdateUplaoder()">取消</a>
					</td>
                </tr>
            </table>
        </form>
        <div id="updateFooter" style="text-align:center;padding:5px">
            <a href="javascript:void(0)" icon="icon-ok" class="easyui-linkbutton" onclick="submitUpdateForm()">保存</a>
            <a href="javascript:void(0)" icon="icon-cancel" class="easyui-linkbutton" onclick="closeUpdateWin()">取消</a>
        </div>
    </div>
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
    <script type="text/javascript" src="source/album.js"></script>
    <script type="text/javascript" src="js/uploadfile.js"></script>
</body>
</html>
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
<title>日常节目</title>
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
	<table id="showlist"></table>
	<div id="win" iconCls="icon-save" title="节目信息">
        <form id="showform" action="admin/addDailyShow" data-options="novalidate:true" method="post" enctype="multipart/form-data">
            <table>
                <tr>
                    <td colspan="4">
                        <div id="songQueue"></div>
                    </td>
                </tr>
                <tr>
                    <td>节目文件:</td>
                    <td>
                        <div>
                            <input type="hidden" id="songPath" name="url" />
                            <input id="song_upload" name="song_upload" type="file" multiple="multiple">
                        </div>
                    </td>
                    <td>
                        <a href="javascript:void(0)" class="easyui-linkbutton" id="btnUpload"
                                onclick="javascript: $('#song_upload').uploadify('upload', '*')">上传</a>
                        <a href="javascript:void(0)" class="easyui-linkbutton" id="btnCancelUpload"
                            onclick="javascript:clearSongUplaoder()">取消</a>
                    </td>
                </tr>
                <tr>
                    <td>节目名称:</td>
                    <td>
                        <input class="f1 easyui-textbox" data-options="required:true" name="name"></input>
                    </td>
                    <td>是否启用:</td>
                    <td>
                        <input type="radio" value="true" name="enable" checked="checked"/>启用
                        <input type="radio" value="false" name="enable"/>禁用
                    </td>
                </tr>
            </table>
        </form>
	    <div id="addFooter" style="text-align:center;padding:5px;">
	    	<a href="javascript:void(0)" icon="icon-ok" class="easyui-linkbutton" onclick="submitForm()">保存</a>
	    	<a href="javascript:void(0)" icon="icon-cancel" class="easyui-linkbutton" onclick="closeWin()">取消</a>
	    </div>
	</div>
	<div id="updateWin" iconCls="icon-edit" title="修改节目信息">
        <form id="updateform" action="admin/updateShow" data-options="novalidate:true" method="post" enctype="multipart/form-data">
            <table>
                <tr>
                    <td>节目名称:</td>
                    <td>
		                <input type="hidden" name="url">
		                <input type="hidden" name="id">
                        <input class="f1 easyui-textbox" data-options="required:true" name="name"></input>
                    </td>
                    <td>是否启用:</td>
                    <td>
                        <input type="radio" value="true" name="enable" checked="checked"/>启用
                        <input type="radio" value="false" name="enable"/>禁用
                    </td>
                </tr>
            </table>
        </form>
        <div id="updateFooter" style="text-align:center;padding:5px">
            <a href="javascript:void(0)" icon="icon-ok" class="easyui-linkbutton" onclick="submitUpdateForm()">保存</a>
            <a href="javascript:void(0)" icon="icon-cancel" class="easyui-linkbutton" onclick="closeUpdateWin()">取消</a>
        </div>
    </div>
    <script type="text/javascript" src="static/js/jquery.min.js"></script>
    <script type="text/javascript" src="static/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="static/easyui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="static/uploadify/swfobject.js"></script>
    <script type="text/javascript" src="static/uploadify/jquery.uploadify.min.js"></script>
    <script type="text/javascript" src="js/dailyshow.js"></script>
    <script type="text/javascript" src="js/uploadfile.js"></script>
</body>
</html>
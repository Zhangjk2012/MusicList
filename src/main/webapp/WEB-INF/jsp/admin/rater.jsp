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
<title>评委管理</title>
<link rel="stylesheet" type="text/css" href="static/easyui/themes/bootstrap/easyui.css" />
<link rel="stylesheet" type="text/css" href="static/easyui/themes/icon.css" />
<link rel="stylesheet" type="text/css" href="static/uploadify/uploadify.css" />
<style type="text/css">
	.f1{
        width:200px;
    }
    textarea {
        display: block;
    }
</style>
</head>
<body>
	<table id="raterlist"></table>
	<div id="win" iconCls="icon-save" title="评委信息">
        <form id="raterform" action="admin/addrater" data-options="novalidate:true" method="post" enctype="multipart/form-data">
            <table>
                <tr>
                    <td>评委姓名:</td>
                    <td ><input name="name" data-options="required:true" class="f1 easyui-textbox"></input></td>
                </tr>
                <tr>
                    <td>评委简介:</td>
                </tr>
                <tr>
                    <td colspan="4">
                        <textarea name="content" id="raterContent" style="width:750px;height:500px;visibility:hidden;"></textarea>
                    </td>
                </tr>
            </table>
        </form>
	    <div id="addFooter" style="text-align:center;padding:5px">
	    	<a href="javascript:void(0)" icon="icon-ok" class="easyui-linkbutton" onclick="submitForm()">保存</a>
	    	<a href="javascript:void(0)" icon="icon-cancel" class="easyui-linkbutton" onclick="closeWin()">取消</a>
	    </div>
	</div>
	<div id="updateWin" iconCls="icon-edit" title="修改评委信息">
        <form id="updateform" action="admin/updaterater" data-options="novalidate:true" method="post" enctype="multipart/form-data">
            <table>
                <tr>
                    <td>评委姓名:</td>
                    <td colspan="2">
                    <input type="hidden" name="id"/>
                    <input name="name" data-options="required:true" class="f1 easyui-textbox"></input></td>
                </tr>
                <tr>
                    <td>评委简介:</td>
                </tr>
                <tr>
                    <td colspan="4">
                        <textarea name="content" id="updateRaterContent" style="width:750px;height:500px;visibility:hidden;"></textarea>
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
<script type="text/javascript" src="kindeditor/kindeditor-min.js"></script>
<script type="text/javascript" src="kindeditor/lang/zh_CN.js"></script>
<script type="text/javascript" src="js/rater.js"></script>
</body>
</html>
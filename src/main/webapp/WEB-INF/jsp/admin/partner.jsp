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
<title>合作厂商管理</title>
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
	<table id="partnerlist"></table>
	<div id="win" iconCls="icon-save" title="合作厂商信息">
        <form id="partnerform" action="admin/addpartner" data-options="novalidate:true" method="post" enctype="multipart/form-data">
            <table>
                <tr>
                    <td>厂商名称:</td>
                    <td colspan="2"><input name="name" data-options="required:true" class="f1 easyui-textbox"></input></td>
                </tr>
                <tr>
                    <td>厂商网站地址:</td>
                    <td colspan="2">
                    <input name="url" width="50px;" data-options="required:true,prompt:'http://...'" class="f1 easyui-textbox"></input>
                    </td>
                </tr>
                <tr>
                    <td>是否启用:</td>
                    <td colspan="2">
                    	<input type="radio" value="true" name="enable" checked="checked"/>启用
                        <input type="radio" value="false" name="enable"/>禁用
                    </td>
                </tr>
                <tr>
                    <td>厂商LOGO:</td>
                    <td><img id="img" class="f1" height="100" src=""/></td>
                </tr>
                <tr>
                    <td>上传LOGO:</td>
                    <td>
						<div>
                            <input type="hidden" id="picture" name="logoPath" />
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
	<div id="updateWin" iconCls="icon-edit" title="修改合作厂商信息">
        <form id="updateform" action="admin/updatepartner" data-options="novalidate:true" method="post" enctype="multipart/form-data">
            <table>
                <tr>
                    <td>厂商名称:</td>
                    <td colspan="2">
                    <input type="hidden" name="id"/>
                    <input name="name" data-options="required:true" class="f1 easyui-textbox"></input></td>
                </tr>
                <tr>
                    <td>厂商网站地址:</td>
                    <td colspan="2">
                    <input name="url" width="50px;" data-options="required:true,prompt:'http://...'" class="f1 easyui-textbox"></input>
                    </td>
                </tr>
                <tr>
                    <td>是否启用:</td>
                    <td colspan="2">
                    	<input type="radio" value="true" name="enable" checked="checked"/>启用
                        <input type="radio" value="false" name="enable"/>禁用
                    </td>
                </tr>
                <tr>
                    <td>厂商LOGO:</td>
                    <td><img id="updateImg" name="update_img" class="f1" height="100" src=""/></td>
                </tr>
                <tr>
                    <td>上传LOGO:</td>
                    <td>
                        <div>
                            <input type="hidden" id="updatePicture" name="logoPath" />
                            <div id="updateQueue"></div>
                            <input id="update_upload" name="update_upload" type="file" multiple="multiple">
                        </div>
                    </td>
                    <td>
                        <a href="javascript:void(0)" class="easyui-linkbutton"
                                onclick="javascript: $('#update_upload').uploadify('upload', '*')">上传</a>
                        <a href="javascript:void(0)" class="easyui-linkbutton"
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
<script type="text/javascript" src="static/js/jquery.min.js"></script>
<script type="text/javascript" src="static/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="static/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="static/uploadify/swfobject.js"></script>
<script type="text/javascript" src="static/uploadify/jquery.uploadify.min.js"></script>
<script type="text/javascript" src="js/partner.js"></script>
<script type="text/javascript" src="js/uploadfile.js"></script>
</body>
</html>
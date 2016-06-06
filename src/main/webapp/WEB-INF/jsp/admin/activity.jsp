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
<title>近期活动</title>
<link rel="stylesheet" type="text/css" href="static/easyui/themes/bootstrap/easyui.css" />
<link rel="stylesheet" type="text/css" href="static/easyui/themes/icon.css" />
<link rel="stylesheet" type="text/css" href="static/uploadify/uploadify.css" />
<link rel="stylesheet" type="text/css" href="kindeditor/themes/simple/simple.css" />
<style type="text/css">
	.f1{
        width:200px;
    }
</style>
</head>
<body>
	 <div class="easyui-panel" data-options="footer:'#ft'" title="近期活动" style="width:800px">
        <div style="padding:10px 60px 20px 60px">
		  <form id="infoform" action="admin/addactivity" data-options="novalidate:true" method="post" enctype="multipart/form-data">
		      <table>
		          <tr>
		              <td>活动主题:</td>
		              <td>
		                <input name="id" id="id" value="${info.id }" type="hidden"></input>
		                <input name="name" value="${info.name }" data-options="required:true" class="f1 easyui-textbox"></input>
		              </td>
                      <td>活动图片:</td>
                      <td><img id="img" class="f1" height="130" src="${info.path }"/></td>
                  </tr>
                  <tr>
                    <td colspan="4">
                       <div id="fileQueue"></div> 
                    </td>
                  </tr>
		          <tr>
                    <td>上传图片:</td>
                    <td>
                        <div>
                            <input type="hidden" id="picture" name="path" value="${info.path }" />
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
                  <tr>
                    <td>活动内容介绍:</td>
                  </tr>
		          <tr>
		             <td colspan="4">
                        <textarea name="content" id="content" style="width:700px;height:300px;visibility:hidden;">${info.content }</textarea>
                     </td>
		          </tr>
		      </table>
		  </form>
  		</div>
  	</div>
  	<div id="ft" style="text-align:center;">
	  <a href="javascript:void(0)" plain="true" icon="icon-ok" class="easyui-linkbutton" onclick="submitForm()">保存</a>
	  <a href="javascript:void(0)" plain="true" icon="icon-cancel" class="easyui-linkbutton" onclick="cancelInfo()">取消</a>
  	</div>
    <script type="text/javascript" src="static/js/jquery.min.js"></script>
    <script type="text/javascript" src="static/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="static/easyui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="static/uploadify/jquery.uploadify.min.js"></script>
    <script type="text/javascript" src="kindeditor/kindeditor-min.js"></script>
    <script type="text/javascript" src="kindeditor/lang/zh_CN.js"></script>
    <script type="text/javascript" src="js/uploadfile.js"></script>
    <script type="text/javascript" src="js/activity.js"></script>
</body>
</html>
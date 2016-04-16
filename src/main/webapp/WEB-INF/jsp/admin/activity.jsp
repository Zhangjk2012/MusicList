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
		                <input name="telephone" id="telephone" value="${info.name }" data-options="required:true" class="f1 easyui-textbox"></input>
		              </td>
		          </tr>
		          <tr>
		          	  <td>活动图片:</td>
		              <td>
		                  <input class="f1 easyui-textbox" id="address" value="${info.path }" data-options="required:true" name="address">
		              </td>
		          </tr>
		          <tr>
		          	  <td>活动内容介绍:</td>
		              <td>
		                  <input class="f1 easyui-textbox" id="address" value="${info.content }" data-options="required:true" name="address">
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
    <script type="text/javascript">
    function cancelInfo() {
    	var id = $("#id").val();
    	if (id !== "") {
	    	$.ajax({
	            url : 'admin/removecontactInfo',
	            data : {"id":id},
	            method:'post',
	            dataType : 'json',
	            success : function(r) {
	                if (r.success) {
	                    $.messager.show({msg : "取消成功。",title : '成功'});
	                    $('#id').val('');
	                    $('#telephone').textbox('clear');
	                    $('#address').textbox('clear');
	                } else {
	                    $.messager.alert('错误',"取消失败。",'error');
	                }
	            }
	        });
    	}
    }
    function submitForm() {
        $('#infoform').form('submit', {
            onSubmit : function() {
                return $(this).form('enableValidation').form('validate');
            },
            success : function(data) {
            	var d = eval("("+data+")");
            	if (d.id) {
            		$("#id").val(d.id);
            	}
                $.messager.show({
                    msg : "保存成功。",
                    title : '成功'
                });
            }
        });
    }
    </script>
</body>
</html>
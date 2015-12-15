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
<title>歌手管理页面</title>
<link rel="stylesheet" type="text/css" href="static/easyui/themes/bootstrap/easyui.css" />
<link rel="stylesheet" type="text/css" href="static/easyui/themes/icon.css" />
<link rel="stylesheet" type="text/css" href="static/uploadify/uploadify.css" />
<script type="text/javascript" src="static/js/jquery.min.js"></script>
<script type="text/javascript" src="static/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="static/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="static/uploadify/swfobject.js"></script>
<script type="text/javascript" src="static/uploadify/jquery.uploadify.min.js"></script>
<style type="text/css">
	.f1{
        width:200px;
    }
</style>
<script type="text/javascript">
	var datagrid;
	$(function(){
		$('#file_upload').uploadify({
            'swf': 'static/uploadify/uploadify.swf',  //FLash文件路径
            'buttonText': '浏  览',                                 //按钮文本
            'uploader': 'file/upload2',                       //处理文件上传Action
            'queueID': 'fileQueue',                        //队列的ID
            folder:'uploader',
            'queueSizeLimit': 10,                          //队列最多可上传文件数量，默认为999
            'auto': false,                                 //选择文件后是否自动上传，默认为true
            'method': "post",
            'multi': false,                                 //是否为多选，默认为true
            'removeCompleted': false,                       //是否完成后移除序列，默认为true
            'fileSizeLimit': '10MB',                       //单个文件大小，0为无限制，可接受KB,MB,GB等单位的字符串值
            fileObjName:'file',
            'fileTypeDesc': 'Image Files',                 //文件描述
            'fileTypeExts': '*.gif; *.jpg; *.png; *.bmp',  //上传的文件后缀过滤器
            'onQueueComplete': function (event, data) {                 //所有队列完成后事件
                ShowUpFiles($("#Attachment_GUID").val(), "div_files");  //完成后更新已上传的文件列表
                $.messager.alert("提示", "上传完毕！");                                     //提示完成           
            },
            'onUploadStart' : function(file) {
                $("#file_upload").uploadify("settings", 'formData', { 'folder': '政策法规', 'guid': "222" }); //动态传参数
            },
            'onUploadError': function (event, queueId, fileObj, errorObj) {
                alert(errorObj.type + "：" + errorObj.info);
            },
            'onUploadSuccess' : function(file, data, response) {
            	alert(data);
           	}
        });
		
		
		
		datagrid=$("#singerlist").datagrid({
			url:"/Test3/ModuleBeanController/getAll.do",//加载的URL
		    isField:"id",
			pagination:true,//显示分页
			pageSize:5,//分页大小
			rownumbers:true,
			pageList:[5,10,15,20],//每页的个数
			fit:true,//自动补全
			fitColumns:true,
			iconCls:"icon-save",//图标
			title:"用户管理",
			columns:[[      //每个列具体内容
		              {
		            	  field:'id',
		            	  title:'id',
		            	  width:100
		           	 },   
		              {field:'pid',title:'pid',width:100},   
		              {field:'text',title:'text',width:100}   
		          ]],
			toolbar:[              //工具条
			        {text:"增加",iconCls:"icon-add",handler:function(){//回调函数
			        	winOpen();
			        }},'-',
			        {text:"删除",iconCls:"icon-remove",handler:function(){
			        	var rows=datagrid.datagrid('getSelections');
			        	if(rows.length<=0)
			        	{
			        		$.messager.alert('警告','您没有选择','error');
			        	}
			        	else if(rows.length>1)
			        	{
			        		$.messager.alert('警告','不支持批量删除','error');
			        	}
			        	else
			        	{
			        		$.messager.confirm('确定','您确定要删除吗',function(t){
			        			if(t)
			        			{
			        				$.ajax({
			        					url : '/Test3/ModuleBeanController/deletecustomer.do',
			        					data : rows[0],
			        					dataType : 'json',
			        					success : function(r) {
			        						if (r.success) {
			        							datagrid.datagrid('acceptChanges');
			        							$.messager.show({
			        								msg : r.msg,
			        								title : '成功'
			        							});
			        							editRow = undefined;
			        							datagrid.datagrid('reload');
			        						} else {
			        							/*datagrid.datagrid('rejectChanges');*/
			        							datagrid.datagrid('beginEdit', editRow);
			        							$.messager.alert('错误', r.msg, 'error');
			        						}
			        						datagrid.datagrid('unselectAll');
			        					}
			        				});
			        			}
			        		})
			        	}
			        }},'-',
			        {text:"修改",iconCls:"icon-edit",handler:function(){
			        	var rows=datagrid.datagrid('getSelections');
			        	if(rows.length==1)
			        	{
			        	}
			        }}
			        ],
			onAfterEdit:function(rowIndex, rowData, changes){
				var inserted = datagrid.datagrid('getChanges', 'inserted');
				var updated = datagrid.datagrid('getChanges', 'updated');
				if (inserted.length < 1 && updated.length < 1) {
					editRow = undefined;
					datagrid.datagrid('unselectAll');
					return;
				}
				var url = '';
				if (inserted.length > 0) {
					url = '/Test3/ModuleBeanController/addcustomer.do';
				}
				if (updated.length > 0) {
					url = '/Test3/ModuleBeanController/addcustomer.do';
				}
				$.ajax({
					url : url,
					data : rowData,
					dataType : 'json',
					success : function(r) {
						if (r.success) {
							datagrid.datagrid('acceptChanges');
							$.messager.show({
								msg : r.msg,
								title : '成功'
							});
							editRow = undefined;
							datagrid.datagrid('reload');
						} else {
							/*datagrid.datagrid('rejectChanges');*/
							datagrid.datagrid('beginEdit', editRow);
							$.messager.alert('错误', r.msg, 'error');
						}
						datagrid.datagrid('unselectAll');
					}
				});
			}
		});
		
		$('#win').window({
			width : 400,
			height : 300,
			modal : true,
			resizeable:false,
			onBeforeClose:function(){ 
				clearForm();
	       }
		});
		$('#win').window('close');
		
	});
	function submitForm(){
		$('#singerform').form('submit',{
			onSubmit:function(){
				return $(this).form('enableValidation').form('validate');
			}
		});
	}
	function clearForm(){
		$('#singerform').form('clear');
	}
	function winOpen() {
		$('#win').window('open');
	}
</script>

</head>
<body>
	<table id="singerlist"></table>
	<div id="win" iconCls="icon-save" title="歌手信息">
        <form id="singerform" action="form1_proc.php" data-options="novalidate:true" method="post" enctype="multipart/form-data">
            <table>
                <tr>
                    <td>歌手名称:</td>
                    <td><input name="name" data-options="required:true" class="f1 easyui-textbox"></input></td>
                </tr>
                <tr>
                    <td>简介:</td>
                    <td><input name="briefIntroduction" data-options="multiline:true" style="height:60px" class="f1 easyui-textbox"></input></td>
                </tr>
                <tr>
                    <td>歌手图片:</td>
                    <td>
						<div>
                            <input class="easyui-validatebox" type="hidden" id="Attachment_GUID" name="Attachment_GUID" />
                            <div id="div_files"></div>
                            <div id="fileQueue"></div>
                            <input id="file_upload" name="file_upload" type="file" multiple="multiple">
                        </div>
					</td>
					<td>
						<a href="javascript:void(0)" class="easyui-linkbutton" id="btnUpload"
                                onclick="javascript: $('#file_upload').uploadify('upload', '*')">上传</a>
                        <a href="javascript:void(0)" class="easyui-linkbutton" id="btnCancelUpload"
                            onclick="javascript: $('#file_upload').uploadify('cancel', '*')">取消</a>
					</td>
                </tr>
            </table>
        </form>
	    <div style="text-align:center;padding:5px">
	    	<a href="javascript:void(0)" icon="icon-ok" class="easyui-linkbutton" onclick="submitForm()">保存</a>
	    	<a href="javascript:void(0)" icon="icon-cancel" class="easyui-linkbutton" onclick="clearForm()">清空</a>
	    </div>
	</div>
</body>
</html>
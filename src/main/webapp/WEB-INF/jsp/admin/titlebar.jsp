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
<title>标题栏管理</title>
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
		datagrid=$("#titlebarlist").datagrid({
			url:"admin/titlebarlist",//加载的URL
		    isField:"id",
			pagination:true,//显示分页
			rownumbers:true,
			fit:true,//自动补全
			fitColumns:true,
			singleSelect:true,
			frozenColumns:[[ 
                {field:'ck',checkbox:true} 
            ]], 
			columns:[[      //每个列具体内容
	              {field:'id',title:'id',hidden:true,width:100},   
	              {field:'name',title:'名称',width:100},
	              {field:'songName',title:'歌曲名称',width:100},
	              {field:'enable',title:'是否启用',width:100,formatter:function(val,row){
	            	  if (val == undefined) {
                          return "";
                      }
                      var text;
                      if (val === true) {
                    	  text = '启用';
                      } else {
                    	  text = '禁用';
                      }
                      return text;
                  }},
                  {field:'picture',title:'标题图片',width:100,formatter:function(val,row){
                 	  if (val == undefined ||val == "") {
                          return "";
                      }
                	  return '<img width=30 height=30 src="'+row.picture+'" alt="类别图片" />';
                  }}
	          ]],
				toolbar : [ //工具条
					{
						text : "增加",
						iconCls : "icon-add",
						handler : function() {//回调函数
							winOpen();
						}
					},
					'-',
					{
						text : "删除",
						iconCls : "icon-remove",
						handler : function() {
							var row = datagrid.datagrid('getSelected');
							if (row <= 0) {
								$.messager.alert('警告', '您没有选择','error');
							} else {
								$.messager.confirm('确定','您确定要删除吗',
								function(t) {
									if (t) {
										$.ajax({
											url : 'admin/deleteTitleBar',
											data : row,
											method:'post',
											dataType : 'json',
											success : function(r) {
												if (r.success) {
													$.messager.show({msg : "删除成功。",title : '成功'});
													datagrid.datagrid('reload');
												} else {
													$.messager.alert('错误',"删除失败。",'error');
												}
											}
										});
									}
								})
							}
						}
					},'-',{
					text : "修改",
					iconCls : "icon-edit",
					handler : function() {
						var row = datagrid.datagrid('getSelected');
                           if (row <= 0) {
                               $.messager.alert('警告', '您没有选择','error');
                           } else {
                           	winUpdateOpen(row);
                           }
					}
			} ]
		});
		var p = $('#titlebarlist').datagrid('getPager'); 
		$(p).pagination({ 
	        pageSize: 20,//每页显示的记录条数，默认为10 
	        pageList: [20],//可以设置每页记录条数的列表 
	        beforePageText: '第',//页数文本框前显示的汉字 
	        afterPageText: '页    共 {pages} 页', 
	        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	    }); 

		$('#win').window({
			width : 500,
			height : 400,
			modal : true,
			closed : true,
			minimizable : false,
			maximizable : false,
			collapsible : false,
			onBeforeClose : function() {
				clearForm();
			},
            onOpen:function() {
            	song.combobox("reload","admin/songcombolist");
            }
		});
		$('#file_upload').uploadify({
			'swf' : 'static/uploadify/uploadify.swf', //FLash文件路径
			'buttonText' : '浏  览', //按钮文本
			'uploader' : 'file/uploadImg', //处理文件上传Action
			'queueID' : 'fileQueue', //队列的ID
			folder : 'uploader',
			'queueSizeLimit' : 1, //队列最多可上传文件数量，默认为999
			'auto' : false, //选择文件后是否自动上传，默认为true
			'method' : "post",
			'multi' : false, //是否为多选，默认为true
			'removeCompleted' : false, //是否完成后移除序列，默认为true
			'fileSizeLimit' : '10MB', //单个文件大小，0为无限制，可接受KB,MB,GB等单位的字符串值
			fileObjName : 'file',
			'fileTypeDesc' : 'Image Files', //文件描述
			'fileTypeExts' : '*.gif; *.jpg; *.png; *.bmp', //上传的文件后缀过滤器
			onUploadSuccess : function(file, data, response) { //一个文件上传成功后的响应事件处理
				$('#picture').val(data);
			    $('#img').attr("src",data);
			},
			onUploadStart : function(file) {
				//$("#file_upload").uploadify("settings", "formData", { "imgType": "other","imgMode":"big") });
			}
		});
		$('#update_upload').uploadify({
            'swf' : 'static/uploadify/uploadify.swf', //FLash文件路径
            'buttonText' : '浏  览', //按钮文本
            'uploader' : 'file/uploadImg', //处理文件上传Action
            'queueID' : 'updateQueue', //队列的ID
            folder : 'uploader',
            'queueSizeLimit' : 1, //队列最多可上传文件数量，默认为999
            'auto' : false, //选择文件后是否自动上传，默认为true
            'method' : "post",
            'multi' : false, //是否为多选，默认为true
            'removeCompleted' : false, //是否完成后移除序列，默认为true
            'fileSizeLimit' : '10MB', //单个文件大小，0为无限制，可接受KB,MB,GB等单位的字符串值
            fileObjName : 'file',
            'fileTypeDesc' : 'Image Files', //文件描述
            'fileTypeExts' : '*.gif; *.jpg; *.png; *.bmp', //上传的文件后缀过滤器
            onUploadSuccess : function(file, data, response) { //一个文件上传成功后的响应事件处理
                $('#updatePicture').val(data);
                $('#updateImg').attr("src",data);
            },
            onUploadStart : function(file) {
                //$("#file_upload").uploadify("settings", "formData", { "imgType": "other","imgMode":"big") });
            }
        });
		$('#updateWin').window({
	        width : 500,
	        height : 400,
	        modal : true,
	        closed : true,
	        minimizable : false,
	        maximizable : false,
	        collapsible : false,
	        onBeforeClose : function() {
	            clearUpdateForm();
	        },
            onOpen:function() {
            	updateSong.combobox("reload","admin/songcombolist");
            }
	    });
		var song = $('#song').combobox({   
            valueField:'id',   
            textField:'name',
            editable:false
        });
		var updateSong = $('#updateSong').combobox({   
            valueField:'id',   
            textField:'name',
            editable:false
        });
	});
	function submitForm() {
		$('#titlebarform').form('submit', {
			onSubmit : function() {
				return $(this).form('enableValidation').form('validate');
			},
			success : function(data) {
				$.messager.show({
                    msg : "添加成功。",
                    title : '成功'
                });
				$('#win').window('close');
				$("#titlebarlist").datagrid("reload");
			}
		});
	}
	function clearForm() {
		$('#titlebarform').form('reset');
		$('#file_upload').uploadify('cancel', '*');
		$('#img').attr("src","");
	}
	function closeWin() {
		$('#win').window('close');
		$('#img').attr("src","");
	}
	function winOpen() {
		$('#win').window('open');
	}
	function clearUplaoder() {
		$('#file_upload').uploadify('cancel', '*');
		$('#picture').val("");
		$('#img').attr("src","");
	}
	
	function submitUpdateForm() {
        $('#updateform').form('submit', {
            onSubmit : function() {
                return $(this).form('enableValidation').form('validate');
            },
            success : function(data) {
                $.messager.show({msg : "修改成功。",title : '成功'});
                $('#updateWin').window('close');
                $("#titlebarlist").datagrid("reload");
            }
        });
    }
    function clearUpdateForm() {
        $('#updateform').form('reset');
        $('#update_upload').uploadify('cancel', '*');
        $('#updateImg').attr("src","");
    }
    function closeUpdateWin() {
        $('#updateWin').window('close');
        $('#updateImg').attr("src","");
    }
    function winUpdateOpen(row) {
        $('#updateWin').window('open');
        $('#updateform').form("load",row);
        $('#updateImg').attr("src",row.picture);
    }
    function clearUpdateUplaoder() {
        $('#update_upload').uploadify('cancel', '*');
        $('#updatePicture').val("");
        $('#updateImg').attr("src","");
    }
</script>

</head>
<body>
	<table id="titlebarlist"></table>
	<div id="win" iconCls="icon-save" title="标题栏信息">
        <form id="titlebarform" action="admin/addTitleBar" data-options="novalidate:true" method="post" enctype="multipart/form-data">
            <table>
                <tr>
                    <td>名称:</td>
                    <td><input name="name" data-options="required:true" class="f1 easyui-textbox"></input></td>
                </tr>
                <tr>
                    <td>标题对应歌曲:</td>
                    <td>
                        <input class="f1 easyui-combobox" data-options="required:true,editable:false" id="song" name="song">
                    </td>
                </tr>
                <tr>
                    <td>是否启用:</td>
                    <td>
                        <input type="radio" value="true" name="enable" checked="checked"/>启用
                        <input type="radio" value="false" name="enable"/>禁用
                    </td>
                </tr>
                <tr>
                    <td>标题图片:</td>
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
	    <div style="text-align:center;padding:5px">
	    	<a href="javascript:void(0)" icon="icon-ok" class="easyui-linkbutton" onclick="submitForm()">保存</a>
	    	<a href="javascript:void(0)" icon="icon-cancel" class="easyui-linkbutton" onclick="closeWin()">取消</a>
	    </div>
	</div>
	<div id="updateWin" iconCls="icon-edit" title="修改标题栏信息">
        <form id="updateform" action="admin/updateTitleBar" data-options="novalidate:true" method="post" enctype="multipart/form-data">
            <table>
                <tr>
                    <td>名称:</td>
                    <td>
	                    <input type="hidden" name="id"/>
	                    <input name="name" data-options="required:true" class="f1 easyui-textbox"></input>
                    </td>
                </tr>
                <tr>
                    <td>标题对应歌曲:</td>
                    <td>
                        <input class="f1 easyui-combobox" data-options="required:true,editable:false" id="updateSong" name="song">
                    </td>
                </tr>
                <tr>
                    <td>是否启用:</td>
                    <td>
                        <input type="radio" value="true" name="enable" />启用
                        <input type="radio" value="false" name="enable"/>禁用
                    </td>
                </tr>
                <tr>
                    <td>歌曲类别图片:</td>
                    <td><img id="updateImg" name="update_img" class="f1" height="100" src=""/></td>
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
                        <a href="javascript:void(0)" class="easyui-linkbutton"
                                onclick="javascript: $('#update_upload').uploadify('upload', '*')">上传</a>
                        <a href="javascript:void(0)" class="easyui-linkbutton"
                            onclick="javascript:clearUpdateUplaoder()">取消</a>
                    </td>
                </tr>
            </table>
        </form>
        <div style="text-align:center;padding:5px">
            <a href="javascript:void(0)" icon="icon-ok" class="easyui-linkbutton" onclick="submitUpdateForm()">保存</a>
            <a href="javascript:void(0)" icon="icon-cancel" class="easyui-linkbutton" onclick="closeUpdateWin()">取消</a>
        </div>
    </div>
</body>
</html>
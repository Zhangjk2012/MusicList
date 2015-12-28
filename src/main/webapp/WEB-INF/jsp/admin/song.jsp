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
<title>歌曲管理</title>
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
	var rowId;
	$(function(){
		datagrid=$("#songlist").datagrid({
			url:"admin/songlist",//加载的URL
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
	              {field:'songName',title:'歌曲名称',width:50},
	              {field:'songCategoryName',title:'歌曲类型',width:50},
                  {field:'singerName',title:'歌手',width:50},
                  {field:'albumName',title:'专辑',width:50},
                  {field:'voteNum',title:'投票数',width:50},
                  {field:'newSong',title:'是否热门推荐',width:50,formatter:function(val,row){
                      if (val == undefined) {
                          return "";
                      }
                      var text;
                      if (val === true) {
                          text = '是';
                      } else {
                          text = '否';
                      }
                      return text;
                  }},
                  {field:'songFlag',title:'MP3/MP4',width:50,formatter:function(val,row){
                      if (val == undefined) {
                          return "";
                      }
                      var text;
                      if (val === true) {
                          text = 'MP3';
                      } else {
                          text = 'MP4';
                      }
                      return text;
                  }},
	              {field:'briefIntroduction',title:'歌曲简介',width:50,formatter:function(val,row){
	            	  if (val == undefined ||val == "") {
                          return "";
                      }
	            	  var text = "";
	            	  if (val.length > 20) {
	            		  text = val.substr(0,20)+"...";
	            	  } else {
	            		  text = val;
	            	  }
	            	  var content = '<span title="' + val + '" class="note">' + text + '</span>';
                         return content;
                     }},
                     {field:'picture',title:'歌曲图片',width:50,formatter:function(val,row){
                    	 if (val == undefined ||val == "") {
                             return "";
                         }
                   	  return '<img width=30 height=30 src="'+row.picture+'" alt="歌曲图片" />';
                     }},
	          ]],
	          onLoadSuccess : function(data) {
					$(".note").tooltip({
						onShow : function() {
							$(this).tooltip('tip').css({
								width : '300',
								boxShadow : '1px 1px 3px #292929'
							});
						}
					});
			   },
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
											url : 'admin/deleteSong',
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
                            	rowId = row;
                            	winUpdateOpen(row);
                            }
						}
			} ]
		});
		var p = $('#songlist').datagrid('getPager'); 
		$(p).pagination({ 
	        pageSize: 20,//每页显示的记录条数，默认为10 
	        pageList: [20],//可以设置每页记录条数的列表 
	        beforePageText: '第',//页数文本框前显示的汉字 
	        afterPageText: '页    共 {pages} 页', 
	        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
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
			'removeCompleted' : true, //是否完成后移除序列，默认为true
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
            'removeCompleted' : true, //是否完成后移除序列，默认为true
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
        $('#song_upload').uploadify({
            'swf' : 'static/uploadify/uploadify.swf', //FLash文件路径
            'buttonText' : '浏  览', //按钮文本
            'uploader' : 'file/uploadSong', //处理文件上传Action
            'queueID' : 'songQueue', //队列的ID
            folder : 'uploader',
            'queueSizeLimit' : 1, //队列最多可上传文件数量，默认为999
            'auto' : false, //选择文件后是否自动上传，默认为true
            'method' : "post",
            'multi' : false, //是否为多选，默认为true
            'removeCompleted' : true, //是否完成后移除序列，默认为true
            'fileSizeLimit' : '20MB', //单个文件大小，0为无限制，可接受KB,MB,GB等单位的字符串值
            'fileTypeDesc' : 'Image Files', //文件描述
            'fileTypeExts' : '*.mp3; *.mp4', //上传的文件后缀过滤器
            onUploadSuccess : function(file, data, response) { //一个文件上传成功后的响应事件处理
            	var obj = eval ("(" + data + ")");
                if (obj.result === "true") {
	                $('#songFlag').val(obj.flag);
	                $('#songPath').val(obj.path);
	                if (obj.songName != "" && obj.songName != undefined) {
	                	$('#songName').textbox("setValue",obj.songName);
	                	$('#songName').textbox("readonly",true);
	                } else {
	                	$('#songName').textbox("setValue",file.name);
	                }
	                if (obj.trackLength != "" && obj.trackLength != undefined) {
                        $('#trackLength').textbox("setValue",obj.trackLength);
                        $('#trackLength').textbox("readonly",true);
                    }
	                if (obj.lyric != "" && obj.lyric != undefined) {
                        $('#lyric').textbox("setValue",obj.lyric);
                        $('#lyric').textbox("readonly",true);
                    }
                } else {
                	$.messager.alert("失败", obj.info); 
                }
            },
            onUploadStart : function(file) {
                //$("#file_upload").uploadify("settings", "formData", { "imgType": "other","imgMode":"big") });
            }
        });
        
        $('#win').window({
            width : 650,
            height : 600,
            modal : true,
            closed : true,
            resizable:false,
            minimizable : false,
            maximizable : false,
            collapsible : false,
            onBeforeClose : function() {
                clearForm();
            },
            onOpen:function() {
            	singer.combobox("reload","admin/singercombolist");
            	songCategory.combobox("reload","admin/songcategorycombolist");
            }
        });
        
		$('#updateWin').window({
	        width : 650,
	        height : 600,
	        modal : true,
	        closed : true,
	        minimizable : false,
	        maximizable : false,
	        collapsible : false,
	        onBeforeClose : function() {
	            clearUpdateForm();
	        },
            onOpen:function() {
            	updateSinger.combobox("reload","admin/singercombolist");
            	updateSongCategory.combobox("reload","admin/songcategorycombolist");
            }
	    });
		
		var singer = $('#singer').combobox({   
		    valueField:'id',   
		    textField:'name',
		    editable:false,
		    onChange:function(newValue, oldValue){
	            $.get('admin/albumcombolist',{singer:newValue},function(data){
	            	album.combobox("clear").combobox('loadData',data);
	            },'json');
	        }
		});
		
		var songCategory = $('#songCategory').combobox({   
            valueField:'id',   
            textField:'name',
            editable:false
        });
		var album = $('#album').combobox({   
            valueField:'id',   
            textField:'name',
            editable:false
        });
		
		var updateSinger = $('#updateSinger').combobox({   
            valueField:'id',   
            textField:'name',
            editable:false,
            onChange:function(newValue, oldValue){
                $.get('admin/albumcombolist',{singer:newValue},function(data){
                	updateAlbum.combobox("clear").combobox('loadData',data);
                	updateAlbum.combobox("select",rowId.album);
                },'json');
            }
        });
        
        var updateSongCategory = $('#updateSongCategory').combobox({   
            valueField:'id',   
            textField:'name',
            editable:false
        });
        var updateAlbum = $('#updateAlbum').combobox({   
            valueField:'id',   
            textField:'name',
            editable:false
        });
		
	});
	function submitForm() {
		$('#songform').form('submit', {
			onSubmit : function() {
				return $(this).form('enableValidation').form('validate');
			},
			success : function(data) {
				$.messager.show({
                    msg : "添加成功。",
                    title : '成功'
                });
				$('#win').window('close');
				$("#songlist").datagrid("reload");
			}
		});
	}
	function clearForm() {
		$('#songform').form('clear');
		$('#file_upload').uploadify('cancel', '*');
		$('#song_upload').uploadify('cancel', '*');
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
	
	function clearSongUplaoder() {
        $('#song_upload').uploadify('cancel', '*');
        $('#songPath').val("");
        $('#songFlag').val("");
        $('#songName').textbox("clear");
        $('#songName').textbox("readonly",false);
        $('#trackLength').textbox("clear");
        $('#trackLength').textbox("readonly",false);
        $('#lyric').textbox("clear");
        $('#lyric').textbox("readonly");
    }
	
	function submitUpdateForm() {
        $('#updateform').form('submit', {
            onSubmit : function() {
                return $(this).form('enableValidation').form('validate');
            },
            success : function(data) {
                $.messager.show({msg : "修改成功。",title : '成功'});
                $('#updateWin').window('close');
                $("#songlist").datagrid("reload");
            }
        });
    }
    function clearUpdateForm() {
        $('#updateform').form('clear');
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
	<table id="songlist"></table>
	<div id="win" iconCls="icon-save" title="歌曲信息">
        <form id="songform" action="admin/addSong" data-options="novalidate:true" method="post" enctype="multipart/form-data">
            <table>
                <tr>
                    <td colspan="4">
                        <div id="songQueue"></div>
                    </td>
                </tr>
                <tr>
                    <td>歌曲文件:</td>
                    <td>
                        <div>
                            <input type="hidden" id="songPath" name="songPath" />
                            <input type="hidden" id="songFlag" name="songFlag" />
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
                    <td>文件名称:</td>
                    <td>
                        <input id="songName" class="f1 easyui-textbox" data-options="required:true" name="songName"></input>
                    </td>
                    <td>时长:</td>
                    <td>
                        <input id="trackLength" name="trackLength" class="f1 easyui-textbox"></input>
                    </td>
                </tr>
                <tr>
                    <td>歌手:</td>
                    <td>
                        <input class="f1 easyui-combobox" data-options="required:true" id="singer" name="singer">
                    </td>
                    <td>专辑:</td>
                    <td>
                        <input class="f1 easyui-combobox" data-options="required:true" id="album" name="album">
                    </td>
                </tr>
                <tr>
                    <td>类型:</td>
                    <td>
                        <input class="f1 easyui-combobox" data-options="required:true,editable:false" id="songCategory" name="songCategory">
                    </td>
                    <td>初始投票数:</td>
                    <td>
                        <input class="f1 easyui-numberbox" name="voteNum" value="0"></input>
                    </td>
                </tr>
                <tr>
                    <td>简介:</td>
                    <td colspan="3">
                        <input name="briefIntroduction" data-options="multiline:true" style="height:60px;width:490px;" class="easyui-textbox"></input>
                    </td>
                </tr>
                <tr>
                    <td>歌词:</td>
                    <td colspan="3">
                        <input name="lyric" id="lyric" data-options="multiline:true" style="height:120px;width:490px;" class="easyui-textbox"></input>
                    </td>
                </tr>
                <tr>
                    <td>设置为热门推荐:</td>
                    <td>
                        <input type="radio" value="true" name="newSong" checked="checked"/>是
                        <input type="radio" value="false" name="newSong"/>否
                    </td>
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
                            <input type="hidden" id="picture" name="picture" />
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
                    <td>歌曲图片:</td>
                    <td><img id="img" class="f1" height="100" src=""/></td>
                </tr>
            </table>
        </form>
	    <div style="text-align:center;padding:5px">
	    	<a href="javascript:void(0)" icon="icon-ok" class="easyui-linkbutton" onclick="submitForm()">保存</a>
	    	<a href="javascript:void(0)" icon="icon-cancel" class="easyui-linkbutton" onclick="closeWin()">取消</a>
	    </div>
	</div>
	<div id="updateWin" iconCls="icon-edit" title="修改歌曲信息">
        <form id="updateform" action="admin/updateSong" data-options="novalidate:true" method="post" enctype="multipart/form-data">
            <table>
                <tr>
                    <td>文件名称:</td>
                    <td>
                        <input type="hidden" name="id">
                        <input type="hidden" id="songPath" name="songPath" />
                        <input type="hidden" id="songFlag" name="songFlag" />
                        <input id="updateName" class="f1 easyui-textbox" data-options="required:true,editable:false" name="songName"></input>
                    </td>
                    <td>时长:</td>
                    <td>
                        <input id="updateTrackLength" data-options="editable:false" name="trackLength" class="f1 easyui-textbox"></input>
                    </td>
                </tr>
                <tr>
                    <td>歌手:</td>
                    <td>
                        <input class="f1 easyui-combobox" data-options="required:true,editable:false" id="updateSinger" name="singer">
                    </td>
                    <td>专辑:</td>
                    <td>
                        <input class="f1 easyui-combobox" data-options="required:true,editable:false" id="updateAlbum" name="album">
                    </td>
                </tr>
                <tr>
                    <td>类型:</td>
                    <td>
                        <input class="f1 easyui-combobox" data-options="required:true,editable:false" id="updateSongCategory" name="songCategory">
                    </td>
                    <td>初始投票数:</td>
                    <td>
                        <input class="easyui-numberbox" name="voteNum" value="0"></input>
                    </td>
                </tr>
                <tr>
                    <td>简介:</td>
                    <td colspan="3">
                        <input name="briefIntroduction" data-options="multiline:true" style="height:60px;width:490px;" class="easyui-textbox"></input>
                    </td>
                </tr>
                <tr>
                    <td>歌词:</td>
                    <td colspan="3">
                        <input name="lyric" id="lyric" data-options="multiline:true" style="height:120px;width:490px;" class="easyui-textbox"></input>
                    </td>
                </tr>
                <tr>
                    <td>设置为热门推荐:</td>
                    <td>
                        <input type="radio" value="true" name="newSong"/>是
                        <input type="radio" value="false" name="newSong"/>否
                    </td>
                </tr>
                <tr>
                    <td colspan="4">
                       <div id="updateQueue"></div> 
                    </td>
                </tr>
                <tr>
                    <td>上传图片:</td>
                    <td>
						<div>
                            <input type="hidden" id="updatePicture" name="picture" />
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
                <tr>
                    <td>歌曲图片:</td>
                    <td><img id="updateImg" class="f1" height="100" src=""/></td>
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
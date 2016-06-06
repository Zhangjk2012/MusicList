/**
 *	榜单管理 
 */
var datagrid;
var songListSelect;
$(function(){
	datagrid=$("#songlistlist").datagrid({
		url:"admin/songlistlist",//加载的URL
		pageSize: 20,
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
              {field:'name',title:'榜单名称',width:100},
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
              {field:'picture',title:'类别图片',width:100,formatter:function(val,row){
             	  if (val == undefined ||val == "") {
                      return "";
                  }
            	  return '<img width=30 height=30 src="'+row.picture+'" alt="类别图片" />';
              }}
          ]],
          onClickRow: function(index,row) {
 			 showSongs(row.id);
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
										url : 'admin/deleteSongList',
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
		},'-',{
			text : "添加榜单歌曲",
			iconCls : "icon-add",
			handler : function() {
				var row = datagrid.datagrid('getSelected');
                if (row <= 0) {
                    $.messager.alert('警告', '您没有选择','error');
                } else {
                	songListSelect = row.id;
                	$('#songListWin').window("open");
                }
			}
	} ]
	});
	var p = $('#songlistlist').datagrid('getPager'); 
	$(p).pagination({ 
        pageSize: 20,//每页显示的记录条数，默认为10 
        pageList: [20],//可以设置每页记录条数的列表 
        beforePageText: '第',//页数文本框前显示的汉字 
        afterPageText: '页    共 {pages} 页', 
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
    }); 
	
	$("#songlist").datagrid({
	    isField:"id",
	    title:'专辑所有音乐',
		pagination:false,//显示分页
		rownumbers:true,
		loadMsg:'Loading...',
		fit:true,//自动补全
		fitColumns:true,
		singleSelect:true,
		frozenColumns:[[ 
            {field:'ck',checkbox:true} 
        ]], 
		columns:[[      //每个列具体内容
		      {field:'id',title:'id',hidden:true},    
		      {field:'songListId',title:'songListId',hidden:true},    
		      {field:'songOrder',title:'歌曲排序',width:50,editor:{type:'numberbox',options:{ "min": 1,"max":10}}},
              {field:'songName',title:'歌曲名称',width:50},
              {field:'singerName',title:'歌手',width:50},
              {field:'albumName',title:'专辑',width:50},
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
                 	}
                 },
          ]],
          onDblClickRow: onDblClickRow,
          onEndEdit: onEndEdit,
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
				text : "删除音乐",
				iconCls : "icon-remove",
				handler : function() {
					var row = $("#songlist").datagrid('getSelected');
					if (row <= 0) {
						$.messager.alert('警告', '您没有选择','error');
					} else {
						$.messager.confirm('确定','您确定要删除吗',
						function(t) {
							if (t) {
								if (editIndex != undefined){
						            $('#songlist').datagrid('cancelEdit', editIndex);
						            editIndex = undefined;
								}
								$.ajax({
									url : 'admin/deleteSongListSong',
									data : {songId:row.id},
									method:'post',
									dataType : 'json',
									success : function(r) {
										if (r.success) {
											$.messager.show({msg : "删除成功。",title : '成功'});
											$("#songlist").datagrid('reload');
										} else {
											$.messager.alert('错误',"删除失败。",'error');
										}
									}
								});
							}
						})
					}
				}
			},"-",{
				text:'保存编辑',
				iconCls : "icon-save",
				handler : accept,
			},"-",{
				text:'取消编辑',
				iconCls : "icon-undo",
				handler : reject,
			},"-",{
				text:'提交保存',
				iconCls : "icon-undo",
				handler : submitOrder,
			}]
	});
	
	$("#selectsonglist").datagrid({
		pageSize: 50,
	    isField:"id",
		pagination:true,//显示分页
		rownumbers:true,
		loadMsg:'Loading...',
		fit:true,//自动补全
		fitColumns:true,
		singleSelect:false,
		toolbar:"#tb",
		frozenColumns:[[ 
            {field:'ck',checkbox:true} 
        ]], 
		columns:[[      //每个列具体内容
              {field:'songName',title:'歌曲名称',width:50},
              {field:'singerName',title:'歌手',width:50},
              {field:'albumName',title:'专辑',width:50},
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
              }}
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
		   }
	});
	
	$('#songListWin').window({
	    width : 800,
	    height : 500,
	    modal : true,
	    closed : true,
	    resizable:false,
	    minimizable : false,
	    maximizable : false,
	    collapsible : false,
	    footer:"#songListWinFooter",
	    onBeforeClose : function() {
	        
	    },
	    onOpen:function() {
	    	$("#selectsonglist").datagrid({
	    		url:"admin/getselectsonglistsongs"
	    	}).datagrid('getPager').pagination({ 
	            pageSize: 50,//每页显示的记录条数，默认为10 
	            pageList: [20,50,100],//可以设置每页记录条数的列表 
	            beforePageText: '第',//页数文本框前显示的汉字 
	            afterPageText: '页    共 {pages} 页', 
	            displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	        });
	    }
	});
	
	$('#win').window({
		width : 500,
		height : 300,
		modal : true,
		closed : true,
		minimizable : false,
		maximizable : false,
		collapsible : false,
		footer:'#addFooter',
		onBeforeClose : function() {
			clearForm();
		}
	});
	createImgUpload("file_upload", "fileQueue", function(file,data){
		$('#picture').val(data);
	    $('#img').attr("src",data);
	});
	
	createImgUpload("update_upload", "updateQueue", function(file,data){
		$('#updatePicture').val(data);
        $('#updateImg').attr("src",data);
	});
	
	$('#updateWin').window({
        width : 500,
        height : 300,
        modal : true,
        closed : true,
        minimizable : false,
        maximizable : false,
        collapsible : false,
        footer:'#updateFooter',
        onBeforeClose : function() {
            clearUpdateForm();
        }
    });
});
function submitForm() {
	$('#songlistform').form('submit', {
		onSubmit : function() {
			return $(this).form('enableValidation').form('validate');
		},
		success : function(data) {
			$.messager.show({
                msg : "添加成功。",
                title : '成功'
            });
			$('#win').window('close');
			$("#songlistlist").datagrid("reload");
		}
	});
}
function clearForm() {
	$('#songlistform').form('reset');
	clearUploadFile("file_upload");
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
	clearUploadFile("file_upload");
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
            $("#songlistlist").datagrid("reload");
        }
    });
}
function clearUpdateForm() {
    $('#updateform').form('reset');
    clearUploadFile("update_upload");
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
	clearUploadFile("update_upload");
    $('#updatePicture').val("");
    $('#updateImg').attr("src","");
}

function showSongs(songListId){
	$("#songlist").datagrid({
		url:"admin/getSongListSongs",
		queryParams:{
			songListId:songListId
		}
	});
}

/** 查询歌曲 */
function searchSongs(){
	$("#selectsonglist").datagrid({
		queryParams: {"songName":$("#songName").val()}
	});
}

function addSongsOK() {
	var rows = $("#selectsonglist").datagrid('getSelections');
	if (rows<=0) {
		$("#songName").textbox("clear");
		$('#songListWin').window("close");
	} else {
		var ids = new Array();
		for(var i=0; i<rows.length; i++){
			ids.push(rows[i].id);
		}
		$.ajax({
			url : 'admin/addSongs2SongList',// 跳转到 action  
			data : {
				songsId : ids,
				songListId : songListSelect
			},
			type : 'post',
			cache : false,
			dataType : 'json',
			success : function(data) {
				if (data === true) {
					$.messager.show({msg : "添加成功。",title : '成功'});
					$("#songlist").datagrid("reload");
				} else {
					$.messager.show({msg : "添加失败。",title : '失败'});
				}
			},
			error : function() {
				alert("异常！");
			}
		});
	}
	$("#songName").textbox("clear");
	$('#songListWin').window("close");
}

function cancelAddSong() {
	$("#songName").textbox("clear");
	$('#songListWin').window("close");
}

var editIndex = undefined;
function endEditing(){
    if (editIndex == undefined){return true}
    if ($('#songlist').datagrid('validateRow', editIndex)){
        $('#songlist').datagrid('endEdit', editIndex);
        editIndex = undefined;
        return true;
    } else {
        return false;
    }
}

function onDblClickRow(index, row){
    if (editIndex != index){
        if (endEditing()){
            $('#songlist').datagrid('selectRow', index).datagrid('beginEdit', index);
            var ed = $('#songlist').datagrid('getEditor', {index:index,field:"songOrder"});
            if (ed){
                ($(ed.target).data('textbox') ? $(ed.target).textbox('textbox') : $(ed.target)).focus();
            }
            editIndex = index;
        } else {
            setTimeout(function(){
                $('#songlist').datagrid('selectRow', editIndex);
            },0);
        }
    }
}

function onEndEdit(index, row){
    var ed = $("#songlist").datagrid('getEditor', {
        index: index,
        field: 'songOrder'
    });
    row.songOrder = $(ed.target).numberbox('getValue');
}

function accept(){
    if (endEditing()){
        $("#songlist").datagrid('acceptChanges');
    }
}

function reject(){
    $("#songlist").datagrid('rejectChanges');
    editIndex = undefined;
}

function submitOrder() {
	if (endEditing()){
		var rows = $('#songlist').datagrid('getRows');
		var result={};
		$(rows).each(function(index,data){
			if (data.songOrder != "") {
				result[data.songListId]=data.songOrder;
			}
		});
		$.ajax({
			url : 'admin/listSongOrder',// 跳转到 action  
			data : result,
			type : 'post',
			cache : false,
			dataType : 'json',
			success : function(data) {
				if (data.success === true) {
					$.messager.show({msg : "修改成功。",title : '成功'});
					$("#songlist").datagrid("reload");
				} else {
					$.messager.show({msg : "修改失败。",title : '失败'});
				}
			},
			error : function() {
				alert("异常！");
			}
		});
    }
}

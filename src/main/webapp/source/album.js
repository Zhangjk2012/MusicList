var datagrid;
var songlistgrid;
var selectSongListGrid;
var albumSelect;
$(function(){
	datagrid=$("#albumlist").datagrid({
		url:"admin/albumlist",//加载的URL
	    isField:"id",
		pagination:true,//显示分页
		rownumbers:true,
		title:'音乐专辑',
		fit:true,//自动补全
		fitColumns:true,
		loadMsg:'Loading...',
		singleSelect:true,
		frozenColumns:[[ 
            {field:'ck',checkbox:true} 
        ]], 
		columns:[[      //每个列具体内容
              {field:'id',title:'id',hidden:true},   
              {field:'name',title:'专辑名称',width:50},
              {field:'singer',title:'singer',hidden:true},
              {field:'singerName',title:'歌手',width:50},
              {field:'publishTime',title:'发行日期',width:50},
              {field:'publishCompany',title:'发行公司',width:50},
              {field:'briefIntroduction',title:'专辑简介',width:50,formatter:function(val,row){
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
              {field:'picture',title:'专辑图片',width:50,formatter:function(val,row){
            	 if (val == undefined ||val == "") {
                     return "";
                 }
           	     return '<img width=30 height=30 src="'+row.picture+'" alt="专辑图片" />';
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
		   onClickRow: function(index,row) {
			 showSongs(row.id);
		   },
		   toolbar : [ //工具条
				{
					text : "增加专辑",
					iconCls : "icon-add",
					handler : function() {//回调函数
						winOpen();
					}
				},
				'-',
				{
					text : "删除专辑",
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
										url : 'admin/deleteAlbum',
										data : row,
										method:'post',
										dataType : 'json',
										success : function(r) {
											if (r.success) {
												$.messager.show({msg : "删除成功。",title : '成功'});
												datagrid.datagrid('reload');
												songlistgrid.datagrid('loadData',{total:0,rows:[]});
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
					text : "修改专辑",
					iconCls : "icon-edit",
					handler : function() {
						var row = datagrid.datagrid('getSelected');
                        if (row <= 0) {
                            $.messager.alert('警告', '您没有选择','error');
                        } else {
                        	winUpdateOpen(row);
                        }
					}
		} ,'-',{
			text : "添加音乐",
			iconCls : "icon-add",
			handler : function() {
				var row = datagrid.datagrid('getSelected');
                if (row <= 0) {
                    $.messager.alert('警告', '您没有选择专辑！','error');
                } else {
                	albumSelect = row.id;
                	$('#songListWin').window("open");
                }
			}
		} ]
	});
	var p = $('#albumlist').datagrid('getPager'); 
	$(p).pagination({ 
        pageSize: 20,//每页显示的记录条数，默认为10 
        pageList: [20],//可以设置每页记录条数的列表 
        beforePageText: '第',//页数文本框前显示的汉字 
        afterPageText: '页    共 {pages} 页', 
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
    });
	
	songlistgrid=$("#songlist").datagrid({
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
				text : "删除",
				iconCls : "icon-remove",
				handler : function() {
					var row = songlistgrid.datagrid('getSelected');
					if (row <= 0) {
						$.messager.alert('警告', '您没有选择','error');
					} else {
						$.messager.confirm('确定','您确定要删除吗',
						function(t) {
							if (t) {
								$.ajax({
									url : 'admin/deleteSelectSongs',
									data : {songId:row.id},
									method:'post',
									dataType : 'json',
									success : function(r) {
										if (r.success) {
											$.messager.show({msg : "删除成功。",title : '成功'});
											songlistgrid.datagrid('reload');
										} else {
											$.messager.alert('错误',"删除失败。",'error');
										}
									}
								});
							}
						})
					}
				}
			}]
	});
	
	selectSongListGrid=$("#selectsonglist").datagrid({
		//url:"admin/songlist",//加载的URL
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
	    height : 600,
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
	    	selectSongListGrid.datagrid({
	    		url:"admin/getselectsonglist"
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
	    height : 500,
	    modal : true,
	    closed : true,
	    resizable:false,
	    minimizable : false,
	    maximizable : false,
	    collapsible : false,
	    footer:'#addFooter',
	    onBeforeClose : function() {
	        clearForm();
	    },
	    onOpen:function() {
	        singer.combobox("reload","admin/singercombolist");
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
	    height : 500,
	    modal : true,
	    closed : true,
	    minimizable : false,
	    maximizable : false,
	    collapsible : false,
	    footer:'#updateFooter',
	    onBeforeClose : function() {
	        clearUpdateForm();
	    },
	    onOpen:function() {
	        updateSinger.combobox("reload","admin/singercombolist");
	    }
	});

	var singer = $('#singer').combobox({   
	    valueField:'id',   
	    textField:'name',
	    editable:false
	});

	var updateSinger = $('#updateSinger').combobox({   
	    valueField:'id',   
	    textField:'name',
	    editable:false
	});
});


function submitForm() {
	$('#albumform').form('submit', {
	    onSubmit : function() {
	        return $(this).form('enableValidation').form('validate');
	    },
	    success : function(data) {
	        $.messager.show({
	            msg : "添加成功。",
	            title : '成功'
	        });
	        $('#win').window('close');
	        $("#albumlist").datagrid("reload");
	    }
	});
}

function clearForm() {
	$('#albumform').form('reset');
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
	        $("#albumlist").datagrid("reload");
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

/** 查询歌曲 */
function searchSongs(){
	selectSongListGrid.datagrid({
		queryParams: {"songName":$("#songName").val()}
	});
}

function addSongsOK() {
	var rows = selectSongListGrid.datagrid('getSelections');
	if (rows<=0) {
		$("#songName").textbox("clear");
		$('#songListWin').window("close");
	} else {
		var ids = new Array();
		for(var i=0; i<rows.length; i++){
			ids.push(rows[i].id);
		}
		$.ajax({
			url : 'admin/addSongs2Album',// 跳转到 action  
			data : {
				songsId : ids,
				albumId : albumSelect
			},
			type : 'post',
			cache : false,
			dataType : 'json',
			success : function(data) {
				if (data === true) {
					$.messager.show({msg : "添加成功。",title : '成功'});
					songlistgrid.datagrid("reload");
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

function showSongs(albumId){
	songlistgrid.datagrid({
		url:"admin/selectSongs",
		queryParams:{
			albumId:albumId
		}
	});
}

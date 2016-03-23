var datagrid;
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
					text : "增加",
					iconCls : "icon-add",
					handler : function() {//回调函数
						$('#win').window('open');
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
	
	createMp3Upload("song_upload","songQueue",function(file,data){
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
            $('#singer').textbox("setValue",obj.singerName);
            $('#album').textbox("setValue",obj.album);
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
	});
	createImgUpload("file_upload", "fileQueue", function(file,data){
		$('#picture').val(data);
        $('#img').attr("src",data);
	});
	createImgUpload("update_upload", "updateQueue", function(file,data){
		$('#updatePicture').val(data);
        $('#updateImg').attr("src",data);
	});
	$('#win').window({
        width : 650,
        height : 650,
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
        }
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
	$('#songform').form('reset');
	$('#file_upload').uploadify('cancel', '*');
	$('#song_upload').uploadify('cancel', '*');
	$('#img').attr("src","");
	// 清空富文本里面的内容
	KindEditor.instances[0].html("");
}

function closeWin() {
	$('#win').window('close');
	$('#img').attr("src","");
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
    $('#lyric').textbox("readonly",false);
    $('#singer').textbox("clear");
    $('#album').textbox("clear");
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
    $('#updateform').form('reset');
    $('#update_upload').uploadify('cancel', '*');
    $('#updateImg').attr("src","");
    KindEditor.instances[1].html("");
}

function closeUpdateWin() {
    $('#updateWin').window('close');
    $('#updateImg').attr("src","");
}

function winUpdateOpen(row) {
    $('#updateWin').window('open');
    $('#updateform').form("load",row);
    KindEditor.instances[1].html(row.lyric);
    $('#updateImg').attr("src",row.picture);
}

function clearUpdateUplaoder() {
    $('#update_upload').uploadify('cancel', '*');
    $('#updatePicture').val("");
    $('#updateImg').attr("src","");
}

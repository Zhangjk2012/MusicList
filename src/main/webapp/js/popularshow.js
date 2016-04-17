var datagrid;
$(function(){
	datagrid=$("#showlist").datagrid({
		url:"admin/showtalklist?type=0",//加载的URL
	    isField:"id",
		pagination:true,//显示分页
		rownumbers:true,
		fit:true,//自动补全
		fitColumns:true,
		loadMsg:'loading...',
		singleSelect:true,
		frozenColumns:[[ 
            {field:'ck',checkbox:true} 
        ]], 
		columns:[[      //每个列具体内容
              {field:'name',title:'节目名称',width:50},
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
              }}
          ]],
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
										url : 'admin/deleteTalkShow',
										data : {id:row.id},
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
	var p = $('#showlist').datagrid('getPager'); 
	$(p).pagination({ 
        pageSize: 20,//每页显示的记录条数，默认为10 
        pageList: [20],//可以设置每页记录条数的列表 
        beforePageText: '第',//页数文本框前显示的汉字 
        afterPageText: '页    共 {pages} 页', 
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
    });
	
	createMp34Upload("song_upload","songQueue",function(file,data){
		var obj = eval ("(" + data + ")");
        if (obj.result === "true") {
        	$('#songPath').val(obj.path);
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
        height : 350,
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
        }
    });
	$('#updateWin').window({
        width : 650,
        height : 350,
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
        }
    });
});
	
function submitForm() {
	$('#showform').form('submit', {
		onSubmit : function() {
			return $(this).form('enableValidation').form('validate');
		},
		success : function(data) {
			$.messager.show({
                msg : "添加成功。",
                title : '成功'
            });
			$('#win').window('close');
			$("#showlist").datagrid("reload");
		}
	});
}

function clearForm() {
	$('#showform').form('reset');
	$('#img').attr("src","");
	clearUploadFile("file_upload");
	clearUploadFile("song_upload");
}

function closeWin() {
	$('#img').attr("src","");
	$('#win').window('close');
}

function clearUplaoder() {
	clearUploadFile("file_upload");
}

function clearUplaoder() {
	clearUploadFile("file_upload");
	$('#picture').val("");
	$('#img').attr("src","");
}

function clearSongUplaoder() {
	clearUploadFile("song_upload");
}

function submitUpdateForm() {
    $('#updateform').form('submit', {
        onSubmit : function() {
            return $(this).form('enableValidation').form('validate');
        },
        success : function(data) {
            $.messager.show({msg : "修改成功。",title : '成功'});
            $('#updateWin').window('close');
            $("#showlist").datagrid("reload");
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
    $('#updateImg').attr("src",row.picUrl);
}

function clearUpdateUplaoder() {
	clearUploadFile("update_upload");
    $('#updatePicture').val("");
    $('#updateImg').attr("src","");
}

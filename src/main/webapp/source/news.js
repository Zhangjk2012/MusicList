var editor;
var editor1;
KindEditor.ready(function(K) {
    editor = K.create('#newsContent', {
        resizeType : 0,
        minWidth:450,
        allowPreviewEmoticons : false,
        allowImageUpload : true,
        fileManagerJson : '../asp/file_manager_json.asp',
        uploadJson:'admin/file/uploadImg',
        //,'|', 'emoticons', 'image', 'link'
        afterBlur:function() {
            editor.sync();
        },
        items : [
            'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
            'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
            'insertunorderedlist']
    });
    editor1 = K.create('#updateNewsContent', {
        resizeType : 0,
        minWidth:450,
        allowPreviewEmoticons : false,
        allowImageUpload : false,
        afterBlur:function() {
            editor1.sync();
        },
        items : [
            'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
            'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
            'insertunorderedlist']
    });
});

var datagrid;
$(function(){
	datagrid=$("#newslist").datagrid({
		url:"admin/newslist",//加载的URL
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
              {field:'title',title:'标题',width:100},
              {field:'subtitle',title:'副标题',width:100},
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
										url : 'admin/deletenews',
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
	var p = $('#newslist').datagrid('getPager'); 
	$(p).pagination({ 
        pageSize: 20,//每页显示的记录条数，默认为10 
        pageList: [20],//可以设置每页记录条数的列表 
        beforePageText: '第',//页数文本框前显示的汉字 
        afterPageText: '页    共 {pages} 页', 
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
    }); 

	$('#win').window({
		width : 850,
		height : 650,
		modal : true,
		closed : true,
		minimizable : false,
		maximizable : false,
		collapsible : false,
		footer:"#newsFooter",
		onBeforeClose : function() {
			clearForm();
		},
        onOpen:function() {
        }
	});
	createImgUpload("file_upload", "fileQueue", function(file, data){
		$('#picture').val(data);
	    $('#img').attr("src",data);
	});
	createImgUpload("update_upload", "updateQueue", function(file, data){
		$('#updatePicture').val(data);
        $('#updateImg').attr("src",data);
	});
	
	$('#updateWin').window({
		width : 850,
		height : 650,
        modal : true,
        closed : true,
        minimizable : false,
        maximizable : false,
        collapsible : false,
        footer:"#updateNewsFooter",
        onBeforeClose : function() {
            clearUpdateForm();
        },
        onOpen:function() {
        }
    });
});
function submitForm() {
	$('#newsform').form('submit', {
		onSubmit : function() {
			return $(this).form('enableValidation').form('validate');
		},
		success : function(data) {
			$.messager.show({
                msg : "添加成功。",
                title : '成功'
            });
			$('#win').window('close');
			$("#newslist").datagrid("reload");
		}
	});
}
function clearForm() {
	$('#newsform').form('reset');
	clearUploadFile("file_upload");
	$('#img').attr("src","");
	editor.html("");
}
function closeWin() {
	$('#win').window('close');
	$('#img').attr("src","");
}
function winOpen() {
	$('#win').window('open');
}
function clearUplaoder() {
	clearUploadFile("file_upload")
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
            $("#newslist").datagrid("reload");
        }
    });
}
function clearUpdateForm() {
    $('#updateform').form('reset');
    editor1.html("");
    clearUploadFile("update_upload")
    $('#updateImg').attr("src","");
}
function closeUpdateWin() {
    $('#updateWin').window('close');
    $('#updateImg').attr("src","");
}
function winUpdateOpen(row) {
	editor1.html(row.content);
    $('#updateWin').window('open');
    $('#updateform').form("load",row);
    $('#updateImg').attr("src",row.picture);
}
function clearUpdateUplaoder() {
	clearUploadFile("update_upload")
    $('#updatePicture').val("");
    $('#updateImg').attr("src","");
}
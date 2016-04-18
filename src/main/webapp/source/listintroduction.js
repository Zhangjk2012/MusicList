var editor;
var editor1;
KindEditor.ready(function(K) {
    editor = K.create('#listintroductionContent', {
        resizeType : 0,
        minWidth:450,
        allowPreviewEmoticons : false,
        allowImageUpload : true,
        allowImageRemote : false,
        fileManagerJson : '../asp/file_manager_json.asp',
        uploadJson:'admin/file/uploadKEImg',
        //,'|', 'emoticons', 'image', 'link'
        afterBlur:function() {
            editor.sync();
        },
        items : [
            'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
            'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
            'insertunorderedlist','|', 'image']
    });
    editor1 = K.create('#updatelistintroductionContent', {
        resizeType : 0,
        minWidth:450,
        allowPreviewEmoticons : false,
        allowImageUpload : true,
        allowImageRemote : false,
        uploadJson:'admin/file/uploadKEImg',
        afterBlur:function() {
            editor1.sync();
        },
        items : [
            'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
            'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
            'insertunorderedlist','|', 'image']
    });
});

var datagrid;
$(function(){
    datagrid=$("#listintroductionlist").datagrid({
        url:"admin/listintroductionlist",//加载的URL
        isField:"id",
        pagination:true,//显示分页
        rownumbers:true,
        fit:true,//自动补全
        fitColumns:true,
        iconCls:"icon-save",//图标
        title:"榜单介绍",
        singleSelect:true,
        frozenColumns:[[ 
            {field:'ck',checkbox:true} 
        ]], 
        columns:[[      //每个列具体内容
              {field:'id',title:'id',hidden:true,width:100},   
              {field:'name',title:'榜单名称',width:100}   
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
                                        url : 'admin/deletelistintroduction',
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
    var p = $('#listintroductionlist').datagrid('getPager'); 
    $(p).pagination({ 
        pageSize: 20,//每页显示的记录条数，默认为10 
        pageList: [20],//可以设置每页记录条数的列表 
        beforePageText: '第',//页数文本框前显示的汉字 
        afterPageText: '页    共 {pages} 页', 
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
    }); 

    $('#win').window({
        width : 800,
        height : 650,
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
    
    $('#updateWin').window({
        width : 800,
        height : 650,
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
    $('#listintroductionform').form('submit', {
        onSubmit : function() {
            return $(this).form('enableValidation').form('validate');
        },
        success : function(data) {
            $.messager.show({
                msg : "添加成功。",
                title : '成功'
            });
            $('#win').window('close');
            $("#listintroductionlist").datagrid("reload");
        }
    });
}
function clearForm() {
    $('#listintroductionform').form('reset');
    editor.html("");
}
function closeWin() {
    $('#win').window('close');
}
function winOpen() {
    $('#win').window('open');
}

function submitUpdateForm() {
    $('#updateform').form('submit', {
        onSubmit : function() {
            return $(this).form('enableValidation').form('validate');
        },
        success : function(data) {
            $.messager.show({msg : "修改成功。",title : '成功'});
            $('#updateWin').window('close');
            $("#listintroductionlist").datagrid("reload");
        }
    });
}
function clearUpdateForm() {
    $('#updateform').form('reset');
    editor1.html("");
}
function closeUpdateWin() {
    $('#updateWin').window('close');
}
function winUpdateOpen(row) {
    $('#updateWin').window('open');
    $('#updateform').form("load",row);
    editor1.html(row.content);
}

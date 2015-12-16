<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="static/easyui/themes/bootstrap/easyui.css"/>
<link rel="stylesheet" type="text/css" href="static/easyui/themes/icon.css"/>
<script type="text/javascript" src="static/js/jquery.min.js"></script>
<script type="text/javascript" src="static/easyui/jquery.easyui.min.js"></script>
<title>歌手管理</title>
</head>
<body>
    <table id="singerlist">
    </table>
    <script type="text/javascript">
    var datagrid;  
    var rowEditor=undefined;  
    $(function(){  
        datagrid=$("#singerlist").datagrid({  
            url:"/Test3/ModuleBeanController/getAll.do",//加载的URL  
            isField:"id",  
            pagination:true,//显示分页  
            pageSize:5,//分页大小  
            pageList:[5,10,15,20],//每页的个数  
            fit:true,//自动补全  
            fitColumns:true,  
            rownumbers:true,
            iconCls:"icon-save",//图标  
            title:"歌手管理",  
            columns:[[
                      {field:'id',title:'id', width:100},     
                      {field:'pid',title:'pid',width:100,formatter:function(value,row,index){return '<img src="'+row.image+'" />';}},     
                      {field:'text',title:'text',width:100}     
                  ]],  
                  toolbar:[              //工具条  
                    {text:"增加",iconCls:"icon-add",handler:function(){//回调函数  
                        if(rowEditor==undefined)  
                        {  
                            datagrid.datagrid('insertRow',{//如果处于未被点击状态，在第一行开启编辑  
                                index: 0,     
                                row: {  
                                }  
                            });  
                            rowEditor=0;  
                            datagrid.datagrid('beginEdit',rowEditor);//没有这行，即使开启了也不编辑  
                        }  
                    }},  
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
                    }},  
                    {text:"修改",iconCls:"icon-edit",handler:function(){  
                        var rows=datagrid.datagrid('getSelections');  
                        if(rows.length==1)  
                        {  
                            if(rowEditor==undefined)  
                            {  
                                var index=datagrid.datagrid('getRowIndex',rows[0]);  
                                 rowEditor=index;  
                                datagrid.datagrid('unselectAll');  
                                datagrid.datagrid('beginEdit',index);  
                            }  
                        }  
                    }},  
                    {text:"查询",iconCls:"icon-search",handler:function(){}},  
                    {text:"保存",iconCls:"icon-save",handler:function(){  
                          
                        datagrid.datagrid('endEdit',rowEditor);  
                        rowEditor=undefined;  
                    }},  
                    {text:"取消编辑",iconCls:"icon-redo",handler:function(){  
                        rowEditor=undefined;  
                        datagrid.datagrid('rejectChanges')  
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
            },  
            onDblClickCell:function(rowIndex, field, value){  
                if(rowEditor==undefined)  
                {  
                    datagrid.datagrid('beginEdit',rowIndex);  
                    rowEditor=rowIndex;  
                }  
                  
            }  
        });  
        $("#search").click(function(){  
            datagrid.datagrid('load',{  
                text:$("#text").val()  
            });  
      
        });  
    })
    </script>
</body>
</html>
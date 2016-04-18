var albumlistgrid;var selectAlbumListGrid;$(function(){albumlistgrid=$("#albumlist").datagrid({url:"admin/newalbum/rockalbums",isField:"id",pagination:true,rownumbers:true,title:"摇滚新碟上架",fit:true,fitColumns:true,loadMsg:"Loading...",singleSelect:true,frozenColumns:[[{field:"ck",checkbox:true}]],columns:[[{field:"id",title:"id",hidden:true},{field:"name",title:"专辑名称",width:50},{field:"publishTime",title:"发行日期",width:50},{field:"publishCompany",title:"发行公司",width:50},{field:"briefIntroduction",title:"专辑简介",width:50,formatter:function(E,D){if(E==undefined||E==""){return""}var B="";if(E.length>20){B=E.substr(0,20)+"..."}else{B=E}var C='<span title="'+E+'" class="note">'+B+"</span>";return C}},{field:"picture",title:"专辑图片",width:50,formatter:function(C,B){if(C==undefined||C==""){return""}return'<img width=30 height=30 src="'+B.picture+'" alt="专辑图片" />'}}]],onLoadSuccess:function(B){$(".note").tooltip({onShow:function(){$(this).tooltip("tip").css({width:"300",boxShadow:"1px 1px 3px #292929"})}})},onClickRow:function(C,B){showSongs(B.id)},toolbar:[{text:"设置新碟",iconCls:"icon-add",handler:function(){$("#albumListWin").window("open")}},"-",{text:"删除新碟",iconCls:"icon-remove",handler:function(){var B=albumlistgrid.datagrid("getSelected");if(B<=0){$.messager.alert("警告","您没有选择","error")}else{$.messager.confirm("确定","您确定要删除吗",function(C){if(C){$.ajax({url:"admin/newalbum/removealbum",data:{albumId:B.id},method:"post",dataType:"json",success:function(D){if(D.success){$.messager.show({msg:"删除成功。",title:"成功"});albumlistgrid.datagrid("reload")}else{$.messager.alert("错误","删除失败。","error")}}})}})}}}]});var A=$("#albumlist").datagrid("getPager");$(A).pagination({pageSize:20,pageList:[20],beforePageText:"第",afterPageText:"页    共 {pages} 页",displayMsg:"当前显示 {from} - {to} 条记录   共 {total} 条记录"});selectAlbumListGrid=$("#selectalbumlist").datagrid({isField:"id",pagination:true,rownumbers:true,loadMsg:"Loading...",fit:true,fitColumns:true,singleSelect:false,toolbar:"#tb",frozenColumns:[[{field:"ck",checkbox:true}]],columns:[[{field:"name",title:"专辑名称",width:50},{field:"publishTime",title:"发行日期",width:50},{field:"publishCompany",title:"发行公司",width:50}]],onLoadSuccess:function(B){$(".note").tooltip({onShow:function(){$(this).tooltip("tip").css({width:"300",boxShadow:"1px 1px 3px #292929"})}})}});$("#albumListWin").window({width:800,height:600,modal:true,closed:true,resizable:false,minimizable:false,maximizable:false,collapsible:false,footer:"#albumListWinFooter",onBeforeClose:function(){},onOpen:function(){selectAlbumListGrid.datagrid({url:"admin/newalbum/selectalbums"}).datagrid("getPager").pagination({pageSize:50,pageList:[20,50,100],beforePageText:"第",afterPageText:"页    共 {pages} 页",displayMsg:"当前显示 {from} - {to} 条记录   共 {total} 条记录"})}})});function searchSongs(){selectAlbumListGrid.datagrid({queryParams:{"albumName":$("#albumName").val()}})}function addSongsOK(){var B=selectAlbumListGrid.datagrid("getSelections");if(B<=0){}else{var C=new Array();for(var A=0;A<B.length;A++){C.push(B[A].id)}$.ajax({url:"admin/newalbum/setrockalbums",data:{albumIds:C,},type:"post",cache:false,dataType:"json",success:function(D){if(D===true){$.messager.show({msg:"添加成功。",title:"成功"});albumlistgrid.datagrid("reload")}else{$.messager.show({msg:"添加失败。",title:"失败"})}},error:function(){alert("异常！")}})}$("#albumName").textbox("clear");$("#albumListWin").window("close")}function cancelAddSong(){$("#albumName").textbox("clear");$("#albumListWin").window("close")};
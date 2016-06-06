var songlistgrid;var selectSongListGrid;$(function(){songlistgrid=$("#songlist").datagrid({url:"admin/hotsong/hotsonglist",isField:"id",title:"热门推荐音乐",pagination:true,rownumbers:true,loadMsg:"Loading...",fit:true,fitColumns:true,singleSelect:true,frozenColumns:[[{field:"ck",checkbox:true}]],columns:[[{field:"id",title:"id",hidden:true},{field:"songName",title:"歌曲名称",width:50},{field:"singerName",title:"歌手",width:50},{field:"albumName",title:"专辑",width:50},{field:"songFlag",title:"MP3/MP4",width:50,formatter:function(D,C){if(D==undefined){return""}var B;if(D===true){B="MP3"}else{B="MP4"}return B}},{field:"briefIntroduction",title:"歌曲简介",width:50,formatter:function(E,D){if(E==undefined||E==""){return""}var B="";if(E.length>20){B=E.substr(0,20)+"..."}else{B=E}var C='<span title="'+E+'" class="note">'+B+"</span>";return C}},{field:"picture",title:"歌曲图片",width:50,formatter:function(C,B){if(C==undefined||C==""){return""}return'<img width=30 height=30 src="'+B.picture+'" alt="歌曲图片" />'}},]],onLoadSuccess:function(B){$(".note").tooltip({onShow:function(){$(this).tooltip("tip").css({width:"300",boxShadow:"1px 1px 3px #292929"})}})},toolbar:[{text:"设置音乐",iconCls:"icon-add",handler:function(){$("#songListWin").window("open")}},"-",{text:"删除",iconCls:"icon-remove",handler:function(){var B=songlistgrid.datagrid("getSelected");if(B<=0){$.messager.alert("警告","您没有选择","error")}else{$.messager.confirm("确定","您确定要删除吗",function(C){if(C){$.ajax({url:"admin/hotsong/removeHotSong",data:{songId:B.id},method:"post",dataType:"json",success:function(D){if(D.success){$.messager.show({msg:"删除成功。",title:"成功"});songlistgrid.datagrid("reload")}else{$.messager.alert("错误","删除失败。","error")}}})}})}}}]});var A=songlistgrid.datagrid("getPager");$(A).pagination({pageSize:20,pageList:[20],beforePageText:"第",afterPageText:"页    共 {pages} 页",displayMsg:"当前显示 {from} - {to} 条记录   共 {total} 条记录"});selectSongListGrid=$("#selectsonglist").datagrid({isField:"id",pagination:true,rownumbers:true,loadMsg:"Loading...",fit:true,fitColumns:true,singleSelect:false,toolbar:"#tb",frozenColumns:[[{field:"ck",checkbox:true}]],columns:[[{field:"songName",title:"歌曲名称",width:50},{field:"singerName",title:"歌手",width:50},{field:"albumName",title:"专辑",width:50},{field:"songFlag",title:"MP3/MP4",width:50,formatter:function(D,C){if(D==undefined){return""}var B;if(D===true){B="MP3"}else{B="MP4"}return B}}]],onLoadSuccess:function(B){$(".note").tooltip({onShow:function(){$(this).tooltip("tip").css({width:"300",boxShadow:"1px 1px 3px #292929"})}})}});$("#songListWin").window({width:800,height:500,modal:true,closed:true,resizable:false,minimizable:false,maximizable:false,collapsible:false,footer:"#songListWinFooter",onBeforeClose:function(){},onOpen:function(){selectSongListGrid.datagrid({url:"admin/hotsong/selecthotsongs"}).datagrid("getPager").pagination({pageSize:50,pageList:[20,50,100],beforePageText:"第",afterPageText:"页    共 {pages} 页",displayMsg:"当前显示 {from} - {to} 条记录   共 {total} 条记录"})}})});function searchSongs(){selectSongListGrid.datagrid({queryParams:{"songName":$("#songName").val()}})}function addSongsOK(){var B=selectSongListGrid.datagrid("getSelections");if(B<=0){$("#songName").textbox("clear");$("#songListWin").window("close")}else{var C=new Array();for(var A=0;A<B.length;A++){C.push(B[A].id)}$.ajax({url:"admin/hotsong/setHotSong",data:{songIds:C,},type:"post",cache:false,dataType:"json",success:function(D){if(D.success===true){$.messager.show({msg:"添加成功。",title:"成功"});songlistgrid.datagrid("reload")}else{$.messager.show({msg:"添加失败。",title:"失败"})}},error:function(){alert("异常！")}})}$("#songName").textbox("clear");$("#songListWin").window("close")}function cancelAddSong(){$("#songName").textbox("clear");$("#songListWin").window("close")};
var songlistgrid;
var selectSongListGrid;
$(function(){
	songlistgrid=$("#songlist").datagrid({
		url:'admin/hotsong/hotsonglist',
	    isField:"id",
	    title:'热门推荐音乐',
		pagination:true,//显示分页
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
				text : "设置音乐",
				iconCls : "icon-add",
				handler : function() {//回调函数
					$('#songListWin').window("open");
				}
			},'-',{
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
									url : 'admin/hotsong/removeHotSong',
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
	
	var p = songlistgrid.datagrid('getPager'); 
	$(p).pagination({ 
        pageSize: 20,//每页显示的记录条数，默认为10 
        pageList: [20],//可以设置每页记录条数的列表 
        beforePageText: '第',//页数文本框前显示的汉字 
        afterPageText: '页    共 {pages} 页', 
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
    });
	
	selectSongListGrid=$("#selectsonglist").datagrid({
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
	    		url:"admin/hotsong/selecthotsongs"
	    	}).datagrid('getPager').pagination({ 
	            pageSize: 50,//每页显示的记录条数，默认为10 
	            pageList: [20,50,100],//可以设置每页记录条数的列表 
	            beforePageText: '第',//页数文本框前显示的汉字 
	            afterPageText: '页    共 {pages} 页', 
	            displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	        });
	    }
	});
	
});

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
			url : 'admin/hotsong/setHotSong',// 跳转到 action  
			data : {
				songIds : ids,
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

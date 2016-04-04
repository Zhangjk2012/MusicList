var albumlistgrid;
var selectAlbumListGrid;
$(function(){
	albumlistgrid=$("#albumlist").datagrid({
		url:"admin/newalbum/popularalbums",
		isField:"id",
		pagination:true,//显示分页
		rownumbers:true,
		title:'流行新碟上架',
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
		   },
		   onClickRow: function(index,row) {
			 showSongs(row.id);
		   },
		   toolbar : [ //工具条
				{
					text : "设置新碟",
					iconCls : "icon-add",
					handler : function() {//回调函数
						$('#albumListWin').window("open");
					}
				},
				'-',
				{
					text : "删除新碟",
					iconCls : "icon-remove",
					handler : function() {
						var row = albumlistgrid.datagrid('getSelected');
						if (row <= 0) {
							$.messager.alert('警告', '您没有选择','error');
						} else {
							$.messager.confirm('确定','您确定要删除吗',
							function(t) {
								if (t) {
									$.ajax({
										url : 'admin/newalbum/removealbum',
										data : {albumId:row.id},
										method:'post',
										dataType : 'json',
										success : function(r) {
											if (r.success) {
												$.messager.show({msg : "删除成功。",title : '成功'});
												albumlistgrid.datagrid('reload');
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
	var p = $('#albumlist').datagrid('getPager'); 
	$(p).pagination({ 
        pageSize: 20,//每页显示的记录条数，默认为10 
        pageList: [20],//可以设置每页记录条数的列表 
        beforePageText: '第',//页数文本框前显示的汉字 
        afterPageText: '页    共 {pages} 页', 
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
    });
	
	selectAlbumListGrid=$("#selectalbumlist").datagrid({
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
            {field:'name',title:'专辑名称',width:50},
            {field:'publishTime',title:'发行日期',width:50},
            {field:'publishCompany',title:'发行公司',width:50}
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
	
	$('#albumListWin').window({
	    width : 800,
	    height : 600,
	    modal : true,
	    closed : true,
	    resizable:false,
	    minimizable : false,
	    maximizable : false,
	    collapsible : false,
	    footer:"#albumListWinFooter",
	    onBeforeClose : function() {
	        
	    },
	    onOpen:function() {
	    	selectAlbumListGrid.datagrid({
	    		url:"admin/newalbum/selectalbums"
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
	selectAlbumListGrid.datagrid({
		queryParams: {"albumName":$("#albumName").val()}
	});
}

function addSongsOK() {
	var rows = selectAlbumListGrid.datagrid('getSelections');
	if (rows<=0) {
	} else {
		var ids = new Array();
		for(var i=0; i<rows.length; i++){
			ids.push(rows[i].id);
		}
		$.ajax({
			url : 'admin/newalbum/setpopularalbums',// 跳转到 action  
			data : {
				albumIds : ids,
			},
			type : 'post',
			cache : false,
			dataType : 'json',
			success : function(data) {
				if (data === true) {
					$.messager.show({msg : "添加成功。",title : '成功'});
					albumlistgrid.datagrid("reload");
				} else {
					$.messager.show({msg : "添加失败。",title : '失败'});
				}
			},
			error : function() {
				alert("异常！");
			}
		});
	}
	$("#albumName").textbox("clear");
	$('#albumListWin').window("close");
}

function cancelAddSong() {
	$("#albumName").textbox("clear");
	$('#albumListWin').window("close");
}

//榜单
var pageIndex = 1;
var pageSize = 15;
$(function(){
	$.ajax({  
        url:'getCategory',
        type:'post',  
        cache:false,  
        dataType:'json',  
        success:function(data) {
            if(data.msg =="true"){  
                var d = data.data;
                var len = d.length;
                if (d.length == 0) {
                } else {
                	var selected = $("#categoryId").val();
                    $.each(d,function(n,value) {
                    	var html = '';
                    	if (selected == "") {
                    		$("#categoryId").val(value.id);
                    		html = '<li data="'+value.id+'" class="mine z-selected">';
                    		selected = value.id;
                    		getAllSongsByCategory(value.id);
                    		setTitleInfo(value.picture,value.name);
                    		getCommentCount(value.id);
                    	} else {
                    		if (value.id == selected) {
                                html = '<li data="'+value.id+'" class="mine z-selected">';
                                getAllSongsByCategory(value.id);
                                setTitleInfo(value.picture,value.name);
                                getCommentCount(value.id);
                            } else {
                                html = '<li data="'+value.id+'" class="mine">';
                            }
                    	}
                    	html+='<input type="hidden" value="'+value.id+'"/>';
                    	html+='<div class="item f-cb"><div class="left">';
                    	html+='<a class="avatar" href="javascript:selectTopList('+value.id+',\''+value.name+'\',\''+value.picture+'\')"><img src="'+value.picture+'" alt="'+value.name+'" width="40" height="40"><span class="msk"></span></a>';
                    	html+='</div><p class="name"><a href="javascript:selectTopList('+value.id+',\''+value.name+'\',\''+value.picture+'\')" class="s-fc0">'+value.name+'</a></p>';
                    	html+=' </div></li>';
                        $("#categoryTitle").append(html);
                    });
                }
            }  
         },
         error:function() {
        	
         }
    });
});

function selectTopList(id,name,pic) {
	$("#tBody").html("");
	$("#totalSong").html("");
	$("#categoryId").val(id);
	$("#commentCount1").html("");
	$("#totalSong").html("");
	$("#commentContent").html("");
	getAllSongsByCategory(id);
    setTitleInfo(pic,name);
    getCommentCount(id);
    $("#categoryTitle li").each(function(i,d) {
    	if($(this).attr("data")==id) {
    		$(this).attr("class","mine z-selected");
    	} else {
    		$(this).attr("class","mine");
    	}
    });
}

function getCommentCount(categoryId) {
	$.ajax({  
        url:'getCategorySongCommentsCount',
        type:'post',  
        cache:false,
        data:{id:categoryId},
        dataType:'json',  
        success:function(data) {
            if(data.msg =="true"){  
            	$("#Pagination").pagination({
                    dataSource: function(done){
                        var result = [];
                        var len = data.commentCount;
                        $("#commentCount1").html(data.commentCount);
                        var pages = len / pageSize+1;
                        for(var i = 1; i < pages; i++){
                            result.push(i);
                        }
                        done(result);
                    },
                    callback: pageCallback,
                    prevText : "« 上一页", 
                    nextText : "下一页 »", 
                    pageRange: 4,
                    pageSize: 1
                });
            }  
         },
         error:function() {
            
         }
    });
}

function getAllSongsByCategory(id) {
	$.ajax({  
        url:'getSongsByCategoryId',
        type:'post',  
        data:{id:id},
        cache:false,  
        dataType:'json',  
        success:function(data) {
            if(data.msg =="true"){  
                var d = data.data;
                var len = d.length;
                $("#totalSong").html(data.total);
                if (d.length == 0) {
                } else {
                	var i = 1;
                    $.each(d,function(n,value) {
                    	var html = '';
                        if (i % 2 == 0) {
                            html+='<tr>';
                        } else {
                            html+='<tr class="even">';
                        }
                        html+='<td><div class="hd"><span class="num">'+i+'</span></div></td>';
                        html+='<td><div class="f-cb">';
                        html+='<div class="tt">';
                        html+='<a href="javascript:;" onclick="play(\''+value.songPath+'\',\''+value.songName+'\')">';
                        html+='<span class="ply ">&nbsp;</span></a>';
                        html+='<div class="ttc">';
                        html+='<span class="txt"><a href="javascript:goPage(\'playmusic\','+value.id+')"><b title="'+value.songName+'">'+value.songName+'</b></a></span></div></div>';
                        html+='</div></td>';
                        html+='<td class=" s-fc3">'+value.trackLength+'</td>';
                        html+='<td><div class="text" title="'+value.singerName+'">';
                        html+='<span title="'+value.singerName+'"><a';
                        html+='href="javascript:;" hidefocus="true">'+value.singerName+'</a></span></div></td>';
                        i++;
                        $("#tBody").append(html);
                    });
                }
            }  
         },
         error:function() {
            
         }
    });
}

function pageCallback(index, jq) {
	$("#commentContent").html("");
	var songId = $("#categoryId").val();
	$.ajax( {  
        url:'commentlist',// 跳转到 action  
        data:{rows:pageSize,songId:songId,page:index[0],type:3},
        type:'post',  
        cache:false,
        dataType:'json',  
        success:function(data) {  
            if(data.msg =="true"){  
                var d = data.data;
                var len = d.length;
                if (d.length == 0) {
                } else {
                    $.each(d,function(n,value) {
                    	var html = "";
                        html+="<div class=\"itm\">";
                        html+="<div class=\"head\">";
                        html+="<img src=\"images/head.jpg\">";
                        html+="</div>";
                        html+="<div class=\"cntwrap\">";
                        html+="<div class=\"\">";
                        html+="<div class=\"cnt f-brk\">";
                        html+=value.ip+"："+value.content;
                        html+="</div>";
                        html+="</div>";
                        html+="<div class=\"rp\">";
                        html+="<div class=\"time s-fc4\">"+value.time+"</div>";
                        html+="<a href=\"javascript:;\" onclick=\"voteNum('"+value.id+"',this);\"><i class=\"zan u-icn2 u-icn2-12\"></i> <span>"+value.supportNum+"</span></a>";
                        html+="</div>";
                        html+="</div>";
                        html+="</div>";
                        $("#commentContent").append(html);
                    });
                }
            }  
         }
    });
}

function setTitleInfo(picture,name) {
	var html = '<div class="cover u-cover u-cover-rank">';
	html += '<img src="'+picture+'" width="150" height="150"><span class="msk"></span></div>';
	html += '<div class="cnt"><div class="cntc m-info"><div class="hd f-cb">';
	html += '<h2 class="f-ff2">'+name+'</h2></div></div></div>';
    $("#titleInfo").html('');    	
    $("#titleInfo").append(html);
}

function voteNum(id,obj) {
	$.ajax( {  
        url:'addCommentSupportNum',
        type:'post',  
        cache:false,
        data:{"id":id},
        dataType:'json',  
        success:function(data) {  
            if(data.success =="true"){  
            	var num = parseInt($("> span",obj).html());
            	$("> span",obj).html(num+1);
            } else {
                alert("点赞失败。");
            }  
         }
    });
}

function songVoteNum(id,obj) {
    $.ajax( {  
        url:'addSongSupportNum',
        type:'post',  
        cache:false,
        data:{"id":id},
        dataType:'json',  
        success:function(data) {  
            if(data.success =="true"){
                var num = parseInt($("> span",obj).html());
                $("> span",obj).html(num+1);
            } else {
                alert("点赞失败。");
            }  
         }
    });
}

function comment() {
	var content = $("#commentContext").val();
	var categoryId = $("#categoryId").val();
	$.ajax( {  
        url:'addComment',
        type:'post',  
        cache:false,
        data:{"content":content,"id":categoryId,type:3},
        dataType:'json',  
        success:function(data) {  
            if(data.success =="true"){  
                var commentCount = data.commentCount;
                $("#commentContext").val("");
                $("#commentCount1").html(commentCount);
                $("#Pagination").pagination({
                    dataSource: function(done){
                        var result = [];
                        var len = commentCount;
                        var pages = len / pageSize+1;
                        for(var i = 1; i < pages; i++){
                            result.push(i);
                        }
                        done(result);
                    },
                    callback: pageCallback,
                    prevText : "« 上一页", 
                    nextText : "下一页 »", 
                    pageRange: 4,
                    pageSize: 1
                });
            } else {
            	alert("评论失败。");
            }  
         }
    });
}
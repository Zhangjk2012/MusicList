var pageIndex = 1;
var pageSize = 15;
$(function(){
	$.ajax( {  
        url:'getSongListByAlbumId',// 跳转到 action  
        type:'post',  
        cache:false,  
        data:{id:$("#albumId").val()},
        dataType:'json',  
        success:function(data) { 
            if(data.msg =="true"){  
                var d = data.data;
                $("#totalSong").html(data.total);
                var len = d.length;
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
	
	$("#Pagination").pagination({
        dataSource: function(done){
            var result = [];
            var len = $("#commentCount").val();
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
});
function pageCallback(index, jq) {
	$("#commentContent").html("");
	var songId = $("#albumId").val();
	$.ajax( {  
        url:'commentlist',// 跳转到 action  
        data:{rows:pageSize,songId:songId,page:index[0],type:2},
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

function comment() {
	var content = $("#commentContext").val();
	var albumId = $("#albumId").val();
	$.ajax( {  
        url:'addComment',
        type:'post',  
        cache:false,
        data:{"content":content,"id":albumId,type:2},
        dataType:'json',  
        success:function(data) {  
            if(data.success =="true"){  
                var commentCount = data.commentCount;
                $("#commentContext").val("");
                $("#commentCount").val(commentCount);
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
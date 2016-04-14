var pageIndex = 1;
var pageSize = 15;
$(function(){
	parent.stop();
	parent.hidePlayBar();
	$('#uniquePlayer-1').videoPlayer({
        name : '',
        media : {
            m4v: $("#songPath").val(),  
            poster: $("#picturePath").val()
        },
        size : {
            width : '911px',
            height: '450px'
        },
        loadstart: function() {
        	$(this).jPlayer("play");
        },
        ended: function() {
            //$(this).jPlayer("play");
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
	var songId = $("#songId").val();
	$.ajax( {  
        url:'commentlist',// 跳转到 action  
        data:{rows:pageSize,songId:songId,page:index[0],type:1},
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
	var songId = $("#songId").val();
	$.ajax( {  
        url:'addComment',
        type:'post',  
        cache:false,
        data:{"content":content,"id":songId,type:1},
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
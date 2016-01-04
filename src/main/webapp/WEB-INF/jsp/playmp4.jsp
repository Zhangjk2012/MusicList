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
<link rel="stylesheet" href="styles/style.css">
<link rel="stylesheet" href="styles/divers.css">
<link rel="stylesheet" href="styles/core.css">
<link href="styles/pagination.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="static/jplayer/css/player.css" type="text/css" media="all" />



<style type="text/css">
    #wrapper{
	    width: 900px;
	    margin: 20px auto;
	}
	.webPlayer .controls {
	    display: block;
	    position: relative;
	    height: 40px;
	    background: #0b0b0b;
	    color: #969696;
	    padding: 5px 10px;
	    z-index: 996;
	}
</style>
<title>播放MP4</title>
</head>
<body>
    <div class="g-bd4 f-cb">
        <div class="g-mn4">
            <div class="g-mn4c">
                <div class="g-wrap6">
                    <div class="n-mv">
                        <div class="title">
                            <h2 class="f-ff2" id="flag_title1">${name }</h2>
                            <a href="javascript:;" class="name s-fc7">${singerName }</a>
                        </div>
                        <div class="mv" id="flash_box">
                            <div id="uniquePlayer-1" class="webPlayer light">
					            <div id="uniqueContainer-1" class="videoPlayer"></div>
					        </div>
                        </div>
                    </div>
                    <div class="n-cmt">
                        <input type="hidden" id="commentCount" value="${commentcount}"/>
                        <input type="hidden" id="songId" value="${id}"/>
                        <input type="hidden" id="songPath" value="${songPath}"/>
                        <input type="hidden" id="picturePath" value="${picture}"/>
                        <div>
                            <div class="u-title u-title-1">
                                <h3>
                                    <span class="f-ff2">评论</span>
                                </h3>
                                <span class="sub s-fc3">共<span id="commentCount1" class="j-flag">${commentcount}</span>条评论
                                </span>
                            </div>
                            <div class="m-cmmt">
                                <div class="iptarea">
                                    <div class="head">
                                        <img src="images/head.jpg">
                                    </div>
                                    <div class="j-flag">
                                        <div>
                                            <div class="m-cmmtipt f-cb f-pr">
                                                <div class="u-txtwrap holder-parent f-pr" style="display: block;">
                                                    <textarea id="commentContext" class="u-txt area j-flag" placeholder="评论"></textarea>
                                                </div>
                                                <div class="btns f-cb f-pr">
                                                    <a href="javascript:comment()" class="btn u-btn u-btn-1 j-flag">评论</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <h3 class="u-hd4">全部评论</h3>
                                <div id="commentContent" class="cmmts j-flag">
                                    
                                </div>
                            </div>
                        </div>
                        <div id="wrapper">
					       <div id="Pagination" class=""></div>
					    </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>

    <script src="static/js/jquery-1.8.2.min.js"></script>
    <script src="static/js/pagination.min.js"></script>
    <script type="text/javascript" src="js/paybutton.js" ></script>
    <script type="text/javascript" src="static/jplayer/jquery.jplayer.min.js"></script>
    <script type="text/javascript" src="static/jplayer/jplayer.cleanskin.js"></script>
    
    <script type="text/javascript">
    function play(url,name) {
    	parent.play(url,name);
    }
    
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
    </script>
</html>
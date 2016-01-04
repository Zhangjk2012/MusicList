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

<style type="text/css">
    #wrapper{
	    width: 900px;
	    margin: 20px auto;
	    text-align:center;
	    height:105px;
	    line-height:105px;
	    overflow:hidden;
	    vertical-align:middle;
	}
</style>
<title>专辑</title>
</head>
<body>
    <div id="nav" class="m-subnav j-tflag">
        <div class="wrap f-pr">
            <ul class="nav">
                <li><a hidefocus="true" href="main.html" class=""><em>首页</em></a></li>
                <li><a hidefocus="true" href="/discover/toplist" class="z-slt"><em>联系我们</em></a></li>
            </ul>
        </div>
    </div>
    <div id="toplist" class="g-bd3 g-bd3-1 f-cb">
        <input type="hidden" id="categoryId" value="${categoryId }" />
        <div class="g-sd3 g-sd3-1">
            <div class="n-minelst n-minelst-2">
                <h2 class="f-ff1">榜单</h2>
                <ul id="categoryTitle" class="f-cb">
                </ul>
            </div>
        </div>
        <div class="g-mn3">
            <div class="g-mn3c">
                <div class="g-wrap">
                    <div id="titleInfo" class="m-info m-info-rank f-cb">
                    </div>
                </div>
                <div class="g-wrap12">
                    <div class="u-title u-title-1 f-cb">
                        <h3>
                            <span class="f-ff2">歌曲列表</span>
                        </h3>
                        <span class="sub s-fc3"><span id="totalSong">0</span>首歌</span>
                    </div>
                    <div>
                        <div>
                            <div class="j-flag">
                                <table class="m-table m-table-rank">
                                    <thead>
                                        <tr>
                                            <th class="first w1"></th>
                                            <th class=""><div class="wp">标题<span style="display: inline-block;float: right;">点赞</span></div></th>
                                            <th class="w2"><div class="wp">时长</div></th>
                                            <th class="w3"><div class="wp">歌手</div></th>
                                        </tr>
                                    </thead>
                                    <tbody id="tBody">
                                    </tbody>
                                </table>
                            </div>
                            <div class="j-flag"></div>
                        </div>
                    </div>
                    <div class="n-cmt">
                         <div>
                             <div class="u-title u-title-1">
                                 <h3>
                                     <span class="f-ff2">评论</span>
                                 </h3>
                                 <span class="sub s-fc3">共<span id="commentCount1" class="j-flag">0</span>条评论</span>
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
    
    <script type="text/javascript">
    function play(url,name) {
    	parent.play(url,name);
    }
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
                        		html = '<li class="mine z-selected">';
                        		selected = value.id;
                        		getAllSongsByCategory(value.id);
                        		setTitleInfo(value.picture,value.name);
                        		getCommentCount(value.id);
                        	} else {
                        		if (value.id == selected) {
                                    html = '<li class="mine z-selected">';
                                    getAllSongsByCategory(value.id);
                                    setTitleInfo(value.picture,value.name);
                                    getCommentCount(value.id);
                                } else {
                                    html = '<li class="mine">';
                                }
                        	}
                        	html+='<div class="item f-cb"><div class="left">';
                        	html+='<a class="avatar" href="toplist?id='+value.id+'"><img src="'+value.picture+'" alt="'+value.name+'" width="40" height="40"><span class="msk"></span></a>';
                        	html+='</div><p class="name"><a href="toplist?id='+value.id+'" class="s-fc0">'+value.name+'</a></p>';
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
                            html+='<span class="txt"><a href="playmusic?id='+value.id+'"><b title="'+value.songName+'">'+value.songName+'</b></a></span></div></div>';
                            html+='<div class="opt " style="display: block;">';
                            html+='<a href="javascript:;" onclick="songVoteNum(\''+value.id+'\',this)"><i class=" like zan u-icn2 u-icn2-12 "></i> (<span>'+value.voteNum+'</span>)</a></div></div></td>';
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
    </script>
</html>
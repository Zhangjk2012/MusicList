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
	}
</style>
<title>专辑</title>
</head>
<body>
<div id="nav"></div>
    <div class="g-bd4 f-cb">
        <div class="g-mn4">
            <div class="g-mn4c">
                <div class="g-wrap6">
                    <div class="m-lycifo">
                        <div class="f-cb">
                            <div class="cvrwrap f-cb f-pr">
                                <div class="u-cover u-cover-6 f-fl">
                                    <img alt="专辑图片" width=130 height=130 src="${picture }" class="j-img">
                                </div>
                            </div>
                            <div class="cnt" style="width: 614px;">
                                <div class="hd">
                                    <div class="tit">
                                        <em>${name }</em>
                                    </div>
                                </div>
                                <p class="des s-fc4">
                                                                            歌手：<span title="${singerName }">
                                        <a class="s-fc7" href="javascript:;">${singerName }</a>
                                     </span>
                                </p>
                                <p class="des s-fc4">
                                                                            发行时间：${publishTime }
                                </p>
                                <p class="des s-fc4">
                                                                            发行公司：${publishCompany }
                                </p>
	                            <div class="n-albdesc">
	                                    <h3>专辑介绍：</h3>
	                                    <p class="f-brk">${briefIntroduction }</p>
	                            </div>
                            </div>
                        </div>
                    </div>
                    <div class="u-title u-title-1 f-cb">
                        <h3>
                            <span class="f-ff2">歌曲列表</span>
                        </h3>
                        <span class="sub s-fc3"><span>97</span>首歌</span>
                    </div>
                    <div id="song-list-pre-cache" data-key="song_toplist-19723756">
                        <div id="auto-id-cpC7LqSOIpuI0AuO">
                            <div class="j-flag" id="auto-id-2Cyzx7nkdUvfaznn">
                                <table class="m-table m-table-rank">
                                    <thead>
                                        <tr>
                                            <th class="first w1"></th>
                                            <th class=""><div class="wp">标题<span style="display: inline-block;float: right;">点赞</span></div></th>
                                            <th class="w2"><div class="wp">时长</div></th>
                                            <th class="w3"><div class="wp">歌手</div></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr id="386796611450251400801" class="even ">
                                            <td><div class="hd">
                                                    <span class="num">97</span>
                                                    <div class="rk ">
                                                        <span class="u-icn u-icn-75"></span>
                                                    </div>
                                                </div></td>
                                            <td class=""><div class="f-cb">
                                                    <div class="tt">
                                                        <span data-res-id="38679661" data-res-type="18"
                                                            data-res-action="play" class="ply ">&nbsp;</span>
                                                        <div class="ttc">
                                                            <span class="txt"><a href="/song?id=38679661"><b title="We&nbsp;Are&nbsp;One&nbsp;(Original&nbsp;Mix)">We&nbsp;Are&nbsp;One&nbsp;(Original&nbsp;Mix)</b></a></span>
                                                        </div>
                                                    </div>
                                                    
                                                    <div class="opt " style="display: block;">
                                                        <a data-id="45394613" data-type="like" href="javascript:void(0)"><i class=" like zan u-icn2 u-icn2-12 "></i> (15)</a>
                                                    </div>
                                                    
                                                </div></td>
                                            <td class=" s-fc3">03:21</td>
                                            <td class=""><div class="text"
                                                    title="Disco Fries/Kaaze/Danyka Nadeau">
                                                    <span title="Disco Fries/Kaaze/Danyka Nadeau"><a
                                                        class="" href="/artist?id=330684" hidefocus="true">Disco&nbsp;Fries</a>/<a
                                                        class="" href="/artist?id=952052" hidefocus="true">Kaaze</a>/<a
                                                        class="" href="/artist?id=1078277" hidefocus="true">Danyka&nbsp;Nadeau</a></span>
                                                </div></td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                            <div class="j-flag"></div>
                        </div>
                    </div>
                    <div class="n-cmt">
                        <input type="hidden" id="commentCount" value="${commentcount}"/>
                        <input type="hidden" id="songId" value="${id}"/>
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
    
    <script type="text/javascript">
    function play(url,name) {
    	parent.play(url,name);
    }
  	var pageIndex = 1;
    var pageSize = 15;
    $(function(){
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
            data:{rows:pageSize,songId:songId,page:index[0]},
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
            url:'addSupportNum',
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
            data:{"content":content,"songId":songId},
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
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
<title>播放音乐</title>
</head>
<body>
    <div id="nav" class="m-subnav j-tflag">
        <div class="wrap f-pr">
            <ul class="nav">
                <li><a hidefocus="true" href="main.html" class=""><em>首页</em></a></li>
                <li><a hidefocus="true" href="/discover/toplist" class="z-slt"><em>电台列表</em></a></li>
                <li><a hidefocus="true" href="/discover/toplist" class="z-slt"><em>背景介绍</em></a></li>
                <li><a hidefocus="true" href="/discover/toplist" class="z-slt"><em>评委介绍</em></a></li>
                <li><a hidefocus="true" href="/discover/toplist" class="z-slt"><em>近期活动</em></a></li>
                <li><a hidefocus="true" href="/discover/toplist" class="z-slt"><em>联系我们</em></a></li>
            </ul>
        </div>
    </div>
    <div class="g-bd4 f-cb">
        <div class="g-mn4">
            <div class="g-mn4c">
                <div class="g-wrap6">
                    <div class="m-lycifo">
                        <div class="f-cb">
                            <div class="cvrwrap f-cb f-pr">
                                <div class="u-cover u-cover-6 f-fl">
                                    <img alt="歌曲图片" width=130 height=130 src="${picture }" class="j-img">
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
                                                                            所属专辑：<a href="album?id=${album }" class="s-fc7">${albumName }</a>
                                </p>
                                <div class="m-info">
                                    <div id="content-operation" class="btns f-cb">
                                        <a href="javascript:play('${songPath }','${name }');" class="u-btn2 u-btn2-2 u-btni-addply f-fl" hidefocus="true" title="播放">
                                            <i><em class="ply"></em>播放</i>
                                        </a> 
                                    </div>
                                </div>
                                <div id="lyric-content" class="bd bd-open f-brk f-ib">
                                        ${lyricFirst }
                                    <div id="flag_more" class="f-hide">
                                        ${lyricSecond }
                                    </div>
                                    <div class="crl">
                                        <a id="flag_ctrl" href="javascript:void(0)" class="s-fc7 ">展开...</a>
                                    </div>
                                </div>
                            </div>
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
    <div class="g-ft">
		<div class="m-ft">
			<div class="wrap">
			     <div class="copy">
			         <p>合作单位:</p>
			         <div id="partners"> 
			         </div>
			     </div>
			 </div>
		</div>
	</div>
</body>

    <script src="static/js/jquery-1.8.2.min.js"></script>
    <script src="static/js/pagination.min.js"></script>
    <script src="static/js/common.js"></script>
    <script src="static/js/playmusic.js"></script>
    <script type="text/javascript" src="js/paybutton.js" ></script>
    
</html>
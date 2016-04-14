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
    <script type="text/javascript" src="js/paybutton.js" ></script>
    <script type="text/javascript" src="static/jplayer/jquery.jplayer.min.js"></script>
    <script type="text/javascript" src="static/jplayer/jplayer.cleanskin.js"></script>
    <script type="text/javascript" src="static/js/common.js"></script>
    <script type="text/javascript" src="static/js/playmp4.js"></script>
</html>
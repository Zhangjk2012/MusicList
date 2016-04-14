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
                <li><a hidefocus="true" href="/discover/toplist" class="z-slt"><em>电台列表</em></a></li>
                <li><a hidefocus="true" href="/discover/toplist" class="z-slt"><em>背景介绍</em></a></li>
                <li><a hidefocus="true" href="/discover/toplist" class="z-slt"><em>评委介绍</em></a></li>
                <li><a hidefocus="true" href="/discover/toplist" class="z-slt"><em>近期活动</em></a></li>
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
                                            <th class=""><div class="wp">标题</div></th>
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
    <script type="text/javascript" src="static/js/common.js" ></script>
    <script type="text/javascript" src="static/js/toplist.js" ></script>
</html>
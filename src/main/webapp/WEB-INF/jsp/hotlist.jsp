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
<title>热门推荐</title>

<style type="text/css">
#s-list .m-cvrlst li {
    width: 154px;
}

#s-list .m-cvrlst  .u-cover-1 {
    width: 154px;
}
#wrapper{
    width: 900px;
    margin: 20px auto;
}
</style>
</head>
<body>
<div id="nav"></div>
    <input type="hidden" id="songcount" value="${songcount}"/>
    <div class="g-bd" id="m-disc-pl-c">
        <div class="g-wrap p-pl f-pr">
            <div class="u-title f-cb">
                <h3>
                    <span class="f-ff2 d-flag">热门推荐</span>
                </h3>
            </div>
            <ul class="m-cvrlst f-cb" id="musicList">
            </ul>
        </div>
	</div>
	<div id="wrapper">
	   <div id="Pagination" class=""></div>
	</div>
</body>

    <script src="static/js/jquery-1.8.2.min.js"></script>
    <script src="static/js/pagination.min.js"></script>
    <script type="text/javascript">
    var pageIndex = 1;
    var pageSize = 30;
    $(function(){
    	$("#Pagination").pagination({
    		dataSource: function(done){
                var result = [];
                var len = $("#songcount").val();
                var pages = len / pageSize+1;
                for(var i = 1; i < pages; i++){
                    result.push(i);
                }
                done(result);
            },
            callback: pageCallback, //PageCallback() 为翻页调用次函数。 
    		prevText : "« 上一页", 
    		nextText : "下一页 »", 
    		pageRange: 4,
    		pageSize: 1
   		}); 
    });
    function pageCallback(index, jq) {
    	initSong(index);
    } 
    function initSong(pageIndex) {
    	$("#musicList").html("");
    	$.ajax( {  
            url:'hotmorelist?rows='+pageSize+'&page='+pageIndex,// 跳转到 action  
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
                            var html = '<li>';
                            html+='<div class="u-cover u-cover-1">';
                            html+='<img class="j-flag" width=140 height=140 src="'+value.picture+'">';
                            html+='<a title="'+value.name+'" href=\'playmusic?id='+value.id+'\' class="msk"></a>';
                            html+='<div class="bottom">';
                            html+='<a class="icon-play f-fr" title="播放" href="javascript:play(\''+value.songPath+'\',\''+value.name+'\');"></a>';
                            html+='<span class="icon-headset"></span>';
                            html+='<span class="icon-headset"></span>';
                            html+='</div></div>';
                            html+='<p class="dec">';
                            html+='<a title="'+value.name+'" href=\'playmusic?id='+value.id+'\' class="tit f-thide s-fc0">'+value.name+'</a>';
                            html+='</p>';
                            html+='<p>';
                            html+='<a title="'+value.singer+'" href="javascript:;" class="nm nm-icn f-thide s-fc3">'+value.singer+'</a>';
                            html+='<sup class="u-icn u-icn-1"></sup>';
                            html+='<p></li>';
                            $("#musicList").append(html);
                        });
                    }
                }  
             }
        });
    }
    function play(url,name) {
        parent.play(url,name);
    }
    </script>
</html>
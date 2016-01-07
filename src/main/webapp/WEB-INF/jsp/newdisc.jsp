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
<title>新碟上架</title>

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
     <div id="nav" class="m-subnav j-tflag">
      <div class="wrap f-pr">
          <ul class="nav">
              <li><a hidefocus="true" href="main.html" class=""><em>首页</em></a></li>
<!--               <li><a hidefocus="true" href="/discover/toplist" class="z-slt"><em>联系我们</em></a></li>
 -->          </ul>
      </div>
     </div>
    <input type="hidden" id="albumcount" value="${albumcount}"/>
    <div class="g-bd" id="m-disc-pl-c">
        <div class="g-wrap p-pl f-pr">
            <div class="u-title f-cb">
                <h3>
                    <span class="f-ff2 d-flag">新碟上架</span>
                </h3>
            </div>
            <ul class="m-cvrlst m-cvrlst-alb2 f-cb" id="albumList">
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
                var len = $("#albumcount").val();
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
    	initAlbum(index);
    } 
    function initAlbum(pageIndex) {
    	$("#albumList").html("");
    	$.ajax( {  
            url:'albumlistmore?rows='+pageSize+'&page='+pageIndex,// 跳转到 action  
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
                            html+='<div class="u-cover u-cover-alb2">';
                            html+='<img width=130 height=130 src="'+value.picture+'">';
                            html+='<a title="'+value.name+'" href="album?id='+value.id+'" class="msk"></a>';
                            html+='</div>';
                            html+='<p class="dec">';
                            html+='<a title="'+value.name+'" href="album?id='+value.id+'" class="tit f-thide s-fc0">'+value.name+'</a>';
                            html+='</p>';
                            html+='<p><a title="'+value.singerName+'" href="javascript:;" class="nm nm-icn f-thide s-fc3">'+value.singerName+'</a></p>';
                            html+='</li>';
                            $("#albumList").append(html);
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
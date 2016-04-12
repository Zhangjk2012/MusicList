$(function(){
	parent.showPlayBar();
	$.ajax( {  
        url:'news',
        type:'post',  
        cache:false,  
        dataType:'json',  
        success:function(data) {  
            if(data.msg =="true"){  
                var d = data.data;
                var len = d.length;
                if (d.length == 0) {
                } else {
                	for (var i=0;i<(len+2);i++) {
                		if (i==0) {
                    		$("#bannerButUL").append('<li style="display: none"></li>');
                		} else if (i == 1) {
                			$("#bannerButUL").append('<li class="hover"></li>');
                		} else if (i == (len+1)) {
                			$("#bannerButUL").append('<li style="display: none"></li>');
                		} else {
                			$("#bannerButUL").append('<li></li>');
                		}
                	}
                    var first = d[len-1];
                    var img = "<a href='javascript:goPage(\"shownews\","+first.id+")'><img src='"+first.picture+"' width='980' height='500' alt='' /></a> ";
                    $("#scroll").append(img);
                    $.each(d,function(n,value) { 
                        img = "<a href='javascript:goPage(\"shownews\","+value.id+")'><img src='"+value.picture+"' width='980' height='500' alt='' /> </a>";
                        $("#scroll").append(img);
                    });
                    var end = d[0];
                    img = "<a href='javascript:goPage(\"shownews\","+end.id+")'><img src='"+end.picture+"' width='980' height='500' alt='' /> </a>";
                    $("#scroll").append(img);
                    if (len > 1) {
                        end = d[1];
                        img = "<a href='javascript:goPage(\"shownews\","+end.id+")'><img src='"+end.picture+"' width='980' height='500' alt='' /> </a>";
                    }
                    $("#scroll").append(img);
                    $("#banner").css("display","block");
                    $("#bannerButUL").css("display","block");
                    $('.bannerBut ul li').mouseover(function(){
                        $(this).addClass('hover').siblings('li').removeClass('hover');
                        var _index1=$(this).index();
                        $('.bannerCon .scroll').stop().animate({left:-_index1*980},500);
                    });
                }
            }  
         }
    });
	
	$.ajax( {  
        url:'hotlist',
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
                 	   html+='<a title="'+value.name+'" href="javascript:goPage(\'playmusic\','+value.id+')" class="msk"></a>';
                 	   html+='<div class="bottom">';
                 	   html+='<a class="icon-play f-fr" title="播放" href="javascript:play(\''+value.songPath+'\',\''+value.name+'\');"></a>';
                 	   html+='<span class="icon-headset"></span>';
                 	   html+='<span class="icon-headset"></span>';
                 	   html+='</div></div>';
                 	   html+='<p class="dec">';
                 	   html+='<a title="'+value.name+'" href="javascript:goPage(\'playmusic\','+value.id+')" class="tit f-thide s-fc0">'+value.name+'</a>';
                 	   html+='</p>';
                 	   html+='<p>';
                 	   html+='<a title="'+value.singer+'" href="javascript:;" class="nm nm-icn f-thide s-fc3">'+value.singer+'</a>';
                 	   html+='<sup class="u-icn u-icn-1"></sup>';
                 	   html+='<p></li>';
                        $("#hotul").append(html);
                    });
                }
            }  
         }
    });
	
});

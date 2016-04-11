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
                    var img = "<a href='javascript:goNews("+first.id+")'><img src='"+first.picture+"' width='980' height='500' alt='' /></a> ";
                    $("#scroll").append(img);
                    $.each(d,function(n,value) { 
                        img = "<a href='javascript:goNews("+value.id+")'><img src='"+value.picture+"' width='980' height='500' alt='' /> </a>";
                        $("#scroll").append(img);
                    });
                    var end = d[0];
                    img = "<a href='javascript:goNews("+end.id+")'><img src='"+end.picture+"' width='980' height='500' alt='' /> </a>";
                    $("#scroll").append(img);
                    if (len > 1) {
                        end = d[1];
                        img = "<a href='javascript:goNews("+end.id+")'><img src='"+end.picture+"' width='980' height='500' alt='' /> </a>";
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
});

function goNews(id) {
	parent.turnPage("shownews?id="+id);
}


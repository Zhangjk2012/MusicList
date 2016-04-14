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
	$.ajax( {  
        url:'popularalbumlist',
        type:'post',  
        cache:false,  
        dataType:'json',  
        success:function(data) {  
            if(data.msg =="true"){ 
                var d = data.data;
                var len = d.length;
                if (len == 0) {
                } else {
             	   var i = 1;
             	   var k = 0;
             	   var html = '<ul class="f-cb roller-flag" style="left:-0px;">';
             	   for (i=1;i<=len;i++) {
             		   var value = d[i-1];
             		   html+='<li>';
                        html+='<div class="u-cover u-cover-alb1">';
                        html+='<img class="j-img" width=100 height=100 src="'+value.picture+'"/>';
                        html+='<a title="'+value.name+'" href="javascript:goPage(\'album\','+value.id+')" class="msk"></a>';
                        html+='<a class="" title="播放" href="javascript:;"></a>';
                        html+='</div>';
                        html+='<p class="f-thide">';
                        html+='<a title="'+value.name+'" href="javascript:goPage(\'album\','+value.id+')" class="s-fc0 tit">'+value.name+'</a>';
                        html+='</p>';
                        html+='<p class="f-thide">';
                        html+='<a title="'+value.singerName+'" href="javascript:;" class="tit s-fc3">'+value.singerName+'</a>';
                        html+='<p></li>';
                        if (i % 5 == 0) {
                     	   html += "</ul>";
                     	   $("#newPopularAlbum").append(html);
                     	   k+= 645;
                     	   html = "";
                     	   if (i == len) {
                     		   break;
                     	   }
                     	   html = '<ul class="f-cb roller-flag" style="left: '+k+'px;">';;
                     	   
                        }
             	   }
             	   if (html != "") {
             		   html += "</ul>";
                        $("#newPopularAlbum").append(html);
                        html = "";
             	   }
             	  initPopularClick();
                }
            }  
         }
    });
	$.ajax( {  
		url:'rockalbumlist',
		type:'post',  
		cache:false,  
		dataType:'json',  
		success:function(data) {  
			if(data.msg =="true"){ 
				var d = data.data;
				var len = d.length;
				if (len == 0) {
				} else {
					var i = 1;
					var k = 0;
					var html = '<ul class="f-cb roller-flag" style="left:-0px;">';
					for (i=1;i<=len;i++) {
						var value = d[i-1];
						html+='<li>';
						html+='<div class="u-cover u-cover-alb1">';
						html+='<img class="j-img" width=100 height=100 src="'+value.picture+'"/>';
						html+='<a title="'+value.name+'" href="javascript:goPage(\'album\','+value.id+')" class="msk"></a>';
						html+='<a class="" title="播放" href="javascript:;"></a>';
						html+='</div>';
						html+='<p class="f-thide">';
						html+='<a title="'+value.name+'" href="javascript:goPage(\'album\','+value.id+')" class="s-fc0 tit">'+value.name+'</a>';
						html+='</p>';
						html+='<p class="f-thide">';
						html+='<a title="'+value.singerName+'" href="javascript:;" class="tit s-fc3">'+value.singerName+'</a>';
						html+='<p></li>';
						if (i % 5 == 0) {
							html += "</ul>";
							$("#newRockAlbum").append(html);
							k+= 645;
							html = "";
							if (i == len) {
								break;
							}
							html = '<ul class="f-cb roller-flag" style="left: '+k+'px;">';;
							
						}
					}
					if (html != "") {
						html += "</ul>";
						$("#newRockAlbum").append(html);
						html = "";
					}
					initRockClick();
				}
			}  
		}
	});
	//榜单
    $.ajax( {  
        url:'songlistcategory',
        type:'post',  
        cache:false,  
        dataType:'json',  
        success:function(data) {  
            if(data.msg =="true"){ 
                var d = data.data;
                var len = d.length;
                if (len == 0) {
                } else {
                    var i = 1;
                    for (i=1;i<=len;i++) {
                        var value = d[i-1];
                        var html = '';
                        if (i == 3) {
                     	   html = '<dl class="blk blk-1">';
                        } else {
                     	   html = '<dl class="blk">';
                        }
                        html += '<dt class="top"><div class="cver u-cover u-cover-4">';
                        html += '<img class="j-img" alert="picture" src="'+value.picture+'" width="100" height="100">';
                        html += '<a href="javascript:goPage(\'toplist\','+value.id+')" class="msk" title="'+value.name+'"></a>';
                        html += '</div><div class="tit"><a href="javascript:goPage(\'toplist\','+value.id+')" title="'+value.name+'"><h3 class="f-fs1 f-thide">'+value.name+'</h3></a></div></dt><dd><ol>';
                        if (value.songs.length != 0) {
                     	   var songs = value.songs;
                     	   $.each(songs, function(i,val){    
                     		   html += '<li onmouseover="this.className=\'z-hvr\'" onmouseout="this.className=\'\'">';
                     		   html += '<span class="no no-top">'+(i+1)+'</span>';
                     		   html += '<a href="javascript:goPage(\'playmusic\','+val.id+')" class="nm s-fc0 f-thide" title="'+val.songName+'">'+val.songName+'</a>';
                     		   html += '<div class="oper"><a href="javascript:play(\''+val.songPath+'\',\''+val.songName+'\');" class="s-bg s-bg-11" title="播放" hidefocus="true"></a>';
                     		   html += '</div></li>';
                    		   });
                        }
                    	html += '</ol><div class="more"><a href="javascript:goPage(\'toplist\','+value.id+')" class="s-fc0">查看全部&gt;</a></div></dd></dl>';
                        $("#toplist").append(html);
                    }
                }
            }  
         }
    });
	autoTurn();
});
var populartransflag = false;
var rocktransflag = false;
function initPopularClick() {
	var uls = $("#rollerPopular").find("ul");
    $("#rollerPopularRight").click(function() {
        if( populartransflag ) {
            return ;
        }
        populartransflag = true;
        for( var i=0; i<uls.length; i++ ) {
            var ul = $(uls[i]);
            var left = parseInt( ul.css("left").replace("px","") );
            left = left + 645;
            if( left > 1290 ) {
                left = -645;
                ul.css({
                    "left":left + "px",
                    "z-index" : "-1"
                }); 
            } else {
                ul.css({
                    "-webkit-transition": "left 1s ease-out 0s",
                    "transition": "left 1s ease-out 0s",
                    "z-index" : "2",
                }); 
                ul.css({
                    "left":left + "px"
                });
            }
        }       
        
        setTimeout(function() {
            $("#rollerPopularRight ul").css({
                "-webkit-transition": "none",
                "transition": "none",
                "display" : "block"
            });  
            populartransflag = false;
        }, 1100);
    });
    
    $("#rollerPopularLeft").click(function() {
        if( populartransflag ) {
            return ;
        }
        populartransflag = true;
        for( var i=0; i<uls.length; i++ ) {
            var ul = $(uls[i]);
            var left = parseInt( ul.css("left").replace("px","") );
            left = left - 645;
            if( left < -645 ) {
                left = 1290;
                ul.css({
                    "left":left + "px",
                    "z-index" : "-1"
                }); 
            } else {
                ul.css({
                    "-webkit-transition": "left 1s ease-out 0s",
                    "transition": "left 1s ease-out 0s",
                    "z-index" : "2",
                }); 
                ul.css({
                    "left":left + "px"
                });
            }
        }       
        setTimeout(function() {
            $("#rollerPopularRight ul").css({
                "-webkit-transition": "none",
                "transition": "none",
                "display" : "block"
            });  
            populartransflag = false;
        }, 1100);
    });
}

function initRockClick() {
	var uls = $("#rollerRock").find("ul");
	$("#rollerRockRight").click(function() {
		if( rocktransflag ) {
			return ;
		}
		rocktransflag = true;
		for( var i=0; i<uls.length; i++ ) {
			var ul = $(uls[i]);
			var left = parseInt( ul.css("left").replace("px","") );
			left = left + 645;
			if( left > 1290 ) {
				left = -645;
				ul.css({
					"left":left + "px",
					"z-index" : "-1"
				}); 
			} else {
				ul.css({
					"-webkit-transition": "left 1s ease-out 0s",
					"transition": "left 1s ease-out 0s",
					"z-index" : "2",
				}); 
				ul.css({
					"left":left + "px"
				});
			}
		}       
		
		setTimeout(function() {
			$("#rollerRockRight ul").css({
				"-webkit-transition": "none",
				"transition": "none",
				"display" : "block"
			});  
			rocktransflag = false;
		}, 1100);
	});
	
	$("#rollerRockLeft").click(function() {
		if( rocktransflag ) {
			return ;
		}
		rocktransflag = true;
		for( var i=0; i<uls.length; i++ ) {
			var ul = $(uls[i]);
			var left = parseInt( ul.css("left").replace("px","") );
			left = left - 645;
			if( left < -645 ) {
				left = 1290;
				ul.css({
					"left":left + "px",
					"z-index" : "-1"
				}); 
			} else {
				ul.css({
					"-webkit-transition": "left 1s ease-out 0s",
					"transition": "left 1s ease-out 0s",
					"z-index" : "2",
				}); 
				ul.css({
					"left":left + "px"
				});
			}
		}       
		setTimeout(function() {
			$("#rollerRockRight ul").css({
				"-webkit-transition": "none",
				"transition": "none",
				"display" : "block"
			});  
			rocktransflag = false;
		}, 1100);
	});
}

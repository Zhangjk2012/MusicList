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
                        $("#musicList").append(html);
                    });
                }
            }  
         }
    });
}
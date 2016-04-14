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
        url:'rockalbumlistmore?rows='+pageSize+'&page='+pageIndex,// 跳转到 action  
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
                        html+='<a title="'+value.name+'" href="javascript:goPage(\'album\','+value.id+')" class="msk"></a>';
                        html+='</div>';
                        html+='<p class="dec">';
                        html+='<a title="'+value.name+'" href="javascript:goPage(\'album\','+value.id+')" class="tit f-thide s-fc0">'+value.name+'</a>';
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
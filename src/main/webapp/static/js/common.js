$(function(){
	// 获取合作单位
	$("#partners") != null && $.get("getpartners",function(data){
		var d = eval('('+data+')');
		if (d.msg) {
			var html = "";
			$(d.data).each(function(index,p){
				html+=("<a target='_blank' style='padding-left: 5px;margin-left: 5px;' href='"+p.url+"' title='"+p.name+"'><img alt='"+p.name+"' src='"+p.logoPath+"' style='width:50px;height:50px'/></a>");
			});
			$("#partners").append(html);
		}
	});
});

function goPage(url,id) {
	parent.turnPage(url+"?id="+id);
}

function play(url,name) {
	parent.play(url,name);
}
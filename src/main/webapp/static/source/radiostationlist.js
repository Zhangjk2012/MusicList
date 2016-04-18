$(function(){
	$.ajax( {  
        url:'radiostationlist',// 跳转到 action  
        type:'post',  
        cache:false,  
        dataType:'json',  
        success:function(data) { 
            if(data.msg =="true"){  
                var d = data.data;
                var len = d.length;
                if (d.length == 0) {
                } else {
                	var i = 1;
                    $.each(d,function(n,value) {
                    	var html = '';
                    	if (i % 2 == 0) {
                    		html+='<tr>';
                    	} else {
                    		html+='<tr class="even">';
                    	}
                    	html+='<td><div class="hd"><span class="num">'+i+'</span></div></td>';
                    	html+='<td><div class="f-cb">';
                    	html+='<div class="tt">';
                    	html+='<a href="'+value.url+'" target="_blank">'+value.name+'</a></td>';
                        i++;
                        $("#tBody").append(html);
                    });
                }
            }  
         },
         error:function() {
        	
         }
    });
});
$(function(){$.ajax({url:"raters",type:"post",cache:false,dataType:"json",success:function(D){if(D.msg=="true"){var A=D.data;var C=A.length;if(A.length==0){}else{var B=1;$.each(A,function(F,G){var E="";if(B%2==0){E+="<tr>"}else{E+='<tr class="even">'}E+='<td><div class="hd"><span class="num">'+B+"</span></div></td>";E+='<td><div class="f-cb">';E+='<div class="tt">';E+="<a href=\"javascript:goPage('getratercontent',"+G.id+')">'+G.name+"</a></td>";B++;$("#tBody").append(E)})}}},error:function(){}})});
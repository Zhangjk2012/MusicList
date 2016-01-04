$(function() {
	var islock = false;
	$("#locktbn").click(function() {
		islock = !islock;
		if( islock ) {
			$(".m-playbar").removeClass("m-playbar-unlock");
			$(".m-playbar").addClass("m-playbar-lock");
		} else {
			$(".m-playbar").removeClass("m-playbar-lock");
			$(".m-playbar").addClass("m-playbar-unlock");
		}
	});
	
	$(".m-playbar").mouseenter(function() {
		if (islock == false) {
			$(this).css({
				"-webkit-transition": "top 0.3s ease-out 0s",
				"transition": "top 0.3s ease-out 0s",
				"top" : "-53px",
			}); 
		}
	});
	$(".m-playbar").mouseleave(function() {
		if (islock == false) {
			$(this).css({
				"-webkit-transition": "top 0.3s ease-out 0s",
				"transition": "top 0.3s ease-out 0s",
				"top" : "-7px",
			});
		}
	});
	
//	$("body").mousemove(function(e) {
//		if(islock){return ;}
//		var xx = e.originalEvent.x || e.originalEvent.layerX || 0; 
//		var yy = e.originalEvent.y || e.originalEvent.layerY || 0; 
//		var height = $(window).height();
//		if( height - yy > 60 ) {
//			setTimeout( function() {
//				$(".m-playbar").css({
//					"-webkit-transition": "top 0.3s ease-out 0s",
//					"transition": "top 0.3s ease-out 0s",
//					"top" : "-7px",
//				}); 
//			}, 100);
//		}
//	});
	$("#flag_ctrl").click(function() {
		if( $("#flag_more").hasClass("f-hide") ) {
			 $("#flag_more").removeClass("f-hide");
			 $(this).text("收起...");
		} else {
			$("#flag_more").addClass("f-hide");
			$(this).text("展开...");
		}
	});
});
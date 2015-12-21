
//广告图动画
var _index1=1;
$('.bannerBut ul li').mouseover(function(){
	$(this).addClass('hover').siblings('li').removeClass('hover');
	var _index1=$(this).index();
	$('.bannerCon .scroll').stop().animate({left:-_index1*750},500);
});

//右边按钮
$('.after img').click(function(){
	_index1++;
	if(_index1>8){
		_index1=1;
		$('.scroll').css('left','0px');
		$('.scroll').animate({left:-_index1*750},500);
		$('.bannerBut ul li').eq(_index1).addClass('hover').siblings('li').removeClass('hover');
	}else{
		$('.bannerBut ul li').eq(_index1).addClass('hover').siblings('li').removeClass('hover');
		$('.scroll').animate({left:-_index1*750},500);
	}
});


//左边按钮
$('.before img').click(function(){
	_index1--;
	if(_index1<1){
		_index1=8;
		$('.scroll').css('left','-6750px');
		$('.scroll').animate({left:-_index1*750},500);
		$('.bannerBut ul li').eq(_index1).addClass('hover').siblings('li').removeClass('hover');
	}else{
		$('.bannerBut ul li').eq(_index1).addClass('hover').siblings('li').removeClass('hover');
		$('.scroll').animate({left:-_index1*750},500);
	}
});



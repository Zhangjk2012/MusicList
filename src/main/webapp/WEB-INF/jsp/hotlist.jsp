<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="styles/style.css">
<link rel="stylesheet" href="styles/divers.css">
<link rel="stylesheet" href="styles/core.css">
<title>Insert title here</title>

<style type="text/css">
#s-list .m-cvrlst li {
    width: 154px;
}

#s-list .m-cvrlst  .u-cover-1 {
    width: 154px;
}
</style>
</head>
<body>
<div id="nav"></div>


    <div class="g-bd" id="m-disc-pl-c">
        <div class="g-wrap p-pl f-pr">
            <div class="u-title f-cb">
                <h3>
                    <span class="f-ff2 d-flag">根据需要自己填写</span>
                </h3>
            </div>
            <div id="cateListBox" class="n-sltlyr d-flag" style="left:0;">
                <div class="hd">
                    <i class="icn"></i>
                </div>
                <div class="bd">
                    <h3>
                        <a href="/discover/playlist/" class="j-flag u-btn u-btn-g s-fc1"
                            data-cat="全部"><em>全部风格</em></a>
                    </h3>
                    <dl class="f-cb">
                        <dt>
                            <i class="u-icn u-icn-71"></i>语种
                        </dt>
                        <dd>
                            <a class="s-fc1 "
                                href="/discover/playlist/?cat=%E5%8D%8E%E8%AF%AD" data-cat="华语">华语</a><span
                                class="line">|</span> <a class="s-fc1 "
                                href="/discover/playlist/?cat=%E6%AC%A7%E7%BE%8E" data-cat="欧美">欧美</a><span
                                class="line">|</span> <a class="s-fc1 "
                                href="/discover/playlist/?cat=%E6%97%A5%E8%AF%AD" data-cat="日语">日语</a><span
                                class="line">|</span> <a class="s-fc1 "
                                href="/discover/playlist/?cat=%E9%9F%A9%E8%AF%AD" data-cat="韩语">韩语</a><span
                                class="line">|</span> <a class="s-fc1 "
                                href="/discover/playlist/?cat=%E7%B2%A4%E8%AF%AD" data-cat="粤语">粤语</a><span
                                class="line">|</span> <a class="s-fc1 "
                                href="/discover/playlist/?cat=%E5%B0%8F%E8%AF%AD%E7%A7%8D"
                                data-cat="小语种">小语种</a><span class="line">|</span>
                        </dd>
                    </dl>
                    <dl class="f-cb">
                        <dt>
                            <i class="u-icn u-icn-6"></i>风格
                        </dt>
                        <dd>
                            <a class="s-fc1 "
                                href="/discover/playlist/?cat=%E6%B5%81%E8%A1%8C" data-cat="流行">流行</a><span
                                class="line">|</span> <a class="s-fc1 "
                                href="/discover/playlist/?cat=%E6%91%87%E6%BB%9A" data-cat="摇滚">摇滚</a><span
                                class="line">|</span> <a class="s-fc1 "
                                href="/discover/playlist/?cat=%E6%B0%91%E8%B0%A3" data-cat="民谣">民谣</a><span
                                class="line">|</span> <a class="s-fc1 "
                                href="/discover/playlist/?cat=%E7%94%B5%E5%AD%90" data-cat="电子">电子</a><span
                                class="line">|</span> <a class="s-fc1 "
                                href="/discover/playlist/?cat=%E8%88%9E%E6%9B%B2" data-cat="舞曲">舞曲</a><span
                                class="line">|</span> <a class="s-fc1 "
                                href="/discover/playlist/?cat=%E8%AF%B4%E5%94%B1" data-cat="说唱">说唱</a><span
                                class="line">|</span> <a class="s-fc1 "
                                href="/discover/playlist/?cat=%E8%BD%BB%E9%9F%B3%E4%B9%90"
                                data-cat="轻音乐">轻音乐</a><span class="line">|</span> <a
                                class="s-fc1 " href="/discover/playlist/?cat=%E7%88%B5%E5%A3%AB"
                                data-cat="爵士">爵士</a><span class="line">|</span> <a
                                class="s-fc1 " href="/discover/playlist/?cat=%E4%B9%A1%E6%9D%91"
                                data-cat="乡村">乡村</a><span class="line">|</span> <a
                                class="s-fc1 " href="/discover/playlist/?cat=R%26B%2FSoul"
                                data-cat="R&amp;B/Soul">R&amp;B/Soul</a><span class="line">|</span>
                            <a class="s-fc1 "
                                href="/discover/playlist/?cat=%E5%8F%A4%E5%85%B8" data-cat="古典">古典</a><span
                                class="line">|</span> <a class="s-fc1 "
                                href="/discover/playlist/?cat=%E6%B0%91%E6%97%8F" data-cat="民族">民族</a><span
                                class="line">|</span> <a class="s-fc1 "
                                href="/discover/playlist/?cat=%E8%8B%B1%E4%BC%A6" data-cat="英伦">英伦</a><span
                                class="line">|</span> <a class="s-fc1 "
                                href="/discover/playlist/?cat=%E9%87%91%E5%B1%9E" data-cat="金属">金属</a><span
                                class="line">|</span> <a class="s-fc1 "
                                href="/discover/playlist/?cat=%E6%9C%8B%E5%85%8B" data-cat="朋克">朋克</a><span
                                class="line">|</span> <a class="s-fc1 "
                                href="/discover/playlist/?cat=%E8%93%9D%E8%B0%83" data-cat="蓝调">蓝调</a><span
                                class="line">|</span> <a class="s-fc1 "
                                href="/discover/playlist/?cat=%E9%9B%B7%E9%AC%BC" data-cat="雷鬼">雷鬼</a><span
                                class="line">|</span> <a class="s-fc1 "
                                href="/discover/playlist/?cat=%E4%B8%96%E7%95%8C%E9%9F%B3%E4%B9%90"
                                data-cat="世界音乐">世界音乐</a><span class="line">|</span> <a
                                class="s-fc1 " href="/discover/playlist/?cat=%E6%8B%89%E4%B8%81"
                                data-cat="拉丁">拉丁</a><span class="line">|</span> <a
                                class="s-fc1 "
                                href="/discover/playlist/?cat=%E5%8F%A6%E7%B1%BB%2F%E7%8B%AC%E7%AB%8B"
                                data-cat="另类/独立">另类/独立</a><span class="line">|</span> <a
                                class="s-fc1 " href="/discover/playlist/?cat=New%20Age"
                                data-cat="New Age">New Age</a><span class="line">|</span> <a
                                class="s-fc1 " href="/discover/playlist/?cat=%E5%8F%A4%E9%A3%8E"
                                data-cat="古风">古风</a><span class="line">|</span> <a
                                class="s-fc1 " href="/discover/playlist/?cat=%E5%90%8E%E6%91%87"
                                data-cat="后摇">后摇</a><span class="line">|</span> <a
                                class="s-fc1 " href="/discover/playlist/?cat=Bossa%20Nova"
                                data-cat="Bossa Nova">Bossa Nova</a><span class="line">|</span>
                        </dd>
                    </dl>
                    <dl class="f-cb">
                        <dt>
                            <i class="u-icn u-icn-7"></i>场景
                        </dt>
                        <dd>
                            <a class="s-fc1 "
                                href="/discover/playlist/?cat=%E6%B8%85%E6%99%A8" data-cat="清晨">清晨</a><span
                                class="line">|</span> <a class="s-fc1 "
                                href="/discover/playlist/?cat=%E5%A4%9C%E6%99%9A" data-cat="夜晚">夜晚</a><span
                                class="line">|</span> <a class="s-fc1 "
                                href="/discover/playlist/?cat=%E5%AD%A6%E4%B9%A0" data-cat="学习">学习</a><span
                                class="line">|</span> <a class="s-fc1 "
                                href="/discover/playlist/?cat=%E5%B7%A5%E4%BD%9C" data-cat="工作">工作</a><span
                                class="line">|</span> <a class="s-fc1 "
                                href="/discover/playlist/?cat=%E5%8D%88%E4%BC%91" data-cat="午休">午休</a><span
                                class="line">|</span> <a class="s-fc1 "
                                href="/discover/playlist/?cat=%E4%B8%8B%E5%8D%88%E8%8C%B6"
                                data-cat="下午茶">下午茶</a><span class="line">|</span> <a
                                class="s-fc1 " href="/discover/playlist/?cat=%E5%9C%B0%E9%93%81"
                                data-cat="地铁">地铁</a><span class="line">|</span> <a
                                class="s-fc1 " href="/discover/playlist/?cat=%E9%A9%BE%E8%BD%A6"
                                data-cat="驾车">驾车</a><span class="line">|</span> <a
                                class="s-fc1 " href="/discover/playlist/?cat=%E8%BF%90%E5%8A%A8"
                                data-cat="运动">运动</a><span class="line">|</span> <a
                                class="s-fc1 " href="/discover/playlist/?cat=%E6%97%85%E8%A1%8C"
                                data-cat="旅行">旅行</a><span class="line">|</span> <a
                                class="s-fc1 " href="/discover/playlist/?cat=%E6%95%A3%E6%AD%A5"
                                data-cat="散步">散步</a><span class="line">|</span> <a
                                class="s-fc1 " href="/discover/playlist/?cat=%E9%85%92%E5%90%A7"
                                data-cat="酒吧">酒吧</a><span class="line">|</span>
                        </dd>
                    </dl>
                    <dl class="f-cb">
                        <dt>
                            <i class="u-icn u-icn-8"></i>情感
                        </dt>
                        <dd>
                            <a class="s-fc1 "
                                href="/discover/playlist/?cat=%E6%80%80%E6%97%A7" data-cat="怀旧">怀旧</a><span
                                class="line">|</span> <a class="s-fc1 "
                                href="/discover/playlist/?cat=%E6%B8%85%E6%96%B0" data-cat="清新">清新</a><span
                                class="line">|</span> <a class="s-fc1 "
                                href="/discover/playlist/?cat=%E6%B5%AA%E6%BC%AB" data-cat="浪漫">浪漫</a><span
                                class="line">|</span> <a class="s-fc1 "
                                href="/discover/playlist/?cat=%E6%80%A7%E6%84%9F" data-cat="性感">性感</a><span
                                class="line">|</span> <a class="s-fc1 "
                                href="/discover/playlist/?cat=%E4%BC%A4%E6%84%9F" data-cat="伤感">伤感</a><span
                                class="line">|</span> <a class="s-fc1 "
                                href="/discover/playlist/?cat=%E6%B2%BB%E6%84%88" data-cat="治愈">治愈</a><span
                                class="line">|</span> <a class="s-fc1 "
                                href="/discover/playlist/?cat=%E6%94%BE%E6%9D%BE" data-cat="放松">放松</a><span
                                class="line">|</span> <a class="s-fc1 "
                                href="/discover/playlist/?cat=%E5%AD%A4%E7%8B%AC" data-cat="孤独">孤独</a><span
                                class="line">|</span> <a class="s-fc1 "
                                href="/discover/playlist/?cat=%E6%84%9F%E5%8A%A8" data-cat="感动">感动</a><span
                                class="line">|</span> <a class="s-fc1 "
                                href="/discover/playlist/?cat=%E5%85%B4%E5%A5%8B" data-cat="兴奋">兴奋</a><span
                                class="line">|</span> <a class="s-fc1 "
                                href="/discover/playlist/?cat=%E5%BF%AB%E4%B9%90" data-cat="快乐">快乐</a><span
                                class="line">|</span> <a class="s-fc1 "
                                href="/discover/playlist/?cat=%E5%AE%89%E9%9D%99" data-cat="安静">安静</a><span
                                class="line">|</span> <a class="s-fc1 "
                                href="/discover/playlist/?cat=%E6%80%9D%E5%BF%B5" data-cat="思念">思念</a><span
                                class="line">|</span>
                        </dd>
                    </dl>
                    <dl class="f-cb">
                        <dt>
                            <i class="u-icn u-icn-9"></i>主题
                        </dt>
                        <dd class="last">
                            <a class="s-fc1 "
                                href="/discover/playlist/?cat=%E5%BD%B1%E8%A7%86%E5%8E%9F%E5%A3%B0"
                                data-cat="影视原声">影视原声</a><span class="line">|</span> <a
                                class="s-fc1 " href="/discover/playlist/?cat=ACG" data-cat="ACG">ACG</a><span
                                class="line">|</span> <a class="s-fc1 "
                                href="/discover/playlist/?cat=%E6%A0%A1%E5%9B%AD" data-cat="校园">校园</a><span
                                class="line">|</span> <a class="s-fc1 "
                                href="/discover/playlist/?cat=%E6%B8%B8%E6%88%8F" data-cat="游戏">游戏</a><span
                                class="line">|</span> <a class="s-fc1 "
                                href="/discover/playlist/?cat=70%E5%90%8E" data-cat="70后">70后</a><span
                                class="line">|</span> <a class="s-fc1 "
                                href="/discover/playlist/?cat=80%E5%90%8E" data-cat="80后">80后</a><span
                                class="line">|</span> <a class="s-fc1 "
                                href="/discover/playlist/?cat=90%E5%90%8E" data-cat="90后">90后</a><span
                                class="line">|</span> <a class="s-fc1 "
                                href="/discover/playlist/?cat=%E7%BD%91%E7%BB%9C%E6%AD%8C%E6%9B%B2"
                                data-cat="网络歌曲">网络歌曲</a><span class="line">|</span> <a
                                class="s-fc1 " href="/discover/playlist/?cat=KTV" data-cat="KTV">KTV</a><span
                                class="line">|</span> <a class="s-fc1 "
                                href="/discover/playlist/?cat=%E7%BB%8F%E5%85%B8" data-cat="经典">经典</a><span
                                class="line">|</span> <a class="s-fc1 "
                                href="/discover/playlist/?cat=%E7%BF%BB%E5%94%B1" data-cat="翻唱">翻唱</a><span
                                class="line">|</span> <a class="s-fc1 "
                                href="/discover/playlist/?cat=%E5%90%89%E4%BB%96" data-cat="吉他">吉他</a><span
                                class="line">|</span> <a class="s-fc1 "
                                href="/discover/playlist/?cat=%E9%92%A2%E7%90%B4" data-cat="钢琴">钢琴</a><span
                                class="line">|</span> <a class="s-fc1 "
                                href="/discover/playlist/?cat=%E5%99%A8%E4%B9%90" data-cat="器乐">器乐</a><span
                                class="line">|</span> <a class="s-fc1 "
                                href="/discover/playlist/?cat=%E5%84%BF%E7%AB%A5" data-cat="儿童">儿童</a><span
                                class="line">|</span> <a class="s-fc1 "
                                href="/discover/playlist/?cat=%E6%A6%9C%E5%8D%95" data-cat="榜单">榜单</a><span
                                class="line">|</span> <a class="s-fc1 "
                                href="/discover/playlist/?cat=00%E5%90%8E" data-cat="00后">00后</a><span
                                class="line">|</span>
                        </dd>
                    </dl>
                </div>
                <div class="ft"></div>
            </div>
            <ul class="m-cvrlst f-cb" id="m-pl-container">
                <li>
                    <div class="u-cover u-cover-1">
                        <img class="j-flag"
                            src="http://p3.music.126.net/jJtRbjKYrJ7V83toM07jBg==/3297435378210412.jpg?param=140y140">
                        <a title="楚生正在听之火车上的旅途" href="/playlist?id=139784927" class="msk"></a>
                        <div class="bottom">
                            <a class="icon-play f-fr" title="播放" href="javascript:;"
                                data-res-type="13" data-res-id="139784927"
                                data-res-action="play"></a> <span class="icon-headset"></span> <span
                                class="nb">37322</span>
                        </div>
                    </div>
                    <p class="dec">
                        <a title="楚生正在听之火车上的旅途" href="/playlist?id=139784927"
                            class="tit f-thide s-fc0">楚生正在听之火车上的旅途</a>
                    </p>
                    <p>
                        <span class="s-fc4">by</span> <a title="陈楚生"
                            href="/user/home?id=39002" class="nm nm-icn f-thide s-fc3">陈楚生</a>
                        <sup class="u-icn u-icn-1"></sup>
                    </p>
                </li>
                <li>
                    <div class="u-cover u-cover-1">
                        <img class="j-flag"
                            src="http://p3.music.126.net/-nMYachgJM03a0SsZu-0XQ==/3273246122389925.jpg?param=140y140">
                        <a title="【MV】素食者都舔屏了，怪我咯" href="/playlist?id=140592496"
                            class="msk"></a>
                        <div class="bottom">
                            <a class="icon-play f-fr" title="播放" href="javascript:;"
                                data-res-type="13" data-res-id="140592496"
                                data-res-action="play"></a> <span class="icon-headset"></span> <span
                                class="nb">25012</span>
                        </div>
                    </div>
                    <p class="dec">
                        <a title="【MV】素食者都舔屏了，怪我咯" href="/playlist?id=140592496"
                            class="tit f-thide s-fc0">【MV】素食者都舔屏了，怪我咯</a>
                    </p>
                    <p>
                        <span class="s-fc4">by</span> <a title="AmyBB"
                            href="/user/home?id=100429319" class="nm nm-icn f-thide s-fc3">AmyBB</a>
                    </p>
                </li>
                <li>
                    <div class="u-cover u-cover-1">
                        <img class="j-flag"
                            src="http://p4.music.126.net/l1T2_Jt4GYWlVKFYUYJQ6Q==/3241360282550942.jpg?param=140y140">
                        <a title="同一旋律两种味道；【男女】" href="/playlist?id=140388016" class="msk"></a>
                        <div class="bottom">
                            <a class="icon-play f-fr" title="播放" href="javascript:;"
                                data-res-type="13" data-res-id="140388016"
                                data-res-action="play"></a> <span class="icon-headset"></span> <span
                                class="nb">24182</span>
                        </div>
                    </div>
                    <p class="dec">
                        <a title="同一旋律两种味道；【男女】" href="/playlist?id=140388016"
                            class="tit f-thide s-fc0">同一旋律两种味道；【男女】</a>
                    </p>
                    <p>
                        <span class="s-fc4">by</span> <a title="花大宝就是辣么萌i"
                            href="/user/home?id=68778828" class="nm nm-icn f-thide s-fc3">花大宝就是辣么萌i</a>
                    </p>
                </li>
                <li>
                    <div class="u-cover u-cover-1">
                        <img class="j-flag"
                            src="http://p4.music.126.net/NEky8TPWzQ0xIfGmoUUPbA==/3295236354996446.jpg?param=140y140">
                        <a title="【2015.10】新番十佳OP、ED" href="/playlist?id=140202912"
                            class="msk"></a>
                        <div class="bottom">
                            <a class="icon-play f-fr" title="播放" href="javascript:;"
                                data-res-type="13" data-res-id="140202912"
                                data-res-action="play"></a> <span class="icon-headset"></span> <span
                                class="nb">11291</span>
                        </div>
                    </div>
                    <p class="dec">
                        <a title="【2015.10】新番十佳OP、ED" href="/playlist?id=140202912"
                            class="tit f-thide s-fc0">【2015.10】新番十佳OP、ED</a>
                    </p>
                    <p>
                        <span class="s-fc4">by</span> <a title="西园美鸟"
                            href="/user/home?id=11602980" class="nm nm-icn f-thide s-fc3">西园美鸟</a>
                        <sup class="u-icn u-icn-84"></sup>
                    </p>
                </li>
                <li>
                    <div class="u-cover u-cover-1">
                        <img class="j-flag"
                            src="http://p3.music.126.net/oSMtzsbIsrUFLqMfreNufQ==/3297435378288974.jpg?param=140y140">
                        <a title="时隔多年依旧经典的韩剧OST" href="/playlist?id=140518802"
                            class="msk"></a>
                        <div class="bottom">
                            <a class="icon-play f-fr" title="播放" href="javascript:;"
                                data-res-type="13" data-res-id="140518802"
                                data-res-action="play"></a> <span class="icon-headset"></span> <span
                                class="nb">10562</span>
                        </div>
                    </div>
                    <p class="dec">
                        <a title="时隔多年依旧经典的韩剧OST" href="/playlist?id=140518802"
                            class="tit f-thide s-fc0">时隔多年依旧经典的韩剧OST</a>
                    </p>
                    <p>
                        <span class="s-fc4">by</span> <a title="pretty萱雅"
                            href="/user/home?id=46149738" class="nm nm-icn f-thide s-fc3">pretty萱雅</a>
                        <sup class="u-icn u-icn-84"></sup>
                    </p>
                </li>
                <li>
                    <div class="u-cover u-cover-1">
                        <img class="j-flag"
                            src="http://p3.music.126.net/LkjQ6euEQqw95xVvtF1J6w==/2542070886879539.jpg?param=140y140">
                        <a title="嘻哈挖掘机Vol.1：冷门嘻哈歌曲精选" href="/playlist?id=140444454"
                            class="msk"></a>
                        <div class="bottom">
                            <a class="icon-play f-fr" title="播放" href="javascript:;"
                                data-res-type="13" data-res-id="140444454"
                                data-res-action="play"></a> <span class="icon-headset"></span> <span
                                class="nb">8299</span>
                        </div>
                    </div>
                    <p class="dec">
                        <a title="嘻哈挖掘机Vol.1：冷门嘻哈歌曲精选" href="/playlist?id=140444454"
                            class="tit f-thide s-fc0">嘻哈挖掘机Vol.1：冷门嘻哈歌曲精选</a>
                    </p>
                    <p>
                        <span class="s-fc4">by</span> <a title="NorthSideRapper"
                            href="/user/home?id=17647877" class="nm nm-icn f-thide s-fc3">NorthSideRapper</a>
                        <sup class="u-icn u-icn-84"></sup>
                    </p>
                </li>
                <li>
                    <div class="u-cover u-cover-1">
                        <img class="j-flag"
                            src="http://p4.music.126.net/fRF6nL-6c-JdapwMDrr7LQ==/3386495815817097.jpg?param=140y140">
                        <a title="深沉而优雅的音乐贵族，大提琴。" href="/playlist?id=139365375"
                            class="msk"></a>
                        <div class="bottom">
                            <a class="icon-play f-fr" title="播放" href="javascript:;"
                                data-res-type="13" data-res-id="139365375"
                                data-res-action="play"></a> <span class="icon-headset"></span> <span
                                class="nb">21万</span>
                        </div>
                    </div>
                    <p class="dec">
                        <a title="深沉而优雅的音乐贵族，大提琴。" href="/playlist?id=139365375"
                            class="tit f-thide s-fc0">深沉而优雅的音乐贵族，大提琴。</a>
                    </p>
                    <p>
                        <span class="s-fc4">by</span> <a title="野良兔"
                            href="/user/home?id=33431226" class="nm nm-icn f-thide s-fc3">野良兔</a>
                        <sup class="u-icn u-icn-84"></sup>
                    </p>
                </li>
                <li>
                    <div class="u-cover u-cover-1">
                        <img class="j-flag"
                            src="http://p4.music.126.net/wxmOm4Y2LwS90qNrvXxUKw==/3233663700737762.jpg?param=140y140">
                        <a title="【爱上一个不可能的人是什么感觉？】" href="/playlist?id=137780248"
                            class="msk"></a>
                        <div class="bottom">
                            <a class="icon-play f-fr" title="播放" href="javascript:;"
                                data-res-type="13" data-res-id="137780248"
                                data-res-action="play"></a> <span class="icon-headset"></span> <span
                                class="nb">208万</span>
                        </div>
                    </div>
                    <p class="dec">
                        <a title="【爱上一个不可能的人是什么感觉？】" href="/playlist?id=137780248"
                            class="tit f-thide s-fc0">【爱上一个不可能的人是什么感觉？】</a>
                    </p>
                    <p>
                        <span class="s-fc4">by</span> <a title="港樂小分隊"
                            href="/user/home?id=47134209" class="nm nm-icn f-thide s-fc3">港樂小分隊</a>
                        <sup class="u-icn u-icn-84"></sup>
                    </p>
                </li>
                <li>
                    <div class="u-cover u-cover-1">
                        <img class="j-flag"
                            src="http://p4.music.126.net/hMidJS3fHC1XBoJmpQQ2sw==/3273246122381233.jpg?param=140y140">
                        <a title="啤酒邂逅音乐之华语摇滚" href="/playlist?id=138911210" class="msk"></a>
                        <div class="bottom">
                            <a class="icon-play f-fr" title="播放" href="javascript:;"
                                data-res-type="13" data-res-id="138911210"
                                data-res-action="play"></a> <span class="icon-headset"></span> <span
                                class="nb">46万</span>
                        </div>
                    </div>
                    <p class="dec">
                        <a title="啤酒邂逅音乐之华语摇滚" href="/playlist?id=138911210"
                            class="tit f-thide s-fc0">啤酒邂逅音乐之华语摇滚</a>
                    </p>
                    <p>
                        <span class="s-fc4">by</span> <a title="Jayle如风"
                            href="/user/home?id=57027929" class="nm nm-icn f-thide s-fc3">Jayle如风</a>
                        <sup class="u-icn u-icn-84"></sup>
                    </p>
                </li>
                <li>
                    <div class="u-cover u-cover-1">
                        <img class="j-flag"
                            src="http://p3.music.126.net/ga7TwLvUhbRejtpcNL37_A==/1364493954261057.jpg?param=140y140">
                        <a title="了不起的挑战BGM" href="/playlist?id=137703634" class="msk"></a>
                        <div class="bottom">
                            <a class="icon-play f-fr" title="播放" href="javascript:;"
                                data-res-type="13" data-res-id="137703634"
                                data-res-action="play"></a> <span class="icon-headset"></span> <span
                                class="nb">26万</span>
                        </div>
                    </div>
                    <p class="dec">
                        <a title="了不起的挑战BGM" href="/playlist?id=137703634"
                            class="tit f-thide s-fc0">了不起的挑战BGM</a>
                    </p>
                    <p>
                        <span class="s-fc4">by</span> <a title="别情先生"
                            href="/user/home?id=31080099" class="nm nm-icn f-thide s-fc3">别情先生</a>
                        <sup class="u-icn u-icn-84"></sup>
                    </p>
                </li>
                <li>
                    <div class="u-cover u-cover-1">
                        <img class="j-flag"
                            src="http://p4.music.126.net/Zk_sf91BF1xp-uCAnp6bFQ==/1364493954052849.jpg?param=140y140">
                        <a title="暖耳宝 • 冬日里的治愈馆" href="/playlist?id=137467192" class="msk"></a>
                        <div class="bottom">
                            <a class="icon-play f-fr" title="播放" href="javascript:;"
                                data-res-type="13" data-res-id="137467192"
                                data-res-action="play"></a> <span class="icon-headset"></span> <span
                                class="nb">147万</span>
                        </div>
                    </div>
                    <p class="dec">
                        <a title="暖耳宝 • 冬日里的治愈馆" href="/playlist?id=137467192"
                            class="tit f-thide s-fc0">暖耳宝 • 冬日里的治愈馆</a>
                    </p>
                    <p>
                        <span class="s-fc4">by</span> <a title="猫景"
                            href="/user/home?id=50951770" class="nm nm-icn f-thide s-fc3">猫景</a>
                        <sup class="u-icn u-icn-84"></sup>
                    </p>
                </li>
                <li>
                    <div class="u-cover u-cover-1">
                        <img class="j-flag"
                            src="http://p4.music.126.net/nQvLcOncdHkmg5nPT2qO1Q==/3407386538003187.jpg?param=140y140">
                        <a title="中国著名十大作词人" href="/playlist?id=137696805" class="msk"></a>
                        <div class="bottom">
                            <a class="icon-play f-fr" title="播放" href="javascript:;"
                                data-res-type="13" data-res-id="137696805"
                                data-res-action="play"></a> <span class="icon-headset"></span> <span
                                class="nb">58万</span>
                        </div>
                    </div>
                    <p class="dec">
                        <a title="中国著名十大作词人" href="/playlist?id=137696805"
                            class="tit f-thide s-fc0">中国著名十大作词人</a>
                    </p>
                    <p>
                        <span class="s-fc4">by</span> <a title="请叫我欧阳少宫"
                            href="/user/home?id=96171008" class="nm nm-icn f-thide s-fc3">请叫我欧阳少宫</a>
                    </p>
                </li>
                <li>
                    <div class="u-cover u-cover-1">
                        <img class="j-flag"
                            src="http://p3.music.126.net/niVl2EKVXsXlPTAAKleEng==/6643249256605375.jpg?param=140y140">
                        <a title="华语乐坛，一人一首沧海遗珠。" href="/playlist?id=140130911"
                            class="msk"></a>
                        <div class="bottom">
                            <a class="icon-play f-fr" title="播放" href="javascript:;"
                                data-res-type="13" data-res-id="140130911"
                                data-res-action="play"></a> <span class="icon-headset"></span> <span
                                class="nb">80万</span>
                        </div>
                    </div>
                    <p class="dec">
                        <a title="华语乐坛，一人一首沧海遗珠。" href="/playlist?id=140130911"
                            class="tit f-thide s-fc0">华语乐坛，一人一首沧海遗珠。</a>
                    </p>
                    <p>
                        <span class="s-fc4">by</span> <a title="紫川秀还是卢云"
                            href="/user/home?id=14168794" class="nm nm-icn f-thide s-fc3">紫川秀还是卢云</a>
                        <sup class="u-icn u-icn-84"></sup>
                    </p>
                </li>
                <li>
                    <div class="u-cover u-cover-1">
                        <img class="j-flag"
                            src="http://p4.music.126.net/wGxC_pcFRjQ6y5j594TyMw==/2538772351738285.jpg?param=140y140">
                        <a title="【ACG】前奏就抓住你耳朵的日漫歌曲" href="/playlist?id=139094302"
                            class="msk"></a>
                        <div class="bottom">
                            <a class="icon-play f-fr" title="播放" href="javascript:;"
                                data-res-type="13" data-res-id="139094302"
                                data-res-action="play"></a> <span class="icon-headset"></span> <span
                                class="nb">25万</span>
                        </div>
                    </div>
                    <p class="dec">
                        <a title="【ACG】前奏就抓住你耳朵的日漫歌曲" href="/playlist?id=139094302"
                            class="tit f-thide s-fc0">【ACG】前奏就抓住你耳朵的日漫歌曲</a>
                    </p>
                    <p>
                        <span class="s-fc4">by</span> <a title="西柚_"
                            href="/user/home?id=31771830" class="nm nm-icn f-thide s-fc3">西柚_</a>
                        <sup class="u-icn u-icn-84"></sup>
                    </p>
                </li>
                <li>
                    <div class="u-cover u-cover-1">
                        <img class="j-flag"
                            src="http://p4.music.126.net/iQo-nbTgLhBU3jhps7Unqw==/2532175282203441.jpg?param=140y140">
                        <a title="每支MV里面的故事会" href="/playlist?id=139564016" class="msk"></a>
                        <div class="bottom">
                            <a class="icon-play f-fr" title="播放" href="javascript:;"
                                data-res-type="13" data-res-id="139564016"
                                data-res-action="play"></a> <span class="icon-headset"></span> <span
                                class="nb">15万</span>
                        </div>
                    </div>
                    <p class="dec">
                        <a title="每支MV里面的故事会" href="/playlist?id=139564016"
                            class="tit f-thide s-fc0">每支MV里面的故事会</a>
                    </p>
                    <p>
                        <span class="s-fc4">by</span> <a title="生活委员"
                            href="/user/home?id=386162" class="nm nm-icn f-thide s-fc3">生活委员</a>
                        <sup class="u-icn u-icn-84"></sup>
                    </p>
                </li>
                <li>
                    <div class="u-cover u-cover-1">
                        <img class="j-flag"
                            src="http://p3.music.126.net/zpMVWWDizWNmXQm6OzS2OA==/3297435378181309.jpg?param=140y140">
                        <a title="【东方rap】胆敢在此饶舌！" href="/playlist?id=139567105"
                            class="msk"></a>
                        <div class="bottom">
                            <a class="icon-play f-fr" title="播放" href="javascript:;"
                                data-res-type="13" data-res-id="139567105"
                                data-res-action="play"></a> <span class="icon-headset"></span> <span
                                class="nb">49631</span>
                        </div>
                    </div>
                    <p class="dec">
                        <a title="【东方rap】胆敢在此饶舌！" href="/playlist?id=139567105"
                            class="tit f-thide s-fc0">【东方rap】胆敢在此饶舌！</a>
                    </p>
                    <p>
                        <span class="s-fc4">by</span> <a title="隔壁小英俊"
                            href="/user/home?id=75459190" class="nm nm-icn f-thide s-fc3">隔壁小英俊</a>
                    </p>
                </li>
                <li>
                    <div class="u-cover u-cover-1">
                        <img class="j-flag"
                            src="http://p4.music.126.net/aCyb-HfjYs7ISAz4A5erpQ==/3233663701041701.jpg?param=140y140">
                        <a title="英国春晚 | 2015年英国皇家文艺汇演" href="/playlist?id=139565094"
                            class="msk"></a>
                        <div class="bottom">
                            <a class="icon-play f-fr" title="播放" href="javascript:;"
                                data-res-type="13" data-res-id="139565094"
                                data-res-action="play"></a> <span class="icon-headset"></span> <span
                                class="nb">95212</span>
                        </div>
                    </div>
                    <p class="dec">
                        <a title="英国春晚 | 2015年英国皇家文艺汇演" href="/playlist?id=139565094"
                            class="tit f-thide s-fc0">英国春晚 | 2015年英国皇家文艺汇演</a>
                    </p>
                    <p>
                        <span class="s-fc4">by</span> <a title="Aisling_Wong"
                            href="/user/home?id=51818325" class="nm nm-icn f-thide s-fc3">Aisling_Wong</a>
                        <sup class="u-icn u-icn-84"></sup>
                    </p>
                </li>
                <li>
                    <div class="u-cover u-cover-1">
                        <img class="j-flag"
                            src="http://p4.music.126.net/L0218F4oe-9YrgbXp7hokA==/3285340746469233.jpg?param=140y140">
                        <a title="听民谣里的故乡结" href="/playlist?id=139106227" class="msk"></a>
                        <div class="bottom">
                            <a class="icon-play f-fr" title="播放" href="javascript:;"
                                data-res-type="13" data-res-id="139106227"
                                data-res-action="play"></a> <span class="icon-headset"></span> <span
                                class="nb">19万</span>
                        </div>
                    </div>
                    <p class="dec">
                        <a title="听民谣里的故乡结" href="/playlist?id=139106227"
                            class="tit f-thide s-fc0">听民谣里的故乡结</a>
                    </p>
                    <p>
                        <span class="s-fc4">by</span> <a title="墨_子陳"
                            href="/user/home?id=7629556" class="nm nm-icn f-thide s-fc3">墨_子陳</a>
                        <sup class="u-icn u-icn-84"></sup>
                    </p>
                </li>
                <li>
                    <div class="u-cover u-cover-1">
                        <img class="j-flag"
                            src="http://p3.music.126.net/GdTKMxAqae4MvRuWExxKqA==/1365593467192932.jpg?param=140y140">
                        <a title="【励志】彩虹之上的梦想，是心的方向" href="/playlist?id=139607248"
                            class="msk"></a>
                        <div class="bottom">
                            <a class="icon-play f-fr" title="播放" href="javascript:;"
                                data-res-type="13" data-res-id="139607248"
                                data-res-action="play"></a> <span class="icon-headset"></span> <span
                                class="nb">89597</span>
                        </div>
                    </div>
                    <p class="dec">
                        <a title="【励志】彩虹之上的梦想，是心的方向" href="/playlist?id=139607248"
                            class="tit f-thide s-fc0">【励志】彩虹之上的梦想，是心的方向</a>
                    </p>
                    <p>
                        <span class="s-fc4">by</span> <a title="Youhee呦呦"
                            href="/user/home?id=102933448" class="nm nm-icn f-thide s-fc3">Youhee呦呦</a>
                    </p>
                </li>
                <li>
                    <div class="u-cover u-cover-1">
                        <img class="j-flag"
                            src="http://p3.music.126.net/jQ1zt0LDgZdvAWyQZxSqmw==/3297435377979631.jpg?param=140y140">
                        <a title="Billboard:&nbsp;2015年度100首热门歌曲"
                            href="/playlist?id=137331864" class="msk"></a>
                        <div class="bottom">
                            <a class="icon-play f-fr" title="播放" href="javascript:;"
                                data-res-type="13" data-res-id="137331864"
                                data-res-action="play"></a> <span class="icon-headset"></span> <span
                                class="nb">162万</span>
                        </div>
                    </div>
                    <p class="dec">
                        <a title="Billboard:&nbsp;2015年度100首热门歌曲"
                            href="/playlist?id=137331864" class="tit f-thide s-fc0">Billboard:&nbsp;2015年度100首热门歌曲</a>
                    </p>
                    <p>
                        <span class="s-fc4">by</span> <a title="浮图音乐"
                            href="/user/home?id=44145137" class="nm nm-icn f-thide s-fc3">浮图音乐</a>
                        <sup class="u-icn u-icn-84"></sup>
                    </p>
                </li>
                <li>
                    <div class="u-cover u-cover-1">
                        <img class="j-flag"
                            src="http://p4.music.126.net/2TJmJ4580bts6p-7c4xQmQ==/3272146610557294.jpg?param=140y140">
                        <a title="韩国风格音乐欣赏第五期|Trot" href="/playlist?id=138746970"
                            class="msk"></a>
                        <div class="bottom">
                            <a class="icon-play f-fr" title="播放" href="javascript:;"
                                data-res-type="13" data-res-id="138746970"
                                data-res-action="play"></a> <span class="icon-headset"></span> <span
                                class="nb">75531</span>
                        </div>
                    </div>
                    <p class="dec">
                        <a title="韩国风格音乐欣赏第五期|Trot" href="/playlist?id=138746970"
                            class="tit f-thide s-fc0">韩国风格音乐欣赏第五期|Trot</a>
                    </p>
                    <p>
                        <span class="s-fc4">by</span> <a title="京阪神奈良"
                            href="/user/home?id=105605395" class="nm nm-icn f-thide s-fc3">京阪神奈良</a>
                    </p>
                </li>
                <li>
                    <div class="u-cover u-cover-1">
                        <img class="j-flag"
                            src="http://p3.music.126.net/tg7kfzrHH_YALhD-zf72cQ==/3242459794019775.jpg?param=140y140">
                        <a title="黑泡曲中的那些优质[抒情女声]" href="/playlist?id=139311955"
                            class="msk"></a>
                        <div class="bottom">
                            <a class="icon-play f-fr" title="播放" href="javascript:;"
                                data-res-type="13" data-res-id="139311955"
                                data-res-action="play"></a> <span class="icon-headset"></span> <span
                                class="nb">43711</span>
                        </div>
                    </div>
                    <p class="dec">
                        <a title="黑泡曲中的那些优质[抒情女声]" href="/playlist?id=139311955"
                            class="tit f-thide s-fc0">黑泡曲中的那些优质[抒情女声]</a>
                    </p>
                    <p>
                        <span class="s-fc4">by</span> <a title="边懵懵的腿毛"
                            href="/user/home?id=2377849" class="nm nm-icn f-thide s-fc3">边懵懵的腿毛</a>
                        <sup class="u-icn u-icn-84"></sup>
                    </p>
                </li>
                <li>
                    <div class="u-cover u-cover-1">
                        <img class="j-flag"
                            src="http://p3.music.126.net/0W4iwIBW_CHlwUN-7kQiow==/2534374305122111.jpg?param=140y140">
                        <a title="【日系】温暖心窝的暖心向日音二十五首！" href="/playlist?id=137930709"
                            class="msk"></a>
                        <div class="bottom">
                            <a class="icon-play f-fr" title="播放" href="javascript:;"
                                data-res-type="13" data-res-id="137930709"
                                data-res-action="play"></a> <span class="icon-headset"></span> <span
                                class="nb">78245</span>
                        </div>
                    </div>
                    <p class="dec">
                        <a title="【日系】温暖心窝的暖心向日音二十五首！" href="/playlist?id=137930709"
                            class="tit f-thide s-fc0">【日系】温暖心窝的暖心向日音二十五首！</a>
                    </p>
                    <p>
                        <span class="s-fc4">by</span> <a title="樱花色的枣糕"
                            href="/user/home?id=20475012" class="nm nm-icn f-thide s-fc3">樱花色的枣糕</a>
                        <sup class="u-icn u-icn-84"></sup>
                    </p>
                </li>
                <li>
                    <div class="u-cover u-cover-1">
                        <img class="j-flag"
                            src="http://p4.music.126.net/kTeNad0zEdbEAcCL9C1dzg==/3233663701114038.jpg?param=140y140">
                        <a title="[华语女声篇] 那些声音极具辨识度的歌手们" href="/playlist?id=139439232"
                            class="msk"></a>
                        <div class="bottom">
                            <a class="icon-play f-fr" title="播放" href="javascript:;"
                                data-res-type="13" data-res-id="139439232"
                                data-res-action="play"></a> <span class="icon-headset"></span> <span
                                class="nb">58万</span>
                        </div>
                    </div>
                    <p class="dec">
                        <a title="[华语女声篇] 那些声音极具辨识度的歌手们" href="/playlist?id=139439232"
                            class="tit f-thide s-fc0">[华语女声篇] 那些声音极具辨识度的歌手们</a>
                    </p>
                    <p>
                        <span class="s-fc4">by</span> <a title="布兰妮一斯皮尔斯"
                            href="/user/home?id=34885501" class="nm nm-icn f-thide s-fc3">布兰妮一斯皮尔斯</a>
                        <sup class="u-icn u-icn-84"></sup>
                    </p>
                </li>
                <li>
                    <div class="u-cover u-cover-1">
                        <img class="j-flag"
                            src="http://p4.music.126.net/-u1gXaYzzy2Gr37WKfP5Rg==/2540971375149547.jpg?param=140y140">
                        <a title="相貌与实力兼具 | 盘点90后电音小鲜肉" href="/playlist?id=138641680"
                            class="msk"></a>
                        <div class="bottom">
                            <a class="icon-play f-fr" title="播放" href="javascript:;"
                                data-res-type="13" data-res-id="138641680"
                                data-res-action="play"></a> <span class="icon-headset"></span> <span
                                class="nb">25万</span>
                        </div>
                    </div>
                    <p class="dec">
                        <a title="相貌与实力兼具 | 盘点90后电音小鲜肉" href="/playlist?id=138641680"
                            class="tit f-thide s-fc0">相貌与实力兼具 | 盘点90后电音小鲜肉</a>
                    </p>
                    <p>
                        <span class="s-fc4">by</span> <a title="welphenEDM"
                            href="/user/home?id=55535424" class="nm nm-icn f-thide s-fc3">welphenEDM</a>
                        <sup class="icn u-icn2 u-icn2-music2"></sup>
                    </p>
                </li>
                <li>
                    <div class="u-cover u-cover-1">
                        <img class="j-flag"
                            src="http://p3.music.126.net/pDC5_2zN8Ca3kqXOk8ad0g==/1364493955315746.jpg?param=140y140">
                        <a title="lol集锦背景音乐" href="/playlist?id=134834401" class="msk"></a>
                        <div class="bottom">
                            <a class="icon-play f-fr" title="播放" href="javascript:;"
                                data-res-type="13" data-res-id="134834401"
                                data-res-action="play"></a> <span class="icon-headset"></span> <span
                                class="nb">18万</span>
                        </div>
                    </div>
                    <p class="dec">
                        <a title="lol集锦背景音乐" href="/playlist?id=134834401"
                            class="tit f-thide s-fc0">lol集锦背景音乐</a>
                    </p>
                    <p>
                        <span class="s-fc4">by</span> <a title="像疯子一样嗨下去"
                            href="/user/home?id=105535579" class="nm nm-icn f-thide s-fc3">像疯子一样嗨下去</a>
                    </p>
                </li>
                <li>
                    <div class="u-cover u-cover-1">
                        <img class="j-flag"
                            src="http://p4.music.126.net/bT9peuVmx4DMPv5P-GwtcA==/2881819979687653.jpg?param=140y140">
                        <a title="Billboard后花园&nbsp;∣&nbsp;2015&nbsp;年度50佳唱片"
                            href="/playlist?id=139218367" class="msk"></a>
                        <div class="bottom">
                            <a class="icon-play f-fr" title="播放" href="javascript:;"
                                data-res-type="13" data-res-id="139218367"
                                data-res-action="play"></a> <span class="icon-headset"></span> <span
                                class="nb">73532</span>
                        </div>
                    </div>
                    <p class="dec">
                        <a title="Billboard后花园&nbsp;∣&nbsp;2015&nbsp;年度50佳唱片"
                            href="/playlist?id=139218367" class="tit f-thide s-fc0">Billboard后花园&nbsp;∣&nbsp;2015&nbsp;年度50佳唱片</a>
                    </p>
                    <p>
                        <span class="s-fc4">by</span> <a title="Billboard后花园"
                            href="/user/home?id=18973087" class="nm nm-icn f-thide s-fc3">Billboard后花园</a>
                        <sup class="u-icn u-icn-84"></sup>
                    </p>
                </li>
                <li>
                    <div class="u-cover u-cover-1">
                        <img class="j-flag"
                            src="http://p4.music.126.net/48l2DP-kce5EZIIUFHrFHA==/3275445145475918.jpg?param=140y140">
                        <a title="我看到的光芒是用黑暗点燃的" href="/playlist?id=139093676" class="msk"></a>
                        <div class="bottom">
                            <a class="icon-play f-fr" title="播放" href="javascript:;"
                                data-res-type="13" data-res-id="139093676"
                                data-res-action="play"></a> <span class="icon-headset"></span> <span
                                class="nb">74592</span>
                        </div>
                    </div>
                    <p class="dec">
                        <a title="我看到的光芒是用黑暗点燃的" href="/playlist?id=139093676"
                            class="tit f-thide s-fc0">我看到的光芒是用黑暗点燃的</a>
                    </p>
                    <p>
                        <span class="s-fc4">by</span> <a title="墨_小宝"
                            href="/user/home?id=19389416" class="nm nm-icn f-thide s-fc3">墨_小宝</a>
                        <sup class="u-icn u-icn-84"></sup>
                    </p>
                </li>
                <li>
                    <div class="u-cover u-cover-1">
                        <img class="j-flag"
                            src="http://p3.music.126.net/d_ZKG09NZsNWPhxfOrXY3w==/3297435378076899.jpg?param=140y140">
                        <a title="韩剧中的英文插曲合集" href="/playlist?id=137936733" class="msk"></a>
                        <div class="bottom">
                            <a class="icon-play f-fr" title="播放" href="javascript:;"
                                data-res-type="13" data-res-id="137936733"
                                data-res-action="play"></a> <span class="icon-headset"></span> <span
                                class="nb">21万</span>
                        </div>
                    </div>
                    <p class="dec">
                        <a title="韩剧中的英文插曲合集" href="/playlist?id=137936733"
                            class="tit f-thide s-fc0">韩剧中的英文插曲合集</a>
                    </p>
                    <p>
                        <span class="s-fc4">by</span> <a title="光咕咕autumn"
                            href="/user/home?id=51485487" class="nm nm-icn f-thide s-fc3">光咕咕autumn</a>
                        <sup class="u-icn u-icn-84"></sup>
                    </p>
                </li>
                <li>
                    <div class="u-cover u-cover-1">
                        <img class="j-flag"
                            src="http://p4.music.126.net/sNwrMXgqhRUzQV12_R3Aqw==/3272146610692046.jpg?param=140y140">
                        <a title="蜜语者丨我的生命之光，欲望之火" href="/playlist?id=140064036"
                            class="msk"></a>
                        <div class="bottom">
                            <a class="icon-play f-fr" title="播放" href="javascript:;"
                                data-res-type="13" data-res-id="140064036"
                                data-res-action="play"></a> <span class="icon-headset"></span> <span
                                class="nb">20449</span>
                        </div>
                    </div>
                    <p class="dec">
                        <a title="蜜语者丨我的生命之光，欲望之火" href="/playlist?id=140064036"
                            class="tit f-thide s-fc0">蜜语者丨我的生命之光，欲望之火</a>
                    </p>
                    <p>
                        <span class="s-fc4">by</span> <a title="音左"
                            href="/user/home?id=2747182" class="nm nm-icn f-thide s-fc3">音左</a>
                        <sup class="u-icn u-icn-84"></sup>
                    </p>
                </li>
                <li>
                    <div class="u-cover u-cover-1">
                        <img class="j-flag"
                            src="http://p3.music.126.net/5RYvHtS3r3KHkRJ2Z6qPlQ==/1364493950801889.jpg?param=140y140">
                        <a title="重遇他们的第一张专辑，惊艳若昔。" href="/playlist?id=134800274"
                            class="msk"></a>
                        <div class="bottom">
                            <a class="icon-play f-fr" title="播放" href="javascript:;"
                                data-res-type="13" data-res-id="134800274"
                                data-res-action="play"></a> <span class="icon-headset"></span> <span
                                class="nb">221万</span>
                        </div>
                    </div>
                    <p class="dec">
                        <a title="重遇他们的第一张专辑，惊艳若昔。" href="/playlist?id=134800274"
                            class="tit f-thide s-fc0">重遇他们的第一张专辑，惊艳若昔。</a>
                    </p>
                    <p>
                        <span class="s-fc4">by</span> <a title="紫川秀还是卢云"
                            href="/user/home?id=14168794" class="nm nm-icn f-thide s-fc3">紫川秀还是卢云</a>
                        <sup class="u-icn u-icn-84"></sup>
                    </p>
                </li>
                <li>
                    <div class="u-cover u-cover-1">
                        <img class="j-flag"
                            src="http://p3.music.126.net/UVj9kRS6MdYtYDYrTAUV4A==/2884019002875442.jpg?param=140y140">
                        <a title="50支欧美超神MV⭐️音乐录影带编年史" href="/playlist?id=137889366"
                            class="msk"></a>
                        <div class="bottom">
                            <a class="icon-play f-fr" title="播放" href="javascript:;"
                                data-res-type="13" data-res-id="137889366"
                                data-res-action="play"></a> <span class="icon-headset"></span> <span
                                class="nb">21万</span>
                        </div>
                    </div>
                    <p class="dec">
                        <a title="50支欧美超神MV⭐️音乐录影带编年史" href="/playlist?id=137889366"
                            class="tit f-thide s-fc0">50支欧美超神MV⭐️音乐录影带编年史</a>
                    </p>
                    <p>
                        <span class="s-fc4">by</span> <a title="大小姐不高兴"
                            href="/user/home?id=73591747" class="nm nm-icn f-thide s-fc3">大小姐不高兴</a>
                        <sup class="u-icn u-icn-84"></sup>
                    </p>
                </li>
                <li>
                    <div class="u-cover u-cover-1">
                        <img class="j-flag"
                            src="http://p3.music.126.net/6uzkEFMScWhjUdDcKdzLnw==/3276544657118871.jpg?param=140y140">
                        <a title="【治愈】歌词比我更懂你" href="/playlist?id=139239869" class="msk"></a>
                        <div class="bottom">
                            <a class="icon-play f-fr" title="播放" href="javascript:;"
                                data-res-type="13" data-res-id="139239869"
                                data-res-action="play"></a> <span class="icon-headset"></span> <span
                                class="nb">76万</span>
                        </div>
                    </div>
                    <p class="dec">
                        <a title="【治愈】歌词比我更懂你" href="/playlist?id=139239869"
                            class="tit f-thide s-fc0">【治愈】歌词比我更懂你</a>
                    </p>
                    <p>
                        <span class="s-fc4">by</span> <a title="不负-今"
                            href="/user/home?id=95944688" class="nm nm-icn f-thide s-fc3">不负-今</a>
                    </p>
                </li>
                <li>
                    <div class="u-cover u-cover-1">
                        <img class="j-flag"
                            src="http://p4.music.126.net/uSqU1F5nBP24Ww1Lxj0OIw==/3404088003123568.jpg?param=140y140">
                        <a title="造梦轻音，带着音乐去寻梦" href="/playlist?id=137848050" class="msk"></a>
                        <div class="bottom">
                            <a class="icon-play f-fr" title="播放" href="javascript:;"
                                data-res-type="13" data-res-id="137848050"
                                data-res-action="play"></a> <span class="icon-headset"></span> <span
                                class="nb">12万</span>
                        </div>
                    </div>
                    <p class="dec">
                        <a title="造梦轻音，带着音乐去寻梦" href="/playlist?id=137848050"
                            class="tit f-thide s-fc0">造梦轻音，带着音乐去寻梦</a>
                    </p>
                    <p>
                        <span class="s-fc4">by</span> <a title="纷桜"
                            href="/user/home?id=62879556" class="nm nm-icn f-thide s-fc3">纷桜</a>
                        <sup class="u-icn u-icn-84"></sup>
                    </p>
                </li>
                <li>
                    <div class="u-cover u-cover-1">
                        <img class="j-flag"
                            src="http://p4.music.126.net/sJ4xMZTfmP1U55WN8aZbOg==/3222668584444762.jpg?param=140y140">
                        <a title="乡村年终榜2015：Best&nbsp;of&nbsp;Country"
                            href="/playlist?id=137363702" class="msk"></a>
                        <div class="bottom">
                            <a class="icon-play f-fr" title="播放" href="javascript:;"
                                data-res-type="13" data-res-id="137363702"
                                data-res-action="play"></a> <span class="icon-headset"></span> <span
                                class="nb">16万</span>
                        </div>
                    </div>
                    <p class="dec">
                        <a title="乡村年终榜2015：Best&nbsp;of&nbsp;Country"
                            href="/playlist?id=137363702" class="tit f-thide s-fc0">乡村年终榜2015：Best&nbsp;of&nbsp;Country</a>
                    </p>
                    <p>
                        <span class="s-fc4">by</span> <a title="非溪"
                            href="/user/home?id=2975449" class="nm nm-icn f-thide s-fc3">非溪</a>
                        <sup class="u-icn u-icn-84"></sup>
                    </p>
                </li>
            </ul>
            <div id="m-pl-pager">
                <div class="u-page">
                    <a href="javascript:void(0)" class="zbtn zprv js-disabled">上一页</a>
                    <a href="javascript:void(0)" class="zpgi js-selected">1</a> <a
                        href="/discover/playlist/?order=hot&amp;cat=%E5%85%A8%E9%83%A8&amp;limit=35&amp;offset=35"
                        class="zpgi">2</a> <a
                        href="/discover/playlist/?order=hot&amp;cat=%E5%85%A8%E9%83%A8&amp;limit=35&amp;offset=70"
                        class="zpgi">3</a> <a
                        href="/discover/playlist/?order=hot&amp;cat=%E5%85%A8%E9%83%A8&amp;limit=35&amp;offset=105"
                        class="zpgi">4</a> <a
                        href="/discover/playlist/?order=hot&amp;cat=%E5%85%A8%E9%83%A8&amp;limit=35&amp;offset=140"
                        class="zpgi">5</a> <a
                        href="/discover/playlist/?order=hot&amp;cat=%E5%85%A8%E9%83%A8&amp;limit=35&amp;offset=175"
                        class="zpgi">6</a> <a
                        href="/discover/playlist/?order=hot&amp;cat=%E5%85%A8%E9%83%A8&amp;limit=35&amp;offset=210"
                        class="zpgi">7</a> <a
                        href="/discover/playlist/?order=hot&amp;cat=%E5%85%A8%E9%83%A8&amp;limit=35&amp;offset=245"
                        class="zpgi">8</a> <span class="zdot">...</span> <a
                        href="/discover/playlist/?order=hot&amp;cat=%E5%85%A8%E9%83%A8&amp;limit=35&amp;offset=1470"
                        class="zpgi">43</a> <a
                        href="/discover/playlist/?order=hot&amp;cat=%E5%85%A8%E9%83%A8&amp;limit=35&amp;offset=35"
                        class="zbtn znxt">下一页</a>
                </div>
            </div>
        </div>
    </div>
</body>

    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/index.js" ></script>
    <script type="text/javascript" src="js/paybutton.js" ></script>
</html>
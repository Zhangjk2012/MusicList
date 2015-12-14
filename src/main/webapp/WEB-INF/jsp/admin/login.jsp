<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh">
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>音乐榜后台登录</title>
<link rel="stylesheet" href="static/login/login.css">
</head>
<body onload="document.getElementsByTagName('input')[0].focus();">
    <div class="login_body">
    <div class="login_form clearfix">
        <form action="admin/login" method="post">
            <p><input type="text" name="username" placeholder="帐号" title="账号"/></p>
            <p><input type="password" name="password" placeholder="密码" title="密码"/></p>
            <p>
                <input type="submit" value="登  录"/>
            </p>
        </form>
    </div>
    <p class="login_msg">
        ${errorinfo}
    </p>
</div>
<script>
var _hmt = _hmt || [];
(function() {
  var hm = document.createElement("script");
  hm.src = "//hm.baidu.com/hm.js?bd81f02e5329554415de9ee15f916a98";
  var s = document.getElementsByTagName("script")[0];
  s.parentNode.insertBefore(hm, s);
})();
</script>
</body>
</html>



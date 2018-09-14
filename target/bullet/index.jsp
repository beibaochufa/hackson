<%--
  Created by IntelliJ IDEA.
  User: whh
  Date: 2018/9/13
  Time: 22:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>扫码登录</title>
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/postMan.css">
    <script src="http://rescdn.qqmail.com/node/ww/wwopenmng/js/sso/wwLogin-1.0.0.js"></script>
</head>
<body>

<div class="content">
    <img src="images/logo.png" class="icon-logo">
    <div class="qrcodebg">
        <div id="code" class="qrcode"></div>
        <h4 class="desc">登录可修改个人信息和查看快递信息</h4>
    </div>
</div>
<script>
    window.WwLogin({
        "id": "code",  //显示二维码的容器id
        "appid": "wwd160a47aadac9970",
        "agentid": "1000002",  //企业微信的cropID，在 企业微信管理端->我的企业 中查看
        "redirect_uri": "127.0.0.1%3a8080%2fjsp%2fblank.jsp",   //重定向地址，需要进行UrlEncode
        "state": "3828293919281",   //用于保持请求和回调的状态，授权请求后原样带回给企业。该参数可用于防止csrf攻击（跨站请求伪造攻击），建议企业带上该参数
        "href": ""   //自定义样式链接，企业可根据实际需求覆盖默认样式。详见文档底部FAQ
    });
</script>
</body>
</html>

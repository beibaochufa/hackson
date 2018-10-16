<%--
  Created by IntelliJ IDEA.
  User: whh
  Date: 2018/9/13
  Time: 18:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script type="text/javascript">
    </script>
    <title>扫码登录</title>
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="../css/blank.css">
    <style type="text/css">
        .content {
            height: 701px;
            background-image: url("../images/bg.jpeg");
            background-size: cover;
            background-repeat: no-repeat;
        }

        img {
            position: absolute;
            left: 50%;
            top: 50%;
            transform: translate(-50%, -50%);
            margin: 0 auto;
            width: 100px;
            height: 100px;
        }
    </style>
</head>
<body>

<div class="content">
    <div class="qrcodebg">
        <img src="../images/loading.jpg">
    </div>
</div>
<script src="../easyui/jquery-3.3.1.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
</body>
</html>

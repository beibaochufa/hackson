<%--
  Created by IntelliJ IDEA.
  User: whh
  Date: 2018/9/14
  Time: 07:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8">
    <title>快递录入系统</title>
    <link rel="icon" href="../image/post-man.png" sizes="32x32">
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/postItem.css">

</head>
<body>
<div class="content">
    <div class="container">
        <div class="info">
            <form class="form-signin">
                <h2 class="form-signin-heading">快递信息录入</h2>
                <div class="form-group">
                    <div>
                        <input type="text" name="username" id="username" class="form-control" autofocus placeholder="请输入姓名" value="">
                        <span class="help-block"></span>
                    </div>
                </div>
                <div class="form-group">
                    <div>
                        <input type="text" name="phone" id="phone" class="form-control " placeholder="手机号" value="">
                        <span class="help-block"></span>
                    </div>
                </div>
                <div class="form-group">
                    <div>
                        <input type="text" name="postnumber" id="postnumber" class="form-control" autofocus placeholder="快递单号(可不填)" value="">
                        <span class="help-block"></span>
                    </div>
                </div>

                <div class="btn-group btn-group-justified" role="group" >
                    <div class="btn-group" role="group">
                        <button type="submit" class="btn btn-primary">发送</button>
                    </div>
                    <div class="btn-group" role="group">
                        <button type="button" onclick="window.location.href='main.jsp'" class="btn btn-primary">统计</button>
                    </div>
                </div>
                <p id="tip"></p>
            </form>
        </div>
    </div>
</div>
<script src="../easyui/jquery.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
<script src="../js/post.js"></script>

</body>
</html>
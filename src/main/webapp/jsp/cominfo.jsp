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
    <title>提交用户信息</title>
    <link rel="icon" href="../image/post-man.png" sizes="32x32">
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/postItem.css">

</head>
<body>
<div class="content">
    <div class="container">
        <div class="info">
            <form class="form-signin">
                <h2 class="form-signin-heading">提交用户信息</h2>
                <div class="form-group">
                    <div>
                        <input type="text" name="username" id="username" class="form-control" autofocus
                               placeholder="请输入姓名" value="">
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
                        <input type="submit" class="btn btn-primary btn-lg btn-block" value="发送">
                        <p id="tip"></p>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<script src="../easyui/jquery.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
<script src="../js/post.js"></script>

</body>
</html>

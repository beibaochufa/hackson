<%--
  Created by IntelliJ IDEA.
  User: whh
  Date: 2018/9/14
  Time: 07:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8">
    <title>快递库</title>
    <link rel="icon" href="../images/ic_kucun.png" sizes="32x32">
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/card.css">
</head>
<body>

<div class="add">
    <button class="btn btn-info" onclick="window.location.href='post.jsp'">+ 添加</button>
</div>
<div class="panel panel-default">
    <!-- Default panel contents -->
    <div class="panel-heading text-center"><h4>快递统计 </h4></div>
    <table class="table">
        <thead>
        <tr>
            <th>姓名</th>
            <th>手机号</th>
            <th>快递单号</th>
            <th>送达时间</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody id="data">
        </tbody>
    </table>
</div>
<script src="../easyui/jquery.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
<script src="../js/card.js"></script>

</body>
</html>

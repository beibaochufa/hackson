<%@ page import="hackson.model.AdminUserModel" %><%--
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
    <%
        // 权限验证
        int uid = 0;
        if (session.getAttribute("currentUser") == null) {
            response.sendRedirect("login.jsp");
            return;
        } else {
            AdminUserModel AdminUserModel = (AdminUserModel) session.getAttribute("currentUser");
            uid = AdminUserModel.getUid();
        }
    %>
    <link rel="icon" href="../images/ic_kucun.png" sizes="32x32">
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/card.css">
    <script type="text/javascript">
        //发送消息
        function sendMessage(phone) {
            $.ajax({
                url: "/order/send_message",
                type: "GET",
                async: false,
                dataType: 'json',
                data: {phone: phone},//参数之间用“,” 逗号隔开。
                error: function () {
                },
                success: function (data) {
                    alert(data.msg);
                }
            });
        }

        //处理状态
        function handleOperation(orderId) {
            $.ajax({
                url: "/order/handle_accepted",
                type: "GET",
                async: false,
                dataType: 'json',
                data: {orderId: orderId, operation: 1},//参数之间用“,” 逗号隔开。
                error: function () {
                },
                success: function (data) {
                    alert(data.msg);
                    location.reload();//刷新页面
                }
            });
        }
    </script>
</head>
<body>

<div class="add">
    <button class="btn btn-info" onclick="JavaScript:window.location.href='post.jsp'">+ 添加</button>
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
<script type="text/javascript">
    $(document).ready(function () {
        getAllPosts();//进来此页面先进行数据刷新

        function getAllPosts() {
            $.ajax({
                url: "/order/getOrderList",
                type: "GET",
                async: false,
                dataType: 'json',
                data: {isAdmin: true},//参数之间用“,” 逗号隔开。
                error: function () {
                },
                success: function (data) {
                    //map 对 data 的每一项执行 map 里的函数
                    data.map(createTableTrTd);
                }
            });
        }

        function createTableTrTd(data) {
            var $tr = $('<tr></tr>');
            var $tdName = $('<td></td>');
            var $tdPhone = $('<td></td>');
            var $tdPostNumber = $('<td></td>');
            var $tdTime = $('<td></td>');
            var $operate = $('<td></td>');
            $tdName.html(data.userName);
            $tdPhone.html(data.phone);
            $tdPostNumber.html(data.postNumber);
            $tdTime.html(data.reachTime);
            var html = '<input type="radio" id="reSend" name="operation" onclick="sendMessage(' + data.phone + ')" value="reSend" /> 重发 ' +
                '<input type="radio" id="Accepted" name="operation" onclick="handleOperation(' + data.orderId + ')" value="accepted" />接收';
            $operate.html(html);
            $tr.append($tdName);
            $tr.append($tdPhone);
            $tr.append($tdPostNumber);
            $tr.append($tdTime);
            $tr.append($operate);
            $('#data').append($tr);
            return $tr;
        }
    });
</script>

</body>
</html>

<%@ page import="hackson.model.CommonUserModel" %><%--
  Created by IntelliJ IDEA.
  User: whh
  Date: 2018/9/14
  Time: 07:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8">
    <title>我的快递</title>
    <script type="text/javascript">
        <%
       // 登录验证
       String userId;
       String userName;
       String phone;
       if (session.getAttribute("currentUser") == null) {
           response.sendRedirect("../index.jsp");
           return;
       } else {
           CommonUserModel user = (CommonUserModel) session.getAttribute("currentUser");
           userId = user.getUserId();
           userName = user.getUsername();
           phone = user.getPhone();
       }
   %>
    </script>
    <link rel="icon" href="../image/ic_my_post.png" sizes="32x32">
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/myPost.css">
    <script type="text/javascript">
        function operate(orderId, operation) {
            $.ajax({
                url: "/order/handle_accepted",
                type: "GET",
                async: false,
                dataType: 'json',
                data: {orderId: orderId, operation: operation},//参数之间用“,” 逗号隔开。
                error: function () {
                },
                success: function (data) {
                    alert(data.msg);
                    location.reload();//刷新页面
                }
            });
        }

        function changeStatus(curStatus, orderId) {
            if (curStatus == "未领取") {
                operate(orderId, 1);//1：已接收
            } else {
                operate(orderId, 0);//0:未接收
            }
        }

        function jumpComInfo() {
            <%
            session.setAttribute("userId",userId);
            %>
            JavaScript:window.location.href = "cominfo.jsp";
        }
    </script>
</head>
<body>
<h3 class="text-center">我的快递</h3>
<div class="text-right"><a onclick="jumpComInfo()" class="btn-primary">修改个人信息</a></div>
<div id="data" class="data"></div>
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
                    data: {
                        isAdmin: false, userId: "<%=userId%>", userName: "<%=userName%>", phone: "<%=phone%>"
                    },//参数之间用“,” 逗号隔开。
                    error: function () {
                    }

                    ,
                    success: function (data) {
                        //map 对 data 的每一项执行 map 里的函数
                        data.map(createCard);
                    }
                }
            );
        }

        function createCard(order) {
            var $panel = $('<div class="panel panel-info"></div>');
            var $head = $('<div class="panel-heading"></div>');
            var $body = $('<div class="panel-body"></div>');
            var $orderid = $('<p></p>');
            var $time = $('<p></p>');
            var curStatus = (order.status == 0) ? "未领取" : "已领取";
            var $btn = $('<button id="status" type="button" onclick="changeStatus(\'' + curStatus + '\',' + order.orderId + ')" class="btn btn-primary"></button>');
            var $btngroup = $('<div class="btn-group" role="group" ></div>');
            var $btngroupjusty = $('<div class="btn-group btn-group-justified" role="group"></div>');
            $head.html(order.orderId);
            $orderid.html('快递单号:' + order.postNumber);
            $time.html('送达时间:' + order.reachTime);
            $btn.html(curStatus);
            $btngroup.append($btn);
            $btngroupjusty.append($btngroup);
            $body.append($orderid);
            $body.append($time);
            $body.append($btngroupjusty);
            $panel.append($head);
            $panel.append($body);
            $('#data').append($panel);
            return $panel;
        }

    });
</script>

</body>
</html>

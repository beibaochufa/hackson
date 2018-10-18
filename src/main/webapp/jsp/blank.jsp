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
    <%
        String code = request.getParameter("code");
        String state = request.getParameter("state");
        if (null == code) {
            response.sendRedirect("../index.jsp");
            return;
        }
    %>
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/blank.css">
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
<script src="../easyui/jquery.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        getAllPosts();//进来此页面先进行数据刷新

        function getAllPosts() {
            $.ajax({
                url: "/scancode/route",
                type: "GET",
                async: false,
                dataType: 'json',
                data: {code: "<%=code%>", state: "<%=state%>"},//参数之间用“,” 逗号隔开。
                error: function () {
                },
                success: function (data) {
                    if (null === data)
                        return;
                    //map 对 data 的每一项执行 map 里的函数
                    if (data.success === 200) {
                        JavaScript:window.location.href = data.msg;
                    } else {
                        alert(data.msg);
                    }
                }
            });
        }
    });
</script>
</body>
</html>

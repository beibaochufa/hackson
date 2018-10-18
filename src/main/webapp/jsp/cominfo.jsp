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
    <%
        String userId;
        if (null == session.getAttribute("userId")) {
            response.sendRedirect("../index.jsp");
            return;
        } else {
            userId = (String) session.getAttribute("userId");
        }
    %>
    <link rel="icon" href="../image/post-man.png" sizes="32x32">
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/postItem.css">
    <script type="text/javascript">
        //todo 增加验证手机号的校验，必须 11 位
        function submitInfo() {
            $("div.form-group").removeClass("has-error");
            $("span.help-block").text("");
            if ($.trim($("#username").val()) == "") {
                $("#username").parent().parent().addClass("has-error");
                $("#username").val("");
                $("#username").next().text("请输入姓名");
                return false;
            }
            if ($.trim($("#phone").val()) == "") {
                $("#phone").parent().parent().addClass("has-error");
                $("#phone").val("");
                $("#phone").next().text("请输入手机号");
                return false;
            }
            if ($.trim($("#phone").val()) != "" && $.trim($("#username").val()) != "") {
                $.ajax({
                    url: "/scancode/add_user",
                    data: {userId: "<%=userId%>", userName: $("#username").val(), phone: $("#phone").val()},
                    async: false,
                    method: "POST",
                    dataType: 'json',
                    success: function (data) {
                        if (null === data)
                            return;
                        if (data.success === 200) {
                            alert("更新成功！");
                            JavaScript:window.location.href = data.msg;//有时会失效
                        } else {
                            alert(data.msg);
                        }
                    }
                });
            }
        }
    </script>

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
                        <input onclick="submitInfo()" class="btn btn-primary btn-lg btn-block" value="提交">
                        <p id="tip"></p>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<script src="../easyui/jquery.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
<script type="text/javascript">
    <%--$(document).ready(function () {--%>
    <%--//todo 增加验证手机号的校验，必须 11 位--%>
    <%--$(".form-signin").submit(function () {--%>
    <%--$("div.form-group").removeClass("has-error");--%>
    <%--$("span.help-block").text("");--%>
    <%--if ($.trim($("#username").val()) == "") {--%>
    <%--$("#username").parent().parent().addClass("has-error");--%>
    <%--$("#username").val("");--%>
    <%--$("#username").next().text("请输入姓名");--%>
    <%--return false;--%>
    <%--}--%>
    <%--if ($.trim($("#phone").val()) == "") {--%>
    <%--$("#phone").parent().parent().addClass("has-error");--%>
    <%--$("#phone").val("");--%>
    <%--$("#phone").next().text("请输入手机号");--%>
    <%--return false;--%>
    <%--}--%>
    <%--if ($.trim($("#phone").val()) != "" && $.trim($("#username").val()) != "") {--%>
    <%--$.ajax({--%>
    <%--url: "/scancode/add_user",--%>
    <%--data: {userId: "<%=userId%>", userName: $("#username").val(), phone: $("#phone").val()},--%>
    <%--async: false,--%>
    <%--method: "POST",--%>
    <%--dataType: 'json',--%>
    <%--success: function (data) {--%>
    <%--if (null === data)--%>
    <%--return;--%>
    <%--if (data.success === 200) {--%>
    <%--window.location.href = "mypost.jsp";--%>
    <%--} else {--%>
    <%--alert(data.msg);--%>
    <%--}--%>
    <%--}--%>
    <%--});--%>
    <%--}--%>
    <%--});--%>
    <%--});--%>
</script>

</body>
</html>

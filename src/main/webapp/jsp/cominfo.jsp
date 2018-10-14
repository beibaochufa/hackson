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
                        <%
                            String userId = (String) session.getAttribute("userId");
                        %>
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
<script type="text/javascript">
    $(document).ready(function () {
        $(".form-signin").submit(function () {
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
                    url: "/add_user",
                    <%--data: {userId:<%=userId%>, username: $("#username").val(), phone: $("#phone").val()},--%>
                    data: {userId:"wanghuanhuan", username: $("#username").val(), phone: $("#phone").val()},
                    async: true,
                    method: "POST",
                    dataType: 'json',
                    success: function (result) {
                        alert(result.msg);
                    },
                    error: {}
                });
            }
        });
    });
</script>

</body>
</html>

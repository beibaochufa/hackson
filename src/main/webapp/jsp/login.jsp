<%--
  Created by IntelliJ IDEA.
  User: whh
  Date: 2018/9/13
  Time: 22:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>邮件收发系统登录</title>
    <link rel="stylesheet" type="text/css" href="../easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="../easyui/themes/icon.css">
    <script type="text/javascript" src="../easyui/jquery.min.js"></script>
    <script type="text/javascript" src="../easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="../easyui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="../easyui/common.js"></script>
    <script type="text/javascript">

        function resetValue() {
            location.reload();//刷新页面
//            $("#userName").value = "";
//            $("#password").value = "";
        }

        $(function () {
            $('#password').textbox('textbox').keydown(function (e) {
                if (e.keyCode == 13) {
                    login();
                }
            });
        });

        function login() {
            var uname = $("#userName").textbox("getValue");
            var pword = $("#password").textbox("getValue");
            if (uname == "" || pword == "") {
                alert("请输入账号和密码");
                return;
            }
            $("#fm").form("submit", {
                onSubmit: function () {
                    return $(this).form("validate");
                },
                success: function (data) {
                    var result = JSON.parse(data);
                    if (result.success === 200) {
                        JavaScript:window.location.href = result.msg;
                    } else {
                        alert(result.msg);
                    }
                }
            });
        }

    </script>
</head>
<body>
<div align="center" style="padding-top: 50px;">
    <form id="fm" action="/login/login" method="post">
        <table width="740" height="500" background="../images/login.jpg">
            <tr height="180">
                <td colspan="4"></td>
            </tr>
            <tr height="10">
                <td width="40%"></td>
                <td width="10%">用户名：</td>
                <td>
                    <input type="text" value="" class="easyui-textbox" name="userName" id="userName" required="true"/>
                </td>
                <td width="30%"></td>
            </tr>
            <tr height="10">
                <td width="40%"></td>
                <td width="10%">密 码：</td>
                <td>
                    <input type="password" value="" class="easyui-textbox" name="password" id="password"
                           required="true"/>
                </td>
                <td width="30%"></td>
            </tr>
            <tr height="10">
                <td width="40%"></td>
                <td width="10%"><a href="javascript:login()" class="easyui-linkbutton" iconCls="icon-ok">登录</a></td>
                <td>
                    <a href="javascript:resetValue()" class="easyui-linkbutton" iconCls="icon-remove">重置</a>
                </td>
                <td width="30%"></td>
            </tr>
            <tr>
                <td colspan="4"></td>
            </tr>
            <tr>
                <td colspan="4"></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
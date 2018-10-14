$(document).ready(function () {
    $(".form-signin").submit(function () {
        var flag = false;
        $("div.form-group").removeClass("has-error");
        $("span.help-block").text("");
        if ($.trim($("#username").val()) == "") {
            $("#username").parent().parent().addClass("has-error");
            $("#username").val("");
            $("#username").next().text("请输入收件人姓名");
            return false;
        }
        if ($.trim($("#phone").val()) == "") {
            $("#phone").parent().parent().addClass("has-error");
            $("#phone").val("");
            $("#phone").next().text("请输入收件人手机号");
            return false;
        }
        if ($.trim($("#phone").val()) != "" && $.trim($("#username").val()) != "") {
            $.ajax({
                url: "/scancode/add_user",
                type: "POST",
                async: true,
                dataType: 'json',
                data: {username: $("#username").val(), phone: $("#phone").val(), postnumber: $("#postnumber").val()},//参数之间用“,” 逗号隔开。
                error: function () {
                },
                success: function (data) {
                    alert(data)
                    // var result = eval("(" + data + ")");
                    // if (result.success == 200) {
                    //     window.location.href = result.msg;
                    // } else {
                    //     alert(result.msg);
                    //     $.messager.alert("系统提示", result.msg);
                    // }
                }
            });
        }
    });
});
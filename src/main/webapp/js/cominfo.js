$(document).ready(function () {
    $(".form-signin").submit(function () {
        var flag = false;
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
                url: "/scan_code/add_user",
                data: {username: $("#username").val(), phone: $("#phone").val()},
                type: "post",
                dataType: 'json',
                success: function (result) {
                    alert(result.msg);
                }
            });
        }
    });
});
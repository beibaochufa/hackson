<%--
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
    <link rel="icon" href="../image/ic_my_post.png" sizes="32x32">
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/myPost.css">
</head>
<body>
<h3 class="text-center">我的快递</h3>
<div class="text-right"><a href="cominfo.jsp" class="btn-primary">修改个人信息</a></div>
<div id="data" class="data"></div>
<script src="../easyui/jquery-3.3.1.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        var data = {
            "doctorList": [{
                "doctorId": "d001",
                "doctorImg": "http://img.dongqiudi.com/uploads/avatar/2014/10/20/8MCTb0WBFG_thumb_1413805282863.jpg",
                "doctorName": "潘仁和",
                "doctorDevlement": "骨科",
                "hospitalName": "上海华山医院"
            }, {
                "doctorId": "d002",
                "doctorImg": "http://cdn.duitang.com/uploads/item/201410/05/20141005160337_RZX8W.thumb.224_0.jpeg",
                "doctorName": "李丽",
                "doctorDevlement": "儿科",
                "hospitalName": "上海中山医院"
            }, {
                "doctorId": "d003",
                "doctorImg": "http://diy.qqjay.com/u2/2012/0924/7032b10ffcdfc9b096ac46bde0d2925b.jpg",
                "doctorName": "陆子林",
                "doctorDevlement": "儿科",
                "hospitalName": "上海中山医院"
            }]
        };
        data.doctorList.map(createCard);

        function createCard(order) {
            var $panel = $('<div class="panel panel-info"></div>');
            var $head = $('<div class="panel-heading"></div>');
            var $body = $('<div class="panel-body"></div>');
            var $orderid = $('<p></p>');
            var $time = $('<p></p>');
            var $btn = $('<button type="button" class="btn btn-primary">已领取</button>');
            var $btngroup = $('<div class="btn-group" role="group"></div>');
            var $btngroupjusty = $('<div class="btn-group btn-group-justified" role="group"></div>');
            $head.html(order.doctorId);
            $orderid.html('快递单号:' + order.doctorName);
            $time.html('送达时间:' + order.doctorDevlement);
            $btngroup.append($btn);
            $btngroupjusty.append($btngroup);
            $body.append($orderid);
            $body.append($time);
            $body.append($btngroupjusty);
            $panel.append($head);
            $panel.append($body);
            $('#data').append($panel)
            return $panel;
        }
    });
</script>

</body>
</html>

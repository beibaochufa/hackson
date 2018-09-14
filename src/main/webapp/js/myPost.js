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
    }
    data.doctorList.map(order => createCard(order)).forEach(panel => $('#data').append(panel));

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
        return $panel;
    }
});
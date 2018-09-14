$(document).ready(function () {
    $.ajax({
        url: "/order/getOrderList",
        type: "GET",
        async: true,
        dataType: 'json',
        data: {isAdmin: true},//参数之间用“,” 逗号隔开。
        error: function () {
        },
        success: function (data) {
                data.map(order => createTableTrTd(order)).forEach(panel => $('#data').append(panel));
        }
    });

    function createTableTrTd(doctor) {
        var $tr = $('<tr></tr>');
        var $tdName = $('<td></td>');
        var $tdPhone = $('<td></td>');
        var $tdOrderId = $('<td></td>');
        var $tdTime = $('<td></td>');
        var $operate = $('<td></td>');
        $tdName.html(doctor.userName);
        $tdPhone.html(doctor.phone);
        $tdOrderId.html(doctor.orderNumber);
        $tdTime.html(doctor.reachTime);
        $tr.append($tdName);
        $tr.append($tdPhone);
        $tr.append($tdOrderId);
        $tr.append($tdTime);
        $tr.append($operate);
        return $tr;
    }
});
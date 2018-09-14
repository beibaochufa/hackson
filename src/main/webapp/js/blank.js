// $(document).ready(function () {
//     function GetRequest() {
//         var url = location.search; //获取url中"?"符后的字串
//         var theRequest = new Object();
//         if (url.indexOf("?") != -1) {
//             var str = url.substr(1);
//             strs = str.split("&");
//             for (var i = 0; i < strs.length; i++) {
//                 theRequest[strs[i].split("=")[0]] = unescape(strs[i].split("=")[1]);
//             }
//         }
//         return theRequest;
//     }
//
//     var Request = new Object();
//     Request = GetRequest();
//     var code, status;
//     code = Request['code'];
//     status = Request['status'];
//
//     $.ajax({
//         type: "GET",
//         url: "/jump",
//         async: true,
//         dataType: 'json',
//         data: {code: code, status: status}, //参数之间用“,” 逗号隔开。
//         error: function () {
//
//         },
//         success: function (data) {
//             var result = eval("(" + data + ")");
//             if (result.success == 200) {
//                 window.location.href = result.msg;
//             } else {
//                 alert(result.msg);
//                 $.messager.alert("系统提示", result.msg);
//             }
//         }
//     });
// });
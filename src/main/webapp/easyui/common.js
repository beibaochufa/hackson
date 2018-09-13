//datagrid自适应分辨率
function getWidth(percent) {
	return document.body.clientWidth * percent;
}

function getHeight(percent) {
	return document.body.clientHeight * percent;
}
//在对象obj上显示msg内容的遮罩层
function showloading(obj, msg) {
	$("div .panel-tool-close").click( function () { hideloading(obj); });
	$("<div class=\"datagrid-mask\"></div>").css( {
		display : "block",
		width : '100%',
		height : '100%'
	}).appendTo(obj);
	$("<div class=\"datagrid-mask-msg\"></div>").html(msg).appendTo(obj).css( {
		display : "block",
		left : "35%",
		top : "35%"
	});
}
//移除遮罩层
function hideloading(obj) {
	obj.find("div.datagrid-mask-msg").remove();
	obj.find("div.datagrid-mask").remove();
}
//格式化数字到几位小数
function formatFloat(src, pos){
    return Math.round(src*Math.pow(10, pos))/Math.pow(10, pos);
}
Date.prototype.Format = function (fmt) {
	var o = {
		"M+": this.getMonth() + 1, //月份
		"d+": this.getDate(), //日
		"H+": this.getHours(), //小时
		"m+": this.getMinutes(), //分
		"s+": this.getSeconds(), //秒
		"q+": Math.floor((this.getMonth() + 3) / 3), //季度
		"S": this.getMilliseconds() //毫秒
	};
	if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	for (var k in o)
		if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
	return fmt;
}
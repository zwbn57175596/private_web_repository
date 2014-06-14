// JavaScript Document
var temp = 0;
$(document).ready(function(e) {
    var $qh = $("#qr_float").prev();
	temp = $qh.position().top + $qh.height();
	console.log(temp);
});

$(window).on("scroll", function(e){
	// header 
	var offset = $(window).scrollTop();
	console.log("offset is" + offset);
	if (offset > 57) {
		$("#header").addClass("header_float");
		$(".main").css("margin-top", "150px");
	} else {
		$("#header").removeClass("header_float");
		$(".main").css("margin-top", "40px");
	}
	
	if (offset >= temp) {
		$("#qr_float").addClass("qrcode");
	} else {
		$("#qr_float").removeClass("qrcode");
	}
});
// JavaScript Document
var temp = 0;
$(document).ready(function(e) {
	temp = $("#qr_float").offset().top;
});

$(window).on("scroll", function(e){
	// header 
	var offset = $(window).scrollTop();
	if (offset > 57) {
		$("#header").addClass("header_float");
		$(".main").css("margin-top", "207px");
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
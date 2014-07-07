// JavaScript Document
var temp = 0;
$(document).ready(function(e) {
	setTimeout(function(){
		temp = $("#qr_float").offset().top - 207;
		console.log(temp); 
	}, 100)
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
	// qrcode 
	if (offset >= temp) {
		$("#qr_float").addClass("qrcode");
	} else {
		$("#qr_float").removeClass("qrcode");
	}
});
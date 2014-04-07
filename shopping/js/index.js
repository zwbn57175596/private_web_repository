// JavaScript Document
// multinavbar
$(function(){
	$(".multinav_bar li").each(function (index, element) {
        $(this).on("mouseout", function (e){
			$(this).removeClass("selected");
			$(".multinav_tips .tip:eq(" + index + ")").hide();
		});
		$(this).on("mouseover", function (e){
			var obj=$(this).offset(); 
			var xobj=obj.left + 160 + "px"; 
			var yobj=obj.top - 1 + "px"; 
			$(this).addClass("selected");
			$(".multinav_tips .tip:eq(" + index + ")").css({"left":xobj,"top":yobj}).show();
		});
    });
});

// newsmodule_label event
$(function(){
	$(".newsmodule_label li").each(function(index, element) {
        $(this).on("mouseover", function (e){
			$(this).siblings().removeClass("selected");
			$(this).addClass("selected");
			$(".newsmodule_content").fadeOut(100);	
			$(".newsmodule_content:eq(" + index + ")").fadeIn(100);
		});
    });
	
});

// newsmodule_content li initial
function nwscli_init(index) {
	$(".newsmodule_content:eq(" + index + ") li").each(function(index, element) {
        $(this).css("background", "url(images/li_" + (index + 1) + ".png) no-repeat left center");
    });
}


$(document).ready(function(e) {
    nwscli_init(0);
	$(".newsmodule_content:eq(0)").fadeIn(100);
});
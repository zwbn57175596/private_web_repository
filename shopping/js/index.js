// JavaScript Document

$(function(){
	// city_list
	window.showCityList = function () {
		$(".city_panel").fadeIn(100);
	}
	
	$(".city_panel").on("mouseleave", function(e){
		$(".city_panel").fadeOut(100);	
	});
	// search type
	$(".search_shops").on("click", function(e){
		e.stopPropagation();
		$(".search_list").fadeIn(100);	
	});
	$(document).on("click", function(e){
		$(".search_list").fadeOut(100);
	});
	
	// navbar
	$(".nav_bar li").each(function(index, element) {
        $(this).on("click", this, function (e) {
			$(this).siblings().removeClass("selected");
			$(this).addClass("selected");	
		});
    });

	// multinavbar
	function clearSelected() {
		$(".multinav_bar li").removeClass("selected");
		$(".multinav_tips .tip").hide();
	}
	
	// $(".multinav_bar").on("mouseleave", clearSelected);
	$(".multinav_bar li").each(function (index, element) {
		$(this).on("mouseover", function (e){
			var obj=$(this).offset(); 
			var xobj=obj.left + 168 + "px"; 
			var yobj=obj.top - 1 + "px"; 
			clearSelected();
			$(this).addClass("selected");
			$(".multinav_tips .tip:eq(" + index + ")").css({"left":xobj,"top":yobj}).show();
		});
    });
	$(".multinav_tips .tip").each(function(index, element) {
        $(this).on("mouseleave", clearSelected);
    });
	
	// newsmodule_label event
	$(".newsmodule_label li").each(function(index, element) {
        $(this).on("mouseover", function (e){
			$(this).siblings().removeClass("selected");
			$(this).addClass("selected");
			$(".newsmodule_content").hide(100);	
			setTimeout(function (){ $(".newsmodule_content:eq(" + index + ")").show(100);}, 100);
			nwscli_init(index);
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
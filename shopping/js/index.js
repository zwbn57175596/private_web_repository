// JavaScript Document

$(function(){
	// city_list
	window.showCityList = function () {
		$(".city_panel").fadeIn(100);
	}
	$(".city_panel").on("mouseleave", function(e){
		$(".city_panel").fadeOut(100);	
	}).on("mouseover", function(e){
		e.stopPropagation();
	});
	$(".top").on("mouseleave", function(e){
		$(".city_panel").fadeOut(100);
	});
	// search type
	$(".search_shops").on("click", function(e){
		e.stopPropagation();
		$(".search_list").fadeIn(100);	
	});
	$(".search_list").on("mouseleave", function(e){
		$(".search_list").fadeOut(100);
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
	
	$(".multinav_bar").on("mouseleave", function( e ){
		var mx = e.pageX;
		var my = e.pageY;
		var mb_left = $( this ).offset().left;
		var mb_top = $( this ).offset().top;
		var mb_bottom = $( this ).offset().top + $( this ).height();
		if ( mx <= mb_left || my <= mb_top || my >= mb_bottom){
			clearSelected();
		}
	});
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
	
	// quick_search select
	$(".quick_search .select_type").on("click", function (e){
		$(".quick_search_type").fadeIn(100);
		e.stopPropagation();
	});
		
	$(".quick_search .select_subtype").on("click", function (e){
		$(".quick_search_subpanel").fadeOut(100);
		$(".quick_search_subtype").fadeIn(100);
		e.stopPropagation();
	});
	$(".quick_search .select_circle").on("click", function (e){
		$(".quick_search_subpanel").fadeOut(100);
		$(".quick_search_circle").fadeIn(100);
		e.stopPropagation();
	});
	
	$(".quick_search_subpanel").on("mouseleave", function(e){
		// $(".quick_search_subpanel").fadeOut(100);
	});
	
	$(document).on("click", function(e){
		$(".quick_search_type").fadeOut(100);
		$(".quick_search_subpanel").fadeOut(100);
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
	
	// scroller shops list
	var scroller = $("#scroller_container");
	var sc_l = $("#scroller_l");
	$("#scroller_r").html(sc_l.html());
	function doScroll(){
			if (scroller.scrollLeft() >= sc_l.width()) {
				scroller.scrollLeft(0);
			} else {
				scroller.scrollLeft(scroller.scrollLeft() + 1);
			}
	}
	var myScroll=setInterval(doScroll,30);
	scroller.on("mouseover", function(e){clearInterval(myScroll);});
	scroller.on("mouseout", function(e){myScroll = setInterval(doScroll, 30);});
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
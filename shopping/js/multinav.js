// JavaScript Document
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
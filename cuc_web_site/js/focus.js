var time = "";

var index = 1;

$(function() {

	showimg(index);

	// 鼠标移入移出

	$(".imgnum span").hover(
			function() {

				clearTimeout(time);

				var icon = $(this).text();

				$(".imgnum span").removeClass("onselect").eq(icon - 1)
						.addClass("onselect");

				$("#banner_img li").hide().stop(true, true).eq(icon - 1)
						.fadeIn("slow");

			}, function() {

				index = $(this).text() > 3 ? 1 : parseInt($(this).text()) + 1;

				time = setTimeout("showimg(" + index + ")", 3000);

			});

});

function showimg(num) {

	index = num;
	$(".imgnum span").removeClass("onselect").eq(index - 1)
			.addClass("onselect");

	$("#banner_img li").hide().stop(true, true).eq(index - 1).fadeIn("slow");

	index = index + 1 > 3 ? 1 : index + 1;

	time = setTimeout("showimg(" + index + ")", 3000);

}

$(document).ready(function(){
	$(".table_border tr").mouseover(function(){$(this).addClass("over");}).mouseout(function(){$(this).removeClass("over");})
	$(".table_border tr:even").addClass("alt");
});
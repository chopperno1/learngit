function alertDeleteNoteWindow(){
	//弹出删除对话框
	$("#can").load("alert/alert_delete_note.html");
	//显示背景色
	$(".opacity_bg").show();
}
function closeAlertWindow(){
		//清空div
		$("#can").html("");
		//隐藏背景色
		$(".opacity_bg").hide();
}
function alertAddBookWindow(){
	//弹出对话框
	$("#can").load("alert/alert_notebook.html");
	//显示背景色
	$(".opacity_bg").show();
}
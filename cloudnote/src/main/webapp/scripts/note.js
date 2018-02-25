function deleteNoteAction(){
					//获取参数
					var $li=$("#note_ul a.checked").parent();
					var noteId=$li.data("noteId");
					console.log(noteId);
					//发送请求
					$.ajax({
						url:path+"/note/delete.do",
						type:"post",
						data:{"noteId":noteId},
						dataType:"json",
						success:function(result){
							if(result.state==0){
								$li.remove();
								alert("笔记删除成功");
							}
						},
						error:function(){
							alert("删除笔记失败");
						}
					});
				}
function showNoteMenu(){
	//绑定下拉菜单单击事件
	$("#note_ul").on("click", ".btn_slide_down",function(){
		//隐藏列表中的下拉菜单
		$("#note_ul div").hide();
		//显示菜单项
		var note_menu =$(this).parents("li").find("div");
		note_menu.show();
		//阻止冒泡事件
		return false;
	});
	//单击body隐藏菜单
	$("body").click(function(){
		$("#note_ul div").hide();
	});
}

function saveNoteAction(){
					//获取请求参数
					//获取被选中的笔记li对象
					var $li=$("#note_ul a.checked").parent();
					var noteId=$li.data("noteId");
					var noteTitle= $("#input_note_title").val().trim();
					var noteBody=um.getContent();
					console.log(noteId+","+noteTitle + ","+noteBody);
					//校验参数有效性
					if(!noteTitle){
						alert("笔记标题不能为空");
						return;
					}
					//发送Ajax请求
					$.ajax({
						url:path+"/note/update.do",
						type:"post",
						data:{"noteId":noteId,
							   "noteTitle":noteTitle,
							   "noteBody":noteBody},
						dataType:"json",
						success:function(result){
							//更新笔记标题
							var str="";
							str+='<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>';	 
							str+= noteTitle;
							str+='<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>';
							//将字符串str替换到li的a元素里
							$li.find("a").html(str);
							//提示笔记内容更新成功
							alert("保存笔记成功");
						},
						error:function(){
							alert("笔记信息更新失败");
						}
					});
				}
function loadNoteAction(){
					// 设置选中效果
					$("#note_ul a").removeClass("checked");
					$(this).find("a").addClass("checked");
					// 获取参数
					var noteId=$(this).data("noteId");
					console.log(noteId);
					// 发送Ajax请求
					$.ajax({
						url:path+"/note/loadnote.do",
						type:"post",
						data:{"noteId":noteId},
						dataType:"json",
						success:function(result){
							if(result.state==0){
								// 获取返回数据的title
								var title=result.data.title;
								// 获取返回数据的body
								var body=result.data.body;
								// 设置笔记标题数据
								$("#input_note_title").val(title);
								// 设置笔记内容数据
								um.setContent(body);
							}
						},
						error:function(){
							alert("加载笔记失败");
						}
					});
				}
function loadNotesAction(){
	// 设置选中效果
	$("#book_ul a").removeClass("checked");
	$(this).find("a").addClass("checked");
	// 获取参数bookId
	var bookId = $(this).data("bookId");
	console.log(bookId);
	// 发送Ajax请求
	$.ajax({
		url : path + "/note/list.do",
		type : "post",
		data : {
			"bookId" : bookId
		},
		dataType : "json",
		success : function(result) {
			if (result.state == 0) {
				// 获取返回的数据
				var notes = result.data;
				// 循环读取id和title
				// 清空笔记列表
				$("#note_ul li").remove();
				for (var i = 0; i < notes.length; i++) {
					// 获取笔记ID
					var noteId = notes[i].id;
					// 获取笔记TITLE
					var noteTitle = notes[i].title;
					createNoteLi(noteId, noteTitle);
				}
			}
		},
		error : function() {
			alert("加载笔记列表失败");
		}
	});
}
function createNoteLi(noteId,noteTitle){
	var sli="";
	sli+='<li class="online">';
	sli+='<a>';
	sli+='<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>';	 
	sli+= noteTitle;
	sli+='<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>';
	sli+='</a>';
	sli+='<div class="note_menu" tabindex="-1">';
	sli+='<dl>';
	sli+='<dt><button type="button" class="btn btn-default btn-xs btn_move" title="移动至..."><i class="fa fa-random"></i></button></dt>';
	sli+='<dt><button type="button" class="btn btn-default btn-xs btn_share" title="分享"><i class="fa fa-sitemap"></i></button></dt>';
	sli+='<dt><button type="button" class="btn btn-default btn-xs btn_delete" title="删除"><i class="fa fa-times"></i></button></dt>';
	sli+='</dl>';
	sli+='</div>';
	sli+='</li>';
	//将字符串转换jQuery对象并绑定noteId
	var $li=$(sli);
	$li.data("noteId",noteId);
	//将li元素添加到note_ul中
	$("#note_ul").append($li);
}








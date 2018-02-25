function shareNoteAction(){
					//获取参数笔记ID
					var $li=$(this).parents("li");
					var noteId=$li.data("noteId");
					console.log(noteId);
					//发送ajax请求
					$.ajax({
						url:path+"/share/add.do",
						type:"post",
						data:{"noteId":noteId},
						dataType:"json",
						success:function(result){
							if(result.state==0){
								//获取笔记标题
								var noteTitle=$li.text();
								//编辑笔记显示内容
								var str="";
								str+='<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>';	 
								str+= noteTitle;
								str+='<i class="fa fa-sitemap"></i>';
								str+='<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>';
								//替换选中笔记a元素中的内容
								$li.find("a").html(str);
								alert(result.message);
							}
						},
						error:function(){
							alert("笔记分享失败");
						}
					});
				}
function addBookAction(){
					//获取参数
					var userId=getCookie("userId");
					var bookName=$("#input_notebook").val().trim();
					//校验参数格式
					var ok=true;
					if(userId==null){
						ok=false;
						window.location.href="log_in.html";
					}
					if(bookName==""){
						ok=false;
						$("#name_span").html("笔记本名不能为空");
					}
					//发送请求
					if(ok){
						$.ajax({
							url:path+"/book/add.do",
							type:"post",
							data:{
									"userId":userId,
									"bookName":bookName
							},
							dataType:"json",
							success:function(result){
								if(result.state==0){
									var book = result.data;
									var id=book.id;
									var name=book.name;
									//关闭Alert对话框
									closeAlertWindow();
									//刷新笔记本列表
									createBookLi(id,name);
									//提示消息
									alert("笔记本创建成功");
								}
							},
							error:function(){
								alert("创建笔记本失败");
							}	
						});
					}
				}
//创建笔记本li添加到book_ul中
function createBookLi(bookId,bookName){
	var sli="";
		sli+='<li class="online">';
		sli+='<a>';
		sli+='<i class="fa fa-book" title="online" rel="tooltip-bottom">';
		sli+='</i>' ;
		sli+=bookName;
		sli+='</a>';
		sli+='</li>';
	//将sli字符串转成jQuery对象li元素
	var $li=$(sli); 
	//将bookId绑定到元素中
	$li.data("bookId",bookId);
	//将li元素添加到笔记本列表book_ul
	$("#book_ul").append($li);
}
function loadBooks(){
	//获取参数-cookie中保存的id
	var userId=getCookie("userId");
	console.log(userId);
	if(userId==null){ //未能获取到userId
		window.location.href="log_in.html";
	}else{
		$.ajax({
			url:path+"/book/loadbooks.do",
			type:"post",
			data:{"userId":userId},
			dataType:"json",
			success:function(result){
				if(result.state==0){
					//获取笔记本集合
					var books=result.data;
					//循环将笔记本集合数据
					for(var i=0;i<books.length;i++){
						//获取笔记本ID
						var bookId=books[i].id;
						//获取笔记本名称
						var bookName=books[i].name;
						//将笔记本li加载到book_ul
						createBookLi(bookId,bookName);
					}
				}
			},
			error:function(){
				alert("加载笔记本失败");
			}
		});
	}
}







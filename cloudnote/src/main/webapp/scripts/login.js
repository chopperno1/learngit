function loginAction(){
	
			//清空提示信息
			$("#count_span").html("");
			$("#password_span").html("");
			//获取请求参数
			var name=$("#count").val().trim();
			var password=$("#password").val().trim();
			var ok=true; //所有参数均有效
			//格式的校验
			if(name==""){
				$("#count_span").html("用户名不能为空");
				ok=false;
			}
			if(password==""){
				$("#password_span").html("密码不能为空");
				ok=false;
			}
			//发送ajax请求
			if(ok){
				$.ajax({
					url:path+"/user/login.do",
					type:"post",
					data:{"name":name,"password":password},
					dataType:"json",
					success:function(result){
						if(result.state==0){ //成功
							//将用户信息写入cookie
							var user=result.data;
							addCookie("userId",user.id,2);
							//跳转到主页面
							window.location.href="edit.html";
						}else if(result.state==2){
							$("#count_span").html(result.message);
						}else if(result.state==3){
							$("#password_span").html(result.message);
						}
					},
					error:function(){
						alert("登录失败");
					}
				});
			}
		}
function registAction(){
	//清楚提示信息
	$("#warning_1 span").html("");
	$("#warning_2 span").html("");
	$("#warning_3 span").html("");
	//获取参数
	var name=$("#regist_username").val().trim();
	var nick=$("#nickname").val().trim();
	var password
		=$("#regist_password").val().trim();
	var final_password
		=$("#final_password").val().trim();
	//校验数据
	var ok=true;
	//检测用户名:空
	if(name==""){
		ok=false;
		$("#warning_1 span").html("用户名不能为空");
		$("#warning_1").show();
	}
	//检测密码:空/长度(大于等于6)
	if(password==""){
		ok=false;
		$("#warning_2 span").html("密码不能为空");
		$("#warning_2").show();
	}else if(password.length<6){
		ok=false;
		$("#warning_2 span").html("长度不能小于6");
		$("#warning_2").show();
	}
	//检测确认密码:是否一致
	if(final_password != password){
		ok=false;
		$("#warning_3 span").html("密码输入不一致");
		$("#warning_3").show();
	}
	//发送Ajax请求
	if(ok){
		$.ajax({
			url:path+"/user/regist.do",
			type:"post",
			data:{"name":name,
				   "nick":nick,
				   "password":password},
			dataType:"json",
			success:function(result){
				if(result.state==0){
					var user=result.data;
					console.log("注册成功");
					$("#back").click();
					$("#count").val(user.name);
					$("#password").focus();
				}else if(result.state==2){
					$("#warning_1 span").html(result.message);
					$("#warning_1").show();
				}
			},
			error:function(){
				alert("注册失败");
			}
		});
	}
}
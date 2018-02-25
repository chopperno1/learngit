package cn.tedu.cloudnote.controller;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.cloudnote.entity.User;
import cn.tedu.cloudnote.service.NameException;
import cn.tedu.cloudnote.service.PasswordException;
import cn.tedu.cloudnote.service.UserService;
import cn.tedu.cloudnote.util.JsonResult;

@Controller
@RequestMapping("/user")
public class UserController extends ExceptionController{
	@Resource
	private UserService userService;
	
	@ResponseBody
	@RequestMapping("/login.do")
	public Object login(String name,String password){
			System.out.println("执行login方法");
			User user = userService.login(name, password);
			return new JsonResult(user);	
	}
	
	@RequestMapping("/regist.do")
	@ResponseBody
	public JsonResult regist(String name,String password,String nick){
			User user = userService.regist(name, password, nick);
			return new JsonResult(user);
	}
	
	@ExceptionHandler(NameException.class)
	@ResponseBody
	public Object nameexp(NameException e){
		e.printStackTrace();
		return new JsonResult(2,e);
	}
	
	@ExceptionHandler(PasswordException.class)
	@ResponseBody
	public Object pwdexp(PasswordException e){
		e.printStackTrace();
		return new JsonResult(3,e);
	}
	
}















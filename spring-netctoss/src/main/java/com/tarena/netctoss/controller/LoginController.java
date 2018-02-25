package com.tarena.netctoss.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tarena.netctoss.entity.Admin;
import com.tarena.netctoss.service.ApplicationException;
import com.tarena.netctoss.service.LoginService;

@Controller
public class LoginController {
	
	@Resource(name="loginService")
	private LoginService service;
	
	@RequestMapping("/toLogin.do")
	public String toLogin(){
		return "login";
	}
	
	@ExceptionHandler
	public String execute(Exception e,
			HttpServletRequest request){
		if(e instanceof ApplicationException){
			//应用异常，明确提示用户如何处理
			request.setAttribute(
					"login_failed", 
					e.getMessage());
			return "login";
		}
		//系统异常
		return "error";
	}
	
	@RequestMapping("/login.do")
	public String login(
			HttpServletRequest request,
			HttpSession session){
		//读取帐号和密码
		String adminCode = 
			request.getParameter("adminCode");
		String pwd = 
			request.getParameter("pwd");
		System.out.println("adminCode:" 
			+ adminCode);
		//调用业务层来进行登录验证
		Admin admin = 
			service.checkLogin(
					adminCode, pwd);
		//登录成功
		session.setAttribute("admin", admin);
		//登录成功
		return "redirect:toIndex.do";
	}
	
	
	@RequestMapping("/toIndex.do")
	public String toIndex(){
		return "index";
	}
	
	
}







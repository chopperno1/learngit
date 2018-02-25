package web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 登录检查拦截器,判断用户是否登录过.
 */
public class LoginFilter implements Filter {

	public void destroy() {

	}

	public void doFilter(
		ServletRequest request, 
		ServletResponse response, 
		FilterChain chain)
		throws IOException, ServletException {
		//对参数转型,便于使用
		System.out.println(request);
		HttpServletRequest req = 
			(HttpServletRequest) request;
		HttpServletResponse res = 
			(HttpServletResponse) response;
		//将不需要检查的路径排除(让请求继续)
		//不需要检查的路径是:
		String[] paths = new String[]{
			"/toLogin.do",
			"/login.do",
			"/createImg.do"
		};
		//当前的路径是:
		String path = req.getServletPath();
		//如果当前路径是三者之一,请求继续
		for(String p : paths) {
			if(p.equals(path)) {
				chain.doFilter(req, res);
				return;
			}
		}
		
		//尝试从session中读取账号
		HttpSession session = req.getSession();
		String adminCode = (String)
			session.getAttribute("adminCode");
		//通过账号判断用户是否登录
		if(adminCode == null) {
			//未登录,重定向到登录
			res.sendRedirect(
				req.getContextPath() + "/toLogin.do");
		} else {
			//已登录,请求继续
			chain.doFilter(req, res);
		}
	}

	public void init(FilterConfig arg0) throws ServletException {

	}

}

package web;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AdminDao;
import dao.AdminDaoImpl;
import dao.CostDao;
import dao.CostDaoImpl;
import entity.Admin;
import entity.Cost;
import util.ImageUtil;

public class MainServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException {
		//获取当前访问路径
		String p = req.getServletPath();
		//判断路径处理业务
		if("/findCost.do".equals(p)) {
			findCost(req, res);
		} else if("/toAddCost.do".equals(p)) {
			toAddCost(req,res);
		} else if("/addCost.do".equals(p)) {
			addCost(req,res);
		} else if("/toLogin.do".equals(p)) {
			toLogin(req,res);
		} else if("/toIndex.do".equals(p)) {
			toIndex(req,res);
		} else if("/login.do".equals(p)) {
			login(req,res);
		} else if("/createImg.do".equals(p)) {
			createImg(req,res);
		} else {
			throw new RuntimeException("查无此页");
		}
	}

	//生成验证码
	private void createImg(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException {
		//创建验证码及图片
		Object[] objs = ImageUtil.createImage();
		//将验证码存入session
		HttpSession session = req.getSession();
		session.setAttribute("imgcode", objs[0]);
		//将图片输出给浏览器
		res.setContentType("image/png");
		BufferedImage img = (BufferedImage) objs[1];
		//浏览器访问服务器时,服务器就会
		//给它创建一个输出流,目标就是
		//这个浏览器.
		OutputStream os = res.getOutputStream();
		ImageIO.write(img, "png", os);
		os.close();
	}
	
	//登录
	private void login(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException {
		//接收参数
		String adminCode = req.getParameter("adminCode");
		String password = req.getParameter("password");
		//检查账号密码
		AdminDao dao = new AdminDaoImpl();
		Admin a = dao.findByCode(adminCode);
		if(a == null) {
			//账号错误
			req.setAttribute("error", "账号错误");
			req.getRequestDispatcher("WEB-INF/main/login.jsp").forward(req, res);
		} else if(!a.getPassword().equals(password)) {
			//密码错误
			req.setAttribute("error", "密码错误");
			req.getRequestDispatcher("WEB-INF/main/login.jsp").forward(req, res);
		} else {
			//将账号存入cookie,
			//后面查询等功能要使用.
			// /netctoss/login.do
			Cookie cookie = new Cookie("adminCode",adminCode);
			res.addCookie(cookie);
			//也可以将账号存入session
			HttpSession session = req.getSession();
			session.setAttribute("adminCode", adminCode);
			//检查通过
			res.sendRedirect("toIndex.do");
		}
	}
	
	//打开登录页面
	private void toLogin(
		HttpServletRequest req, 
		HttpServletResponse res) throws ServletException, IOException {
		req.getRequestDispatcher("WEB-INF/main/login.jsp").forward(req, res);
		System.out.println("哈喽");
	}
	
	//打开主页
	private void toIndex(
		HttpServletRequest req, 
		HttpServletResponse res) throws ServletException, IOException {
		req.getRequestDispatcher("WEB-INF/main/index.jsp").forward(req, res);
	}
	
	//增加资费
	private void addCost(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException {
		//接收参数
		req.setCharacterEncoding("utf-8");
		String name = req.getParameter("name");
		String costType = req.getParameter("costType");
		String baseDuration = req.getParameter("baseDuration");
		String baseCost = req.getParameter("baseCost");
		String unitCost = req.getParameter("unitCost");
		String descr = req.getParameter("descr");
		//保存资费
		Cost c = new Cost();
		c.setName(name);
		c.setCostType(costType);
		if(baseDuration!=null && !baseDuration.equals("")) {
			c.setBaseDuration(new Integer(baseDuration));
		}
		if(baseCost!=null && !baseCost.equals("")) {
			c.setBaseCost(new Double(baseCost));
		}
		if(unitCost!=null && !unitCost.equals("")) {
			c.setUnitCost(new Double(unitCost));
		}
		c.setDescr(descr);
		CostDao dao = new CostDaoImpl();
		dao.save(c);
		//重定向到查询
		//当前:/netctoss/addCost.do
		//目标:/netctoss/findCost.do
		res.sendRedirect("findCost.do");
	}
	
	//打开增加资费页面
	private void toAddCost(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException {
		req.getRequestDispatcher("WEB-INF/cost/add.jsp").forward(req, res);
	}
	
	//Alt+Shift+M
	//查询资费
	private void findCost(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException {
		//查询所有的资费
		CostDao dao = new CostDaoImpl();
		List<Cost> list = dao.findAll();
		//转发到jsp
		req.setAttribute("costs", list);
		//当前:/netctoss/findCost.do
		//目标:/netctoss/WEB-INF/cost/find.jsp
		req.getRequestDispatcher("WEB-INF/cost/find.jsp").forward(req, res);
	}

}






package test;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tarena.netctoss.dao.AdminDAO;
import com.tarena.netctoss.entity.Admin;
import com.tarena.netctoss.service.LoginService;

public class TestCase {
	@Test
	//测试连接池
	public void test1() throws SQLException{
		String config = "spring-mvc.xml";
		ApplicationContext ac = 
		new ClassPathXmlApplicationContext(
					config);
		DataSource ds = 
				ac.getBean("ds",
						DataSource.class);
		System.out.println(
				ds.getConnection());
	}
	
	@Test
	//测试持久层
	public void test2(){
		String config = "spring-mvc.xml";
		ApplicationContext ac = 
		new ClassPathXmlApplicationContext(
					config);
		AdminDAO dao = 
				ac.getBean("adminDAO",
						AdminDAO.class);
		Admin admin = 
				dao.findByAdminCode("caocao");
		System.out.println(admin);
		
	}
	
	@Test
	//测试业务层
	public void test3(){
		String config = "spring-mvc.xml";
		ApplicationContext ac = 
		new ClassPathXmlApplicationContext(
					config);
		LoginService service = 
				ac.getBean("loginService",
						LoginService.class);
		Admin admin = 
				service.checkLogin(
						"caocao", "123");
		System.out.println(admin);
	}
	
	
}




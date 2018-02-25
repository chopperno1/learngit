package cn.tedu.cloudnote.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.cloudnote.dao.UserDao;
import cn.tedu.cloudnote.entity.User;

public class TestUserDao {
	//@Test
	public void testFindByName(){
		ApplicationContext context=
				new ClassPathXmlApplicationContext(
						"conf/spring-mybatis.xml");
		UserDao dao=
		context.getBean("userDao",UserDao.class);
		
		User user=dao.findByName("demo");
		
		System.out.println(user);
	}
	@Test
	public void testAddUser(){
		ApplicationContext context=
				new ClassPathXmlApplicationContext(
						"conf/spring-mybatis.xml");
		UserDao dao=
		context.getBean("userDao",UserDao.class);
		
		User user=new User("111111","Jack",
				"123456","","JJ");
		
		dao.addUser(user);
	}
}
















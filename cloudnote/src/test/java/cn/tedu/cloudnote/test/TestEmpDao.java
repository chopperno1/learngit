package cn.tedu.cloudnote.test;

import org.junit.Before;
import org.junit.Test;

import cn.tedu.cloudnote.dao.EmpDao;
import cn.tedu.cloudnote.entity.Emp;

public class TestEmpDao extends TestBase{
	private EmpDao empDao;
	@Before
	public void init(){
		empDao=super.getContext()
				.getBean("empDao",EmpDao.class);
	}
	@Test
	public void test(){
		Emp emp=new Emp();
		emp.setName("Tom");
		emp.setAge(28);
		empDao.save(emp);
		//获取emp记录插入的id值
		System.out.println(emp.getId());
	}
}










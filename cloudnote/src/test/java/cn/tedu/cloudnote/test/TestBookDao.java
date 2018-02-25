package cn.tedu.cloudnote.test;

import java.sql.Timestamp;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import cn.tedu.cloudnote.dao.BookDao;
import cn.tedu.cloudnote.entity.Book;

public class TestBookDao extends TestBase{
	private BookDao bookDao;
	@Before
	public void init(){
		bookDao=super.getContext().getBean(
				"bookDao",BookDao.class);
	}
	//@Test
	public void testFindByUserId(){
		List<Book> list=
				bookDao.findByUserId(
				"48595f52-b22c-4485-9244-f4004255b972");
		for(Book book:list){
			System.out.println(
					book.getId()+","
					+book.getName());
		}
	}
	@Test
	public void testSave(){
		Book book=new Book();
		book.setId("123456");
		book.setUserId("48595f52-b22c-4485-9244-f4004255b972");
		book.setName("cloudnote");
		book.setTypeId("5");
		Timestamp time = new Timestamp(
				System.currentTimeMillis());
		book.setCreateTime(time);
		bookDao.save(book);
	}
}









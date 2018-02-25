package cn.tedu.cloudnote.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import cn.tedu.cloudnote.entity.Book;
import cn.tedu.cloudnote.service.BookService;

public class TestBookService 
					extends TestBase{
	private BookService bookService;
	@Before
	public void init(){
		bookService=super.getContext().getBean(
				"bookService", BookService.class);
	}
	//@Test
	public void test(){
		List<Book> list=
			bookService.listBooks(
				"48595f52-b22c-4485-9244-f4004255b972");
		for(Book book:list){
			System.out.println(
					book.getId()+","
					+book.getName());
		}
	}
	@Test
	public void testAddBookService(){
		Book book=
		bookService.addBook("48595f52-b22c-4485-9244-f4004255b972",
					"JavaResource");
	}
}
















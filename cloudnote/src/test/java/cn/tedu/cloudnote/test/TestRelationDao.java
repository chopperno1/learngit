package cn.tedu.cloudnote.test;



import java.util.List;

import org.junit.Before;
import org.junit.Test;

import cn.tedu.cloudnote.dao.RelationDao;
import cn.tedu.cloudnote.entity.Book;
import cn.tedu.cloudnote.entity.User;

public class TestRelationDao extends TestBase{
	private RelationDao rdao;
	@Before
	public void init(){
		rdao=super.getContext().getBean(
				"relationDao", RelationDao.class);			
	}
	//@Test
	public void testFindUserAndBooks(){
	String userId="48595f52-b22c-4485-9244-f4004255b972";
	User user=rdao.findUserAndBooks1(userId);
	System.out.println("====用户信息====");
	System.out.println("名字:"+user.getName());
	System.out.println("昵称:"+user.getNick());
	System.out.println("拥有笔记本的数量:"
	+ user.getBooks().size());
	System.out.println("====笔记本列表====");
	for(Book book:user.getBooks()){
		System.out.println(book.getName());
	}
	}
	@Test
	public void testFindBookAndUser(){
		List<Book> list
			=rdao.findBookAndUser();
		for(Book book:list){
			System.out.print(
				book.getName()+","
				+book.getCreateTime()+",");
			if(book.getUser()!=null){
				System.out.println(book.getUser().getName());
			}
		}
	}
}

















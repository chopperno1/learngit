package cn.tedu.cloudnote.dao;

import java.util.List;

import cn.tedu.cloudnote.entity.Book;

public interface BookDao {
	public void save(Book book);
	public List<Book> findByUserId(String userId);
	public Book findByBookId(String bookId);
}









package cn.tedu.cloudnote.service;

import java.util.List;

import cn.tedu.cloudnote.entity.Book;

public interface BookService {
	public List<Book> listBooks(String userId)throws UserNotFoundException;
	
	public Book addBook(String userId,String bookName) throws UserNotFoundException;
}









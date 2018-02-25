package cn.tedu.cloudnote.dao;

import java.util.List;

import cn.tedu.cloudnote.entity.Book;
import cn.tedu.cloudnote.entity.User;

public interface RelationDao {
	public User findUserAndBooks(String userId);
	public User findUserAndBooks1(String userId);
	
	public List<Book> findBookAndUser();
}








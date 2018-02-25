package cn.tedu.cloudnote.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.tedu.cloudnote.dao.UserDao;
import cn.tedu.cloudnote.entity.User;
import cn.tedu.cloudnote.util.NoteUtil;

@Service("userService")
@Transactional
public class UserServiceImpl 
			implements UserService{
	@Resource
	private UserDao userDao;
	public User login(String name, String password) throws NameException, PasswordException {
		if(name == null || name.trim().isEmpty()){
			throw new NameException("用户不能为空");
		}
		if(password == null || password.trim().isEmpty()){
			throw new PasswordException("密码不能为空");
		}
		//1.利用用户名查找用户信息
		//2.检验用户密码是否正确
		User user=userDao.findByName(name);
		if(user==null){
			throw new NameException("用户名错误");
		}
		//检测密码
		String md5Password=NoteUtil.md5(password);
		if(user.getPassword().equals(md5Password)){
			return user;
		}else{
			throw new PasswordException("密码错误");
		}
	}
	
	public User regist(String name, String password, String nick) throws NameException {
		User user=userDao.findByName(name);
		if(user != null){
			throw new NameException("用户被占用");
		}
		if(nick == null || nick.trim().isEmpty()){
			nick = name;
		}
		String id = NoteUtil.createId();
		String token = "";
		password = NoteUtil.md5(password);
		
		user = new User(id,name,password,token,nick);
		userDao.addUser(user);
		return user;
	}
	
}







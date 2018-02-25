package com.tarena.netctoss.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tarena.netctoss.dao.AdminDAO;
import com.tarena.netctoss.entity.Admin;

/**
 * 业务层实现
 *
 */
@Service("loginService")
//业务层组件应该使用@Service来标识
public class LoginServiceImpl implements
LoginService{
	@Resource(name="adminDAO")
	private AdminDAO dao;
	
	public Admin checkLogin(
			String adminCode, String pwd) {
		Admin admin = null;
		admin = dao.findByAdminCode(adminCode);
		if(admin == null){
			//用户不存在
			throw new ApplicationException(
					"帐号不存在");
		}
		if(!admin.getPassword().equals(pwd)){
			//密码错误
			throw new ApplicationException(
					"密码错误");
		}
		//验证通过
		return admin;
	}
	
	
	
	

}

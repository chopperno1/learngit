package com.tarena.netctoss.service;

import com.tarena.netctoss.entity.Admin;

/**
 * 业务层接口
 */
public interface LoginService {
	public Admin checkLogin(
			String adminCode,
			String pwd);
	
}




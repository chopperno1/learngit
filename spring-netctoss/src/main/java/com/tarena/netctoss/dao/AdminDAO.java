package com.tarena.netctoss.dao;

import com.tarena.netctoss.entity.Admin;
/**
 * 	持久层接口
 *		接口的设计一定不要涉及任何具体的
 *	技术！  
 */
public interface AdminDAO {
	public Admin findByAdminCode(
			String adminCode);
}





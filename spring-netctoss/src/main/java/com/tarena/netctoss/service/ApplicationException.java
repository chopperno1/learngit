package com.tarena.netctoss.service;
/**
 * 应用异常类
 *	 用户在使用系统时，因为一些不正确的操作
 *  引起的异常，比如输入了错误的帐号。
 */
public class ApplicationException 
	extends RuntimeException {

	public ApplicationException() {
		
	}

	public ApplicationException(
			String message) {
		super(message);
	}
	
}

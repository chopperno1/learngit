package cn.tedu.cloudnote.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component	//等价于<bean>配置
@Aspect     //等价于<aop:aspect ref="loggerBean">
public class AspectDemo {
	//@Before("within(cn.tedu.cloudnote.controller..*)")
	//@Before("execution(* cn.tedu.cloudnote.service..*.*(..))")
	@Before("bean(userController)")
	public void testAop(){
		System.out.println("AOP实现注入");
		System.out.println("在执行方法前");
	}
}







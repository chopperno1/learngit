package cn.tedu.cloudnote.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AuditBean {
	@Around("within(cn.tedu.cloudnote.service..*)")
	public Object log(ProceedingJoinPoint point) throws Throwable{
			 Object obj=new Object();
			 try {
				long startTime=System.currentTimeMillis();
				obj=point.proceed();
				long endTime=System.currentTimeMillis();
				String str=point.getSignature().toString();
				System.out.println(str+"耗时:"+(endTime-startTime));
			} catch (Throwable e) {	
				e.printStackTrace();
				throw e;
			}
		return obj;
	}	
}









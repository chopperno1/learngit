package cn.tedu.cloudnote.aspect;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component //将组件扫描到spring
@Aspect    //将组件指定为切面组件
public class ExceptionBean {
	//e是目标组件方法抛出的异常对象
	@AfterThrowing(throwing="e",pointcut="within(cn.tedu.cloudnote.service..*)")
	public void execute(Exception e){
		try {
			//创建日志文件
			FileWriter fw=new FileWriter("D:\\note_error.log",true);
			//利用pw对象写入信息
			PrintWriter pw =new PrintWriter(fw);
			//定义时间字符串
			Date time= new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String timeStr=sdf.format(time);
			//写入异常信息
			pw.println("**********************");
			pw.println("*异常类型："+e);
			pw.println("*发生事件："+timeStr);
			pw.println("****异常详细信息******");
			e.printStackTrace(pw);
			pw.close();
			fw.close();
		} catch (Exception ex) {
			System.out.println("记录异常失败");
		}
	}
}









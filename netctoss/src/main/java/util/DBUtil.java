package util;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;

/**
 * 该类用于维护数据库连接
 * @author adminitartor
 *
 */
public class DBUtil {
	/*
	 * ThreadLocal用于线程跨方法共享数据使用
	 * ThreadLocal内部有一个Map，key为需要共享
	 * 数据的线程本身，value就是其需要共享的数据。
	 * 
	 */
	private static ThreadLocal<Connection> tl;
	/*
	 * DBCP连接池
	 */
	private static BasicDataSource dataSource;
	
	static{
		tl = new ThreadLocal<Connection>();
		/*
		 * properties文件
		 * properties文件常被用作配置文件，结构简单。
		 */
		try {
			Properties prop = new Properties();
			//prop.load(new FileInputStream("config.properties"));
			prop.load(DBUtil.class.getClassLoader().getResourceAsStream("config.properties"));
			
			String className = prop.getProperty("classname");
			String url = prop.getProperty("url");
			String username = prop.getProperty("username");
			String password = prop.getProperty("password");
			int maxActive = Integer.parseInt(prop.getProperty("maxactive"));
			long maxWait = Long.parseLong(prop.getProperty("maxwait"));
			
//			//打桩
//			System.out.println(className);
//			System.out.println(url);
//			System.out.println(username);
//			System.out.println(password);
			
			//初始化连接池
			dataSource = new BasicDataSource();
			//Class.forName();
			dataSource.setDriverClassName(className);
			//DriverManager.getConnection();
			dataSource.setUrl(url);
			dataSource.setUsername(username);
			dataSource.setPassword(password);
			//设置最大连接数
			dataSource.setMaxActive(maxActive);
			//设置最大等待时间
			dataSource.setMaxWait(maxWait);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取数据库连接
	 * @return
	 * @throws Exception 
	 */
	int i = Integer.parseInt("");
	public static Connection getConnection() throws Exception{
		try {			
			Connection connection = dataSource.getConnection();
			tl.set(connection);
			return connection;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	/**
	 * 关闭数据库连接
	 * @param conn
	 */
	public static void closeConnection(){
		try {
			//关闭连接前，提交事务
			TransCommit();
			Connection conn = tl.get();
			tl.remove();
			if(conn!=null){
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 开启事务
	 * @throws SQLException 
	 */
	public static void TransBegin() throws SQLException{
		tl.get().setAutoCommit(false);
	}
	/**
	 * 事务回滚
	 * @throws SQLException 
	 */
	public static void TransRollBack() throws SQLException{
		tl.get().rollback();
		tl.get().setAutoCommit(true);
	}
	/**
	 * 提交事务
	 * @throws SQLException 
	 */
	public static void TransCommit() throws SQLException{
		tl.get().commit();
		tl.get().setAutoCommit(true);
	}
	
	public static void main(String[] args) throws Exception {
		Connection conn = DBUtil.getConnection();
		System.out.println(conn);
		DBUtil.closeConnection();
	}
	
}






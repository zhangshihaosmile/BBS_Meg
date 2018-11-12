package cn.bdqn.bbs.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 * 数据操作工具类
 * @author Jack
 *
 */
public class BaseDao {
	//4个属性
	private static String driver;//驱动
	private static String url;//连接字符串
	private static String user;//用户名
	private static String password;//密码
	static {
		init();//初始化方法，加载配置文件
	}
	public static void init() {
		Properties properties=new Properties();
		String configFile="database.properties";
		//获取配置文件的输入流
		InputStream is=BaseDao.class.getClassLoader().getResourceAsStream(configFile);
		try {
			properties.load(is);//从输入流中加载配置文件
		} catch (IOException e) {
			e.printStackTrace();
		}
		//获取对应键的值
		driver=properties.getProperty("driver");
		url=properties.getProperty("url");
		user=properties.getProperty("username");
		password=properties.getProperty("password");
	}
	//3个方法
	/**
	 * 获取数据库连接对象
	 * @return
	 */
	public Connection getConnection() {
		Connection conn=null;
		try {
			Class.forName(driver);//加载驱动
			conn=DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	/**
	 * 按顺序关闭资源 
	 * @param conn 连接对象
	 * @param pstmt 执行sql语句对象
	 * @param rs 结果集
	 */
	public void closeAll(Connection conn,PreparedStatement pstmt,ResultSet rs) {
		if (rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (pstmt!=null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 增、删、改通用方法
	 * @param sql 要执行的sql语句  
	 * @param param 参数
	 * @return 受影响行数
	 */
	public int executeUpdate(String sql,Object[] param) {
		int num=0;
		Connection conn=getConnection();
		PreparedStatement pstmt=null;
		try {
			pstmt=conn.prepareStatement(sql);
			if (param!=null) {
				//动态添加参数到sql
				for(int i=0;i<param.length;i++) {
					pstmt.setObject(i+1, param[i]);
				}
			}
			num=pstmt.executeUpdate();//执行sql得到受影响行数
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeAll(conn, pstmt, null);
		}
		return num;
	}
	
	public static void main(String[] args) {
		BaseDao dao=new BaseDao();
		Connection conn=dao.getConnection();
		System.out.println("连接成功");
		dao.closeAll(conn, null, null);
		System.out.println("关闭成功");
	}
}

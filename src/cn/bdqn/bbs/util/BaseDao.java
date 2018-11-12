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
 * ���ݲ���������
 * @author Jack
 *
 */
public class BaseDao {
	//4������
	private static String driver;//����
	private static String url;//�����ַ���
	private static String user;//�û���
	private static String password;//����
	static {
		init();//��ʼ�����������������ļ�
	}
	public static void init() {
		Properties properties=new Properties();
		String configFile="database.properties";
		//��ȡ�����ļ���������
		InputStream is=BaseDao.class.getClassLoader().getResourceAsStream(configFile);
		try {
			properties.load(is);//���������м��������ļ�
		} catch (IOException e) {
			e.printStackTrace();
		}
		//��ȡ��Ӧ����ֵ
		driver=properties.getProperty("driver");
		url=properties.getProperty("url");
		user=properties.getProperty("username");
		password=properties.getProperty("password");
	}
	//3������
	/**
	 * ��ȡ���ݿ����Ӷ���
	 * @return
	 */
	public Connection getConnection() {
		Connection conn=null;
		try {
			Class.forName(driver);//��������
			conn=DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	/**
	 * ��˳��ر���Դ 
	 * @param conn ���Ӷ���
	 * @param pstmt ִ��sql������
	 * @param rs �����
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
	 * ����ɾ����ͨ�÷���
	 * @param sql Ҫִ�е�sql���  
	 * @param param ����
	 * @return ��Ӱ������
	 */
	public int executeUpdate(String sql,Object[] param) {
		int num=0;
		Connection conn=getConnection();
		PreparedStatement pstmt=null;
		try {
			pstmt=conn.prepareStatement(sql);
			if (param!=null) {
				//��̬��Ӳ�����sql
				for(int i=0;i<param.length;i++) {
					pstmt.setObject(i+1, param[i]);
				}
			}
			num=pstmt.executeUpdate();//ִ��sql�õ���Ӱ������
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
		System.out.println("���ӳɹ�");
		dao.closeAll(conn, null, null);
		System.out.println("�رճɹ�");
	}
}

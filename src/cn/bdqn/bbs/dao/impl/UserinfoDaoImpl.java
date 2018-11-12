package cn.bdqn.bbs.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cn.bdqn.bbs.dao.UserinfoDao;
import cn.bdqn.bbs.entity.Userinfo;
import cn.bdqn.bbs.util.BaseDao;

public class UserinfoDaoImpl extends BaseDao implements UserinfoDao {

	@Override
	public Userinfo findUser(Userinfo userParam) {
		String sql="SELECT username,`password`,email FROM msg_userinfo where username=? and `password`=?";
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Userinfo userinfo=null;
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, userParam.getUsername());
			pstmt.setString(2,userParam.getPassword());
			rs=pstmt.executeQuery();
			while(rs.next()){
				String username=rs.getString("username");
				String password=rs.getString("password");
				String email=rs.getString("email");
				userinfo=new Userinfo(username, password, email);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeAll(conn, pstmt, rs);
		}
		return userinfo;
	}

	@Override
	public List<Userinfo> findAll() {
		String sql="SELECT username,`password`,email FROM msg_userinfo";
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Userinfo> userinfoList=new ArrayList<Userinfo>();
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()){
				String username=rs.getString("username");
				String password=rs.getString("password");
				String email=rs.getString("email");
				Userinfo userinfo=new Userinfo(username, password, email);
				userinfoList.add(userinfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeAll(conn, pstmt, rs);
		}
		return userinfoList;
	}

	@Override
	public int addUser(Userinfo userinfo) {
		String sql="insert into msg_userinfo (username,`password`,email) values (?,?,?)";
		Object[] param={userinfo.getUsername(),userinfo.getPassword(),userinfo.getEmail()};
		int result=executeUpdate(sql,param);
		return result;
	}

}

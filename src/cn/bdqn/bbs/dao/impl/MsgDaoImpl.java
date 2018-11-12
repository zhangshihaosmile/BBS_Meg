package cn.bdqn.bbs.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.bdqn.bbs.dao.MsgDao;
import cn.bdqn.bbs.entity.Msg;
import cn.bdqn.bbs.util.BaseDao;

public class MsgDaoImpl extends BaseDao implements MsgDao {

	@Override
	public List<Msg> listAll(int pageIndex, int pageSize) {
		String sql="SELECT msgid,username,title,msgcontent,state,sendto,msg_create_date FROM msg order by msg_create_date desc limit ?,? ";
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Msg> msgList=new ArrayList<Msg>();
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, (pageIndex-1)*pageSize);
			pstmt.setInt(2, pageSize);
			rs=pstmt.executeQuery();
			while(rs.next()){
				int msgid=rs.getInt("msgid");           
				String username=rs.getString("username");     
				String title=rs.getString("title");        
				String msgcontent=rs.getString("msgcontent");   
				int state=rs.getInt("state");           
				String sendto=rs.getString("sendto");       
				Date msg_create_date=rs.getDate("msg_create_date");
				Msg msg=new Msg(msgid, username, title, msgcontent, state, sendto, msg_create_date);
				msgList.add(msg);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeAll(conn, pstmt, rs);
		}
		return msgList;
	}

	@Override
	public Msg findById(int id,String username) {
		String sql="SELECT msgid,username,title,msgcontent,state,sendto,msg_create_date FROM msg where msgid=? and sendto=?";
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Msg msg=null;
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.setString(2,username);
			rs=pstmt.executeQuery();
			while(rs.next()){
				int msgid=rs.getInt("msgid");           
				String uname=rs.getString("username");     
				String title=rs.getString("title");        
				String msgcontent=rs.getString("msgcontent");   
				int state=rs.getInt("state");           
				String sendto=rs.getString("sendto");       
				Date msg_create_date=rs.getDate("msg_create_date");
				msg=new Msg(msgid, uname, title, msgcontent, state, sendto, msg_create_date);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeAll(conn, pstmt, rs);
		}
		return msg;
	}

	@Override
	public int updateMsg(Msg msg) {
		String sql="update msg set state=? where msgid=?";
		Object[] param={msg.getState(),msg.getMsgid()};
		int result=executeUpdate(sql, param);
		return result;
	}

	@Override
	public int addMsg(Msg msg) {
		String sql="insert into msg (username,title,msgcontent,state,sendto,msg_create_date) values (?,?,?,?,?,?)";
		Object[] param={msg.getUsername(),msg.getTitle(),msg.getMsgcontent(),
				msg.getState(),msg.getSendto(),msg.getMsg_create_date()};
		int result=executeUpdate(sql,param);
		return result;
	}

	@Override
	public int count() {
		String sql="select count(1) from msg";
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int count=0;
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if (rs.next()) {
				count=rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeAll(conn, pstmt, rs);
		}
		return count;
	}

	@Override
	public List<Msg> listAll(int pageIndex, int pageSize, String username) {
		String sql="SELECT msgid,username,title,msgcontent,state,sendto,msg_create_date FROM msg where sendto=? order by msg_create_date desc limit ?,? ";
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Msg> msgList=new ArrayList<Msg>();
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setInt(2, (pageIndex-1)*pageSize);
			pstmt.setInt(3, pageSize);
			rs=pstmt.executeQuery();
			while(rs.next()){
				int msgid=rs.getInt("msgid");           
				String uname=rs.getString("username");     
				String title=rs.getString("title");        
				String msgcontent=rs.getString("msgcontent");   
				int state=rs.getInt("state");           
				String sendto=rs.getString("sendto");       
				Date msg_create_date=rs.getDate("msg_create_date");
				Msg msg=new Msg(msgid, uname, title, msgcontent, state, sendto, msg_create_date);
				msgList.add(msg);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeAll(conn, pstmt, rs);
		}
		return msgList;
	}

	@Override
	public int del(int id, String username) {
		String sql="delete from msg where msgid=? and sendto=?";
		Object[] param={id,username};
		return executeUpdate(sql, param);
	}

}

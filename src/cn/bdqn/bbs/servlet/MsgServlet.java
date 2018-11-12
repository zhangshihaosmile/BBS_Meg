package cn.bdqn.bbs.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.bdqn.bbs.biz.MsgBiz;
import cn.bdqn.bbs.biz.UserinfoBiz;
import cn.bdqn.bbs.biz.impl.MsgBizImpl;
import cn.bdqn.bbs.biz.impl.UserinfoBizImpl;
import cn.bdqn.bbs.entity.Msg;
import cn.bdqn.bbs.entity.Userinfo;
import cn.bdqn.bbs.util.MsgPage;

@WebServlet("/msg")
public class MsgServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String action=request.getParameter("action");//action参数决定做什么操作
		//所有操作都是当前用户的
		Userinfo user=(Userinfo)request.getSession().getAttribute("currentUser");
		if(user==null){
			response.sendRedirect("index.jsp");
			return;
		}
		if ("list".equals(action)) {
			String pageIndex=request.getParameter("pageIndex");
			String pageSize=request.getParameter("pageSize");
			int index=1;
			int size=10;
			try {
				index=Integer.parseInt(pageIndex);
			} catch (Exception e) {
				//e.printStackTrace();
			}
			try {
				size=Integer.parseInt(pageSize);
			} catch (NumberFormatException e) {
				//e.printStackTrace();
			}
			MsgPage msgPage=new MsgPage();//将分页参数封装到分页对象中
			msgPage.setPageIndex(index);
			msgPage.setPageSize(size);
			//调用biz进行分页查询 TODO 页面越界
			MsgBiz msgBiz=new MsgBizImpl();
			msgBiz.list(msgPage,user.getUsername());
			//将业务层处理后的分页对象存放至request作用域中
			request.setAttribute("msgPage", msgPage);
			request.getRequestDispatcher("main.jsp").forward(request, response);
		}else if("read".equals(action)){
			String msgid=request.getParameter("msgid");
			int id=0;
			try {
				id=Integer.parseInt(msgid);
			} catch (NumberFormatException e) {
			}
			MsgBiz msgBiz=new MsgBizImpl();
			int result=msgBiz.updateRead(id);
			//查询详情
			Msg msg=msgBiz.findById(id,user.getUsername());//查询当前用户的消息
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("readMsg.jsp").forward(request, response);
		}else if("del".equals(action)){
			String msgid=request.getParameter("msgid");
			int id=0;
			try {
				id=Integer.parseInt(msgid);
			} catch (NumberFormatException e) {
			}
			MsgBiz msgBiz=new MsgBizImpl();
			int result=msgBiz.del(id,user.getUsername());
			response.sendRedirect("main.jsp");
		}else if("returnmsg".equals(action)){
			UserinfoBiz userBiz=new UserinfoBizImpl();
			List<Userinfo> users=userBiz.listAll();
			String sendto=request.getParameter("sendto");
			request.setAttribute("sendto", sendto);
			request.setAttribute("users", users);
			request.getRequestDispatcher("newMsg.jsp").forward(request, response);
		}else if("send".equals(action)){
			String title=request.getParameter("title");
			String content=request.getParameter("content");
			String toUser=request.getParameter("toUser");
			Msg msg=new Msg(0, user.getUsername(), title, content, 0, toUser, new Date());
			MsgBiz msgBiz=new MsgBizImpl();
			int result=msgBiz.addMsg(msg);
			PrintWriter out=response.getWriter();
			if(result==1){
				out.print("<script>alert('发送成功！静候佳音~！');location='main.jsp'</script>");
			}else{
				out.print("<script>alert('发送失败！');location='newMsg.jsp'</script>");
			}
		}
	}
	
}

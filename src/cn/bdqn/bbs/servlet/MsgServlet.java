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
		String action=request.getParameter("action");//action����������ʲô����
		//���в������ǵ�ǰ�û���
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
			MsgPage msgPage=new MsgPage();//����ҳ������װ����ҳ������
			msgPage.setPageIndex(index);
			msgPage.setPageSize(size);
			//����biz���з�ҳ��ѯ TODO ҳ��Խ��
			MsgBiz msgBiz=new MsgBizImpl();
			msgBiz.list(msgPage,user.getUsername());
			//��ҵ��㴦���ķ�ҳ��������request��������
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
			//��ѯ����
			Msg msg=msgBiz.findById(id,user.getUsername());//��ѯ��ǰ�û�����Ϣ
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
				out.print("<script>alert('���ͳɹ����������~��');location='main.jsp'</script>");
			}else{
				out.print("<script>alert('����ʧ�ܣ�');location='newMsg.jsp'</script>");
			}
		}
	}
	
}

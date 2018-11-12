package cn.bdqn.bbs.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.bdqn.bbs.biz.UserinfoBiz;
import cn.bdqn.bbs.biz.impl.UserinfoBizImpl;
import cn.bdqn.bbs.entity.Userinfo;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String action=request.getParameter("action");
		UserinfoBiz userinfoBiz=new UserinfoBizImpl();
		if ("login".equals(action)) {
			//��¼����
			String username=request.getParameter("username");
			String password=request.getParameter("password");
			Userinfo userParam=new Userinfo(username, password, null);
			Userinfo userResult=userinfoBiz.login(userParam);
			if (null!=userResult) {
				//��¼�ɹ���
				request.getSession().setAttribute("currentUser", userResult);
				response.sendRedirect("main.jsp");
				return;
			}else{
				//��¼ʧ��
				PrintWriter out=response.getWriter();
				out.print("<script>alert('��¼ʧ�ܣ��û������������');location='index.jsp'</script>");
				out.flush();
				out.close();
				return;
			}
		}else if("regist".equals(action)){
			String username=request.getParameter("username");//�û���
			String password=request.getParameter("password");//����
			String affirm=request.getParameter("affirm");//ȷ������
			String email=request.getParameter("email");//����
			String error="";//��ʾ��Ϣ
			if(null==username||"".equals(username)){
				error="�û�������Ϊ�գ�";
			}else if(null==password||"".equals(password)){
				error="���벻��Ϊ�գ�";
			}else if(!password.equals(affirm)){
				error="�����������벻һ�£�";
			}else if(null==email||"".equals(email)){
				error="���䲻��Ϊ��";
			}
			UserinfoBiz userBiz=new UserinfoBizImpl();
			//��֤�û����Ƿ��ظ�
			List<Userinfo> users=userBiz.listAll();
			for (Userinfo userinfo : users) {
				if(userinfo.getUsername().equals(username)){
					request.setAttribute("error", "���û����Ѿ�ע�ᣡ");
					request.getRequestDispatcher("register.jsp").forward(request, response);
					return;
				}
			}
			if(!"".equals(error)){//error��Ϊ��˵����֤û��ͨ��
				request.setAttribute("error", error);
				//��������Ϣת����regist.jsp��չʾ
				request.getRequestDispatcher("register.jsp").forward(request, response);
				return;
			}else{
				Userinfo userinfo=new Userinfo(username, password, email);
				
				boolean isRegist=userBiz.regist(userinfo);
				if(isRegist==true){
					//ע��ɹ�
					request.setAttribute("error", "ע��ɹ���");
					request.getRequestDispatcher("register.jsp").forward(request, response);
				}else{
					//ע��ʧ��
					request.setAttribute("error", "ע��ʧ�ܣ���ϵ����Ա�����");
					request.getRequestDispatcher("register.jsp").forward(request, response);
				}
			}
			
		}else if("logout".equals(action)){
			request.getSession().invalidate();//���ٻỰ
			response.sendRedirect("index.jsp");
		}else if("findUsers".equals(action)){
			UserinfoBiz userBiz=new UserinfoBizImpl();
			List<Userinfo> users=userBiz.listAll();
			request.setAttribute("users", users);
			request.getRequestDispatcher("newMsg.jsp").forward(request, response);
		}
	}
	
}	

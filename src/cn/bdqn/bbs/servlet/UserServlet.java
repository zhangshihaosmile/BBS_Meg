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
			//登录操作
			String username=request.getParameter("username");
			String password=request.getParameter("password");
			Userinfo userParam=new Userinfo(username, password, null);
			Userinfo userResult=userinfoBiz.login(userParam);
			if (null!=userResult) {
				//登录成功！
				request.getSession().setAttribute("currentUser", userResult);
				response.sendRedirect("main.jsp");
				return;
			}else{
				//登录失败
				PrintWriter out=response.getWriter();
				out.print("<script>alert('登录失败！用户名或密码错误！');location='index.jsp'</script>");
				out.flush();
				out.close();
				return;
			}
		}else if("regist".equals(action)){
			String username=request.getParameter("username");//用户名
			String password=request.getParameter("password");//密码
			String affirm=request.getParameter("affirm");//确认密码
			String email=request.getParameter("email");//邮箱
			String error="";//提示消息
			if(null==username||"".equals(username)){
				error="用户名不能为空！";
			}else if(null==password||"".equals(password)){
				error="密码不能为空！";
			}else if(!password.equals(affirm)){
				error="两次密码输入不一致！";
			}else if(null==email||"".equals(email)){
				error="邮箱不能为空";
			}
			UserinfoBiz userBiz=new UserinfoBizImpl();
			//验证用户名是否重复
			List<Userinfo> users=userBiz.listAll();
			for (Userinfo userinfo : users) {
				if(userinfo.getUsername().equals(username)){
					request.setAttribute("error", "该用户名已经注册！");
					request.getRequestDispatcher("register.jsp").forward(request, response);
					return;
				}
			}
			if(!"".equals(error)){//error不为空说明验证没有通过
				request.setAttribute("error", error);
				//将错误消息转发到regist.jsp并展示
				request.getRequestDispatcher("register.jsp").forward(request, response);
				return;
			}else{
				Userinfo userinfo=new Userinfo(username, password, email);
				
				boolean isRegist=userBiz.regist(userinfo);
				if(isRegist==true){
					//注册成功
					request.setAttribute("error", "注册成功！");
					request.getRequestDispatcher("register.jsp").forward(request, response);
				}else{
					//注册失败
					request.setAttribute("error", "注册失败，联系管理员解决！");
					request.getRequestDispatcher("register.jsp").forward(request, response);
				}
			}
			
		}else if("logout".equals(action)){
			request.getSession().invalidate();//销毁会话
			response.sendRedirect("index.jsp");
		}else if("findUsers".equals(action)){
			UserinfoBiz userBiz=new UserinfoBizImpl();
			List<Userinfo> users=userBiz.listAll();
			request.setAttribute("users", users);
			request.getRequestDispatcher("newMsg.jsp").forward(request, response);
		}
	}
	
}	

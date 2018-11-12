<%@page import="cn.bdqn.bbs.util.MsgPage"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="elements/head.jsp"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%
	MsgPage msgPage=(MsgPage)request.getAttribute("msgPage");
	//只要是访问main.jsp都需要先访问msg?action=list让作用域中有 msgPage的数据
	if(msgPage==null){
		request.getRequestDispatcher("msg?action=list").forward(request, response);
		return;
	}
 %>
<body>
	<div id="main">
		<div class="mainbox">
			<div class="title myMessage png"></div>
			<div class="menu">
				<span>当前用户：<a href="main.jsp">${currentUser.username }</a> </span> <span><a
					href="UserServlet?action=findUsers">发短消息</a> </span> <span><a
					href="UserServlet?action=logout">退出</a> </span>
			</div>
			<div id="error"></div>
			<div class="content messageList">
				<ul>
					<c:forEach items="${msgPage.msgList }" var="msg">
					 
						<li <c:if test="${msg.state==0 }">class="unReaded"</c:if>><em><c:out
									value="${msg.msg_create_date}" />
						</em> <em> <a href="msg?action=returnmsg&msgid=${msg.msgid }&sendto=${msg.username}">回信</a> </em> <em> <a onclick="return confirm('确定要删除吗？')" href="msg?action=del&msgid=${msg.msgid }">删除</a> </em>
							<p>
								<strong><a href="msg?action=read&msgid=${msg.msgid }">${msg.title }</a> </strong>
								<c:if test="${fn:length(msg.msgcontent) > 8}">
									<c:out value="${fn:substring(msg.msgcontent,0,7)}" />....
			  	 				</c:if>
								<c:if test="${fn:length(msg.msgcontent) <= 8}">
									<c:out value="${msg.msgcontent}" />
								</c:if>
							</p>
						</li>
					</c:forEach>
				</ul>

				<p>
				<center>
					<a href="msg?action=list&pageIndex=1">首页</a> <a
						href="msg?action=list&pageIndex=${msgPage.pageIndex-1 }">上一页</a>
					${msgPage.pageIndex }/${msgPage.totalPageCount } <a
						href="msg?action=list&pageIndex=${msgPage.pageIndex+1 }">下一页</a> <a
						href="msg?action=list&pageIndex=${msgPage.totalPageCount }">末页</a>
				</center>
				</p>
			</div>
		</div>
	</div>
</body>
</html>

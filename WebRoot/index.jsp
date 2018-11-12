﻿<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>零度短消息平台</title>
<link type="text/css" rel="stylesheet" href="css/sms.css" />
<style>
	#loginTitle h1{
		text-align: center;
		font-size: 30px;
	}
	#loginTitle{
		height:100px;
		position: relative;
		top: 150px;
	}
</style>
<script type="text/javascript" src="scripts/jquery.js"></script>
<!--[if IE 6]>
<script type="text/javascript" src="scripts/DD_belatedPNG.js"></script>
<script type="text/javascript">DD_belatedPNG.fix('.png');</script>
<![endif]-->
<script type="text/javascript">
$(function(){
	$(".btn-reg").bind("click", function(){
		window.location.href = "register.jsp";
	});
});

function check(){
	var username = document.getElementById("username");
	var password = document.getElementById("password");
	var error = document.getElementById("error");
	if(username.value == ""){
		error.innerHTML="<font color=\"red\">用户名不能为空！</font>";
		username.focus();
		return false;
	}else if(password.value == ""){
		error.innerHTML="<font color=\"red\">密码不能为空！</font>";
		password.focus();				
		return false;
	}
	return true;
}
</script>
</head>
<body>
<!-- <div id="loginTitle" class="png"></div> -->
<div id="loginTitle"><h1>零度短消息登录</h1></div>
<div id="loginForm" class="userForm png">
	<form method="post" name="loginform" action="UserServlet?action=login" onsubmit = "return check()">		
		<dl>			
			<div id="error"></div>
			<dt>用户名：</dt>
			<dd><input type="text" name="username" /></dd>
			<dt>密　码：</dt>
			<dd><input type="password" name="password" /></dd>
		</dl>
		<div class="buttons">
			<input class="btn-login png" type="submit" name="submit" value=" " /><input class="btn-reg png" type="button" name="register" value=" " />
		</div>		
	</form>
</div>
</body>
</html>


<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML >
<html>
<head>
<title>后台登录</title>
<%-- <base href="<%=basePath%>"> --%>
<link rel="stylesheet" href="<%=basePath%>css/login.css">
</head>
<body class="login-bg">
	<div class="login">
		<span style="color: red;"> ${ addError}</span>
		<div class="message">用户添加界面</div>
		<div id="darkbannerwrap"></div>
		<form action="go_insert" method="post">
			<input type="text" name="userName" placeholder="请输入你要添加的用户名">
			<hr class="hr15">
			<input type="text" name="password" placeholder="请输入注册的用户名密码">
			<hr class="hr15">
			<input value="确认添加" style="width:100%;"type="submit">
			<hr class="hr20">
			<a href="go_toMain">返回主页</a>
		</form>
	</div>
</body>
</html>
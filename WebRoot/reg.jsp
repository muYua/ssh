<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html>
<head>
<title>REG</title>
<base href="<%=basePath%>">
<link rel="stylesheet" href="<%=basePath%>css/login.css">

</head>

<body class="login-bg">
	<div class="login layui-anim layui-anim-up">
		<span style="color :red;">${ regError}</span>
		<div class="message">注册界面</div>
		<div id="darkbannerwrap"></div>
		<form action="admin/go_reg" method="post">
			<input type="text" placeholder="请输入用户名" name="adminName"><br>
			<hr class="hr15">
			<input type="password" placeholder="请输入密码" name="adminPassword"><br>
			<hr class="hr15">
			<input type="password" placeholder="请再次输入密码" name="adminPassword2"><br>
			<hr class="hr15">
			<input value="OK，开始注册" style="width:100%;"type="submit">
			<hr class="hr20">
			<a href="login.jsp">返回</a>
		</form>
	</div>
</body>
</html>

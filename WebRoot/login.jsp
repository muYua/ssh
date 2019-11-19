<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
<title>后台登录</title>
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta name="viewport"
	content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
<base href="<%=basePath%>">
<link rel="stylesheet" href="<%=basePath%>css/login.css">

</head>
<body class="login-bg">
	<div class="login">
		<span style="color :red;">${ loginError}</span><c:if test="${ loginFlag}"><a href="reg.jsp">还没注册？</a></c:if>
		<div class="message">管理员登录</div>
		<div id="darkbannerwrap"></div>
		<form method="post" action="admin/go_login">
			<input name="adminName" placeholder="管理员用户名" type="text">
			<hr class="hr20">
			<input name="adminPassword" type="password"  placeholder="密码">
			<hr class="hr20">
			<input value="登录" style="width:100%;" type="submit">
			<hr class="hr20">
			<a href="reg.jsp">注册</a> 
		</form>
	</div>
</body>
</html>

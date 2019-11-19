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
<base href="<%=basePath%>">
<link rel="stylesheet" href="<%=basePath%>css/login.css">
</head>
<body class="login-bg">
	<div class="login">
		<span style="color: red;"> ${ updateError}</span>
		<div class="message">管理员修改界面</div>
		<div id="darkbannerwrap"></div>
		<form action="admin/go_update" method="post">
			<!-- 便于post传值input-hidden -->
			<input type="hidden" name="id" value=${ id}>
			<input type="hidden" name="oldAdminName" value=${ oldAdminName}>
			<input type="hidden" name="oldAdminPassword" value=${ oldAdminPassword}>
			用户ID<input type="text" name="id" value=${ id} style="width:100%;" disabled="disabled">
			<hr class="hr15">
			用户名<input type="text" name="adminName" placeholder=${ oldAdminName}>
			<hr class="hr15">
			用户密码<input type="text" name="adminPassword" placeholder=${ oldAdminPassword}>
			<hr class="hr15">
			<input value="确认修改" style="width:100%;"type="submit">
			<hr class="hr20">
			<a href="admin/go_toAdmin">返回主页</a>
		</form>
	</div>
</body>
</html>
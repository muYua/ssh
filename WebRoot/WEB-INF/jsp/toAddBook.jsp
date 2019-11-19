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
		<span style="color: red;"> ${ addError}</span>
		<div class="message">图书信息添加界面</div>
		<div id="darkbannerwrap"></div>
		<form action="book/go_insert" method="post">
			<input type="text" name="bookName" placeholder="图书名">
			<hr class="hr15">
			<input type="text" name="bookPrice" placeholder="图书价格">
			<hr class="hr15">
			<input type="text" name="author" placeholder="图书作者">
			<hr class="hr15">
			<input type="text" name="reader.userName" placeholder="借阅者（可不填）">
			<hr class="hr15">
			<input value="确认添加" style="width:100%;"type="submit">
			<hr class="hr20">
			<a href="book/go_toBook">返回主页</a>
		</form>
	</div>
</body>
</html>
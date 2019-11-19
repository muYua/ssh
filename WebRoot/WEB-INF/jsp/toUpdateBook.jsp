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
		<div class="message">图书信息修改界面</div>
		<div id="darkbannerwrap"></div>
		<form action="book/go_update" method="post">
			<!-- 便于post传值input-hidden -->
			<input type="hidden" name="oldBookName" value=${ oldBookName}>
			<input type="hidden" name="oldAuthor" value=${ oldAuthor}>
			<input type="hidden" name="oldBookPrice" value=${ oldBookPrice}>
			<input type="hidden" name="oldReaderName" value=${ oldReaderName}>
			图书ID<input type="text" name="id" value=${ oldId} disabled="disabled">
			<hr class="hr15">
			图书名<input type="text" name="bookName" placeholder=${ oldBookName}>
			<hr class="hr15">
			图书价格<input type="text" name="bookPrice" placeholder=${ oldBookPrice}>
			<hr class="hr15">
			图书作者<input type="text" name="author" placeholder=${ oldAuthor}>
			<hr class="hr15">
			借阅者（可不填）<input type="text" name="reader.userName" placeholder=${ oldReaderName}>
			<hr class="hr15">
			<input value="确认修改" style="width:100%;"type="submit">
			<hr class="hr20">
			<a href="book/go_toBook">返回主页</a>
		</form>
	</div>
</body>
</html>
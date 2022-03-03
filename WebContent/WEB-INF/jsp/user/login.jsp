<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>在线书店</title>
<style type="text/css">
table, h1 {
	text-align: center
}
</style>
</head>
<body>
	<h1>用户登录页面</h1>
	<form action="${pageContext.request.contextPath }/user/login"
		method="post">
		<table border="1" width="55%" align="center">
			<tr>
				<td align="center">请输入用户名称：</td>
				<td><input type="text" name="uid"><font color="red">${errors.uid }</font></td>
			</tr>
			<tr>
				<td align="center">请输入登录密码：</td>
				<td><input type="password" name="loginPwd"><font color="red">${errors.loginPwd }</font></td>
			</tr>
			<tr>
				<td align="center">一个月内自动登录：</td>
				<td><input type="checkbox" name="isAutoLogin" value="true">一个月内自动登录</td>
			</tr>
			<tr>
				<td align="center" colspan="2"><input type="submit" value="登录">&nbsp;&nbsp;&nbsp;<a
					href="${pageContext.request.contextPath }/user/register">注册新用户</a></td>
			</tr>
		</table>
	</form>
</body>
</html>
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
	<h1>用户注册页面</h1>
	<form action="${pageContext.request.contextPath }/user/register"
		method="post">
		<table border="1" width="55%" align="center">
			<tr>
				<td align="center">请输入用户名称：</td>
				<td><input type="text" name="userName"><font color="red">${errors.userName }</font></td>
			</tr>
			<tr>
				<td align="center">请输入登录密码：</td>
				<td><input type="password" name="loginPwd"><font color="red">${errors.loginPwd }</font></td>
			</tr>
			<tr>
				<td align="center">请输入联系电话：</td>
				<td><input type="text" name="mobile"><font color="red">${errors.mobile }</font></td>
			</tr>
			<tr>
				<td align="center">请输入家庭住址：</td>
				<td><textarea rows="2" cols="55" name="addr"></textarea><font color="red">${errors.addr }</font></td>
			</tr>
			<tr>
				<td align="center" colspan="2"><input type="submit" value="注册新用户"></td>
			</tr>
		</table>
	</form>
</body>
</html>
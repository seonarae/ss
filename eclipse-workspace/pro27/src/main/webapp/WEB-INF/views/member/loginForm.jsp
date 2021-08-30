<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%
	request.setCharacterEncoding("utf-8");
%>
<html>
<head>
<meta charset="utfo-8">
<title>로그인창</title>
<c:choose>
<c:when test="${result=='loginFailed'}">
<script>
	window.onload=function(){
		alert("아이디나 비밀번호가 틀립니다. 다시 로그인 하세요!")
	}
</script>
</c:when>
</c:choose>
</head>
<body>
<form method="post" action="${contextPath}/member/login.do">
	<table border="1" width="400" align="center">
	<tr align="center">
	<td>아이디<input type="text" name="id" size="10"></td>
	</tr>
	<tr align="center">
	<td>비밀번호<input type="text" name="pwd" size="10"></td>
	</tr>
	<tr align="center">
		<td colspan="2">
			<input type="submit" value="로그인">
			<input type="reset" value="다시입력">
		</td>
	</tr>		
			
	</table>
</form>
</body>
</html>
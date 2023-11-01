<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="/resources/memList.css" rel="stylesheet">
</head>
<body>
	<div class="container-fluid">
		<div class="header">
			<div class="title">
				<h1>회원 목록(관리자용)</h1>
			</div>
			<div class="nav-menu">
				<ul>
					<li><a href="/index.jsp">INDEX</a></li>
				</ul>
			</div>
		
		</div>
		<div class="list-container">
			<div class="list-board">
				<table class="list-table">
					<tr>
						<th>ID</th>
						<th>PW</th>
						<th>E-MAIL</th>
						<th>나이</th>
						<th>계정생성일</th>
						<th>마지막접속</th>
					</tr>
					<c:forEach items="${mvo}" var="mvo">
					<tr>
						<td>${mvo.id}</td>
						<td>${mvo.pw}</td>
						<td>${mvo.email}</td>
						<td>${mvo.age}</td>
						<td>${mvo.joinDate}</td>
						<td>${mvo.lastLogin}</td>
					</tr>	
					</c:forEach>
				</table>
			</div>
		</div>
	
	</div>
	
</body>
</html>
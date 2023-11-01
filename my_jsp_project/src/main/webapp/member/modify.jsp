<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="/resources/memberModify.css" rel="stylesheet">
</head>
<body>
	<div class="container-fluid">
	
		<div class="header">
			<div class="title">
				<h1>회원정보 수정</h1>
			</div>
			<div class="nav-menu">
				<ul>
					<li><a href="/index.jsp">INDEX</a></li>
				</ul>
			</div>
		</div>
		
		
		<form action="/mem/update" method="post">
			<div class="modify-container">
				<div class="modify-board">
					<div class="modify-board">
						<table class="modify-table">
							<tr>
								<th>ID</th>
								<td><input type="text" name="id" value="${ses.id}" readonly="readonly">
							</tr>
							<tr>
								<th>PW</th>
								<td><input type="text" name="pw" value="${ses.pw}">
							</tr>
							<tr>
								<th>E-MAIL</th>
								<td><input type="text" name="email" value="${ses.email}">
							</tr>
							<tr>
								<th>age</th>
								<td><input type="text" name="age" value="${ses.age}" readonly="readonly">
							</tr>
							<tr>
								<th>age</th>
								<td><input type="text" name="joinDate" value="${ses.joinDate}" readonly="readonly">
							</tr>
						</table>
					</div>
					<div class="btn-div">
						<div id="btn">
							<button type="submit" class="modBtn">수정완료</button>
							<a href="/mem/remove"><button type="button" class="outBtn">회원탈퇴</button></a>
						</div>
						
					</div>
				</div>
			
			</div>
		</form>
		
	</div>
</body>
</html>
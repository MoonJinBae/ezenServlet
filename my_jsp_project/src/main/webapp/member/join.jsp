<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="/resources/join.css" rel="stylesheet">
</head>
<body>
	<div class="container-fluid">
		<div class="header">
			<div class="title">
				<h1>회원가입</h1>
			</div>
			<div class="nav-menu">
				<ul>
					<li><a href="/index.jsp">INDEX</a></li>
				</ul>
			</div>
		</div>
		
		
		<form action="/mem/register" method="post">
			<div class="join-container">
				<div class="join-board">
					<table class="join-table">
						<tr class="tr">
							<th>ID</th>
							<td><input type="text" name="id"></td>
						</tr>
						<tr class="tr">
							<th>PW</th>
							<td><input type="text" name="pw"></td>
						</tr>
						<tr class="tr">
							<th>EMAIL</th>
							<td><input type="text" name="email"></td>
						</tr>
						<tr class="tr">
							<th>AGE</th>
							<td><input type="text" name="age"></td>
						</tr>
					</table>
				</div>
				<div class="btn-div">
					<button type="submit" class="joinBtn">가입</button>
				</div>
			</div>
		</form>
		
		
	</div>
</body>
</html>
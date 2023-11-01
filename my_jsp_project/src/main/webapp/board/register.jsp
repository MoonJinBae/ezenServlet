<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="/resources/boardRegister.css" rel="stylesheet">
</head>
<body>
	<div class="container-fluid">
	
		<form action="/brd/writing" method="post" enctype="multipart/form-data">
			<div class="register-container">
				<div class="register-board">
					<table class="register-table">
						<tr>
							<th>제목</th>
							<td><input type="text" name="title"></td>
						</tr>
						<tr>
							<th>작성자</th>
							<td><input type="text" name="writer"></td>
						</tr>
						<tr>
							<th>내용</th>
							<td><textarea rows="10" cols="60" name="content"></textarea></td>
						</tr>
						<tr>
							<th>첨부파일</th>
							<td><input type="file" name=img_file></td>
						</tr>
					</table>
				</div>
				<div class="btn-div">
					<button type="submit" class="add">등록</button>
					<a href="/index.jsp"><button type="button" class="index">INDEX</button></a>		
				</div>
			</div>
		</form>
	</div>
</body>
</html>
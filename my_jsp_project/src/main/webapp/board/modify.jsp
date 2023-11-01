<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="/resources/modify.css" rel="stylesheet">
</head>
<body>
	<div class="container-fluid">
		<div class="header">
			<div class="title">
				<h1>게시글 수정</h1>
			</div>
			<div class="nav-menu">
				<ul>
					<li><a href="/index.jsp">INDEX</a></li>
					<li><a href="/brd/list">게시글목록</a></li>
				</ul>
			</div>
		</div>
		
			
			
		<form action="/brd/update" method="post" enctype="multipart/form-data">
			<div class="modify-container">
				<div class="modify-board">
					<table class="modify-table	">
						<tr>
							<th>글번호</th>
							<td><input type="text" name="bno" class="bno" value="${bvo.bno}" readonly="readonly"></td>
						</tr>
						<tr>
							<th>제목</th>
							<td><input type="text" name="title" class="mod-title" value="${bvo.title}"></td>
						</tr>
						<tr>
							<th>작성자</th>
							<td>${bvo.writer}</td>
						</tr>
						<tr>
							<th>내용</th>
							<td><textarea name="content" class="mod-title">${bvo.content}</textarea></td>
						</tr>
						<tr>
							<th>첨부파일</th>
							<td>
								<input type="hidden" name="new_file"value="${bvo.img_file}">
								<input type="file" name="new_file" accept="image/png, image/jpg, image/gif, image/jpeg">					
							</td>
						</tr>
						<tr>
							<th>조회수</th>
							<td>${bvo.hit}</td>
						</tr>
						<tr>
							<th>작성일</th>
							<td>${bvo.regdate}</td>
						</tr>
						<tr>
							<th>수정일</th>
							<td>${bvo.moddate}</td>
						</tr>
					</table>
				</div>
				<div class="btn-div">
					<a href="/brd/detail?bno${bvo.bno}"><button type="submit" class="modBtn">수정완료</button></a>
				</div>	
			</div>
		</form>
		
		
		
		
		
	</div>

</body>
</html>
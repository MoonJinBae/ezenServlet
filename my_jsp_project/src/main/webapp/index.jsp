<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
<link href="/resources/index.css" rel="stylesheet">
</head>
<body>
	<div class="container-fluid">
		<!-- 헤더 -->
		<div class="header">
			<div class="title">
				<h1>INDEX PAGE</h1>
			</div>
			<div class="nav-menu">
			<!-- 메뉴 영역 -->
				<ul>
					<li><a href="/brd/register">게시글쓰기</a></li>
					<li><a href="/brd/list">게시글목록</a></li>
					<li><a href="/mem/list">회원목록</a></li>
					<li><a href="/mem/modify">회원정보수정</a></li>
				</ul>
			</div>
			<div class="nav-join">
				 <div>
					<c:if test="${not empty ses.id}">
					 <a href="/mem/logout"><button type="button" class="btn btn-outline-light">로그아웃</button></a>
					</c:if>	
				 </div>
				 <div>
					<a href="/mem/join"><button class="btn btn-outline-light">회원가입</button></a>
				 </div>
			</div>
		</div>

		<div>
		
			<!-- 로그인 정보 영역 -->
			<c:if test="${not empty ses.id}">
			<div class="login-container">
				<div id="loginInfo">
					<dl id="login-dl"></dl>
				</div>
				<div id="login-board-list">
					<div>
						<h2 class="writer-info">${ses.id}님의 작성글</h2>
					</div>
					<table class="login-table">
						<tr>
							<th class="login-th"><h1>글번호</h1></th>
							<th class="login-th"><h1>제목</h1></th>
							<th class="login-th"><h1>작성일</h1></th>
						</tr>
						<c:forEach items="${loginBoardList}" var="bvo">
						<tr>
							<td class="login-td">${bvo.bno}</td>
							<td class="login-td">${bvo.title}</td>
							<td class="login-td">${bvo.regdate}</td>
						</tr>
						</c:forEach>
					</table>
				</div>

			</div>
			</c:if>


			<!-- 로그인 영역 -->
			<c:if test="${empty ses.id}">
			<form action="/mem/login" method="post">
			 	<div class="login">
					<div class="form-floating mb-3 id">
						<input type="text" class="form-control" name="id" id="floatingInput" placeholder="ID"> 
						<label for="floatingInput">ID</label>
					</div>
					<div class="form-floating pw">
						<input type="password" class="form-control" name="pw" id="floatingPassword" placeholder="Password"> 
						<label for="floatingPassword">Password</label>
					</div>
					<div class="loginBtn">
						<button type="submit" class="btn btn-outline-light">로그인</button>
					</div>
			 	</div>
			</form>
			</c:if>
			
		</div>
		
	</div>
</body>
<script>
	const msg_login = `<c:out value="${msg_login}" />`;
	if(msg_login === "0"){
		alert("로그인 정보가 일치하지 않습니다.");
	}
	
	const loginInfo = `<c:out value="${ses.id}" />`;
	if(loginInfo != null){
		document.getElementById('login-dl').innerHTML = `<dd>${ses.id} 님이 로그인 하였습니다.</dd><dd>계정생성일 : ${ses.joinDate}</dd><dd>마지막접속 : ${ses.lastLogin}</dd>`;
	}
	
	
</script>
</html>
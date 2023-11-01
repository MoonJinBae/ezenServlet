<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="/resources/boardList.css" rel="stylesheet">
</head>
<body>
	<div class="container-fluid">
		<!-- 헤더 -->
		<div class="header">
			<div class="title">
				<h1>게시판</h1>
			</div>
			<div class="nav-menu">
				<ul>
					<li><a href="/index.jsp">INDEX</a></li>
					<li><a href="/brd/register">게시글쓰기</a></li>
				</ul>
			</div>
		</div>
	</div>
	
	
	<div class="list-container">
			<!-- 검색 영역 -->
		<form action="/brd/list" method="get">
			<div class="search-div">
				<c:set value="${ph.pgvo.type}" var="typed" />
				<div>
					<select name="type" id="select">
						<option ${typed == null ? 'selected' : ''}>검색메뉴</option>
						<option value="t" ${typed eq 't' ? 'selected' : ''}>제목</option>
						<option value="w" ${typed eq 'w' ? 'selected' : ''}>작성자</option>
						<option value="c" ${typed eq 'c' ? 'selected' : ''}>내용</option>
						<option value="tw" ${typed eq 'tw' ? 'selected' : ''}>제목+작성자</option>
						<option value="tc" ${typed eq 'tc' ? 'selected' : ''}>제목+내용</option>
						<option value="wc" ${typed eq 'wc' ? 'selected' : ''}>작성자+내용</option>
						<option value="twc" ${typed eq 'twc' ? 'selected' : ''}>제목+작성자+내용</option>
					</select>
				</div>
				<div>
					<input type="text" id="keyword" name="keyword" value="${ph.pgvo.keyword}" placeholder="Search">
				</div>
				<div>
					<input type="hidden" name="pageNo" value="${ph.pgvo.pageNo}">
				</div>
				<div>
					<button type="submit" id="searchBtn">검색</button>
				</div>
			</div>
		</form>
		<div class="list-board">
			<table class="list-table">
				<tr>
					<th>글번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>댓글수</th>
					<th>작성일</th>
					<th>수정일</th>
					<th>조회수</th>
				</tr>
				<c:forEach items="${list}" var="bvo">
				<tr>
					<td><a href="/brd/detail?bno=${bvo.bno}">${bvo.bno}</a></td>
					<td><a href="/brd/detail?bno=${bvo.bno}">${bvo.title}</a></td>
					<td>${bvo.writer}</td>
					<td>${bvo.cmtCount}</td>
					<td>${bvo.regdate}</td>
					<td>${bvo.moddate}</td>
					<td>${bvo.hit}</td>
				</tr>
				</c:forEach>
			</table>
		</div>
		
		<div class="pageing-div">
			<!-- 페이지네이션 영역 -->
			<!-- prev -->
			<c:if test="${ph.prev}">
			<div class="paging-prev">
				<a href="/brd/list?pageNo=${ph.startPage-1}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}">
					<button type="button" id="searchBtn">PREV</button>
				</a>
			</div>
			</c:if>
			<!-- page 번호 -->
			<div class="pagin-no">
			<c:forEach begin="${ph.startPage}" end="${ph.endPage}" var="i">
				<a href="/brd/list?pageNo=${i}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}">
					${i}
				</a>
			</c:forEach>
			</div>
			<!-- next -->
			<c:if test="${ph.next}">
			<div class="pagin-next">
				<a href="/brd/list?pageNo=${ph.endPage+1}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}">
					<button type="button" id="searchBtn">NEXT</button>
				</a>
			</div>
			</c:if>
			<div class="page-total">${ph.pgvo.pageNo }/${ph.totalCount}</div>
		</div>

		
	</div>
</body>
</html>
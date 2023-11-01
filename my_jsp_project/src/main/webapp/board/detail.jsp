<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
<link href="/resources/detail.css" rel="stylesheet">
</head>
<body>
	<div class="container-fluid">
	
		<!-- 헤더 -->
		<div class="header">
			<div class="title">
				<h1>${bvo.writer}님의 작성글</h1>
			</div>
			<div class="nav-menu">
			<!-- 메뉴 영역 -->
				<ul>
					<li><a href="/index.jsp">INDEX</a></li>
					<li><a href="/brd/list">게시글목록</a></li>
				</ul>
			</div>
		</div>
		<div class="detail-container">
			<div class="detail-board">
				<table class="detail-table">
					<tr>
						<th>글번호</th>
						<td>${bvo.bno}</td>
					</tr>
					<tr>
						<th>제목</th>
						<td>${bvo.title}</td>
					</tr>
					<tr>
						<th>작성자</th>
						<td>${bvo.writer}</td>
					</tr>
					<tr>
						<th>내용</th>
						<td>${bvo.content}</td>
					</tr>
					<tr>
						<th>댓글수</th>
						<td>${bvo.cmtCount}</td>
					</tr>
					<tr>
						<th>첨부파일</th>
						<td>
						<c:choose>
							<c:when test="${not empty bvo.img_file}">
							<img src="/_fileUpload/${bvo.img_file}">
							</c:when>
							<c:otherwise>
								<span>첨부파일 없음</span>
							</c:otherwise>
						</c:choose>
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
			
		<!-- 수정, 삭제 -->	
			<div class="remove-btn modify-btn">
				<c:if test="${ses.id eq bvo.writer}">
				<div>
					<a href="/brd/modify?bno=${bvo.bno}"><button type="button" id="modifyBtn">수정</button></a>
				</div>
				<div>
					<a href="/brd/remove?bno=${bvo.bno}"><button type="button" id="removeBtn">삭제</button></a>
				</div>
				</c:if>
			</div>

		
			<!-- 댓글 등록 영역 -->
			<div class="comment-container">
				<div class="comment-div">
					<div>
						<span>댓글 쓰기</span>
					</div>
					<div class="comment-add">
						<div>
							<input type="text" id="cmtWriter" value="${empty ses.id ? '' : ses.id}" readonly="readonly" placeholder="ID">
						</div>
						<div>
							<input type="text" id="cmtContent" placeholder="댓글 입력">
						</div>
						<div>
						 	<button type="button" id="cmtAddBtn">댓글등록</button>
						</div>
					</div>
				</div>
					<!-- 댓글 표시 영역 -->
				<div class="accordion" id="accordionExample">
					<!-- 댓글 아이템 1개 표시 영역 -->
					<div class="accordion-item">
						<h2 class="accordion-header">
							<button class="accordion-button" type="button"
								data-bs-toggle="collapse" data-bs-target="#collapseOne"
								aria-expanded="true" aria-controls="collapseOne">
								cno, writer, reg_date
							</button>
						</h2>
						<div id="collapseOne" class="accordion-collapse collapse show"
							data-bs-parent="#accordionExample">
							<div class="accordion-body">
								content
							</div>
							
						</div>
					</div>
				</div>
			</div>
		
		
		</div>
		
	</div>
</body>
	<script type="text/javascript">
		const bnoVal = `<c:out value="${bvo.bno}" />`;
	</script>
	<script src="/resources/board_detail.js"></script>
	<script type="text/javascript">
		printCommentList(bnoVal);
	</script>
</html>
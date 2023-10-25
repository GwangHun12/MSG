<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%-- <%@ page session="false" %> --%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>공지사항 상세</title>
		<link rel="stylesheet" href="/resources/css/summernote/qDetail.css?after" />
    <link rel="stylesheet" href="/resources/css/reset.css?after" />
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css"
	  	integrity="sha256-7ZWbZUAi97rkirk4DcEp4GWDPkWpRMcNaEyXGsNXjLg=" crossorigin="anonymous">
	  	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/summernote@0.8.20/dist/summernote-lite.min.css"
		integrity="sha256-IKhQVXDfwbVELwiR0ke6dX+pJt0RSmWky3WB2pNx9Hg=" crossorigin="anonymous">
		<script src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" 
		rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" 
		crossorigin="anonymous">
		<link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" />
        <link href="/resources/css/styles.css" rel="stylesheet" />
        <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
        <style>
        .comment-section {
            margin-top: 20px;
            border: 1px solid #ccc;
            padding: 10px;
        }
        .comment {
            margin-bottom: 10px;
            border: 1px solid #ddd;
            padding: 10px;
        }
        table {
        border-collapse: collapse; /* 셀 간격을 없애기 위해 사용 */
        width: 100%; /* 테이블 폭 100%로 설정 (가운데 정렬을 유지하려면) */
    }

    table, th, td {
        border: 1px solid #000; /* 셀 경계선 스타일 설정 */
    }

    th, td {
        padding: 5px; /* 셀 패딩 설정 (원하는 값으로 조절) */
        text-align: center; /* 텍스트 가운데 정렬 */
    }

    tr:nth-child(odd) {
        background-color: #f2f2f2; /* 홀수 행 배경색 설정 (선택적) */
    }
    </style>
	</head>
	<body class="sb-nav-fixed">
	<nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
            <!-- Navbar Brand-->
            <a class="navbar-brand ps-3" href="/">MSG</a>
            <!-- Sidebar Toggle-->
            <button class="btn btn-link btn-sm order-1 order-lg-0 me-4 me-lg-0" id="sidebarToggle" href="#!"><i class="fas fa-bars"></i></button>
            <a class="navbar-brand ps-5" href="/notice/list.do">공지사항</a>
            <a class="navbar-brand" href="/qa/qalist.do">질문게시판</a>
            <a class="navbar-brand" href="/project/my.do">모임페이지</a>
            <!-- Navbar Search-->
            <!-- 공간 띄우기용-->
            <form class="d-none d-md-inline-block form-inline ms-auto me-0 me-md-3 my-2 my-md-0">
                <div class="input-group">
<!--                     <input class="form-control" type="text" placeholder="Search for..." aria-label="Search for..." aria-describedby="btnNavbarSearch" /> -->
<!--                     <button class="btn btn-primary" id="btnNavbarSearch" type="button"><i class="fas fa-search"></i></button> -->
                </div>
            </form>
            <!-- Navbar-->
            <ul class="navbar-nav ms-auto ms-md-0 me-3 me-lg-4">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false"><i class="fas fa-user fa-fw"></i></a>
                    <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
                        <li><a class="dropdown-item" href="/project/my.do">마이페이지</a></li>
                        <li><hr class="dropdown-divider" /></li>
                        <li><a class="dropdown-item" href="/user/login.do">로그인</a></li>
                    </ul>
                </li>
            </ul>
        </nav>
        <br><br><br>

<!-- 		<div class="col-md-10 col-md-offset-1" id="contents"> -->
<!-- 				<form id="passwordForm" method="post"> -->
<!-- 					<input type="password" id="enteredPassword" name="enteredPassword" placeholder="비밀번호"> -->
<%-- 		            <input type="hidden" id="qaNo" value="${qa.qaNo}"> --%>
<%-- 		            <input type="hidden" id="qaSecret" value="${qa.qaSecret}"> --%>
<%-- 		            <input type="hidden" id="userId" value="${userId}"> --%>
<!-- 		            <button type="button" onclick="checkPasswordAndNavigate();">확인</button> -->
<!-- 				</form> -->
<!-- 					<table class="table table-bordered req" id="req"> -->
<!-- 						<tr> -->
<!-- 							<td class="qaTitle">제목</td> -->
<!-- 							<td class="qaTitle"> -->
<%-- 								<input type="text" class="qatitle" name="qatitle" value="${qa.qaTitle }" readonly="readonly"> --%>
<!-- 							</td> -->
<!-- 						</tr> -->
<!-- 						<tr> -->
<!-- 							<td class="qaContent">내용</td> -->
<!-- 							<td class="qaContent"> -->
<%-- 								<textarea class="qaContent" name="qaContent" cols="110" readonly="readonly">${qa.qaContent }</textarea> --%>
<!-- 							</td> -->
<!-- 						</tr> -->
<!-- 						<tr> -->
<!-- 							<td id="qaSecret">비밀</td> -->
<%-- 							<td id="secretContent"><input type="radio" id="public" class="qaSecret" name="qaSecret" value="${qa.qaSecret }" checked>  --%>
<!-- 								<label for="public">공개글</label>&nbsp;  -->
<%-- 								<input type="radio" id="qaSecret" class="qaSecret" name="qaSecret" value="${qa.qaSecret }">  --%>
<!-- 								<label for="qaSecret">비밀글</label> -->
<!-- 							</td> -->
<!-- 						</tr> -->
<!-- 					</table> -->
				<div class="container">
				<ul class="detail-list">
					<li>
						<label class="detail-label">작성자</label>
						<input class="detail-input" type="text" name="userId" value="${qa.userId }" readonly >
					</li>
<%-- 					<c:if test="${qa.qaSecret ne 0 }"> --%>
<!-- 						<li> -->
<!-- 							<label class="detail-label">비밀번호</label> -->
<%-- 							<input class="detail-input" type="text" name="password" value="${qa.password }"  readonly> --%>
<!-- 						</li> -->
<%-- 					</c:if> --%>
					<li>
						<label class="detail-label">작성일</label>
						<p class="detail-date">
	                		<fmt:formatDate pattern="yyyy-MM-dd" value="${qa.qCreateDate}" />
	           		 	</p>
					</li>
					<li>
						<label class="detail-label">제목</label>
						<input class="detail-input" type="text" name="qaTitle" value="${qa.qaTitle }"  readonly>
					</li>
					<li>
						<label class="detail-label">내용</label>
						<p class="detail-content">${qa.qaContent } </p>
<%-- 						<input class="detail-input" type="text" name="qaContent" value="${qa.qaContent }"  readonly> --%>
					</li>
					<li>
						<label for="public">공개글</label>
						<input type="radio" id="public" class="qaSecret" name="qaSecret" value="0" <c:if test="${qa.qaSecret == 0}">checked</c:if>>
						<label for="qaSecret">비밀글</label>
						<input type="radio" id="qaSecret" class="qaSecret" name="qaSecret" value="1" <c:if test="${qa.qaSecret == 1}">checked</c:if>>
					</li>
				</ul>
				<c:url var="qaDelUrl" value="/qa/qadelete.do">
			        <c:param name="qaNo" value="${qa.qaNo}"></c:param>
			        <c:param name="userId" value="${qa.userId}"></c:param>
			    </c:url>
			    <c:url var="modifyUrl" value="/qa/qamodify.do">
			        <c:param name="qaNo" value="${qa.qaNo}"></c:param>
			        <c:param name="userId" value="${qa.userId}"></c:param>
			    </c:url>
			
	    <div>
<%-- 	        <c:if test="${userId eq 'admin'}"> --%>
	            <button type="button" onclick="showModifyPage('${modifyUrl}');">수정하기</button>
	            <button type="button" onclick="deleteQa('${qaDelUrl}');">삭제하기</button>
<%-- 	        </c:if> --%>
	        <button type="button" onclick="showQaList();">목록으로</button>
	        <button type="button" onclick="javascript:history.go(-1);">뒤로가기</button>
	    </div>
	    </div>
			<!-- 댓글 등록 -->
			<form action="/qreply/add.do" method="post" style="width: 68%; margin: auto;">
			<input type="hidden" name="replyNo" value="0">
			<input type="hidden" name="qaNo" value="${qa.qaNo }">
				<table width="500" border="1" style="margin: 0 auto;">
					<tr style="display:flex; align-items:center;justify-content: center;">
						<td>
							<input class="detail-input" type="text" name="userId" value="${qa.userId}" readonly style="width: 100%;">
						</td>
						<td>
							<input class="detail-input" size="100" name="replyContent" style="height: 37px;">
						</td>
						<td>
							<input type="checkbox" name="secret" value="1" id="secret">
							<label for="secret">비밀 댓글</label>
							<input type="submit" value="완료">
						</td>
					</tr>
				</table>
			</form>
			<!-- 댓글 목록 -->
			<table width="600" border="1" style="width: 68%; margin: auto;">
				<tr>
					<th>작성자</th>
					<th>내용</th>
					<th>작성일</th>
					<th>수정/삭제</th>
				</tr>
				<c:forEach var="qreply" items="${qrList }">
				<tr>
					<td>${qreply.userId }</td>
					<td>
		                <c:choose>
                    <c:when test="${qreply.secret == 1}">
				        <c:choose>
				            <c:when test="${!qreply.userId eq sessionScope.userId}">
				                [비밀 댓글]
				            </c:when>
				            <c:otherwise>
				                ${qreply.replyContent}
				            </c:otherwise>
				        </c:choose>
				    </c:when>
	                    <c:otherwise>
	                        ${qreply.replyContent}
	                    </c:otherwise>
	                </c:choose>
<%-- 				        <c:choose> --%>
<%-- 		                    <c:when test="${qreply.secret == 1 && !qreply.userId eq sessionScope.userId}"> --%>
<!-- 		                        [비밀 댓글] -->
<%-- 		                    </c:when> --%>
<%-- 		                    <c:otherwise> --%>
<%-- 		                        ${qreply.secret == 1 && qreply.userId eq sessionScope.userId ? qreply.replyContent : '[비밀 댓글]'} --%>
<%-- 		                    </c:otherwise> --%>
<%-- 		                </c:choose> --%>
		            </td>
<%-- 					<td>${qreply.replyContent }</td> --%>
					<td>${qreply.rCreateDate }</td>
					<td>
						<a href="javascript:void(0);" onclick="showReplyModifyForm(this);">수정하기</a>
						<c:url var="delUrl" value="/qreply/delete.do">
							<c:param name="replyNo" value="${qreply.replyNo }"></c:param>
							<c:param name="userId" value="${qreply.userId }"></c:param>
							<c:param name="qaNo" value="${qa.qaNo }"></c:param>						
						</c:url>
						<a href="javascript:void(0);" onclick="deleteReply('${delUrl}');">삭제하기</a>
					</td>
				</tr>
				<tr id="replyModifyForm" style="display:none;">
					<%-- <form action="/reply/update.kh" method="post">
						<input type="hidden" name="replyNo" value="${reply.replyNo }">
						<input type="hidden" name="refBoardNo" value="${reply.refBoardNo }">
						<td colspan="3"><input type="text" size="50" name="replyContent" value="${reply.replyContent }"></td>
						<td><input type="submit" value="완료"></td>
					</form> --%>
					<td colspan="1"><input id="replyContent" type="text" size="50" name="replyContent" value="${qreply.replyContent }"></td>
					<td><input type="button" onclick="replyModify(this,'${qreply.qaNo}','${qreply.replyNo }')" value="완료"></td>
				</tr>
				</c:forEach>
				</table>
				</div>
			<script>
// 			const imageUpload = document.getElementById("imageUpload");

// 		    const imagePreview = document.getElementById("imagePreview");
// 		    imageUpload.addEventListener("change", function () {
// 		        const file = this.files[0];

// 		        if (file) {
// 		            const reader = new FileReader();
// 		            reader.onload = function (e) {
// 		                imagePreview.src = e.target.result;
// 		            };
// 		            reader.readAsDataURL(file);
// 		        } else {
// 		            imagePreview.src = "#";
// 		        }
// 		    });

				function deleteQa(url){
					location.href=url;
				}
				function deleteReply(url){
					location.href=url;
				}
				function showModifyPage(modifyUrl){
					location.href=modifyUrl;
				}
				function showQaList(){
					location.href="/qa/qalist.do";
				}
				
				function replyModify(obj, qaNo, replyNo) {
// 					DOM 프로그래밍을 이용하는 방법
					const form = document.createElement("form");
					form.action = "/qreply/update.do";
					form.method = "post";
					const input = document.createElement("input");
					input.type = "hidden";
					input.value = replyNo;
					input.name = "replyNo";
					const input2 = document.createElement("input");
					input2.type = "hidden";
					input2.value = qaNo;
					input2.name = "qaNo";
					const input3 = document.createElement("input");
					input3.type = "text";
					// this를 이용해서 내가 원하는 노드 찾기
					input3.value = obj.parentElement.previousElementSibling.firstChild.value;
					input3.name = "replyContent";
					form.appendChild(input);
					form.appendChild(input2);
					form.appendChild(input3);
					
					document.body.appendChild(form);
					form.submit();
				}
				function showReplyModifyForm(obj) {
					if(obj.parentElement.parentElement.nextElementSibling.style.display=="none"){
						obj.parentElement.parentElement.nextElementSibling.style.display="";
						obj.innerText = "수정취소";
					} else{
						obj.parentElement.parentElement.nextElementSibling.style.display="none";
						obj.innerText = "수정하기";
					}
				}
						
// 				<input type="password" id="enteredPassword" name="enteredPassword" placeholder="비밀번호">
// 	            <input type="hidden" id="qaNo" value="${qa.qaNo}">
// 	            <input type="hidden" id="qaSecret" value="${qa.qaSecret}">
// 	            <input type="hidden" id="userId" value="${userId}">
// 	            <button type="button" onclick="checkPasswordAndNavigate();">확인</button>
			</script>
	</body>
</html>
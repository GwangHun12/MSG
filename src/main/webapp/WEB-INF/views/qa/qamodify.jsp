<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시글 수정</title>
<link href="/resources/css/summernote/write.css" rel="stylesheet" />
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
</head>
<body>
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
    <form action="/qa/qamodify.do" method="post" enctype="multipart/form-data" onsubmit="return blankValid();">
			<input type="hidden" name="qaNo" value="${qa.qaNo }">
			<table class="table table-bordered req" id="req">
        <ul>
            <li>
                <label>작성자</label>
                <input type="text" name="userId" value="${qa.userId}" readonly>
            </li>
<!--             <li> -->
<!--                 <label>비밀번호</label> -->
<%--                 <input type="text" name="password" value="${qa.password}"> --%>
<!--             </li> -->
            <li>
                <label>제목</label>
                <input type="text" name="qaTitle" value="${qa.qaTitle}">
            </li>
            <li>
                <label>내용</label>
                <textarea id="summernote" class="qaContent" name="qaContent">${qa.qaContent}</textarea>
            </li>
            <li>
                <label for="public">공개글</label>
                <input type="radio" id="public" class="qaSecret" name="qaSecret" value="0" <c:if test="${qa.qaSecret == 0}">checked</c:if>>
                <label for="qaSecret">비밀글</label>
                <input type="radio" id="qaSecret" class="qaSecret" name="qaSecret" value="1" <c:if test="${qa.qaSecret == 1}">checked</c:if>>
            
            </li>
            <li id="passwordInput" style="display: none;">
			    <label>비밀번호</label>
			    <input type="text" name="password" value="${qa.password}">
			</li>
        </ul>
        <div>
<%--             <c:choose> --%>
<%-- <%--                 <c:when test="${qna.userId eq userId}"> --%>
<!--                     <input type="submit" value="수정완료"> -->
<%-- <%--                 </c:when> --%> 
<%--                 <c:otherwise> --%>
<!--                     <p>게시글 수정 권한이 없습니다.</p> -->
<%--                 </c:otherwise> --%>
<%--             </c:choose> --%>
			<input type="submit" value="수정완료">
            <button type="button" onclick="showQaList();">목록으로</button>
            <button type="button" onclick="javascript:history.go(-1);">뒤로가기</button>
        </div>
        </table>
    </form>
    <script>
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"
		integrity="sha256-u7e5khyithlIdTpu22PHhENmPcRdFiHRjhAuHcs05RI=" crossorigin="anonymous"></script>
		
		<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.20/dist/summernote-lite.min.js"
		integrity="sha256-5slxYrL5Ct3mhMAp/dgnb5JSnTYMtkr4dHby34N10qw=" crossorigin="anonymous"></script>
		
		<!-- language pack -->
		<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.20/dist/lang/summernote-ko-KR.min.js"
		integrity="sha256-y2bkXLA0VKwUx5hwbBKnaboRThcu7YOFyuYarJbCnoQ=" crossorigin="anonymous"></script>
		
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js"
		  integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa" crossorigin="anonymous"></script>
        <script>
	      $('#summernote').summernote({
	        placeholder: '글을 등록하세요',
	        tabsize: 2,
	        height: 200,
	        lang: 'ko-KR'
	      });
    
	    function showQaList() {
	        location.href="/qa/qalist.do";
	    }
	    
	    window.onload = function() {
	        // 비밀글 라디오 버튼에 대한 이벤트 리스너 추가
	        document.getElementById("qaSecret").addEventListener("change", togglePasswordInput);
	        document.getElementById("public").addEventListener("change", togglePasswordInput);

	        // 페이지 로드 시 초기 상태 설정
	        togglePasswordInput();
	    }

	    // 비밀번호 입력란을 토글하는 함수
	    function togglePasswordInput() {
	        var passwordInput = document.getElementById("passwordInput");
	        var qaSecretRadio = document.getElementById("qaSecret");

	        if (qaSecretRadio.checked) {
	            passwordInput.style.display = "block";
	        } else {
	            passwordInput.style.display = "none";
	        }
	    }
	      
    </script>
    </body>
</html>

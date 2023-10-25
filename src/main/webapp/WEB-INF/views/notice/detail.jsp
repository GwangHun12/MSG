<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>공지사항 상세</title>
		<link rel="stylesheet" href="/resources/css/nDetail.css?after" />
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
        <br><br><br><br>
        <div class="container">
			<ul class="detail-list" style="margin: auto; width: 50%;">
				<li>
					<label class="detail-label">작성일</label>
					<p class="detail-date">
                		<fmt:formatDate pattern="yyyy-MM-dd" value="${notice.nCreateDate}" />
           		 	</p>
				</li>
				<li>
					<label class="detail-label">제목</label>
					<input class="detail-input" type="text" name="noticeTitle" value="${notice.noticeTitle }"  >
				</li>
				<li>
					<label class="detail-label">내용</label>
                	<div class="detail-content" id="summernote">${notice.noticeContent}</div>
				</li>
			</ul>
			<c:url var="noticeDelUrl" value="/notice/delete.do">
		        <c:param name="noticeNo" value="${notice.noticeNo}"></c:param>
		    </c:url>
		    <c:url var="modifyUrl" value="/notice/modify.do">
		        <c:param name="noticeNo" value="${notice.noticeNo}"></c:param>
		    </c:url>
	    <div class="button-list" style="margin: auto; width: 50%;">
<%-- 	        <c:if test="${userId eq 'admin'}"> --%>
	            <button type="button" onclick="showModifyPage('${modifyUrl}');">수정하기</button>
	            <button type="button" onclick="deleteNotice('${noticeDelUrl}');">삭제하기</button>
<%-- 	        </c:if> --%>
	        <button type="button" onclick="showNoticeList();">목록으로</button>
	        <button type="button" onclick="javascript:history.go(-1);">뒤로가기</button>
	    </div>
        </div>
			
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
				$(document).ready(function() {
		            $('#summernote').summernote({
		                height: 300, // You can adjust the height as needed
		                toolbar: [],
		                airMode: true,
		                disableDragAndDrop: true,
		                dialogsInBody: true
		            });
		        });

				function deleteNotice(url){
					location.href=url;
				}
				
				function showModifyPage(modifyUrl){
					location.href=modifyUrl;
				}
				function showNoticeList(){
					location.href="/notice/list.do";
				}
						
			</script>
	</body>
</html>
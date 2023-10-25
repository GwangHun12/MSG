<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
		<nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
            <!-- Navbar Brand-->
            <a class="navbar-brand ps-3" href="index.html">MSG</a>
            <!-- Sidebar Toggle-->
            <button class="btn btn-link btn-sm order-1 order-lg-0 me-4 me-lg-0" id="sidebarToggle" href="#!"><i class="fas fa-bars"></i></button>
            <a class="navbar-brand ps-5" href="index.html">공지사항</a>
            <a class="navbar-brand" href="index.html">질문게시판</a>
            <a class="navbar-brand" href="/project/my.do">모임페이지</a>
            <!-- Navbar Search-->
            <!-- 공간 띄우기용-->
            <div class="input-group">
            </div>
            <!-- Navbar-->
            <ul class="navbar-nav ms-auto ms-md-0 me-3 me-lg-4">
            	<div class="navbar-brand">${sessionScope.userId }</div>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false"><i class="fas fa-user fa-fw"></i></a>
                    <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
                    <c:if test="${sessionScope.userId eq null }">
                        <li><a class="dropdown-item" href="/user/register.do">회원가입</a></li>
                        <li><hr class="dropdown-divider" /></li>
                        <li><a class="dropdown-item" href="/">로그인</a></li>
                    </c:if>
                    <c:if test="${sessionScope.userId ne null }">
                        <li><a class="dropdown-item" href="#">마이페이지</a></li>
                        <li><hr class="dropdown-divider" /></li>
                        <li><a class="dropdown-item" href="/user/logout.do">로그아웃</a></li>
                    </c:if>
                    </ul>
                </li>
            </ul>
        </nav>
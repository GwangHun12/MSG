<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
		<title>모임페이지</title>
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" />
        <link href="/resources/css/styles.css" rel="stylesheet" />
        <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
	</head>
	<body class="sb-nav-fixed">
<%-- 		<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include> --%>
        <nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
            Navbar Brand
            <a class="navbar-brand ps-3" href="/">MSG</a>
            Sidebar Toggle
            <button class="btn btn-link btn-sm order-1 order-lg-0 me-4 me-lg-0" id="sidebarToggle" href="#!"><i class="fas fa-bars"></i></button>
            <a class="navbar-brand ps-5" href="/notice/list.do">공지사항</a>
            <a class="navbar-brand" href="/qa/qalist.do">질문게시판</a>
            <a class="navbar-brand" href="/project/my.do">모임페이지</a>
         
                <div class="input-group">
                </div>
           
            <ul class="navbar-nav ms-auto ms-md-0 me-3 me-lg-4">
            	<div class="navbar-brand">${sessionScope.userId }</div>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false"><i class="fas fa-user fa-fw"></i></a>
                    <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
                    <c:if test="${sessionScope.userId eq null }">
                        <li><a class="dropdown-item" href="/user/register.do">회원가입</a></li>
                        <li><hr class="dropdown-divider" /></li>
                        <li><a class="dropdown-item" href="/user/login.do">로그인</a></li>
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
        <div id="layoutSidenav">
            <div id="layoutSidenav_nav">
                <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
                    <div class="sb-sidenav-menu">
                        <div class="nav">
                            <div class="sb-sidenav-menu-heading">MAIN</div>
                            <a class="nav-link" href="/project/my.do">
                                <div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>
                                My Page
                            </a>
                            <div class="sb-sidenav-menu-heading">참여중인 프로젝트</div>
                            <c:forEach var="project" items="${pList }">
	                            <a class="nav-link" href="/project/main.do?projectNo=${project.projectNo }">
	                                <div class="sb-nav-link-icon"><i class="fas fa-columns"></i></div>
	                                ${project.projectName }
	                            </a>
	                        </c:forEach>
                        </div>
                    </div>
                    <div class="sb-sidenav-footer">
                        <div class="small">Logged in as:</div>
                        Start Bootstrap
                    </div>
                </nav>
            </div>
            <div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid px-4">
                        <h1 class="mt-4">${sessionScope.userNickName }</h1>
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item active">My Page</li>
                        </ol>
                        <div class="row">
                            <div class="col-xl-3 col-md-6">
                                <div class="card bg-primary text-white mb-4">
                                    <div class="card-body d-flex align-items-center justify-content-between">
                                    	<a class="text-white stretched-link" href="javascript:void(0);" onclick="addProject();" style="text-decoration: none;">프로젝트 생성</a><i class="fas fa-angle-right"></i>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xl-6">
                                <div class="card mb-4">
                                    <div class="card-header">
                                        <i class="fas fa-chart-bar me-1"></i>
                                        일정 요약
                                    </div>
<!--                                     <div class="card-body"><canvas id="myAreaChart" width="100%" height="40"></canvas></div> -->
                                    <div class="card-body">
										<table id="datatablesSimple">
		                                    <thead>
		                                        <tr>
		                                            <th>프로젝트 이름</th>
		                                            <th>일정 이름</th>
		                                            <th>Start date</th>
		                                        </tr>
		                                    </thead>
		                                    <tfoot>
		                                        <tr>
		                                            <th>프로젝트 이름</th>
		                                            <th>일정 이름</th>
		                                            <th>Start date</th>
		                                        </tr>
		                                    </tfoot>
		                                    <tbody>
			                                    <c:forEach var="Calendar" items="${cList }">
			                                        <tr>
			                                            <td>${Calendar.project.projectName }</td>
			                                            <td>${Calendar.title }</td>
			                                            <td>${Calendar.start }</td>
			                                        </tr>
			                                    </c:forEach>
		                                    </tbody>
		                                </table>
									</div>
                                </div>
                            </div>
                            <div class="col-xl-6">
                                <div class="card mb-4">
                                    <div class="card-header">
                                        <i class="fas fa-chart-bar me-1"></i>
                                        공지 요약
                                    </div>
<!--                                     <div class="card-body"><canvas id="myBarChart" width="100%" height="40"></canvas></div> -->
                                    <div class="card-body">
										<table id="datatablesSimple2">
											<thead>
												<tr>
													<th>공지사항 번호</th>
													<th>공지사항 이름</th>
													<th>공지사항 작성일</th>
												</tr>
											</thead>
											<tfoot>
												<tr>
													<th>프로젝트 이름</th>
													<th>공지사항 이름</th>
													<th>공지사항 작성일</th>
												</tr>
											</tfoot>
											<tbody>
												<c:forEach var="Notice" items="${nList }">
			                                        <tr>
			                                            <td>${Notice.noticeNo }</td>
			                                            <td><a href="/notice/detail.do?noticeNo=${Notice.noticeNo }">${Notice.noticeTitle }</a></td>
			                                            <td>${Notice.nCreateDate }</td>
			                                        </tr>
			                                    </c:forEach>
											</tbody>
										</table>
									</div>
                                </div>
                            </div>
                        </div>
                        <div class="card mb-4">
                            <div class="card-header">
                                <i class="fas fa-table me-1"></i>
                                캘린더
                            </div>
                            <div class="card-body">
                            	<jsp:include page="/WEB-INF/views/calendar/calendar.jsp"></jsp:include>
                            </div>
                        </div>
                    </div>
                </main>
                <footer class="py-4 bg-light mt-auto">
                    <div class="container-fluid px-4">
                        <div class="d-flex align-items-center justify-content-between small">
                            <div class="text-muted">Copyright &copy; Your Website 2023</div>
                            <div>
                                <a href="#">Privacy Policy</a>
                                &middot;
                                <a href="#">Terms &amp; Conditions</a>
                            </div>
                        </div>
                    </div>
                </footer>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="/resources/js/scripts.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
        <script src="/resources/demo/chart-area-demo.js"></script>
        <script src="/resources/demo/chart-bar-demo.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js" crossorigin="anonymous"></script>
        <script src="/resources/js/datatables-simple-demo.js"></script>
        <script>
			function addProject(){
				var userInput = window.prompt("프로젝트 제목을 입력해주세요:", "");
				if (userInput != null) {
					location.href="/project/createProject.do?projectName=" + userInput;
				} else {
					  alert("입력이 취소되었거나 아무 내용도 입력하지 않았습니다.");
					}
			};
		</script>
	</body>
</html>
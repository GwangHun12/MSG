<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>질문 게시판</title>
<link rel="stylesheet" href="/resources/css/reset.css?after" />
		<link rel="stylesheet" href="/resources/css/nList.css" />
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
<!--         <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br> -->
        <div>
		<table>
			<colgroup>
				<col width="10%">
				<col width="60%">
				<col width="20%">
				<col width="20%">
			</colgroup>
			<thead>
				<tr>
					<th>글번호</th>
					<th>제목</th>
					<th>작성일</th>
					<th>조회수</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="qa" items="${qList }" varStatus="i">
				<tr>
				<td>${qList.size() - i.count + 1}</td>
				<c:url var="detailUrl" value="/qa/qadetail.do">
					<c:param name="qaNo" value="${qa.qaNo }"></c:param>
				</c:url>
				<td>
				<c:choose>
                <c:when test="${qa.qaSecret eq 1}">
                    <a id="qaTitle" class="qaTitle" href="javascript:void(0);" onclick="passWord(${qa.qaNo});">
                        비밀글 입니다.
                        <img class="secret_img" src="/resources/img/secret.jpg">
                    </a>
                </c:when>
                <c:otherwise>
                    <a id="qaTitle" class="qaTitle" href="${detailUrl}">
                        <c:out value="${qa.qaTitle}" />
                    </a>
                    <span class="commentCount">[${qa.commentCount}]</span>
                </c:otherwise>
            	</c:choose>
				</td>


<%-- 				<c:if test="${qa.qaSecret == true}"> --%>
<%-- 				    <c:choose> --%>
<%-- 				        <c:when test="${qa.userId eq qa.vo.userId || qa.authorities eq '[ROLE_ADMIN, ROLE_USER]'}"> <!-- 작성자이거나 관리자일 때 --> --%>
<%-- 				            <td><a href="get${pageMaker.cri.listLink}&qaNo=${qa.qaNo}" class="text-secondary text-center"><i class="icofont-lock"></i><c:out value="${qa.qaTitle}"/><span class="text-muted small"> </span></a></td> --%>
<%-- 				        </c:when> --%>
<%-- 				        <c:otherwise> --%>
<%-- 				            <td class="text-secondary"><i class="icofont-lock"></i><c:out value="${qa.qaTitle}"/><span class="text-muted small"> </span></td> --%>
<%-- 				        </c:otherwise> --%>
<%-- 				    </c:choose>                                             --%>
<%-- 				</c:if> --%>
				<td><fmt:formatDate pattern="yyyy-MM-dd" value="${qa.qCreateDate }"/></td>
				<td>${qa.viewCount }</td>
				</tr>
				</c:forEach>
			</tbody>
			<tfoot>
			<tr align="center">
					<td colspan="5">
					<c:if test="${pInfo.startNavi != 1 }">
					<c:url var="prevUrl" value="/qa/qalist.do">
						<c:param name="page" value="${pInfo.startNavi - 1 }"></c:param>
					</c:url>
					<a href="${prevUrl }">[이전]</a>
					</c:if>
						<c:forEach begin="${pInfo.startNavi }" end="${pInfo.endNavi }" var="p">
							<c:url var="pageUrl" value="/qa/qalist.do">
								<c:param name="page" value="${p }"></c:param>
							</c:url>
							<a href="${pageUrl }">${p }</a>&nbsp;
						</c:forEach>
					<c:if test="${pInfo.endNavi != pInfo.naviTotalCount }">
						<c:url var="nextUrl" value="/qa/qalist.do">
							<c:param name="page" value="${pInfo.endNavi + 1 }"></c:param>
						</c:url>
					<a href="${nextUrl }">[다음]</a>
					</c:if>
						
					</td>
				</tr>
				<tr>
				<td colspan="4">
					<form action="/qa/qasearch.do" method="get">
						<select name="searchCondition">
							<option value="qaTitle" <c:if test="${searchCondition == 'qaTitle' }">selected</c:if>>제목</option>
						</select>
						<input type="text" name="searchKeyword" placeholder="검색어를 입력하세요">
						<input type="submit" value="검색">
					</form>
					</td>
					<td>
					    <button type="button" onclick="showRegisterForm();" style="float: right; margin-right: 100%; font-size: 16px; width: 180%">질문등록</button>
					</td>
				</tr>
<!-- 					<div class="button-container"> -->
<!-- 						<button type="button" onclick="showRegisterForm();">공지등록</button> -->
<!-- 					</div> -->
			</tfoot>
		</table>
		<input type="hidden" id="passwordValue" value="password">
        </div>
		<script>
        const showRegisterForm = () => {
            location.href = "/qa/qawrite.do";
        }
        
        
        
        
        var testV = 0;
        function passWord(qaNo) {
            var pass1 = prompt('비밀번호를 입력하세요:', '비밀번호를 입력하세요'); // 초기에 비밀번호를 묻는 메시지
//             while (testV < 3) {
//                 if (!pass1) {
//                     history.go(-1);
//                 	location.href = "/qa/qalist.do";
//                 }
//                     alert('어서 오세요! 확인을 누르면 지정한 페이지로 이동합니다.'); // 올바른 비밀번호일 때의 메시지
             location.href = "/qa/qadetail.do?qaNo="+qaNo+"&password="+pass1; // 이동할 웹페이지를 지정 (현재 창에서 이동)
//                 testV += 1;
                //pass1 = prompt('비밀번호를 확인하고 다시 입력하세요:', '비밀번호 확인'); // 잘못된 비밀번호일 때의 메시지
//             }
// //             if (pass1.toLowerCase() !== passwordValue && testV === 3) {
//             	testV = 0;
//                 history.go(-1);
//             }
        }
        
        document.addEventListener('DOMContentLoaded', function() {
//             var qaLinks = document.querySelectorAll('.qaTitle');
//             qaLinks.forEach(function(qaLink) {
//                 qaLink.addEventListener('click', function() {
//                     passWord();
//                 });
//             });
        });

        const urlParams = new URLSearchParams(window.location.search);
        const msg = urlParams.get("msg");
        if (msg) {
            alert(msg);
        }
    </script>
	</body>
</html>
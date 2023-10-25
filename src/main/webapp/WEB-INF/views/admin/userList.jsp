<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<link rel="stylesheet" href="/resources/css/reset.css">
		<link rel='stylesheet' href='//cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.0/css/bootstrap.min.css'>
		<link rel="stylesheet" href="/resources/css/userList.css">
		<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
		<script src="//cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.0/js/bootstrap.bundle.min.js"></script>
	</head>
	<body>
		<div class="container-fluid">
		  <div class="row flex-nowrap">
		    <div class="col-3 bd-sidebar">
		      <div class="bd-sidebar-body">
		        <br>
		      </div>
		    </div>	
		    <main class="col-9 py-md-3 pl-md-5 bd-content" role="main">
		    	<h1 class="main-logo">회원 관리</h1>
		    	<div class="search">
					<form action="/admin/search.do" method="get">
						<select name="searchCondition">
							<option value="all">전체</option>
							<option value="userId">회원아이디</option>
							<option value="userName">회원이름</option>
						</select>
						<input type="text" name="searchKeyword" id="searchKeyword" placeholder="검색어를 입력하세요">
						<input type="submit" value="검색">
					</form>
		    	</div>
		    	<table>
		    	<colgroup>
		    		<col width="8%"/>
		    		<col width="8%"/>
		    		<col width="8%"/>
		    		<col width="8%"/>
		    		<col width="8%"/>
		    		<col width="17%"/>
		    		<col width="15%"/>
		    		<col width="15%"/>
		    		<col width="8%"/>
		    	</colgroup>
		    		<thead>
			    		<tr>
			    			<th>아이디</th>
			    			<th>비밀번호</th>
			    			<th>닉네임</th>
			    			<th>이름</th>
			    			<th>전화번호</th>
			    			<th>주소</th>
			    			<th>이메일</th>
			    			<th>가입날짜</th>
			    			<th>회원탈퇴</th>
			    		</tr>
		    		</thead>
		    		<tbody>
		    			<c:forEach var="user" items="${uList }" varStatus="i">
			    			<tr>
			    				<td>${user.userId}</td>
			    				<td>${user.userPw}</td>
			    				<td>${user.userNickName }</td>
			    				<td>${user.userName }</td>
			    				<td>${user.userPhone }</td>
			    				<td>${user.userAdd }</td>
			    				<td>${user.userEmail }</td>
			    				<td><fmt:formatDate pattern="yyyy-MM-dd" value="${user.userDate }"/></td>
			    				<td>
			    				<c:if test="${user.userId == 'admin' }">
			    					<span>관리자</span>
			    				</c:if>
			    				<c:if test="${user.userId != 'admin' }">
				    				<input type="button" value="탈퇴" onClick="userRemove('${user.userId}');">
			    				</c:if>
			    				</td>
			    			</tr>
		    			</c:forEach>
		    		</tbody>
		    		<tfoot>
		    			<tr align="center">
						    <td colspan="10">
						        <c:if test="${pInfo.startNavi != 1}">
						            <c:url var="prevUrl" value="/admin/userList.do">
						                <c:param name="page" value="${pInfo.startNavi-1}"></c:param>
						            </c:url>
						            <a href="${prevUrl}">[이전]</a>
						        </c:if>
						        <c:forEach begin="${pInfo.startNavi}" end="${pInfo.endNavi}" var="p">
						            <c:url var="pageUrl" value="/admin/userList.do">
						                <c:param name="page" value="${p}"></c:param>
						            </c:url>
						            <c:choose>
						                <c:when test="${p == pageInfo.currentPage}">
						                    <strong>${p}</strong>&nbsp;
						                </c:when>
						                <c:otherwise>
						                    <a href="${pageUrl}">${p}</a>&nbsp;
						                </c:otherwise>
						            </c:choose>
						        </c:forEach>
						        <c:if test="${pInfo.endNavi < naviTotalCount}">
						            <c:url var="nextUrl" value="/admin/userList.do">
						                <c:param name="page" value="${pInfo.endNavi+1}"></c:param>
						            </c:url>
						            <a href="${nextUrl}">[다음]</a>
						        </c:if>
						    </td>
						</tr>
		    		</tfoot>
		    	</table>
		    </main>
		  </div>
		</div>
		<script>
			function userRemove(userId) {
			    var result = confirm("탈퇴 처리를 하시겠습니까?");
			    if (result) {
			        location.href = '/admin/removeUser.do?userId=' + userId;
			    } else {
			    	
			    }
			}
			function handleKeyPress(event) {
			  if (event.keyCode === 13) { 
			    event.preventDefault(); 
			    document.forms[0].submit(); 
			  }
			}
		  document.getElementById('searchKeyword').addEventListener('keydown', handleKeyPress);
		</script>
	</body>
</html>
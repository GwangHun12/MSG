<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>회원정보 수정</title>
		<link rel="stylesheet" href="../resources/css/main.css">
	</head>
	<body>
		<h1>회원정보 수정</h1>
		<form action="/user/update.do" method="post">
			<fieldset>
				<legend>정보 수정</legend>
				<ul>
					<li>
						<label>아이디 *</label>					
						<input type="text" name="userId" value="${user.userId }" readonly>
					</li>
					<li>
						<label>비밀번호 *</label>					
						<input type="password" name="userPw" value="${user.userPw }">
					</li>
					<li>
						<label>닉네임</label>					
						<input type="text" name="userNickName" value="${user.userNickName }" readonly>
					</li>
					<li>
						<label>이름 *</label>					
						<input type="text" name="userName" value="${user.userName }" readonly>
					</li>
					<li>
						<label>핸드폰</label>					
						<input type="text" name="userPhone" value="${user.userPhone }" >
					</li>
					<li>
						<label>주소</label>					
						<input type="text" name="userAdd" value="${user.userAdd }">
					</li>
					<li>
						<label>이메일</label>					
						<input type="text" name="userEmail" value="${user.userEmail }">
					</li>
					
				</ul>
			</fieldset>
			<div>
				<input type="submit" value="수정">
				<input type="reset" value="취소">
			</div>
		</form>
	</body>
</html>














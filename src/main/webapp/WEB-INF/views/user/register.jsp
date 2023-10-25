<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>회원 가입 폼</title>

<link rel='stylesheet' href='//cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.0/css/bootstrap.min.css'>
<style>
.bd-navbar {
  position: sticky;
  top: 0;
  z-index: 1071;
  min-height: 4rem;
  box-shadow: 0 0.5rem 1rem rgba(0,0,0,.05), inset 0 -1px 0 rgba(0,0,0,.1);
}
.bd-sidebar {
  position: sticky;
  top: 4rem;
  z-index: 1000;
  height: calc(100vh - 4rem);
  background: #eee;
  border-right: 1px solid rgba(0,0,0,.1);
  max-width: 220px;
  display: flex;
  padding: 0;
  overflow-y: hidden;
  flex-direction: column;
}
.bd-sidebar-body {
  height: 100%;
  overflow-y: auto;
  display: block;
}
.bd-sidebar-body .nav {
  display: block;
}
.bd-sidebar-body .nav>li>a {
  display: block;
  padding: .25rem 1.5rem;
  font-size: 90%;
}
.bd-sidebar-footer {
  padding: 1rem;
  background: #ddd;
}
</style>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.0/js/bootstrap.bundle.min.js"></script>
</head>

<body>
<header class="navbar navbar-expand navbar-dark bg-dark bd-navbar">
  <a class="navbar-brand" href="#">Navbar</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNav">
    <ul class="navbar-nav">
      <li class="nav-item active"><a class="nav-link" href="#">홈</a></li>
      <li class="nav-item"><a class="nav-link" href="#">1번 메뉴</a></li>
      <li class="nav-item"><a class="nav-link" href="#">2번 메뉴</a></li>
    </ul>
  </div>
</header>







<form action="/user/register2.do" method="post" id="regForm">
<input type="text" id="userId" placeholder="아이디를 입력하세요" />
<button id="checkDuplicate">중복 체크</button>
<div id="result"></div>
</form>
<script>
/* document.getElementById("checkDuplicate").addEventListener("click", function() {
    // 사용자가 입력한 아이디 가져오기
    var userId = document.getElementById("userId").value;

    // AJAX 요청을 생성
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "/member/idChk", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

    // 요청 완료 시 실행되는 콜백 함수
    xhr.onload = function() {
        if (xhr.status === 200) {
            var response = JSON.parse(xhr.responseText);
            if (response.result === 1) {
                // 중복된 아이디입니다.
                document.getElementById("result").textContent = "중복된 아이디입니다.";
            } else if (response.result === 0) {
                // 사용 가능한 아이디입니다.
                document.getElementById("result").textContent = "사용 가능한 아이디입니다.";
            }
        }
    };

    // 요청 본문 데이터 설정
    var data = "userId=" + userId;

    // 요청 전송
    xhr.send(data);
}); */
$(document).ready(function() {
    $("#checkButton").click(function() {
        var username = $("#username").val();
        $.ajax({
            type: "POST",
            url: "/checkUsername",
            data: { username: username },
            success: function(response) {
                $("#message").text(response);
            },
            error: function() {
                alert("서버와 통신 중 오류가 발생했습니다.");
            }
        });
    });
});
</script>
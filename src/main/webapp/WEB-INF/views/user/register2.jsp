<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>회원 가입 폼</title>
		    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
		 <script src="https://code.jquery.com/jquery-latest.min.js"></script>
		<link rel="stylesheet" href="../resources/css/join.css">
		
<link rel='stylesheet' href='//cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.0/css/bootstrap.min.css'>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js"></script>

<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.1/jquery.min.js" type="text/javascript"></script>
<script src="http://ajax.googleapis.com/ajax/libs/prototype/1.6.1.0/prototype.js" type="text/javascript"></script>

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



    <main class="col-5 py-md-3 pl-md-5 bd-content" role="main">
    <section id="container">
			<form action="/user/register2.do" method="post" id="regForm">
					<div class="form-group has-feedback">
						<label class="control-label" for="userId">아이디</label>
						<input class="form-control" type="text" id="userId" name="userId" />
					</div>
					
 					<div class="form-group has-feedback">
						<label class="control-label" for="userPw">비밀번호</label>
						<input class="form-control" type="password" id="userPw" name="userPw" />
					</div>
 					
 					<div class="form-group has-feedback">
						<label class="control-label">주소</label><br>
						<input type="text" id="userAdd" name="userAdd" />
						<input type="button" onclick="sample4_execDaumPostcode();" value="우편번호 찾기" >
					</div>
 					
					<div class="form-group has-feedback">
						<label class="control-label" for="userName">이름</label>
						<input class="form-control" type="text" id="userName" name="userName" />
					</div>
 					<div class="form-group has-feedback">
						<label class="control-label" for="userNickName">닉네임</label>
						<input class="form-control" type="text" id="userNickName" name="userNickName" />
					</div>
					<div class="form-group has-feedback">
						<label class="control-label" for="userPhone">핸폰</label>
						<input class="form-control" type="text" id="userPhone" name="userPhone"/>
					</div>
					
					<div class="form-group has-feedback">
						<label class="control-label" for="userEmail">이메일</label>
						<input class="form-control" type="text" id="userEmail" name="userEmail"/>
					</div>
					
				<div class="form-group has-feedback">
					<button class="btn btn-success" type="submit" id="submit">회원가입</button>
					<button class="cencle btn btn-danger" type="button">취소</button>
				</div>
				
				
			</form>
		</section>
	</main>	
					
	
  




<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<script>
 /* 인증번호 이메일 전송 */
/* $(".userEmail_button").click(function(){
    
	var email = $(".mail_input").val();
	
	$.ajax({
		type:"POST",
		url:"mailCheck.do?email="+ email
/* 		 	console.log("keyup 테스트");	 */
 
	
	/* });
	
	
}); 
 */



$(document).ready(function() {
    $("#checkButton").click(function() {
        var username = $("#username").val();
        $.ajax({
            type: "POST",
            url: "/checkUsername.do",
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


$('.id_input').on("propertychange change keyup paste input", function(){

 	//console.log("keyup 테스트");	
 
});// function 종료
var userId = $('.id_input').val();			// .id_input에 입력되는 값
var data = {userId : userId}				// '컨트롤에 넘길 데이터 이름' : '데이터(.id_input에 입력되는 값)'

$.ajax({
	type : "post",
	url : "/user/userIdChk.do",
	data : data,
	success : function(result){
		  console.log("성공 여부" + result); 
		
	}// success 종료
}); // ajax 종료




 $(document).ready(function(){
	// 취소
	$(".cencle").on("click", function(){
		location.href = "/";
	})
	
	$("#submit").on("click", function(){
		if($("#userId").val()==""){
			alert("아이디를 입력해주세요.");
			$("#userId").focus();
			return false;
		}
		if($("#userPass").val()==""){
			alert("비밀번호를 입력해주세요.");
			$("#userPass").focus();
			return false;
		}
		if($("#userName").val()==""){
			alert("성명을 입력해주세요.");
			$("#userName").focus();
			return false;
		}
		var idChkVal = $("#idChk").val();
		if(idChkVal == "N"){
			alert("중복확인 버튼을 눌러주세요.");
			return false;
		}else if(idChkVal == "Y"){
			$("#regForm").submit();
		}
	});
})

 
/* $(function() {
    $(".fn_idChk").on("click", function() {
	$.ajax({
		url : "/user/idChk.do",
		type : "post",
		dataType : "json",
		data : {"userId" : $("#userId").val()},
		success : function(data){
			if(data == 1){
				alert("중복된 아이디입니다.");
			}else if(data == 0){
				$("#idChk").attr("value", "Y");
				alert("사용가능한 아이디입니다.");
			}
            }
			
		})
	});
});
  */

function sample4_execDaumPostcode() {
	new daum.Postcode({
		oncomplete : function(data) {
			document.querySelector("#userAdd")
			.value = "("+data.zonecode +") "+data.autoJibunAddress+", "+data.buildingName;
		}
	}).open();
}
</script>



</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	 <script src="https://developers.kakao.com/sdk/js/kakao.js"></script>

<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<!-- content에 자신의 OAuth2.0 클라이언트ID를 넣습니다. -->
<meta name="google-signin-client_id"
	content="593934331425-09el84f8mkfptuvi5ph5v15qrvf11lni.apps.googleusercontent.com">

<script src="https://apis.google.com/js/platform.js" async defer></script>
<!-- <script src="https://accounts.google.com/gsi/client" async defer></script> -->


<meta charset="UTF-8">
<title>Insert title here</title>


<link rel='stylesheet'
	href='//cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.0/css/bootstrap.min.css'>
<style>
.bd-navbar {
	position: sticky;
	top: 0;
	z-index: 1071;
	min-height: 4rem;
	box-shadow: 0 0.5rem 1rem rgba(0, 0, 0, .05), inset 0 -1px 0
		rgba(0, 0, 0, .1);
}

.bd-sidebar {
	position: sticky;
	top: 4rem;
	z-index: 1000;
	height: calc(100vh - 4rem);
	background: #eee;
	border-right: 1px solid rgba(0, 0, 0, .1);
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
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.0/js/bootstrap.bundle.min.js"></script>




</head>
<body>


	<c:if test="${sessionScope.userId eq null }">
		<div class="container">
			<h2>로그인폼</h2>
			<form action="/user/login.do" method="post">
				<div class="mb-3">
					<label class="form-label" for="id">아이디</label> <input
						class="form-control" type="text" name="userId" id="userId" />
				</div>
				<div class="mb-3">
					<label class="form-label" for="pwd">비밀번호</label> <input
						class="form-control" type="password" name="userPw" id="userPw" />
				</div>
				<button class="btn btn-outline-primary w-100 btn-lg" type="submit">로그인</button>
				<button class="btn btn-outline-primary w-100 btn-lg" type="button"
					onclick="location.href ='/user/register2.do'">회원가입</button>
			</form>
			
	</div>
	</c:if>
	

	<c:if test="${sessionScope.userId ne null }">
			${userNickName }님 환영합니다. <a href="/user/logout.do">로그아웃</a>
		<br>
		<!-- 			a태그 .kh 뒤에 ?가 필요한가 고민, 쿼리문 생각해보면 필요함 -->
		<a href="/user/mypage.do?userId=${userId }">마이페이지</a>
	</c:if>
	<div id="g_id_onload"
		data-client_id="593934331425-dbtqtv8ociqsbo14eahm6khua9rjlqlb.apps.googleusercontent.com"
		data-callback="handleCredentialResponse"></div>

	<!-- <a href="#" class="btn btn-primary btn-user btn-block" onclick="return frmCheck();">로그인</a>
<hr>
<a href="/account/register" class="btn btn-google btn-user btn-block">회원가입</a>  -->
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>

	 <script type="text/javascript">
	 Kakao.init('43ed89853b9be1beaa37efa5682be40c'); //발급받은 키 중 javascript키를 사용해준다.
	 console.log(Kakao.isInitialized()); // sdk초기화여부판단
	 //카카오로그인
	 function kakaoLogin() {
	     Kakao.Auth.login({
	       success: function (response) {
	         Kakao.API.request({
	           url: '/user/login.do',
	           success: function (response) {
	         	  console.log(response)
	           },
	           fail: function (error) {
	             console.log(error)
	           },
	         })
	       },
	       fail: function (error) {
	         console.log(error)
	       },
	     })
	   }
	 //카카오로그아웃  
	 function kakaoLogout() {
	     if (Kakao.Auth.getAccessToken()) {
	       Kakao.API.request({
	         url: 'http://localhost:9999/index.do',
	         success: function (response) {
	         	console.log(response)
	         },
	         fail: function (error) {
	           console.log(error)
	         },
	       })
	       Kakao.Auth.setAccessToken(undefined)
	     }
	   }  
	 </script>
	 
	 
	 
	
		
	</script>
	<script src="https://apis.google.com/js/platform.js?onload=init" async
		defer></script>
<!-- 	<script src="https://accounts.google.com/gsi/client.js?onload=init" async defer></script>
 -->
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
		<meta charset="utf-8"/>
		<title>캘린더</title>
		<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1"/>
		<meta name="description" content="Waxom free psd template"/>
		<meta name="keywords" content="free, psd, template, responsive"/>
		<meta name="robots" content="index, follow"/>
		<meta name="viewport" content="width=device-width, initial-scale=1"/>	
		<script defer src="https://use.fontawesome.com/releases/v5.5.0/js/all.js" integrity="sha384-GqVMZRt5Gn7tB9D9q7ONtcp4gtHIUEW/yG7h98J7IpE3kpi+srfFyyB/04OV6pG0" crossorigin="anonymous"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/webfont/1.6.26/webfont.js"></script>
		<script>
		  WebFont.load({
			google: {
			  families: ['Montserrat:300,400,700', 'Raleway:300,400,500,600,700,800']
			}
		  });
		</script>     
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/Swiper/4.5.1/css/swiper.min.css">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Swiper/4.5.1/js/swiper.min.js"></script>
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
		<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
		<script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>		
		<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/defualt.css">
		<script src="<%=request.getContextPath() %>/resources/js/script.js"></script>	
		
	</head>
	<body>
		<header> 
			<div class="container clearfix">
				<nav class="clearfix">
					<ul class="main-menu">
						<li class="dropmenu"><a href="/" class="active">Home</a></li>
						<li class="dropmenu"><a href="schedule">일정</a>
							<ul class="babymenu-ul">
						    	<li class="babymenu-li"><a href="makeGroup">모임 만들기</a></li>
						    </ul>
						</li>    											    
						<li class="dropmenu"><a href="/">동아리</a>
							<ul class="babymenu-ul">
						    	<li class="babymenu-li"><a href="makeClub">동아리 만들기</a></li>
						    </ul>
						</li>
						<c:if test="${not empty id}">
						<li class="dropmenu"><a href="myPage_group">마이페이지</a>
							<ul class="babymenu-ul"> 
						        <li class="babymenu-li"><a href="myPage_group">모임 관리</a></li>
						        <li class="babymenu-li"><a href="myPage_club">동아리 관리</a></li>
						        <li class="babymenu-li"><a href="myPage_userUpdate">회원정보수정</a></li>
						    </ul>
						</li>
						</c:if>
						<li class="dropmenu"><a href="notice">고객센터</a>
							<ul class="babymenu-ul">
							 	<li class="babymenu-li"><a href="notice">공지사항</a></li>
						        <li class="babymenu-li"><a href="event">이벤트</a></li>
						        <li class="babymenu-li"><a href="QnA">Q&A</a></li>
						    </ul>
						</li>
						<c:if test="${id == 'master'}">
							<li class="dropmenu"><a href="master_userManage">관리자페이지</a>
								<ul class="babymenu-ul">
							        <li class="babymenu-li"><a href="master_userManage">회원 관리</a></li>
									<li class="babymenu-li"><a href="master_clubManage">동아리 관리</a></li>
									<li class="babymenu-li"><a href="master_groupManage">모임 관리</a></li>
							    </ul>
						    </li>
						</c:if>	
						<c:if test="${id == null}">
							<li class="dropmenu"><a href="/user/login.do">로그인</a></li>
						</c:if>
						<c:if test="${id != null}">
							<li class="dropmenu"><a href="/user/logout.do">로그아웃</a></li>
						</c:if>				
					</ul>
					<span class="toggle"><i class="fas fa-bars"></i></span>		
				</nav>		
			</div>		
		</header>
	
	</body>
</html>		
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
		<title>프로젝트 페이지</title>
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" />
        <link href="/resources/css/styles.css" rel="stylesheet" />
        <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
        <!-- 썸머노트 -->
		<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
		<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
		<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
        <script src="/resources/js/summernote/summernote-lite.js"></script>
		<script src="/resources/js/summernote/lang/summernote-ko-KR.js"></script>
		<link rel="stylesheet" href="/resources/css/summernote/summernote-lite.css">
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
                        <li><a class="dropdown-item" href="/user/login.do">로그인</a></li>
                    </c:if>
                    <c:if test="${sessionScope.userId ne null }">
                        <li><a class="dropdown-item" href="#!">마이페이지</a></li>
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
                            <a class="nav-link" href="/project/main.do?projectNo=${pOne.projectNo }">
                                <div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>
                                ${pOne.projectName }
                            </a>
                            <div class="sb-sidenav-menu-heading">사이드 프로젝트</div>
                            <c:forEach var="sideProject" items="${spList }">
	                            <a class="nav-link" href="/project/side.do?sideProjectNo=${sideProject.sproNo }">
	                                <div class="sb-nav-link-icon"><i class="fas fa-columns"></i></div>
	                                ${sideProject.sproName }
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
                        <h1 class="mt-4">${spOne.sproName } <a href="javascript:void(0);" onclick="customName();"><i class="fas fa-angle-right text-dark"></i></a></h1>
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item active">Side Project Page</li>
                        </ol>
	                        <div class="card mb-4">
	                            <div class="card-header">
	                                <i class="fas fa-chart-bar me-1"></i>
	                                대화방
	                            </div>
	                            <div id="chat-board" class="card-body" style="max-height: calc(100vh - 520px); height: calc(100vh - 520px); overflow-y: scroll; display: flex; flex-direction: column-reverse;">
									<!-- 채팅 내용 출력 -->
	                            </div>
	                            <div>
                            		<input type="hidden" name="sideProjectNo" value="${spOne.sproNo }" />
	                            	<div>
										<textarea id="summernote" name="summernote"></textarea>
									</div>
									<div class="float-end">
										<button id="submit-btn" class="btn btn-primary" type="submit" onclick="button_click();">입력</button>
									</div>
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
			function customName() {
				var userInput = window.prompt("사이드 프로젝트의 수정할 이름을 입력해주세요:", "");
				if (userInput != null) {
					location.href="/project/customSideProject.do?projectNo=${pOne.projectNo}&sproName=" + userInput;
				} else {
					  alert("입력이 취소되었거나 아무 내용도 입력하지 않았습니다.");
				}
			}
		</script>
		<script>
			$(document).ready(function() {
		        $('#summernote').summernote({
		        	placeholder: '대화입력',
		        	tabsize: 2,
		        	height: 100,
		        	minHeight: 100,
		        	maxHeight: 100,
		        	toolbar: [
						// [groupName, [list of button]]
						['picture']
					],
	        		onFocus: function (contents) {
	        		    if($('#summernote').summernote('isEmpty')){
	        		    	$('#summernote').summernote('insertText', '');
	        			}
	        		}
		        });
				// Summernote의 내용을 가져옵니다.
				var summernoteContent = $('#summernote').summernote('code');
				// <p> 태그와 <br> 태그를 제거합니다.
				var cleanedContent = summernoteContent.replace(/<p>/g, '').replace(/<\/p>/g, '').replace(/<br>/g, '');
				// Summernote에 수정된 내용을 다시 설정합니다.
				$('#summernote').summernote('code', cleanedContent);
		    });
			
			$('#summernote').on('summernote.keydown', function(we, e) {
				if(e.keyCode==13) {
					if(!e.shiftKey) {						
						var sproNo = "${spOne.sproNo }";
						var spbContent = $("#summernote").val();
						
						$.ajax({
							url: "/project/createBoard.do",
							type: "POST",
							data: {"sproNo" : sproNo, "spbContent" : spbContent},
							success: function(result) {
								getBoardList();
 								// 썸머노트 초기화하는 메소드
								resetSummernote();
								// Summernote의 내용을 가져옵니다.
								var summernoteContent = $('#summernote').summernote('code');
								// <p> 태그와 <br> 태그를 제거합니다.
								var cleanedContent = summernoteContent.replace(/<p>/g, '').replace(/<\/p>/g, '').replace(/<br>/g, '');
								// Summernote에 수정된 내용을 다시 설정합니다.
								$('#summernote').summernote('code', cleanedContent);
							},
							error: function() {
								alert("ajax 오류, 관리자에게 문의해주세요.");
							}
						});
					}
				}
			});
			
			function resetSummernote() {
				$('#summernote').summernote('reset');
			}
			
			function button_click() {
				var sproNo = "${spOne.sproNo }";
				var spbContent = $("#summernote").val();
				
				$.ajax({
					url: "/project/createBoard.do",
					type: "POST",
					data: {"sproNo" : sproNo, "spbContent" : spbContent},
					success: function(result) {
						getBoardList();
							// 썸머노트 초기화하는 메소드
						resetSummernote();
						// Summernote의 내용을 가져옵니다.
						var summernoteContent = $('#summernote').summernote('code');
						// <p> 태그와 <br> 태그를 제거합니다.
						var cleanedContent = summernoteContent.replace(/<p>/g, '').replace(/<\/p>/g, '').replace(/<br>/g, '');
						// Summernote에 수정된 내용을 다시 설정합니다.
						$('#summernote').summernote('code', cleanedContent);
					},
					error: function() {
						alert("ajax 오류, 관리자에게 문의해주세요.");
					}
				});
			}
		</script>
		<script>
			function getBoardList() {
// 				timer = setInterval( function () {
					var sproNo = "${spOne.sproNo }";
					$.ajax({
						url : "/project/spBoardList.do",
						type : "GET",
						data : {sproNo : sproNo},
						dataType : "json",
						success : function(data) {
							let str = "";
							for(let i = 0; i < data.spbList.length; i++) {
								if(data.userNickName == data.spbList[i].spbWriter){
									str += "<div class='card mb-4' style='width: fit-content; align-self: flex-end;'>"+
					                        	"<div class='card-body'>"+
					                    			data.spbList[i].spbContent+
					                    		"</div>"+
					                		"</div>"+
					                		"<div style='align-self: flex-end;'>"+
					                		data.spbList[i].spbWriter+
					                		"</div>";
								} else {
									str += "<div class='card mb-4' style='width: fit-content;'>"+
					                        	"<div class='card-body'>"+
					                        		data.spbList[i].spbContent+
					                    		"</div>"+
					                		"</div>"+
					                		"<div>"+
					                		data.spbList[i].spbWriter+
					                		"</div>";
								}
							}
							$("#chat-board").html(str);
						},
						error : function() {
// 							alert("Ajax 오류! 관리자에게 문의해주세요!");
						}
					});
// 				},3000);
			}
			
			timer = setInterval( function () {
				getBoardList();
			},3000);
			
			$(document).ready(function() {
				getBoardList();
			});
		</script>
	</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>일정 페이지</title>
<head>
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
<c:set var="path" value="${pageContext.request.contextPath}"/>
     <script src="${path}/resources/vendor/js/select2.min.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css">
<script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>
 <script src="${path}/resources/js/calendarmain2.js"></script>
 <script src="${path }/resources/vendor/js/fullcalendar.min.js"></script>
  <script src="${path}/resources/js/main.js"></script>
 <script src="${path}/resources/js/addEvent.js"></script>
 <script src="${path}/resources/js/editEvent.js"></script>
 <script src="${path}/resources/js/etcSetting.js"></script>
<%--  <script src="${path }/resources/vendor/js/luxon.min.js"></script>
 --%>
 <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment.min.js"></script>
 <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/locale/ko.js"></script>
  
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>일정</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/vendor/css/fullcalendar.min.css" />
<%-- <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/main.css" />
 --%><%-- <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/vendor/css/bootstrap.min.css" />
 --%>
<%--  <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/vendor/css/select2.min.css" /> 
 --%><%--  <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/vendor/css/bootstrap-datetimepicker.min.css" />  
 --%>
 <%--  <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/vendor/css/bootstrap2.min.css"/>
 --%> 
<!-- <link href="https://cdn.jsdelivr.net/npm/bootstrap-datepicker@1.9.0/dist/css/bootstrap-datepicker.min.css" rel="stylesheet"> 
 -->  <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/schedule.css" />
  
<!--   <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/calmain.css" />-->
<!-- <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Open+Sans:400,500,600">
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons"> -->
<!-- <link href="https://cdn.jsdelivr.net/npm/fullcalendar@5.11.5/main.css" rel="stylesheet" />

 -->
<!--  <link href='fullcalendar/daygrid/main.css' rel='stylesheet' />
 --> 
 </head>


<body>
<div style="overflow:hidden; ">
      <div class="container-xxl" >
         <input type="hidden" id="id" value="${sessionScope.userId }"> 
         <input type="hidden" id="hidden" value="${project.projectNo }">       
        <!-- 일자 클릭시 메뉴오픈 -->
        
        <div class="dropdown clearfix">
  <button class="btn btn-secondary dropdown-toggle" type="button" id="contextMenu" data-bs-toggle="dropdown" aria-expanded="false">
    일정 추가
  </button>
  <ul class="dropdown-menu dropNewEvent" aria-labelledby="contextMenu">
    <li><a class="dropdown-item" href="/schedule_makeSchedule">일정 추가</a></li>
  </ul>
</div>
       <div id="calendar-container">
    <div id="calendar" ></div>
</div>

<!-- 일정 추가 MODAL -->
<div class="modal fade" id="eventModal" tabindex="-1" role="dialog"  >
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header" style="text-align:left;float:left" >
                <h4 class="modal-title" ></h4>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <input type="hidden" id="projectNo" name="projectNo" value="${project.projectNo}">
            	<input type="hidden" id="id" value="${sessionScope.userId }"> 
                <div class="l1">
                    <div class="l_row">
                        <div class="col-12">
                            <label for="edit-allDay" class="form-label">하루종일</label>
                           <c:if test="${edit-allDay == 1}">
                                   <input class='form-check-input allDayNewEvent' id="edit-allDay" type="checkbox" value="1" checked>
                                   </c:if>
                                   <c:if test="${edit-allDay != 1}">
                                   <input class='form-check-input allDayNewEvent' id="edit-allDay" type="checkbox" value="0">
                                   </c:if>
                        </div>
                    </div>
                    <div class="r_row">
                        <div class="col-12">
                            <label for="edit-schk" class="form-label">공유여부</label>
                            <input class="form-check-input allDayNewEvent" type="checkbox" id="edit-schk" value="1">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-12">
                            <label for="edit-title" class="form-label">일정명</label>
                            <input class="form-control inputModal" type="text" name="edit-title" id="edit-title" required="required">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-12">
                            <label for="edit-start" class="form-label">시작</label>
                            <input class="form-control inputModal" type="date" name="edit-start" id="edit-start">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-12">
                            <label for="edit-end" class="form-label">끝</label>
                            <input class="form-control inputModal" type="date" name="edit-end" id="edit-end">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-12">
                            <label for="edit-type" class="form-label">구분</label>
                           <select class="form-select inputModal" type="text" name="edit-type" id="edit-type">
    				<option value="개인일정">개인일정</option>
    				<c:forEach var="project" items="${pList}">
      		  	<option value="${project.projectNo}" name="g_code">${project.projectName}</option>
  		 			 </c:forEach>
				</select>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-12">
                            <label for="edit-color" class="form-label">색상</label>
                            <select class="form-select" name="color" id="edit-color">
                                <option value="#D25565" style="color: #D25565;">빨간색</option>
                                <option value="#9775fa" style="color: #9775fa;">보라색</option>
                                <option value="#ffa94d" style="color: #ffa94d;">주황색</option>
                                <option value="#74c0fc" style="color: #74c0fc;">파란색</option>
                                <option value="#f06595" style="color: #f06595;">핑크색</option>
                                <option value="#63e6be" style="color: #63e6be;">연두색</option>
                                <option value="#a9e34b" style="color: #a9e34b;">초록색</option>
                                <option value="#4d638c" style="color: #4d638c;">남색</option>
                                <option value="#495057" style="color: #495057;">검정색</option>
                            </select>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-12">
                            <label for="edit-desc" class="form-label">설명</label>
                            <textarea rows="4" cols="50" class="form-control inputModal" name="edit-desc" id="edit-desc"></textarea>
                        </div>
                    </div>
                </div>

                <!-- 수정 추가 -->
				<div class="modal-footer modalBtnContainer-closeEvent" style="display: none;">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
                </div>
                <!-- 수정 -->

				<div class="modal-footer modalBtnContainer-addEvent" style="display: none;">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
                    <button type="button" class="btn btn-primary" id="save-event"onClick="window.location.reload()">저장</button>
                </div>
                <div class="modal-footer modalBtnContainer-modifyEvent">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
                    <button type="button" class="btn btn-danger" id="deleteEvent"onClick="window.location.reload()" >삭제</button>
                    <button type="button" class="btn btn-primary" id="updateEvent"onClick="window.location.reload()">저장</button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->
</div><!-- /.filte-->
    </div>
    </div>
   <%--  <div style="float:left;  margin-top: 160px;  margin-left: 80px; ">
     <div class="schedule-Top">
            <input type="hidden" id="joinedGroup" value="${project.projectNo }"></select>
            <script id="temp_g_code" type="text/x-handlebars-template">
           <option value="0">개인일정</option>
             {{#each .}}
                 <option value="${project.projectNo}">${Proejct.projectMember}</option>
            {{/each}}
             </script>
            <!-- 인원 추가하기  -->
            <div id="searchUser">              
               <div id="popup" class="popup_overlay">
                     <div class="popup">
                        <h2>인원추가</h2>
                        <a class="close" href="#" id="close">&times;</a>
                        <input type="text" id="query" placeholder="아이디를 검색하시오">
                        <span><button id="search" class="btnUserse">검색</button></span>
                        <table id="result" class="resultUser"></table>
                        <div id="resultoo"> </div>
                        <script id="tempResult" type="text/x-handlebars-template">
                         <table class="schedTable">
                        <tr style="margin-bottom: 15px;">
                           <th class="s_ck">체크</th>
                            <th class="s_name">이름</th>
                            <th class="s_pid">아이디</th>
                         </tr>
                    </table> 
                       {{#each .}}                            
                            <tr style="margin-bottom: 15px;">
                            <td><input type="checkbox" value="{{id}}" name="selectedMember"></td>
                           <td class="s_name">{{name}}</td>
                           <td class="s_pid">{{id}}</td>
                         </tr>                               
                        {{/each}}
                        </script>
                        <button class="bubbly-button" id="complete" >완료</button>
                     </div>
               </div>
            </div>
         </div>
        <div class="" style="width: 250px; margin-top:25px;">                
                <p for="calendar_view" style="text-align:center; font-size:20px;">등록자별</p>
                <table id="joinedMember" class="resultUser"></table> 
                <script id="temp1" type="text/x-handlebars-template">
                <table class="schedTable">
                      <tr style="margin-bottom: 15px;">
                         <th class="s_ck" style="width: 4%;">체크</th>
                         <th class="s_name">이름</th>
                      </tr>
                </table> 
                {{#each .}}                            
                      <tr style="margin-bottom: 15px;">
                        <td><input class='filter' type="checkbox" value="{{id}}" checked></td>
                        <td class="s_name">{{name}}</td>
                     </tr>                               
                {{/each}}
                </script>                                      
                </div>
                <input type="button" value="인원추가" onClick="location.href='#popup'" class="btnschedule">
        </div>       
        </div> --%>
    <!-- /.container -->
    <script src="${path }/resources/js/locales-all.js"></script>
<%--     <script src="${path}/resources/vendor/js/bootstrap.min.js"></script> --%>
<%--     <script src="${path}/resources/vendor/js/jquery.min.js"></script>
 --%>
<!--  <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment.min.js"></script>
 -->    
<%--  <script src="${path}/resources/js/moment.js"></script>
 --%> 
  <%--     <script src="${path}/resources/vendor/js/bootstrap-datetimepicker.min.js"></script>  
 --%> 
<!--  <script src="https://cdn.jsdelivr.net/npm/bootstrap-datepicker@1.9.0/dist/js/bootstrap-datepicker.min.js"></script>
 -->   
   
<%--     <script src="${path}/resources/js/etcSetting.js"></script>
 --%><%--      <script src="${path}/resources/vendor/js/locales-all.min.js" ></script> 
 --%></body>
<script>


document.addEventListener('DOMContentLoaded', function () {
    document.getElementById('contextMenu').addEventListener('click', function () {
        var projectNo = document.getElementById('hidden').value;
        $('#projectNo').val(projectNo);
        $('#eventModal').modal('show'); 
    });
});

//g_code 변수 및 선택자 업데이트
var g_code = "0";

var eventModal = document.getElementById('eventModal');
var modalTitle = document.querySelector('.modal-title');
var editAllDay = document.getElementById('edit-allDay');
var editTitle = document.getElementById('cal_title');
var editStart = document.getElementById('cal_sdate');
var editEnd = document.getElementById('cal_edate');
var editType = document.getElementById('edit-type');
var editColor = document.getElementById('edit-color');
var editDesc = document.getElementById('cal_content');
var editschk = document.getElementById('edit-schk');
var addBtnContainer = document.querySelector('.modalBtnContainer-addEvent');
var modifyBtnContainer = document.querySelector('.modalBtnContainer-modifyEvent');

// chk 함수 업데이트
function chk() {
    $.ajax({
        type: "get",
        url: "myGroup",
        dataType: "json",
        success: function (data) {
            var temp_g_name = Handlebars.compile($("#temp_g_name").html());
            var temp_g_code = Handlebars.compile($("#temp_g_code").html());

            $("#edit-type").html(temp_g_name(data));
            $("#joinedGroup").html(temp_g_code(data));

            // 필터링하도록 기본값을 넣어준다.
            var id = $("#id").val();
            var str = "<label class='form-check-label'><input class='filter form-check-input' type='checkbox'";
            str += " value='" + id + "' checked>개인일정</label>";
            $("#joinedMember").html(str);

            // 핸들바 변경.
            $(document).ready(function () {
                $(".filter").change(function () {
                    $('#calendar').fullCalendar('removeEvents');
                    $('#calendar').fullCalendar('refetchEvents');
                });
            });
        }
    });
}

// 그룹 선택 업데이트
$("#joinedGroup").on("change", function () {
    var selectG_code = $("#joinedGroup").val();
    g_code = selectG_code;
    if (selectG_code != "0") {
        $.ajax({
            type: "get",
            url: "g_pic",
            data: { "g_code": selectG_code },
            success: function (data) {
                $("#image").attr("src", "display?fileName=" + data.gpic.g_pic);
            }
        });
        // 필터링 할 member를 가져온다.
        $.ajax({
            type: "get",
            url: "guget",
            dataType: "json",
            data: { "g_code": selectG_code },
            success: function (data) {
                var temp = Handlebars.compile($("#temp1").html());
                $("#joinedMember").html(temp(data));
                $("#hidden").val(selectG_code);
                $('#calendar').fullCalendar('removeEvents');
                $('#calendar').fullCalendar('refetchEvents');

                $(document).ready(function () {
                    $(".filter").change(function () {
                        $('#calendar').fullCalendar('removeEvents');
                        $('#calendar').fullCalendar('refetchEvents');
                    });
                });

                $("#searchUser").style.display = "block";
            }
        });
    } else {
        $.ajax({
            type: "get",
            url: "g_pic",
            data: { "g_code": selectG_code },
            success: function (data) {
                $("#image").attr("src", "display?fileName=" + data.upic.image);
            }
        });
        var id = $("#id").val();
        var str = "<label class='form-check-label'><input class='filter form-check-input' type='checkbox'";
        str += " value='" + id + "' checked>개인일정</label>";
        $("#joinedMember").html(str);

        $("#hidden").val(selectG_code);
        $('#calendar').fullCalendar('removeEvents');
        $('#calendar').fullCalendar('refetchEvents');

        $("#searchUser").style.display = "none";
    }
})

// $("#search").on("click", function () {
//     var ' = $("#query").val();
//     $.ajax({
//         type: "get",
//         url: "searchGroupMember",
//         dataType: "json",
//         data: { "query": query },
//         success: function (data) {
//             var temp = Handlebars.compile($("#tempResult").html());
//             $("#result").html(temp(data));
//             $("#resultoo").style.display = "none";
//             if (data.length == 0) {
//                 $("#resultoo").innerHTML = "검색결과가 없습니다";
//                 $("#resultoo").style.display = "block";
//             }
//         }
//     });
// })

$("#complete").on("click", function () {
    var g_code = $("#joinedGroup").val();
    var gu_pwchk = "N";
    var checkboxes = document.getElementsByName("selectedMember");
    var check_count = checkboxes.length;

    checkboxes.forEach(function (checkbox) {
        if (checkbox.checked) {
            var id = checkbox.value;
            $.ajax({
                type: "get",
                url: "addMembers",
                data: { "g_code": g_code, "gu_pwchk": gu_pwchk, "id": id },
                success: function () {
                    alert("완료");
                    $("#query").val("");
                    $("#result").html("검색결과 없음");
                    var selectG_code = $("#joinedGroup").val();
                    g_code = selectG_code;

                    $.ajax({
                        type: "get",
                        url: "guget",
                        dataType: "json",
                        data: { "g_code": selectG_code },
                        success: function (data) {
                            var temp = Handlebars.compile($("#temp1").html());
                            $("#joinedMember").html(temp(data));

                            $(document).ready(function () {
                                $(".filter").change(function () {
                                    $('#calendar').fullCalendar('removeEvents');
                                    $('#calendar').fullCalendar('refetchEvents');
                                });
                            });
                        }
                    });
                }
            });
        }
    });
});

document.addEventListener('DOMContentLoaded', function () {
    document.getElementById('calendar').addEventListener('click', function (event) {
        var top = event.clientY;
        var left = event.clientX;

        var dropdownEvent = document.querySelector('.dropdown-event');
        if (dropdownEvent) {
            dropdownEvent.style.top = top + 'px';
            dropdownEvent.style.left = left + 'px';
            dropdownEvent.style.display = 'block';
        }
    });

    document.addEventListener('click', function (event) {
        if (!event.target.closest('.dropdown-event')) {
            var dropdownEvent = document.querySelector('.dropdown-event');
            if (dropdownEvent) {
                dropdownEvent.style.display = 'none';
            }
        }
    });
});
      
   
</script>

</html>
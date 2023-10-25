var eventModal = $("#eventModal");
var modalTitle = $(".modal-title");
var editAllDay = $("#edit-allDay");
var editTitle = $("#edit-title");
var editStart = $("#edit-start");
var editEnd = $("#edit-end");
var editType = $("#edit-type");
var editColor = $("#edit-color");
var editDesc = $("#edit-desc");
var calendar = $("#calendar");
var projectNo = $("#projectNo");
var addBtnContainer = $(".modalBtnContainer-addEvent");
var modifyBtnContainer = $(".modalBtnContainer-modifyEvent");
var clodseBtnContainer = $(".modalBtnContainer-closeEvent");

/* ****************
 *  새로운 일정 생성
 * ************** */
 
var newEvent = function (start, end, eventType) {
	var projectNo = document.getElementById('hidden').value;
    $("#contextMenu").hide(); // 메뉴 숨김
    modalTitle = $(".modal-title");
    modalTitle.html('새로운 일정');
    $("#edit-title").val('');
    $("#edit-start").val(start);
    $("#edit-end").val(end);
    $("#edit-desc").val('');
	$("#projectNo").val(projectNo);

    $(".modalBtnContainer-closeEvent").hide();
    $(".modalBtnContainer-addEvent").show();
    $(".modalBtnContainer-modifyEvent").hide();
	
	eventModal = $("#eventModal");
	
    eventModal.modal('show');

    // 새로운 일정 저장버튼 클릭
    $("#save-event").unbind();
	var dateStr = '2023-10-18T14:30:00';
	var date = new Date(dateStr);

// 날짜 및 시간 형식을 설정
	var options = { year: 'numeric', month: 'long', day: 'numeric', hour: '2-digit', minute: '2-digit' };

// 형식화된 날짜 및 시간 문자열 생성
	var formattedDate = date.toLocaleDateString('en-US', options); 

console.log(formattedDate);    
$("#save-event").on('click', function () {
        var eventData = {
            title: $("#edit-title").val(),
            projectNo:$("#projectNo").val(),
            start: $("#edit-start").val(),
            end: $("#edit-end").val(),
            description: $("#edit-desc").val(),
            type: $("#edit-type").val(),
            username: "", // controller에서 session에 있는 값으로 넣어줘야 한다.
            backgroundColor: $("#edit-color").val(),
            textColor: "ffffff",
            allDay: "0",
            schk: "0"
        };

        if (eventData.start > eventData.end) {
            alert("끝나는 날짜가 앞설 수 없습니다.");
            return false;
        }

        if (eventData.title === '') {
            alert('일정명은 필수입니다.');
            return false;
        }

        var realEndDay;

        if ($("#edit-schk").is(":checked")) {
            eventData.schk = "1";
        }
        if ($("#edit-allDay").is(':checked')) {
          eventData.start = new Date(eventData.start);

// `eventData.end`를 설정: `eventData.start`에 1일을 더합니다.
const endDate = new Date(eventData.start);
endDate.setDate(endDate.getDate() + 1);

// 원하는 형식으로 문자열로 변환
function formatDate(date) {
  const year = date.getFullYear();
  const month = String(date.getMonth() + 1).padStart(2, '0');
  const day = String(date.getDate()).padStart(2, '0');
  const hours = '00';
  const minutes = '00';

  return `${year}-${month}-${day} ${hours}:${minutes}`;
}

eventData.start = formatDate(eventData.start);
eventData.end = formatDate(endDate);

console.log(eventData.start); // "2023-10-18 00:00"
console.log(eventData.end); // "2023-
            realEndDay = eventData.start;

            eventData.allDay = "1";
        } else {
         eventData.start = new Date(eventData.start);
		eventData.end = new Date(eventData.end);

// 원하는 형식으로 문자열로 변환
function formatDate(date) {
  const year = date.getFullYear();
  const month = String(date.getMonth() + 1).padStart(2, '0');
  const day = String(date.getDate()).padStart(2, '0');
  const hours = String(date.getHours()).padStart(2, '0');
  const minutes = String(date.getMinutes()).padStart(2, '0');
  
  return `${year}-${month}-${day} ${hours}:${minutes}`;
}

eventData.start = formatDate(eventData.start);
eventData.end = formatDate(eventData.end);

console.log(eventData.start); // "2023-10-18 14:30"
console.log(eventData.end); // "2023-10-18 15:30"
        }
      $("#eventModal").find("input, textarea").val("");
      eventModal.modal("hide");
        // 새로운 일정 저장
        insertCal();

        function insertCal() {
            $.ajax({
                type: "get",
                url: "/schedule/scheduleinsert.do",
                data: {
                    "schk": eventData.schk,
                    "description": eventData.description,
                    "start": eventData.start,
                    "end": eventData.end,
                    "type": eventData.type,
                    "backgroundColor": eventData.backgroundColor,
                    "textColor": eventData.textColor,
                    "allDay": eventData.allDay,
                    "title": eventData.title,
                    "projectNo": eventData.projectNo
                },
                success: function (response) {
                    alert("저장을 완료했습니다. ");
                    // DB연동시 중복이벤트 방지를 위한
                    $('#calendar').fullCalendar('removeEvents');
                    $('#calendar').fullCalendar('refetchEvents');
                }
            });
        }
    });
};

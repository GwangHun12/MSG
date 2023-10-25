var eventModal = $("#eventModal");
var modalTitle = $(".modal-title");
var editAllDay = $("#edit-allDay");
var editTitle = $("#edit-title");
var editStart = $("#edit-start");
var editEnd = $("#edit-end");
var editType = $("#edit-type");
var editColor = $("#edit-color");
var editDesc = $("#edit-desc");
var addBtnContainer = document.querySelector('.modalBtnContainer-addEvent');
var modifyBtnContainer = document.querySelector('.modalBtnContainer-modifyEvent');
var clodseBtnContainer = $(".modalBtnContainer-closeEvent");
var editEvent = function (event, element, view) {
var modalTitle = $('#modal-title');
    modalTitle.html('일정 수정');
    $("#deleteEvent").data("id", event.extendedProps.calno); // 클릭한 이벤트 ID
  	$("#updateEvent").data("id",event.extendedProps.calno);
    $(".popover.fade.top").remove();
    $(element).popover("hide");

    if (event.allDayjs == true) {
        $("#edit-allDay").prop('checked', true);
    } else {
        $("#edit-allDay").prop('checked', false);
    }

    if (event.schk == "1") {
        $("#edit-schk").prop('checked', true);
    } else {
        $("#edit-schk").prop('checked', false);
    }

    if (event.end === null) {
        event.end = event.start;
    }

    if (event.allDayjs == true) {
       var startDate = new Date(event.start);
	   var endDate = new Date(event.end);

// 날짜를 'YYYY-MM-DD' 형식의 문자열로 변환
function formatDate(date) {
  const year = date.getFullYear();
  const month = String(date.getMonth() + 1).padStart(2, '0');
  const day = String(date.getDate()).padStart(2, '0');

  return `${year}-${month}-${day}`;
}

// 변환된 날짜를 필드에 설정
$("#edit-start").val(formatDate(startDate));
$("#edit-end").val(formatDate(endDate));
    } else {
 var startDate = new Date(event.start);
var endDate = new Date(event.end);

// 날짜 및 시간을 'YYYY-MM-DD HH:mm' 형식의 문자열로 변환
function formatDateTime(date) {
  const year = date.getFullYear();
  const month = String(date.getMonth() + 1).padStart(2, '0');
  const day = String(date.getDate()).padStart(2, '0');
  const hours = String(date.getHours()).padStart(2, '0');
  const minutes = String(date.getMinutes()).padStart(2, '0');

  return `${year}-${month}-${day} ${hours}:${minutes}`;
}

// 변환된 날짜 및 시간을 필드에 설정
$("#edit-start").val(formatDateTime(startDate));
$("#edit-end").val(formatDateTime(endDate));
    }
	
    $("#edit-title").val(event.title);
    $("#edit-type").val(event.type);
    $("#edit-desc").val(event.description);
    $("#edit-color").val(event.backgroundColor).css('color', event.backgroundColor);

  //  if (id == event.username) {
  //      $(".modalBtnContainer-addEvent").hide();
  //      $(".modalBtnContainer-modifyEvent").show();
  //      $(".modalBtnContainer-closeEvent").hide();
  //  } else {
   //     $(".modalBtnContainer-addEvent").hide();
  //       $(".modalBtnContainer-modifyEvent").hide();
  //      $(".modalBtnContainer-closeEvent").show();
   // }
    	eventModal = $("#eventModal");
    
    eventModal.modal('show');

    // 업데이트 버튼 클릭시
    $('#updateEvent').unbind();
// 원래 문자열을 JavaScript Date 객체로 변환
var dateStr = '2023-10-18T14:30:00';
var date = new Date(dateStr);

// 날짜 및 시간을 'YYYY-MM-DD HH:mm' 형식의 문자열로 변환
function formatDateTime(date) {
  const year = date.getFullYear();
  const month = String(date.getMonth() + 1).padStart(2, '0');
  const day = String(date.getDate()).padStart(2, '0');
  const hours = String(date.getHours()).padStart(2, '0');
  const minutes = String(date.getMinutes()).padStart(2, '0');

  return `${year}-${month}-${day} ${hours}:${minutes}`;
}

// 날짜를 'YYYY-MM-DD HH:mm' 형식으로 형식화
var formattedDate = formatDateTime(date);

console.log(formattedDate); // "2023-10-18 14:30"    
    $('#updateEvent').on('click', function () {
        var eventData = {
            title: $("#edit-title").val(),
            start: $("#edit-start").val(),
            end: $("#edit-end").val(),
            description: $("#edit-desc").val(),
            type: $("#edit-type").val(),
            backgroundColor: $("#edit-color").val(),
            textColor: "ffffff",
            allDay: "0",
            schk: "0"
        };

        if ($("#edit-schk").is(":checked")) {
            eventData.schk = "1"
        }

        if ($("#edit-start").val() > $("#edit-end").val()) {
            alert('끝나는 날짜가 앞설 수 없습니다.');
            return false;
        }

        if ($("#edit-title").val() === '') {
            alert('일정명은 필수입니다.');
            return false;
        }

        if ($("#edit-allDay").is(':checked')) {
            eventData.start = new Date(eventData.start);

// eventData.end를 설정: eventData.start에 1일을 더합니다.
var endDate = new Date(eventData.start);
endDate.setDate(endDate.getDate() + 1);

// 날짜를 'YYYY-MM-DD 00:00' 형식으로 문자열로 변환
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
console.log(eventData.end); // "2023-10-19 00:00"
            realEndDay = eventData.start;
            eventData.allDay = "1";
        } else {
           eventData.start = new Date(eventData.start);
eventData.end = new Date(eventData.end);

// 날짜 및 시간을 'YYYY-MM-DD HH:mm' 형식의 문자열로 변환
function formatDateTime(date) {
  const year = date.getFullYear();
  const month = String(date.getMonth() + 1).padStart(2, '0');
  const day = String(date.getDate()).padStart(2, '0');
  const hours = String(date.getHours()).padStart(2, '0');
  const minutes = String(date.getMinutes()).padStart(2, '0');

  return `${year}-${month}-${day} ${hours}:${minutes}`;
}

// eventData.start 및 eventData.end를 'YYYY-MM-DD HH:mm' 형식으로 변환
eventData.start = formatDateTime(eventData.start);
eventData.end = formatDateTime(eventData.end);

console.log(eventData.start); // "2023-10-18 14:30"
console.log(eventData.end); // "2023-10-18 15:30"
            // DB에 넣을때(선택)
        }
	
        $("#eventModal").find("input, textarea").val("");
        $("#edit-allDay").prop("checked", false);
        eventModal.modal("hide");
$('#deleteEvent').on('click', function () {
    console.log("Delete button clicked"); // Add this line to check if the click event is triggered
    // Rest of your delete event code
});
        // 일정 업데이트
        $.ajax({
            type: "get",
            url: "/schedule/scheduleupdate.do",
            data: {
                "schk": eventData.schk,
                "description": eventData.description,
                "start": eventData.start,
                "end": eventData.end,
                "type": eventData.type,
                "calno": $(this).data('id'),
                "backgroundColor": eventData.backgroundColor,
                "textColor": eventData.textColor,
                "allDay": eventData.allDay,
                "title": eventData.title
            },
            success: function (response) {
                alert('수정되었습니다.');
                $('#calendar').fullCalendar('removeEvents');
                $('#calendar').fullCalendar('refetchEvents');
            }
        });

    });

    // 삭제버튼
    $('#deleteEvent').on('click', function () {
        $('#deleteEvent').unbind();
        eventModal.modal('hide');
        alert("삭제");

        // 삭제시
        $.ajax({
            type: "get",
            url: "/schedule/scheduledelete.do",
            data: {
                "calno": $(this).data('id')
            },
            success: function () {
                alert('삭제되었습니다.');
            }
        });
    });
};

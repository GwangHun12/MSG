document.addEventListener('DOMContentLoaded', function () {
  
  var draggedEventIsAllDay;
  var activeInactiveWeekends = true;
  var g_code = document.getElementById('hidden').value;
  var id = document.getElementById('id').value;
  //var str = "<label class='checkbox-inline'><input class='filter' type='checkbox'";
  //str += " value='" + id + "' checked>개인일정</label>";
 // document.getElementById('joinedMember').innerHTML = str;

  var calendarEl = document.getElementById('calendar');
  var calendar = new FullCalendar.Calendar(calendarEl, {
   initialView: 'dayGridMonth',
    editable: true,
    selectable: true,
    timeZone: 'local',
    slotDuration: '01:00:00',
slotMinTime: '00:00:00',  // 시간 선택의 시작 시간 (24시간 형식)
  slotMaxTime: '24:00:00',  // 시간 선택의 종료 시간 (24시간 형식)

 	 slotEventOverlap: false, // 이벤트 오버랩 방지
    droppable: true, // this allows things to be dropped onto the calendar
    editable: true,
    locale: 'ko',
    allDaySlot: false,
    slotLabelFormat: 'HH:mm',
    weekends: true,
dayMaxEventRows: 5,
    views: {
  month: {
    eventLimit: 5
  }
},
    firstDay: 0,
    nowIndicator: true,
    dayMaxEvents: false,
    weekNumberCalculation: 'ISO',
    navLinks: true,
    dayHeaders: 'dddd',
    defaultTimedEventDuration: '01:00:00',
    weekends: true,
    nowIndicator: true,
    dayPopoverFormat: 'MM/DD dddd',
    longPressDelay: 0,
    eventLongPressDelay: 0,
    selectLongPressDelay: 0,
    slotLabelFormat: {
  hour: '2-digit',
  minute: '2-digit',
},
    headerToolbar: {
      left: 'today prevYear nextYear viewWeekends prev',
      center: 'title',
      right: 'next dayGridMonth timeGridWeek timeGridDay listWeek',
    }
    
    ,views: {
  month: {
    dayHeaderFormat: { weekday: 'long' }
  },
  timeGridWeek: {
    dayHeaderFormat: { month: 'numeric', day: 'numeric', weekday: 'short' },
    titleFormat: { year: 'numeric', month: 'long', day: 'numeric' },
    eventTimeFormat: { hour: '2-digit', minute: '2-digit' },
    eventLimit: false
  },
  timeGridDay: {
    dayHeaderFormat: { weekday: 'long' },
    eventTimeFormat: { hour: '2-digit', minute: '2-digit' },
    eventLimit: false
  },
  listWeek: {
    dayHeaderFormat: { weekday: 'short' }
  }
},customButtons: {
  viewWeekends: {
    text: '주말',
    click: function() {
      activeInactiveWeekends = !activeInactiveWeekends;
      calendar.setOption('weekends', activeInactiveWeekends);
    }
  }
},
    events: function (info, successCallback, failureCallback) {
      $.ajax({
        type: 'get',
        url: '/schedule/getScheduleList.do',
        data: { "g_code": g_code }, // Change to your specific data
        dataType: 'json',
        success: function (response) {
          var fixedDate = response.map(function (array) {
           	var start = array.start;
        	var end = array.end;
      		var formattedStartDate = moment(start).format('YYYY-MM-DD HH:mm');
        	var formattedEndDate = moment(end).format('YYYY-MM-DD HH:mm');
         	array.start = formattedStartDate;
        	array.end = formattedEndDate;
            array.backgroundColor = "#" + array.backgroundColor;
            array.allday;
            return array;
          });
      
          successCallback(fixedDate);
        },
      });
    },eventContent: function (arg) {
  var event = arg.event;
  var element = arg.el;

  // FullCalendar 이벤트 렌더링 시 Bootstrap 팝오버 초기화
  $(element).popover({
    title: event.title,
    content: event.extendedProps.description,
    delay: {
      show: 800,
      hide: 50
    },
    trigger: 'hover',
    placement: 'top',
    html: true,
    container: 'body'
  });

  // FullCalendar 이벤트 클릭 시 팝오버 표시
  $(element).on('click', function () {
    $(element).popover('toggle');
  });

  // 이벤트를 클릭하면 filtering 함수 실행
  $(element).on('click', function () {
    filtering(event);
  });
},
    eventContent: function (arg) {
      var event = arg.event;
      var content = document.createElement('div');
      content.innerHTML = '<strong>' + event.title + '</strong>'+'<br>';
      content.innerHTML += '<div class="popovrDescCalendar"><strong>설명:</strong> ' + event.extendedProps.description + '</div>';
      return { domNodes: [content] };
    },
	   eventClick: function (info) {
	  var event = info.event;
	  editEvent(event);
	},
    eventDidMount: function (info) {
  if (info.view.type === 'dayGridMonth') {
    // 월간 뷰에서 이벤트 렌더링 이후 작업 수행
    $(".fc-content").css('height', 'auto');
 	 }
	},
    dateClick: function (info) {
      var startDate = info.date;
      var endDate = new Date(startDate);
      endDate.setHours(startDate.getHours() + 1); // Example: set the end time to 1 hour later
      newEvent(startDate, endDate);
    },viewDidMount: function (info) {
  if (info.view.type === 'dayGridMonth') {
    setTimeout(function () {
      $(".fc-daygrid-event").css('height', 'auto');
    }, 0);
  }
},
    eventDragStart: function (info) {
      var event = info.event;
      draggedEventIsAllDay = event._def.allDay;
    },
    eventDrop: function (eventDropInfo) {
  var event = eventDropInfo.event;
  var delta = eventDropInfo.delta;
  var view = eventDropInfo.view;

  if (id === event.extendedProps.userName) {
    $('.popover.fade.top').remove();

    // 주,일 view일때 종일 <-> 시간 변경불가
    if (view.type === 'timeGridWeek' || view.type === 'timeGridDay') {
      if (draggedEventIsAllDay !== event._def.allDay) {
        alert('드래그앤드롭으로 종일<->시간 변경은 불가합니다.');
        location.reload();
        return false;
      }
    }

    // 드롭시 수정된 날짜 반영
    var newDates = calDateWhenDragNDrop(event);

    // 드롭한 일정 업데이트
    $.ajax({
      type: "get",
      url: "/schedule/dragupdate.do",
      data: {
        "calno": event.extendedProps.calno,
        "start": newDates.startDate,
        "end": newDates.endDate
      },
      success: function (response) {
        alert('수정: ' + newDates.startDate + ' ~ ' + newDates.endDate);
      }
    });
  } else {
    alert("수정불가");
    location.reload();
  }
},select: function (info) {
  var selectstartDate = info.start;
  var selectendDate = info.end;
  var view = info.view;

  // 여기서 startDate와 endDate를 가공하거나 필요한 형식으로 포맷합니다.

  // 카테고리 선택 메뉴를 표시하거나 이벤트 생성 로직을 추가합니다.
  var $contextMenu = $("#contextMenu");
  $contextMenu.addClass("contextOpened").css({
    display: "block",
    left: info.jsEvent.pageX,
    top: info.jsEvent.pageY
  });

  $contextMenu.on("click", "a", function (e) {
    e.preventDefault();
    
    if ($(this).data().role !== 'close') {
      // 카테고리 선택 메뉴 항목을 클릭했을 때 실행할 로직 추가
      newEvent(startDate, endDate, $(this).html());
    }

    $contextMenu.removeClass("contextOpened");
    $contextMenu.hide();
  });

  $('body').on('click', function () {
    $contextMenu.removeClass("contextOpened");
    $contextMenu.hide();
  });
},
    eventResize: function (eventResizeInfo) {
  $(".popover.fade.top").remove();

  // 리사이즈시 수정된 날짜반영 (하루를 빼야 함)
  var newDates = calDateWhenResize(eventResizeInfo.event);

  // 리사이즈한 일정 업데이트
  $.ajax({
    type: "get",
    url: "/schedule/dragupdate.do",
    data: {
      "calno": eventResizeInfo.event.extendedProps.calno,
      "start": newDates.startDate,
      "end": newDates.endDate
    },
    success: function (response) {
      alert('수정: ' + newDates.startDate + ' ~ ' + newDates.endDate);
    }
  });
},
  });
	
  calendar.render();
var today = new Date();
var dateFormatOptions = {
  year: 'numeric',
  month: '2-digit',
  day: '2-digit',
  hour: '2-digit',
  minute: '2-digit',
};
var view = calendar.view; 
var startDate = new Date();
var endDate = new Date();
if (view.name == "month") {
  startDate.setHours(today.getHours(), today.getMinutes(), 0, 0);
  startDate = startDate.toLocaleString('en-US', dateFormatOptions);

  endDate.setHours(today.getHours() + 1, today.getMinutes(), 0, 0);
  endDate = endDate.toLocaleString('en-US', dateFormatOptions);
} else {
  startDate = startDate.toLocaleString('en-US', dateFormatOptions);
  endDate = endDate.toLocaleString('en-US', dateFormatOptions);
}
  function getDisplayEventDate(event) {
  var displayEventDate;

if (event._def.allDay==0) {
  var startTime = event._instance.range.start.toTimeString().slice(0, 5); // HH:mm 형식
    var endTime = event._instance.range.end.toTimeString().slice(0, 5); // HH:mm 형식
    displayEventDate = startTime + " - " + endTime;
} else {
    displayEventDate = "하루종일";
}

return displayEventDate;
  }
function calDateWhenResize(event) {
  var newDates = {
    startDate: '',
    endDate: ''
  };

 if (event._def.allDay) {
 
  newDates.startDate = event._instance.range.start.toISOString().slice(0, 10); // YYYY-MM-DD 형식
  var endDate = new Date(event.end);
  endDate.setDate(endDate.getDate() + 1); // 종일 일정의 경우 1일을 더해줍니다.
  newDates.endDate = endDate.toISOString().slice(0, 10); // YYYY-MM-DD 형식
} else {
  newDates.startDate = event._instance.range.start.toISOString().slice(0, 16); // YYYY-MM-DDTHH:mm 형식
  newDates.endDate = event._instance.range.end.toISOString().slice(0, 16); // YYYY-MM-DDTHH:mm 형식
}
  return newDates;
}
  function filtering(event) {
    var show_username = true;
    var username = document.querySelectorAll('.filter:checked').map(function () {
      return this.value;
    }).get();
    show_username = username.indexOf(event.extendedProps.username) >= 0;
    return show_username;
  }
 function calDateWhenDragNDrop(event) {
  // 드랍시 수정된 날짜반영
  var newDates = {
    startDate: '',
    endDate: ''
  };

  // 날짜 & 시간이 모두 같은 경우
  if (event._instance.range.start.toISOString() === event._instance.range.end.toISOString()) {
    newDates.startDate = event._instance.range.start.toISOString();
    newDates.endDate = event._instance.range.end.toISOString();
  }
  // 하루짜리 all day
  else if (event._def.allDay && event._instance.range.start.toISOString().slice(0, 10) === event._instance.range.end.toISOString().slice(0, 10)) {
    newDates.startDate = event._instance.range.start.toISOString().slice(0, 10);
    newDates.endDate = event._instance.range.start.toISOString().slice(0, 10);
  }
  // 2일 이상 all day
  else if (event._def.allDay && event._instance.range.start.toISOString().slice(0, 10) !== event._instance.range.end.toISOString().slice(0, 10)) {
    newDates.startDate = event._instance.range.start.toISOString().slice(0, 10);
    var endDate = new Date(event._instance.range.end);
    endDate.setDate(endDate.getDate() - 1);
    newDates.endDate = endDate.toISOString().slice(0, 10);
  }
  // all day가 아님
  else if (!event._def.allDay) {
    newDates.startDate = event._instance.range.start.toISOString().slice(0, 16);
    newDates.endDate = event._instance.range.end.toISOString().slice(0, 16);
  }

  return newDates;
}
});


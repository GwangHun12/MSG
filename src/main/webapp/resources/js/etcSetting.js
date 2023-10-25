$('#edit-color').on('change', function () {
    $(this).css('color', $(this).val());
});

$('.filter').on('change', function () {
    $('#calendar').getCalendar().refetchEvents();
});

$('#type_filter').select2({
    placeholder: '선택..',
    allowClear: true
});

flatpickr('#edit-start, #edit-end', {
    enableTime: true, // 시간 선택 가능
    dateFormat: 'Y-m-d H:i', // 원하는 날짜 및 시간 형식 지정
    allowInput: true // 직접 입력 가능하도록 설정
});
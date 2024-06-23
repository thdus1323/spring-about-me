$(document).ready(function () {
    const urlParams = new URLSearchParams(window.location.search);
    const voucherId = urlParams.get('voucherId');
    const expertId = urlParams.get('expertId');

    console.log('voucherId:', voucherId);
    console.log('expertId:', expertId);

    const today = new Date();
    let currentYear = today.getFullYear();
    let currentMonth = today.getMonth();
    let selectedDate = null;

    function renderCalendar(year, month) {
        const firstDay = new Date(Date.UTC(year, month, 1)).getUTCDay();
        const daysInMonth = new Date(Date.UTC(year, month + 1, 0)).getUTCDate();
        const monthYearText = `${month + 1}월 ${year}년`;

        $('#reservation-month-year').text(monthYearText);

        const daysHtml = [];
        for (let i = 0; i < firstDay; i++) {
            daysHtml.push('<div></div>');
        }
        for (let i = 1; i <= daysInMonth; i++) {
            const date = new Date(Date.UTC(year, month, i));
            const pastClass = date < today ? 'reservation-past' : '';
            const todayClass = date.toDateString() === today.toDateString() ? 'reservation-today' : '';
            daysHtml.push(`<div class="reservation-day ${pastClass} ${todayClass}" data-date="${i}" data-full-date="${date.toISOString().split('T')[0]}">${i}</div>`);
        }

        $('#reservation-days').html(`
        <div class="main-color reservation-disabled reservation-day">일</div>
        <div class="main-color reservation-disabled reservation-day">월</div>
        <div class="main-color reservation-disabled reservation-day">화</div>
        <div class="main-color reservation-disabled reservation-day">수</div>
        <div class="main-color reservation-disabled reservation-day">목</div>
        <div class="main-color reservation-disabled reservation-day">금</div>
        <div class="main-color reservation-disabled reservation-day">토</div>
        ${daysHtml.join('')}
    `);

        // Attach click event to each day
        $('#reservation-days .reservation-day').not('.reservation-past').click(function () {
            $('#reservation-days .reservation-day').removeClass('reservation-selected');
            $(this).addClass('reservation-selected');
            const fullDate = $(this).data('full-date');
            console.log("Selected full date:", fullDate); // 로그 추가
            displayTimes(fullDate);
            $('#reservation-payButton').prop('disabled', true).removeClass('active');
        });

        // Select the first available day automatically
        selectFirstAvailableDay();
    }

    function displayTimes(fullDate) {
        $.ajax({
            url: '/api/available-times',
            method: 'GET',
            data: {date: fullDate, expertId: expertId},
            success: function (times) {
                console.log("Available times for date", fullDate, ":", times);  // 로그 추가
                if (times.length === 0) {
                    $('#reservation-times').html('<p>해당 날짜에 예약 가능한 시간이 없습니다.</p>');
                } else {
                    $('#reservation-times').html(times.map(time => `<button class="reservation-time-btn" data-time="${time}">${time}</button>`).join(''));

                    // Attach click event to each time button
                    $('#reservation-times .reservation-time-btn').click(function () {
                        $('#reservation-times .reservation-time-btn').removeClass('active');
                        $(this).addClass('active');
                        $('#reservation-payButton').prop('disabled', false).addClass('active');
                    });
                }
            },
            error: function (xhr, status, error) {
                console.error("Failed to load available times:", error);
                $('#reservation-times').html('<p>시간을 로드하는데 실패했습니다.</p>');
            }
        });
    }

    function selectFirstAvailableDay() {
        // Select the first day after today
        const tomorrow = new Date(today);
        tomorrow.setDate(today.getDate());

        const firstAvailableDay = $('#reservation-days .reservation-day').filter(function () {
            const dayDate = new Date(Date.UTC(currentYear, currentMonth, $(this).data('date')));
            return dayDate >= tomorrow;
        }).first();

        if (firstAvailableDay.length) {
            firstAvailableDay.addClass('reservation-selected');
            selectedDate = firstAvailableDay.data('date');
            const fullDate = firstAvailableDay.data('full-date');
            displayTimes(fullDate);
            console.log("First available day selected:", selectedDate);
        } else {
            console.log("No available day found.");
        }
    }

    renderCalendar(currentYear, currentMonth);

    // Navigation buttons
    $('#reservation-prevMonth').click(function () {
        if (currentMonth > 0) {
            currentMonth--;
        } else {
            currentMonth = 11;
            currentYear--;
        }
        renderCalendar(currentYear, currentMonth);
    });

    $('#reservation-nextMonth').click(function () {
        if (currentMonth < 11) {
            currentMonth++;
        } else {
            currentMonth = 0;
            currentYear++;
        }
        renderCalendar(currentYear, currentMonth);
    });

    // 결제하기 버튼 활성화 시 색상 변경
    $('#reservation-payButton').click(function () {
        if (!$(this).prop('disabled')) { // 버튼이 활성화된 경우에만 색상 변경
            $(this).css('background-color', '#00bbba').css('color', 'white');
        }
    });
    
})
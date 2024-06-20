$(document).ready(function() {

    //옵션 열고 닫기
    $('.option-wrapper > form > div > p').click(function() {
        $(this).toggleClass('active');
        $(this).next('.option-list').slideToggle('slow', function() {
            $(this).toggleClass('active');
        });
    });

    const availableTimes = {
        weekdays: {
            1: ["10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00"],
            2: ["10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00"],
            3: ["10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00"],
            4: ["10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00"],
            5: ["10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00"]
        },
        weekends: {
            6: ["10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00"],
            7: ["10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00"]
        }
    };

    const monthNames = ["1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"];
    let currentMonth = new Date().getMonth();
    let currentDate = new Date().getDate();

    function renderCalendar(month) {
        const year = 2024;
        const firstDay = new Date(year, month).getDay();
        const daysInMonth = new Date(year, month + 1, 0).getDate();

        $('.month h3').text(`${monthNames[month]} ${year}년`);
        $('.days').html(`
                    <div class="main-color disabled day">일</div>
                    <div class="main-color disabled day">월</div>
                    <div class="main-color disabled day">화</div>
                    <div class="main-color disabled day">수</div>
                    <div class="main-color disabled day">목</div>
                    <div class="main-color disabled day">금</div>
                    <div class="main-color disabled day">토</div>
                `);

        for (let i = 0; i < firstDay; i++) {
            $('.days').append('<div></div>');
        }

        for (let i = 1; i <= daysInMonth; i++) {
            const date = new Date(year, month, i);
            const isWeekend = date.getDay() === 0 || date.getDay() === 6;

            $('.days').append(`
                        <div class="day" data-date="${i}" data-is-weekend="${isWeekend}">
                            ${i}
                        </div>
                    `);
        }

        disablePastDates();
        setActiveDate();
    }

    function disablePastDates() {
        const today = new Date();

        $('.days > div[data-date]').each(function() {
            const date = new Date(2024, currentMonth, $(this).data('date'));
            if (date < today) {
                $(this).addClass('past').off('click');
            }
        });
    }

    function setActiveDate() {
        const today = new Date();
        const $selectedDay = $(`.days > div[data-date="${currentDate}"]`);

        if (today.getFullYear() === 2024 && today.getMonth() === currentMonth) {
            $selectedDay.addClass('selected');
            displayTimes($selectedDay);
        }
    }

    function displayTimes(dayElement) {
        const date = dayElement.data('date');
        const isWeekend = dayElement.data('is-weekend') === 'true';
        let times;

        if (isWeekend) {
            times = availableTimes.weekends[date] || ["10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00"];
        } else {
            times = availableTimes.weekdays[date] || ["10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00"];
        }

        $('#times').html(times.map(time => `<button class="time-btn" data-time="${time}">${time}</button>`).join(''));

    }

    renderCalendar(currentMonth);

    $('.days').on('click', 'div[data-date]', function() {
        if ($(this).hasClass('past')) return;

        $('.days > div').removeClass('selected');
        $(this).addClass('selected');
        displayTimes($(this));
    });

    $('#prevMonth').on('click', function() {
        if (currentMonth > 0) {
            currentMonth--;
            renderCalendar(currentMonth);
        }
    });

    $('#nextMonth').on('click', function() {
        if (currentMonth < 11) {
            currentMonth++;
            renderCalendar(currentMonth);
        }
    });

    $('#times').on('click',function (){
        console.log("hi");
    })


    setActiveDate();

    // 마우스 핸들링 이벤트
    const times = $('#times');
    let isDown = false;
    let startX;
    let scrollLeft;

    times.mousedown(function(e) {
        isDown = true;
        times.css('cursor', 'grabbing');
        startX = e.pageX;
        scrollLeft = times.scrollLeft();
    });

    $(document).mousemove(function(e) {
        if (!isDown) return;
        e.preventDefault();
        const x = e.pageX;
        const walk = (x - startX);
        times.scrollLeft(scrollLeft - walk);
    });

    $(document).mouseup(function() {
        isDown = false;
        times.css('cursor', 'grab');
    });

});

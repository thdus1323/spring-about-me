$(document).ready(function() {

    //옵션 열고 닫기
    $('.option-wrapper > form > div > p').click(function() {
        $(this).toggleClass('active');
        $(this).next('.option-list').slideToggle('slow', function() {
            $(this).toggleClass('active');
        });
    });


    // 옵션 클릭 시 .active 클래스 추가 및 제거
    $('.situation .option-list.btn li').on('click', function() {
        $('.situation .option-list.btn li').removeClass('active');
        $(this).addClass('active');
    });

    $('.state .option-list.btn li').on('click', function() {
        $('.state .option-list.btn li').removeClass('active');
        $(this).addClass('active');
    });

    const availableTimes = {
        1: ["10:00", "11:00", "12:00"],
        2: ["13:00", "14:00", "15:00"],
        3: ["16:00", "17:00", "18:00"],
        4: ["19:00", "20:00", "21:00"],
        5: ["10:00", "11:00", "12:00"],
        6: ["13:00", "14:00", "15:00"],
        7: ["16:00", "17:00", "18:00"],
        8: ["19:00", "20:00", "21:00"],
        9: ["10:00", "11:00", "12:00"],
        10: ["13:00", "14:00", "15:00"],
        11: ["16:00", "17:00", "18:00"],
        12: ["19:00", "20:00", "21:00"],
        13: ["10:00", "11:00", "12:00"],
        14: ["13:00", "14:00", "15:00"],
        15: ["16:00", "17:00", "18:00"],
        16: ["19:00", "20:00", "21:00"],
        17: ["10:00", "11:00", "12:00"],
        18: ["13:00", "14:00", "15:00"],
        19: ["16:00", "17:00", "18:00"],
        20: ["19:00", "20:00", "21:00"],
        21: ["10:00", "11:00", "12:00"],
        22: ["13:00", "14:00", "15:00"],
        23: ["16:00", "17:00", "18:00"],
        24: ["19:00", "20:00", "21:00"],
        25: ["10:00", "11:00", "12:00"],
        26: ["13:00", "14:00", "15:00"],
        27: ["16:00", "17:00", "18:00"],
        28: ["19:00", "20:00", "21:00"],
        29: ["10:00", "11:00", "12:00"],
        30: ["13:00", "14:00", "15:00"]
    };

    const monthNames = ["1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"];
    let currentMonth = new Date().getMonth(); // 현재 월
    let currentDate = new Date().getDate(); // 현재 날짜

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
            $('.days').append(`<div data-date="${i}">${i}</div>`);
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
        if (today.getFullYear() === 2024 && today.getMonth() === currentMonth) {
            $(`.days > div[data-date="${currentDate}"]`).addClass('selected');
            const times = availableTimes[currentDate] || [];
            $('#times').html(times.map(time => `<button class="time-btn">${time}</button>`).join(''));
        }
    }

    renderCalendar(currentMonth);

    $('.days').on('click', 'div[data-date]', function() {
        if ($(this).hasClass('past')) return;

        $('.days > div').removeClass('selected');
        $(this).addClass('selected');

        const date = $(this).data('date');
        const times = availableTimes[date] || [];
        $('#times').html(times.map(time => `<button class="time-btn">${time}</button>`).join(''));
    });

    $('#times').on('click', '.time-btn', function() {
        $('.time-btn').removeClass('active');
        $(this).addClass('active');
        $('#payButton').addClass('active').prop('disabled', false);
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
    // Set the current date as active on page load
    setActiveDate();



    // 폼데이터 보내기
    // 폼 데이터 전송 시 선택된 옵션의 value 값을 포함
    $('form').on('submit', function(e) {
        e.preventDefault(); // 폼 전송 막기 (디버깅용)

        const selectedSituations = [];
        const selectedSymptoms = [];

        $('.situation .option-list.btn li.active').each(function() {
            selectedSituations.push($(this).data('value'));
        });

        $('.state .option-list.btn li.active').each(function() {
            selectedSymptoms.push($(this).data('value'));
        });

        const formData = {
            situations: selectedSituations,
            symptoms: selectedSymptoms
        };

        console.log(formData); // 폼 데이터 확인 (디버깅용)

        // 실제 폼 전송 로직 추가
        // 예: $.post('your-endpoint-url', formData);
    });
});

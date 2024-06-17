$(document).ready(function() {
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
        18: ["18:00", "19:00", "20:00", "21:00"],
        19: ["13:00", "14:00", "15:00"],
        20: ["16:00", "17:00", "18:00"],
        21: ["19:00", "20:00", "21:00"],
        22: ["10:00", "11:00", "12:00"],
        23: ["13:00", "14:00", "15:00"],
        24: ["16:00", "17:00", "18:00"],
        25: ["19:00", "20:00", "21:00"],
        26: ["10:00", "11:00", "12:00"],
        27: ["13:00", "14:00", "15:00"],
        28: ["16:00", "17:00", "18:00"],
        29: ["19:00", "20:00", "21:00"],
        30: ["10:00", "11:00", "12:00"]
    };

    const today = new Date();
    const currentYear = today.getFullYear();
    const currentMonth = today.getMonth() + 1; // JavaScript months are 0-based

    $('.calendar .days div[data-date]').each(function() {
        const day = parseInt($(this).data('date'));
        const date = new Date(currentYear, currentMonth - 1, day);

        if (date < today) {
            $(this).addClass('past').off('click');
        }
    });

    $('.calendar .days div[data-date]').click(function() {
        if (!$(this).hasClass('past')) {
            $('.calendar .days div').removeClass('selected');
            $(this).addClass('selected');
            const date = $(this).data('date');
            const times = availableTimes[date] || [];
            $('#times').html(times.map(time => `<button class="time-btn">${time}</button>`).join(''));
        }
    });

    $('#times').on('click', '.time-btn', function() {
        $('.time-btn').removeClass('active');
        $(this).addClass('active');
        $('#payButton').addClass('active').prop('disabled', false);
    });
});
$(document).ready(function () {
    $('.toggle-text').each(function () {
        const target = $(this).data('target');
        const content = $(target);
        const overlay = content.find('.overlay');
        const fullHeight = content[0].scrollHeight;

        // 초기 상태 설정
        if (content.height() < fullHeight) {
            overlay.show();
        }

        $(this).click(function () {
            const expanded = content.hasClass('expanded');

            if (expanded) {
                content.removeClass('expanded');
                $(this).text('더보기');
                overlay.show();
            } else {
                content.addClass('expanded');
                $(this).text('접기');
                overlay.hide();
            }
        });
    });
});

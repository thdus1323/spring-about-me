$(document).ready(function () {

    // 리뷰 섹션 더보기/접기 기능
    const reviewsToShow = 2; // 초기 표시할 리뷰 개수
    const $reviewItems = $('.review-item'); // 수정: 리뷰 카드를 대상으로 설정
    const $buttonMore = $('.btn-toggle.more');
    const $buttonLess = $('.btn-toggle.less');

    // 초기 상태 설정: 첫 번째 reviewsToShow개의 리뷰만 표시
    $reviewItems.hide().slice(0, reviewsToShow).show();

    // "후기 더보기" 버튼 클릭 시 동작
    $buttonMore.on('click', function () {
        const visibleReviews = $reviewItems.filter(':visible').length;
        $reviewItems.slice(visibleReviews, visibleReviews + reviewsToShow).slideDown();
        if ($reviewItems.filter(':visible').length >= $reviewItems.length) {
            $buttonMore.hide();
        }
        $buttonLess.show();
    });

    // "접기" 버튼 클릭 시 동작
    $buttonLess.on('click', function () {

        $reviewItems.slice(reviewsToShow).slideUp();
        $buttonLess.hide();
        $buttonMore.show();
    });
    //
    $('.toggle-text').each(function () {

        const target = $(this).data('target');
        const content = $(target);
        const overlay = content.find('.overlay');
        const fullHeight = content[0].scrollHeight;

        // 초기 상태 설정
        if (content.height() < fullHeight) {
            overlay.show();
        }

        $(this).click(function (e) {
            const expanded = content.hasClass('expanded');
            console.log(e)
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

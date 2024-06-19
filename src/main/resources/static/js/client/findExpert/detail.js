
$(document).ready(function () {
    const reviewsToShow = 2;
    const $reviewItems = $('.review-item');
    const $buttonMore = $('.btn-toggle.more');
    const $buttonLess = $('.btn-toggle.less');

    // 초기 상태 설정
    $reviewItems.hide().slice(0, reviewsToShow).show();

    // "더보기" 버튼 클릭
    $buttonMore.on('click', function () {
        const visibleReviews = $reviewItems.filter(':visible').length;
        $reviewItems.slice(visibleReviews, visibleReviews + reviewsToShow).slideDown();
        // "접기" 버튼을 표시하고 "더보기" 버튼을 업데이트
        if ($reviewItems.filter(':visible').length >= reviewsToShow) {
            $buttonLess.show();
        }
        if ($reviewItems.filter(':visible').length >= $reviewItems.length) {
            $buttonMore.hide();
        }
    });

    // "접기" 버튼 클릭
    $buttonLess.on('click', function () {
        $reviewItems.not(':lt(' + reviewsToShow + ')').slideUp();
        $buttonLess.hide();
        $buttonMore.show();
    });
});
    
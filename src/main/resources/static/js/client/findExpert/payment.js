$(document).ready(function() {
    const originalPrice = 62000; // 원래 가격
    let discountAmount = 0; // 할인 금액

    // 쿠폰 적용 버튼 클릭 시
    $('#applyCoupon').on('click', function() {
        const selectedCoupon = $('#coupon').val();
        discountAmount = selectedCoupon ? parseInt(selectedCoupon) : 0;
        updateFinalAmount();
    });

    // 최종 결제 금액 업데이트 함수
    function updateFinalAmount() {
        const finalAmount = originalPrice - discountAmount;
        $('.amount-details .total span').text(finalAmount.toLocaleString() + '원');
        $('.amount-details .coupon-discount span').text('-' + discountAmount.toLocaleString() + '원');
        $('#payButton').text(finalAmount.toLocaleString() + '원 결제하기');
    }

    // 초기화
    updateFinalAmount();
});
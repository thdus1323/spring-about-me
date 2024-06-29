$(document).ready(function () {
    // 출생 연도 셀렉트 박스에 옵션 추가
    const currentYear = new Date().getFullYear();
    for (let year = currentYear; year >= 1900; year--) {
        $('#edit-birth-year').append(new Option(year, year));
    }

    // 프로필 이미지 클릭 시 파일 입력 열기
    $('#profileImageEdit').on('click', function () {
        $('#profileImageInput').click();
    });

    // 프로필 이미지 파일 선택 시
    $('#profileImageInput').on('change', function (event) {
        const reader = new FileReader();
        reader.onload = function (e) {
            $('#profileImageEdit').attr('src', e.target.result);
        };
        reader.readAsDataURL(event.target.files[0]);
    });

    // 프로필 수정 버튼 클릭 시
    $('#editProfile').on('click', function () {
        // 현재 표시된 연도를 셀렉트 박스에서 선택
        const birthYear = $('#birth-year').text();
        $('#edit-birth-year').val(birthYear);

        $('#profile-view-card').addClass('d-none');
        $('#profile-edit-card').removeClass('d-none');
    });

    // 취소 버튼 클릭 시
    $('#cancelEdit').on('click', function () {
        $('#profile-edit-card').addClass('d-none');
        $('#profile-view-card').removeClass('d-none');
    });

    // 저장 버튼 클릭 시
    $('#saveProfile').on('click', function () {
        // 수정된 값 가져오기
        const username = $('#edit-username').val();
        const birthYear = $('#edit-birth-year').val();
        const gender = $('input[name="gender"]:checked').val();
        const profileImageSrc = $('#profileImageEdit').attr('src');

        // 보기 모드에 값 반영
        $('#username').text(username);
        $('#birth-year').text(birthYear);
        $('#gender').text(gender);
        $('#profileImageView').attr('src', profileImageSrc);

        // 폼 전환
        $('#profile-edit-card').addClass('d-none');
        $('#profile-view-card').removeClass('d-none');

        // 실제 저장 로직은 여기에 추가
        // 예: 서버로 데이터 전송
        $.ajax({
            url: '/client/profiles',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({
                username: username,
                birthYear: birthYear,
                gender: gender,
                profileImage: profileImageSrc // 이미지 URL
            }),
            success: function (response) {
                if (response.success) {
                    alert(response.message);
                } else {
                    alert('저장에 실패했습니다: ' + response.message);
                }
            },
            error: function (xhr) {
                let message = '저장에 실패했습니다.';
                if (xhr.responseJSON && xhr.responseJSON.message) {
                    message += ' ' + xhr.responseJSON.message;
                }
                alert(message);
            }
        });
    });

    // 탭 클릭 시 콘텐츠 전환 (내 이용권 및 커뮤니티 활동 탭 모두 적용)
    $('.tabs .tab').on('click', function () {
        const target = $(this).data('tab');

        // 모든 탭 비활성화
        $(this).closest('.tabs').find('.tab').removeClass('active');
        // 클릭한 탭 활성화
        $(this).addClass('active');

        // 모든 탭 콘텐츠 숨기기
        $(this).closest('.tabs-container').find('.tab-content').removeClass('active');
        // 클릭한 탭의 콘텐츠 표시
        $('#' + target).addClass('active');
    });

    // 좋아요 버튼 클릭 시
    $('.like-btn').on('click', function () {
        const $post = $(this).closest('.post');
        const postId = $post.data('post-id');
        const $likeIcon = $(this);
        const $likeCountSpan = $likeIcon.siblings('span');

        // 현재 좋아요 상태 확인 (비어있는 하트: 좋아요 안 함, 채워진 하트: 좋아요 함)
        const isLiked = $likeIcon.hasClass('fas');

        // 서버에 AJAX 요청 보내기
        $.ajax({
            url: '/like', // 서버의 엔드포인트 URL
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({postId: postId, like: !isLiked}), // 좋아요 상태 전환
            success: function (response) {
                if (response.success) {
                    // 서버 응답에서 새로운 좋아요 수를 받아와 업데이트
                    $likeCountSpan.text(response.newLikeCount);
                    // 좋아요 상태 전환
                    $likeIcon.toggleClass('fas far');
                } else {
                    alert('좋아요를 반영하는데 실패했습니다.');
                }
            },
            error: function () {
                alert('서버와 통신 중 오류가 발생했습니다.');
            }
        });
    });
});

function editReservation() {
    // 상담 예약 변경 로직을 여기에 추가
    alert('상담 일정을 변경합니다.');
}

function writeReview(id) {
    alert('리뷰를 작성하시겠습니까?')
}

function makeReservation(id) {
    alert('예약하시겠습니까?')
}

function cancelReservation(id) {
    // 상담 예약 취소 로직을 여기에 추가
    alert("상담실로 입장하시겠습니까?")
}